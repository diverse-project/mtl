/*
* $Id: MTLCore.java,v 1.5 2004-06-03 13:08:52 sdzale Exp $
* Authors : ${user}
*
* Created on ${date}
* Copyright 2004 - INRIA - LGPL license
*/ 
package org.inria.mtl.plugin.core;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.xerces.dom.DocumentImpl;
import org.apache.xml.serialize.Method;
import org.apache.xml.serialize.OutputFormat;
import org.apache.xml.serialize.Serializer;
import org.apache.xml.serialize.SerializerFactory;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaModelStatusConstants;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.internal.core.Assert;
import org.eclipse.jdt.internal.core.ClasspathEntry;
import org.eclipse.jdt.internal.core.JavaModel;
import org.eclipse.jdt.internal.core.JavaProject;
import org.eclipse.jdt.internal.core.Util;
import org.eclipse.jface.preference.IPreferenceStore;
import org.inria.mtl.plugin.MTLPlugin;
import org.inria.mtl.plugin.builders.MTLModel;
import org.inria.mtl.plugin.preferences.PreferenceConstants;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;



public  class MTLCore  {

	/**
		 * Value of the mtlclasspath extension. 
		 */

	public static final String MTLCLASSPATH_FILENAME = ".mtlclasspath";  //$NON-NLS-1$
	
	/**
		 * Value of the project's raw classpath if the .mtlclasspath file contains invalid entries.
		 */
	public static final IClasspathEntry[] INVALID_CLASSPATH = new IClasspathEntry[0];
	
	/**
	 * An empty array of strings indicating that a project doesn't have any prerequesite projects.
	 */
	protected static final String[] NO_PREREQUISITES = new String[0];

	
	private static IProject fCurrProject;
	
	/**
	 * Reads the .mtlclasspath file from disk and returns the list of entries it contains 
	 * Returns null if .mtlclassfile is not present.
	 * Returns INVALID_CLASSPATH if it has a format problem.
	 */
	public static IClasspathEntry[] readClasspathFile() {
		try {
			String xmlClasspath = getSharedProperty(MTLCLASSPATH_FILENAME);
			if (xmlClasspath == null) return null;
			return decodeClasspath(xmlClasspath);
		} catch(CoreException e) {
			// file does not exist (or not accessible)
			System.out.println("File does not exists");
		//	JavaCore
	
		}
		return null;
	}

	public static IProject getProject()
	{
		return fCurrProject;
	}
	
	public static void setProject(IProject proj)
		{
			fCurrProject=proj;
		}
	
	public static IClasspathEntry getResolvedClasspathEntry(IClasspathEntry entry) {
	
		if (entry.getEntryKind() != IClasspathEntry.CPE_VARIABLE)
			return entry;
	
		IWorkspaceRoot workspaceRoot = ResourcesPlugin.getWorkspace().getRoot();
		
		IPath resolvedPath = getResolvedVariablePath(entry.getPath());
		if (resolvedPath == null)
			return null;
	
		Object target = JavaModel.getTarget(workspaceRoot, resolvedPath, false);
		if (target == null)
			return null;
	
		// inside the workspace
		if (target instanceof IResource) {
			IResource resolvedResource = (IResource) target;
			if (resolvedResource != null) {
				switch (resolvedResource.getType()) {
					
					case IResource.PROJECT :  
						// internal project
						return newProjectEntry(resolvedPath, entry.isExported());
						
					case IResource.FILE : 
						if (Util.isArchiveFileName(resolvedResource.getName())) {
							// internal binary archive
							return newLibraryEntry(
									resolvedPath,
									getResolvedVariablePath(entry.getSourceAttachmentPath()),
									getResolvedVariablePath(entry.getSourceAttachmentRootPath()),
									entry.isExported());
						}
						break;
						
					case IResource.FOLDER : 
						// internal binary folder
						return  newLibraryEntry(
								resolvedPath,
								getResolvedVariablePath(entry.getSourceAttachmentPath()),
								getResolvedVariablePath(entry.getSourceAttachmentRootPath()),
								entry.isExported());
				}
			}
		}
		// outside the workspace
		if (target instanceof File) {
			File externalFile = (File) target;
			if (externalFile.isFile()) {
				String fileName = externalFile.getName().toLowerCase();
				if (fileName.endsWith(".tll"  //$NON-NLS-1$
					)) { // external binary archive
					return newLibraryEntry(
							resolvedPath,
							getResolvedVariablePath(entry.getSourceAttachmentPath()),
							getResolvedVariablePath(entry.getSourceAttachmentRootPath()),
							entry.isExported());
				}
			} else { // external binary folder
				if (resolvedPath.isAbsolute()){
					return newLibraryEntry(
							resolvedPath,
							getResolvedVariablePath(entry.getSourceAttachmentPath()),
							getResolvedVariablePath(entry.getSourceAttachmentRootPath()),
							entry.isExported());
				}
			}
		}
		return null;
	}

	public static IPath getResolvedVariablePath(IPath variablePath) {
	
		if (variablePath == null)
			return null;
		int count = variablePath.segmentCount();
		if (count == 0)
			return null;
	
		// lookup variable	
		String variableName = variablePath.segment(0);
		IPath resolvedPath = JavaCore.getClasspathVariable(variableName);
		if (resolvedPath == null)
			return null;
	
		// append path suffix
		if (count > 1) {
			resolvedPath = resolvedPath.append(variablePath.removeFirstSegments(1));
		}
		return resolvedPath; 
	}

	/**
		 * Creates and returns a new classpath entry of kind <code>CPE_CONTAINER</code>
		 * for the given path. The path of the container will be used during resolution so as to map this
		 * container entry to a set of other classpath entries the container is acting for.
		 * <p>
		 * A container entry allows to express indirect references to a set of libraries, projects and variable entries,
		 * which can be interpreted differently for each Java project where it is used.
		 * A classpath container entry can be resolved using <code>JavaCore#getResolvedClasspathContainer</code>,
		 * and updated with <code>JavaCore#classpathContainerChanged</code>
		 * <p>
		 * A container is exclusively resolved by a <code>ClasspathContainerInitializer</code> registered onto the
		 * extension point "org.eclipse.jdt.core.classpathContainerInitializer".
		 * <p>
		 * A container path must be formed of at least one segment, where: <ul>
		 * <li> the first segment is a unique ID identifying the target container, there must be a container initializer registered
		 * 	onto this ID through the extension point  "org.eclipse.jdt.core.classpathContainerInitializer". </li>
		 * <li> the remaining segments will be passed onto the initializer, and can be used as additional
		 * 	hints during the initialization phase. </li>
		 * </ul>
		 * <p>
		 * Example of an ClasspathContainerInitializer for a classpath container denoting a default JDK container:
		 * 
		 * containerEntry = JavaCore.newContainerEntry(new Path("MyProvidedJDK/default"));
		 * 
		 * <extension
		 *    point="org.eclipse.jdt.core.classpathContainerInitializer">
		 *    <containerInitializer
		 *       id="MyProvidedJDK"
		 *       class="com.example.MyInitializer"/> 
		 * <p>
		 * Note that this operation does not attempt to validate classpath containers
		 * or access the resources at the given paths.
		 * <p>
		 * The resulting entry is not exported to dependent projects. This method is equivalent to
		 * <code>newContainerEntry(-,false)</code>.
		 * <p>
		 * @param containerPath the path identifying the container, it must be formed of two
		 * 	segments
		 * @return a new container classpath entry
		 * 
		*/
		public static IClasspathEntry newContainerEntry(IPath containerPath) {
			
			return newContainerEntry(containerPath, false);
		}

		/**
		 * Creates and returns a new classpath entry of kind <code>CPE_CONTAINER</code>
		 * for the given path. The path of the container will be used during resolution so as to map this
		 * container entry to a set of other classpath entries the container is acting for.
		 * <p>
		 * A container entry allows to express indirect references to a set of libraries, projects and variable entries,
		 * which can be interpreted differently for each Java project where it is used.
		 * A classpath container entry can be resolved using <code>JavaCore#getResolvedClasspathContainer</code>,
		 * and updated with <code>JavaCore#classpathContainerChanged</code>
		 * <p>
		 * A container is exclusively resolved by a <code>ClasspathContainerInitializer</code> registered onto the
		 * extension point "org.eclipse.jdt.core.classpathContainerInitializer".
		 * <p>
		 * A container path must be formed of at least one segment, where: <ul>
		 * <li> the first segment is a unique ID identifying the target container, there must be a container initializer registered
		 * 	onto this ID through the extension point  "org.eclipse.jdt.core.classpathContainerInitializer". </li>
		 * <li> the remaining segments will be passed onto the initializer, and can be used as additional
		 * 	hints during the initialization phase. </li>
		 * </ul>
		 * <p>
		 * Example of an ClasspathContainerInitializer for a classpath container denoting a default JDK container:
		 * 
		 * containerEntry = JavaCore.newContainerEntry(new Path("MyProvidedJDK/default"));
		 * 
		 * <extension
		 *    point="org.eclipse.jdt.core.classpathContainerInitializer">
		 *    <containerInitializer
		 *       id="MyProvidedJDK"
		 *       class="com.example.MyInitializer"/> 
		 * <p>
		 * Note that this operation does not attempt to validate classpath containers
		 * or access the resources at the given paths.
		 * <p>
		 * @param containerPath the path identifying the container, it must be formed of at least
		 * 	one segment (ID+hints)
		 * @param isExported a boolean indicating whether this entry is contributed to dependent
		 *    projects in addition to the output location
		 * @return a new container classpath entry
		 * 
		 * @see JavaCore#getClasspathContainer(IPath, IJavaProject)
		 * @see JavaCore#setClasspathContainer(IPath, IJavaProject[], IClasspathContainer[], IProgressMonitor)
		 * @see JavaCore#newContainerEntry(IPath, boolean)
		 * @since 2.0
		 */
		public static IClasspathEntry newContainerEntry(IPath containerPath, boolean isExported) {
			
			Assert.isTrue(
				containerPath != null && containerPath.segmentCount() >= 1,
				Util.bind("classpath.illegalContainerPath" )); //$NON-NLS-1$
			return new MtlClasspathEntry(
						IPackageFragmentRoot.K_SOURCE,
						MtlClasspathEntry.K_COMP, 
						containerPath,
						MtlClasspathEntry.NO_EXCLUSION_PATTERNS, 
						null, // source attachment
						null, // source attachment root
						null, // specific output folder
						isExported);
		}


	public static IClasspathEntry newLibraryEntry(
		IPath path,
		IPath sourceAttachmentPath,
		IPath sourceAttachmentRootPath) {
			
		return newLibraryEntry(path, sourceAttachmentPath, sourceAttachmentRootPath, false);
	}

	public static IClasspathEntry newLibraryEntry(
		IPath path,
		IPath sourceAttachmentPath,
		IPath sourceAttachmentRootPath,
		boolean isExported) {
			
		if (!path.isAbsolute()) Assert.isTrue(false, "Path for IClasspathEntry must be absolute"); //$NON-NLS-1$

		return new MtlClasspathEntry(
			IPackageFragmentRoot.K_BINARY,
			IClasspathEntry.CPE_LIBRARY,
			JavaProject.canonicalizedPath(path),
			ClasspathEntry.NO_EXCLUSION_PATTERNS, 
			sourceAttachmentPath,
			sourceAttachmentRootPath,
			null, // specific output folder
			isExported);
	}

	public static IClasspathEntry newProjectEntry(IPath path) {
		return newProjectEntry(path, false);
	}
	
public static IClasspathEntry newProjectEntry(IPath path, boolean isExported) {
		
		if (!path.isAbsolute()) Assert.isTrue(false, "Path for IClasspathEntry must be absolute"); //$NON-NLS-1$
		
		return new MtlClasspathEntry(
			IPackageFragmentRoot.K_SOURCE,
			IClasspathEntry.CPE_PROJECT,
			path,
			MtlClasspathEntry.NO_EXCLUSION_PATTERNS, 
			null, // source attachment
			null, // source attachment root
			null, // specific output folder
			isExported);
	}
	public static IClasspathEntry newSourceEntry(IPath path) {

		return newSourceEntry(path, MtlClasspathEntry.NO_EXCLUSION_PATTERNS, null /*output location*/);
	}
	
	public static IClasspathEntry newSourceEntry(IPath path, IPath[] exclusionPatterns) {

		return newSourceEntry(path, exclusionPatterns, null /*output location*/); 
	}

	public static IClasspathEntry newSourceEntry(IPath path, IPath[] exclusionPatterns, IPath specificOutputLocation) {

		if (!path.isAbsolute()){ 
						Assert.isTrue(false, "Path for IClasspathEntry must be absolute"); }//$NON-NLS-1$
		if (exclusionPatterns == null){
						Assert.isTrue(false, "Exclusion pattern set cannot be null"); }//$NON-NLS-1$
		//System.out.println("MTLClasspath exécuté");
		return new MtlClasspathEntry(
			IPackageFragmentRoot.K_SOURCE,
			IClasspathEntry.CPE_SOURCE,
			path,
			exclusionPatterns,
			null, // source attachment
			null, // source attachment root
			specificOutputLocation, // custom output location
			false);
	}
		
		/**
		 * Reads and decode an XML classpath string
		 */
public static IClasspathEntry[] decodeClasspath(String xmlClasspath) {

	ArrayList paths = new ArrayList();
	IClasspathEntry defaultOutput = null;
	try {
		if (xmlClasspath == null) return null;
		StringReader reader = new StringReader(xmlClasspath);
		Element cpElement;
	
		try {
			DocumentBuilder parser =
				DocumentBuilderFactory.newInstance().newDocumentBuilder();
			cpElement = parser.parse(new InputSource(reader)).getDocumentElement();
		} catch (SAXException e) {
			throw new IOException(Util.bind("file.badFormat")); //$NON-NLS-1$
		} catch (ParserConfigurationException e) {
			throw new IOException(Util.bind("file.badFormat")); //$NON-NLS-1$
		} finally {
			reader.close();
		}
	
		if (!cpElement.getNodeName().equalsIgnoreCase("classpath")) { //$NON-NLS-1$
			throw new IOException(Util.bind("file.badFormat")); //$NON-NLS-1$
		}
		NodeList list = cpElement.getElementsByTagName("classpathentry"); //$NON-NLS-1$
					
		int length = list.getLength();
	
		for (int i = 0; i < length; ++i) {
			Node node = list.item(i);
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				IClasspathEntry entry = MtlClasspathEntry.elementDecode((Element)node, getProject());
				if (entry != null){
					if (entry.getContentKind() == MtlClasspathEntry.K_OUTPUT) { 
						defaultOutput = entry; // separate output
					} else {
						paths.add(entry);
			}
		}
			}
		}
	} catch (IOException e) {
		// bad format
	return INVALID_CLASSPATH;
	} catch (Assert.AssertionFailedException e) { 
		// failed creating CP entries from file
		return INVALID_CLASSPATH;
	}
	int pathSize = paths.size();
	if (pathSize > 0 || defaultOutput != null) {
		IClasspathEntry[] entries = new IClasspathEntry[pathSize + (defaultOutput == null ? 0 : 1)];
		paths.toArray(entries);
		if (defaultOutput != null) entries[pathSize] = defaultOutput; // ensure output is last item
		return entries;
	} else {
		return null;
	}
}

		/**
		 * Returns a default class path.
		 * This is the root of the project
		 */
		public static IClasspathEntry[] defaultClasspath() throws JavaModelException {

			return new IClasspathEntry[] { newSourceEntry(getProject().getFullPath())};
		}

		/**
		 * Returns the XML String encoding of the class path.
		 */
		public static String encodeClasspath(IClasspathEntry[] classpath, IPath outputLocation, boolean useLineSeparator) throws JavaModelException {

			Document document = new DocumentImpl();
			Element cpElement = document.createElement("classpath"); //$NON-NLS-1$
			document.appendChild(cpElement);

			for (int i = 0; i < classpath.length; ++i) {
					cpElement.appendChild(((MtlClasspathEntry)classpath[i]).elementEncode(document, getProject().getFullPath()));
			}

			if (outputLocation != null) {
				outputLocation = outputLocation.removeFirstSegments(1);
				outputLocation = outputLocation.makeRelative();
				Element oElement = document.createElement("classpathentry"); //$NON-NLS-1$
				oElement.setAttribute("kind", MtlClasspathEntry.kindToString(MtlClasspathEntry.K_OUTPUT));	//$NON-NLS-1$
				oElement.setAttribute("path", outputLocation.toString()); //$NON-NLS-1$
				cpElement.appendChild(oElement);
			}

			// produce a String output
			try {
				ByteArrayOutputStream s = new ByteArrayOutputStream();
				OutputFormat format = new OutputFormat();
				if (useLineSeparator) {
					format.setIndenting(true);
					format.setLineSeparator(System.getProperty("line.separator"));  //$NON-NLS-1$
				} else {
					format.setPreserveSpace(true);
				}			
				Serializer serializer =
					SerializerFactory.getSerializerFactory(Method.XML).makeSerializer(
						new OutputStreamWriter(s, "UTF8"), //$NON-NLS-1$
						format);
				serializer.asDOMSerializer().serialize(document);
				return s.toString("UTF8"); //$NON-NLS-1$
			} catch (IOException e) {
				throw new JavaModelException(e, IJavaModelStatusConstants.IO_EXCEPTION);
			}
		}

		/**
		 * @see IJavaProject
		 */
public static IClasspathEntry[] getRawClasspath() throws JavaModelException {

			IClasspathEntry[] classpath = readClasspathFile();
		
			// extract out the output location
			IPath outputLocation = null;
			if (classpath != null && classpath.length > 0) {
				IClasspathEntry entry = classpath[classpath.length - 1];
				if (entry.getContentKind() == MtlClasspathEntry.K_OUTPUT) {
					outputLocation = entry.getPath();
					IClasspathEntry[] copy = new IClasspathEntry[classpath.length - 1];
					System.arraycopy(classpath, 0, copy, 0, copy.length);
					classpath = copy;
				}
			}
			return classpath;
		}

		/**
		 * @see IJavaProject#getRequiredProjectNames
		 */
//		public String[] getRequiredProjectNames() throws JavaModelException {
//
//			return projectPrerequisites(getResolvedClasspath(true));
//		}


public static String[] projectPrerequisites(IClasspathEntry[] entries)
			throws JavaModelException {
			
			ArrayList prerequisites = new ArrayList();
			for (int i = 0, length = entries.length; i < length; i++) {
				IClasspathEntry entry = entries[i];
				if (entry.getEntryKind() == IClasspathEntry.CPE_PROJECT) {
					prerequisites.add(entry.getPath().lastSegment());
				}
			}
			int size = prerequisites.size();
			if (size == 0) {
					return NO_PREREQUISITES;
				} else {
					String[] result = new String[size];
					prerequisites.toArray(result);
				return result;
				}
		}
		
	/**
	 * Compare current classpath with given one to see if any different.
	 * Note that the argument classpath contains its binary output.
	 */
public static boolean isClasspathEqualsTo(IClasspathEntry[] newClasspath, IPath newOutputLocation, IClasspathEntry[] otherClasspathWithOutput)
		throws JavaModelException {

		if (otherClasspathWithOutput != null && otherClasspathWithOutput.length > 0) {

			int length = otherClasspathWithOutput.length;
			if (length == newClasspath.length + 1) {
				// output is amongst file entries (last one)

				// compare classpath entries
				for (int i = 0; i < length - 1; i++) {
					if (!otherClasspathWithOutput[i].equals(newClasspath[i]))
						return false;
				}
				// compare binary outputs
				IClasspathEntry output = otherClasspathWithOutput[length - 1];
				if (output.getContentKind() == ClasspathEntry.K_OUTPUT
						&& output.getPath().equals(newOutputLocation))
					return true;
			}
		}
		return false;
	}
	


	/**
		 * Saves the classpath in a shareable format (VCM-wise) only when necessary, that is, if  it is semantically different
		 * from the existing one in file. Will never write an identical one.
		 * 
		 * @return Return whether the .classpath file was modified.
		 */
public static boolean saveClasspath(IClasspathEntry[] newClasspath, IPath newOutputLocation) throws JavaModelException {
			
			if (!getProject().exists())return false;
			IClasspathEntry[] fileEntries = readClasspathFile(); 
			if (fileEntries != null && isClasspathEqualsTo(newClasspath, newOutputLocation, fileEntries)) {
			//	if (fileEntries != null ) {
				// no need to save it, it is the same
					return false;
				}

			// actual file saving
			try {
				setSharedProperty(MTLCLASSPATH_FILENAME, encodeClasspath(newClasspath, newOutputLocation, true));
				return true;
			} catch (CoreException e) {
				throw new JavaModelException(e);
			}
		}

// création du fichier mtlclasspath
public static void setSharedProperty(String key, String value) throws CoreException {

	IFile rscFile = getProject().getFile(key);
	InputStream inputStream = new ByteArrayInputStream(value.getBytes());
	// update the resource content
	if (rscFile.exists()) {
		if (rscFile.isReadOnly()) {
			// provide opportunity to checkout read-only .classpath file (23984)
			ResourcesPlugin.getWorkspace().validateEdit(new IFile[]{rscFile}, null);
		}
		rscFile.setContents(inputStream, IResource.FORCE, null);
	} else {
		rscFile.create(inputStream, IResource.FORCE, null);
	}
}


public static String getSharedProperty(String key) throws CoreException {

	String property = null;
	
	IFile rscFile = getProject().getFile(key);
	if (rscFile.exists()) {
		property = new String(Util.getResourceContentsAsByteArray(rscFile));
	}
	return property;
}

public static String checkPathEnd(String path)
	{	if (path.charAt(path.length()-1)=='/'
			|| path.charAt(path.length()-1)=='\\')
			return path;
		return path+'/';
	}
	
/**
* this method read the mtlclasspath file and find all mtl source folders
*@param  mtlclasspath  .mtlclasspath file
*@return srcMtlFolderList 
*@exception  Exception  all errors reported by
*/
public static void findFolders() {
	IPreferenceStore store=MTLPlugin.getDefault().getPreferenceStore();
				
				ArrayList lSources = new ArrayList();
				ArrayList lProj = new ArrayList(); 
				ArrayList lLib = new ArrayList();
				 
				MTLCore.setProject(MTLModel.getProject());
				IPath projPath =MTLCore.fCurrProject.getFullPath();
				//System.out.println("Projet :"+projPath); 
				IClasspathEntry[] classpathEntries = MTLCore.readClasspathFile();
				for (int i= 0; i < classpathEntries.length; i++) {
					IClasspathEntry curr= classpathEntries[i];
					if( curr.getEntryKind()==IClasspathEntry.CPE_SOURCE){
						if (projPath.isPrefixOf(curr.getPath())){
							lSources.add(curr.getPath());
						}else{
							lProj.add(curr.getPath()); 
						}
					}
					if( curr.getEntryKind()==MtlClasspathEntry.K_COMP){
									  lProj.add(curr.getPath());
									  store.setValue(PreferenceConstants.MTL_COMPILER_PATH,(curr.getPath()).toOSString());
						}
					if( curr.getEntryKind()==IClasspathEntry.CPE_LIBRARY){
										lLib.add(curr.getPath());
					}
				}
				
				MTLPlugin.srcFolders=new IPath[lSources.size()];
				MTLModel.srcFolders=new IPath[lSources.size()];
				MTLPlugin.projFolders=new IPath[lProj.size()];
				MTLModel.projFolders=new IPath[lProj.size()];
				MTLPlugin.libFolders=new IPath[lLib.size()];
				MTLModel.libFolders=new IPath[lLib.size()];
				for (int i= 0; i < lSources.size(); i++) {
					MTLPlugin.srcFolders[i]=(IPath)lSources.get(i);
					MTLModel.srcFolders[i]=(IPath)lSources.get(i);
					//System.out.println("Src :"+((IPath)lSources.get(i)).makeAbsolute());			  
				}
				for (int i= 0; i < lProj.size(); i++) {
						MTLPlugin.projFolders[i]=(IPath)lProj.get(i);
						MTLModel.projFolders[i]=(IPath)lProj.get(i);
					//	System.out.println("Proj :"+((IPath)lProj.get(i)).makeAbsolute());			  
				}
				for (int i= 0; i < lLib.size(); i++) {
							MTLPlugin.libFolders[i]=(IPath)lLib.get(i);
							MTLModel.libFolders[i]=(IPath)lLib.get(i);
					//		System.out.println("Lib :"+((IPath)lLib.get(i)).makeAbsolute());			  
				}
	}
			

}
/*
* $Id: IMtlJavaProject.java,v 1.2 2004-05-19 09:21:52 sdzale Exp $
* Authors : ${user}
*
* Created on ${date}
* Copyright 2004 - INRIA - LGPL license
*/ 
package org.inria.mtl.plugin.wizards; 
import org.eclipse.jdt.core.*;
import org.eclipse.core.runtime.IPath;

/**
 * A MTL Java project represents a view of a project resource in terms of MTL and Java 
 * elements such as package fragments, types, methods and fields.
 * A project may contain several package roots, which contain package fragments. 
 * A package root corresponds to an underlying folder or JAR.
 * <p>
 * Each Java project has a classpath, defining which folders contain source code and
 * where required libraries are located. Each Java project also has an output location,
 * defining where the builder writes <code>.class</code> files. A project that
 * references packages in another project can access the packages by including
 * the required project in a classpath entry. The Java model will present the
 * source elements in the required project; when building, the compiler will use
 * the corresponding generated class files from the required project's output
 * location(s)). The classpath format is a sequence of classpath entries
 * describing the location and contents of package fragment roots.
 * </p>
 * Java project elements need to be opened before they can be navigated or manipulated.
 * The children of a Java project are the package fragment roots that are 
 * defined by the classpath and contained in this project (in other words, it
 * does not include package fragment roots for other projects).
 * </p>
 * <p>
 * This interface is not intended to be implemented by clients. An instance
 * of one of these handles can be created via 
 * <code>JavaCore.create(project)</code>.
 * </p>
 *
 * @see JavaCore#create(org.eclipse.core.resources.IProject)
 * @see IClasspathEntry
 */
public interface IMtlJavaProject extends IJavaProject {

	
	/**
	 * Returns the default output location for this project as a workspace-
	 * relative absolute path.
	 * <p>
	 * The default output location is where class files are ordinarily generated
	 * (and resource files, copied). Each source classpath entry can also
	 * specify an output location for the generated class files (and copied
	 * resource files) corresponding to compilation units under that source
	 * folder. This makes it possible to arrange generated class files for
	 * different source folders in different output folders, and not
	 * necessarily the default output folder. This means that the generated
	 * class files for the project may end up scattered across several folders,
	 * rather than all in the default output folder (which is more standard).
	 * </p>
	 * 
	 * @return the workspace-relative absolute path of the default output folder
	 * @exception JavaModelException if this element does not exist
	 * @see #setOutputLocation
	 * @see IClasspathEntry#getOutputLocation
	 */
	IPath getMtlOutputLocation() throws JavaModelException;


	/**
	 * Returns the raw classpath for the project, as a list of classpath entries. This corresponds to the exact set
	 * of entries which were assigned using <code>setRawClasspath</code>, in particular such a classpath may contain
	 * classpath variable entries. Classpath variable entries can be resolved individually (see <code>JavaCore#getClasspathVariable</code>),
	 * or the full classpath can be resolved at once using the helper method <code>getResolvedClasspath</code>.
	 * <p>
	 * A classpath variable provides an indirection level for better sharing a classpath. As an example, it allows
	 * a classpath to no longer refer directly to external JARs located in some user specific location. The classpath
	 * can simply refer to some variables defining the proper locations of these external JARs.
	 *  <p>
	 * Note that in case the project isn't yet opened, the classpath will directly be read from the associated <tt>.classpath</tt> file.
	 * <p>
	 * 
	 * @return the raw classpath for the project, as a list of classpath entries
	 * @exception JavaModelException if this element does not exist or if an
	 *		exception occurs while accessing its corresponding resource
	 * @see IClasspathEntry
	 */
	IClasspathEntry[] getMtlRawClasspath() throws JavaModelException;

	/**
	 * Returns the names of the projects that are directly required by this
	 * project. A project is required if it is in its classpath.
	 * <p>
	 * The project names are returned in the order they appear on the classpath.
	 *
	 * @return the names of the projects that are directly required by this
	 * project in classpath order
	 * @exception JavaModelException if this element does not exist or if an
	 *		exception occurs while accessing its corresponding resource
	 */
	String[] getMtlRequiredProjectNames() throws JavaModelException;

	/**
	 * This is a helper method returning the resolved classpath for the project
	 * as a list of simple (non-variable, non-container) classpath entries.
	 * All classpath variable and classpath container entries in the project's
	 * raw classpath will be replaced by the simple classpath entries they
	 * resolve to.
	 * <p>
	 * The resulting resolved classpath is accurate for the given point in time.
	 * If the project's raw classpath is later modified, or if classpath
	 * variables are changed, the resolved classpath can become out of date.
	 * Because of this, hanging on resolved classpath is not recommended.
	 * </p>
	 * 
	 * @param ignoreUnresolvedEntry indicates how to handle unresolvable
	 * variables and containers; <code>true</code> indicates that missing
	 * variables and unresolvable classpath containers should be silently
	 * ignored, and that the resulting list should consist only of the
	 * entries that could be successfully resolved; <code>false</code> indicates
	 * that a <code>JavaModelException</code> should be thrown for the first
	 * unresolved variable or container
	 * @return the resolved classpath for the project as a list of simple 
	 * classpath entries, where all classpath variable and container entries
	 * have been resolved and substituted with their final target entries
	 * @exception JavaModelException in one of the corresponding situation:
	 * <ul>
	 *    <li>this element does not exist</li>
	 *    <li>an exception occurs while accessing its corresponding resource</li>
	 *    <li>a classpath variable or classpath container was not resolvable
	 *    and <code>ignoreUnresolvedEntry</code> is <code>false</code>.</li>
	 * </ul>
	 * @see IClasspathEntry
	 */
	IClasspathEntry[] getMtlResolvedClasspath(boolean ignoreUnresolvedEntry)
		 throws JavaModelException;

	/**
	 * Returns whether this project has been built at least once and thus whether it has a build state.
	 * @return true if this project has been built at least once, false otherwise
	 */
	//boolean hasBuildState();

	/**
	 * Returns whether setting this project's classpath to the given classpath entries
	 * would result in a cycle.
	 *
	 * If the set of entries contains some variables, those are resolved in order to determine
	 * cycles.
	 * 
	 * @param entries the given classpath entries
	 * @return true if the given classpath entries would result in a cycle, false otherwise
	 */
	//boolean hasClasspathCycle(IClasspathEntry[] entries);
	/**
	 * Returns whether the given element is on the classpath of this project, 
	 * that is, referenced from a classpath entry and not explicitly excluded
	 * using an exclusion pattern. 
	 * 
	 * @param element the given element
	 * @exception JavaModelException if this element does not exist or if an
	 *		exception occurs while accessing its corresponding resource
	 * @return <code>true</code> if the given element is on the classpath of
	 * this project, <code>false</code> otherwise
	 * @see IClasspathEntry#getExclusionPatterns
	 * @since 2.0
	 */
	//boolean isOnClasspath(IJavaElement element) throws JavaModelException;
	/**
	 * Returns whether the given resource is on the classpath of this project,
	 * that is, referenced from a classpath entry and not explicitly excluded
	 * using an exclusion pattern.
	 * 
	 * @param element the given element
	 * @exception JavaModelException if this project does not exist or if an
	 *		exception occurs while accessing its corresponding resource
	 * @return <code>true</code> if the given resource is on the classpath of
	 * this project, <code>false</code> otherwise
	 * @see IClasspathEntry#getExclusionPatterns
	 * @since 2.1
	 */
	//boolean isOnClasspath(IResource resource) throws JavaModelException;

	
	/**
	 * Sets the default output location of this project to the location
	 * described by the given workspace-relative absolute path.
	 * <p>
	 * The default output location is where class files are ordinarily generated
	 * (and resource files, copied). Each source classpath entries can also
	 * specify an output location for the generated class files (and copied
	 * resource files) corresponding to compilation units under that source
	 * folder. This makes it possible to arrange that generated class files for
	 * different source folders to end up in different output folders, and not
	 * necessarily the default output folder. This means that the generated
	 * class files for the project may end up scattered across several folders,
	 * rather than all in the default output folder (which is more standard).
	 * </p>
	 * 
	 * @param path the workspace-relative absolute path of the default output
	 * folder
	 * @param monitor the progress monitor
	 * 
	 * @exception JavaModelException if the classpath could not be set. Reasons include:
	 * <ul>
	 *  <li> This Java element does not exist (ELEMENT_DOES_NOT_EXIST)</li>
	 *  <li> The path refers to a location not contained in this project (<code>PATH_OUTSIDE_PROJECT</code>)
	 *  <li> The path is not an absolute path (<code>RELATIVE_PATH</code>)
	 *  <li> The path is nested inside a package fragment root of this project (<code>INVALID_PATH</code>)
	 *  <li> The output location is being modified during resource change event notification (CORE_EXCEPTION)	 
	 * </ul>
	 * @see #getOutputLocation
	 * @see IClasspathEntry#getOutputLocation
	 */
	//void setOutputLocation(IPath path, IProgressMonitor monitor)
	//	throws JavaModelException;

	/**
	 * Sets the classpath of this project using a list of classpath entries. In particular such a classpath may contain
	 * classpath variable entries. Classpath variable entries can be resolved individually (see <code>JavaCore#getClasspathVariable</code>),
	 * or the full classpath can be resolved at once using the helper method <code>getResolvedClasspath</code>.
	 * <p>
	 * A classpath variable provides an indirection level for better sharing a classpath. As an example, it allows
	 * a classpath to no longer refer directly to external JARs located in some user specific location. The classpath
	 * can simply refer to some variables defining the proper locations of these external JARs.
	 * <p>
	 * Setting the classpath to <code>null</code> specifies a default classpath
	 * (the project root). Setting the classpath to an empty array specifies an
	 * empty classpath.
	 * <p>
	 * If a cycle is detected while setting this classpath, an error marker will be added
	 * to the project closing the cycle.
	 * To avoid this problem, use <code>hasClasspathCycle(IClasspathEntry[] entries)</code>
	 * before setting the classpath.
	 *
	 * @param entries a list of classpath entries
	 * @param monitor the given progress monitor
	 * @exception JavaModelException if the classpath could not be set. Reasons include:
	 * <ul>
	 * <li> This Java element does not exist (ELEMENT_DOES_NOT_EXIST)</li>
	 * <li> The classpath is being modified during resource change event notification (CORE_EXCEPTION)
	 * <li> The classpath failed the validation check as defined by <code>JavaConventions#validateClasspath</code>
	 * </ul>
	 * @see IClasspathEntry
	 */
	//void setRawClasspath(IClasspathEntry[] entries, IProgressMonitor monitor)
	//	throws JavaModelException;
		
	/**
	 * Sets the both the classpath of this project and its default output
	 * location at once. The classpath is defined using a list of classpath
	 * entries. In particular, such a classpath may contain classpath variable
	 * entries. Classpath variable entries can be resolved individually (see
	 * <code>JavaCore#getClasspathVariable</code>), or the full classpath can be
	 * resolved at once using the helper method
	 * <code>getResolvedClasspath</code>.
	 * <p>
	 * A classpath variable provides an indirection level for better sharing a
	 * classpath. As an example, it allows a classpath to no longer refer
	 * directly to external JARs located in some user specific location. The
	 * classpath can simply refer to some variables defining the proper
	 * locations of these external JARs.
	 * </p>
	 * <p>
	 * Setting the classpath to <code>null</code> specifies a default classpath
	 * (the project root). Setting the classpath to an empty array specifies an
	 * empty classpath.
	 * </p>
	 * <p>
	 * If a cycle is detected while setting this classpath, an error marker will
	 * be added to the project closing the cycle. To avoid this problem, use
	 * <code>hasClasspathCycle(IClasspathEntry[] entries)</code> before setting
	 * the classpath.
	 * </p>
	 * 
	 * @param entries a list of classpath entries
	 * @param monitor the progress monitor
	 * @param outputLocation the default output location
	 * @exception JavaModelException if the classpath could not be set. Reasons
	 * include:
	 * <ul>
	 * <li> This Java element does not exist (ELEMENT_DOES_NOT_EXIST)</li>
	 * <li> Two or more entries specify source roots with the same or overlapping paths (NAME_COLLISION)
	 * <li> A entry of kind <code>CPE_PROJECT</code> refers to this project (INVALID_PATH)
	 *  <li>This Java element does not exist (ELEMENT_DOES_NOT_EXIST)</li>
	 *	<li>The output location path refers to a location not contained in this project (<code>PATH_OUTSIDE_PROJECT</code>)
	 *	<li>The output location path is not an absolute path (<code>RELATIVE_PATH</code>)
	 *  <li>The output location path is nested inside a package fragment root of this project (<code>INVALID_PATH</code>)
	 * <li> The classpath is being modified during resource change event notification (CORE_EXCEPTION)
	 * </ul>
	 * @see IClasspathEntry
	 * @since 2.0
	 */
//	void setRawClasspath(IClasspathEntry[] entries, IPath outputLocation, IProgressMonitor monitor)
//		throws JavaModelException;
}
/*
* $Id: CPListLabelProvider.java,v 1.1 2004-07-30 14:11:19 sdzale Exp $
* Authors : ${user}
*
* Created on ${date}
* Copyright 2004 - INRIA - LGPL license
*/ 
package org.inria.mtl.wizards.pages; 
import org.eclipse.jdt.internal.ui.wizards.buildpaths.*;

import java.net.URL;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IPath;

import org.eclipse.swt.graphics.Image;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.LabelProvider;

import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbench;

import org.eclipse.jdt.core.IClasspathContainer;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;

import org.eclipse.jdt.ui.JavaElementImageDescriptor;

import org.eclipse.jdt.internal.ui.JavaPlugin;
import org.eclipse.jdt.internal.ui.JavaPluginImages;
import org.eclipse.jdt.internal.ui.viewsupport.ImageDescriptorRegistry;
import org.eclipse.jdt.internal.ui.viewsupport.JavaElementImageProvider;
import org.eclipse.jdt.internal.ui.wizards.NewWizardMessages;

class CPListLabelProvider extends LabelProvider {
		
	private String fNewLabel, fClassLabel, fCreateLabel;
	private ImageDescriptor fJarIcon, fExtJarIcon, fJarWSrcIcon, fExtJarWSrcIcon;
	private ImageDescriptor fFolderImage, fProjectImage, fVariableImage, fContainerImage;
	
	private ImageDescriptorRegistry fRegistry;
	
	public CPListLabelProvider() {
		fNewLabel= NewWizardMessages.getString("CPListLabelProvider.new"); //$NON-NLS-1$
		fClassLabel= NewWizardMessages.getString("CPListLabelProvider.classcontainer"); //$NON-NLS-1$
		fCreateLabel= NewWizardMessages.getString("CPListLabelProvider.willbecreated"); //$NON-NLS-1$
		fRegistry= JavaPlugin.getImageDescriptorRegistry();
		
		fJarIcon= JavaPluginImages.DESC_OBJS_JAR;
		fExtJarIcon= JavaPluginImages.DESC_OBJS_EXTJAR;
		fJarWSrcIcon= JavaPluginImages.DESC_OBJS_JAR_WSRC;
		fExtJarWSrcIcon= JavaPluginImages.DESC_OBJS_EXTJAR_WSRC;
		fFolderImage= JavaPluginImages.DESC_OBJS_PACKFRAG_ROOT;
		fContainerImage= JavaPluginImages.DESC_OBJS_LIBRARY;
		fVariableImage= JavaPluginImages.DESC_OBJS_ENV_VAR;

		IWorkbench workbench= JavaPlugin.getDefault().getWorkbench();
		fProjectImage= workbench.getSharedImages().getImageDescriptor(ISharedImages.IMG_OBJ_PROJECT);
	}
	
	public String getText(Object element) {
		if (element instanceof CPListElement) {
			return getCPListElementText((CPListElement) element);
		} else if (element instanceof CPListElementAttribute) {
			return getCPListElementAttributeText((CPListElementAttribute) element);
		}
		return super.getText(element);
	}
	
	public String getCPListElementAttributeText(CPListElementAttribute attrib) {
		String notAvailable= NewWizardMessages.getString("CPListLabelProvider.none"); //$NON-NLS-1$
		StringBuffer buf= new StringBuffer();
		String key= attrib.getKey();
		if (key.equals(CPListElement.SOURCEATTACHMENT)) {
			buf.append(NewWizardMessages.getString("CPListLabelProvider.source_attachment.label")); //$NON-NLS-1$
			IPath path= (IPath) attrib.getValue();
			if (path != null && !path.isEmpty()) {
				if (attrib.getParent().getEntryKind() == IClasspathEntry.CPE_VARIABLE) {
					buf.append(getVariableString(path));
				} else {
					buf.append(getPathString(path, path.getDevice() != null));
				}
			} else {
				buf.append(notAvailable);
			}
		} else if (key.equals(CPListElement.SOURCEATTACHMENTROOT)) {
			buf.append(NewWizardMessages.getString("CPListLabelProvider.source_attachment_root.label")); //$NON-NLS-1$
			IPath path= (IPath) attrib.getValue();
			if (path != null && !path.isEmpty()) {
				buf.append(path.toString());
			} else {
				buf.append(notAvailable);
			}
		} else if (key.equals(CPListElement.JAVADOC)) {
			buf.append(NewWizardMessages.getString("CPListLabelProvider.javadoc_location.label")); //$NON-NLS-1$
			URL path= (URL) attrib.getValue();
			if (path != null) {
				buf.append(path.toExternalForm());
			} else {
				buf.append(notAvailable);
			}
		} else if (key.equals(CPListElement.OUTPUT)) {
			buf.append(NewWizardMessages.getString("CPListLabelProvider.output_folder.label")); //$NON-NLS-1$
			IPath path= (IPath) attrib.getValue();
			if (path != null) {
				buf.append(path.makeRelative().toString());
			} else {
				buf.append(NewWizardMessages.getString("CPListLabelProvider.default_output_folder.label")); //$NON-NLS-1$
			}
		} else if (key.equals(CPListElement.EXCLUSION)) {
			buf.append(NewWizardMessages.getString("CPListLabelProvider.exclusion_filter.label")); //$NON-NLS-1$
			IPath[] patterns= (IPath[]) attrib.getValue();
			if (patterns != null && patterns.length > 0) {
				for (int i= 0; i < patterns.length; i++) {
					if (i > 0) {
						buf.append(NewWizardMessages.getString("CPListLabelProvider.exclusion_filter_separator")); //$NON-NLS-1$
					}
					buf.append(patterns[i].toString());
				}
			} else {
				buf.append(notAvailable);
			}
		}
		return buf.toString();
	}
	
	public String getCPListElementText(CPListElement cpentry) {
		IPath path= cpentry.getPath();
		switch (cpentry.getEntryKind()) {
			case IClasspathEntry.CPE_LIBRARY: {
				IResource resource= cpentry.getResource();
				if (resource instanceof IFolder) {
					StringBuffer buf= new StringBuffer(path.makeRelative().toString());
					buf.append(' ');
					buf.append(fClassLabel);
					if (!resource.exists()) {
						buf.append(' ');
						if (cpentry.isMissing()) {
							buf.append(fCreateLabel);
						} else {
							buf.append(fNewLabel);
						}
					}
					return buf.toString();
				} else if (ArchiveFileFilter.isArchivePath(path)) {
					return getPathString(path, resource == null);
				}
				// should not come here
				return path.makeRelative().toString();
			}
			case IClasspathEntry.CPE_VARIABLE: {
				return getVariableString(path);
			}
			case IClasspathEntry.CPE_PROJECT:
				return path.lastSegment();
			case IClasspathEntry.CPE_CONTAINER:
				try {
					IClasspathContainer container= JavaCore.getClasspathContainer(cpentry.getPath(), cpentry.getJavaProject());
					if (container != null) {
						return container.getDescription();
					}
				} catch (JavaModelException e) {
	
				}
				return path.toString();
			case IClasspathEntry.CPE_SOURCE: {
				StringBuffer buf= new StringBuffer(path.makeRelative().toString());
				IResource resource= cpentry.getResource();
				if (resource != null && !resource.exists()) {
					buf.append(' ');
					if (cpentry.isMissing()) {
						buf.append(fCreateLabel);
					} else {
						buf.append(fNewLabel);
					}
				}
				return buf.toString();
			}
			default:
				// pass
		}
		return NewWizardMessages.getString("CPListLabelProvider.unknown_element.label"); //$NON-NLS-1$
	}
	
	private String getPathString(IPath path, boolean isExternal) {
		if (ArchiveFileFilter.isArchivePath(path)) {
			IPath appendedPath= path.removeLastSegments(1);
			String appended= isExternal ? appendedPath.toOSString() : appendedPath.makeRelative().toString();
			return NewWizardMessages.getFormattedString("CPListLabelProvider.twopart", new String[] { path.lastSegment(), appended }); //$NON-NLS-1$
		} else {
			return isExternal ? path.toOSString() : path.makeRelative().toString();
		}
	}
	
	private String getVariableString(IPath path) {
		String name= path.makeRelative().toString();
		IPath entryPath= JavaCore.getClasspathVariable(path.segment(0));
		if (entryPath != null) {
			String appended= entryPath.append(path.removeFirstSegments(1)).toOSString();
			return NewWizardMessages.getFormattedString("CPListLabelProvider.twopart", new String[] { name, appended }); //$NON-NLS-1$
		} else {
			return name;
		}
	}
	
	private ImageDescriptor getCPListElementBaseImage(CPListElement cpentry) {
		switch (cpentry.getEntryKind()) {
			case IClasspathEntry.CPE_SOURCE:
				if (cpentry.getPath().segmentCount() == 1) {
					return fProjectImage;
				} else {
					return fFolderImage;
				}
			case IClasspathEntry.CPE_LIBRARY:
				IResource res= cpentry.getResource();
				IPath path= (IPath) cpentry.getAttribute(CPListElement.SOURCEATTACHMENT);
				if (res == null) {
					if (path == null || path.isEmpty()) {
						return fExtJarIcon;
					} else {
						return fExtJarWSrcIcon;
					}
				} else if (res instanceof IFile) {
					if (path == null || path.isEmpty()) {
						return fJarIcon;
					} else {
						return fJarWSrcIcon;
					}
				} else {
					return fFolderImage;
				}
			case IClasspathEntry.CPE_PROJECT:
				return fProjectImage;
			case IClasspathEntry.CPE_VARIABLE:
				return fVariableImage;
			case IClasspathEntry.CPE_CONTAINER:
				return fContainerImage;
			default:
				return null;
		}
	}			
		
	public Image getImage(Object element) {
		if (element instanceof CPListElement) {
			CPListElement cpentry= (CPListElement) element;
			ImageDescriptor imageDescriptor= getCPListElementBaseImage(cpentry);
			if (imageDescriptor != null) {
				if (cpentry.isMissing()) {
					imageDescriptor= new JavaElementImageDescriptor(imageDescriptor, JavaElementImageDescriptor.WARNING, JavaElementImageProvider.SMALL_SIZE);
				}
				return fRegistry.get(imageDescriptor);
			}
		} else if (element instanceof CPListElementAttribute) {
			String key= ((CPListElementAttribute) element).getKey();
			if (key.equals(CPListElement.SOURCEATTACHMENT)) {
				return fRegistry.get(JavaPluginImages.DESC_OBJS_CUNIT); // todo: change image
			} else if (key.equals(CPListElement.JAVADOC)) {
				return fRegistry.get(JavaPluginImages.DESC_OBJS_HTMLTAG); // todo: change image
			} else if (key.equals(CPListElement.OUTPUT)) {
				return fRegistry.get(JavaPluginImages.DESC_OBJS_OUTPUT_FOLDER_ATTRIB);
			} else if (key.equals(CPListElement.EXCLUSION)) {
				return fRegistry.get(JavaPluginImages.DESC_OBJS_EXCLUSION_FILTER_ATTRIB);
			}
			return  fRegistry.get(fVariableImage);
		}
		return null;
	}


}	
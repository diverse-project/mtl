package org.inria.mtl.plugin.builders;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.resources.IResourceVisitor;

import org.inria.mtl.plugin.MTLPlugin;
import org.inria.mtl.plugin.preferences.MTLPreferencePage;
import org.inria.mtl.plugin.preferences.PreferenceConstants;


/**
 *  Helper class that implements resource listener events
 *
 *@author     Serge DZALE
 *@created    20 April 2004
 */
public class ResourceChangeListener
		 implements IResourceChangeListener, IResourceDeltaVisitor, IResourceVisitor {

	private String fileOwner = null;
	private boolean Auto_build;


	/**
	 *  Constructor for ResourceChangeListener
	 */
	public ResourceChangeListener() {
		super();
		Auto_build=MTLPlugin.getDefault().getPreferenceStore().getBoolean(PreferenceConstants.AUTO_COMPILE);	
	}

	/**
	 *  Constructor for the ResourceChangeListener object
	 *
	 *@param  fileOwner  Description of the Parameter
	 */
	public ResourceChangeListener(String fileOwner) {
		super();
		this.fileOwner = fileOwner;
	}


	/**
	 *  Sets the fileOwner attribute of the ResourceChangeListener object
	 *
	 *@param  fileOwner  The new fileOwner value
	 */
	public void setFileOwner(String fileOwner) {
		this.fileOwner = fileOwner;
	}


	/**
	 *@param  event  
	 *@see           IResourceChangeListener#resourceChanged(IResourceChangeEvent)
	 */
	public void resourceChanged(IResourceChangeEvent event) {

		System.out.print("Resource Changed " + event.getResource().getName());

		try {
			IResource res = event.getResource();
			switch (event.getType()) {
				case IResourceChangeEvent.PRE_CLOSE:
					break;
				case IResourceChangeEvent.PRE_DELETE:
					break;
				case IResourceChangeEvent.POST_CHANGE:
					if (Auto_build){
						event.getDelta().accept(this);
					}
					break;
				case IResourceChangeEvent.PRE_AUTO_BUILD:
					break;
				case IResourceChangeEvent.POST_AUTO_BUILD:
					break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	/**
	 *  Passes the event on to the MTLModel object for the attached project
	 *
	 *@param  delta  the change
	 *@return        
	 */
	public boolean visit(IResourceDelta delta) {
		IResource res = delta.getResource();
		if (res.getType() == IResource.FILE || res.getType() == IResource.FOLDER) {
			MTLPlugin.instance().getModel(res.getProject()).setProject(res.getProject());
			return MTLPlugin.instance().getModel(res.getProject()).processDelta(delta);
		} else {
			return true;
		}

	}

	/**
	 *  Passes the resource to be handled onto the MTLModel object for the attached project
	 *
	 *@param  res  
	 *@return      
	 */
	public boolean visit(IResource res) {
		if (res.getType() == IResource.FILE || res.getType() == IResource.FOLDER) {
			MTLPlugin.instance().getModel(res.getProject()).setProject(res.getProject());
			return MTLPlugin.instance().getModel(res.getProject()).processResource(res);
		} else {
			return true;
		}
	}

}

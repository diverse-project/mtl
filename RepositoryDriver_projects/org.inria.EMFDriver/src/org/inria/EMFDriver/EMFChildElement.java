/* $Id: EMFChildElement.java,v 1.2 2004-09-15 08:12:09 jpthibau Exp $
 * Authors : 
 * 
 * Copyright 2003 - INRIA - LGPL license
 */
package org.inria.EMFDriver;

import org.eclipse.emf.edit.command.*;
import org.eclipse.emf.common.command.*;
/**
 * @author jpthibau
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class EMFChildElement {
	public Object owner;
	public CommandParameter childDescriptor;
	public Command creationCommand;
	public EMFMetaClass metaClass;
	public boolean hasCreateCmd;
	public boolean isAbstarct;
	
	public EMFChildElement(Object o,CommandParameter cp,Command cmd) {
		this.owner = o;
		this.childDescriptor = cp;
		this.creationCommand = cmd;
		this.hasCreateCmd=true;
		this.isAbstarct=false;
	}

	public EMFChildElement(Object o,boolean isAbstract) {
		this.owner = o;
		this.hasCreateCmd=false;
		this.isAbstarct=isAbstract;
	}
}

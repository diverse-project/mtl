/* $Id: EMFChildElement.java,v 1.1 2004-04-28 10:20:48 jpthibau Exp $
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
	
	public EMFChildElement(Object o,CommandParameter cp,Command cmd) {
		this.owner = o;
		this.childDescriptor = cp;
		this.creationCommand = cmd;
	}

}

/*
* $Id: MTLMethodObject.java,v 1.2 2004-05-19 09:21:39 sdzale Exp $
* Authors : ${user}
*
* Created on ${date}
* Copyright 2004 - INRIA - LGPL license
*/ 
package org.inria.mtl.plugin.editors.mtlsyntax;

/**
 * @author sdzale
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class MTLMethodObject extends MTLObject{
  private String FunctionUsage;
  
  public void setDescription(String Usage) {
	this.FunctionUsage = Usage;
  }
  public String getDescription() {
	return this.FunctionUsage;
  }

  public String getHoverText() {
	return super.getHoverText()+"\n\n"+getDescription();
  }
  
  public MTLMethodObject(String Name, String Description, String Usage) {
	super(Name, Description);
	setDescription(Usage);
  }
}

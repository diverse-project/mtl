/*
* $Id: MTLMethodObject.java,v 1.2 2004-08-26 12:40:58 sdzale Exp $
* Authors : ${user}
*
* Created on ${date}
* Copyright 2004 - INRIA - LGPL license
*/ 
package org.inria.mtl.editors.mtlsyntax;

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

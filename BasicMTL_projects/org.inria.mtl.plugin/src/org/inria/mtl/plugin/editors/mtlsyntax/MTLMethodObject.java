/*
 * Created on 7 mai 2004
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
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

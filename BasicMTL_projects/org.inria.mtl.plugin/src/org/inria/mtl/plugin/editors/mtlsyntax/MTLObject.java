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
public abstract class  MTLObject {

	private String ElementName;
	private String ElementUsage;

	//Setters
	public final void setName(String ElementName) {
	  this.ElementName = ElementName;
	}
	public final void setUsage(String ElementDescription) {
	  this.ElementUsage = ElementDescription;
	}

	//Getters
	public final String getName() {
	  return ElementName;
	}
  
	public final String getUsage() {
	  return ElementUsage;
	}

	public String getHoverText() {
	  return getUsage();
	}
  
	public MTLObject() {
	}
  
	public MTLObject(String Name, String Usage) {
	  setName(Name);
	  if ((Usage == null) || (Usage.equals(""))) {
		setUsage(Name + " - ");
	  } else {
		setUsage(Usage);
	  }
	}

  }

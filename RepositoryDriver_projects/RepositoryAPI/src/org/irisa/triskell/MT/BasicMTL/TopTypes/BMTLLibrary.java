package org.irisa.triskell.MT.BasicMTL.TopTypes;
import org.irisa.triskell.MT.DataTypes.Java.commands.*;
import org.irisa.triskell.MT.DataTypes.Java.*;
import org.irisa.triskell.MT.repository.API.Java.API;
import java.util.*;

/*
 * Created on May 26, 2003
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */

/**
 * @author ffondeme
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public abstract class BMTLLibrary extends BMTLObject {
	
	public BMTLLibrary (String name) {
		super(name);
	}

	public BMTLLibrary getLibrary() {
		return this;
	}
	
	public String [] getQualifiedName () {
		if (this.qualifiedName == null)
			this.qualifiedName = new String [] {this.getName()};
		return this.qualifiedName;
	}

}

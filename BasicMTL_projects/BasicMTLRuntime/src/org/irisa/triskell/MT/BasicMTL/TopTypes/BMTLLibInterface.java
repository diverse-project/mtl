/*
 * Created on 18 août 2003
 * $Id: BMTLLibInterface.java,v 1.6 2004-02-17 08:58:22 dvojtise Exp $
 * Authors : ffondeme
 * 
 * Copyright 2004 - INRIA - LGPL license
 */
package org.irisa.triskell.MT.BasicMTL.TopTypes;

import java.util.List;

import org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOclAnyInterface;
import org.irisa.triskell.MT.DataTypes.Java.Type;
//import org.irisa.triskell.MT.DataTypes.Java.Value;

/**
 * @author ffondeme
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public interface BMTLLibInterface extends BMTLInterface, BMTLOclAnyInterface {
	String getName();
	
	Type getMetaClass (String [] qualifiedName);

	void recordNewInstance(String className,Object instance);
	void removeInstance(String className, Object instance);
	List allClassInstances (String className);
	
	BMTLLibInterface getLibrary();
	
	BMTLLibInterface getUsedLibrary(String libName);
}

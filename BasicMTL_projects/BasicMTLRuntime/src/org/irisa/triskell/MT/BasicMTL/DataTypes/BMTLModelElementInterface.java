/*
 * Created on 20 août 2003
 * $Id: BMTLModelElementInterface.java,v 1.3 2004-02-17 08:58:23 dvojtise Exp $
 * Authors : ffondeme
 * 
 * Copyright 2004 - INRIA - LGPL license
 */
package org.irisa.triskell.MT.BasicMTL.DataTypes;

//import org.irisa.triskell.MT.BasicMTL.DataTypes.impl.BMTLString;
//import org.irisa.triskell.MT.BasicMTL.DataTypes.impl.BMTLVoid;
//import org.irisa.triskell.MT.BasicMTL.TopTypes.BMTLType;
//import org.irisa.triskell.MT.DataTypes.Java.StringValue;
//import org.irisa.triskell.MT.DataTypes.Java.VoidValue;
import org.irisa.triskell.MT.repository.API.Java.ModelElement;

/**
 * @author ffondeme
 * Definition of accessible methods from BasicMTL on ModelElement objects 
 */
public interface BMTLModelElementInterface extends ModelElement, BMTLOclAnyInterface {
	ModelElement getModelElementDelegate();
	
	BMTLVoidInterface BMTL_delete();
	
	BMTLStringInterface BMTL_oclUid();
	
	BMTLOclTypeInterface BMTL_getType();
}

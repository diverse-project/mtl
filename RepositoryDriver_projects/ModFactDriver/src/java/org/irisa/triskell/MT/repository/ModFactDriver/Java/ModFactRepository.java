/*
 * Created on 28 janv. 2004
 *
 * $Id: ModFactRepository.java,v 1.1 2004-10-25 13:57:13 dvojtise Exp $
 * Authors : ffondeme xblanc dvojtise
 * 
 * Copyright 2004 - INRIA - LGPL license
 */
package org.irisa.triskell.MT.repository.ModFactDriver.Java;

import java.util.*;

//import javax.jmi.reflect.RefBaseObject;
import javax.jmi.reflect.RefObject;
import javax.jmi.reflect.RefPackage;

import org.objectweb.modfact.jmi.reflect.RefPackageImpl;


public class ModFactRepository {
	
	public Map extentMap = new Hashtable();
	
	public ModFactRepository() {
	    RefPackage m2 = RefPackageImpl.getM2Repository();
	    
		extentMap.put("MOF", m2.refMetaObject().refOutermostPackage() );
	}
	/* (non-Javadoc)
	 * @see org.netbeans.api.mdr.MDRepository#createExtent(java.lang.String)
	 */
	public RefPackage createExtent(String arg0)  {
		if(!extentMap.containsKey(arg0)) {
			extentMap.put(arg0, RefPackageImpl.getM2Repository() );
		}
		return (RefPackage) extentMap.get(arg0);
	}

	/* (non-Javadoc)
	 * @see org.netbeans.api.mdr.MDRepository#createExtent(java.lang.String, javax.jmi.reflect.RefObject)
	 */
	public RefPackage createExtent(String arg0, RefObject arg1) {
		if(!extentMap.containsKey(arg0)) {
			extentMap.put(arg0, RefPackageImpl.getM1Repository(arg1) );
		}
		return (RefPackage) extentMap.get(arg0);
	}

	/* (non-Javadoc)
	 * @see org.netbeans.api.mdr.MDRepository#createExtent(java.lang.String, javax.jmi.reflect.RefObject, javax.jmi.reflect.RefPackage[])
	 */
//	public RefPackage createExtent(String arg0, RefObject arg1, RefPackage[] arg2) {

//		return null;
//	}

	/* (non-Javadoc)
	 * @see org.netbeans.api.mdr.MDRepository#getExtent(java.lang.String)
	 */
	public RefPackage getExtent(String arg0) {
		return (RefPackage) extentMap.get(arg0);
	}

	/* (non-Javadoc)
	 * @see org.netbeans.api.mdr.MDRepository#getExtentNames()
	 */
	public String[] getExtentNames() {
			
		return (String[]) extentMap.keySet().toArray(new String[0]);
	}



}

/*
 * Created on 21 juil. 2003
 * $Id: TheLibraryClassAnalyser.java,v 1.1 2004-08-26 12:40:34 sdzale Exp $
 * Authors : jpthibau
 * 
 * Copyright 2004 - INRIA - LGPL license
 */
package org.inria.mtl.views.TLLCollector;

import java.util.Vector;

import org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.TheLibraryClass;

public class TheLibraryClassAnalyser extends TLLTopDownVisitor.TheLibraryClassAnalyser {

	public Object TheLibraryClassBefore(TheLibraryClass ASTnode,java.util.Map context) {
		Vector result = (Vector)context.get("CollectedElts");
		tllObject obj=new tllObject(ASTnode.getName(),null,0,"","");
		//result.add("TheLibraryClass Name : "+ASTnode.getName());
		result.add(obj);
		
		return null;
}
}
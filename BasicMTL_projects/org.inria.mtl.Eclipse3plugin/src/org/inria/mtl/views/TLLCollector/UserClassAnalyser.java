/*
 * Created on 21 juil. 2003
 * $Id: UserClassAnalyser.java,v 1.1 2004-08-26 12:40:35 sdzale Exp $
 * Authors : jpthibau
 * 
 * Copyright 2004 - INRIA - LGPL license
 */
package org.inria.mtl.views.TLLCollector;

import java.util.Vector;

import org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.UserClass;


public class UserClassAnalyser extends TLLTopDownVisitor.UserClassAnalyser {

	public Object UserClassBefore(UserClass  ASTnode,java.util.Map context) {
		Vector result = (Vector)context.get("CollectedElts");
		tllObject obj=new tllObject(ASTnode.getName(),null,1,"","");
		//result.add("UserClass Name : "+ASTnode.);
//		result.add("UserClass line number : "+ASTnode.getLineNumber());
		//result.add("UserClass Name : "+ASTnode.getDecoration(0));
		result.add(obj);
		
		return null;
}
}
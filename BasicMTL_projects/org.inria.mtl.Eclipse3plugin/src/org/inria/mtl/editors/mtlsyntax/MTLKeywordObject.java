/*
* $Id: MTLKeywordObject.java,v 1.1 2004-07-30 14:11:09 sdzale Exp $
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
public class MTLKeywordObject extends MTLObject{
	private int tokenval;
	public void settokenval(String tokenval) { this.tokenval = Integer.parseInt(tokenval);}
	public void settokenval(int tokenval) { this.tokenval = tokenval;}
	public int gettokenval() { return this.tokenval; }
	
	public MTLKeywordObject(String Name, String Description, String tokenval){
		super(Name, Description);
		if ((tokenval == null) || (tokenval.equals("")))
				{
					settokenval(-1);
				}
				else {
					settokenval(tokenval); 
				}		
	}
}

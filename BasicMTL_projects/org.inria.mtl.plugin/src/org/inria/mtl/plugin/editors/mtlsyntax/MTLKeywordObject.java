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

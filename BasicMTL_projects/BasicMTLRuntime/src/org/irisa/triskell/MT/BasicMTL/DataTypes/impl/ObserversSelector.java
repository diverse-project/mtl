/*
 * Created on 2 juil. 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package org.irisa.triskell.MT.BasicMTL.DataTypes.impl;

/**
 * @author jpthibau
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class ObserversSelector {
	public static java.util.Vector checkopSelection(BMTLString opSelection) {
		boolean error=false;
		java.util.Vector result = new java.util.Vector();
		String selection = opSelection.getTheString();
		for (int i=0;i<selection.length();i++) {
			char c=selection.charAt(i);
			if (c=='S' || c=='G' || c=='N' || c=='D')
				result.add(new Character(c));
			else error=true;
		}
		if (error) {
			System.out.println("Operation selection (here "+selection+") should only contain characters D(delete model element),N(new Model element),G(attribute getter) or S(attribute setter).");
			result = new java.util.Vector(); }
		 return result; }}

/*
* $Id: BuildPathBasePage.java,v 1.2 2004-06-24 09:23:28 sdzale Exp $
* Authors : ${user}
*
* Created on ${date}
* Copyright 2004 - INRIA - LGPL license
*/ 
package org.inria.mtl.plugin.wizards;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.internal.ui.wizards.buildpaths.CPListElement;

public abstract class BuildPathBasePage {
	
	public abstract List getSelection();
	public abstract void setSelection(List selection);
	
	public abstract boolean isEntryKind(int kind);
			
	protected void filterAndSetSelection(List list) {
		ArrayList res= new ArrayList(list.size());
		for (int i= list.size()-1; i >= 0; i--) {
			Object curr= list.get(i);
			if (curr instanceof CPListElement) {
				CPListElement elem= (CPListElement) curr;
				if (elem.getParentContainer() == null && isEntryKind(elem.getEntryKind())) {
					res.add(curr);
				}
			}
		}
		setSelection(res);
	}		
}
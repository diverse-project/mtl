/*
* $Id: MTLDamageRepairer.java,v 1.2 2004-05-19 09:22:37 sdzale Exp $
* Authors : ${user}
*
* Created on ${date}
* Copyright 2004 - INRIA - LGPL license
*/ 
package org.inria.mtl.plugin.editors;

import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.DocumentEvent;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.ITypedRegion;
import org.eclipse.jface.text.rules.DefaultDamagerRepairer;
import org.eclipse.jface.text.rules.ITokenScanner;

/**
 * @author sdzale
 *
 */
public class MTLDamageRepairer extends DefaultDamagerRepairer {
	
	public MTLDamageRepairer(ITokenScanner scanner){
		super(scanner);
	}
	
	public IRegion getDamageRegion(ITypedRegion partition, DocumentEvent event, boolean documentPartitioningChanged){
		System.out.println("Called Damager");
		try{
			if(event.getText().length()==1){
				return fDocument.getLineInformationOfOffset(event.getOffset());
			}else{
				return super.getDamageRegion(partition,event,documentPartitioningChanged);
			}
		} catch (BadLocationException x) {
			System.out.println("Hmmmm its all ballsed up!");
			return partition;
		}
		
	} 

	
}

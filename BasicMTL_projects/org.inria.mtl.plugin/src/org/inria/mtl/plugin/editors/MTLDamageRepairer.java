/*
 * Created on 06-Oct-2003
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package org.inria.mtl.plugin.editors;

import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.DocumentEvent;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.ITypedRegion;
import org.eclipse.jface.text.rules.DefaultDamagerRepairer;
import org.eclipse.jface.text.rules.ITokenScanner;

/**
 * @author ish
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
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

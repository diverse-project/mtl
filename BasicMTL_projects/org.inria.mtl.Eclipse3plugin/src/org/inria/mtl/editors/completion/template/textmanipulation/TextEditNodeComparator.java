/*
* $Id: TextEditNodeComparator.java,v 1.1 2004-07-30 14:09:08 sdzale Exp $
* Authors : ${user}
*
* Created on ${date}
* Copyright 2004 - INRIA - LGPL license
*/ 
package org.inria.mtl.editors.completion.template.textmanipulation;

/**
 * @author sdzale
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */

import java.util.Comparator;

/**
 * A special comparator to comapre <code>TextRange</code>s.
 */
/* package */ class TextEditNodeComparator  implements Comparator {
	public int compare(Object o1, Object o2) {
		TextEditNode node1= (TextEditNode)o1;
		TextEditNode node2= (TextEditNode)o2;
		TextRange pos1= node1.getTextRange();
		TextRange pos2= node2.getTextRange();
		
		int offset1= pos1.fOffset;
		int offset2= pos2.fOffset;
		if (offset1 < offset2)
			return -1;
		if (offset1 > offset2)
			return 1;

		// same offset
		int length1= pos1.fLength;
		int length2= pos2.fLength;
		
		// insertion points come before anything else at the same position.
		if (length1 == 0 && length2 != 0)
			return -1;
		if (length1 != 0 && length2 == 0)
			return 1;
		
		// Longer edits come before shorter edits
		if (length1 <  length2)
			return 1;
		if (length1 > length2)
			return -1;
		
		if (node1.fEdit.index < node2.fEdit.index)
			return -1;
		return 1;	
	}	
}


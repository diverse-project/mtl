/*
 * Created on 19 août 2003
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.signatures;

import org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.Attribute;
import org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.Operation;
import org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.QualifiedName;

/**
 * @author ffondeme
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public abstract class AttributeAccessor extends ComputedSignature {
	
	public final String prefix;
	public String getPrefix () {
		return this.prefix;
	}
	
	public final Attribute theAttribute;
	public int cardTheAttribute () {
		return  this.theAttribute == null ? 0 : 1;
	}
	public Attribute getTheAttribute () {
		return this.theAttribute;
	}

	public AttributeAccessor(String prefix, Attribute attribute) {
		super();
		this.prefix = prefix;
		this.theAttribute = attribute;
	}

	public String getOpMangle() {
		return this.getPrefix() + this.getTheAttribute().getMangle();
	}

	public String getOpName() {
		return this.getPrefix() + this.getTheAttribute().getName();
	}
	
	public Operation getTheOperation() {
		return null;
	}

}

/*
 * Created on 19 ao�t 2003
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.signatures;

import org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.Attribute;
import org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.BasicMtlLibrary;
import org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.QualifiedName;

/**
 * @author ffondeme
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class AttributeSetterSignature extends AttributeAccessor {
	public static final String SetPrefix = "set_";
	
	private final QualifiedName rt;

	public AttributeSetterSignature(Attribute attribute, QualifiedName voidType) {
		super(SetPrefix, attribute);
		this.rt = voidType;
	}

	public int cardArgsTypes() {
		return 1;
	}

	public QualifiedName getArgsTypes(int i) {
		if (i == 0)
			return this.getTheAttribute().getFeatureType();
		else
			throw new ArrayIndexOutOfBoundsException();
	}

	public QualifiedName getReturnedType() {
		return this.rt;
	}

}

/*
 * Created on 19 août 2003
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.signatures;

import org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.Attribute;
import org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.QualifiedName;

/**
 * @author ffondeme
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class AttributeGetterSignature extends AttributeAccessor {
	public static final String GetPrefix = "get_";

	public AttributeGetterSignature(Attribute attribute) {
		super(GetPrefix, attribute);
	}

	public int cardArgsTypes() {
		return 0;
	}

	public QualifiedName getArgsTypes(int i) {
		throw new ArrayIndexOutOfBoundsException();
	}

	public QualifiedName getReturnedType() {
		return this.getTheAttribute().getFeatureType();
	}

}
	
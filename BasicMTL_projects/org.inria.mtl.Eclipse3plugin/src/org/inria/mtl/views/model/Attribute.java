package org.inria.mtl.views.model;

import org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.QualifiedName;

public class Attribute extends Model {
	
	public Attribute(String name, String GivenName, QualifiedName qname) {
		super(name, GivenName, qname);
	}
	/*
	 * @see Model#accept(ModelVisitorI, Object)
	 */
	public void accept(IModelVisitor visitor, Object passAlongArgument) {
		visitor.visitAttribute(this, passAlongArgument);
	}
	
	public String getTitle() {
		return name+"  Type :"+qName;
	}

}

package org.inria.mtl.views.model;

import org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.QualifiedName;

public class Classe extends Model {
	
	public Classe(String title, String authorGivenName, QualifiedName qname) {
		super(title, authorGivenName, qname);
	}
	/*
	 * @see Model#accept(ModelVisitorI, Object)
	 */
	public void accept(IModelVisitor visitor, Object passAlongArgument) {
		visitor.visitClasse(this, passAlongArgument);
	}

}

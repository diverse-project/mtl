package org.inria.mtl.views.model;



public interface IModelVisitor {
	public void visitMovingBox(MTLNode box, Object passAlongArgument);
	public void visitLibrary(Library book, Object passAlongArgument);
	public void visitAttribute(Attribute attr, Object passAlongArgument);
	public void visitClasse(Classe cl, Object passAlongArgument);
	public void visitMethod(Method cl, Object passAlongArgument);
}

package org.inria.mtl.views.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author sdzale
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class MTLNode extends Model {
	protected List boxes;
	protected List attributes;
	protected List classes;
	protected List librairies;
	protected List methods;
	
	private static IModelVisitor adder = new Adder();
	private static IModelVisitor remover = new Remover();
	/**
	 * This field represent the type of node
	 * 0=generic node
	 * 1=Library
	 * 2=Classe
	 * 3=Attribute
	 * 4=Method
	 */
	public int type =0;// (
	public MTLNode() {
		boxes = new ArrayList();
		attributes = new ArrayList();
		librairies = new ArrayList();
		classes = new ArrayList();
		methods=new ArrayList();
		
	}
	
	public MTLNode(int typ) {
		boxes = new ArrayList();
		attributes = new ArrayList();
		librairies = new ArrayList();
		classes = new ArrayList();
		methods=new ArrayList();
		type=typ;
	}
	
	private static class Adder implements IModelVisitor {

		/*
		 * @see ModelVisitorI#visitBoardgame(BoardGame)
		 */

		/*
		 * @see ModelVisitorI#visitBook(MovingBox)
		 */

		/*
		 * @see ModelVisitorI#visitMovingBox(MovingBox)
		 */

		/*
		 * @see ModelVisitorI#visitAttribute(Attribute, Object)
		 */
		public void visitAttribute(Attribute attr, Object argument) {
			((MTLNode) argument).addAttribute(attr);
		}
		
		/*
		 * @see ModelVisitorI#visitMethod(Method, Object)
		 */
		public void visitMethod(Method met, Object argument) {
			((MTLNode) argument).addMethod(met);
		}
		/*
		 * @see ModelVisitorI#visitBook(MovingBox, Object)
		 */
		public void visitLibrary(Library library, Object argument) {
			((MTLNode) argument).addLibrary(library);
		}
		
		/*
		 * @see ModelVisitorI#visitClasse(Classe, Object)
		 */
		public void visitClasse(Classe cl, Object argument) {
			((MTLNode) argument).addClasse(cl);
		}

		/*
		 * @see ModelVisitorI#visitMovingBox(MovingBox, Object)
		 */
		public void visitMovingBox(MTLNode box, Object argument) {
			((MTLNode) argument).addBox(box);
		}

	}

	private static class Remover implements IModelVisitor {
		public void visitAttribute(Attribute attr, Object argument) {
			((MTLNode) argument).removeAttribute(attr);
		}
		
		public void visitMethod(Method met, Object argument) {
			((MTLNode) argument).removeMethod(met);
		}

		/*
		 * @see ModelVisitorI#visitBook(MovingBox, Object)
		 */
		public void visitLibrary(Library library, Object argument) {
			((MTLNode) argument).removeLibrary(library);
		}

		/*
		 * @see ModelVisitorI#visitLibrary(MovingBox, Object)
		 */
		public void visitMovingBox(MTLNode box, Object argument) {
			((MTLNode) argument).removeBox(box);
			box.addListener(NullDeltaListener.getSoleInstance());
		}
		
		/*
		 * @see ModelVisitorI#visitClasse(MovingBox, Object)
		 */
		public void visitClasse(Classe cl, Object argument) {
			((MTLNode) argument).removeClasse(cl);
		}

	}
	
	public MTLNode(String name) {
		this();
		this.name = name;
	}
	
	public MTLNode(String name,int typ) {
		this();
		this.name = name;
		type=typ;
	}
	
	public List getBoxes() {
		return boxes;
	}
	
	protected void addBox(MTLNode box) {
		boxes.add(box);
		box.parent = this;
		fireAdd(box);
	}
	
	protected void addLibrary(Library librairy) {
		librairies.add(librairy);
		librairy.parent = this;
		fireAdd(librairy);
	}
	
	protected void addAttribute(Attribute attr) {
		attributes.add(attr);
		attr.parent = this;
		fireAdd(attr);
	}
	
	protected void addMethod(Method met) {
		methods.add(met);
		met.parent = this;
		fireAdd(met);
	}
	
	protected void addClasse(Classe cl) {
		classes.add(cl);
		cl.parent = this;
		fireAdd(cl);
	}	
	
	public List getlibrairies() {
		return librairies;
	}
	
	public List getmethods() {
		return methods;
	}
	
	public List getclasses() {
		return classes;
	}
	
	public void remove(Model toRemove) {
		toRemove.accept(remover, this);
	}
	
	protected void removeAttribute(Attribute attr) {
		attributes.remove(attr);
		attr.addListener(NullDeltaListener.getSoleInstance());
		fireRemove(attr);
	}
	
	protected void removeMethod(Method met) {
		methods.remove(met);
		met.addListener(NullDeltaListener.getSoleInstance());
		fireRemove(met);
	}
	
	protected void removeClasse(Classe cl) {
		classes.remove(cl);
		cl.addListener(NullDeltaListener.getSoleInstance());
		fireRemove(cl);
	}
	
	protected void removeLibrary(Library library) {
		librairies.remove(library);
		library.addListener(NullDeltaListener.getSoleInstance());
		fireRemove(library);
	}
	
	protected void removeBox(MTLNode box) {
		boxes.remove(box);
		box.addListener(NullDeltaListener.getSoleInstance());
		fireRemove(box);	
	}

	public void add(Model toAdd) {
		toAdd.accept(adder, this);
	}
	
	public List getAttributes() {
		return attributes;
	}
	
	/** Answer the total number of items the
	 * receiver contains. */
	public int size() {
		return getlibrairies().size()+getmethods().size() + getBoxes().size() + getAttributes().size()+getclasses().size() ;
	}
	/*
	 * @see Model#accept(ModelVisitorI, Object)
	 */
	public void accept(IModelVisitor visitor, Object passAlongArgument) {
		visitor.visitMovingBox(this, passAlongArgument);
	}

}

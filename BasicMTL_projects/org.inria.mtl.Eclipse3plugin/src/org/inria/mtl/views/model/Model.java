package org.inria.mtl.views.model;

import org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.QualifiedName;

public abstract class Model {
	protected MTLNode parent;
	protected String name;
	protected String typeObj;
	protected QualifiedName qName;	
	protected IDeltaListener listener = NullDeltaListener.getSoleInstance();
	
	protected void fireAdd(Object added) {
		listener.add(new DeltaEvent(added));
	}

	protected void fireRemove(Object removed) {
		listener.remove(new DeltaEvent(removed));
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public MTLNode getParent() {
		return parent;
	}
	
	/* The receiver should visit the toVisit object and
	 * pass along the argument. */
	public abstract void accept(IModelVisitor visitor, Object passAlongArgument);
	
	public String getName() {
		return name;
	}
	
	public void addListener(IDeltaListener listener) {
		this.listener = listener;
	}
	
	public Model(String title, String typeObj, QualifiedName qname) {
		this.name = title;
		this.typeObj = typeObj;
		this.qName = qname;
	}
	
	public Model(String title) {
		this.name = title;
	}
	
	public Model() {
	}	
	
	public void removeListener(IDeltaListener listener) {
		if(this.listener.equals(listener)) {
			this.listener = NullDeltaListener.getSoleInstance();
		}
	}

	public String objectGivenType() {
		return typeObj;
	}


	public QualifiedName qualifName() {
		return qName;
	}

	public String getTitle() {
		return name;
	}


}

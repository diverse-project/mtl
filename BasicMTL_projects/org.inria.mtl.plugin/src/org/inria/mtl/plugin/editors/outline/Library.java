/*
 * Created on 05-05-2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package org.inria.mtl.plugin.editors.outline;

import java.util.ArrayList;

import org.eclipse.jface.text.Position;

/**
 * @author ish
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class Library implements ICodePart {

	private ArrayList children;
	private Object parent;
	private String title;
	private Position p;
	
	public Library(String name){
		this.title = name;
		children = new ArrayList();
	}


	public String getTitle() {
		return title;
	}

	public ICodePart[] getChildern() {
		ICodePart[] sections = new ICodePart[children.size()];
		children.toArray(sections);
		return sections;
	}

	public Object getParent() {
		return parent;
	}

	public void addChild(ICodePart child) {
		this.children.add(child);
	}

	public void setParent(Object parent) {
		this.parent = parent;
	}
	
	public Position getPosition(){
		return p;
	}
	
	public void setPosition(Position p){
		this.p = p;
	}
	
	public String toString(){
		return this.title;
	}

}

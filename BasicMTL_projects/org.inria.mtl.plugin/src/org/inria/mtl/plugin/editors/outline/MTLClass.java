/*
 * Created on 5 mai 2004
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package org.inria.mtl.plugin.editors.outline;

import org.eclipse.jface.text.Position;
import java.util.ArrayList;
/**
 * @author sdzale
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class MTLClass implements ICodePart {


	private ArrayList children;
	private Object parent;
	private String title;
	private Position p;
	
	public MTLClass(String name,Position p){
		this.title = name;
		this.p = p;
		this.children = new ArrayList();
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
		children.add(child);
	}

	public void setParent(Object parent) {
		this.parent = parent;
	}


	public Position getPosition() {
		return p;
	}


	public void setPosition(Position p) {
		this.p = p;
	}

	public String toString(){
		return this.title;
	}
}

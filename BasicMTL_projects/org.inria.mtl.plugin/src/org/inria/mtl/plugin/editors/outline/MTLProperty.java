/*
* $Id: MTLProperty.java,v 1.2 2004-05-19 09:21:08 sdzale Exp $
* Authors : ${user}
*
* Created on ${date}
* Copyright 2004 - INRIA - LGPL license
*/ 
package org.inria.mtl.plugin.editors.outline;

import java.util.ArrayList;

import org.eclipse.jface.text.Position;


/**
 * @author sdzale
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class MTLProperty implements ICodePart{
	
		private ArrayList children;
		private Object parent;
		private String title;
		private Position p;
	
		public MTLProperty(String name,Position p){
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


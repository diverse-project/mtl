package org.inria.mtl.views.model;

import java.util.ArrayList;
import java.util.List;

import org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.QualifiedName;

public class Library extends Model {
	protected static List newLibrarys = buildLibraryList();
	protected static int cursor = 0;
	protected List classes;
	
	public Library(String title, String authorGivenName, QualifiedName qname) {
		super(title, authorGivenName, qname);
	}
	
	
	public static Library newLibrary() {
		Library newLibrary = (Library)newLibrarys.get(cursor);
		cursor = ((cursor + 1) % newLibrarys.size());
		return newLibrary; 
	}
	
	
	protected static List buildLibraryList() {
		newLibrarys = new ArrayList();
		Library[] Librarys = new Library[] {
			new Library("Advanced Java: Idioms, Pitfalls, Styles and Programming Tips", "Chris", null),
			new Library("Programming Ruby: A Pragmatic Programmer's Guide", "David", null),
			new Library("The Pragmatic Programmer", "Andrew", null),
			new Library("Java Virtual Machine", "Jon", null),
			new Library("Using Netscape IFC", "Arun", null),
			new Library("Smalltalk-80", "Adele", null),
			new Library("Java 2 Exam Cram", "William", null)
		};
		
		for (int i = 0; i < Librarys.length; i++) {
			newLibrarys.add(Librarys[i]);
			
		}
		return newLibrarys;
	}
	/*
	 * @see Model#accept(ModelVisitorI, Object)
	 */
	public void accept(IModelVisitor visitor, Object passAlongArgument) {
		visitor.visitLibrary(this, passAlongArgument);
	}

}

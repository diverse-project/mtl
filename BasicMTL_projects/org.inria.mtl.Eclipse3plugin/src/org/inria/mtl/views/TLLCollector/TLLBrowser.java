package org.inria.mtl.views.TLLCollector;

import java.io.File;
import java.util.Iterator;
import java.util.Vector;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.irisa.triskell.MT.visitors.Java.AnalysingVisitor.DefaultAnalysingVisitor;
import org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.Library;

public class TLLBrowser {
	
	
	public Vector Browse(String TLLFileName) {
		Vector result = new Vector();
		java.util.Hashtable context=new java.util.Hashtable();
		context.put("CollectedElts",result);
		File f = new File(TLLFileName);
		if (! f.exists()) System.err.println("Cannot find file :"+TLLFileName);
		else {
			System.out.println("FILE EXIST :"+ TLLFileName);
			Library lib=Library.load(TLLFileName);
			DefaultAnalysingVisitor visitor = new DefaultAnalysingVisitor("org.inria.mtl.views.TLLCollector");
			visitor.visit(lib,context);
		}
		return result;
		
	}
	
	public  Vector Browse(File f) {
		Vector result = new Vector();
		java.util.Hashtable context=new java.util.Hashtable();
		context.put("CollectedElts",result);
		if (! f.exists()) System.err.println("Cannot find files :"+f);
		else {
			Library lib=Library.load(f.toString());
			DefaultAnalysingVisitor visitor = new DefaultAnalysingVisitor("org.inria.mtl.views.TLLCollector");
			visitor.visit(lib,context);
		}
		return result;
		
	}
	
	public static Vector Browse(IFolder TLLFolderName) {
		Vector result = new Vector();
		java.util.Hashtable context=new java.util.Hashtable();
		context.put("CollectedElts",result);
		String TLLFileName=TLLFolderName.getName().concat(".tll");
		System.out.println("Folder :"+TLLFolderName.getFullPath());
		IFile f=TLLFolderName.getFile(TLLFileName);
		if (! TLLFolderName.exists()) System.err.println("Cannot find folder :"+TLLFolderName);
		if (! f.exists()) System.err.println("Cannot find file :"+TLLFileName);
		else {
			Library lib=Library.load(TLLFileName);
			DefaultAnalysingVisitor visitor = new DefaultAnalysingVisitor("org.inria.mtl.views.TLLCollector");
			visitor.visit(lib,context);
		}
		return result;
		
	}

	public  void explorer(Vector v) {
		System.out.println("Vector :"+v.size());
		Iterator it = v.iterator();
		while (it.hasNext())
			System.out.println(it.next());
	}
	
	public static void main(String[] args) {
		TLLBrowser b=new TLLBrowser();
		Vector v=b.Browse("C:\\V3\\eclipse-SDK-3.0-win32\\eclipse\\runtime-workspace\\MyProject6\\build\\tll\\UML_Utils.tll");
		Iterator it = v.iterator();
		while (it.hasNext())
			System.out.println(it.next());
	}
}

/*
 * $Header: /tmp/cvs2svn/cvsroot/BasicMTL_projects/BasicMtlAstJava/src/ASTTopDownVisitor/NativeLibraryAnalyser.java,v 1.1 2003-07-28 07:35:33 jpthibau Exp $
 * Created on 17 juil. 2003
 *
 */
package ASTTopDownVisitor;

import org.irisa.triskell.MT.visitors.Java.AnalysingVisitor.*;
import org.irisa.triskell.MT.visitors.Java.GenericVisitor.*;
import org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.*;
/**
 * @author jpthibau
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class NativeLibraryAnalyser extends Analyser {

public NativeLibraryAnalyser()
{	super(NativeLibrary.class);
}

public void analyse(Visitable node,Visitor visitor,java.util.Map context)
{	int i,limit;
	NativeLibrary Lib=(NativeLibrary) node;
	this.NativeLibraryBefore(Lib,context);
	limit=Lib.cardParameters();
	for (i=0;i<limit;i++) {
		((ModelRef)Lib.getParameters(i)).accept(visitor,context);
		this.NativeLibraryModelRef(context.get("ModelRef"),context);
	}
	limit=Lib.cardDefinedOperations();
	for (i=0;i<limit;i++) {
		((Operation)Lib.getDefinedOperations(i)).accept(visitor,context);
		this.NativeLibraryOperation(context.get("Operation"),context);
	}
	limit=Lib.cardDefinedClasses();
	for (i=0;i<limit;i++) {
			((UserClass)Lib.getDefinedClasses(i)).accept(visitor,context);
		this.NativeLibraryUserClass(context.get("UserClass"),context);
	}
	this.NativeLibraryAfter(Lib,context);
}

public void NativeLibraryBefore(NativeLibrary Lib,java.util.Map context) {}

public void NativeLibraryModelRef(Object model,java.util.Map context) {}

public void NativeLibraryOperation(Object op,java.util.Map context) {}

public void NativeLibraryUserClass(Object userClass,java.util.Map context) {}

public void NativeLibraryAfter(NativeLibrary Lib,java.util.Map context) {}

}

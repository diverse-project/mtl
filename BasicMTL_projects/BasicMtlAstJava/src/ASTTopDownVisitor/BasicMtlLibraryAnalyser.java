/*
 * $Header: /tmp/cvs2svn/cvsroot/BasicMTL_projects/BasicMtlAstJava/src/ASTTopDownVisitor/BasicMtlLibraryAnalyser.java,v 1.1 2003-07-28 07:35:34 jpthibau Exp $
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
public class BasicMtlLibraryAnalyser extends Analyser {

	public BasicMtlLibraryAnalyser()
	{	super(BasicMtlLibrary.class);
	}

	public void analyse(Visitable node,Visitor visitor,java.util.Map context)
	{	int i,limit;
		BasicMtlLibrary Lib=(BasicMtlLibrary) node;
		this.BasicMtlLibraryBefore(Lib,context);
		limit=Lib.cardParameters();
		for (i=0;i<limit;i++) {
			((ModelRef)Lib.getParameters(i)).accept(visitor,context);
			this.BasicMtlLibraryModelRef(context.get("ModelRef"),context);
		}
		limit=Lib.cardDefinedOperations();
		for (i=0;i<limit;i++) {
			((Operation)Lib.getDefinedOperations(i)).accept(visitor,context);
			this.BasicMtlLibraryOperation(context.get("Operation"),context);
		}
		limit=Lib.cardDefinedClasses();
		for (i=0;i<limit;i++) {
				((UserClass)Lib.getDefinedClasses(i)).accept(visitor,context);
			this.BasicMtlLibraryUserClass(context.get("UserClass"),context);
		}
		this.BasicMtlLibraryAfter(Lib,context);
	}

	public void BasicMtlLibraryBefore(BasicMtlLibrary Lib,java.util.Map context) {}

	public void BasicMtlLibraryModelRef(Object model,java.util.Map context) {}

	public void BasicMtlLibraryOperation(Object op,java.util.Map context) {}

	public void BasicMtlLibraryUserClass(Object userClass,java.util.Map context) {}

	public void BasicMtlLibraryAfter(BasicMtlLibrary Lib,java.util.Map context) {}

}

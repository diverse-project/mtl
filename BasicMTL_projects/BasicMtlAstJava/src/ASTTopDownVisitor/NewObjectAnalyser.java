/*
 * $Header: /tmp/cvs2svn/cvsroot/BasicMTL_projects/BasicMtlAstJava/src/ASTTopDownVisitor/NewObjectAnalyser.java,v 1.1 2003-07-28 07:35:33 jpthibau Exp $
 * Created on 24 juil. 2003
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
public class NewObjectAnalyser extends Analyser {

	public NewObjectAnalyser()
	{	super(NewObject.class);
	}

	public void analyse(Visitable node,Visitor visitor,java.util.Map context)
	{	int i,limit;
		NewObject ASTnode=(NewObject) node;
		this.NewObjectBefore(ASTnode,context);
		limit=ASTnode.cardArguments();
		for (i=0;i<limit;i++) {
			((Expression)ASTnode.getArguments(i)).accept(visitor,context);
			this.NewObjectArgument(context.get("Instruction"),context);
		}
		this.NewObjectAfter(ASTnode,context);
	}

	public void NewObjectBefore(NewObject ASTnode,java.util.Map context) {}

	public void NewObjectArgument(Object arg,java.util.Map context) {}

	public void NewObjectAfter(NewObject ASTnode,java.util.Map context) {}

}

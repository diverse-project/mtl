/*
 * $Header: /tmp/cvs2svn/cvsroot/BasicMTL_projects/BasicMtlTLLJava/src/TLLTopDownVisitor/NewObjectAnalyser.java,v 1.1 2003-08-06 16:13:26 jpthibau Exp $
 * Created on 24 juil. 2003
 *
 */
package TLLTopDownVisitor;

import org.irisa.triskell.MT.visitors.Java.AnalysingVisitor.*;
import org.irisa.triskell.MT.visitors.Java.GenericVisitor.*;
import org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.*;

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
		Object theNewObject=this.NewObjectBefore(ASTnode,context);
		limit=ASTnode.cardArguments();
		for (i=0;i<limit;i++) {
			((Expression)ASTnode.getArguments(i)).accept(visitor,context);
			this.NewObjectArgument(theNewObject,context.get("Instruction"),context);
		}
		this.NewObjectAfter(theNewObject,ASTnode,context);
	}

	public Object NewObjectBefore(NewObject ASTnode,java.util.Map context)
	{	return null; }

	public void NewObjectArgument(Object theNewObject,Object arg,java.util.Map context) {}

	public void NewObjectAfter(Object theNewObject,NewObject ASTnode,java.util.Map context) {}

}

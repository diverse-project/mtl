/*
 * $Header: /tmp/cvs2svn/cvsroot/BasicMTL_projects/BasicMtlTLLJava/src/TLLTopDownVisitor/JavaCodeLiteralAnalyser.java,v 1.1 2003-08-28 16:42:02 jpthibau Exp $
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
public class JavaCodeLiteralAnalyser extends Analyser {

	public JavaCodeLiteralAnalyser()
	{	super(JavaCodeLiteral.class);
	}

	public void analyse(Visitable node,Visitor visitor,java.util.Map context)
	{	int i,limit;
		JavaCodeLiteral ASTnode=(JavaCodeLiteral) node;
		JavaCodeLiteralAction(ASTnode,context);
	}

	public void JavaCodeLiteralAction(JavaCodeLiteral ASTnode,java.util.Map context) {}

}

/*
 * $Header: /tmp/cvs2svn/cvsroot/BasicMTL_projects/BasicMtlTLLJava/src/TLLTopDownVisitor/NullLiteralAnalyser.java,v 1.1 2003-08-06 16:13:27 jpthibau Exp $
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
public class NullLiteralAnalyser extends Analyser {

	public NullLiteralAnalyser()
	{	super(NullLiteral.class);
	}

	public void analyse(Visitable node,Visitor visitor,java.util.Map context)
	{	int i,limit;
		NullLiteral ASTnode=(NullLiteral) node;
		NullLiteralAction(ASTnode,context);
	}

	public void NullLiteralAction(NullLiteral ASTnode,java.util.Map context) {}

}

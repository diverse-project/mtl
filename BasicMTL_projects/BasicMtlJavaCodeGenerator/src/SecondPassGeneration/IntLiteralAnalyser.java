/*
 * $Header: /tmp/cvs2svn/cvsroot/BasicMTL_projects/BasicMtlJavaCodeGenerator/src/SecondPassGeneration/IntLiteralAnalyser.java,v 1.1 2003-08-08 15:41:12 jpthibau Exp $
 * Created on 8 août 2003
 *
 */
package SecondPassGeneration;

import java.io.*;
import org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.*;

import TLLTopDownVisitor.OclTypeLiteralAnalyser;

/**
 * @author jpthibau
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class IntLiteralAnalyser extends TLLTopDownVisitor.IntLiteralAnalyser {

	public void IntLiteralAction(IntLiteral ASTnode,java.util.Map context)
	{	PrintWriter outputForClass = (PrintWriter)context.get("OutputForClass");
		outputForClass.print("new IntegerValueImpl(false,null,"+ASTnode.getValue()+")");
	}
}

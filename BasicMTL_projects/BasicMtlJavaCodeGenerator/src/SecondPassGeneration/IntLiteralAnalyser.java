/*
 * $Header: /tmp/cvs2svn/cvsroot/BasicMTL_projects/BasicMtlJavaCodeGenerator/src/SecondPassGeneration/IntLiteralAnalyser.java,v 1.5 2003-12-16 07:51:45 jpthibau Exp $
 * Created on 8 ao�t 2003
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
		CommonFunctions.generateCastBefore(outputForClass, ASTnode,ASTnode.getIsTrownExpression());
		outputForClass.print("new BMTLInteger("+ASTnode.getValue()+')');
		CommonFunctions.generateCastAfter(outputForClass, ASTnode,ASTnode.getIsTrownExpression());
	}
}

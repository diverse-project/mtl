/*
 * $Header: /tmp/cvs2svn/cvsroot/BasicMTL_projects/BasicMtlJavaCodeGenerator/src/SecondPassGeneration/NullLiteralAnalyser.java,v 1.5 2003-12-16 07:51:44 jpthibau Exp $
 * Created on 8 août 2003
 *
 */
package SecondPassGeneration;

import java.io.*;
import org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.*;

/**
 * @author jpthibau
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class NullLiteralAnalyser extends TLLTopDownVisitor.NullLiteralAnalyser {

	public void NullLiteralAction(NullLiteral ASTnode,java.util.Map context)
	{	PrintWriter outputForClass = (PrintWriter)context.get("OutputForClass");
		CommonFunctions.generateCastBefore(outputForClass, ASTnode,ASTnode.getIsTrownExpression());
		//outputForClass.print("BMTLNull.TheInstance");
		outputForClass.print("null");
		CommonFunctions.generateCastAfter(outputForClass, ASTnode,ASTnode.getIsTrownExpression());
	}
}

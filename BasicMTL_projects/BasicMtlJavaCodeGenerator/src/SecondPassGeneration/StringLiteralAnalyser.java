/*
 * $Header: /tmp/cvs2svn/cvsroot/BasicMTL_projects/BasicMtlJavaCodeGenerator/src/SecondPassGeneration/StringLiteralAnalyser.java,v 1.4 2003-08-21 20:10:17 ffondeme Exp $
 * Created on 8 ao�t 2003
 *
 */
package SecondPassGeneration;

import java.io.*;
import org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.*;
import org.irisa.triskell.MT.utils.Java.JavaStringLiteralEncoder;

/**
 * @author jpthibau
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class StringLiteralAnalyser extends TLLTopDownVisitor.StringLiteralAnalyser {

	public void StringLiteralAction(StringLiteral ASTnode,java.util.Map context)
	{	PrintWriter outputForClass = (PrintWriter)context.get("OutputForClass");
		CommonFunctions.generateCastBefore(outputForClass, ASTnode);
		outputForClass.print("new BMTLString(\""+JavaStringLiteralEncoder.encodeString(ASTnode.getValue())+"\")");
		CommonFunctions.generateCastAfter(outputForClass, ASTnode);
	}
}

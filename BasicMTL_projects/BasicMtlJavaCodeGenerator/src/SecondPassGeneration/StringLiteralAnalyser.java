/*
 * $Header: /tmp/cvs2svn/cvsroot/BasicMTL_projects/BasicMtlJavaCodeGenerator/src/SecondPassGeneration/StringLiteralAnalyser.java,v 1.3 2003-08-20 16:07:34 ffondeme Exp $
 * Created on 8 août 2003
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
		outputForClass.print("new BMTLString(\""+JavaStringLiteralEncoder.encodeString(ASTnode.getValue())+"\")");
	}
}

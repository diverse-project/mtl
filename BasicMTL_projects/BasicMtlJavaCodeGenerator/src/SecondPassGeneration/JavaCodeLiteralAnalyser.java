/*
 * $Header: /tmp/cvs2svn/cvsroot/BasicMTL_projects/BasicMtlJavaCodeGenerator/src/SecondPassGeneration/JavaCodeLiteralAnalyser.java,v 1.1 2003-08-28 16:51:37 jpthibau Exp $
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
public class JavaCodeLiteralAnalyser extends TLLTopDownVisitor.JavaCodeLiteralAnalyser {

	public void JavaCodeLiteralAction(JavaCodeLiteral ASTnode,java.util.Map context)
	{	PrintWriter outputForClass = (PrintWriter)context.get("OutputForClass");
		outputForClass.print(ASTnode.getValue());
	}
}

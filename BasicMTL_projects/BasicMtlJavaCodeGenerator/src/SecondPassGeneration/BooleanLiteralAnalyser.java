/*
 * $Header: /tmp/cvs2svn/cvsroot/BasicMTL_projects/BasicMtlJavaCodeGenerator/src/SecondPassGeneration/BooleanLiteralAnalyser.java,v 1.3 2003-08-20 16:07:32 ffondeme Exp $
 * Created on 7 août 2003
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
public class BooleanLiteralAnalyser extends TLLTopDownVisitor.BooleanLiteralAnalyser {

	public void BooleanLiteralAction(BooleanLiteral ASTnode,java.util.Map context)
	{	PrintWriter outputForClass = (PrintWriter)context.get("OutputForClass");
		if (ASTnode.getValue())
			outputForClass.print("BMTLBoolean.TRUE");
		else
			outputForClass.print("BMTLBoolean.FALSE");
			
	}

}

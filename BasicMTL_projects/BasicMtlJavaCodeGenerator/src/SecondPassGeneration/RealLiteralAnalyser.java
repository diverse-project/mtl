/*
 * $Header: /tmp/cvs2svn/cvsroot/BasicMTL_projects/BasicMtlJavaCodeGenerator/src/SecondPassGeneration/RealLiteralAnalyser.java,v 1.3 2003-08-20 16:07:34 ffondeme Exp $
 * Created on 8 ao�t 2003
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
public class RealLiteralAnalyser extends TLLTopDownVisitor.RealLiteralAnalyser {

	public void RealLiteralAction(RealLiteral ASTnode,java.util.Map context)
	{	PrintWriter outputForClass = (PrintWriter)context.get("OutputForClass");
		outputForClass.print("new BMTLReal("+ASTnode.getValue()+")");
	}
}

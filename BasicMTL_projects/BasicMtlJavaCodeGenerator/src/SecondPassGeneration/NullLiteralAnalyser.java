/*
 * $Header: /tmp/cvs2svn/cvsroot/BasicMTL_projects/BasicMtlJavaCodeGenerator/src/SecondPassGeneration/NullLiteralAnalyser.java,v 1.2 2003-08-14 21:31:40 ffondeme Exp $
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
public class NullLiteralAnalyser extends TLLTopDownVisitor.NullLiteralAnalyser {

	public void NullLiteralAction(NullLiteral ASTnode,java.util.Map context)
	{	PrintWriter outputForClass = (PrintWriter)context.get("OutputForClass");
		outputForClass.print("NullValueImpl.getTheInstance()");
	}
}
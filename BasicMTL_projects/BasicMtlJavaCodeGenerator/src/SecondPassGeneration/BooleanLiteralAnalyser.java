/*
 * $Header: /tmp/cvs2svn/cvsroot/BasicMTL_projects/BasicMtlJavaCodeGenerator/src/SecondPassGeneration/BooleanLiteralAnalyser.java,v 1.2 2003-08-14 21:31:40 ffondeme Exp $
 * Created on 7 ao�t 2003
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
		outputForClass.print("new BooleanValueImpl(false,null,"+ASTnode.getValue()+')');
	}

}
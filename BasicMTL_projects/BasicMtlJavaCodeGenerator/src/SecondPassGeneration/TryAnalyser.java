/*
 * $Header: /tmp/cvs2svn/cvsroot/BasicMTL_projects/BasicMtlJavaCodeGenerator/src/SecondPassGeneration/TryAnalyser.java,v 1.4 2003-08-20 16:07:34 ffondeme Exp $
 * Created on 7 août 2003
 *
 */
package SecondPassGeneration;

import java.io.*;
import java.util.Map;

import org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.*;

/**
 * @author jpthibau
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class TryAnalyser extends TLLTopDownVisitor.TryAnalyser {

	public Object TryBefore(Try ASTnode,java.util.Map context)
	{	PrintWriter outputForClass = (PrintWriter)context.get("OutputForClass");
		outputForClass.println("try {");
		return null;
	}

	public void TryEndTryBody(java.util.Map context)
	{	PrintWriter outputForClass = (PrintWriter)context.get("OutputForClass");
		outputForClass.println('}');
	}

	public void TryFinallyBody(java.util.Map context)
	{	PrintWriter outputForClass = (PrintWriter)context.get("OutputForClass");
		outputForClass.println("finally {");
	}

	public void TryFinallyEndBody(java.util.Map context)
	{	PrintWriter outputForClass = (PrintWriter)context.get("OutputForClass");
		outputForClass.println('}');
		context.put("NeedsSemiColumn", Boolean.FALSE);
	}

	public void TryBodyInstruction(Object theTry, Object instr, Map context) {
		Boolean needsColumn = (Boolean)context.get("NeedsSemiColumn");
		if (needsColumn == null || needsColumn.booleanValue()) {
			PrintWriter outputForClass = (PrintWriter)context.get("OutputForClass");
			outputForClass.println(';');
		}
		context.put("NeedsSemiColumn", Boolean.TRUE);
	}

}

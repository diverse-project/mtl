/*
 * $Header: /tmp/cvs2svn/cvsroot/BasicMTL_projects/BasicMtlJavaCodeGenerator/src/SecondPassGeneration/OperationCallAnalyser.java,v 1.2 2003-08-14 21:31:40 ffondeme Exp $
 * Created on 8 août 2003
 *
 */
package SecondPassGeneration;

import java.io.*;
import org.irisa.triskell.MT.utils.Java.Mangler;
import org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.*;

/**
 * @author jpthibau
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class OperationCallAnalyser extends TLLTopDownVisitor.OperationCallAnalyser {

	public Object OperationCallBefore(OperationCall ASTnode,java.util.Map context)
	{	return ASTnode; }

	public void OperationCallCaller(Object theOperationCall,Object expr,java.util.Map context)
	{	OperationCall theOpCall=(OperationCall)theOperationCall;
		String mangledOpCall=Mangler.mangle("BMTL_",theOpCall.getName());
		PrintWriter outputForClass = (PrintWriter)context.get("OutputForClass");
		if (theOpCall.getIsToInvoke()) 
			outputForClass.print(".invoke(null,\""+mangledOpCall+"\",new Value[]{");
		else outputForClass.print("."+mangledOpCall+'(');
	}

	public void OperationCallArgSeparator(java.util.Map context)
	{	PrintWriter outputForClass = (PrintWriter)context.get("OutputForClass");
		outputForClass.print(",");
	}

	public void OperationCallAfter(Object theOperationCall,OperationCall ASTnode,java.util.Map context)
	{	OperationCall theOpCall=(OperationCall)theOperationCall;
		PrintWriter outputForClass = (PrintWriter)context.get("OutputForClass");
		if (theOpCall.getIsToInvoke()) 
			outputForClass.print("},new String[]{ ModelElement.OperationDiscriminant})");
		else outputForClass.print(')');
	}

}

/*
 * $Header: /tmp/cvs2svn/cvsroot/BasicMTL_projects/BasicMtlJavaCodeGenerator/src/SecondPassGeneration/OperationCallAnalyser.java,v 1.4 2003-08-20 16:07:31 ffondeme Exp $
 * Created on 8 août 2003
 *
 */
package SecondPassGeneration;

import java.io.*;
import java.util.Map;

import org.irisa.triskell.MT.utils.Java.Mangler;
import org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.*;
import org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.signatures.AttributeGetterSignature;
import org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.signatures.AttributeSetterSignature;

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
		PrintWriter outputForClass = (PrintWriter)context.get("OutputForClass");
		if (theOpCall.getIsToInvoke()) 
			outputForClass.print(".invoke(null,\""+theOpCall.getName()+"\",new Value[]{");
//@TODO IMPORTANT; this is a bug ; called values have to be casted into the correct BMTLDataTypes... This requires to know which operation is called.
		else if (theOpCall.getKind().equals(OperationKind.getAttributeCall()))
			outputForClass.print("."+AttributeGetterSignature.GetPrefix+Mangler.mangle("BMTL_", theOpCall.getName())+'(');
		else if (theOpCall.getKind().equals(OperationKind.getAttributeSet()))
			outputForClass.print("."+AttributeSetterSignature.SetPrefix+Mangler.mangle("BMTL_", theOpCall.getName())+'(');
		else {
			String mangledOpCall=Mangler.mangle("BMTL_",theOpCall.getName());
			outputForClass.print("."+mangledOpCall+'(');
		} 
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

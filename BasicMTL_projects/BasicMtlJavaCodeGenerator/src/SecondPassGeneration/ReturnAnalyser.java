/*
 * $Header: /tmp/cvs2svn/cvsroot/BasicMTL_projects/BasicMtlJavaCodeGenerator/src/SecondPassGeneration/ReturnAnalyser.java,v 1.5 2003-08-26 13:00:15 ffondeme Exp $
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
public class ReturnAnalyser extends TLLTopDownVisitor.ReturnAnalyser {

	public Object ReturnBefore(Return ASTnode,java.util.Map context)
	{	Operation theContainer=ASTnode.getContainerOp();
		QualifiedName returnedType=theContainer.getFeatureType();
		PrintWriter outputForClass = (PrintWriter)context.get("OutputForClass");
		outputForClass.print("return ");
		if (ASTnode.getReturnedExpression() != null)
			outputForClass.print("("+returnedType.getDeclarationName()+")CommonFunctions.toBMTLDataType(");
		else
			outputForClass.print("BMTLVoid.TheInstance");		
		return null; }

	public void ReturnAfter(Object theReturn, Return ASTnode, Map context) {
		PrintWriter outputForClass = (PrintWriter)context.get("OutputForClass");
		if (ASTnode.getReturnedExpression() != null)
			outputForClass.print(')');
	}

}

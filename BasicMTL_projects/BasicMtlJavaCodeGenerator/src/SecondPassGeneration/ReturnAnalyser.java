/*
 * $Header: /tmp/cvs2svn/cvsroot/BasicMTL_projects/BasicMtlJavaCodeGenerator/src/SecondPassGeneration/ReturnAnalyser.java,v 1.2 2003-08-14 21:31:41 ffondeme Exp $
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
public class ReturnAnalyser extends TLLTopDownVisitor.ReturnAnalyser {

	public Object ReturnBefore(Return ASTnode,java.util.Map context)
	{	Operation theContainer=ASTnode.getContainerOp();
		QualifiedName returnedType=theContainer.getFeatureType();
		PrintWriter outputForClass = (PrintWriter)context.get("OutputForClass");
		outputForClass.println("return ");
		if (returnedType.getIsLocalType())
			outputForClass.print("("+returnedType.getLocalMangledName()+')');
		else outputForClass.print("("+returnedType.getExternCompleteName()+')');		
		return null; }

	public void ReturnAfter(Object theReturn,Return ASTnode,java.util.Map context)
	{	PrintWriter outputForClass = (PrintWriter)context.get("OutputForClass");
		outputForClass.println(';');
	}

}

/*
 * $Header: /tmp/cvs2svn/cvsroot/BasicMTL_projects/BasicMtlJavaCodeGenerator/src/SecondPassGeneration/OclTypeLiteralAnalyser.java,v 1.1 2003-08-08 15:41:11 jpthibau Exp $
 * Created on 8 août 2003
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
public class OclTypeLiteralAnalyser extends TLLTopDownVisitor.OclTypeLiteralAnalyser {

	public void OclTypeLiteralAction(OclTypeLiteral ASTnode,java.util.Map context)
	{	PrintWriter outputForClass = (PrintWriter)context.get("OutputForClass");
		QualifiedName type=ASTnode.getTheType();
		outputForClass.print("new OclTypeValueImpl(false,null,");
		if (type.getIsModelType()) {
			if (type.getIsRepositoryModel())
				outputForClass.print("\"isRepositoryModel\",");
			else outputForClass.print("\"isTypedModel\",");
		}
		if (type.getIsLocalType()) outputForClass.print("\"isLocalType\",");
		if (type.getIsExternType()) outputForClass.print("\"isExternType\",");
		outputForClass.print("new java.util.Vector()={");
		for (int i=0;i<type.size();i++) {
			outputForClass.print("\""+type.get(i)+"\"");
			if (i<type.size()-1) outputForClass.print(",");
		}
		outputForClass.print("})");
	}
}

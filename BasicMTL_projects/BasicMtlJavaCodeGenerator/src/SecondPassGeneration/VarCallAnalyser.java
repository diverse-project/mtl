/*
 * $Header: /tmp/cvs2svn/cvsroot/BasicMTL_projects/BasicMtlJavaCodeGenerator/src/SecondPassGeneration/VarCallAnalyser.java,v 1.3 2003-08-21 20:10:17 ffondeme Exp $
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
public class VarCallAnalyser extends TLLTopDownVisitor.VarCallAnalyser {

	public void VarCallAction(VarCall ASTnode,java.util.Map context)
	{	PrintWriter outputForClass = (PrintWriter)context.get("OutputForClass");
		CommonFunctions.generateCastBefore(outputForClass, ASTnode);
		outputForClass.print(ASTnode.getRelatedDecl().getMangle());
		CommonFunctions.generateCastAfter(outputForClass, ASTnode);
	}

}

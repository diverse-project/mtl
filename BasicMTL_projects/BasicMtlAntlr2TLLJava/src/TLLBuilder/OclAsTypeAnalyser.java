/*
 * $Header: /tmp/cvs2svn/cvsroot/BasicMTL_projects/BasicMtlAntlr2TLLJava/src/TLLBuilder/OclAsTypeAnalyser.java,v 1.2 2003-10-14 15:15:33 jpthibau Exp $
 * Created on 24 juil. 2003
 *
 */
package TLLBuilder;

import java.util.Map;
import java.util.Vector;

import org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.OclAsType;
import org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.*;

/**
 * @author jpthibau
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class OclAsTypeAnalyser extends ASTTopDownVisitor.OclAsTypeAnalyser {

	public void OclAsTypeType(boolean isConstant,Object expr, Vector type,String typeVar,String methodVar,String parameterVar,java.util.Map context) {
		if (isConstant)
		{	if (expr == null)
				expr = new SelfLiteral();
			((Expression)expr).setToBeCasted(CommonFunctions.findOrAddType(type, (BasicMtlLibrary)context.get("TheCreatedLibrary")));
		}
		else
			{	if (expr == null)
					expr = new SelfLiteral(); //TODO check this part
				((Expression)expr).setToBeCasted(CommonFunctions.findOrAddType(type, (BasicMtlLibrary)context.get("TheCreatedLibrary")));
				((Expression)expr).setToBeCastedWithTypeVar(typeVar);
				((Expression)expr).setToBeCastedWithMethodVar(methodVar);
				((Expression)expr).setToBeCastedWithParameterVar(parameterVar);
		}
	}

}

/*
 * Created on 24 juil. 2003
 * $Id: OclAsTypeAnalyser.java,v 1.3 2004-02-16 17:32:59 dvojtise Exp $
 * Authors : jpthibau
 * 
 * Copyright 2004 - INRIA - LGPL license
 */
package TLLBuilder;

// import java.util.Map;
import java.util.Vector;

// import org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.OclAsType;
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

/*
 * $Header: /tmp/cvs2svn/cvsroot/BasicMTL_projects/BasicMtlAntlr2TLLJava/src/TLLBuilder/OclAsTypeAnalyser.java,v 1.1 2003-08-21 20:03:07 ffondeme Exp $
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

	public void OclAsTypeType(Object expr, Vector type, Map context) {
		if (expr == null)
			expr = new SelfLiteral();
		((Expression)expr).setToBeCasted(CommonFunctions.findOrAddType(type, (BasicMtlLibrary)context.get("TheCreatedLibrary")));
	}

}

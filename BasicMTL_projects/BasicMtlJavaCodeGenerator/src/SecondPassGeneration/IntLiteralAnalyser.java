/*
 * Created on 8 août 2003
 * $Id: IntLiteralAnalyser.java,v 1.6 2004-02-16 17:36:43 dvojtise Exp $
 * Authors : jpthibau
 * 
 * Copyright 2004 - INRIA - LGPL license
 */
package SecondPassGeneration;

import java.io.*;
import org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.*;

// import TLLTopDownVisitor.OclTypeLiteralAnalyser;

/**
 * @author jpthibau
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class IntLiteralAnalyser extends TLLTopDownVisitor.IntLiteralAnalyser {

	public void IntLiteralAction(IntLiteral ASTnode,java.util.Map context)
	{	PrintWriter outputForClass = (PrintWriter)context.get("OutputForClass");
		CommonFunctions.generateCastBefore(outputForClass, ASTnode,ASTnode.getIsTrownExpression());
		outputForClass.print("new BMTLInteger("+ASTnode.getValue()+')');
		CommonFunctions.generateCastAfter(outputForClass, ASTnode,ASTnode.getIsTrownExpression());
	}
}

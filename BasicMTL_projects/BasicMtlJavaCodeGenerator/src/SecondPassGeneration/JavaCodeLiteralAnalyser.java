/*
 * Created on 8 août 2003
 * $Id: JavaCodeLiteralAnalyser.java,v 1.2 2004-02-16 17:36:42 dvojtise Exp $
 * Authors : jpthibau
 * 
 * Copyright 2004 - INRIA - LGPL license
 */
package SecondPassGeneration;

import java.io.*;
import org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.*;
// import org.irisa.triskell.MT.utils.Java.JavaStringLiteralEncoder;

/**
 * @author jpthibau
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class JavaCodeLiteralAnalyser extends TLLTopDownVisitor.JavaCodeLiteralAnalyser {

	public void JavaCodeLiteralAction(JavaCodeLiteral ASTnode,java.util.Map context)
	{	PrintWriter outputForClass = (PrintWriter)context.get("OutputForClass");
		outputForClass.print(ASTnode.getValue());
	}
}

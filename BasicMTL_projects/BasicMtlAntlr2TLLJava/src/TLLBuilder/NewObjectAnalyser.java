/*
 * $Header: /tmp/cvs2svn/cvsroot/BasicMTL_projects/BasicMtlAntlr2TLLJava/src/TLLBuilder/NewObjectAnalyser.java,v 1.1 2003-08-06 16:18:46 jpthibau Exp $
 * Created on 25 juil. 2003
 *
 */
package TLLBuilder;

import org.irisa.triskell.MT.visitors.Java.AnalysingVisitor.*;
import org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.*;

/**
 * @author jpthibau
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class NewObjectAnalyser extends ASTTopDownVisitor.NewObjectAnalyser {

	public Object NewObjectBefore(org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.NewObject ASTnode,java.util.Map context)
	{	int lineNumber=Integer.parseInt((String)ASTnode.getProperty("LineNumber").getValue());
		NewObject theCreatedNewObject=new NewObject(lineNumber);
		BasicMtlLibrary theCreatedLib=(BasicMtlLibrary)context.get("TheCreatedLibrary");
		Property typeToCreate=(Property)ASTnode.getProperty("TypeToCreate");
		QualifiedName type=CommonFunctions.findOrAddType((java.util.Vector)typeToCreate.getValue(),theCreatedLib);
		theCreatedNewObject.setTypeToCreate(type);
		return theCreatedNewObject;
	}

	public void NewObjectArgument(Object theNewObject,Object objectArg,java.util.Map context)
	{	NewObject theCreatedNewObject=(NewObject)theNewObject;
		Expression arg=(Expression)objectArg;
		theCreatedNewObject.appendArguments(arg);
	}

	public void NewObjectAfter(Object theNewObject,org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.NewObject ASTnode,java.util.Map context)
	{	NewObject theCreatedNewObject=(NewObject)theNewObject;
		context.put("Instruction",theCreatedNewObject);
	}

}

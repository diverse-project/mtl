/*
 * $Header: /tmp/cvs2svn/cvsroot/BasicMTL_projects/BasicMtlAntlr2TLLJava/src/TLLBuilder/VarCallAnalyser.java,v 1.2 2003-08-14 20:47:47 ffondeme Exp $
 * Created on 25 juil. 2003
 *
 */
package TLLBuilder;

import org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.*;

/**
 * @author jpthibau
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class VarCallAnalyser extends ASTTopDownVisitor.VarCallAnalyser {

	public void VarCallAction(org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.VarCall ASTnode,java.util.Map context)
	{	String varName=ASTnode.getVarName();
		java.util.Vector knownAttributes=(java.util.Vector)context.get("KnownAttributes");
		//check whether the varCall is an attributeCall
		boolean found=false;
		if (knownAttributes !=null) {
			int i=0;
			while (!found &&(i<knownAttributes.size()))
				{if (varName.equals(knownAttributes.get(i))) found=true;
				 i++; } 
		}
		if (found) {
			OperationCall theCreatedOpCall=new OperationCall("get_"+varName,-1000);
			theCreatedOpCall.setContainerOp((Operation)context.get("CurrentOperation"));
			context.put("Instruction",theCreatedOpCall);
		}
		else {
			//first find the varName in the known declared variables
			java.util.Vector knownVarDecls=(java.util.Vector)context.get("DeclaredParametersandVars");
			found=false;
			int i=0;
			int foundIndex=0;
			while (!found &&(i<knownVarDecls.size()))
				{if (varName.equals(((VarDeclaration)knownVarDecls.get(i)).getName()))
					{found=true;
					foundIndex=i;} 
				 i++;
				}
			if (found) {
				VarCall theCreatedVarCall=new VarCall(ASTnode.getVarName());
				theCreatedVarCall.setRelatedDecl((VarDeclaration)knownVarDecls.get(foundIndex));
				theCreatedVarCall.setContainerOp((Operation)context.get("CurrentOperation"));
				context.put("Instruction",theCreatedVarCall); }
			else {
				ANTLR2TLLJava.antlr2tll.getLog().error("Undeclared variable found (VarCall):"+varName);
				VarCall theCreatedVarCall = new VarCall(ASTnode.getVarName());
				theCreatedVarCall.setContainerOp((Operation)context.get("CurrentOperation"));
				context.put("Instruction",theCreatedVarCall);
			}
		}
	}
}

/*
 * $Header: /tmp/cvs2svn/cvsroot/BasicMTL_projects/BasicMtlAntlr2TLLJava/src/TLLBuilder/VarCallAnalyser.java,v 1.4 2003-08-22 18:26:29 ffondeme Exp $
 * Created on 25 juil. 2003
 *
 */
package TLLBuilder;

import java.util.Arrays;
import java.util.Vector;

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
		BasicMtlLibrary theCreatedLib=(BasicMtlLibrary)context.get("TheCreatedLibrary");
		int lineNumber= ASTnode.getProperty("LineNumber") == null ? -1000:Integer.parseInt((String)ASTnode.getProperty("LineNumber").getValue());
		//first find the varName in the known declared variables
		java.util.Vector known=(java.util.Vector)context.get("DeclaredParametersandVars");
		boolean found=false;
		int i=0;
		int foundIndex=0;
		while (!found &&(i<known.size()))
			{if (varName.equals(((VarDeclaration)known.get(i)).getName()))
				{found=true;
				foundIndex=i;} 
			 i++;
			}
		if (found) {
			VarCall theCreatedVarCall=new VarCall(ASTnode.getVarName());
			theCreatedVarCall.setRelatedDecl((VarDeclaration)known.get(foundIndex));
			theCreatedVarCall.setContainerOp((Operation)context.get("CurrentOperation"));
			context.put("Instruction",theCreatedVarCall); }
		if (! found) {
			//check whether the varCall is an attributeCall
			known=(java.util.Vector)context.get("KnownAttributes");
			if (known !=null) {
				i=0;
				while (!found &&(i<known.size()))
					{if (varName.equals(known.get(i))) found=true;
					 i++; } 
			}
			if (found) {
				OperationCall theCreatedOpCall=new OperationCall(varName,lineNumber);
				theCreatedOpCall.setKind(OperationKind.getAttributeCall());
				theCreatedOpCall.setContainerOp((Operation)context.get("CurrentOperation"));
				context.put("Instruction",theCreatedOpCall);
			}
		}
		boolean inClass = context.get("InClass") != null && ((Boolean)context.get("InClass")).booleanValue();
		if (!found) {
			//check whether the varCall is an access to this library
			found = theCreatedLib.getName().equals(varName);
			if (found) {
				if (!inClass)
					context.put("Instruction",new SelfLiteral());
				else {
					OperationCall theCreatedOpCall=new OperationCall(varName,lineNumber);
					theCreatedOpCall.setKind(OperationKind.getCurrentLibraryCall());
					theCreatedOpCall.setContainerOp((Operation)context.get("CurrentOperation"));
					context.put("Instruction",theCreatedOpCall);
				}
			}
		}
		if (!found) {
			//check whether the varCall is a library parameter or use
			ModelRef lib = null;
			for (i = 0; (!found) && i < theCreatedLib.cardUsedModels(); ++i) {
				lib = theCreatedLib.getUsedModels(i);
				found = lib.getName().equals(varName);
			}
					
			if (found) {
				Expression getLibrary;
				if (!inClass) {
					getLibrary = new SelfLiteral();
				} else {
					getLibrary=new OperationCall(varName,lineNumber);
					((OperationCall)getLibrary).setKind(OperationKind.getCurrentLibraryCall());
					((OperationCall)getLibrary).setContainerOp((Operation)context.get("CurrentOperation"));
				}
				OperationCall theCreatedOpCall=new OperationCall(varName,lineNumber);
				theCreatedOpCall.setKind(OperationKind.getAttributeCall());
				theCreatedOpCall.setCaller(getLibrary);
				theCreatedOpCall.setContainerOp((Operation)context.get("CurrentOperation"));
				context.put("Instruction",theCreatedOpCall);
			}
		}
		if (!found) {
			//the last hope: this is a used library...
			Expression getLibrary;
			if (!inClass) {
				getLibrary = new SelfLiteral();
			} else {
				getLibrary=new OperationCall(varName,lineNumber);
				((OperationCall)getLibrary).setKind(OperationKind.getCurrentLibraryCall());
				((OperationCall)getLibrary).setContainerOp((Operation)context.get("CurrentOperation"));
			}
			OperationCall theCreatedOpCall=new OperationCall(varName,lineNumber);
			theCreatedOpCall.setKind(OperationKind.getLibraryCall());
			Vector qn = new Vector(1);
			qn.add(varName);
			CommonFunctions.findOrAddType(qn, theCreatedLib);
			theCreatedOpCall.setCaller(getLibrary);
			theCreatedOpCall.setContainerOp((Operation)context.get("CurrentOperation"));
			context.put("Instruction",theCreatedOpCall);
		}
//		ANTLR2TLLJava.antlr2tll.getLog().error("Undeclared variable found (VarCall):"+varName);
//		VarCall theCreatedVarCall = new VarCall(ASTnode.getVarName());
//		theCreatedVarCall.setContainerOp((Operation)context.get("CurrentOperation"));
//		context.put("Instruction",theCreatedVarCall);
	}
}

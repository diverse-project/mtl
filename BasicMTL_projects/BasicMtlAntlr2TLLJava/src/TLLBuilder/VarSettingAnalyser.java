/*
 * $Header: /tmp/cvs2svn/cvsroot/BasicMTL_projects/BasicMtlAntlr2TLLJava/src/TLLBuilder/VarSettingAnalyser.java,v 1.3 2003-08-19 13:32:51 ffondeme Exp $
 * Created on 24 juil. 2003
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
public class VarSettingAnalyser extends ASTTopDownVisitor.VarSettingAnalyser {

	public Object VarSettingBefore(org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.VarSetting ASTnode,java.util.Map context)
	{	int lineNumber=Integer.parseInt((String)ASTnode.getProperty("LineNumber").getValue());
		Instruction theCreatedInstruction;
		Property classOfAttribute=(Property)ASTnode.getProperty("ClassOfSetAttribute");
		if (classOfAttribute == null)
			theCreatedInstruction=new VarSetting(ASTnode.getVarName(),lineNumber);
		else { //AttributeSetter => OperationCall
			OperationCall theCreatedOpCall=new OperationCall(ASTnode.getVarName(),lineNumber);
			theCreatedOpCall.setKind(OperationKind.getAttributeSet());
			theCreatedOpCall.setCaller(new VarCall((String)classOfAttribute.getValue()));
			theCreatedInstruction=theCreatedOpCall;
		}
		return theCreatedInstruction;
	}

	public void VarSettingExpression(Object theInstr,Object objectExpr,java.util.Map context)
	{	Instruction theCreatedInstruction=(Instruction)theInstr;
		Expression expr=(Expression)objectExpr;
		if (theCreatedInstruction.getClass().getName().equalsIgnoreCase("org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.VarSetting"))
			((VarSetting)theCreatedInstruction).setValue(expr);
		else ((OperationCall)theCreatedInstruction).appendArguments(expr); //AttributeSetter => OperationCall
	}

	public void VarSettingAfter(Object theInstr,org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.VarSetting ASTnode,java.util.Map context)
	{	Instruction theCreatedInstruction=(Instruction)theInstr;
		theCreatedInstruction.setContainerOp((Operation)context.get("CurrentOperation"));
		if (theCreatedInstruction instanceof OperationCall)
			((OperationCall)theCreatedInstruction).getCaller().setContainerOp((Operation)context.get("CurrentOperation"));
		context.put("Instruction",theCreatedInstruction);
	}

}

/*
 * Created on 24 juil. 2003
 * $Id: VarSettingAnalyser.java,v 1.11 2004-10-18 15:05:13 jpthibau Exp $
 * Authors : jpthibau
 * 
 * Copyright 2004 - INRIA - LGPL license
 */
package TLLBuilder;

import java.util.Map;

//import org.irisa.triskell.MT.visitors.Java.AnalysingVisitor.*;
import org.apache.log4j.Logger;
import org.irisa.triskell.MT.utils.MessagesHandler.MSGHandler;
import org.irisa.triskell.MT.visitors.Java.GenericVisitor.Visitor;
import org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.*;

import ANTLR2TLLJava.antlr2tll;

/**
 * @author jpthibau
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class VarSettingAnalyser extends ASTTopDownVisitor.VarSettingAnalyser {
	static final Logger log=Logger.getLogger("MSGHandler");

/*
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
		if (theCreatedInstruction instanceof org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.VarSetting)
			((VarSetting)theCreatedInstruction).setValue(expr);
		else ((OperationCall)theCreatedInstruction).appendArguments(expr); //AttributeSetter => OperationCall
	}*/

	public void VarSettingAfter(Object theInstr,org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.VarSetting ASTnode,java.util.Map context)
	{	Instruction theCreatedInstruction=(Instruction)theInstr;
		theCreatedInstruction.setContainerOp((Operation)context.get("CurrentOperation"));
		context.put("Instruction",theCreatedInstruction);
	}

	public void VarSettingExpression(
		Object theVarSet,
		Object expr,
		Map context) {
			if (theVarSet instanceof VarSetting) {
				((VarSetting)theVarSet).setValue((Expression)expr);
			} else if (theVarSet instanceof OperationCall) {
				((OperationCall)theVarSet).appendArguments((Expression)expr);
			}
	}

	public Object VarSettingBefore(org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.VarSetting ASTnode, Map context) {
		Visitor v = (Visitor)context.get("visitor");
		org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.VarCall vc = new org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.VarCall(ASTnode.getVarName());
		for (int i = 0; i < ASTnode.cardDecoration(); ++i)
			vc.appendDecoration(ASTnode.getDecoration(i));
		vc.accept(v, context);
		Object c = context.get("Instruction");
		int lineNumber=Integer.parseInt((String)ASTnode.getProperty("LineNumber").getValue());
		ASTNode ret = null;
		if (c instanceof VarCall) {
			ret = new VarSetting(((VarCall)c).getVarName(), lineNumber);
		} else if ((c instanceof OperationCall) && ((OperationCall)c).getKind().equals(OperationKind.getAttributeCall())) {
			ret = (OperationCall)c;
			((OperationCall)ret).setKind(OperationKind.getAttributeSet());
		} else
			{	log.error("line " + lineNumber + ": Can just affect attributes or variables.");
				if (c instanceof VarCall) log.error("Probably undeclared variable... "+((VarCall)c).getVarName());
				else log.error("Probably undeclared variable... "+((OperationCall)c).getName());
			} 
		return ret;
	}

}

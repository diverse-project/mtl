/*
 * $Header: /tmp/cvs2svn/cvsroot/BasicMTL_projects/BasicMtlAstJava/src/ASTTopDownVisitor/VarSettingAnalyser.java,v 1.1 2003-07-28 07:35:34 jpthibau Exp $
 * Created on 24 juil. 2003
 *
 */
package ASTTopDownVisitor;

import org.irisa.triskell.MT.visitors.Java.AnalysingVisitor.*;
import org.irisa.triskell.MT.visitors.Java.GenericVisitor.*;
import org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.*;

/**
 * @author jpthibau
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class VarSettingAnalyser extends Analyser {

	public VarSettingAnalyser()
	{	super(VarSetting.class);
	}

	public void analyse(Visitable node,Visitor visitor,java.util.Map context)
	{	VarSetting ASTnode=(VarSetting) node;
		this.VarSettingBefore(ASTnode,context);
		((Expression)ASTnode.getValue()).accept(visitor,context);
		this.VarSettingExpression(context.get("Instruction"),context);
		this.VarSettingAfter(ASTnode,context);
	}

	public void VarSettingBefore(VarSetting ASTnode,java.util.Map context) {}

	public void VarSettingExpression(Object expr,java.util.Map context) {}

	public void VarSettingAfter(VarSetting ASTnode,java.util.Map context) {}

}

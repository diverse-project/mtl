/*
 * $Header: /tmp/cvs2svn/cvsroot/BasicMTL_projects/BasicMtlTLLJava/src/TLLTopDownVisitor/VarSettingAnalyser.java,v 1.1 2003-08-06 16:13:26 jpthibau Exp $
 * Created on 24 juil. 2003
 *
 */
package TLLTopDownVisitor;

import org.irisa.triskell.MT.visitors.Java.AnalysingVisitor.*;
import org.irisa.triskell.MT.visitors.Java.GenericVisitor.*;
import org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.*;

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
		Object theVarSet=this.VarSettingBefore(ASTnode,context);
		((Expression)ASTnode.getValue()).accept(visitor,context);
		this.VarSettingExpression(theVarSet,context.get("Instruction"),context);
		this.VarSettingAfter(theVarSet,ASTnode,context);
	}

	public Object VarSettingBefore(VarSetting ASTnode,java.util.Map context)
	{	return null; }

	public void VarSettingExpression(Object theVarSet,Object expr,java.util.Map context) {}

	public void VarSettingAfter(Object theVarSet,VarSetting ASTnode,java.util.Map context) {}

}

/*
 * $Header: /tmp/cvs2svn/cvsroot/BasicMTL_projects/TLLTypeChecker/src/OperationCallChecker/VarSettingAnalyser.java,v 1.2 2003-08-14 21:00:19 ffondeme Exp $
 * Created on 1 août 2003
 *
 */
package OperationCallChecker;

import java.util.Map;

import org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.*;

import TypeChecker.TLLtypechecking;

/**
 * @author jpthibau
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class VarSettingAnalyser extends TLLTopDownVisitor.VarSettingAnalyser {

	public Object VarSettingBefore(VarSetting ASTnode, Map context) {
		VarSetting setting = (VarSetting)ASTnode;
		setting.setModifiedVar(CommonFunctions.getVarDeclaration(setting.getContainerOp(), setting.getVarName(), setting.getLineNumber()));
		return super.VarSettingBefore(ASTnode, context);
	}

}

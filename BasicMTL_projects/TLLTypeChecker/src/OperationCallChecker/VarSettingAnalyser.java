/*
 * Created on 1 août 2003
 * $Id: VarSettingAnalyser.java,v 1.3 2004-02-16 17:18:11 dvojtise Exp $
 * Authors : jpthibau
 * 
 * Copyright 2004 - INRIA - LGPL license
 */
package OperationCallChecker;

import java.util.Map;

import org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.*;

//import TypeChecker.TLLtypechecking;

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

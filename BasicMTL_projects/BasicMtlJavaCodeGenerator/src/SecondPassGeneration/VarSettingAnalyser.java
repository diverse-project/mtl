/*
 * $Header: /tmp/cvs2svn/cvsroot/BasicMTL_projects/BasicMtlJavaCodeGenerator/src/SecondPassGeneration/VarSettingAnalyser.java,v 1.4 2003-08-20 16:07:33 ffondeme Exp $
 * Created on 7 août 2003
 *
 */
package SecondPassGeneration;

import java.io.PrintWriter;
import java.util.Map;

import org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.QualifiedName;
import org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.VarDeclaration;
import org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.VarSetting;

/**
 * @author jpthibau
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class VarSettingAnalyser extends TLLTopDownVisitor.VarSettingAnalyser {

	public Object VarSettingBefore(VarSetting ASTnode,java.util.Map context)
	{	VarDeclaration theSetVar=ASTnode.getModifiedVar();
		QualifiedName type=theSetVar.getType();
		PrintWriter outputForClass = (PrintWriter)context.get("OutputForClass");
		outputForClass.print(theSetVar.getMangle()+'=');
		outputForClass.print("("+type.getDeclarationName()+")CommonFunctions.toBMTLDataType(");
		return null; }

	public void VarSettingAfter(
		Object theVarSet,
		VarSetting ASTnode,
		Map context) {
		PrintWriter outputForClass = (PrintWriter)context.get("OutputForClass");
		outputForClass.print(')');
	}

}

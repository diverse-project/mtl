/*
 * Created on 14 août 2003
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package OperationCallChecker;

import org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.VarDeclaration;
import org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.Operation;

import TypeChecker.TLLtypechecking;
;

/**
 * @author ffondeme
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class CommonFunctions {
	public static VarDeclaration getVarDeclaration (Operation operation, String name, int line) {
		VarDeclaration declaration = null;
		for (int i = operation.cardDeclaredVariables()-1; declaration == null && i >= 0; --i)
			if (operation.getDeclaredVariables(i).getName().equals(name))
				declaration = operation.getDeclaredVariables(i);
		if (declaration == null)
			TLLtypechecking.getLog().error("line " + line + ": Unknown variable " + name);
		return declaration;
	}
}

/*
 * Created on 25 août 2003
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package SecondPassGeneration;

import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

import org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.Associate;

/**
 * @author ffondeme
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class AssociateAnalyser extends TLLTopDownVisitor.AssociateAnalyser {

	public Object AssociateBefore(Associate ASTnode, Map context) {
		context.remove("roles");
		return null;
	}

	public void AssociateAfter(
		Object theAssociate,
		Associate ASTnode,
		Map context) {
		Vector roles = (Vector)context.get("roles");
		PrintWriter outputForClass = (PrintWriter)context.get("OutputForClass");
		String assoc = CommonFunctions.generateNewSymbol();
		outputForClass.print("MetaAssociation " + assoc + " = " + roles.get(0) + "_expr.getAPI().getMetaAssociationWithAssociationEnds(new MetaAssociationEnd [] {");
		Iterator rolesIt = roles.iterator();
		boolean first = true;
		while (rolesIt.hasNext()) {
			if (first)
				first = false;
			else
				outputForClass.print(", ");
			outputForClass.print(rolesIt.next().toString() + "_ae");
		}
		outputForClass.println("});");
		if (ASTnode.getIsAssociate()) outputForClass.print(assoc + ".associateModelElements(null, new ModelRole [] {");
		else outputForClass.print(assoc + ".dissociateModelElements(null, new ModelRole [] {");
		rolesIt = roles.iterator();
		first = true;
		while (rolesIt.hasNext()) {
			if (first)
				first = false;
			else
				outputForClass.print(", ");
			String r = (String)rolesIt.next();
			outputForClass.print(r + "_expr.getAPI().getRole(" + r + "_expr, " + r + "_ae)");
		}
		outputForClass.print("})");
		context.remove("roles");
	}

}

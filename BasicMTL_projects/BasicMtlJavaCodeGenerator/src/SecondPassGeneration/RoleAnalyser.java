/*
 * Created on 25 août 2003
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package SecondPassGeneration;

import java.io.PrintWriter;
import java.util.Map;
import java.util.Vector;

import org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.Role;
import org.irisa.triskell.MT.utils.Java.AWK;

/**
 * @author ffondeme
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class RoleAnalyser extends TLLTopDownVisitor.RoleAnalyser {

	public void RoleExpression(Object theRole, Object expr, Map context) {
		PrintWriter outputForClass = (PrintWriter)context.get("OutputForClass");
		outputForClass.println(';');
	}

	public Object RoleBefore(Role ASTnode, Map context) {
		Vector roles = (Vector)context.get("roles");
		if (roles == null) {
			roles = new Vector();
			context.put("roles", roles);
		}
		String var = CommonFunctions.generateNewSymbol();
		roles.add(var);
		PrintWriter outputForClass = (PrintWriter)context.get("OutputForClass");
		outputForClass.print("ModelElement " + var + "_expr = (ModelElement)");
		return ASTnode;
	}

	public void RoleAfter(Object theRole, Role ASTnode, Map context) {
		Vector roles = (Vector)context.get("roles");
		String var = (String)roles.lastElement();
		PrintWriter outputForClass = (PrintWriter)context.get("OutputForClass");
		outputForClass.println("if (" + var + "_expr instanceof BMTLModelElementInterface) " + var + "_expr = ((BMTLModelElementInterface)" + var + "_expr).getModelElementDelegate();");
		StringBuffer metaClass = new StringBuffer();
		if (ASTnode.getLinkedEltType() == null)
			metaClass.append("null");
		else {
			metaClass.append("(MetaClass)this.getLibrary().getMetaClass(new String [] {\"");
			metaClass.append(AWK.mergeCollection(ASTnode.getLinkedEltType(), "\", \""));
			metaClass.append("\"})");
		}
		StringBuffer name = new StringBuffer();
		if (ASTnode.getRoleName() == null)
			name.append("null");
		else
			name.append(ASTnode.getRoleName());
		StringBuffer scope = new StringBuffer();
		if(ASTnode.getExpression().getToBeCasted() == null)
			scope.append("null");
		else {
			scope.append("(MetaClass)this.getLibrary().getMetaClass(new String [] {\"");
			scope.append(AWK.mergeCollection(ASTnode.getLinkedEltType(), "\", \""));
			scope.append("\"})");
		}
		outputForClass.println("MetaAssociationEnd " + var + "_ae = " + var + "_expr.getAPI().getMetaAssociationEnd(\"" + name + "\", " + metaClass + ", " + scope + ");");
	}

}

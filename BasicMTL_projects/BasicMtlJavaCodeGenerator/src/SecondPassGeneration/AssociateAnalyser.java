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
		//test whether generation is for modelelements or for BasicMtl objects
		if (ASTnode.getRoles(0).getLinkedEltType().getIsModelType())
			context.put("AssociateKind","ModelElementsAssociate");
		else context.put("AssociateKind","BMTLObjectsAssociate");
		return null;
	}

	public void AssociateModelElementsgeneration(Vector roles,Associate ASTnode,PrintWriter outputForClass)
	{
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
	}

	public void AssociateBMTLObjectsgeneration(Vector roles,Associate ASTnode,PrintWriter outputForClass,Map context)
	{
		int i;
		String realAssociationName=null;
		String rolesNames = CommonFunctions.generateNewSymbol();
		String rolesValues = CommonFunctions.generateNewSymbol();
		String assocInstances = CommonFunctions.generateNewSymbol();
		String assoc = CommonFunctions.generateNewSymbol();
		java.util.Vector knownAssociations = (java.util.Vector)context.get("KnownAssociations");
		outputForClass.println("BMTLOrderedSetInterface "+rolesNames+"=new BMTLOrderedSet();");
		for (i=0;i<ASTnode.cardRoles();i++)
			outputForClass.println(rolesNames+"="+rolesNames+".BMTL_including(new BMTLString(\""+ASTnode.getRoles(i).getRoleName()+"\"));");
		outputForClass.println("BMTLOrderedSetInterface "+rolesValues+"=new BMTLOrderedSet();");
		Iterator rolesIt = roles.iterator();
		rolesIt = roles.iterator();
		while (rolesIt.hasNext()) {
			String r = (String)rolesIt.next();
			outputForClass.println(rolesValues+"="+rolesValues+".BMTL_including("+ r + "_expr);");
		}
		for (i=0;i<knownAssociations.size();i++)
		{	int j=0;
			while (j < ASTnode.cardRoles()
				&& ((String)knownAssociations.get(i)).lastIndexOf(ASTnode.getRoles(j).getRoleName()) != -1)
				j++;
			if (j==ASTnode.cardRoles()) realAssociationName=(String)knownAssociations.get(i); 
		}
//TODO introduce logger		if (realAssociationName==null) log.error
  		outputForClass.println("BMTL_"+realAssociationName+"Interface "+assoc+";");
	  	outputForClass.println("Value [] "+assocInstances+"=this.getLibrary().getMetaClass(new String [] {\""+realAssociationName+"\"}).allInstances().getTheCollection();");
		outputForClass.println("if ("+assocInstances+".length==0)");
		outputForClass.println(assoc+"=(BMTL_"+realAssociationName+"Interface)((InstanciableType)this.getLibrary().getMetaClass(new String [] {\""+realAssociationName+"\"})).instanciate();");
		outputForClass.println("else "+assoc+"=(BMTL_"+realAssociationName+"Interface)"+assocInstances+"[0];");
		if (ASTnode.getIsAssociate()) outputForClass.println(assoc+".BMTL_associateOperation("+rolesNames+","+rolesValues+");");
		else outputForClass.println(assoc+".BMTL_dissociateOperation("+rolesNames+","+rolesValues+");");
		rolesIt = roles.iterator();
		while (rolesIt.hasNext()) {
			String r = (String)rolesIt.next();
			outputForClass.println(r + "_expr.BMTL_"+realAssociationName+"setLink("+assoc+");");
		}
	}

	public void AssociateAfter(
		Object theAssociate,
		Associate ASTnode,
		Map context) {
		Vector roles = (Vector)context.get("roles");
		PrintWriter outputForClass = (PrintWriter)context.get("OutputForClass");
		String associateKind = (String)context.get("AssociateKind");
		if (associateKind.equals("ModelElementsAssociate"))
			AssociateModelElementsgeneration(roles,ASTnode,outputForClass);
		else AssociateBMTLObjectsgeneration(roles,ASTnode,outputForClass,context);
		context.remove("roles");
	}

}

/*
 * $Header: /tmp/cvs2svn/cvsroot/BasicMTL_projects/BasicMtlJavaCodeGenerator/src/SecondPassGeneration/ForeachAnalyser.java,v 1.3 2004-04-28 15:44:50 edrezen Exp $
 * Created on 7 août 2003
 *
 */
package SecondPassGeneration;

import java.io.PrintWriter;
import org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.Foreach;
import org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.Instruction;

/**
 * @author jpthibau
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class ForeachAnalyser extends TLLTopDownVisitor.ForeachAnalyser 
{
	/** */
	public Object ForeachBefore (Foreach ASTnode,java.util.Map context)
	{	
		// we generate a symbol for the iterator
		String genSymbol = CommonFunctions.generateNewSymbol();

		PrintWriter outputForClass = (PrintWriter)context.get("OutputForClass");
	
		outputForClass.print (
			"org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLIteratorInterface " 
			+ genSymbol 
			+ " = (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLIteratorInterface)CommonFunctions.toBMTLDataType("
		);

		return genSymbol;
	}


	/** */
	public void ForeachAfter (
		Foreach ASTnode, 
		Object createdObject, 
		java.util.Map context
	)
	{	
		PrintWriter outputForClass = (PrintWriter)context.get("OutputForClass");
		
		if (ASTnode.getCondition() != null)
		{
			outputForClass.println ("} // end of foreach 'where' condition");
		}
		outputForClass.println ((String)createdObject + ".BMTL_next();");		
		outputForClass.println ("}");

		context.put("NeedsSemiColumn", Boolean.FALSE);		
	}

	
	/** */
	public void ForeachCondition (
		Foreach ASTnode, 
		Object createdObject, 
		java.util.Map context
	) 
	{
		PrintWriter outputForClass = (PrintWriter)context.get("OutputForClass");

		if (ASTnode.getCondition() != null)
		{
			outputForClass.println (").getTheBoolean()) {");
		}
	}


	/** */
    public void ForeachCollection (
		Foreach ASTnode, 
		Object createdObject, 
		java.util.Map context
	) 
	{
		PrintWriter outputForClass = (PrintWriter)context.get("OutputForClass");
		
		outputForClass.println (".invoke (null, \"getNewIterator\",	new Value[]{}, new String[]{ModelElement.OperationDiscriminant}	) );" );

		outputForClass.println ("while (" + (String)createdObject + ".BMTL_isOn().getTheBoolean())");
		outputForClass.println ("{");

		outputForClass.print (ASTnode.getVarDeclaration().getMangle());
		outputForClass.print (" = ");
		outputForClass.print ("(");
		outputForClass.print (ASTnode.getVarDeclaration().getType().getDeclarationName());
		outputForClass.print (")");
		outputForClass.print ("CommonFunctions.toBMTLDataType (" + (String)createdObject + ".BMTL_item())");
		outputForClass.println (";");
		
		if (ASTnode.getCondition() != null)
		{
			outputForClass.print ("if ( ((BooleanValue)");
		}
    }
	
	
	/** */
	public void ForeachBodyInstruction (
		Instruction instr, 
		Object createdObject, 
		java.util.Map context
	) 
	{
		Boolean needsColumn = (Boolean)context.get("NeedsSemiColumn");
		if (needsColumn == null || needsColumn.booleanValue()) 
		{
			PrintWriter outputForClass = (PrintWriter)context.get("OutputForClass");
			outputForClass.println(';');
		}
		context.put("NeedsSemiColumn", Boolean.TRUE);		
	}

}

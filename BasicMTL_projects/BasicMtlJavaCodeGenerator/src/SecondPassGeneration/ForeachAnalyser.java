/*
 * $Header: /tmp/cvs2svn/cvsroot/BasicMTL_projects/BasicMtlJavaCodeGenerator/src/SecondPassGeneration/ForeachAnalyser.java,v 1.1 2004-04-21 18:20:29 edrezen Exp $
 * Created on 7 août 2003
 *
 */
package SecondPassGeneration;

import java.io.*;
import java.util.Map;

import org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.*;

/**
 * @author jpthibau
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class ForeachAnalyser extends TLLTopDownVisitor.ForeachAnalyser 
{
	VarDeclaration theVarDeclaration = null;
	String genSymbol;


	/** */
	public Object ForeachBefore (Foreach ASTnode,java.util.Map context)
	{	
		// we generate a symbol for the iterator
		genSymbol = CommonFunctions.generateNewSymbol();

		PrintWriter outputForClass = (PrintWriter)context.get("OutputForClass");
	
		theVarDeclaration = ASTnode.getVarDeclaration();
		
		outputForClass.print ("org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLIteratorInterface " + genSymbol + 
	" = (org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLIteratorInterface)CommonFunctions.toBMTLDataType(");

		return genSymbol;
	}


	/** */
	public void ForeachAfter (Object theForeach, Foreach ASTnode, java.util.Map context)
	{	
		String genSymbol=(String)theForeach;
		PrintWriter outputForClass = (PrintWriter)context.get("OutputForClass");
		
		outputForClass.println (genSymbol + ".BMTL_next();");		
		outputForClass.println ("}");
	}


	/** */
    public void ForeachVarDeclaration (
        Object theForeach,
        Object varDeclaration,
        Map context
   	) 
   	{
		PrintWriter outputForClass = (PrintWriter)context.get("OutputForClass");
		
		VarDeclaration varDec = this.theVarDeclaration;

		outputForClass.print (varDec.getMangle());
		outputForClass.print (" = ");
		outputForClass.print ("(");
		outputForClass.print (varDec.getType().getDeclarationName());
		outputForClass.print (")");
		outputForClass.print ("CommonFunctions.toBMTLDataType (" + genSymbol + ".BMTL_item())");
		outputForClass.println (";");
    }


	/** */
    public void ForeachCollection (
        Object theForeach,
        Object collection,
        Map context
	) 
	{
		PrintWriter outputForClass = (PrintWriter)context.get("OutputForClass");
		
		outputForClass.println (".invoke (null, \"getNewIterator\",	new Value[]{}, new String[]{ModelElement.OperationDiscriminant}	) );" );

		outputForClass.println ("while (" + genSymbol + ".BMTL_isOn().getTheBoolean())");
		outputForClass.println ("{");
    }
	
	
	/** */
	public void ForeachBodyInstruction (
		Object theForeach,
		Object instr,
		Map context
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

/*
 * Created on 1 août 2003
 * $Id: ForeachAnalyser.java,v 1.3 2004-06-14 10:03:46 edrezen Exp $
 * Authors : jpthibau
 * 
 * Copyright 2004 - INRIA - LGPL license
 */
package OperationCallChecker;

import java.util.Map;

import org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.*;
import org.irisa.triskell.MT.utils.MessagesHandler.MSGHandler;
import org.irisa.triskell.MT.visitors.Java.GenericVisitor.Visitable;
import org.irisa.triskell.MT.visitors.Java.GenericVisitor.Visitor;

/**
 * @author jpthibau
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class ForeachAnalyser extends TLLTopDownVisitor.ForeachAnalyser 
{
	// we need to extract OperationCall objects whose caller is a VarCall
	// we look up into caller and arguments
	public class VarCallExtractor implements Visitor
	{
		protected java.util.Collection operationCalls = new java.util.Vector ();
		
		/** */
		public void visit (Visitable node, Map context) 
		{
			if (node instanceof OperationCall)
			{
				OperationCall oc = (OperationCall)node;
				
				if (oc.getCaller()==null)
				{
					MSGHandler.debug (ForeachAnalyser.class,40, "null caller for an OperationCall");				
					return;	
				}
				
				if (oc.getCaller() instanceof VarCall)
				{
					operationCalls.add (node);					
				}
				else if (oc.getCaller() instanceof OperationCall)
				{
					// search into arguments
					for (int i=0; i<oc.cardArguments(); i++)
					{
						oc.getArguments(i).accept (this,context);
					}
					
					// search into caller
					oc.getCaller().accept (this, context);					
				}
				else
				{
					// other kinds of caller don't need any extra check (like StringLiteral for instance)				
				}
			}
		}
		
		/** */
		public java.util.Collection getOperationCalls ()
		{
			return this.operationCalls;
		}
	}


	/** */ 
	public void ForeachAfter (
		Foreach ASTnode, 
		Object createdObject, 
		Map context
	) 
	{
		for (int i=0; i<ASTnode.cardBody(); i++)
		{
			Instruction instr = (Instruction)ASTnode.getBody(i);
	
			// we retrieve all VarCall objects in the body instructions of the Foreach
			VarCallExtractor v = new VarCallExtractor();
			instr.accept (v, new java.util.Hashtable());
			
			// for VarCall objects that match the mute variable of the foreach,
			// we force the cast (like using oclAsType(...)
			java.util.Iterator it = v.getOperationCalls().iterator();
			while (it.hasNext())
			{
				OperationCall oc = (OperationCall) it.next(); 
				VarCall vc = (VarCall) oc.getCaller();
				if (vc.getRelatedDecl().equals (ASTnode.getVarDeclaration()))
				{
					oc.setOclAsType (vc.getRelatedDecl().getType());
				}
			}
		}
		
	}

}

/*
 * $Header: /tmp/cvs2svn/cvsroot/BasicMTL_projects/BasicMtlAstJava/src/ASTTopDownVisitor/AttributeAnalyser.java,v 1.1 2003-07-28 07:35:33 jpthibau Exp $
 * Created on 18 juil. 2003
 *
 */
package ASTTopDownVisitor;

import org.irisa.triskell.MT.visitors.Java.AnalysingVisitor.*;
import org.irisa.triskell.MT.visitors.Java.GenericVisitor.*;
import org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.*;

/**
 * @author jpthibau
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class AttributeAnalyser extends Analyser {

	public AttributeAnalyser()
	{	super(Attribute.class);
	}

	public void analyse(Visitable node,Visitor visitor,java.util.Map context)
	{	int i,limit;
		Attribute ASTnode=(Attribute) node;
		AttributeAction(ASTnode,context);
	}

	public void AttributeAction(Attribute ASTnode,java.util.Map context) {}

}

/*
 * $Header: /tmp/cvs2svn/cvsroot/BasicMTL_projects/BasicMtlTLLJava/src/TLLTopDownVisitor/TheLibraryClassAnalyser.java,v 1.1 2003-08-06 16:13:25 jpthibau Exp $
 * Created on 21 juil. 2003
 *
 */
package TLLTopDownVisitor;

import org.irisa.triskell.MT.visitors.Java.AnalysingVisitor.*;
import org.irisa.triskell.MT.visitors.Java.GenericVisitor.*;
import org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.*;

/**
 * @author jpthibau
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class TheLibraryClassAnalyser extends Analyser {

public TheLibraryClassAnalyser()
{	super(TheLibraryClass.class);
}

public void analyse(Visitable node,Visitor visitor,java.util.Map context)
{	int i,limit;
	TheLibraryClass ASTnode=(TheLibraryClass) node;
	Object theClass=this.TheLibraryClassBefore(ASTnode,context);
	limit=ASTnode.cardDefinedFeatures();
	for (i=0;i<limit;i++) {
		((Feature)ASTnode.getDefinedFeatures(i)).accept(visitor,context);
		this.TheLibraryClassFeature(theClass,context.get("Feature"),context);
	}
	this.TheLibraryClassAfter(theClass,ASTnode,context);
}

public Object TheLibraryClassBefore(TheLibraryClass ASTnode,java.util.Map context)
{	return null; }

public void TheLibraryClassFeature(Object theClass,Object attrib,java.util.Map context) {}

public void TheLibraryClassAfter(Object theClass,TheLibraryClass ASTnode,java.util.Map context) {}


}

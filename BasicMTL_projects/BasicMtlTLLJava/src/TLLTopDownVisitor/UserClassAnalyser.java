/*
 * $Header: /tmp/cvs2svn/cvsroot/BasicMTL_projects/BasicMtlTLLJava/src/TLLTopDownVisitor/UserClassAnalyser.java,v 1.1 2003-08-06 16:13:26 jpthibau Exp $
 * Created on 17 juil. 2003
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
public class UserClassAnalyser extends Analyser {

	public UserClassAnalyser()
	{	super(UserClass.class);
	}

	public void analyse(Visitable node,Visitor visitor,java.util.Map context)
	{	int i,limit;
		UserClass ASTnode=(UserClass) node;
		Object theClass=this.UserClassBefore(ASTnode,context);
		limit=ASTnode.cardDefinedFeatures();
		for (i=0;i<limit;i++) {
			((Feature)ASTnode.getDefinedFeatures(i)).accept(visitor,context);
			this.UserClassFeature(theClass,context.get("Feature"),context);
		}
		this.UserClassAfter(theClass,ASTnode,context);
	}

	public Object UserClassBefore(UserClass ASTnode,java.util.Map context)
	{	return null; }

	public void UserClassFeature(Object theClass,Object feature,java.util.Map context) {}

	public void UserClassAfter(Object theClass,UserClass ASTnode,java.util.Map context) {}


}

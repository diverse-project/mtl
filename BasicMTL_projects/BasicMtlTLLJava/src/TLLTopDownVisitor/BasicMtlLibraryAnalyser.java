/*
 * $Header: /tmp/cvs2svn/cvsroot/BasicMTL_projects/BasicMtlTLLJava/src/TLLTopDownVisitor/BasicMtlLibraryAnalyser.java,v 1.1 2003-08-06 16:13:27 jpthibau Exp $
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
public class BasicMtlLibraryAnalyser extends Analyser {

	public BasicMtlLibraryAnalyser()
	{	super(BasicMtlLibrary.class);
	}

	public void analyse(Visitable node,Visitor visitor,java.util.Map context)
	{	int i,limit;
		BasicMtlLibrary Lib=(BasicMtlLibrary) node;
		this.BasicMtlLibraryBefore(Lib,context);
		limit=Lib.cardUsedModels();
		for (i=0;i<limit;i++) {
			((ModelRef)Lib.getUsedModels(i)).accept(visitor,context);
			this.BasicMtlLibraryModelRef(context.get("ModelRef"),context);
		}
		((TheLibraryClass)Lib.getLibraryClass()).accept(visitor,context);
		this.BasicMtlLibraryTheLibraryClass(context.get("UserClass"),context);
		limit=Lib.cardClasses();
		for (i=0;i<limit;i++) {
				if (Lib.getLibraryClass()!=Lib.getClasses(i)) {
					((UserClass)Lib.getClasses(i)).accept(visitor,context);
					this.BasicMtlLibraryUserClass(context.get("UserClass"),context);
				}
		}
		this.BasicMtlLibraryAfter(Lib,context);
	}

	public void BasicMtlLibraryBefore(BasicMtlLibrary Lib,java.util.Map context) {}

	public void BasicMtlLibraryModelRef(Object model,java.util.Map context) {}

	public void BasicMtlLibraryTheLibraryClass(Object userClass,java.util.Map context) {}

	public void BasicMtlLibraryUserClass(Object userClass,java.util.Map context) {}

	public void BasicMtlLibraryAfter(BasicMtlLibrary Lib,java.util.Map context) {}

}

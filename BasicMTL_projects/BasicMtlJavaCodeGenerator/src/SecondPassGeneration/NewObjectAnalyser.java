/*
 * $Header: /tmp/cvs2svn/cvsroot/BasicMTL_projects/BasicMtlJavaCodeGenerator/src/SecondPassGeneration/NewObjectAnalyser.java,v 1.1 2003-08-08 15:41:11 jpthibau Exp $
 * Created on 8 août 2003
 *
 */
package SecondPassGeneration;

import java.io.*;
import org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.*;

import CodeGeneration.BMTLCompiler;

/**
 * @author jpthibau
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class NewObjectAnalyser extends TLLTopDownVisitor.NewObjectAnalyser {

	public Object NewObjectBefore(NewObject ASTnode,java.util.Map context)
	{	QualifiedName type=ASTnode.getTypeToCreate();
		PrintWriter outputForClass = (PrintWriter)context.get("OutputForClass");
		if (type.getIsModelType()) {
			if (type.getIsRepositoryModel()) {
				outputForClass.print(type.getLocalMangledName()+".getMetaClass(new String[]{");
				for (int i=1;i<type.size();i++) {
					outputForClass.print("\""+type.get(i)+"\"");
					if (i<type.size()-1) outputForClass.print(",");
				}
				outputForClass.print("}).instanciate(null,null);");
			}
		}
		if (type.getIsExternType()
			|| (type.getIsModelType() && (! type.getIsRepositoryModel())))
		 {
				outputForClass.print("try {\nthis."+type.getExternMangledName()+"getMetaClass(\"");
				for (int i=1;i<type.size();i++) {
					outputForClass.print(type.get(i));
					if (i<type.size()-1) outputForClass.print("::");
				}
				outputForClass.println("\").newInstance();");
				outputForClass.println("}\ncatch(IllegalAccessException e) {System.out.println(\"Illegal access pb\"); }");
				outputForClass.println("catch(InstantiationException e) {System.out.println(\"Instantiation pb\"); }");
		}
		if (type.getIsLocalType())
			outputForClass.println("new "+type.getLocalMangledName()+"()");
		if (ASTnode.cardArguments() > 0)
			BMTLCompiler.getLog().error("new should not have arguments as you can't define your own constructors...");
		return null; }

}

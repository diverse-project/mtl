/*
 * $Header: /tmp/cvs2svn/cvsroot/BasicMTL_projects/BasicMtlJavaCodeGenerator/src/SecondPassGeneration/NewObjectAnalyser.java,v 1.2 2003-08-14 21:31:40 ffondeme Exp $
 * Created on 8 ao�t 2003
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
		if (type.getIsModelType() && type.getIsRepositoryModel()) {
			outputForClass.print(type.getLocalMangledName()+".getMetaClass(new String[]{");
			for (int i=1;i<type.size();i++) {
				outputForClass.print("\""+type.get(i)+'"');
				if (i<type.size()-1) outputForClass.print(',');
			}
			outputForClass.print("}).instanciate(null,null);");
		}
		else /*if (type.getIsExternType()
			|| (type.getIsModelType() && (! type.getIsRepositoryModel())))*/
		 {
				outputForClass.print("((BMTLType)this.getLibrary().getMetaClass(new String [] {");
				for (int i=1;i<type.size();i++) {
					outputForClass.print('"');
					outputForClass.print(type.get(i));
					outputForClass.print('"');
					if (i<type.size()-1) outputForClass.print(", ");
				}
				outputForClass.print("})).instanciate()");
		}
		//@TODO the following is a good idea but does not work to instanciate a library
//		if (type.getIsLocalType())
//			outputForClass.println("new "+type.getLocalMangledName()+"(this.getLibrary())");
		if (ASTnode.cardArguments() > 0)
			BMTLCompiler.getLog().error("new should not have arguments as you can't define your own constructors...");
		return null; }

}
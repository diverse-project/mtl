/*
 * $Header: /tmp/cvs2svn/cvsroot/BasicMTL_projects/BasicMtlJavaCodeGenerator/src/SecondPassGeneration/NewObjectAnalyser.java,v 1.12 2004-10-18 16:01:25 jpthibau Exp $
 * Created on 8 août 2003
 *
 */
package SecondPassGeneration;

import java.io.*;

import org.apache.log4j.Logger;
import org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.*;
import org.irisa.triskell.MT.utils.MessagesHandler.MSGHandler;

import CodeGeneration.BMTLCompiler;

/**
 * @author jpthibau
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class NewObjectAnalyser extends TLLTopDownVisitor.NewObjectAnalyser {
	static final Logger log=MSGHandler.init();

	public Object NewObjectBefore(NewObject ASTnode,java.util.Map context)
	{	QualifiedName type=ASTnode.getTypeToCreate();
		PrintWriter outputForClass = (PrintWriter)context.get("OutputForClass");
		if (type.getIsModelType() && type.getIsRepositoryModel()) {
			outputForClass.print("((" + type.getDeclarationName() + ")CommonFunctions.toBMTLDataType(((org.irisa.triskell.MT.repository.API.Java.MetaClass)this.getLibrary().getMetaClass(new String[]{");
			for (int i=0;i<type.size();i++) {
				outputForClass.print("\""+type.get(i)+'"');
				if (i<type.size()-1) outputForClass.print(',');
			}
			outputForClass.print("})).instanciate(null,null)))");
		}
		else /*see next comment
				if (type.getIsExternType()
			|| (type.getIsModelType() && (! type.getIsRepositoryModel())))*/
		 {
				outputForClass.print("((" + type.getDeclarationName() + ")CommonFunctions.toBMTLDataType(((org.irisa.triskell.MT.DataTypes.Java.commands.InstanciableType)this.getLibrary().getMetaClass(new String [] {");
				for (int i=0;i<type.size();i++) {
					outputForClass.print('"');
					outputForClass.print(type.get(i));
					outputForClass.print('"');
					if (i<type.size()-1) outputForClass.print(", ");
				}
				outputForClass.print("})).instanciate()))");
		}
		//@TODO the following is a good idea but does not work to instanciate a library
//		if (type.getIsLocalType())
//			outputForClass.println("new "+type.getLocalMangledName()+"(this.getLibrary())");
		if (ASTnode.cardArguments() > 0)
			log.error("new should not have arguments as you can't define your own constructors...");
		CommonFunctions.generateCastAfter(outputForClass, ASTnode,ASTnode.getIsTrownExpression());
		return null; }

}

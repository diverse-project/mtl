/*
 * $Header: /tmp/cvs2svn/cvsroot/BasicMTL_projects/BasicMtlJavaCodeGenerator/src/SecondPassGeneration/BasicMtlLibraryAnalyser.java,v 1.2 2003-08-14 21:31:40 ffondeme Exp $
 * Created on 21 juil. 2003
 *
 */
package SecondPassGeneration;

import org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.*;

/**
 * @author jpthibau
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class BasicMtlLibraryAnalyser extends TLLTopDownVisitor.BasicMtlLibraryAnalyser {

public void BasicMtlLibraryBefore(BasicMtlLibrary Lib,java.util.Map context)
{	context.put("GeneratedLibName",Lib.getName());
	context.put("GeneratedLibMangledName",Lib.getMangle());
	context.put("GeneratedLibCompleteMangledName",Lib.getPackageName()+'.'+Lib.getMangle());
}

}

/*
 * $Header: /tmp/cvs2svn/cvsroot/BasicMTL_projects/org.inria.mtl.Eclipse3plugin/src/org/inria/mtl/views/TLLCollector/BasicMtlLibraryAnalyser.java,v 1.1 2004-08-26 12:40:35 sdzale Exp $
 * Created on 21 juil. 2003
 *
 */
package org.inria.mtl.views.TLLCollector;

import java.util.Vector;

import org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.*;

/**
 * @author jpthibau
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class BasicMtlLibraryAnalyser extends TLLTopDownVisitor.BasicMtlLibraryAnalyser {

public void BasicMtlLibraryBefore(BasicMtlLibrary Lib,java.util.Map context)
{	
	Vector result = (Vector)context.get("CollectedElts");
	
//	result.add("LibName : "+Lib.getName());
//	result.add("LibMangledName : "+Lib.getMangle());
//	result.add("LibCompleteMangledName : "+Lib.getPackageName()+'.'+Lib.getMangle());
//	result.add("PackageName : "+Lib.getPackageName());
}

}

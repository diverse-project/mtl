/*
 * $Header: /tmp/cvs2svn/cvsroot/BasicMTL_projects/BasicMtlAntlr2TLLJava/src/TLLBuilder/CommonFunctions.java,v 1.1 2003-08-06 16:18:46 jpthibau Exp $
 * Created on 23 juil. 2003
 *
 */
package TLLBuilder;

import org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.*;

/**
 * @author jpthibau
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class CommonFunctions {
	
	public static QualifiedName findOrAddType(java.util.Vector type,BasicMtlLibrary theCreatedLib)
	{	java.util.Vector allReferedTypes=theCreatedLib.getAllReferedTypes();
		QualifiedName found=null;
		int i=0;
		while ((found==null) && (i < allReferedTypes.size()))
		{	QualifiedName aReferedType=(QualifiedName)allReferedTypes.get(i);
			if (type.size()==aReferedType.size())
			{ 	int j=0;
				boolean match=true;
				while (match && (j<type.size()))
				{	match=((String)type.get(j)).equals((String)aReferedType.get(j));
					j++; } 
				if (match) found=aReferedType;
			}
			i++; }
		if (found==null) {
				found=new QualifiedName();
				for(i=0;i<type.size();i++) found.addElement(type.get(i));
				allReferedTypes.addElement(found);
				theCreatedLib.setAllReferedTypes((AllReferedTypes)allReferedTypes); }
		return found;
		}

}

/*
 * Created on 19 août 2003
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.signatures;

import org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.Operation;
import org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.QualifiedName;
import org.irisa.triskell.MT.utils.Java.AWK;
import org.irisa.triskell.MT.utils.Java.Mangler;

/**
 * @author ffondeme
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class GetReferenceSignature extends ComputedSignature {
	public static final String GetReferencePrefix = "getRef_";
	
	public static QualifiedName forceRealDeclaration(QualifiedName qn)  {
		QualifiedName ret = ComputedSignature.cloneQualifiedName(qn);
		if (ret.getIsExternType())
			ret.setDeclarationName(ret.getExternCompleteName());
		else if (ret.getIsLocalType())
			ret.setDeclarationName(ret.getExternLibCompleteName()+'.'+ret.getLocalMangledName());
		else if (ret.getIsRepositoryModel())
			ret.setDeclarationName("API");
		else if (ret.getIsModelType())
			ret.setDeclarationName(ret.getExternCompleteName());
		return ret;
	}
	
	public final QualifiedName referenceType;
	public int cardReferenceType () {
		return 1;
	}
	public QualifiedName getReferenceType () {
		return this.referenceType;
	}

	public GetReferenceSignature(QualifiedName referenceType) {
		super();
		this.referenceType = referenceType;
	}
	
	public String getPrefix () {
		return GetReferencePrefix;
	}

	public int cardArgsTypes() {
		return 0;
	}

	public QualifiedName getArgsTypes(int i) {
		throw new ArrayIndexOutOfBoundsException();
	}

	public String getOpMangle() {
		return this.getPrefix() + Mangler.mangle("BMTL_", AWK.mergeCollection(this.getReferenceType(), "_")/*(String)this.getReferenceType().get(this.getReferenceType().size()-1)*/);
	}

	public String getOpName() {
		return this.getPrefix() + this.getReferenceType().get(this.getReferenceType().size()-1);
	}

	public QualifiedName getReturnedType() {
		return forceRealDeclaration(this.getReferenceType());
	}

	public Operation getTheOperation() {
		return null;
	}

}

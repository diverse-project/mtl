/*
 * Created on 19 août 2003
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.signatures;

import org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.OpSignature;
import org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.Operation;
import org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.QualifiedName;

/**
 * This class represents a computed operation signature, eg for attribute accessor or reference operations.
 * It was putten outside Objecteering because this tools does not suppport the overriding of attributes accesor.
 */
public abstract class ComputedSignature extends OpSignature {
	public static QualifiedName cloneQualifiedName (QualifiedName qn) {
		QualifiedName ret = new QualifiedName();
		ret.addAll(qn);
		ret.setDeclarationName(qn.getDeclarationName());
		ret.setExternCompleteName(qn.getExternCompleteName());
		ret.setExternLibCompleteName(qn.getExternLibCompleteName());
		ret.setExternLibMangledName(qn.getExternLibMangledName());
		ret.setExternMangledName(qn.getExternMangledName());
		ret.setIsExternType(qn.getIsExternType());
		ret.setIsLocalType(qn.getIsLocalType());
		ret.setIsLocalType(qn.getIsLocalType());
		ret.setIsModelType(qn.getIsModelType());
		ret.setIsRepositoryModel(qn.getIsRepositoryModel());
		ret.setLocalMangledName(qn.getLocalMangledName());
		return ret;
	}

	public ComputedSignature() {
		super(null, null);
	}

	public void appendArgsTypes(QualifiedName value) {
		throw new IllegalStateException();
	}

	public abstract int cardArgsTypes();

	public int cardReturnedType() {
		return this.getReturnedType() == null ? 0 : 1;
	}

	public int cardTheOperation() {
		return this.getTheOperation() == null ? 0 : 1;
	}

	public void eraseArgsTypes(int i) {
		throw new IllegalStateException();
	}

	public void eraseArgsTypes(QualifiedName value) {
		throw new IllegalStateException();
	}

	public int getArgsCount() {
		return this.cardArgsTypes();
	}

	public abstract QualifiedName getArgsTypes(int i);

	public abstract String getOpMangle();

	public abstract String getOpName();

	public abstract QualifiedName getReturnedType();

	public abstract Operation getTheOperation();

	public void setArgsCount(int value) {
		throw new IllegalStateException();
	}

	public void setArgsTypes(int i, QualifiedName value) {
		throw new IllegalStateException();
	}

	public void setReturnedType(QualifiedName value) {
		throw new IllegalStateException();
	}

}

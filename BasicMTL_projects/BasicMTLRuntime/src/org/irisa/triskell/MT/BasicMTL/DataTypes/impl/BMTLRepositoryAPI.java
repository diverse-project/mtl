/*
 * Created on 26 oct. 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.irisa.triskell.MT.BasicMTL.DataTypes.impl;

import java.util.Vector;

import org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLBooleanInterface;
import org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOclTypeInterface;
import org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLRepositoryAPIInterface;
import org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface;
import org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface;
import org.irisa.triskell.MT.BasicMTL.TopTypes.BMTLType;
import org.irisa.triskell.MT.DataTypes.Java.Type;
import org.irisa.triskell.MT.DataTypes.Java.TypeValue;
import org.irisa.triskell.MT.DataTypes.Java.Value;
import org.irisa.triskell.MT.DataTypes.Java.ValueVisitor;
import org.irisa.triskell.MT.DataTypes.Java.commands.MultipleCommandException;
import org.irisa.triskell.MT.DataTypes.Java.commands.UnknownCommandException;
import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.TypeValueImpl;
import org.irisa.triskell.MT.repository.API.Java.API;
import org.irisa.triskell.MT.repository.API.Java.Element;
import org.irisa.triskell.MT.repository.API.Java.EventListener;
import org.irisa.triskell.MT.repository.API.Java.EventListenerFactory;
import org.irisa.triskell.MT.repository.API.Java.MetaAssociation;
import org.irisa.triskell.MT.repository.API.Java.MetaAssociationEnd;
import org.irisa.triskell.MT.repository.API.Java.MetaAttribute;
import org.irisa.triskell.MT.repository.API.Java.MetaClass;
import org.irisa.triskell.MT.repository.API.Java.MetaFeature;
import org.irisa.triskell.MT.repository.API.Java.MetaOperation;
import org.irisa.triskell.MT.repository.API.Java.ModelElement;
import org.irisa.triskell.MT.repository.API.Java.ModelRole;
import org.irisa.triskell.MT.repository.API.Java.UnknownElementException;

/**
 * @author jpthibau
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class BMTLRepositoryAPI
	extends BMTLOclAny
	implements BMTLRepositoryAPIInterface {

	/* @param delegate
	 */
	public BMTLRepositoryAPI(API delegate) {
		super(delegate);
	}
	/* (non-Javadoc)
	 * @see org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLRepositoryAPIInterface#getRepositoryAPIDelegate()
	 */
	public API getRepositoryAPIDelegate() {
		return (API)this.getDelegate();
	}

	/* (non-Javadoc)
	 * @see org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLRepositoryAPIInterface#BMTL_oclUid()
	 */
	public BMTLStringInterface BMTL_oclUid() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLRepositoryAPIInterface#BMTL_getType()
	 */
	public BMTLOclTypeInterface BMTL_getType() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.irisa.triskell.MT.repository.API.Java.API#getMetaClass(java.lang.String[])
	 */
	public MetaClass getMetaClass(String[] qualifiedName)
			throws UnknownElementException {
		return this.getRepositoryAPIDelegate().getMetaClass(qualifiedName);
	}

	/* (non-Javadoc)
	 * @see org.irisa.triskell.MT.repository.API.Java.API#getMetaAssociation(java.lang.String[])
	 */
	public MetaAssociation getMetaAssociation(String[] qualifiedName)
			throws UnknownElementException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.irisa.triskell.MT.repository.API.Java.API#getMetaAssociationWithAssociationEnds(org.irisa.triskell.MT.repository.API.Java.MetaAssociationEnd[])
	 */
	public MetaAssociation getMetaAssociationWithAssociationEnds(
			MetaAssociationEnd[] associationEnds)
			throws UnknownElementException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.irisa.triskell.MT.repository.API.Java.API#getMetaFeature(java.lang.String, org.irisa.triskell.MT.repository.API.Java.MetaClass)
	 */
	public MetaFeature getMetaFeature(String name, MetaClass scope) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.irisa.triskell.MT.repository.API.Java.API#getMetaAttribute(java.lang.String, org.irisa.triskell.MT.repository.API.Java.MetaClass)
	 */
	public MetaAttribute getMetaAttribute(String name, MetaClass scope) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.irisa.triskell.MT.repository.API.Java.API#getMetaOperation(java.lang.String, org.irisa.triskell.MT.repository.API.Java.MetaClass)
	 */
	public MetaOperation getMetaOperation(String name, MetaClass scope) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.irisa.triskell.MT.repository.API.Java.API#getMetaAssociationEnd(java.lang.String, org.irisa.triskell.MT.repository.API.Java.MetaClass, org.irisa.triskell.MT.repository.API.Java.MetaClass)
	 */
	public MetaAssociationEnd getMetaAssociationEnd(String role,
			MetaClass type, MetaClass scope) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.irisa.triskell.MT.repository.API.Java.API#getRole(org.irisa.triskell.MT.repository.API.Java.ModelElement, org.irisa.triskell.MT.repository.API.Java.MetaAssociationEnd)
	 */
	public ModelRole getRole(ModelElement element,
			MetaAssociationEnd associationEnd) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.irisa.triskell.MT.repository.API.Java.API#startup(org.irisa.triskell.MT.DataTypes.Java.Value[])
	 */
	public void startup(Value[] arguments) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.irisa.triskell.MT.repository.API.Java.API#shutdown(org.irisa.triskell.MT.DataTypes.Java.Value[])
	 */
	public void shutdown(Value[] arguments) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.irisa.triskell.MT.repository.API.Java.API#addListenerToElement(org.irisa.triskell.MT.repository.API.Java.Element, org.irisa.triskell.MT.repository.API.Java.EventListener)
	 */
	public void addListenerToElement(Element element, EventListener listener) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.irisa.triskell.MT.repository.API.Java.API#removeListenerToElement(org.irisa.triskell.MT.repository.API.Java.Element, org.irisa.triskell.MT.repository.API.Java.EventListener)
	 */
	public void removeListenerToElement(Element element, EventListener listener) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.irisa.triskell.MT.repository.API.Java.API#getEventListenerFactory()
	 */
	public EventListenerFactory getEventListenerFactory() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOclAnyInterface#getOclAnyDelegate()
	 */
/*	public Value getOclAnyDelegate() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOclAnyInterface#BMTL__3d(org.irisa.triskell.MT.DataTypes.Java.Value)
	 */
	public BMTLBooleanInterface BMTL__3d(Value rhs) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOclAnyInterface#BMTL__3c_3e(org.irisa.triskell.MT.DataTypes.Java.Value)
	 */
	public BMTLBooleanInterface BMTL__3c_3e(Value rhs) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOclAnyInterface#BMTL_oclIsUndefined()
	 */
	public BMTLBooleanInterface BMTL_oclIsUndefined() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOclAnyInterface#BMTL_oclIsTypeOf(org.irisa.triskell.MT.DataTypes.Java.TypeValue)
	 */
	public BMTLBooleanInterface BMTL_oclIsTypeOf(TypeValue type) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOclAnyInterface#BMTL_oclIsKindOf(org.irisa.triskell.MT.DataTypes.Java.TypeValue)
	 */
	public BMTLBooleanInterface BMTL_oclIsKindOf(TypeValue type) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOclAnyInterface#BMTL_toOut()
	 */
	public BMTLVoidInterface BMTL_toOut() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOclAnyInterface#BMTL_toErr()
	 */
	public BMTLVoidInterface BMTL_toErr() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.irisa.triskell.MT.DataTypes.Java.RepositoryAPIValue#getTheRepositoryAPI()
	 */
	public String getTheRepositoryAPI() {
		return this.getRepositoryAPIDelegate().getTheRepositoryAPI();
	}

	/* (non-Javadoc)
	 * @see org.irisa.triskell.MT.DataTypes.Java.Value#isUndefined()
	 */
	public boolean isUndefined() {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see org.irisa.triskell.MT.DataTypes.Java.Value#getErrorMessage()
	 */
	public String getErrorMessage() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.irisa.triskell.MT.DataTypes.Java.Value#equals(org.irisa.triskell.MT.DataTypes.Java.Value)
	 */
	public boolean equals(Value rhs) {
		// TODO Auto-generated method stub
		return false;
	}

	private String[] toQualifiedName(String s) {
		Vector v=new java.util.Vector();
		int index=s.indexOf("::");
		while (index>0) {
			v.add(s.substring(0,index));
			s=s.substring(index+2);
			index=s.indexOf("::");
		}
		v.add(s);
		String [] result=new String[v.size()];
		v.toArray(result);
		return result;
	}
	/* (non-Javadoc)
	 * @see org.irisa.triskell.MT.DataTypes.Java.Value#invoke(java.lang.String[], java.lang.String, org.irisa.triskell.MT.DataTypes.Java.Value[], java.lang.String[])
	 */
	public Value invoke(String[] scopeQualifiedName, String name,
			Value[] arguments, String[] discriminants)
			throws UnknownCommandException, MultipleCommandException {
		if (name.equals("getTypeFromName")) {
			BMTLString type=(BMTLString)arguments[0];
			String [] qn=this.toQualifiedName(type.getTheString());
			MetaClass mc=null;
			try {
			mc=this.getRepositoryAPIDelegate().getMetaClass(qn);
			} catch (UnknownElementException e) { System.err.println("Unknown element "+qn); }
			return new TypeValueImpl(false, null, mc);
		}
		if (name.equals("instanciate")) {
			TypeValue type=(TypeValue)arguments[0];
			MetaClass mc=(MetaClass)type.getTheType();
			Value instance=null;
			try {
			instance=CommonFunctions.toBMTLDataType(mc.instanciate(null,null));
			} catch(Exception e) {}
			return instance;
		}
		return this.getRepositoryAPIDelegate().invoke(scopeQualifiedName,name,arguments,discriminants);
	}

	/* (non-Javadoc)
	 * @see org.irisa.triskell.MT.DataTypes.Java.Value#accept(org.irisa.triskell.MT.DataTypes.Java.ValueVisitor)
	 */
	public void accept(ValueVisitor visitor) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.irisa.triskell.MT.DataTypes.Java.Value#getType()
	 */
	public Type getType() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLDataTypeInterface#getDelegate()
	 */
//	public Value getDelegate() {
//		return this.d;
//	}

}

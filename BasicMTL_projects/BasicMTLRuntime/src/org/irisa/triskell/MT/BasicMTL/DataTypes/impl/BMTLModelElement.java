/*
 * Created on 20 août 2003
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package org.irisa.triskell.MT.BasicMTL.DataTypes.impl;

import org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOclTypeInterface;
import org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface;
import org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface;
import org.irisa.triskell.MT.DataTypes.Java.Value;
import org.irisa.triskell.MT.DataTypes.Java.commands.ModelElement.ModelElement_delete;
import org.irisa.triskell.MT.DataTypes.Java.commands.ModelElement.ModelElement_getType;
import org.irisa.triskell.MT.DataTypes.Java.commands.ModelElement.ModelElement_oclUid;
import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.TypeValueImpl;
import org.irisa.triskell.MT.repository.API.Java.API;
import org.irisa.triskell.MT.repository.API.Java.CommonException;
import org.irisa.triskell.MT.repository.API.Java.EventListener;
import org.irisa.triskell.MT.repository.API.Java.IllegalAccessException;
import org.irisa.triskell.MT.repository.API.Java.IsQueryException;
import org.irisa.triskell.MT.repository.API.Java.MetaAttribute;
import org.irisa.triskell.MT.repository.API.Java.MetaClass;
import org.irisa.triskell.MT.repository.API.Java.MetaFeature;
import org.irisa.triskell.MT.repository.API.Java.MetaOperation;
import org.irisa.triskell.MT.repository.API.Java.ModelElement;
import org.irisa.triskell.MT.repository.API.Java.UnknownElementException;

/**
 * @author ffondeme
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class BMTLModelElement
	extends BMTLOclAny
	implements org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLModelElementInterface {

	/**
	 * @param delegate
	 */
	public BMTLModelElement(ModelElement delegate) {
		super(delegate);
	}

	public ModelElement getModelElementDelegate() {
		return (ModelElement)this.getDelegate();
	}
	
	public BMTLVoidInterface BMTL_delete() {
		ModelElement_delete.TheInstance.invoke(this.getDelegate(), null);
		return BMTLVoid.TheInstance;
	}

	public BMTLStringInterface BMTL_oclUid() {
		return new BMTLString(ModelElement_oclUid.TheInstance.invoke(this.getDelegate(), null));
	}

	public BMTLOclTypeInterface BMTL_getType () {
		return new BMTLOclType ((TypeValueImpl) ModelElement_getType.TheInstance.invoke(this.getDelegate(), null));
	}

	public boolean isMetaObject() {
		return this.getModelElementDelegate().isMetaObject();
	}

	public void delete() throws UnknownElementException, CommonException {
		this.getModelElementDelegate().delete();
	}

	public boolean isTypeOf(MetaClass classifier) {
		return this.getModelElementDelegate().isTypeOf(classifier);
	}

	public boolean isKindOf(MetaClass classifier) {
		return this.getModelElementDelegate().isKindOf(classifier);
	}

	public Value getFeatureValue(
		ModelElement contextualElement,
		MetaFeature feature,
		Value[] arguments)
		throws UnknownElementException, IllegalAccessException, CommonException {
		return this.getModelElementDelegate().getFeatureValue(contextualElement, feature, arguments);
	}
	
	public void setAttributeValue(
		ModelElement contextualElement,
		MetaAttribute argument,
		Value value)
		throws UnknownElementException, CommonException, IllegalAccessException {
		this.getModelElementDelegate().setAttributeValue(contextualElement, argument, value);

	}

	public Value invokeQueryOperation(
		ModelElement contextualElement,
		MetaOperation feature,
		Value[] arguments)
		throws
			UnknownElementException,
			IllegalAccessException,
			CommonException,
			IsQueryException {
		return this.getModelElementDelegate().invokeQueryOperation(contextualElement, feature, arguments);
	}

	public String getUniqId() {
		return this.getModelElementDelegate().getUniqId();
	}

	public API getAPI() {
		return this.getModelElementDelegate().getAPI();
	}

	public String getTheModelElement() {
		return this.getModelElementDelegate().getTheModelElement();
	}

	public void deleteTheModelElement() throws Exception {
		this.getModelElementDelegate().deleteTheModelElement();
	}

	/** */
	public void addListener (EventListener listener) 
	{
		getAPI().addListenerToElement (this.getModelElementDelegate(),listener);
	}

	/** */
	public void removeListener (EventListener listener) 
	{
		getAPI().removeListenerToElement (this.getModelElementDelegate(),listener);
	}
	
}

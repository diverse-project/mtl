/* $Id: EMFFeatured.java,v 1.4 2004-06-23 15:14:36 dvojtise Exp $
 * Authors : 
 * 
 * Copyright 2003 - INRIA - LGPL license
 */
package org.inria.EMFDriver;

import org.irisa.triskell.MT.DataTypes.Java.Value;
import org.irisa.triskell.MT.DataTypes.Java.commands.CommandGroup;
import org.irisa.triskell.MT.repository.API.Java.ModelElement;
import org.irisa.triskell.MT.DataTypes.Java.commands.OclAny.OclAnyType;
import org.irisa.triskell.MT.DataTypes.Java.commands.UnknownCommandException;
import org.irisa.triskell.MT.repository.API.Java.UnknownElementException;

// import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
// import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
// import org.eclipse.emf.ecore.ETypedElement;
// import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.impl.ESuperAdapter;
import org.eclipse.emf.edit.command.*;
import org.eclipse.emf.common.command.*;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;

import java.util.List;
import java.util.Arrays;

/**
 * @author jpthibau
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public abstract class EMFFeatured
	extends EMFElement
	implements org.irisa.triskell.MT.repository.API.Java.ModelElement
{
	protected final CommandParameter refFeatured;
	protected final Object refElementFeatured;
	public Object getRefFeatured () {
		return this.refFeatured;
	}
	public int cardRefFeatured () {
		if ( this.refFeatured == null ) return 0;
		else return 1;
	}

	private final boolean undefined;

	private final String error;


//for a metaclass
	public EMFFeatured(
		boolean undefined,
		String error,
		EMFAPI api,
		CommandParameter ref)
	{
		super(api, ref);
		this.undefined = undefined;
		this.error = error;
		this.refFeatured = ref;
		this.refElementFeatured = null;
	}

//for a modelElement
	public EMFFeatured(
		boolean undefined,
		String error,
		EMFAPI api,
		Object ref)
	{
		super(api, ref);
		this.undefined = undefined;
		this.error = error;
		this.refFeatured = null;
		this.refElementFeatured = ref;
	}

	/**
	  * @see org.irisa.triskell.MT.DataTypes.Java.Value#invoke(String, org.irisa.triskell.MT.DataTypes.Java.Value[], String[]) 
	  */
	public org.irisa.triskell.MT.DataTypes.Java.Value invoke(
		String [] scopeQualifiedName,
		String name,
		org.irisa.triskell.MT.DataTypes.Java.Value[] arguments,
		String[] discriminants)
		throws org.irisa.triskell.MT.DataTypes.Java.commands.UnknownCommandException, org.irisa.triskell.MT.DataTypes.Java.commands.MultipleCommandException
	{
		EMFMetaFeature f = null, g = null;
		EMFMetaClass c = null;
		List discs = Arrays.asList(discriminants == null ? new Object [0] : discriminants);
		boolean setAttribute = discs.contains(ModelElement.SetAttributeDiscriminant);
		boolean attribute = discs.contains(ModelElement.AttributeDiscriminant);
		boolean associationEnd = discs.contains(ModelElement.AssociationDiscriminant);
		boolean operation = discs.contains(ModelElement.OperationDiscriminant);
		boolean feature = ! (attribute || associationEnd || operation);
		if (setAttribute && !feature)
			throw new UnknownCommandException(this, name, arguments, discriminants, "Cannot both query the model and affect an attribute.");
		if (setAttribute) { 
			EStructuralFeature sf = ((EMFModelElement)this).type.getAttribute(name);
			Object val = this.getSpecificAPI().value2java(arguments[0],false,false);
			Command setcmd = SetCommand.create(this.api.editingDomain,((EMFModelElement)this).getRefObject(),sf,val);
			if (setcmd.canExecute())
				setcmd.execute();
			else throw new UnknownCommandException(this, name, arguments, discriminants, "One parameter required : the new attriute value.");
/*			if (arguments == null || arguments.length != 1)
				throw new UnknownCommandException(this, name, arguments, discriminants, "One parameter required : the new attriute value.");
			try {
				MetaAttribute att = this.getAPI().getMetaAttribute(name, scopeQualifiedName == null ? null : this.getAPI().getMetaClass(scopeQualifiedName));
				this.setAttributeValue(null, att, arguments[0]);	
				return VoidValueImpl.getTheInstance();
			} catch (Exception x) {
				UnknownCommandException thrown = new UnknownCommandException(this, name, arguments, discriminants, "Problems while affecting attribute: " + x.getMessage());
				thrown.initCause(x); 
				throw thrown;
			}*/
		} else {
			try {
				c = scopeQualifiedName == null ? null : (EMFMetaClass)this.getSpecificAPI().getMetaClass(scopeQualifiedName);
				if (feature)
					System.out.println("feature : Not yet implemented");
//					f = (JMIMetaFeature)this.getSpecificAPI().getMetaFeature(name, c);
				else {
					if (attribute) {
						EStructuralFeature sf = ((EMFModelElement)this).type.getAttribute(name);
						return this.getSpecificAPI().java2value(((EMFModelElement)this).getRefObject().eGet(sf),false,false,false);
					}
						
					if (associationEnd) {
						g = (EMFMetaFeature)this.getSpecificAPI().getMetaAssociationEnd(name, null, c);
						if (f == null)
							f = g;
						else
							f = new EMFMetaFeature(this.getSpecificAPI(), name, c, new EMFMetaFeature [] {f, g});
					}
					if (operation) {
						if (name.equals("delete")) {
							this.delete();
							return null; }
						if (name.equals("SubClasses")) {
							EObject elt = ((EMFModelElement)this).getRefObject();
							if (elt instanceof EClass) {
							ESuperAdapter sa = ESuperAdapter.getESuperAdapter((EClass)elt);
							if (sa != null) return this.getSpecificAPI().java2value(sa.getSubclasses(),false,false,false);
							}
							//by default return an empty collection for subclasses() call
							return this.getSpecificAPI().java2value(new BasicEList(),false,false,false);  
						}
						if (name.equals("TagValues")) {
							EObject elt = ((EMFModelElement)this).getRefObject();
							if (elt instanceof EModelElement) {
								EList annotations = ((EModelElement)elt).getEAnnotations();
								if (annotations != null && annotations.size() > 0)
									return this.getSpecificAPI().java2value(annotations,false,false,false);
							}
							//by default return an empty collection for subclasses() call
							return this.getSpecificAPI().java2value(new BasicEList(),false,false,false);  
						}						
						g = (EMFMetaFeature)this.getSpecificAPI().getMetaOperation(name, c);
						if (f == null)
							f = g;
						else
							f = new EMFMetaFeature(this.getSpecificAPI(), name, c, new EMFMetaFeature [] {f, g});
					}
				}
				return ((EMFMetaFeature)f).retrieveRef(null, this, arguments).execute();
//			} catch (JMIMetaFeature.VisibilityException x) {
//				throw new UnknownCommandException(this, name, arguments, discriminants, x.getMessage());
//			} catch (JMIMetaFeature.MultipleDeclarationException x) {
//				throw new UnknownCommandException(this, name, arguments, discriminants, x.getMessage());
//			} catch (JMIMetaFeature.ScopeException x) {
//				throw new UnknownCommandException(this, name, arguments, discriminants, x.getMessage());
			} catch (Exception x) {
				if ((x instanceof UnknownElementException) && (scopeQualifiedName != null) && (c == null) && (!Arrays.equals(OclAnyType.TheInstance.getQualifiedName(), scopeQualifiedName)))
					throw new UnknownCommandException(this, name, arguments, discriminants, x.getMessage());
				//Uncomment the following would make the driver able to answer the operation message get_<attribute name> : <attribute type> and set_<attribute name> (<attribute type) wich are the accessor of an attribute
	    		
				return this.getBaseCommandGroup().invoke(scopeQualifiedName, this, name, arguments, discriminants);
			}
		}
		return null;
	}
    
	protected abstract CommandGroup getBaseCommandGroup ();

	/**
	  * @see org.irisa.triskell.MT.repository.API.Java.ModelElement#getFeatureValue(org.irisa.triskell.MT.repository.API.Java.ModelElement, org.irisa.triskell.MT.repository.API.Java.MetaFeature, org.irisa.triskell.MT.DataTypes.Java.Value[]) 
	  */
	public org.irisa.triskell.MT.DataTypes.Java.Value getFeatureValue(
		org.irisa.triskell.MT.repository.API.Java.ModelElement contextualElement,
		org.irisa.triskell.MT.repository.API.Java.MetaFeature feature,
		org.irisa.triskell.MT.DataTypes.Java.Value[] arguments)
		throws org.irisa.triskell.MT.repository.API.Java.UnknownElementException, org.irisa.triskell.MT.repository.API.Java.IllegalAccessException, org.irisa.triskell.MT.repository.API.Java.CommonException
	{
/*	try {
			return ((JMIMetaFeature)feature).retreiveRef((JMIFeatured)contextualElement, this, arguments).execute();
		} catch (JMIMetaFeature.ElementNotFoundException x) {
			throw new org.irisa.triskell.MT.repository.API.Java.UnknownElementException(feature);
		} catch (JMIMetaFeature.MultipleDeclarationException x) {
			throw new CommonException(feature.toString() + " is ambiguous for " + this.toString() + '.');
		} catch (JMIMetaFeature.ScopeException x) {
			throw new CommonException(feature.toString() + " is not accessible in a classifier scope.");
		} catch (JMIMetaFeature.VisibilityException x) {
			throw new IllegalAccessException(contextualElement, feature);
		} catch (InvalidObjectException x) {
			String msg = "Cannot operate on deleted object.";
			String xmsg = x.getMessage();
			if (xmsg != null && xmsg.length() > 0)
				msg += " (" + xmsg + ')';
			throw new CommonException(msg);
		} catch (Exception x) {
			throw new CommonException(x.getMessage() + " - " + x.getClass().getName());
		}*/
		return null;
	}

	public org.irisa.triskell.MT.DataTypes.Java.Value invokeQueryOperation(
		org.irisa.triskell.MT.repository.API.Java.ModelElement contextualElement,
		org.irisa.triskell.MT.repository.API.Java.MetaOperation feature,
		org.irisa.triskell.MT.DataTypes.Java.Value[] arguments)
		throws org.irisa.triskell.MT.repository.API.Java.UnknownElementException, org.irisa.triskell.MT.repository.API.Java.IllegalAccessException, org.irisa.triskell.MT.repository.API.Java.CommonException, org.irisa.triskell.MT.repository.API.Java.IsQueryException
	{
/*		try {
			ExecutableJMIOperation op = (ExecutableJMIOperation)((JMIMetaFeature)feature).retreiveRef((JMIFeatured)contextualElement, this, arguments);
			if (op.operation.isQuery())
				return op.execute();
			else
				throw new IsQueryException(feature);
		} catch (JMIMetaFeature.ElementNotFoundException x) {
			throw new org.irisa.triskell.MT.repository.API.Java.UnknownElementException(feature);
		} catch (JMIMetaFeature.MultipleDeclarationException x) {
			throw new CommonException(feature.toString() + " is ambiguous for " + this.toString() + '.');
		} catch (JMIMetaFeature.ScopeException x) {
			throw new CommonException(feature.toString() + " is not accessible in a classifier scope.");
		} catch (JMIMetaFeature.VisibilityException x) {
			throw new IllegalAccessException(contextualElement, feature);
		} catch (InvalidObjectException x) {
			String msg = "Cannot operate on deleted object.";
			String xmsg = x.getMessage();
			if (xmsg != null && xmsg.length() > 0)
				msg += " (" + xmsg + ')';
			throw new CommonException(msg);
		} catch (Exception x) {
			throw new CommonException(x.getMessage() + " - " + x.getClass().getName());
		}*/
		return null;
	}

	/**
	  * @see org.irisa.triskell.MT.repository.API.Java.ModelElement#setAttributeValue(org.irisa.triskell.MT.repository.API.Java.ModelElement, org.irisa.triskell.MT.repository.API.Java.MetaAttribute, org.irisa.triskell.MT.DataTypes.Java.Value) 
	  */
	public void setAttributeValue(
		org.irisa.triskell.MT.repository.API.Java.ModelElement contextualElement,
		org.irisa.triskell.MT.repository.API.Java.MetaAttribute argument,
		org.irisa.triskell.MT.DataTypes.Java.Value value)
		throws org.irisa.triskell.MT.repository.API.Java.UnknownElementException, org.irisa.triskell.MT.repository.API.Java.CommonException, org.irisa.triskell.MT.repository.API.Java.IllegalAccessException
	{
/*		try {
			((ExecutableJMIAttribute)((JMIMetaAttribute)argument).retreiveRef((JMIFeatured)contextualElement, this, null)).setValue(value);
		} catch (JMIMetaFeature.ElementNotFoundException x) {
			throw new org.irisa.triskell.MT.repository.API.Java.UnknownElementException(argument);
		} catch (JMIMetaFeature.MultipleDeclarationException x) {
			throw new CommonException(argument.toString() + " is ambiguous for " + this.toString() + '.');
		} catch (JMIMetaFeature.ScopeException x) {
			throw new CommonException(argument.toString() + " is not accessible in a classifier scope.");
		} catch (JMIMetaFeature.VisibilityException x) {
			throw new IllegalAccessException(contextualElement, argument);
		} catch (InvalidCallException x) {
			throw new CommonException("Check you are not attempting to modify a non-changeable resource.");
		} catch (Exception x) {
			throw new CommonException(x.getMessage() + " - " + x.getClass().getName());
		}*/
	}

	/**
	  * @see org.irisa.triskell.MT.DataTypes.Java.ModelElementValue#getTheModelElement() 
	  */
	public String getTheModelElement()
	{
		if (this.refFeatured != null) return (this.refFeatured.getEReference().getEReferenceType().getName());
		if (this.refElementFeatured !=null) return this.refElementFeatured.toString();
		return null;
	}

	/**
	  * @see org.irisa.triskell.MT.DataTypes.Java.Value#getErrorMessage() 
	  */
	public String getErrorMessage()
	{
		return error;
	}

	/**
	  * @see org.irisa.triskell.MT.DataTypes.Java.Value#isUndefined() 
	  */
	public boolean isUndefined()
	{
		return this.undefined;
	}

	public boolean equals(
		org.irisa.triskell.MT.DataTypes.Java.Value rhs)
	{
		return (this == rhs);
/*			 || ((rhs instanceof EMFFeatured) ? (this.refFeatured == ((EMFFeatured)rhs).refFeatured)
			 									 && (this.isUndefined() == ((EMFFeatured)rhs).isUndefined())
			 									 && (this.isUndefined()
			 									      || (this.getErrorMessage() == null
			 									          && ((EMFFeatured)rhs).getErrorMessage() == null)
			 									      || (this.getErrorMessage().equals(((EMFFeatured)rhs).getErrorMessage())))
			 								 : rhs != null && rhs.equals(this));*/
	}

	public boolean equals(
		java.lang.Object rhs)
	{
		return (rhs instanceof Value) && (this.equals((Value)rhs));
	}

	public void accept(
		org.irisa.triskell.MT.DataTypes.Java.ValueVisitor visitor)
	{
		visitor.visitModelElementValue(this);
	}

	public abstract EMFChildElement getRefClass();

	public String getUniqId()
	{
		return this.refFeatured.getEReference().getName();
	}

}

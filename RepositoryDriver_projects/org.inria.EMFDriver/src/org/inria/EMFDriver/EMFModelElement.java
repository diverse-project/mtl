/* $Id: EMFModelElement.java,v 1.4 2004-06-23 15:14:37 dvojtise Exp $
 * Authors : 
 * 
 * Copyright 2003 - INRIA - LGPL license
 */
package org.inria.EMFDriver;

import org.irisa.triskell.MT.DataTypes.Java.Type;
import org.irisa.triskell.MT.DataTypes.Java.commands.CommandGroup;
import org.irisa.triskell.MT.DataTypes.Java.commands.ModelElement.ModelElementCommandGroup;

// import org.eclipse.emf.ecore.EClassifier;
// import org.eclipse.emf.ecore.ETypedElement;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.command.*;
import org.eclipse.emf.common.command.*;
// import org.eclipse.emf.ecore.resource.Resource;

import org.eclipse.emf.ecore.*;
// import java.util.Collection;
// import java.util.ArrayList;


/**
 * @author jpthibau
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class EMFModelElement
	extends EMFFeatured
{
	private final EObject refObject;
	protected EObject getRefObject () {
		return this.refObject;
	}
	protected int cardRefObject () {
		if ( this.refObject == null ) return 0;
		else return 1;
	}

	protected EMFMetaClass type;


	public EMFModelElement(
		boolean undefined,
		String error,
		EMFAPI api,
		EObject ref)
	{
		super(undefined, error, api, ref);
		this.refObject = ref;
	}

	public EMFModelElement(
		boolean undefined,
		String error,
		EMFAPI api,
		EObject ref,
		EMFMetaClass type)
	{
		super(undefined, error, api, ref);
		this.refObject = ref;
		this.type = type;
	}
	/**
	  * @see org.irisa.triskell.MT.repository.API.Java.ModelElement#delete() 
	  */
	public void delete()
		throws org.irisa.triskell.MT.repository.API.Java.UnknownElementException, org.irisa.triskell.MT.repository.API.Java.CommonException
	{
		if (this.refObject != null) {
			EditingDomain ed = this.getSpecificAPI().editingDomain;
			EMFChildElement childDescr = this.type.getRefClass();
			Command deleteCmd = RemoveCommand.create(ed,childDescr.owner,childDescr.childDescriptor.getEReference(),this.refObject);
			if (deleteCmd.canExecute())
				deleteCmd.execute();
			else {
				EMFDriver.log.warn("Cannot delete the following model element "+this.refObject+" instance of metaclass"+this.type);
				EMFDriver.log.warn("Trying to remove it directly from the managed resource...");
				this.getSpecificAPI().resource.getContents().remove(this.refObject);
				} 
		} else
			throw new org.irisa.triskell.MT.repository.API.Java.UnknownElementException(this);

	}
	
	public void deleteTheModelElement () throws Exception {
		this.delete();
	}

	/**
	  * @see org.irisa.triskell.MT.repository.API.Java.ModelElement#isKindOf(org.irisa.triskell.MT.repository.API.Java.MetaClass) 
	  */
	public boolean isKindOf(
		org.irisa.triskell.MT.repository.API.Java.MetaClass classifier)
	{
//		return (classifier instanceof EMFMetaClass) && this.refObject.refIsInstanceOf(((EMFMetaClass)classifier).getRefClass().refMetaObject(), true);
System.out.println("TO IMPLEMENT EMFModelElement isKindOf");
return false;	}

	/**
	  * @see org.irisa.triskell.MT.repository.API.Java.ModelElement#isMetaObject() 
	  */
	public boolean isMetaObject()
	{
		return false;
	}

	/**
	  * @see org.irisa.triskell.MT.repository.API.Java.ModelElement#isTypeOf(org.irisa.triskell.MT.repository.API.Java.MetaClass) 
	  */
	public boolean isTypeOf(
		org.irisa.triskell.MT.repository.API.Java.MetaClass classifier)
	{
/*		return (classifier instanceof EMFMetaClass) && this.refObject.refIsInstanceOf(((EMFMetaClass)classifier).getRefClass().refMetaObject(), false);*/
	System.out.println("TO IMPLEMENT : EMFModelElement isTypeOf(MetaClass)");
	return false;
	}

	public String toString()
	{
		return "model element #" + this.getTheModelElement() + " : " + this.getType().toString();
	}

	public Type getType()
	{
		if (this.type == null) {
			System.out.println("TO IMPLEMENT : EMFModelElement getType()");
//			this.type = (EMFMetaClass)this.getSpecificAPI().getMetaClass(((ETypedElement)this.refObject).getEType());
		}
		return this.type;
	}

	public EMFChildElement getRefClass()
	{
System.out.println("TO IMPLEMENT : EMFModelElement getRefClass(");
//find CommandParameter related to type of the element
//		return ((ETypedElement)this.getRefObject()).getEType();
return null;
	}

	protected CommandGroup getBaseCommandGroup() {
		return ModelElementCommandGroup.TheInstance;
	}


}

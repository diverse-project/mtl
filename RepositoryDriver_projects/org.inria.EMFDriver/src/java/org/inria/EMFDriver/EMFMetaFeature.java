/* $Id: EMFMetaFeature.java,v 1.2 2004-10-27 14:52:58 dvojtise Exp $
 * Authors : 
 * 
 * Copyright 2003 - INRIA - LGPL license
 */
package org.inria.EMFDriver;

import java.util.List;

// import org.irisa.triskell.MT.repository.API.Java.ModelElement;

/**
 * @author jpthibau
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class EMFMetaFeature
	extends EMFElement
	implements org.irisa.triskell.MT.repository.API.Java.MetaFeature
{
	private final EMFMetaClass scope;

	private final String name;

	private List delegates;
	protected EMFMetaFeature getDelegates (int i) {
		return (EMFMetaFeature)this.delegates.get(i);
	}
	protected int cardDelegates () {
		return this.delegates.size();
	}

	public static class ElementNotFoundException 
		extends java.lang.Exception
	{
	}

/*	public static class VisibilityException 
		extends java.lang.Exception
	{
		public VisibilityException (ModelElement contextualElement) {
			this(contextualElement, null);
		}
		public VisibilityException (ModelElement contextualElement, VisibilityKind foundVisibility) {
			super((contextualElement == null ? "Any element" : ("The contextual element " + contextualElement.getTheModelElement() + " of type " + contextualElement.getType().getQualifiedName())) + " has no access to this resource." + (foundVisibility == null ? "" : (" Found visibility is " + foundVisibility.toString() + '.')));
		}
	}*/

	public static class MultipleDeclarationException 
		extends java.lang.Exception
	{
    	
		public MultipleDeclarationException ()  {
			super("Different possibilities found for resource; be more precise...");
		}
	}

	public static class ScopeException 
		extends java.lang.Exception
	{
		public ScopeException () {
			super("Cannot access an instance-level resource a static way.");
		}
	}


	protected EMFMetaFeature(
		EMFAPI api,
		String name,
		EMFMetaClass scope,
		EMFMetaFeature[] delegates)
	{
		super(api, null);
		this.name = name;
		this.scope = scope;
		this.delegates = java.util.Arrays.asList(delegates == null ? new EMFMetaFeature[0] : delegates);
	}

	public EMFMetaFeature(
		EMFAPI api,
		String name,
		EMFMetaClass scope)
	{
		this(api, name, scope, new EMFMetaFeature [] {/*new MetaAttribute(api, name, scope), new EMFMetaAssociationEnd(api, name, scope, null), new EMFMetaOperation(api, name, scope)*/});
	}

	public org.irisa.triskell.MT.repository.API.Java.MetaClass getScope()
	{
		return this.scope;
	}

	public String getName()
	{
		return this.name;
	}

	public String toString()
	{
		return this.getKind() + ' ' + this.getName() + (this.getScope() == null ? "" : (" scoped " + this.getScope().toString()));
	}

	public org.inria.EMFDriver.ExecutableFeature retrieveRef(
		EMFFeatured contextualElement,
		EMFFeatured self,
		org.irisa.triskell.MT.DataTypes.Java.Value[] arguments)
		throws ElementNotFoundException, MultipleDeclarationException, ScopeException
	{
/*		java.util.Iterator it;
		java.util.Set levelSuperTypes = new java.util.HashSet();
		if (this.getScope() == null)
			levelSuperTypes.add(self.getRefClass().refMetaObject());
		else
			levelSuperTypes.add(((JMIMetaClass)this.getScope()).getRefClass().refMetaObject());
		java.util.Set alreadyExplored = new java.util.HashSet();
		javax.jmi.model.Namespace explored;
		javax.jmi.model.ModelElement me;
		ExecutableFeature ret = null, tmp;
		while (!levelSuperTypes.isEmpty()) {
			it = levelSuperTypes.iterator();
			while (it.hasNext()) {
				explored = (javax.jmi.model.Namespace)it.next();
				tmp = this.retrieveRef(contextualElement, self, arguments, explored);
				if (tmp != null) {
					if (ret == null)
						ret = tmp;
					else
						throw new MultipleDeclarationException();
				}
				alreadyExplored.add(explored);
			}
			if (ret != null)
				return ret;
			
			it = levelSuperTypes.iterator();
			levelSuperTypes = new java.util.HashSet();
			while (it.hasNext()) {
				me = (javax.jmi.model.ModelElement)it.next();
				if (me instanceof javax.jmi.model.GeneralizableElement)
					levelSuperTypes.addAll(((javax.jmi.model.GeneralizableElement)me).getSupertypes());
			}
			levelSuperTypes.removeAll(alreadyExplored);
		}	
		throw new ElementNotFoundException();*/
		return null;
	}

	public static Object[] getParameters(
		Object operation)
	{
/*	java.util.List parameters = new java.util.ArrayList(operation.getContents());
		java.util.Iterator parametersIt = parameters.iterator();
		Object o;
		while (parametersIt.hasNext()) {
			o = parametersIt.next();
			if (!(o instanceof Parameter))
				parametersIt.remove();
		}

		return (javax.jmi.model.Parameter[])parameters.toArray(new javax.jmi.model.Parameter[parameters.size()]);*/
		return null;
	}

	public static Object[] getParameters(
		Object operation,
		String[] acceptedDirections)
	{
/*		java.util.List directions = java.util.Arrays.asList(acceptedDirections);
		java.util.List parameters = new java.util.ArrayList(java.util.Arrays.asList(getParameters(operation)));
		java.util.Iterator parametersIt = parameters.iterator();
		javax.jmi.model.Parameter parameter;
		while (parametersIt.hasNext()) {
			parameter = (javax.jmi.model.Parameter)parametersIt.next();
			if (! directions.contains(parameter.getDirection().toString()))
				parametersIt.remove();
		}
		return (javax.jmi.model.Parameter[])parameters.toArray(new javax.jmi.model.Parameter[parameters.size()]);*/
		return null;
	}

	public String getKind()
	{
		return "feature";
	}


	protected boolean checkName(
		Object element)
	{
		String elementName = "pipo";// element.getName();
		return this.getName() == null || this.getName().length() == 0 || elementName == null || elementName.length() == 0 || this.getName().equals(elementName);
	}



	protected boolean checkArguments(
		Object element,
		org.irisa.triskell.MT.DataTypes.Java.Value[] arguments)
	{
		return arguments == null || arguments.length == 0;
	}


	protected boolean checkKind(
		Object element)
	{
		return true;
	}


}

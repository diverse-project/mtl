/*
 * $Id: JMIMetaFeature.java,v 1.2 2004-07-30 13:20:13 ffondeme Exp $
 * Authors : ffondeme dvojtise
 */
package org.irisa.triskell.MT.repository.genericJMIDriver;

import java.util.List;

import javax.jmi.model.Parameter;
import javax.jmi.model.VisibilityKind;

import org.irisa.triskell.MT.repository.API.Java.ModelElement;

/**
 * Generic implementation of the repository API (org.irisa.triskell.MT.repository.API.Java.API)
 * This serve as the base for all Driver that uses JMI to connect to the repository 
 */
public class JMIMetaFeature 
    extends org.irisa.triskell.MT.repository.genericJMIDriver.JMIElement
    implements org.irisa.triskell.MT.repository.API.Java.MetaFeature
{
    private final org.irisa.triskell.MT.repository.genericJMIDriver.JMIMetaClass scope;

    private final String name;

    private List delegates;
    protected org.irisa.triskell.MT.repository.genericJMIDriver.JMIMetaFeature getDelegates (int i) {
        return (org.irisa.triskell.MT.repository.genericJMIDriver.JMIMetaFeature)this.delegates.get(i);
    }
    protected int cardDelegates () {
        return this.delegates.size();
    }

    public static class ElementNotFoundException 
        extends java.lang.Exception
    {
    }

    public static class VisibilityException 
        extends java.lang.Exception
    {
    	public VisibilityException (ModelElement contextualElement) {
    		this(contextualElement, null);
    	}
    	public VisibilityException (ModelElement contextualElement, VisibilityKind foundVisibility) {
    		super((contextualElement == null ? "Any element" : ("The contextual element " + contextualElement.getTheModelElement() + " of type " + contextualElement.getType().getQualifiedName())) + " has no access to this resource." + (foundVisibility == null ? "" : (" Found visibility is " + foundVisibility.toString() + '.')));
    	}
    }

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


    protected JMIMetaFeature(
        org.irisa.triskell.MT.repository.genericJMIDriver.JMIAPI api,
        String name,
        org.irisa.triskell.MT.repository.genericJMIDriver.JMIMetaClass scope,
        org.irisa.triskell.MT.repository.genericJMIDriver.JMIMetaFeature[] delegates)
    {
        super(api, null);
		this.name = name;
    	this.scope = scope;
		this.delegates = java.util.Arrays.asList(delegates == null ? new JMIMetaFeature[0] : delegates);
    }

    public JMIMetaFeature(
        org.irisa.triskell.MT.repository.genericJMIDriver.JMIAPI api,
        String name,
        org.irisa.triskell.MT.repository.genericJMIDriver.JMIMetaClass scope)
    {
		this(api, name, scope, new JMIMetaFeature [] {new JMIMetaAttribute(api, name, scope), new JMIMetaAssociationEnd(api, name, scope, null), new JMIMetaOperation(api, name, scope)});
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

    public org.irisa.triskell.MT.repository.genericJMIDriver.ExecutableFeature retreiveRef(
        org.irisa.triskell.MT.repository.genericJMIDriver.JMIFeatured contextualElement,
        org.irisa.triskell.MT.repository.genericJMIDriver.JMIFeatured self,
        org.irisa.triskell.MT.DataTypes.Java.Value[] arguments)
        throws ElementNotFoundException, VisibilityException, MultipleDeclarationException, ScopeException
    {
		java.util.Iterator it;
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
				tmp = this.retreiveRef(contextualElement, self, arguments, explored);
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
		throw new ElementNotFoundException();
    }

    public static javax.jmi.model.Parameter[] getParameters(
        javax.jmi.model.Operation operation)
    {
	java.util.List parameters = new java.util.ArrayList(operation.getContents());
    	java.util.Iterator parametersIt = parameters.iterator();
    	Object o;
    	while (parametersIt.hasNext()) {
    		o = parametersIt.next();
    		if (!(o instanceof Parameter))
    			parametersIt.remove();
    	}

		return (javax.jmi.model.Parameter[])parameters.toArray(new javax.jmi.model.Parameter[parameters.size()]);
    }

    public static javax.jmi.model.Parameter[] getParameters(
        javax.jmi.model.Operation operation,
        String[] acceptedDirections)
    {
		java.util.List directions = java.util.Arrays.asList(acceptedDirections);
    	java.util.List parameters = new java.util.ArrayList(java.util.Arrays.asList(getParameters(operation)));
		java.util.Iterator parametersIt = parameters.iterator();
		javax.jmi.model.Parameter parameter;
		while (parametersIt.hasNext()) {
			parameter = (javax.jmi.model.Parameter)parametersIt.next();
			if (! directions.contains(parameter.getDirection().toString()))
				parametersIt.remove();
		}
		return (javax.jmi.model.Parameter[])parameters.toArray(new javax.jmi.model.Parameter[parameters.size()]);
    }

    public String getKind()
    {
		return "feature";
    }

    public org.irisa.triskell.MT.repository.genericJMIDriver.ExecutableFeature retreiveRef(
        org.irisa.triskell.MT.repository.genericJMIDriver.JMIFeatured contextualElement,
        org.irisa.triskell.MT.repository.genericJMIDriver.JMIFeatured self,
        org.irisa.triskell.MT.DataTypes.Java.Value[] arguments,
        javax.jmi.model.Namespace owner)
        throws ElementNotFoundException, VisibilityException, MultipleDeclarationException, ScopeException
    {
			ExecutableFeature ret = null, tmp;
			javax.jmi.model.ModelElement me;
    	if (this.cardDelegates() > 0) {
			for (int i = 0; i < this.cardDelegates(); ++i) {
				tmp = this.getDelegates(i).retreiveRef(contextualElement, self, arguments, owner);
				if (tmp != null) {
					if (ret == null)	
						ret = tmp;
					else
						throw new MultipleDeclarationException();
				}
			}
			return ret;
    	} else {
    		java.util.Iterator it = owner.getContents().iterator();
    		while (it.hasNext()) {
    			me = (javax.jmi.model.ModelElement)it.next();
    			if (this.checkAll(me, contextualElement, self, arguments)) {
    				if (ret != null)
						throw new MultipleDeclarationException();
					else
						ret = this.toExecutableFeature(self, arguments, me);
    			}
    		}
    	}
		return ret;
    }

    protected boolean checkName(
        javax.jmi.model.ModelElement element)
    {
		String elementName = element.getName();
		return this.getName() == null || this.getName().length() == 0 || elementName == null || elementName.length() == 0 || this.getName().equals(elementName);
    }

    protected boolean checkVisibility(
        javax.jmi.model.ModelElement element,
        org.irisa.triskell.MT.repository.genericJMIDriver.JMIFeatured context)
        throws VisibilityException
    {
		javax.jmi.model.ModelElement contextClassRef = context == null ? null : (javax.jmi.model.ModelElement)context.getRefClass().refMetaObject();
		if ((contextClassRef != null) && (! contextClassRef.isVisible(element)))
			throw new VisibilityException(context);
		if (contextClassRef == null && (element instanceof javax.jmi.model.Feature)) {
		 	VisibilityKind vis = ((javax.jmi.model.Feature)element).getVisibility();
		 	if (!vis.toString().equals("public_vis"))
				throw new VisibilityException(context, vis);
		}
		return true;
    }

    protected boolean checkScopeKind(
        javax.jmi.model.ModelElement element,
        org.irisa.triskell.MT.repository.genericJMIDriver.JMIFeatured self)
        throws ScopeException
    {
		if (self.isMetaObject() && (element instanceof javax.jmi.model.Feature) && (!(((javax.jmi.model.Feature)element).getScope().toString().equals("class_level"))))
				throw new ScopeException();
		return true;
    }

    protected boolean checkArguments(
        javax.jmi.model.ModelElement element,
        org.irisa.triskell.MT.DataTypes.Java.Value[] arguments)
    {
		return arguments == null || arguments.length == 0;
    }

    protected boolean checkAll(
        javax.jmi.model.ModelElement element,
        org.irisa.triskell.MT.repository.genericJMIDriver.JMIFeatured context,
        org.irisa.triskell.MT.repository.genericJMIDriver.JMIFeatured self,
        org.irisa.triskell.MT.DataTypes.Java.Value[] arguments)
        throws ElementNotFoundException, VisibilityException, MultipleDeclarationException, ScopeException
    {
		if (this.cardDelegates() != 0)
			throw new RuntimeException("Internal error.", new Exception("Only delegates can access this method."));
		return this.checkKind(element) && this.checkName(element) && this.checkVisibility(element, context) && this.checkScopeKind(element, self) && this.checkArguments(element, arguments);
    }

    protected boolean checkKind(
        javax.jmi.model.ModelElement element)
    {
		return true;
    }

    protected org.irisa.triskell.MT.repository.genericJMIDriver.ExecutableFeature toExecutableFeature(
        org.irisa.triskell.MT.repository.genericJMIDriver.JMIFeatured self,
        org.irisa.triskell.MT.DataTypes.Java.Value[] arguments,
        javax.jmi.model.ModelElement me)
    {
		throw new RuntimeException("Internal error.", new Exception("Only delegates can access this method."));
    }

	protected void cache() {
		this.getSpecificAPI().setCachedMetaElement(this);
	}
}
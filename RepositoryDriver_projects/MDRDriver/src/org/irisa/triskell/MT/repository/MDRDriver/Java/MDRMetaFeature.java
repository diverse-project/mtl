package org.irisa.triskell.MT.repository.MDRDriver.Java;

import java.util.*;

import javax.jmi.xmi.*;
import javax.jmi.reflect.*;
import org.irisa.triskell.MT.DataTypes.Java.*;
import org.netbeans.api.mdr.*;
import org.irisa.triskell.MT.DataTypes.Java.commands.Command;
import org.irisa.triskell.MT.DataTypes.Java.commands.CommandGroup;
import org.irisa.triskell.MT.DataTypes.Java.commands.CommandGroupImpl;
import org.irisa.triskell.MT.DataTypes.Java.commands.MultipleCommandException;
import org.irisa.triskell.MT.DataTypes.Java.commands.Type;
import org.irisa.triskell.MT.DataTypes.Java.commands.OclAny.OclAnyCommandGroup;
import org.irisa.triskell.MT.DataTypes.Java.commands.OclAny.OclAnyType;
import org.irisa.triskell.MT.DataTypes.Java.commands.modelElement.ModelElementType;
import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.*;
import javax.jmi.model.*;
import org.apache.log4j.*;
import org.irisa.triskell.MT.repository.API.Java.*;
import org.irisa.triskell.MT.repository.API.Java.utils.ModelElementIteratorToJavaIteratorConverter;
import org.irisa.triskell.MT.utils.Java.IteratingFinalList;

public class MDRMetaFeature 
    extends org.irisa.triskell.MT.repository.MDRDriver.Java.MDRElement
    implements org.irisa.triskell.MT.repository.API.Java.MetaFeature
{
    private final org.irisa.triskell.MT.repository.MDRDriver.Java.MDRMetaClass scope;

    private final String name;

    private List delegates;
    protected org.irisa.triskell.MT.repository.MDRDriver.Java.MDRMetaFeature getDelegates (int i) {
        return (org.irisa.triskell.MT.repository.MDRDriver.Java.MDRMetaFeature)this.delegates.get(i);
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
    }

    public static class MultipleDeclarationException 
        extends java.lang.Exception
    {
    }

    public static class ScopeException 
        extends java.lang.Exception
    {
    }


    protected MDRMetaFeature(
        org.irisa.triskell.MT.repository.MDRDriver.Java.MDRAPI api,
        String name,
        org.irisa.triskell.MT.repository.MDRDriver.Java.MDRMetaClass scope,
        org.irisa.triskell.MT.repository.MDRDriver.Java.MDRMetaFeature[] delegates)
    {
        super(api, null);
		this.name = name;
    	this.scope = scope;
		this.delegates = java.util.Arrays.asList(delegates == null ? new MDRMetaFeature[0] : delegates);
    }

    public MDRMetaFeature(
        org.irisa.triskell.MT.repository.MDRDriver.Java.MDRAPI api,
        String name,
        org.irisa.triskell.MT.repository.MDRDriver.Java.MDRMetaClass scope)
    {
		this(api, name, scope, new MDRMetaFeature [] {new MDRMetaAttribute(api, name, scope), new MDRMetaAssociationEnd(api, name, scope, null), new MDRMetaOperation(api, name, scope)});
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

    public org.irisa.triskell.MT.repository.MDRDriver.Java.ExecutableFeature retreiveRef(
        org.irisa.triskell.MT.repository.MDRDriver.Java.MDRFeatured contextualElement,
        org.irisa.triskell.MT.repository.MDRDriver.Java.MDRFeatured self,
        org.irisa.triskell.MT.DataTypes.Java.Value[] arguments)
        throws ElementNotFoundException, VisibilityException, MultipleDeclarationException, ScopeException
    {
		java.util.Iterator it;
		java.util.Set levelSuperTypes = new java.util.HashSet();
		if (this.getScope() == null)
			levelSuperTypes.add(self.getRefClass().refMetaObject());
		else
			levelSuperTypes.add(((MDRMetaClass)this.getScope()).getRefClass());
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
						return ret = tmp;
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

    public org.irisa.triskell.MT.repository.MDRDriver.Java.ExecutableFeature retreiveRef(
        org.irisa.triskell.MT.repository.MDRDriver.Java.MDRFeatured contextualElement,
        org.irisa.triskell.MT.repository.MDRDriver.Java.MDRFeatured self,
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
        org.irisa.triskell.MT.repository.MDRDriver.Java.MDRFeatured context)
        throws VisibilityException
    {
		javax.jmi.model.ModelElement contextClassRef = context == null ? null : (javax.jmi.model.ModelElement)context.getRefClass().refMetaObject();
		if ((contextClassRef != null) && (! contextClassRef.isVisible(element)))
			throw new VisibilityException();
		if (contextClassRef == null && (element instanceof javax.jmi.model.Feature) && !((javax.jmi.model.Feature)element).getVisibility().toString().equals("public_vis"))
				throw new VisibilityException();
		return true;
    }

    protected boolean checkScopeKind(
        javax.jmi.model.ModelElement element,
        org.irisa.triskell.MT.repository.MDRDriver.Java.MDRFeatured self)
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
        org.irisa.triskell.MT.repository.MDRDriver.Java.MDRFeatured context,
        org.irisa.triskell.MT.repository.MDRDriver.Java.MDRFeatured self,
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

    protected org.irisa.triskell.MT.repository.MDRDriver.Java.ExecutableFeature toExecutableFeature(
        org.irisa.triskell.MT.repository.MDRDriver.Java.MDRFeatured self,
        org.irisa.triskell.MT.DataTypes.Java.Value[] arguments,
        javax.jmi.model.ModelElement me)
    {
		throw new RuntimeException("Internal error.", new Exception("Only delegates can access this method."));
    }
}
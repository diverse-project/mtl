package org.irisa.triskell.MT.repository.ModFactDriver.Java;

import java.util.*;
import javax.jmi.xmi.*;
import javax.jmi.reflect.*;
import org.irisa.triskell.MT.DataTypes.Java.*;
// import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.*;
import javax.jmi.model.*;
import org.apache.log4j.*;
import org.irisa.triskell.MT.utils.Java.*;
import org.irisa.triskell.MT.repository.API.Java.*;

public class MDRMetaAssociation 
    extends org.irisa.triskell.MT.repository.ModFactDriver.Java.MDRMetaElement
    implements org.irisa.triskell.MT.repository.API.Java.MetaAssociation
{
    protected final javax.jmi.reflect.RefAssociation refMetaObject;
    public javax.jmi.reflect.RefAssociation getRefMetaObject () {
        return this.refMetaObject;
    }
    public int cardRefMetaObject () {
        if ( this.refMetaObject == null ) return 0;
        else return 1;
    }
    

	protected static NameCriterium nameCriterium = new NameCriterium();
	protected static TypeCriterium typeCriterium = new TypeCriterium();
	protected static PositionSorter.Criterium [] criteria = new PositionSorter.Criterium [] {new NameCriterium(), new TypeCriterium()};
	
	protected transient AssociationEnd [] ends = null;
	protected AssociationEnd [] getEnds () {
		if (this.ends == null) {
			List endsAsList = getAssociationEnds(this.getRefMetaObject());
			this.ends = (AssociationEnd [])endsAsList.toArray(new AssociationEnd [endsAsList.size()]); 
		}
		return this.ends;
	}

    public MDRMetaAssociation(
        org.irisa.triskell.MT.repository.ModFactDriver.Java.MDRAPI api,
        javax.jmi.reflect.RefAssociation refMetaObject)
    {
        super(api, refMetaObject, null, retreiveQualifiedName((javax.jmi.model.Association)refMetaObject.refMetaObject()));
		this.refMetaObject = refMetaObject;
    }

    public static String[] retreiveQualifiedName(
        javax.jmi.model.Association ref)
    {
		java.util.Collection qualifiedNameAsCollection = ref.getQualifiedName();
		String [] ret = new String [qualifiedNameAsCollection.size()];
		qualifiedNameAsCollection.toArray(ret);
		return ret;
    }

    public void associateModelElements(
        org.irisa.triskell.MT.repository.API.Java.ModelElement contextualElement,
        org.irisa.triskell.MT.repository.API.Java.ModelRole[] roles)
        throws org.irisa.triskell.MT.repository.API.Java.UnknownElementException, org.irisa.triskell.MT.repository.API.Java.IllegalAccessException, org.irisa.triskell.MT.repository.API.Java.CommonException
    {
    	this.act(contextualElement, roles, true);
    }

    public void dissociateModelElements(
        org.irisa.triskell.MT.repository.API.Java.ModelElement contextualElement,
        org.irisa.triskell.MT.repository.API.Java.ModelRole[] roles)
        throws org.irisa.triskell.MT.repository.API.Java.UnknownElementException, org.irisa.triskell.MT.repository.API.Java.IllegalAccessException, org.irisa.triskell.MT.repository.API.Java.CommonException
    {
    	this.act(contextualElement, roles, false);
    }

    protected void act(
        org.irisa.triskell.MT.repository.API.Java.ModelElement contextualElement,
        org.irisa.triskell.MT.repository.API.Java.ModelRole[] roles,
        boolean associate)
        throws org.irisa.triskell.MT.repository.API.Java.UnknownElementException, org.irisa.triskell.MT.repository.API.Java.IllegalAccessException, org.irisa.triskell.MT.repository.API.Java.CommonException
    {
    	if (contextualElement != null && !((javax.jmi.model.ModelElement)((MDRModelElement)contextualElement).getRefClass().refMetaObject()).isVisible((javax.jmi.model.ModelElement)this.getRefMetaObject().refMetaObject()))
    		throw new org.irisa.triskell.MT.repository.API.Java.IllegalAccessException(contextualElement, this);
    	
    	ModelRole [] sortedRoles = (ModelRole[]) new PositionSorter(this.getEnds(), roles, criteria).sort();
    	if (sortedRoles == null)
    		throw new CommonException("Illegal roles for association " + this.toString());
    	if (sortedRoles.length != 2)
    		throw new RuntimeException("Internal error.", new Exception("Unsupported cardinality " + sortedRoles.length + " for association " + this.toString()));
    	
    	boolean exists = this.getRefMetaObject().refLinkExists((RefObject)((MDRElement)sortedRoles[0].getModelElement()).getRef(), (RefObject)((MDRElement)sortedRoles[1].getModelElement()).getRef());
    	try {
    		if (associate) {
    			if (! exists)
    				this.getRefMetaObject().refAddLink((RefObject)((MDRElement)sortedRoles[0].getModelElement()).getRef(), (RefObject)((MDRElement)sortedRoles[1].getModelElement()).getRef());
    		} else {
    			if (exists)
    				this.getRefMetaObject().refRemoveLink((RefObject)((MDRElement)sortedRoles[0].getModelElement()).getRef(), (RefObject)((MDRElement)sortedRoles[1].getModelElement()).getRef());
    		}
		} catch (InvalidObjectException x) {
			String msg = "Cannot operate on deleted object.";
			String xmsg = x.getMessage();
			if (xmsg != null && xmsg.length() > 0)
				msg += " (" + xmsg + ')';
			throw new CommonException(msg);
    	} catch (CompositionViolationException x) {
    		String msg = "You are linking an element threw a composition link while already composed by another element.";
    		String xmsg = x.getMessage();
    		if (xmsg != null && xmsg.length() > 0)
    			msg += " (" + xmsg + ')';
    		throw new CommonException(msg);
    	} catch (Exception x) {
    		throw new CommonException(x.getMessage() + " - " + x.getClass().getName());
    	}
    		
    }

    public String toString()
    {
    	java.util.Iterator it = getAssociationEnds(this.getRefMetaObject()).iterator();
    	StringBuffer aeBuf = new StringBuffer();
    	boolean first = true;
    	AssociationEnd ae;
    	List aetqn;
    	while (it.hasNext()) {
    		if (first)
    			first = false;
    		else
    			aeBuf.append(" and ");
    		ae = (AssociationEnd)it.next();
    		aeBuf.append("association end ");
    		aeBuf.append(ae.getName());
    		aeBuf.append(" of type ");
    		aeBuf.append(this.getSpecificAPI().qualifiedName(ae.getType().getQualifiedName()));
    	}
    	String qn = org.irisa.triskell.MT.utils.Java.AWK.merge(this.getQualifiedName(), Type.PackageIndirection);
		return "association " + (qn == null || qn.length() == 0 ? "<unknown name>" : qn) + " between " + aeBuf.toString();
    }

    public static List getAssociationEnds(
        javax.jmi.reflect.RefAssociation association)
    {
		java.util.List ret = new java.util.ArrayList();
    	java.util.Iterator it = ((javax.jmi.model.Association)association.refMetaObject()).getContents().iterator();
    	Object o;
    	while (it.hasNext()) {
    		o = it.next();
    		if (o instanceof javax.jmi.model.AssociationEnd)
    			ret.add(o);
    	}
		return ret;
    }
    
    public static RefAssociation retreiveAssociation (MDRMetaAssociationEnd [] ends, RefPackage pack) {
    	RefAssociation ret;
    	PositionSorter sorter;
    	Iterator it = pack.refAllAssociations().iterator();
    	while (it.hasNext()) {
    		ret = (RefAssociation)it.next();
    		if (new PositionSorter(getAssociationEnds(ret).toArray(), ends, criteria).sort() != null)
    			return ret;
    	}
    	
		it = pack.refAllPackages().iterator();
		while (it.hasNext()) {
			 ret = retreiveAssociation(ends, (RefPackage)it.next());
			 if (ret != null)	
			 	return ret;
		}
		
		return null;
    }
}
    
abstract class EndPointCriterium
    implements org.irisa.triskell.MT.utils.Java.PositionSorter.Criterium
{
	protected MDRMetaAssociationEnd getEnd (Object o) {
    	MDRMetaAssociationEnd elt=null;
    	String en;
    	if (o instanceof MDRMetaAssociationEnd)
    		elt = (MDRMetaAssociationEnd)o;
    	else if (o instanceof MDRRole)
    		elt = (MDRMetaAssociationEnd)((MDRRole)o).getMetaAssociationEnd();
    	return elt;
	}
}

class NameCriterium 
    extends EndPointCriterium
{

    public boolean mayBeEquivalent(
        java.lang.Object o,
        java.lang.Object model)
    {
    	return this.getEnd(o).checkName((javax.jmi.model.ModelElement)model);
    }
}

class TypeCriterium 
    extends EndPointCriterium
{

    public boolean mayBeEquivalent(
        java.lang.Object o,
        java.lang.Object model)
    {
		return this.getEnd(o).checkType((javax.jmi.model.ModelElement)model);
    }
}


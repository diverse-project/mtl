/*
 * $Id: EMFMetaAssociation.java,v 1.3 2004-09-15 08:12:09 jpthibau Exp $
 * Authors : ffondeme dvojtise
 * 
 * Copyright 2003 - INRIA - LGPL license
 */
package org.inria.EMFDriver;

import java.util.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.irisa.triskell.MT.DataTypes.Java.*;
// import org.netbeans.api.mdr.*;
// import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.*;
// import org.apache.log4j.*;
// import org.irisa.triskell.MT.utils.Java.*;
import org.irisa.triskell.MT.repository.API.Java.*;
import org.eclipse.emf.common.command.*;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;

/**
 * Generic implementation of the repository API (org.irisa.triskell.MT.repository.API.Java.API)
 * This serve as the base for all Driver that uses JMI to connect to the repository 
 */
public class EMFMetaAssociation 
    extends EMFMetaElement
    implements org.irisa.triskell.MT.repository.API.Java.MetaAssociation
{
/*    protected final javax.jmi.reflect.RefAssociation refMetaObject;
    public javax.jmi.reflect.RefAssociation getRefMetaObject () {
        return this.refMetaObject;
    }
    public int cardRefMetaObject () {
        if ( this.refMetaObject == null ) return 0;
        else return 1;
    }*/
    

/*	protected static NameCriterium nameCriterium = new NameCriterium();
	protected static TypeCriterium typeCriterium = new TypeCriterium();
	protected static PositionSorter.Criterium [] criteria = new PositionSorter.Criterium [] {new NameCriterium(), new TypeCriterium()};
*/	
	protected transient MetaAssociationEnd [] ends = null;
	protected MetaAssociationEnd [] getEnds () {
		return this.ends;
	}

    public EMFMetaAssociation(
        EMFAPI api,
		MetaAssociationEnd [] assocEnds)
    {
        super(api, null, null, retrieveQualifiedName(assocEnds));
		this.ends = assocEnds;
    }

    public static String[] retrieveQualifiedName(
	MetaAssociationEnd [] assocEnds)
    {
///		java.util.Collection qualifiedNameAsCollection = ref.getQualifiedName();
		String [] ret = new String [1];
		ret[0] = "Association";
//		qualifiedNameAsCollection.toArray(ret);*/
		return ret; //TODO
    }

    public void associateModelElements(
        org.irisa.triskell.MT.repository.API.Java.ModelElement contextualElement,
        org.irisa.triskell.MT.repository.API.Java.ModelRole[] roles)
        throws org.irisa.triskell.MT.repository.API.Java.UnknownElementException, org.irisa.triskell.MT.repository.API.Java.IllegalAccessException, org.irisa.triskell.MT.repository.API.Java.CommonException
    {
    	if (roles.length == 2) {
    		//only binary associations are managed with EMF
			EMFFeatured leftElt = (EMFFeatured)roles[0].getModelElement();
    		EMFMetaAssociationEnd rightEnd = (EMFMetaAssociationEnd)roles[0].getMetaAssociationEnd();
			EMFFeatured rightElt = (EMFFeatured)roles[1].getModelElement();
    		EMFAPI api = this.getSpecificAPI();
    		Command addCmd = AddCommand.create(api.editingDomain,leftElt.getRef(),rightEnd.getFeature(),rightElt.getRef());
    		if (addCmd.canExecute())
    			addCmd.execute();
    		else {
        		Command setCmd = SetCommand.create(api.editingDomain,leftElt.getRef(),rightEnd.getFeature(),rightElt.getRef());
            	if (setCmd.canExecute())
            		setCmd.execute();
            	else
            			throw new CommonException("Cannot execute associate command !");
    		}
    			
    		
    	}
    	else throw new CommonException("Can only associate 2 modelElements in EMF; roles count :"+roles.length);
    }

    public void dissociateModelElements(
        org.irisa.triskell.MT.repository.API.Java.ModelElement contextualElement,
        org.irisa.triskell.MT.repository.API.Java.ModelRole[] roles)
        throws org.irisa.triskell.MT.repository.API.Java.UnknownElementException, org.irisa.triskell.MT.repository.API.Java.IllegalAccessException, org.irisa.triskell.MT.repository.API.Java.CommonException
    {
		if (roles.length == 2) {
			//only binary associations are managed with EMF
			EMFFeatured leftElt = (EMFFeatured)roles[0].getModelElement();
			EMFMetaAssociationEnd leftEnd = (EMFMetaAssociationEnd)roles[0].getMetaAssociationEnd();
			EMFFeatured rightElt = (EMFFeatured)roles[1].getModelElement();
			EMFAPI api = this.getSpecificAPI();
			Command removeCmd = RemoveCommand.create(api.editingDomain,leftElt.getRef(),leftEnd.getFeature(),rightElt.getRef());
			if (removeCmd.canExecute())
				removeCmd.execute();
			else throw new CommonException("Cannot execute dissociate command !");
    		
		}
		else throw new CommonException("Can only dissociate 2 modelElements in EMF; roles count :"+roles.length);
    }


    public String toString()
    {
    	StringBuffer aeBuf = new StringBuffer();
    	boolean first = true;
    	MetaAssociationEnd ae;
    	List aetqn;
 /*   	while (it.hasNext()) {
    		if (first)
    			first = false;
    		else
    			aeBuf.append(" and ");
    		ae = (AssociationEnd)it.next();
    		aeBuf.append("association end ");
    		aeBuf.append(ae.getName());
    		aeBuf.append(" of type ");
    		aeBuf.append(this.getSpecificAPI().qualifiedName(ae.getType().getQualifiedName()));
    	}*/
    	String qn = org.irisa.triskell.MT.utils.Java.AWK.merge(this.getQualifiedName(), Type.PackageIndirection);
		return "association " + (qn == null || qn.length() == 0 ? "<unknown name>" : qn) + " between " + aeBuf.toString();
    }

/*    public static List getAssociationEnds(
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
    
    public static RefAssociation retrieveAssociation (JMIMetaAssociationEnd [] ends, RefPackage pack) {
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
			 ret = retrieveAssociation(ends, (RefPackage)it.next());
			 if (ret != null)	
			 	return ret;
		}
		
		return null;
    }
}
    
abstract class EndPointCriterium
    implements org.irisa.triskell.MT.utils.Java.PositionSorter.Criterium
{
	protected JMIMetaAssociationEnd getEnd (Object o) {
    	JMIMetaAssociationEnd elt=null;
    	String en;
    	if (o instanceof JMIMetaAssociationEnd)
    		elt = (JMIMetaAssociationEnd)o;
    	else if (o instanceof JMIRole)
    		elt = (JMIMetaAssociationEnd)((JMIRole)o).getMetaAssociationEnd();
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
    }*/
}


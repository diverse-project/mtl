package org.irisa.triskell.MT.repository.ModFactDriver.Java;

import java.util.*;
import javax.jmi.xmi.*;
import javax.jmi.reflect.*;
import org.irisa.triskell.MT.DataTypes.Java.*;
/*import org.netbeans.api.mdr.*;*/
import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.*;
import javax.jmi.model.*;
import java.lang.*;
import org.apache.log4j.*;
import org.irisa.triskell.MT.repository.API.Java.*;

public class MDRMetaAssociationEnd 
    extends org.irisa.triskell.MT.repository.ModFactDriver.Java.MDRMetaFeature
    implements org.irisa.triskell.MT.repository.API.Java.MetaAssociationEnd
{
    protected final org.irisa.triskell.MT.repository.ModFactDriver.Java.MDRMetaClass type;

/*    public static class InvolvedAssociation 
    {
        protected final javax.jmi.model.ModelElement involvedOwner;
        public javax.jmi.model.ModelElement getInvolvedOwner () {
            return this.involvedOwner;
        }
        public int cardInvolvedOwner () {
            if ( this.involvedOwner == null ) return 0;
            else return 1;
        }

        protected final javax.jmi.reflect.RefAssociation involvedAssociation;
        public javax.jmi.reflect.RefAssociation getInvolvedAssociation () {
            return this.involvedAssociation;
        }
        public int cardInvolvedAssociation () {
            if ( this.involvedAssociation == null ) return 0;
            else return 1;
        }

        protected final javax.jmi.model.AssociationEnd involvedAssociationEnd;
        public javax.jmi.model.AssociationEnd getInvolvedAssociationEnd () {
            return this.involvedAssociationEnd;
        }
        public int cardInvolvedAssociationEnd () {
            if ( this.involvedAssociationEnd == null ) return 0;
            else return 1;
        }

        protected final Collection otherAssociationEnd;
        public int cardOtherAssociationEnd () {
            return this.otherAssociationEnd.size();
        }


        public InvolvedAssociation(
            javax.jmi.model.ModelElement owner,
            javax.jmi.reflect.RefAssociation association,
            javax.jmi.model.AssociationEnd end,
            Collection otherEnds)
        {
		this.involvedOwner = owner;
		this.involvedAssociation = association;
		this.involvedAssociationEnd = end;
		this.otherAssociationEnd = otherEnds;
        }
    }*/


    public MDRMetaAssociationEnd(
        org.irisa.triskell.MT.repository.ModFactDriver.Java.MDRAPI api,
        String name,
        org.irisa.triskell.MT.repository.ModFactDriver.Java.MDRMetaClass scope,
        org.irisa.triskell.MT.repository.ModFactDriver.Java.MDRMetaClass type)
    {
        super(api, name, scope, null);
		this.type = type;
    }

    public org.irisa.triskell.MT.repository.API.Java.MetaClass getType()
    {
		return this.type;
    }

    public String getKind()
    {
		return "association end";
    }

    public org.irisa.triskell.MT.repository.ModFactDriver.Java.ExecutableFeature retreiveRef(
        org.irisa.triskell.MT.repository.ModFactDriver.Java.MDRFeatured contextualElement,
        org.irisa.triskell.MT.repository.ModFactDriver.Java.MDRFeatured self,
        org.irisa.triskell.MT.DataTypes.Java.Value[] arguments,
        javax.jmi.model.Namespace owner)
        throws ElementNotFoundException, VisibilityException, MultipleDeclarationException, ScopeException
    {
		ExecutableFeature ret = super.retreiveRef(contextualElement, self, arguments, owner);
    	if (ret == null && !System.getProperty(MDRAPI.getIGNORE_ASSOCIATION_ENDS_FOR_NAVIGATION_KEY(), "true").equalsIgnoreCase("true") && (owner instanceof javax.jmi.model.MofClass)) {
				javax.jmi.model.AssociationEnd me = null, tmp;
				javax.jmi.model.Classifier retType = null, tmpType;
			java.util.Collection involved = this.getSpecificAPI().getRelatedAssociationEnds((javax.jmi.model.MofClass)owner);
			if (involved != null) {
	    		java.util.Iterator itI = involved.iterator();
	    		while (itI.hasNext()) {
					tmp = ((javax.jmi.model.AssociationEnd)itI.next()).otherEnd();
					tmpType = tmp.getType();
	    			if (this.checkAll(tmp, contextualElement, self, arguments)) {
	    				if (ret != null && !(tmpType.allSupertypes().contains(retType)))
	    					throw new MultipleDeclarationException();
	    				else {
	    					ret = new ExecutableJMIAssociationEnd(this.getSpecificAPI(), self, tmp);
							retType = tmpType;
						}
	    			}
				}
    		}
    	}
		return ret;
    }

/*    public static Collection getRefInvolved(
        javax.jmi.model.ModelElement clazz,
        javax.jmi.reflect.RefPackage pack)
    {
		java.util.Collection ret = new java.util.ArrayList();
    	
    	java.util.Iterator assIt = pack.refAllAssociations().iterator(), aeIt, pIt;
    	javax.jmi.reflect.RefAssociation ass;
    	java.util.Collection aes, oaes;
    	javax.jmi.model.AssociationEnd ae, oae;
			javax.jmi.model.ModelElement aet;
		while (assIt.hasNext()) {
    		ass = (javax.jmi.reflect.RefAssociation)assIt.next();
    		aes = MDRMetaAssociation.getAssociationEnds(ass);
    		aeIt = aes.iterator();
			while (aeIt.hasNext()) {
    			ae = (javax.jmi.model.AssociationEnd)aeIt.next();
					aet = ae.getType();
    			if (aet.equals(clazz)) {
    				oaes = new java.util.ArrayList(aes);
    				oaes.remove(ae);
    				ret.add(new InvolvedAssociation(clazz, ass, ae, oaes));
    			}
    		}
    	}
    	
    	pIt = pack.refAllPackages().iterator();
    	while (pIt.hasNext())
    		ret.addAll(getRefInvolved(clazz, (javax.jmi.reflect.RefPackage)pIt.next()));
    	
		return  ret;
    }*/

    public String toString()
    {
		return this.getKind() + ' ' + (this.getName() == null ||  this.getName().length() == 0 ? "<unknown name> " : (this.getName() + ' ')) + " of type " + (this.getScope() == null ? (this.getType() == null ? "<unknown type>" : this.getType().toString()) : this.getScope().toString());
    }

    protected boolean checkKind(
        javax.jmi.model.ModelElement element)
    {
		return (element instanceof javax.jmi.model.Reference) || (element instanceof javax.jmi.model.AssociationEnd);
    }

    protected boolean checkType(
        javax.jmi.model.ModelElement element)
    {
		if (this.getType() == null)
	    	return true;
	    javax.jmi.model.Classifier elementType = ((javax.jmi.model.TypedElement)element).getType(), thisType = (javax.jmi.model.Classifier)((MDRMetaClass)this.getType()).getRefClass().refMetaObject();
		return elementType.equals(thisType) || elementType.allSupertypes().contains(thisType);
    }

    protected boolean checkName(
        javax.jmi.model.ModelElement element)
    {
		if (this.getName() == null || this.getName().length() == 0)
    		return true;
    	else {
    		String elName, aeName;
			javax.jmi.model.AssociationEnd ae;
			if (element instanceof javax.jmi.model.AssociationEnd) {
				ae = (javax.jmi.model.AssociationEnd)element;
			} else if (element instanceof javax.jmi.model.Reference)
				ae = ((javax.jmi.model.Reference)element).getReferencedEnd();
			else
				ae = null;
			aeName = ae == null ? null : ae.getName();
    		if (ae != null && aeName == null) {
    			aeName = ae.getType().getName();
				if (! Character.isUpperCase(aeName.charAt(0)))
					aeName = null;
				else
					aeName = "" + Character.toLowerCase(aeName.charAt(0)) + aeName.substring(1);
    		}
    		if (aeName == null || aeName.length() == 0)
    			return true;
    		elName = element.getName();
    		return elName == null || elName.length() == 0 || elName.equals(this.getName()) || (aeName != null && aeName.equals(this.getName()));
    	}
    }

    protected boolean checkAll(
        javax.jmi.model.ModelElement element,
        org.irisa.triskell.MT.repository.ModFactDriver.Java.MDRFeatured context,
        org.irisa.triskell.MT.repository.ModFactDriver.Java.MDRFeatured self,
        org.irisa.triskell.MT.DataTypes.Java.Value[] arguments)
        throws ElementNotFoundException, VisibilityException, MultipleDeclarationException, ScopeException
    {
		return super.checkAll(element, context, self, arguments) && this.checkType(element);
    }

    protected boolean checkVisibility(
        javax.jmi.model.ModelElement element,
        org.irisa.triskell.MT.repository.ModFactDriver.Java.MDRFeatured context)
        throws VisibilityException
    {
		return ((! (element instanceof javax.jmi.model.AssociationEnd)) || ((javax.jmi.model.AssociationEnd)element).isNavigable()) && super.checkVisibility(element, context);
    }

    protected boolean checkScopeKind(
        javax.jmi.model.ModelElement element,
        org.irisa.triskell.MT.repository.ModFactDriver.Java.MDRFeatured self)
        throws ScopeException
    {
		if (self.isMetaObject())
			throw new ScopeException();
		return super.checkScopeKind(element, self);
    }

    protected org.irisa.triskell.MT.repository.ModFactDriver.Java.ExecutableFeature toExecutableFeature(
        org.irisa.triskell.MT.repository.ModFactDriver.Java.MDRFeatured self,
        org.irisa.triskell.MT.DataTypes.Java.Value[] arguments,
        javax.jmi.model.ModelElement me)
    {
		return new ExecutableJMIFeature(this.getSpecificAPI(), self, (javax.jmi.model.Reference)me);
    }
}

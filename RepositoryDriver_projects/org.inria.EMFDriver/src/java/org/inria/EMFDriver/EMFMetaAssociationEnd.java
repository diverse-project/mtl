/*
 * $Id: EMFMetaAssociationEnd.java,v 1.1 2004-10-25 13:59:57 dvojtise Exp $
 * Authors : ffondeme dvojtise
 */
package org.inria.EMFDriver;

import org.eclipse.emf.ecore.EStructuralFeature;

/**
 * Generic implementation of the repository API (org.irisa.triskell.MT.repository.API.Java.API)
 * This serve as the base for all Driver that uses EMF to connect to the repository 
 */
public class EMFMetaAssociationEnd 
    extends EMFMetaFeature
    implements org.irisa.triskell.MT.repository.API.Java.MetaAssociationEnd
{
    protected final EMFMetaClass type;
    protected EStructuralFeature feature;



    public EMFMetaAssociationEnd(
        EMFAPI api,
        String name,
        EMFMetaClass scope,
        EStructuralFeature feature,
        EMFMetaClass type)
    {
        super(api, name, scope, null);
		this.type = type;
		this.feature = feature;
    }

    public org.irisa.triskell.MT.repository.API.Java.MetaClass getType()
    {
		return this.type;
    }

	public EStructuralFeature getFeature()
	{
		return this.feature;
	}
	
	public void setFeature(EStructuralFeature f)
	{
		this.feature=f;
	}

    public String getKind()
    {
		return "association end";
    }

/*    public org.irisa.triskell.MT.repository.genericJMIDriver.ExecutableFeature retreiveRef(
        org.irisa.triskell.MT.repository.genericJMIDriver.JMIFeatured contextualElement,
        org.irisa.triskell.MT.repository.genericJMIDriver.JMIFeatured self,
        org.irisa.triskell.MT.DataTypes.Java.Value[] arguments,
        javax.jmi.model.Namespace owner)
        throws ElementNotFoundException, VisibilityException, MultipleDeclarationException, ScopeException
    {
		ExecutableFeature ret = super.retreiveRef(contextualElement, self, arguments, owner);
    	if (ret == null && !System.getProperty(JMIAPI.getIGNORE_ASSOCIATION_ENDS_FOR_NAVIGATION_KEY(), "true").equalsIgnoreCase("true") && (owner instanceof javax.jmi.model.MofClass)) {
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
    }*/


    public String toString()
    {
		return this.getKind() + ' ' + (this.getName() == null ||  this.getName().length() == 0 ? "<unknown name> " : (this.getName() + ' ')) + " of type " + (this.getScope() == null ? (this.getType() == null ? "<unknown type>" : this.getType().toString()) : this.getScope().toString());
    }

}

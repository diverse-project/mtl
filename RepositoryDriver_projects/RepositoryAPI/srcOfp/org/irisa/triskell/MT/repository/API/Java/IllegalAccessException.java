package org.irisa.triskell.MT.repository.API.Java;

import java.lang.Exception;

public class IllegalAccessException 
    extends java.lang.Exception
{
    protected final org.irisa.triskell.MT.repository.API.Java.ModelElement contextualElement;
    public org.irisa.triskell.MT.repository.API.Java.ModelElement getContextualElement () {
        return this.contextualElement;
    }
    public int cardContextualElement () {
        if ( this.contextualElement == null ) return 0;
        else return 1;
    }

    protected final org.irisa.triskell.MT.repository.API.Java.MetaElement inaccessibleElement;
    public org.irisa.triskell.MT.repository.API.Java.MetaElement getInaccessibleElement () {
        return this.inaccessibleElement;
    }
    public int cardInaccessibleElement () {
        if ( this.inaccessibleElement == null ) return 0;
        else return 1;
    }


    public IllegalAccessException(
        org.irisa.triskell.MT.repository.API.Java.ModelElement contextualElement,
        org.irisa.triskell.MT.repository.API.Java.MetaElement inaccessibleElement)
    {
        super(contextualElement.toString() + " canot access feature " + inaccessibleElement.toString());
    	this.contextualElement = contextualElement;
    	this.inaccessibleElement = inaccessibleElement; 
    }
}

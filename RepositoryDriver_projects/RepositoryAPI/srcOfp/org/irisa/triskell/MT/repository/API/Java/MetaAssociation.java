package org.irisa.triskell.MT.repository.API.Java;

import org.irisa.triskell.MT.DataTypes.Java.*;
import java.lang.*;

public interface MetaAssociation 
    extends org.irisa.triskell.MT.repository.API.Java.MetaElement
{

     String[] getQualifiedName();

     void associateModelElements(
        org.irisa.triskell.MT.repository.API.Java.ModelElement contextualElement,
        org.irisa.triskell.MT.repository.API.Java.ModelRole[] roles)
        throws org.irisa.triskell.MT.repository.API.Java.UnknownElementException, org.irisa.triskell.MT.repository.API.Java.IllegalAccessException, org.irisa.triskell.MT.repository.API.Java.CommonException;

     void dissociateModelElements(
        org.irisa.triskell.MT.repository.API.Java.ModelElement contextualElement,
        org.irisa.triskell.MT.repository.API.Java.ModelRole[] roles)
        throws org.irisa.triskell.MT.repository.API.Java.UnknownElementException, org.irisa.triskell.MT.repository.API.Java.IllegalAccessException, org.irisa.triskell.MT.repository.API.Java.CommonException;
}

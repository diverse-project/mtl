package org.irisa.triskell.MT.repository.API.Java;

import java.lang.Exception;

public interface MetaClass 
    extends org.irisa.triskell.MT.repository.API.Java.MetaElement
{

     String[] getQualifiedName();

     org.irisa.triskell.MT.repository.API.Java.ModelElement getMetaObject();

     org.irisa.triskell.MT.repository.API.Java.ModelElementIterator allInstances()
        throws org.irisa.triskell.MT.repository.API.Java.UnknownElementException;

     org.irisa.triskell.MT.repository.API.Java.ModelElementIterator allInstancesWithConstraint(
        org.irisa.triskell.MT.repository.API.Java.LookupConstraint constraint)
        throws org.irisa.triskell.MT.repository.API.Java.UnknownElementException;

     org.irisa.triskell.MT.repository.API.Java.ModelElement instanciate(
        org.irisa.triskell.MT.repository.API.Java.ModelElement contextualElement,
        org.irisa.triskell.MT.DataTypes.Java.Value[] arguments)
        throws org.irisa.triskell.MT.repository.API.Java.UnknownElementException, org.irisa.triskell.MT.repository.API.Java.CommonException, org.irisa.triskell.MT.repository.API.Java.IllegalAccessException;
}

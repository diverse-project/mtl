package org.irisa.triskell.MT.repository.API.Java;

import java.lang.Exception;

public interface MetaClass 
    extends org.irisa.triskell.MT.repository.API.Java.MetaElement, org.irisa.triskell.MT.DataTypes.Java.Type
{

     String[] getQualifiedName();

     org.irisa.triskell.MT.repository.API.Java.ModelElement getMetaObject();

     org.irisa.triskell.MT.DataTypes.Java.CollectionValue allInstances()
        throws org.irisa.triskell.MT.repository.API.Java.UnknownElementException;

     org.irisa.triskell.MT.DataTypes.Java.CollectionValue allInstancesWithConstraint(
        org.irisa.triskell.MT.repository.API.Java.LookupConstraint constraint)
        throws org.irisa.triskell.MT.repository.API.Java.UnknownElementException;

     org.irisa.triskell.MT.repository.API.Java.ModelElement instanciate(
        org.irisa.triskell.MT.repository.API.Java.ModelElement contextualElement,
        org.irisa.triskell.MT.DataTypes.Java.Value[] arguments)
        throws org.irisa.triskell.MT.repository.API.Java.UnknownElementException, org.irisa.triskell.MT.repository.API.Java.CommonException, org.irisa.triskell.MT.repository.API.Java.IllegalAccessException;

     org.irisa.triskell.MT.repository.API.Java.ModelElementIterator allInstancesIterator(
        org.irisa.triskell.MT.repository.API.Java.LookupConstraint constraint)
        throws org.irisa.triskell.MT.repository.API.Java.UnknownElementException;
}

package org.irisa.triskell.MT.repository.API.Java;

import java.lang.Exception;

public interface API 
{

     org.irisa.triskell.MT.repository.API.Java.MetaClass getMetaClass(
        String[] qualifiedName)
        throws org.irisa.triskell.MT.repository.API.Java.UnknownElementException;

     org.irisa.triskell.MT.repository.API.Java.MetaAssociation getMetaAssociation(
        String[] qualifiedName)
        throws org.irisa.triskell.MT.repository.API.Java.UnknownElementException;

     org.irisa.triskell.MT.repository.API.Java.MetaAssociation getMetaAssociationWithAssociationEnds(
        org.irisa.triskell.MT.repository.API.Java.MetaAssociationEnd[] associationEnds)
        throws org.irisa.triskell.MT.repository.API.Java.UnknownElementException;

     org.irisa.triskell.MT.repository.API.Java.MetaFeature getMetaFeature(
        String name,
        org.irisa.triskell.MT.repository.API.Java.MetaClass scope);

     org.irisa.triskell.MT.repository.API.Java.MetaAttribute getMetaAttribute(
        String name,
        org.irisa.triskell.MT.repository.API.Java.MetaClass scope);

     org.irisa.triskell.MT.repository.API.Java.MetaOperation getMetaOperation(
        String name,
        org.irisa.triskell.MT.repository.API.Java.MetaClass scope);

     org.irisa.triskell.MT.repository.API.Java.MetaAssociationEnd getMetaAssociationEnd(
        String role,
        org.irisa.triskell.MT.repository.API.Java.MetaClass type,
        org.irisa.triskell.MT.repository.API.Java.MetaClass scope);

     org.irisa.triskell.MT.repository.API.Java.ModelRole getRole(
        org.irisa.triskell.MT.repository.API.Java.ModelElement element,
        org.irisa.triskell.MT.repository.API.Java.MetaAssociationEnd associationEnd);

     void startup(
        org.irisa.triskell.MT.DataTypes.Java.Value[] arguments);

     void shutdown(
        org.irisa.triskell.MT.DataTypes.Java.Value[] arguments);

     void addListenerToElement(
        org.irisa.triskell.MT.repository.API.Java.Element element,
        org.irisa.triskell.MT.repository.API.Java.EventListener listener);

     void removeListenerToElement(
        org.irisa.triskell.MT.repository.API.Java.Element element,
        org.irisa.triskell.MT.repository.API.Java.EventListener listener);

     org.irisa.triskell.MT.repository.API.Java.EventListenerFactory getEventListenerFactory();
}

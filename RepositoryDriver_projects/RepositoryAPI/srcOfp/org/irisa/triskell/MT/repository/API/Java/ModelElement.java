package org.irisa.triskell.MT.repository.API.Java;

import java.util.*;
import java.lang.Exception;

public interface ModelElement 
    extends org.irisa.triskell.MT.repository.API.Java.Element, org.irisa.triskell.MT.DataTypes.Java.ModelElementValue
{
    public static final String AttributeDiscriminant = "Attribute";

    public static final String AssociationDiscriminant = "Association";

    public static final String OperationDiscriminant = "Operation";

    public static final String SetAttributeDiscriminant = "SetAttribute";


     boolean isMetaObject();

     void delete()
        throws org.irisa.triskell.MT.repository.API.Java.UnknownElementException, org.irisa.triskell.MT.repository.API.Java.CommonException;

     boolean isTypeOf(
        org.irisa.triskell.MT.repository.API.Java.MetaClass classifier);

     boolean isKindOf(
        org.irisa.triskell.MT.repository.API.Java.MetaClass classifier);

     org.irisa.triskell.MT.DataTypes.Java.Value getFeatureValue(
        org.irisa.triskell.MT.repository.API.Java.ModelElement contextualElement,
        org.irisa.triskell.MT.repository.API.Java.MetaFeature feature,
        org.irisa.triskell.MT.DataTypes.Java.Value[] arguments)
        throws org.irisa.triskell.MT.repository.API.Java.UnknownElementException, org.irisa.triskell.MT.repository.API.Java.IllegalAccessException, org.irisa.triskell.MT.repository.API.Java.CommonException;

     void setAttributeValue(
        org.irisa.triskell.MT.repository.API.Java.ModelElement contextualElement,
        org.irisa.triskell.MT.repository.API.Java.MetaAttribute argument,
        org.irisa.triskell.MT.DataTypes.Java.Value value)
        throws org.irisa.triskell.MT.repository.API.Java.UnknownElementException, org.irisa.triskell.MT.repository.API.Java.CommonException, org.irisa.triskell.MT.repository.API.Java.IllegalAccessException;

     org.irisa.triskell.MT.DataTypes.Java.Value invokeQueryOperation(
        org.irisa.triskell.MT.repository.API.Java.ModelElement contextualElement,
        org.irisa.triskell.MT.repository.API.Java.MetaOperation feature,
        org.irisa.triskell.MT.DataTypes.Java.Value[] arguments)
        throws org.irisa.triskell.MT.repository.API.Java.UnknownElementException, org.irisa.triskell.MT.repository.API.Java.IllegalAccessException, org.irisa.triskell.MT.repository.API.Java.CommonException, org.irisa.triskell.MT.repository.API.Java.IsQueryException;

     String getUniqId();
}

package org.irisa.triskell.MT.repository.API.Java;

import java.util.*;
import java.lang.Exception;

/**
  * Any model element,  either instance of meta class or meta class representant.
  */
public interface ModelElement 
    extends org.irisa.triskell.MT.repository.API.Java.Element, org.irisa.triskell.MT.DataTypes.Java.ModelElementValue
{
    public static final String AttributeDiscriminant = "Attribute";

    public static final String AssociationDiscriminant = "Association";

    public static final String OperationDiscriminant = "Operation";

    public static final String SetAttributeDiscriminant = "SetAttribute";


    /**
      * Indicates if the model element is a meta class representant.
      */
     boolean isMetaObject();

    /**
      * Removes the model element from the model. Not efficient on meta objects, enumered and structures.
      * WARNING: This is a side effect operation (e.g. it modifies the model).
      * Raises UnknownElementException if the model element does not exists in the model (e.g. when the model element have ever been deleted).
      * Raises CommonException when the model element deletion introduces a problem (e.g. the model element is null).
      */
     void delete()
        throws org.irisa.triskell.MT.repository.API.Java.UnknownElementException, org.irisa.triskell.MT.repository.API.Java.CommonException;

    /**
      * Indicates if the model element is instance of the class parameter (non transitive). False for meta objects. True for null.
      */
     boolean isTypeOf(
        org.irisa.triskell.MT.repository.API.Java.MetaClass classifier);

    /**
      * Indicates if the model element is instance of the class parameter (transitive). False for meta objects. True for null.
      */
     boolean isKindOf(
        org.irisa.triskell.MT.repository.API.Java.MetaClass classifier);

    /**
      * Returns the value for a given feature (either attributes, association ends or operation). It points on a static feature if this object is a meta object.
      * Returns an UndefinedValue if this object is null.
      * WARNING: this may introduce a side effect if attempting to invoke a side effect operation (e.g. it may modifies the model).
      * Raises UnknownElementException if the given meta feature is undefined in the metamodel.
      * Raises IllegalAccessException if the contextual model element has no access rights to the given meta attribute.
      * Raises CommonException for any other problem (i.e. an operation invokation which leads to an error).
      */
     org.irisa.triskell.MT.DataTypes.Java.Value getFeatureValue(
        org.irisa.triskell.MT.repository.API.Java.ModelElement contextualElement,
        org.irisa.triskell.MT.repository.API.Java.MetaFeature feature,
        org.irisa.triskell.MT.DataTypes.Java.Value[] arguments)
        throws org.irisa.triskell.MT.repository.API.Java.UnknownElementException, org.irisa.triskell.MT.repository.API.Java.IllegalAccessException, org.irisa.triskell.MT.repository.API.Java.CommonException;

    /**
      * Fixes the value of a given attribute. It points on a static attribute if this object is a meta object.
      * WARNING: This is a side effect operation (e.g. it modifies the model).
      * Raises UnknownElementException if the given meta attribute is undefined in the metamodel.
      * Raises IllegalAccessException if the contextual model element has no access rights to the given meta attribute.
      * Raises CommonException for any other problem (e.g. this object is null).
      */
     void setAttributeValue(
        org.irisa.triskell.MT.repository.API.Java.ModelElement contextualElement,
        org.irisa.triskell.MT.repository.API.Java.MetaAttribute argument,
        org.irisa.triskell.MT.DataTypes.Java.Value value)
        throws org.irisa.triskell.MT.repository.API.Java.UnknownElementException, org.irisa.triskell.MT.repository.API.Java.CommonException, org.irisa.triskell.MT.repository.API.Java.IllegalAccessException;

    /**
      * A side effect free maneer to invoke an operation. It points on a static operation if this object is a meta object.
      * Returns an UndefinedValue if this object is null.
      * WARNING: this may introduce a side effect if attempting to invoke a side effect operation (e.g. it may modifies the model).
      * Raises IsQueryException if the invokation leads to a side effect (e.g. modifies the model); this exception is raised before the modification, that is the modification is never done.
      * Raises UnknownElementException if the given meta feature is undefined in the metamodel.
      * Raises IllegalAccessException if the contextual model element has no access rights to the given meta attribute.
      * Raises CommonException for any other problem (i.e. an operation invokation which leads to an error).
      */
     org.irisa.triskell.MT.DataTypes.Java.Value invokeQueryOperation(
        org.irisa.triskell.MT.repository.API.Java.ModelElement contextualElement,
        org.irisa.triskell.MT.repository.API.Java.MetaOperation feature,
        org.irisa.triskell.MT.DataTypes.Java.Value[] arguments)
        throws org.irisa.triskell.MT.repository.API.Java.UnknownElementException, org.irisa.triskell.MT.repository.API.Java.IllegalAccessException, org.irisa.triskell.MT.repository.API.Java.CommonException, org.irisa.triskell.MT.repository.API.Java.IsQueryException;

     String getUniqId();
}

/**
 * <copyright>
 * </copyright>
 *
 * $Id: SimpleUMLFactoryImpl.java,v 1.1 2004-08-10 11:55:39 dvojtise Exp $
 */
package SimpleUML.impl;

import SimpleUML.Association;
import SimpleUML.Attribute;
import SimpleUML.PrimitiveDataType;
import SimpleUML.SimpleUMLFactory;
import SimpleUML.SimpleUMLPackage;
import SimpleUML.SimpleUmlMM;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class SimpleUMLFactoryImpl extends EFactoryImpl implements SimpleUMLFactory
{
  /**
   * Creates and instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public SimpleUMLFactoryImpl()
  {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EObject create(EClass eClass)
  {
    switch (eClass.getClassifierID())
    {
      case SimpleUMLPackage.CLASS: return createClass();
      case SimpleUMLPackage.PRIMITIVE_DATA_TYPE: return createPrimitiveDataType();
      case SimpleUMLPackage.ATTRIBUTE: return createAttribute();
      case SimpleUMLPackage.ASSOCIATION: return createAssociation();
      case SimpleUMLPackage.SIMPLE_UML_MM: return createSimpleUmlMM();
      default:
        throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public SimpleUML.Class createClass()
  {
    ClassImpl class_ = new ClassImpl();
    return class_;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public PrimitiveDataType createPrimitiveDataType()
  {
    PrimitiveDataTypeImpl primitiveDataType = new PrimitiveDataTypeImpl();
    return primitiveDataType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Attribute createAttribute()
  {
    AttributeImpl attribute = new AttributeImpl();
    return attribute;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Association createAssociation()
  {
    AssociationImpl association = new AssociationImpl();
    return association;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public SimpleUmlMM createSimpleUmlMM()
  {
    SimpleUmlMMImpl simpleUmlMM = new SimpleUmlMMImpl();
    return simpleUmlMM;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public SimpleUMLPackage getSimpleUMLPackage()
  {
    return (SimpleUMLPackage)getEPackage();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @deprecated
   * @generated
   */
  public static SimpleUMLPackage getPackage()
  {
    return SimpleUMLPackage.eINSTANCE;
  }

} //SimpleUMLFactoryImpl

/**
 * <copyright>
 * </copyright>
 *
 * $Id: SimpleUMLPackage.java,v 1.1 2004-08-10 11:55:36 dvojtise Exp $
 */
package SimpleUML;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see SimpleUML.SimpleUMLFactory
 * @generated
 */
public interface SimpleUMLPackage extends EPackage
{
  /**
   * The package name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNAME = "SimpleUML";

  /**
   * The package namespace URI.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_URI = "null";

  /**
   * The package namespace name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_PREFIX = "null";

  /**
   * The singleton instance of the package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  SimpleUMLPackage eINSTANCE = SimpleUML.impl.SimpleUMLPackageImpl.init();

  /**
   * The meta object id for the '{@link SimpleUML.impl.ModelElementImpl <em>Model Element</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see SimpleUML.impl.ModelElementImpl
   * @see SimpleUML.impl.SimpleUMLPackageImpl#getModelElement()
   * @generated
   */
  int MODEL_ELEMENT = 0;

  /**
   * The feature id for the '<em><b>Kind</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MODEL_ELEMENT__KIND = 0;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MODEL_ELEMENT__NAME = 1;

  /**
   * The feature id for the '<em><b>Simple Uml MM</b></em>' container reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MODEL_ELEMENT__SIMPLE_UML_MM = 2;

  /**
   * The number of structural features of the the '<em>Model Element</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MODEL_ELEMENT_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link SimpleUML.impl.ClassifierImpl <em>Classifier</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see SimpleUML.impl.ClassifierImpl
   * @see SimpleUML.impl.SimpleUMLPackageImpl#getClassifier()
   * @generated
   */
  int CLASSIFIER = 1;

  /**
   * The feature id for the '<em><b>Kind</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CLASSIFIER__KIND = MODEL_ELEMENT__KIND;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CLASSIFIER__NAME = MODEL_ELEMENT__NAME;

  /**
   * The feature id for the '<em><b>Simple Uml MM</b></em>' container reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CLASSIFIER__SIMPLE_UML_MM = MODEL_ELEMENT__SIMPLE_UML_MM;

  /**
   * The feature id for the '<em><b>Typed</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CLASSIFIER__TYPED = MODEL_ELEMENT_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the the '<em>Classifier</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CLASSIFIER_FEATURE_COUNT = MODEL_ELEMENT_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link SimpleUML.impl.ClassImpl <em>Class</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see SimpleUML.impl.ClassImpl
   * @see SimpleUML.impl.SimpleUMLPackageImpl#getClass_()
   * @generated
   */
  int CLASS = 2;

  /**
   * The feature id for the '<em><b>Kind</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CLASS__KIND = CLASSIFIER__KIND;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CLASS__NAME = CLASSIFIER__NAME;

  /**
   * The feature id for the '<em><b>Simple Uml MM</b></em>' container reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CLASS__SIMPLE_UML_MM = CLASSIFIER__SIMPLE_UML_MM;

  /**
   * The feature id for the '<em><b>Typed</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CLASS__TYPED = CLASSIFIER__TYPED;

  /**
   * The feature id for the '<em><b>Attribute</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CLASS__ATTRIBUTE = CLASSIFIER_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Forward</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CLASS__FORWARD = CLASSIFIER_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Backward</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CLASS__BACKWARD = CLASSIFIER_FEATURE_COUNT + 2;

  /**
   * The number of structural features of the the '<em>Class</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CLASS_FEATURE_COUNT = CLASSIFIER_FEATURE_COUNT + 3;

  /**
   * The meta object id for the '{@link SimpleUML.impl.PrimitiveDataTypeImpl <em>Primitive Data Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see SimpleUML.impl.PrimitiveDataTypeImpl
   * @see SimpleUML.impl.SimpleUMLPackageImpl#getPrimitiveDataType()
   * @generated
   */
  int PRIMITIVE_DATA_TYPE = 3;

  /**
   * The feature id for the '<em><b>Kind</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PRIMITIVE_DATA_TYPE__KIND = CLASSIFIER__KIND;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PRIMITIVE_DATA_TYPE__NAME = CLASSIFIER__NAME;

  /**
   * The feature id for the '<em><b>Simple Uml MM</b></em>' container reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PRIMITIVE_DATA_TYPE__SIMPLE_UML_MM = CLASSIFIER__SIMPLE_UML_MM;

  /**
   * The feature id for the '<em><b>Typed</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PRIMITIVE_DATA_TYPE__TYPED = CLASSIFIER__TYPED;

  /**
   * The number of structural features of the the '<em>Primitive Data Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PRIMITIVE_DATA_TYPE_FEATURE_COUNT = CLASSIFIER_FEATURE_COUNT + 0;

  /**
   * The meta object id for the '{@link SimpleUML.impl.AttributeImpl <em>Attribute</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see SimpleUML.impl.AttributeImpl
   * @see SimpleUML.impl.SimpleUMLPackageImpl#getAttribute()
   * @generated
   */
  int ATTRIBUTE = 4;

  /**
   * The feature id for the '<em><b>Kind</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ATTRIBUTE__KIND = MODEL_ELEMENT__KIND;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ATTRIBUTE__NAME = MODEL_ELEMENT__NAME;

  /**
   * The feature id for the '<em><b>Simple Uml MM</b></em>' container reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ATTRIBUTE__SIMPLE_UML_MM = MODEL_ELEMENT__SIMPLE_UML_MM;

  /**
   * The feature id for the '<em><b>Type</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ATTRIBUTE__TYPE = MODEL_ELEMENT_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Owner</b></em>' container reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ATTRIBUTE__OWNER = MODEL_ELEMENT_FEATURE_COUNT + 1;

  /**
   * The number of structural features of the the '<em>Attribute</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ATTRIBUTE_FEATURE_COUNT = MODEL_ELEMENT_FEATURE_COUNT + 2;

  /**
   * The meta object id for the '{@link SimpleUML.impl.AssociationImpl <em>Association</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see SimpleUML.impl.AssociationImpl
   * @see SimpleUML.impl.SimpleUMLPackageImpl#getAssociation()
   * @generated
   */
  int ASSOCIATION = 5;

  /**
   * The feature id for the '<em><b>Kind</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ASSOCIATION__KIND = MODEL_ELEMENT__KIND;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ASSOCIATION__NAME = MODEL_ELEMENT__NAME;

  /**
   * The feature id for the '<em><b>Simple Uml MM</b></em>' container reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ASSOCIATION__SIMPLE_UML_MM = MODEL_ELEMENT__SIMPLE_UML_MM;

  /**
   * The feature id for the '<em><b>Source</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ASSOCIATION__SOURCE = MODEL_ELEMENT_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Destination</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ASSOCIATION__DESTINATION = MODEL_ELEMENT_FEATURE_COUNT + 1;

  /**
   * The number of structural features of the the '<em>Association</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ASSOCIATION_FEATURE_COUNT = MODEL_ELEMENT_FEATURE_COUNT + 2;

  /**
   * The meta object id for the '{@link SimpleUML.impl.SimpleUmlMMImpl <em>Simple Uml MM</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see SimpleUML.impl.SimpleUmlMMImpl
   * @see SimpleUML.impl.SimpleUMLPackageImpl#getSimpleUmlMM()
   * @generated
   */
  int SIMPLE_UML_MM = 6;

  /**
   * The feature id for the '<em><b>Model Element</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SIMPLE_UML_MM__MODEL_ELEMENT = 0;

  /**
   * The number of structural features of the the '<em>Simple Uml MM</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SIMPLE_UML_MM_FEATURE_COUNT = 1;


  /**
   * Returns the meta object for class '{@link SimpleUML.ModelElement <em>Model Element</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Model Element</em>'.
   * @see SimpleUML.ModelElement
   * @generated
   */
  EClass getModelElement();

  /**
   * Returns the meta object for the attribute '{@link SimpleUML.ModelElement#getKind <em>Kind</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Kind</em>'.
   * @see SimpleUML.ModelElement#getKind()
   * @see #getModelElement()
   * @generated
   */
  EAttribute getModelElement_Kind();

  /**
   * Returns the meta object for the attribute '{@link SimpleUML.ModelElement#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see SimpleUML.ModelElement#getName()
   * @see #getModelElement()
   * @generated
   */
  EAttribute getModelElement_Name();

  /**
   * Returns the meta object for the container reference '{@link SimpleUML.ModelElement#getSimpleUmlMM <em>Simple Uml MM</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the container reference '<em>Simple Uml MM</em>'.
   * @see SimpleUML.ModelElement#getSimpleUmlMM()
   * @see #getModelElement()
   * @generated
   */
  EReference getModelElement_SimpleUmlMM();

  /**
   * Returns the meta object for class '{@link SimpleUML.Classifier <em>Classifier</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Classifier</em>'.
   * @see SimpleUML.Classifier
   * @generated
   */
  EClass getClassifier();

  /**
   * Returns the meta object for the reference list '{@link SimpleUML.Classifier#getTyped <em>Typed</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Typed</em>'.
   * @see SimpleUML.Classifier#getTyped()
   * @see #getClassifier()
   * @generated
   */
  EReference getClassifier_Typed();

  /**
   * Returns the meta object for class '{@link SimpleUML.Class <em>Class</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Class</em>'.
   * @see SimpleUML.Class
   * @generated
   */
  EClass getClass_();

  /**
   * Returns the meta object for the containment reference list '{@link SimpleUML.Class#getAttribute <em>Attribute</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Attribute</em>'.
   * @see SimpleUML.Class#getAttribute()
   * @see #getClass_()
   * @generated
   */
  EReference getClass_Attribute();

  /**
   * Returns the meta object for the reference list '{@link SimpleUML.Class#getForward <em>Forward</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Forward</em>'.
   * @see SimpleUML.Class#getForward()
   * @see #getClass_()
   * @generated
   */
  EReference getClass_Forward();

  /**
   * Returns the meta object for the reference list '{@link SimpleUML.Class#getBackward <em>Backward</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Backward</em>'.
   * @see SimpleUML.Class#getBackward()
   * @see #getClass_()
   * @generated
   */
  EReference getClass_Backward();

  /**
   * Returns the meta object for class '{@link SimpleUML.PrimitiveDataType <em>Primitive Data Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Primitive Data Type</em>'.
   * @see SimpleUML.PrimitiveDataType
   * @generated
   */
  EClass getPrimitiveDataType();

  /**
   * Returns the meta object for class '{@link SimpleUML.Attribute <em>Attribute</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Attribute</em>'.
   * @see SimpleUML.Attribute
   * @generated
   */
  EClass getAttribute();

  /**
   * Returns the meta object for the reference '{@link SimpleUML.Attribute#getType <em>Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Type</em>'.
   * @see SimpleUML.Attribute#getType()
   * @see #getAttribute()
   * @generated
   */
  EReference getAttribute_Type();

  /**
   * Returns the meta object for the container reference '{@link SimpleUML.Attribute#getOwner <em>Owner</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the container reference '<em>Owner</em>'.
   * @see SimpleUML.Attribute#getOwner()
   * @see #getAttribute()
   * @generated
   */
  EReference getAttribute_Owner();

  /**
   * Returns the meta object for class '{@link SimpleUML.Association <em>Association</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Association</em>'.
   * @see SimpleUML.Association
   * @generated
   */
  EClass getAssociation();

  /**
   * Returns the meta object for the reference '{@link SimpleUML.Association#getSource <em>Source</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Source</em>'.
   * @see SimpleUML.Association#getSource()
   * @see #getAssociation()
   * @generated
   */
  EReference getAssociation_Source();

  /**
   * Returns the meta object for the reference '{@link SimpleUML.Association#getDestination <em>Destination</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Destination</em>'.
   * @see SimpleUML.Association#getDestination()
   * @see #getAssociation()
   * @generated
   */
  EReference getAssociation_Destination();

  /**
   * Returns the meta object for class '{@link SimpleUML.SimpleUmlMM <em>Simple Uml MM</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Simple Uml MM</em>'.
   * @see SimpleUML.SimpleUmlMM
   * @generated
   */
  EClass getSimpleUmlMM();

  /**
   * Returns the meta object for the containment reference list '{@link SimpleUML.SimpleUmlMM#getModelElement <em>Model Element</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Model Element</em>'.
   * @see SimpleUML.SimpleUmlMM#getModelElement()
   * @see #getSimpleUmlMM()
   * @generated
   */
  EReference getSimpleUmlMM_ModelElement();

  /**
   * Returns the factory that creates the instances of the model.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the factory that creates the instances of the model.
   * @generated
   */
  SimpleUMLFactory getSimpleUMLFactory();

} //SimpleUMLPackage

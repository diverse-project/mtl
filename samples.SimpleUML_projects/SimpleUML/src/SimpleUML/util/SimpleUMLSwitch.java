/**
 * <copyright>
 * </copyright>
 *
 * $Id: SimpleUMLSwitch.java,v 1.1 2004-08-10 11:55:41 dvojtise Exp $
 */
package SimpleUML.util;

import SimpleUML.Association;
import SimpleUML.Attribute;
import SimpleUML.Classifier;
import SimpleUML.ModelElement;
import SimpleUML.PrimitiveDataType;
import SimpleUML.SimpleUMLPackage;
import SimpleUML.SimpleUmlMM;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see SimpleUML.SimpleUMLPackage
 * @generated
 */
public class SimpleUMLSwitch
{
  /**
   * The cached model package
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected static SimpleUMLPackage modelPackage;

  /**
   * Creates an instance of the switch.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public SimpleUMLSwitch()
  {
    if (modelPackage == null)
    {
      modelPackage = SimpleUMLPackage.eINSTANCE;
    }
  }

  /**
   * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the first non-null result returned by a <code>caseXXX</code> call.
   * @generated
   */
  public Object doSwitch(EObject theEObject)
  {
    return doSwitch(theEObject.eClass(), theEObject);
  }

  /**
   * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the first non-null result returned by a <code>caseXXX</code> call.
   * @generated
   */
  protected Object doSwitch(EClass theEClass, EObject theEObject)
  {
    if (theEClass.eContainer() == modelPackage)
    {
      return doSwitch(theEClass.getClassifierID(), theEObject);
    }
    else
    {
      List eSuperTypes = theEClass.getESuperTypes();
      return
        eSuperTypes.isEmpty() ?
          defaultCase(theEObject) :
          doSwitch((EClass)eSuperTypes.get(0), theEObject);
    }
  }

  /**
   * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the first non-null result returned by a <code>caseXXX</code> call.
   * @generated
   */
  protected Object doSwitch(int classifierID, EObject theEObject)
  {
    switch (classifierID)
    {
      case SimpleUMLPackage.CLASS:
      {
        SimpleUML.Class class_ = (SimpleUML.Class)theEObject;
        Object result = caseClass(class_);
        if (result == null) result = caseClassifier(class_);
        if (result == null) result = caseModelElement(class_);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case SimpleUMLPackage.PRIMITIVE_DATA_TYPE:
      {
        PrimitiveDataType primitiveDataType = (PrimitiveDataType)theEObject;
        Object result = casePrimitiveDataType(primitiveDataType);
        if (result == null) result = caseClassifier(primitiveDataType);
        if (result == null) result = caseModelElement(primitiveDataType);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case SimpleUMLPackage.ATTRIBUTE:
      {
        Attribute attribute = (Attribute)theEObject;
        Object result = caseAttribute(attribute);
        if (result == null) result = caseModelElement(attribute);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case SimpleUMLPackage.ASSOCIATION:
      {
        Association association = (Association)theEObject;
        Object result = caseAssociation(association);
        if (result == null) result = caseModelElement(association);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case SimpleUMLPackage.SIMPLE_UML_MM:
      {
        SimpleUmlMM simpleUmlMM = (SimpleUmlMM)theEObject;
        Object result = caseSimpleUmlMM(simpleUmlMM);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      default: return defaultCase(theEObject);
    }
  }

  /**
   * Returns the result of interpretting the object as an instance of '<em>Model Element</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpretting the object as an instance of '<em>Model Element</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public Object caseModelElement(ModelElement object)
  {
    return null;
  }

  /**
   * Returns the result of interpretting the object as an instance of '<em>Classifier</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpretting the object as an instance of '<em>Classifier</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public Object caseClassifier(Classifier object)
  {
    return null;
  }

  /**
   * Returns the result of interpretting the object as an instance of '<em>Class</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpretting the object as an instance of '<em>Class</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public Object caseClass(SimpleUML.Class object)
  {
    return null;
  }

  /**
   * Returns the result of interpretting the object as an instance of '<em>Primitive Data Type</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpretting the object as an instance of '<em>Primitive Data Type</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public Object casePrimitiveDataType(PrimitiveDataType object)
  {
    return null;
  }

  /**
   * Returns the result of interpretting the object as an instance of '<em>Attribute</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpretting the object as an instance of '<em>Attribute</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public Object caseAttribute(Attribute object)
  {
    return null;
  }

  /**
   * Returns the result of interpretting the object as an instance of '<em>Association</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpretting the object as an instance of '<em>Association</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public Object caseAssociation(Association object)
  {
    return null;
  }

  /**
   * Returns the result of interpretting the object as an instance of '<em>Simple Uml MM</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpretting the object as an instance of '<em>Simple Uml MM</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public Object caseSimpleUmlMM(SimpleUmlMM object)
  {
    return null;
  }

  /**
   * Returns the result of interpretting the object as an instance of '<em>EObject</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch, but this is the last case anyway.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpretting the object as an instance of '<em>EObject</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject)
   * @generated
   */
  public Object defaultCase(EObject object)
  {
    return null;
  }

} //SimpleUMLSwitch

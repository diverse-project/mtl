/**
 * <copyright>
 * </copyright>
 *
 * $Id: SimpleUmlMMImpl.java,v 1.1 2004-08-10 11:55:38 dvojtise Exp $
 */
package SimpleUML.impl;

import SimpleUML.ModelElement;
import SimpleUML.SimpleUMLPackage;
import SimpleUML.SimpleUmlMM;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Simple Uml MM</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link SimpleUML.impl.SimpleUmlMMImpl#getModelElement <em>Model Element</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SimpleUmlMMImpl extends EObjectImpl implements SimpleUmlMM
{
  /**
   * The cached value of the '{@link #getModelElement() <em>Model Element</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getModelElement()
   * @generated
   * @ordered
   */
  protected EList modelElement = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected SimpleUmlMMImpl()
  {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected EClass eStaticClass()
  {
    return SimpleUMLPackage.eINSTANCE.getSimpleUmlMM();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList getModelElement()
  {
    if (modelElement == null)
    {
      modelElement = new EObjectContainmentWithInverseEList(ModelElement.class, this, SimpleUMLPackage.SIMPLE_UML_MM__MODEL_ELEMENT, SimpleUMLPackage.MODEL_ELEMENT__SIMPLE_UML_MM);
    }
    return modelElement;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs)
  {
    if (featureID >= 0)
    {
      switch (eDerivedStructuralFeatureID(featureID, baseClass))
      {
        case SimpleUMLPackage.SIMPLE_UML_MM__MODEL_ELEMENT:
          return ((InternalEList)getModelElement()).basicAdd(otherEnd, msgs);
        default:
          return eDynamicInverseAdd(otherEnd, featureID, baseClass, msgs);
      }
    }
    if (eContainer != null)
      msgs = eBasicRemoveFromContainer(msgs);
    return eBasicSetContainer(otherEnd, featureID, msgs);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs)
  {
    if (featureID >= 0)
    {
      switch (eDerivedStructuralFeatureID(featureID, baseClass))
      {
        case SimpleUMLPackage.SIMPLE_UML_MM__MODEL_ELEMENT:
          return ((InternalEList)getModelElement()).basicRemove(otherEnd, msgs);
        default:
          return eDynamicInverseRemove(otherEnd, featureID, baseClass, msgs);
      }
    }
    return eBasicSetContainer(null, featureID, msgs);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Object eGet(EStructuralFeature eFeature, boolean resolve)
  {
    switch (eDerivedStructuralFeatureID(eFeature))
    {
      case SimpleUMLPackage.SIMPLE_UML_MM__MODEL_ELEMENT:
        return getModelElement();
    }
    return eDynamicGet(eFeature, resolve);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void eSet(EStructuralFeature eFeature, Object newValue)
  {
    switch (eDerivedStructuralFeatureID(eFeature))
    {
      case SimpleUMLPackage.SIMPLE_UML_MM__MODEL_ELEMENT:
        getModelElement().clear();
        getModelElement().addAll((Collection)newValue);
        return;
    }
    eDynamicSet(eFeature, newValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void eUnset(EStructuralFeature eFeature)
  {
    switch (eDerivedStructuralFeatureID(eFeature))
    {
      case SimpleUMLPackage.SIMPLE_UML_MM__MODEL_ELEMENT:
        getModelElement().clear();
        return;
    }
    eDynamicUnset(eFeature);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean eIsSet(EStructuralFeature eFeature)
  {
    switch (eDerivedStructuralFeatureID(eFeature))
    {
      case SimpleUMLPackage.SIMPLE_UML_MM__MODEL_ELEMENT:
        return modelElement != null && !modelElement.isEmpty();
    }
    return eDynamicIsSet(eFeature);
  }

} //SimpleUmlMMImpl

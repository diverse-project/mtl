/**
 * <copyright>
 * </copyright>
 *
 * $Id: PrimitiveDataTypeImpl.java,v 1.1 2004-08-10 11:55:39 dvojtise Exp $
 */
package SimpleUML.impl;

import SimpleUML.PrimitiveDataType;
import SimpleUML.SimpleUMLPackage;
import SimpleUML.SimpleUmlMM;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Primitive Data Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * </p>
 *
 * @generated
 */
public class PrimitiveDataTypeImpl extends ClassifierImpl implements PrimitiveDataType
{
  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected PrimitiveDataTypeImpl()
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
    return SimpleUMLPackage.eINSTANCE.getPrimitiveDataType();
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
        case SimpleUMLPackage.PRIMITIVE_DATA_TYPE__SIMPLE_UML_MM:
          if (eContainer != null)
            msgs = eBasicRemoveFromContainer(msgs);
          return eBasicSetContainer(otherEnd, SimpleUMLPackage.PRIMITIVE_DATA_TYPE__SIMPLE_UML_MM, msgs);
        case SimpleUMLPackage.PRIMITIVE_DATA_TYPE__TYPED:
          return ((InternalEList)getTyped()).basicAdd(otherEnd, msgs);
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
        case SimpleUMLPackage.PRIMITIVE_DATA_TYPE__SIMPLE_UML_MM:
          return eBasicSetContainer(null, SimpleUMLPackage.PRIMITIVE_DATA_TYPE__SIMPLE_UML_MM, msgs);
        case SimpleUMLPackage.PRIMITIVE_DATA_TYPE__TYPED:
          return ((InternalEList)getTyped()).basicRemove(otherEnd, msgs);
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
  public NotificationChain eBasicRemoveFromContainer(NotificationChain msgs)
  {
    if (eContainerFeatureID >= 0)
    {
      switch (eContainerFeatureID)
      {
        case SimpleUMLPackage.PRIMITIVE_DATA_TYPE__SIMPLE_UML_MM:
          return ((InternalEObject)eContainer).eInverseRemove(this, SimpleUMLPackage.SIMPLE_UML_MM__MODEL_ELEMENT, SimpleUmlMM.class, msgs);
        default:
          return eDynamicBasicRemoveFromContainer(msgs);
      }
    }
    return ((InternalEObject)eContainer).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - eContainerFeatureID, null, msgs);
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
      case SimpleUMLPackage.PRIMITIVE_DATA_TYPE__KIND:
        return getKind();
      case SimpleUMLPackage.PRIMITIVE_DATA_TYPE__NAME:
        return getName();
      case SimpleUMLPackage.PRIMITIVE_DATA_TYPE__SIMPLE_UML_MM:
        return getSimpleUmlMM();
      case SimpleUMLPackage.PRIMITIVE_DATA_TYPE__TYPED:
        return getTyped();
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
      case SimpleUMLPackage.PRIMITIVE_DATA_TYPE__KIND:
        setKind((String)newValue);
        return;
      case SimpleUMLPackage.PRIMITIVE_DATA_TYPE__NAME:
        setName((String)newValue);
        return;
      case SimpleUMLPackage.PRIMITIVE_DATA_TYPE__SIMPLE_UML_MM:
        setSimpleUmlMM((SimpleUmlMM)newValue);
        return;
      case SimpleUMLPackage.PRIMITIVE_DATA_TYPE__TYPED:
        getTyped().clear();
        getTyped().addAll((Collection)newValue);
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
      case SimpleUMLPackage.PRIMITIVE_DATA_TYPE__KIND:
        setKind(KIND_EDEFAULT);
        return;
      case SimpleUMLPackage.PRIMITIVE_DATA_TYPE__NAME:
        setName(NAME_EDEFAULT);
        return;
      case SimpleUMLPackage.PRIMITIVE_DATA_TYPE__SIMPLE_UML_MM:
        setSimpleUmlMM((SimpleUmlMM)null);
        return;
      case SimpleUMLPackage.PRIMITIVE_DATA_TYPE__TYPED:
        getTyped().clear();
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
      case SimpleUMLPackage.PRIMITIVE_DATA_TYPE__KIND:
        return KIND_EDEFAULT == null ? kind != null : !KIND_EDEFAULT.equals(kind);
      case SimpleUMLPackage.PRIMITIVE_DATA_TYPE__NAME:
        return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
      case SimpleUMLPackage.PRIMITIVE_DATA_TYPE__SIMPLE_UML_MM:
        return getSimpleUmlMM() != null;
      case SimpleUMLPackage.PRIMITIVE_DATA_TYPE__TYPED:
        return typed != null && !typed.isEmpty();
    }
    return eDynamicIsSet(eFeature);
  }

} //PrimitiveDataTypeImpl

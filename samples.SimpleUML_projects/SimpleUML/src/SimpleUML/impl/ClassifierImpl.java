/**
 * <copyright>
 * </copyright>
 *
 * $Id: ClassifierImpl.java,v 1.1 2004-08-10 11:55:40 dvojtise Exp $
 */
package SimpleUML.impl;

import SimpleUML.Attribute;
import SimpleUML.Classifier;
import SimpleUML.SimpleUMLPackage;
import SimpleUML.SimpleUmlMM;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Classifier</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link SimpleUML.impl.ClassifierImpl#getTyped <em>Typed</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class ClassifierImpl extends ModelElementImpl implements Classifier
{
  /**
   * The cached value of the '{@link #getTyped() <em>Typed</em>}' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getTyped()
   * @generated
   * @ordered
   */
  protected EList typed = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected ClassifierImpl()
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
    return SimpleUMLPackage.eINSTANCE.getClassifier();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList getTyped()
  {
    if (typed == null)
    {
      typed = new EObjectWithInverseResolvingEList(Attribute.class, this, SimpleUMLPackage.CLASSIFIER__TYPED, SimpleUMLPackage.ATTRIBUTE__TYPE);
    }
    return typed;
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
        case SimpleUMLPackage.CLASSIFIER__SIMPLE_UML_MM:
          if (eContainer != null)
            msgs = eBasicRemoveFromContainer(msgs);
          return eBasicSetContainer(otherEnd, SimpleUMLPackage.CLASSIFIER__SIMPLE_UML_MM, msgs);
        case SimpleUMLPackage.CLASSIFIER__TYPED:
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
        case SimpleUMLPackage.CLASSIFIER__SIMPLE_UML_MM:
          return eBasicSetContainer(null, SimpleUMLPackage.CLASSIFIER__SIMPLE_UML_MM, msgs);
        case SimpleUMLPackage.CLASSIFIER__TYPED:
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
        case SimpleUMLPackage.CLASSIFIER__SIMPLE_UML_MM:
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
      case SimpleUMLPackage.CLASSIFIER__KIND:
        return getKind();
      case SimpleUMLPackage.CLASSIFIER__NAME:
        return getName();
      case SimpleUMLPackage.CLASSIFIER__SIMPLE_UML_MM:
        return getSimpleUmlMM();
      case SimpleUMLPackage.CLASSIFIER__TYPED:
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
      case SimpleUMLPackage.CLASSIFIER__KIND:
        setKind((String)newValue);
        return;
      case SimpleUMLPackage.CLASSIFIER__NAME:
        setName((String)newValue);
        return;
      case SimpleUMLPackage.CLASSIFIER__SIMPLE_UML_MM:
        setSimpleUmlMM((SimpleUmlMM)newValue);
        return;
      case SimpleUMLPackage.CLASSIFIER__TYPED:
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
      case SimpleUMLPackage.CLASSIFIER__KIND:
        setKind(KIND_EDEFAULT);
        return;
      case SimpleUMLPackage.CLASSIFIER__NAME:
        setName(NAME_EDEFAULT);
        return;
      case SimpleUMLPackage.CLASSIFIER__SIMPLE_UML_MM:
        setSimpleUmlMM((SimpleUmlMM)null);
        return;
      case SimpleUMLPackage.CLASSIFIER__TYPED:
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
      case SimpleUMLPackage.CLASSIFIER__KIND:
        return KIND_EDEFAULT == null ? kind != null : !KIND_EDEFAULT.equals(kind);
      case SimpleUMLPackage.CLASSIFIER__NAME:
        return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
      case SimpleUMLPackage.CLASSIFIER__SIMPLE_UML_MM:
        return getSimpleUmlMM() != null;
      case SimpleUMLPackage.CLASSIFIER__TYPED:
        return typed != null && !typed.isEmpty();
    }
    return eDynamicIsSet(eFeature);
  }

} //ClassifierImpl

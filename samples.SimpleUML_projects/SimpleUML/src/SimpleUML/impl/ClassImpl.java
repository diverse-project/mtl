/**
 * <copyright>
 * </copyright>
 *
 * $Id: ClassImpl.java,v 1.1 2004-08-10 11:55:39 dvojtise Exp $
 */
package SimpleUML.impl;

import SimpleUML.Association;
import SimpleUML.Attribute;
import SimpleUML.SimpleUMLPackage;
import SimpleUML.SimpleUmlMM;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Class</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link SimpleUML.impl.ClassImpl#getAttribute <em>Attribute</em>}</li>
 *   <li>{@link SimpleUML.impl.ClassImpl#getForward <em>Forward</em>}</li>
 *   <li>{@link SimpleUML.impl.ClassImpl#getBackward <em>Backward</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ClassImpl extends ClassifierImpl implements SimpleUML.Class
{
  /**
   * The cached value of the '{@link #getAttribute() <em>Attribute</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getAttribute()
   * @generated
   * @ordered
   */
  protected EList attribute = null;

  /**
   * The cached value of the '{@link #getForward() <em>Forward</em>}' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getForward()
   * @generated
   * @ordered
   */
  protected EList forward = null;

  /**
   * The cached value of the '{@link #getBackward() <em>Backward</em>}' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getBackward()
   * @generated
   * @ordered
   */
  protected EList backward = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected ClassImpl()
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
    return SimpleUMLPackage.eINSTANCE.getClass_();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList getAttribute()
  {
    if (attribute == null)
    {
      attribute = new EObjectContainmentWithInverseEList(Attribute.class, this, SimpleUMLPackage.CLASS__ATTRIBUTE, SimpleUMLPackage.ATTRIBUTE__OWNER);
    }
    return attribute;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList getForward()
  {
    if (forward == null)
    {
      forward = new EObjectWithInverseResolvingEList(Association.class, this, SimpleUMLPackage.CLASS__FORWARD, SimpleUMLPackage.ASSOCIATION__SOURCE);
    }
    return forward;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList getBackward()
  {
    if (backward == null)
    {
      backward = new EObjectWithInverseResolvingEList(Association.class, this, SimpleUMLPackage.CLASS__BACKWARD, SimpleUMLPackage.ASSOCIATION__DESTINATION);
    }
    return backward;
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
        case SimpleUMLPackage.CLASS__SIMPLE_UML_MM:
          if (eContainer != null)
            msgs = eBasicRemoveFromContainer(msgs);
          return eBasicSetContainer(otherEnd, SimpleUMLPackage.CLASS__SIMPLE_UML_MM, msgs);
        case SimpleUMLPackage.CLASS__TYPED:
          return ((InternalEList)getTyped()).basicAdd(otherEnd, msgs);
        case SimpleUMLPackage.CLASS__ATTRIBUTE:
          return ((InternalEList)getAttribute()).basicAdd(otherEnd, msgs);
        case SimpleUMLPackage.CLASS__FORWARD:
          return ((InternalEList)getForward()).basicAdd(otherEnd, msgs);
        case SimpleUMLPackage.CLASS__BACKWARD:
          return ((InternalEList)getBackward()).basicAdd(otherEnd, msgs);
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
        case SimpleUMLPackage.CLASS__SIMPLE_UML_MM:
          return eBasicSetContainer(null, SimpleUMLPackage.CLASS__SIMPLE_UML_MM, msgs);
        case SimpleUMLPackage.CLASS__TYPED:
          return ((InternalEList)getTyped()).basicRemove(otherEnd, msgs);
        case SimpleUMLPackage.CLASS__ATTRIBUTE:
          return ((InternalEList)getAttribute()).basicRemove(otherEnd, msgs);
        case SimpleUMLPackage.CLASS__FORWARD:
          return ((InternalEList)getForward()).basicRemove(otherEnd, msgs);
        case SimpleUMLPackage.CLASS__BACKWARD:
          return ((InternalEList)getBackward()).basicRemove(otherEnd, msgs);
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
        case SimpleUMLPackage.CLASS__SIMPLE_UML_MM:
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
      case SimpleUMLPackage.CLASS__KIND:
        return getKind();
      case SimpleUMLPackage.CLASS__NAME:
        return getName();
      case SimpleUMLPackage.CLASS__SIMPLE_UML_MM:
        return getSimpleUmlMM();
      case SimpleUMLPackage.CLASS__TYPED:
        return getTyped();
      case SimpleUMLPackage.CLASS__ATTRIBUTE:
        return getAttribute();
      case SimpleUMLPackage.CLASS__FORWARD:
        return getForward();
      case SimpleUMLPackage.CLASS__BACKWARD:
        return getBackward();
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
      case SimpleUMLPackage.CLASS__KIND:
        setKind((String)newValue);
        return;
      case SimpleUMLPackage.CLASS__NAME:
        setName((String)newValue);
        return;
      case SimpleUMLPackage.CLASS__SIMPLE_UML_MM:
        setSimpleUmlMM((SimpleUmlMM)newValue);
        return;
      case SimpleUMLPackage.CLASS__TYPED:
        getTyped().clear();
        getTyped().addAll((Collection)newValue);
        return;
      case SimpleUMLPackage.CLASS__ATTRIBUTE:
        getAttribute().clear();
        getAttribute().addAll((Collection)newValue);
        return;
      case SimpleUMLPackage.CLASS__FORWARD:
        getForward().clear();
        getForward().addAll((Collection)newValue);
        return;
      case SimpleUMLPackage.CLASS__BACKWARD:
        getBackward().clear();
        getBackward().addAll((Collection)newValue);
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
      case SimpleUMLPackage.CLASS__KIND:
        setKind(KIND_EDEFAULT);
        return;
      case SimpleUMLPackage.CLASS__NAME:
        setName(NAME_EDEFAULT);
        return;
      case SimpleUMLPackage.CLASS__SIMPLE_UML_MM:
        setSimpleUmlMM((SimpleUmlMM)null);
        return;
      case SimpleUMLPackage.CLASS__TYPED:
        getTyped().clear();
        return;
      case SimpleUMLPackage.CLASS__ATTRIBUTE:
        getAttribute().clear();
        return;
      case SimpleUMLPackage.CLASS__FORWARD:
        getForward().clear();
        return;
      case SimpleUMLPackage.CLASS__BACKWARD:
        getBackward().clear();
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
      case SimpleUMLPackage.CLASS__KIND:
        return KIND_EDEFAULT == null ? kind != null : !KIND_EDEFAULT.equals(kind);
      case SimpleUMLPackage.CLASS__NAME:
        return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
      case SimpleUMLPackage.CLASS__SIMPLE_UML_MM:
        return getSimpleUmlMM() != null;
      case SimpleUMLPackage.CLASS__TYPED:
        return typed != null && !typed.isEmpty();
      case SimpleUMLPackage.CLASS__ATTRIBUTE:
        return attribute != null && !attribute.isEmpty();
      case SimpleUMLPackage.CLASS__FORWARD:
        return forward != null && !forward.isEmpty();
      case SimpleUMLPackage.CLASS__BACKWARD:
        return backward != null && !backward.isEmpty();
    }
    return eDynamicIsSet(eFeature);
  }

} //ClassImpl

/**
 * <copyright>
 * </copyright>
 *
 * $Id: ModelElementImpl.java,v 1.1 2004-08-10 11:55:39 dvojtise Exp $
 */
package SimpleUML.impl;

import SimpleUML.ModelElement;
import SimpleUML.SimpleUMLPackage;
import SimpleUML.SimpleUmlMM;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EcoreUtil;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Model Element</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link SimpleUML.impl.ModelElementImpl#getKind <em>Kind</em>}</li>
 *   <li>{@link SimpleUML.impl.ModelElementImpl#getName <em>Name</em>}</li>
 *   <li>{@link SimpleUML.impl.ModelElementImpl#getSimpleUmlMM <em>Simple Uml MM</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class ModelElementImpl extends EObjectImpl implements ModelElement
{
  /**
   * The default value of the '{@link #getKind() <em>Kind</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getKind()
   * @generated
   * @ordered
   */
  protected static final String KIND_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getKind() <em>Kind</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getKind()
   * @generated
   * @ordered
   */
  protected String kind = KIND_EDEFAULT;

  /**
   * The default value of the '{@link #getName() <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getName()
   * @generated
   * @ordered
   */
  protected static final String NAME_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getName()
   * @generated
   * @ordered
   */
  protected String name = NAME_EDEFAULT;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected ModelElementImpl()
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
    return SimpleUMLPackage.eINSTANCE.getModelElement();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getKind()
  {
    return kind;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setKind(String newKind)
  {
    String oldKind = kind;
    kind = newKind;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SimpleUMLPackage.MODEL_ELEMENT__KIND, oldKind, kind));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getName()
  {
    return name;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setName(String newName)
  {
    String oldName = name;
    name = newName;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SimpleUMLPackage.MODEL_ELEMENT__NAME, oldName, name));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public SimpleUmlMM getSimpleUmlMM()
  {
    if (eContainerFeatureID != SimpleUMLPackage.MODEL_ELEMENT__SIMPLE_UML_MM) return null;
    return (SimpleUmlMM)eContainer;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setSimpleUmlMM(SimpleUmlMM newSimpleUmlMM)
  {
    if (newSimpleUmlMM != eContainer || (eContainerFeatureID != SimpleUMLPackage.MODEL_ELEMENT__SIMPLE_UML_MM && newSimpleUmlMM != null))
    {
      if (EcoreUtil.isAncestor(this, newSimpleUmlMM))
        throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
      NotificationChain msgs = null;
      if (eContainer != null)
        msgs = eBasicRemoveFromContainer(msgs);
      if (newSimpleUmlMM != null)
        msgs = ((InternalEObject)newSimpleUmlMM).eInverseAdd(this, SimpleUMLPackage.SIMPLE_UML_MM__MODEL_ELEMENT, SimpleUmlMM.class, msgs);
      msgs = eBasicSetContainer((InternalEObject)newSimpleUmlMM, SimpleUMLPackage.MODEL_ELEMENT__SIMPLE_UML_MM, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SimpleUMLPackage.MODEL_ELEMENT__SIMPLE_UML_MM, newSimpleUmlMM, newSimpleUmlMM));
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
        case SimpleUMLPackage.MODEL_ELEMENT__SIMPLE_UML_MM:
          if (eContainer != null)
            msgs = eBasicRemoveFromContainer(msgs);
          return eBasicSetContainer(otherEnd, SimpleUMLPackage.MODEL_ELEMENT__SIMPLE_UML_MM, msgs);
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
        case SimpleUMLPackage.MODEL_ELEMENT__SIMPLE_UML_MM:
          return eBasicSetContainer(null, SimpleUMLPackage.MODEL_ELEMENT__SIMPLE_UML_MM, msgs);
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
        case SimpleUMLPackage.MODEL_ELEMENT__SIMPLE_UML_MM:
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
      case SimpleUMLPackage.MODEL_ELEMENT__KIND:
        return getKind();
      case SimpleUMLPackage.MODEL_ELEMENT__NAME:
        return getName();
      case SimpleUMLPackage.MODEL_ELEMENT__SIMPLE_UML_MM:
        return getSimpleUmlMM();
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
      case SimpleUMLPackage.MODEL_ELEMENT__KIND:
        setKind((String)newValue);
        return;
      case SimpleUMLPackage.MODEL_ELEMENT__NAME:
        setName((String)newValue);
        return;
      case SimpleUMLPackage.MODEL_ELEMENT__SIMPLE_UML_MM:
        setSimpleUmlMM((SimpleUmlMM)newValue);
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
      case SimpleUMLPackage.MODEL_ELEMENT__KIND:
        setKind(KIND_EDEFAULT);
        return;
      case SimpleUMLPackage.MODEL_ELEMENT__NAME:
        setName(NAME_EDEFAULT);
        return;
      case SimpleUMLPackage.MODEL_ELEMENT__SIMPLE_UML_MM:
        setSimpleUmlMM((SimpleUmlMM)null);
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
      case SimpleUMLPackage.MODEL_ELEMENT__KIND:
        return KIND_EDEFAULT == null ? kind != null : !KIND_EDEFAULT.equals(kind);
      case SimpleUMLPackage.MODEL_ELEMENT__NAME:
        return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
      case SimpleUMLPackage.MODEL_ELEMENT__SIMPLE_UML_MM:
        return getSimpleUmlMM() != null;
    }
    return eDynamicIsSet(eFeature);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String toString()
  {
    if (eIsProxy()) return super.toString();

    StringBuffer result = new StringBuffer(super.toString());
    result.append(" (kind: ");
    result.append(kind);
    result.append(", name: ");
    result.append(name);
    result.append(')');
    return result.toString();
  }

} //ModelElementImpl

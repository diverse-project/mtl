/**
 * <copyright>
 * </copyright>
 *
 * $Id: AttributeImpl.java,v 1.1 2004-08-10 11:55:39 dvojtise Exp $
 */
package SimpleUML.impl;

import SimpleUML.Attribute;
import SimpleUML.Classifier;
import SimpleUML.SimpleUMLPackage;
import SimpleUML.SimpleUmlMM;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EcoreUtil;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Attribute</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link SimpleUML.impl.AttributeImpl#getType <em>Type</em>}</li>
 *   <li>{@link SimpleUML.impl.AttributeImpl#getOwner <em>Owner</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class AttributeImpl extends ModelElementImpl implements Attribute
{
  /**
   * The cached value of the '{@link #getType() <em>Type</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getType()
   * @generated
   * @ordered
   */
  protected Classifier type = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected AttributeImpl()
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
    return SimpleUMLPackage.eINSTANCE.getAttribute();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Classifier getType()
  {
    if (type != null && type.eIsProxy())
    {
      Classifier oldType = type;
      type = (Classifier)eResolveProxy((InternalEObject)type);
      if (type != oldType)
      {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, SimpleUMLPackage.ATTRIBUTE__TYPE, oldType, type));
      }
    }
    return type;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Classifier basicGetType()
  {
    return type;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetType(Classifier newType, NotificationChain msgs)
  {
    Classifier oldType = type;
    type = newType;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SimpleUMLPackage.ATTRIBUTE__TYPE, oldType, newType);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setType(Classifier newType)
  {
    if (newType != type)
    {
      NotificationChain msgs = null;
      if (type != null)
        msgs = ((InternalEObject)type).eInverseRemove(this, SimpleUMLPackage.CLASSIFIER__TYPED, Classifier.class, msgs);
      if (newType != null)
        msgs = ((InternalEObject)newType).eInverseAdd(this, SimpleUMLPackage.CLASSIFIER__TYPED, Classifier.class, msgs);
      msgs = basicSetType(newType, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SimpleUMLPackage.ATTRIBUTE__TYPE, newType, newType));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public SimpleUML.Class getOwner()
  {
    if (eContainerFeatureID != SimpleUMLPackage.ATTRIBUTE__OWNER) return null;
    return (SimpleUML.Class)eContainer;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setOwner(SimpleUML.Class newOwner)
  {
    if (newOwner != eContainer || (eContainerFeatureID != SimpleUMLPackage.ATTRIBUTE__OWNER && newOwner != null))
    {
      if (EcoreUtil.isAncestor(this, newOwner))
        throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
      NotificationChain msgs = null;
      if (eContainer != null)
        msgs = eBasicRemoveFromContainer(msgs);
      if (newOwner != null)
        msgs = ((InternalEObject)newOwner).eInverseAdd(this, SimpleUMLPackage.CLASS__ATTRIBUTE, SimpleUML.Class.class, msgs);
      msgs = eBasicSetContainer((InternalEObject)newOwner, SimpleUMLPackage.ATTRIBUTE__OWNER, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SimpleUMLPackage.ATTRIBUTE__OWNER, newOwner, newOwner));
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
        case SimpleUMLPackage.ATTRIBUTE__SIMPLE_UML_MM:
          if (eContainer != null)
            msgs = eBasicRemoveFromContainer(msgs);
          return eBasicSetContainer(otherEnd, SimpleUMLPackage.ATTRIBUTE__SIMPLE_UML_MM, msgs);
        case SimpleUMLPackage.ATTRIBUTE__TYPE:
          if (type != null)
            msgs = ((InternalEObject)type).eInverseRemove(this, SimpleUMLPackage.CLASSIFIER__TYPED, Classifier.class, msgs);
          return basicSetType((Classifier)otherEnd, msgs);
        case SimpleUMLPackage.ATTRIBUTE__OWNER:
          if (eContainer != null)
            msgs = eBasicRemoveFromContainer(msgs);
          return eBasicSetContainer(otherEnd, SimpleUMLPackage.ATTRIBUTE__OWNER, msgs);
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
        case SimpleUMLPackage.ATTRIBUTE__SIMPLE_UML_MM:
          return eBasicSetContainer(null, SimpleUMLPackage.ATTRIBUTE__SIMPLE_UML_MM, msgs);
        case SimpleUMLPackage.ATTRIBUTE__TYPE:
          return basicSetType(null, msgs);
        case SimpleUMLPackage.ATTRIBUTE__OWNER:
          return eBasicSetContainer(null, SimpleUMLPackage.ATTRIBUTE__OWNER, msgs);
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
        case SimpleUMLPackage.ATTRIBUTE__SIMPLE_UML_MM:
          return ((InternalEObject)eContainer).eInverseRemove(this, SimpleUMLPackage.SIMPLE_UML_MM__MODEL_ELEMENT, SimpleUmlMM.class, msgs);
        case SimpleUMLPackage.ATTRIBUTE__OWNER:
          return ((InternalEObject)eContainer).eInverseRemove(this, SimpleUMLPackage.CLASS__ATTRIBUTE, SimpleUML.Class.class, msgs);
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
      case SimpleUMLPackage.ATTRIBUTE__KIND:
        return getKind();
      case SimpleUMLPackage.ATTRIBUTE__NAME:
        return getName();
      case SimpleUMLPackage.ATTRIBUTE__SIMPLE_UML_MM:
        return getSimpleUmlMM();
      case SimpleUMLPackage.ATTRIBUTE__TYPE:
        if (resolve) return getType();
        return basicGetType();
      case SimpleUMLPackage.ATTRIBUTE__OWNER:
        return getOwner();
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
      case SimpleUMLPackage.ATTRIBUTE__KIND:
        setKind((String)newValue);
        return;
      case SimpleUMLPackage.ATTRIBUTE__NAME:
        setName((String)newValue);
        return;
      case SimpleUMLPackage.ATTRIBUTE__SIMPLE_UML_MM:
        setSimpleUmlMM((SimpleUmlMM)newValue);
        return;
      case SimpleUMLPackage.ATTRIBUTE__TYPE:
        setType((Classifier)newValue);
        return;
      case SimpleUMLPackage.ATTRIBUTE__OWNER:
        setOwner((SimpleUML.Class)newValue);
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
      case SimpleUMLPackage.ATTRIBUTE__KIND:
        setKind(KIND_EDEFAULT);
        return;
      case SimpleUMLPackage.ATTRIBUTE__NAME:
        setName(NAME_EDEFAULT);
        return;
      case SimpleUMLPackage.ATTRIBUTE__SIMPLE_UML_MM:
        setSimpleUmlMM((SimpleUmlMM)null);
        return;
      case SimpleUMLPackage.ATTRIBUTE__TYPE:
        setType((Classifier)null);
        return;
      case SimpleUMLPackage.ATTRIBUTE__OWNER:
        setOwner((SimpleUML.Class)null);
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
      case SimpleUMLPackage.ATTRIBUTE__KIND:
        return KIND_EDEFAULT == null ? kind != null : !KIND_EDEFAULT.equals(kind);
      case SimpleUMLPackage.ATTRIBUTE__NAME:
        return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
      case SimpleUMLPackage.ATTRIBUTE__SIMPLE_UML_MM:
        return getSimpleUmlMM() != null;
      case SimpleUMLPackage.ATTRIBUTE__TYPE:
        return type != null;
      case SimpleUMLPackage.ATTRIBUTE__OWNER:
        return getOwner() != null;
    }
    return eDynamicIsSet(eFeature);
  }

} //AttributeImpl

/**
 * <copyright>
 * </copyright>
 *
 * $Id: AssociationImpl.java,v 1.1 2004-08-10 11:55:38 dvojtise Exp $
 */
package SimpleUML.impl;

import SimpleUML.Association;
import SimpleUML.SimpleUMLPackage;
import SimpleUML.SimpleUmlMM;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Association</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link SimpleUML.impl.AssociationImpl#getSource <em>Source</em>}</li>
 *   <li>{@link SimpleUML.impl.AssociationImpl#getDestination <em>Destination</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class AssociationImpl extends ModelElementImpl implements Association
{
  /**
   * The cached value of the '{@link #getSource() <em>Source</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getSource()
   * @generated
   * @ordered
   */
  protected SimpleUML.Class source = null;

  /**
   * The cached value of the '{@link #getDestination() <em>Destination</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getDestination()
   * @generated
   * @ordered
   */
  protected SimpleUML.Class destination = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected AssociationImpl()
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
    return SimpleUMLPackage.eINSTANCE.getAssociation();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public SimpleUML.Class getSource()
  {
    if (source != null && source.eIsProxy())
    {
      SimpleUML.Class oldSource = source;
      source = (SimpleUML.Class)eResolveProxy((InternalEObject)source);
      if (source != oldSource)
      {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, SimpleUMLPackage.ASSOCIATION__SOURCE, oldSource, source));
      }
    }
    return source;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public SimpleUML.Class basicGetSource()
  {
    return source;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetSource(SimpleUML.Class newSource, NotificationChain msgs)
  {
    SimpleUML.Class oldSource = source;
    source = newSource;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SimpleUMLPackage.ASSOCIATION__SOURCE, oldSource, newSource);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setSource(SimpleUML.Class newSource)
  {
    if (newSource != source)
    {
      NotificationChain msgs = null;
      if (source != null)
        msgs = ((InternalEObject)source).eInverseRemove(this, SimpleUMLPackage.CLASS__FORWARD, SimpleUML.Class.class, msgs);
      if (newSource != null)
        msgs = ((InternalEObject)newSource).eInverseAdd(this, SimpleUMLPackage.CLASS__FORWARD, SimpleUML.Class.class, msgs);
      msgs = basicSetSource(newSource, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SimpleUMLPackage.ASSOCIATION__SOURCE, newSource, newSource));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public SimpleUML.Class getDestination()
  {
    if (destination != null && destination.eIsProxy())
    {
      SimpleUML.Class oldDestination = destination;
      destination = (SimpleUML.Class)eResolveProxy((InternalEObject)destination);
      if (destination != oldDestination)
      {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, SimpleUMLPackage.ASSOCIATION__DESTINATION, oldDestination, destination));
      }
    }
    return destination;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public SimpleUML.Class basicGetDestination()
  {
    return destination;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetDestination(SimpleUML.Class newDestination, NotificationChain msgs)
  {
    SimpleUML.Class oldDestination = destination;
    destination = newDestination;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SimpleUMLPackage.ASSOCIATION__DESTINATION, oldDestination, newDestination);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setDestination(SimpleUML.Class newDestination)
  {
    if (newDestination != destination)
    {
      NotificationChain msgs = null;
      if (destination != null)
        msgs = ((InternalEObject)destination).eInverseRemove(this, SimpleUMLPackage.CLASS__BACKWARD, SimpleUML.Class.class, msgs);
      if (newDestination != null)
        msgs = ((InternalEObject)newDestination).eInverseAdd(this, SimpleUMLPackage.CLASS__BACKWARD, SimpleUML.Class.class, msgs);
      msgs = basicSetDestination(newDestination, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SimpleUMLPackage.ASSOCIATION__DESTINATION, newDestination, newDestination));
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
        case SimpleUMLPackage.ASSOCIATION__SIMPLE_UML_MM:
          if (eContainer != null)
            msgs = eBasicRemoveFromContainer(msgs);
          return eBasicSetContainer(otherEnd, SimpleUMLPackage.ASSOCIATION__SIMPLE_UML_MM, msgs);
        case SimpleUMLPackage.ASSOCIATION__SOURCE:
          if (source != null)
            msgs = ((InternalEObject)source).eInverseRemove(this, SimpleUMLPackage.CLASS__FORWARD, SimpleUML.Class.class, msgs);
          return basicSetSource((SimpleUML.Class)otherEnd, msgs);
        case SimpleUMLPackage.ASSOCIATION__DESTINATION:
          if (destination != null)
            msgs = ((InternalEObject)destination).eInverseRemove(this, SimpleUMLPackage.CLASS__BACKWARD, SimpleUML.Class.class, msgs);
          return basicSetDestination((SimpleUML.Class)otherEnd, msgs);
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
        case SimpleUMLPackage.ASSOCIATION__SIMPLE_UML_MM:
          return eBasicSetContainer(null, SimpleUMLPackage.ASSOCIATION__SIMPLE_UML_MM, msgs);
        case SimpleUMLPackage.ASSOCIATION__SOURCE:
          return basicSetSource(null, msgs);
        case SimpleUMLPackage.ASSOCIATION__DESTINATION:
          return basicSetDestination(null, msgs);
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
        case SimpleUMLPackage.ASSOCIATION__SIMPLE_UML_MM:
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
      case SimpleUMLPackage.ASSOCIATION__KIND:
        return getKind();
      case SimpleUMLPackage.ASSOCIATION__NAME:
        return getName();
      case SimpleUMLPackage.ASSOCIATION__SIMPLE_UML_MM:
        return getSimpleUmlMM();
      case SimpleUMLPackage.ASSOCIATION__SOURCE:
        if (resolve) return getSource();
        return basicGetSource();
      case SimpleUMLPackage.ASSOCIATION__DESTINATION:
        if (resolve) return getDestination();
        return basicGetDestination();
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
      case SimpleUMLPackage.ASSOCIATION__KIND:
        setKind((String)newValue);
        return;
      case SimpleUMLPackage.ASSOCIATION__NAME:
        setName((String)newValue);
        return;
      case SimpleUMLPackage.ASSOCIATION__SIMPLE_UML_MM:
        setSimpleUmlMM((SimpleUmlMM)newValue);
        return;
      case SimpleUMLPackage.ASSOCIATION__SOURCE:
        setSource((SimpleUML.Class)newValue);
        return;
      case SimpleUMLPackage.ASSOCIATION__DESTINATION:
        setDestination((SimpleUML.Class)newValue);
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
      case SimpleUMLPackage.ASSOCIATION__KIND:
        setKind(KIND_EDEFAULT);
        return;
      case SimpleUMLPackage.ASSOCIATION__NAME:
        setName(NAME_EDEFAULT);
        return;
      case SimpleUMLPackage.ASSOCIATION__SIMPLE_UML_MM:
        setSimpleUmlMM((SimpleUmlMM)null);
        return;
      case SimpleUMLPackage.ASSOCIATION__SOURCE:
        setSource((SimpleUML.Class)null);
        return;
      case SimpleUMLPackage.ASSOCIATION__DESTINATION:
        setDestination((SimpleUML.Class)null);
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
      case SimpleUMLPackage.ASSOCIATION__KIND:
        return KIND_EDEFAULT == null ? kind != null : !KIND_EDEFAULT.equals(kind);
      case SimpleUMLPackage.ASSOCIATION__NAME:
        return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
      case SimpleUMLPackage.ASSOCIATION__SIMPLE_UML_MM:
        return getSimpleUmlMM() != null;
      case SimpleUMLPackage.ASSOCIATION__SOURCE:
        return source != null;
      case SimpleUMLPackage.ASSOCIATION__DESTINATION:
        return destination != null;
    }
    return eDynamicIsSet(eFeature);
  }

} //AssociationImpl

/**
 * <copyright>
 * </copyright>
 *
 * $Id: ModelElement.java,v 1.1 2004-08-10 11:55:37 dvojtise Exp $
 */
package SimpleUML;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Model Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link SimpleUML.ModelElement#getKind <em>Kind</em>}</li>
 *   <li>{@link SimpleUML.ModelElement#getName <em>Name</em>}</li>
 *   <li>{@link SimpleUML.ModelElement#getSimpleUmlMM <em>Simple Uml MM</em>}</li>
 * </ul>
 * </p>
 *
 * @see SimpleUML.SimpleUMLPackage#getModelElement()
 * @model abstract="true"
 * @generated
 */
public interface ModelElement extends EObject
{
  /**
   * Returns the value of the '<em><b>Kind</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Kind</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Kind</em>' attribute.
   * @see #setKind(String)
   * @see SimpleUML.SimpleUMLPackage#getModelElement_Kind()
   * @model 
   * @generated
   */
  String getKind();

  /**
   * Sets the value of the '{@link SimpleUML.ModelElement#getKind <em>Kind</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Kind</em>' attribute.
   * @see #getKind()
   * @generated
   */
  void setKind(String value);

  /**
   * Returns the value of the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Name</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Name</em>' attribute.
   * @see #setName(String)
   * @see SimpleUML.SimpleUMLPackage#getModelElement_Name()
   * @model 
   * @generated
   */
  String getName();

  /**
   * Sets the value of the '{@link SimpleUML.ModelElement#getName <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Name</em>' attribute.
   * @see #getName()
   * @generated
   */
  void setName(String value);

  /**
   * Returns the value of the '<em><b>Simple Uml MM</b></em>' container reference.
   * It is bidirectional and its opposite is '{@link SimpleUML.SimpleUmlMM#getModelElement <em>Model Element</em>}'.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Simple Uml MM</em>' container reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Simple Uml MM</em>' container reference.
   * @see #setSimpleUmlMM(SimpleUmlMM)
   * @see SimpleUML.SimpleUMLPackage#getModelElement_SimpleUmlMM()
   * @see SimpleUML.SimpleUmlMM#getModelElement
   * @model opposite="modelElement"
   * @generated
   */
  SimpleUmlMM getSimpleUmlMM();

  /**
   * Sets the value of the '{@link SimpleUML.ModelElement#getSimpleUmlMM <em>Simple Uml MM</em>}' container reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Simple Uml MM</em>' container reference.
   * @see #getSimpleUmlMM()
   * @generated
   */
  void setSimpleUmlMM(SimpleUmlMM value);

} // ModelElement

/**
 * <copyright>
 * </copyright>
 *
 * $Id: SimpleUmlMM.java,v 1.1 2004-08-10 11:55:36 dvojtise Exp $
 */
package SimpleUML;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Simple Uml MM</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link SimpleUML.SimpleUmlMM#getModelElement <em>Model Element</em>}</li>
 * </ul>
 * </p>
 *
 * @see SimpleUML.SimpleUMLPackage#getSimpleUmlMM()
 * @model 
 * @generated
 */
public interface SimpleUmlMM extends EObject
{
  /**
   * Returns the value of the '<em><b>Model Element</b></em>' containment reference list.
   * The list contents are of type {@link SimpleUML.ModelElement}.
   * It is bidirectional and its opposite is '{@link SimpleUML.ModelElement#getSimpleUmlMM <em>Simple Uml MM</em>}'.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Model Element</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Model Element</em>' containment reference list.
   * @see SimpleUML.SimpleUMLPackage#getSimpleUmlMM_ModelElement()
   * @see SimpleUML.ModelElement#getSimpleUmlMM
   * @model type="SimpleUML.ModelElement" opposite="simpleUmlMM" containment="true"
   * @generated
   */
  EList getModelElement();

} // SimpleUmlMM

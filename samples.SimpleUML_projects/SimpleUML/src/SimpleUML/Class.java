/**
 * <copyright>
 * </copyright>
 *
 * $Id: Class.java,v 1.1 2004-08-10 11:55:37 dvojtise Exp $
 */
package SimpleUML;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Class</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link SimpleUML.Class#getAttribute <em>Attribute</em>}</li>
 *   <li>{@link SimpleUML.Class#getForward <em>Forward</em>}</li>
 *   <li>{@link SimpleUML.Class#getBackward <em>Backward</em>}</li>
 * </ul>
 * </p>
 *
 * @see SimpleUML.SimpleUMLPackage#getClass_()
 * @model 
 * @generated
 */
public interface Class extends Classifier
{
  /**
   * Returns the value of the '<em><b>Attribute</b></em>' containment reference list.
   * The list contents are of type {@link SimpleUML.Attribute}.
   * It is bidirectional and its opposite is '{@link SimpleUML.Attribute#getOwner <em>Owner</em>}'.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Attribute</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Attribute</em>' containment reference list.
   * @see SimpleUML.SimpleUMLPackage#getClass_Attribute()
   * @see SimpleUML.Attribute#getOwner
   * @model type="SimpleUML.Attribute" opposite="owner" containment="true"
   * @generated
   */
  EList getAttribute();

  /**
   * Returns the value of the '<em><b>Forward</b></em>' reference list.
   * The list contents are of type {@link SimpleUML.Association}.
   * It is bidirectional and its opposite is '{@link SimpleUML.Association#getSource <em>Source</em>}'.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Forward</em>' reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Forward</em>' reference list.
   * @see SimpleUML.SimpleUMLPackage#getClass_Forward()
   * @see SimpleUML.Association#getSource
   * @model type="SimpleUML.Association" opposite="source"
   * @generated
   */
  EList getForward();

  /**
   * Returns the value of the '<em><b>Backward</b></em>' reference list.
   * The list contents are of type {@link SimpleUML.Association}.
   * It is bidirectional and its opposite is '{@link SimpleUML.Association#getDestination <em>Destination</em>}'.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Backward</em>' reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Backward</em>' reference list.
   * @see SimpleUML.SimpleUMLPackage#getClass_Backward()
   * @see SimpleUML.Association#getDestination
   * @model type="SimpleUML.Association" opposite="destination"
   * @generated
   */
  EList getBackward();

} // Class

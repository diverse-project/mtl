/**
 * <copyright>
 * </copyright>
 *
 * $Id: Association.java,v 1.1 2004-08-10 11:55:37 dvojtise Exp $
 */
package SimpleUML;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Association</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link SimpleUML.Association#getSource <em>Source</em>}</li>
 *   <li>{@link SimpleUML.Association#getDestination <em>Destination</em>}</li>
 * </ul>
 * </p>
 *
 * @see SimpleUML.SimpleUMLPackage#getAssociation()
 * @model 
 * @generated
 */
public interface Association extends ModelElement
{
  /**
   * Returns the value of the '<em><b>Source</b></em>' reference.
   * It is bidirectional and its opposite is '{@link SimpleUML.Class#getForward <em>Forward</em>}'.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Source</em>' reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Source</em>' reference.
   * @see #setSource(SimpleUML.Class)
   * @see SimpleUML.SimpleUMLPackage#getAssociation_Source()
   * @see SimpleUML.Class#getForward
   * @model opposite="forward" required="true"
   * @generated
   */
  SimpleUML.Class getSource();

  /**
   * Sets the value of the '{@link SimpleUML.Association#getSource <em>Source</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Source</em>' reference.
   * @see #getSource()
   * @generated
   */
  void setSource(SimpleUML.Class value);

  /**
   * Returns the value of the '<em><b>Destination</b></em>' reference.
   * It is bidirectional and its opposite is '{@link SimpleUML.Class#getBackward <em>Backward</em>}'.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Destination</em>' reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Destination</em>' reference.
   * @see #setDestination(SimpleUML.Class)
   * @see SimpleUML.SimpleUMLPackage#getAssociation_Destination()
   * @see SimpleUML.Class#getBackward
   * @model opposite="backward" required="true"
   * @generated
   */
  SimpleUML.Class getDestination();

  /**
   * Sets the value of the '{@link SimpleUML.Association#getDestination <em>Destination</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Destination</em>' reference.
   * @see #getDestination()
   * @generated
   */
  void setDestination(SimpleUML.Class value);

} // Association

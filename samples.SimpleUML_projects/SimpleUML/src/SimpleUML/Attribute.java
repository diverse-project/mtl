/**
 * <copyright>
 * </copyright>
 *
 * $Id: Attribute.java,v 1.1 2004-08-10 11:55:36 dvojtise Exp $
 */
package SimpleUML;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Attribute</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link SimpleUML.Attribute#getType <em>Type</em>}</li>
 *   <li>{@link SimpleUML.Attribute#getOwner <em>Owner</em>}</li>
 * </ul>
 * </p>
 *
 * @see SimpleUML.SimpleUMLPackage#getAttribute()
 * @model 
 * @generated
 */
public interface Attribute extends ModelElement
{
  /**
   * Returns the value of the '<em><b>Type</b></em>' reference.
   * It is bidirectional and its opposite is '{@link SimpleUML.Classifier#getTyped <em>Typed</em>}'.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Type</em>' reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Type</em>' reference.
   * @see #setType(Classifier)
   * @see SimpleUML.SimpleUMLPackage#getAttribute_Type()
   * @see SimpleUML.Classifier#getTyped
   * @model opposite="typed" required="true"
   * @generated
   */
  Classifier getType();

  /**
   * Sets the value of the '{@link SimpleUML.Attribute#getType <em>Type</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Type</em>' reference.
   * @see #getType()
   * @generated
   */
  void setType(Classifier value);

  /**
   * Returns the value of the '<em><b>Owner</b></em>' container reference.
   * It is bidirectional and its opposite is '{@link SimpleUML.Class#getAttribute <em>Attribute</em>}'.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Owner</em>' container reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Owner</em>' container reference.
   * @see #setOwner(SimpleUML.Class)
   * @see SimpleUML.SimpleUMLPackage#getAttribute_Owner()
   * @see SimpleUML.Class#getAttribute
   * @model opposite="attribute"
   * @generated
   */
  SimpleUML.Class getOwner();

  /**
   * Sets the value of the '{@link SimpleUML.Attribute#getOwner <em>Owner</em>}' container reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Owner</em>' container reference.
   * @see #getOwner()
   * @generated
   */
  void setOwner(SimpleUML.Class value);

} // Attribute

/**
 * <copyright>
 * </copyright>
 *
 * $Id: Classifier.java,v 1.1 2004-08-10 11:55:36 dvojtise Exp $
 */
package SimpleUML;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Classifier</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link SimpleUML.Classifier#getTyped <em>Typed</em>}</li>
 * </ul>
 * </p>
 *
 * @see SimpleUML.SimpleUMLPackage#getClassifier()
 * @model abstract="true"
 * @generated
 */
public interface Classifier extends ModelElement
{
  /**
   * Returns the value of the '<em><b>Typed</b></em>' reference list.
   * The list contents are of type {@link SimpleUML.Attribute}.
   * It is bidirectional and its opposite is '{@link SimpleUML.Attribute#getType <em>Type</em>}'.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Typed</em>' reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Typed</em>' reference list.
   * @see SimpleUML.SimpleUMLPackage#getClassifier_Typed()
   * @see SimpleUML.Attribute#getType
   * @model type="SimpleUML.Attribute" opposite="type"
   * @generated
   */
  EList getTyped();

} // Classifier

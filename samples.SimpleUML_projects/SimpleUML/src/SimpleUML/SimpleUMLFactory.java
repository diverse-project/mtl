/**
 * <copyright>
 * </copyright>
 *
 * $Id: SimpleUMLFactory.java,v 1.1 2004-08-10 11:55:37 dvojtise Exp $
 */
package SimpleUML;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see SimpleUML.SimpleUMLPackage
 * @generated
 */
public interface SimpleUMLFactory extends EFactory
{
  /**
   * The singleton instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  SimpleUMLFactory eINSTANCE = new SimpleUML.impl.SimpleUMLFactoryImpl();

  /**
   * Returns a new object of class '<em>Class</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Class</em>'.
   * @generated
   */
  SimpleUML.Class createClass();

  /**
   * Returns a new object of class '<em>Primitive Data Type</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Primitive Data Type</em>'.
   * @generated
   */
  PrimitiveDataType createPrimitiveDataType();

  /**
   * Returns a new object of class '<em>Attribute</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Attribute</em>'.
   * @generated
   */
  Attribute createAttribute();

  /**
   * Returns a new object of class '<em>Association</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Association</em>'.
   * @generated
   */
  Association createAssociation();

  /**
   * Returns a new object of class '<em>Simple Uml MM</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Simple Uml MM</em>'.
   * @generated
   */
  SimpleUmlMM createSimpleUmlMM();

  /**
   * Returns the package supported by this factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the package supported by this factory.
   * @generated
   */
  SimpleUMLPackage getSimpleUMLPackage();

} //SimpleUMLFactory

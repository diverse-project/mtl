/*
 * Created on 16.05.2003
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package org.irisa.triskell.MT.DataTypes.Test;

import junit.framework.TestCase;
import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.*;

/**
 * @author rumpe
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class StringValueImplTest extends TestCase {

	StringValueImpl svi1;
	
	public void setUp() {
		svi1 = new StringValueImpl(true,"errMess","strVal");
	}

	public void testCreateGetString() {
	  String s = svi1.getTheString();
	  assertEquals(s,"strVal");
	 }
 
	public void testEquals1() {
	  assertTrue(svi1.equals(svi1));
	 }

	public void testEquals2() {
		StringValueImpl svi2 = new StringValueImpl(true,"errMess","strVal");	  
	  assertTrue(svi1.equals(svi2));
	 }

	public void testEquals3() {
	  StringValueImpl svi2 = new StringValueImpl(true,"fgdh   ","strVal");	  
	  assertFalse(svi1.equals(svi2));
	 }

	public void testEquals4() {
	  StringValueImpl svi3 = new StringValueImpl(false,"errMess","strVal");
	  StringValueImpl svi2 = new StringValueImpl(false,"fgdh   ","strVal");	  
	  assertTrue(svi3.equals(svi2));
	 }

	public void testEquals5() {
	  StringValueImpl svi2 = new StringValueImpl(true,"errMess","------");	  
	  assertFalse(svi1.equals(svi2));
	 }

	public void testEquals6() {
	  StringValueImpl svi2 = new StringValueImpl(false,"errMess","------");	  
	  assertFalse(svi1.equals(svi2));
	 }

	public void testEquals7() {
	  StringValueImpl svi2 = new StringValueImpl(false,".......","------");	  
	  assertFalse(svi1.equals(svi2));
	 }

	public void testEquals8() {
		StringValueImpl svi2 = new StringValueImpl(false,"errMess","strVal");	  
		assertFalse(svi1.equals(svi2));
	 }


	public void testAccept() {
		ValueVisitorDummy vis = new ValueVisitorDummy(); 
		svi1.accept(vis);
		assertTrue(vis.calledVisitStringValue);
	 }

}

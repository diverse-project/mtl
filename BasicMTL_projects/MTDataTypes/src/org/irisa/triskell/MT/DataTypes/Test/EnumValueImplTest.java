/*
 * Created on 20.05.2003
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
public class EnumValueImplTest extends TestCase {

	public void testGetters() {
		String[] slist = new String[] { "e1", "e2", "e3" };
		EnumValueImpl evi = new EnumValueImpl(false,"errMess","enumVal",slist);	  
		assertEquals(evi.getValue(),"enumVal");
		assertEquals(evi.getEnumeration(),slist);
	 }

	public void testAccept() {
		String[] slist = new String[] { "e1", "e2", "e3" };
		EnumValueImpl evi = new EnumValueImpl(false,"errMess","enumVal",slist);	  
		ValueVisitorDummy vis = new ValueVisitorDummy(); 
		evi.accept(vis);
		assertTrue(vis.calledVisitEnumValue);

		// This is just to check that not all are true:
		assertFalse(vis.calledVisitStringValue);
	 }

}

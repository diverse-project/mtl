/*
 * Created on May 22, 2003
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package org.irisa.triskell.MT.DataTypes.Test;

import junit.framework.TestCase;
import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.*;
import org.irisa.triskell.MT.DataTypes.Java.*;

/**
 * @author ffondeme
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class ValueImplTest extends TestCase {
	protected StringValueImpl value1 = new StringValueImpl(false, null, "toto");
	protected StringValueImpl value2 = new StringValueImpl(false, null, "toto");
	protected StringValueImpl valueA = new StringValueImpl(false, null, "truc");
	protected StringValueImpl valueUndef1 = new StringValueImpl(true, "ErrMsg1", "val1");
	protected StringValueImpl valueUndef2 = new StringValueImpl(true, "ErrMsg1", "val2");
	protected StringValueImpl valueUndefA = new StringValueImpl(true, "ErrMsg2", "valA");
	
	protected BooleanValueImpl ok = new BooleanValueImpl(false, null, true);
	protected BooleanValueImpl nok = new BooleanValueImpl(false, null, false);
	protected BooleanValueImpl undefFalse = new BooleanValueImpl(true, null, false);
	protected BooleanValueImpl undefTrue = new BooleanValueImpl(true, null, true);
	
	public void testInvoke () {
		try {
			assertEquals(value1.invoke(null, "=", new Value[]{value2}, null), ok);
			assertEquals(value1.invoke(null, "<>", new Value[]{value2}, null), nok);
		} catch (Exception x) {
			throw new RuntimeException(x);
		}
	}

	public void testBmtlEquals () {
		Value ret;
		
		ret = value1.bmtl_equals(value1);
		assertFalse(ret.isUndefined());
		assertTrue(((BooleanValue)ret).getTheBoolean());
		assertEquals(ret,ok);
		
		ret = value1.bmtl_equals(value2);
		assertFalse(ret.isUndefined());
		assertTrue(((BooleanValue)ret).getTheBoolean());
		assertEquals(ret,ok);
		
		ret = value1.bmtl_equals(valueA);
		assertFalse(ret.isUndefined());
		assertFalse(((BooleanValue)ret).getTheBoolean());
		assertEquals(ret,nok);
		
		ret = value1.bmtl_equals(valueUndef1);
		assertTrue(ret.isUndefined());
		
		ret = valueUndef1.bmtl_equals(valueUndef2);
		assertTrue(ret.isUndefined());
		
		ret = valueUndef1.bmtl_equals(valueUndefA);
		assertTrue(ret.isUndefined());
		
		ret = valueUndef1.bmtl_equals(value1);
		assertTrue(ret.isUndefined());
	}

	public void testBmtlNotEquals () {
		Value ret;
		
		ret = value1.bmtl_notEquals(value1);
		assertFalse(ret.isUndefined());
		assertFalse(((BooleanValue)ret).getTheBoolean());
		assertEquals(ret,nok);
		
		ret = value1.bmtl_notEquals(value2);
		assertFalse(ret.isUndefined());
		assertFalse(((BooleanValue)ret).getTheBoolean());
		assertEquals(ret,nok);
		
		ret = value1.bmtl_notEquals(valueA);
		assertFalse(ret.isUndefined());
		assertTrue(((BooleanValue)ret).getTheBoolean());
		assertEquals(ret,ok);
		
		ret = value1.bmtl_notEquals(valueUndef1);
		assertTrue(ret.isUndefined());
		
		ret = valueUndef1.bmtl_notEquals(valueUndef2);
		assertTrue(ret.isUndefined());
		
		ret = valueUndef1.bmtl_notEquals(valueUndefA);
		assertTrue(ret.isUndefined());
		
		ret = valueUndef1.bmtl_notEquals(value1);
		assertTrue(ret.isUndefined());
	}
}

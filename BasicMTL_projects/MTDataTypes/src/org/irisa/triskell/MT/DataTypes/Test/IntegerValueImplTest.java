/*
 * Created on 29 juil. 2003
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package org.irisa.triskell.MT.DataTypes.Test;

import org.irisa.triskell.MT.DataTypes.Java.IntegerValue;
import org.irisa.triskell.MT.DataTypes.Java.RealValue;
import org.irisa.triskell.MT.DataTypes.Java.Value;
import org.irisa.triskell.MT.DataTypes.Java.commands.MultipleCommandException;
import org.irisa.triskell.MT.DataTypes.Java.commands.UnknownCommandException;
import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.BooleanValueImpl;
import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.IntegerValueImpl;
import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.RealValueImpl;

import junit.framework.TestCase;

/**
 * @author ffondeme
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class IntegerValueImplTest extends TestCase {
	public IntegerValue one = new IntegerValueImpl(false, null, 1);
	public IntegerValue two = new IntegerValueImpl(false, null, 2);
	public IntegerValue three = new IntegerValueImpl(false, null, 3);
	public IntegerValue zero = new IntegerValueImpl(false, null, 0);
	public IntegerValue minusOne = new IntegerValueImpl(false, null, -1);
	public IntegerValue minusTwo = new IntegerValueImpl(false, null, -2);
	public IntegerValue twentyNine = new IntegerValueImpl(false, null, 29);
	public IntegerValue minusTwentyNine = new IntegerValueImpl(false, null, -29);
	public IntegerValue four = new IntegerValueImpl(false, null, 4);
	public IntegerValue minusFour = new IntegerValueImpl(false, null, -4);
	public RealValue rzero = new RealValueImpl(false, null, 0);
	public RealValue rone = new RealValueImpl(false, null, 1); 
	public RealValue rhalf = new RealValueImpl(false, null, (float)(1.0/2.0));
	public RealValue rthrird = new RealValueImpl(false, null, (float)(1.0/3.0)); 
	
	public void testEquals0 () {
		IntegerValueImpl z = new IntegerValueImpl(false, null, 0);
		try {
			assertTrue(((BooleanValueImpl)z.invoke(null, "=", new Value [] {zero}, null)).getTheBoolean());
			assertFalse(((BooleanValueImpl)z.invoke(null, "<>", new Value [] {zero}, null)).getTheBoolean());
			assertTrue(((BooleanValueImpl)z.invoke(null, "=", new Value [] {rzero}, null)).getTheBoolean());
			assertFalse(((BooleanValueImpl)z.invoke(null, "<>", new Value [] {rzero}, null)).getTheBoolean());
			assertFalse(((BooleanValueImpl)z.invoke(null, "=", new Value [] {one}, null)).getTheBoolean());
			assertTrue(((BooleanValueImpl)z.invoke(null, "<>", new Value [] {one}, null)).getTheBoolean());
			assertFalse(((BooleanValueImpl)z.invoke(null, "=", new Value [] {rone}, null)).getTheBoolean());
			assertTrue(((BooleanValueImpl)z.invoke(null, "<>", new Value [] {rone}, null)).getTheBoolean());
		} catch (MultipleCommandException x) {
			fail(x.getMessage());
			x.printStackTrace();
		} catch (UnknownCommandException x) {
			fail(x.getMessage());
			x.printStackTrace();
		}
	}
	
	public void testEquals1 () {
		try {
			assertFalse(((BooleanValueImpl)zero.invoke(null, "=", new Value [] {one}, null)).getTheBoolean());
			assertTrue(((BooleanValueImpl)zero.invoke(null, "<>", new Value [] {one}, null)).getTheBoolean());
			assertFalse(((BooleanValueImpl)zero.invoke(null, "=", new Value [] {two}, null)).getTheBoolean());
			assertTrue(((BooleanValueImpl)zero.invoke(null, "<>", new Value [] {two}, null)).getTheBoolean());
			assertFalse(((BooleanValueImpl)one.invoke(null, "=", new Value [] {zero}, null)).getTheBoolean());
			assertTrue(((BooleanValueImpl)one.invoke(null, "<>", new Value [] {two}, null)).getTheBoolean());
		} catch (MultipleCommandException x) {
			fail(x.getMessage());
			x.printStackTrace();
		} catch (UnknownCommandException x) {
			fail(x.getMessage());
			x.printStackTrace();
		}
	}
	
	public void testOps () {
		try {
			assertEquals(zero.invoke(null, "+", null, null), (zero));
			assertEquals(zero.invoke(null, "-", null, null), (zero));
			assertEquals(one.invoke(null, "+", null, null), (one));
			assertEquals(minusOne.invoke(null, "-", null, null), (one));
			
			assertEquals(zero.invoke(null, "+", new Value [] {zero}, null), zero);
			assertEquals(one.invoke(null, "+", new Value [] {zero}, null), one);
			assertEquals(zero.invoke(null, "+", new Value [] {one}, null), one);
			assertEquals(one.invoke(null, "+", new Value [] {one}, null), two);
			assertEquals(minusOne.invoke(null, "+", new Value [] {one}, null), zero);
			assertEquals(minusOne.invoke(null, "+", new Value [] {minusOne}, null), minusTwo);
			
			assertEquals(zero.invoke(null, "-", new Value [] {zero}, null), zero);
			assertEquals(one.invoke(null, "-", new Value [] {zero}, null), one);
			assertEquals(zero.invoke(null, "-", new Value [] {one}, null), minusOne);
			assertEquals(one.invoke(null, "-", new Value [] {one}, null), zero);
			assertEquals(minusOne.invoke(null, "-", new Value [] {one}, null), minusTwo);
			assertEquals(minusOne.invoke(null, "-", new Value [] {minusOne}, null), zero);
			
			assertEquals(zero.invoke(null, "*", new Value [] {zero}, null), zero);
			assertEquals(one.invoke(null, "*", new Value [] {zero}, null), zero);
			assertEquals(zero.invoke(null, "*", new Value [] {one}, null), zero);
			assertEquals(one.invoke(null, "*", new Value [] {one}, null), one);
			assertEquals(minusOne.invoke(null, "*", new Value [] {one}, null), minusOne);
			assertEquals(minusOne.invoke(null, "*", new Value [] {minusOne}, null), one);
			assertEquals(two.invoke(null, "*", new Value [] {two}, null), four);
			assertEquals(two.invoke(null, "*", new Value [] {minusTwo}, null), minusFour);
			
			assertTrue(zero.invoke(null, "/", new Value [] {zero}, null).isUndefined());
			assertTrue(one.invoke(null, "/", new Value [] {zero}, null).isUndefined());
			assertEquals(zero.invoke(null, "/", new Value [] {one}, null), zero);
			assertEquals(one.invoke(null, "/", new Value [] {one}, null), one);
			assertEquals(minusOne.invoke(null, "/", new Value [] {one}, null), minusOne);
			assertEquals(minusOne.invoke(null, "/", new Value [] {minusOne}, null), one);
			assertEquals(two.invoke(null, "/", new Value [] {two}, null), one);
			assertEquals(four.invoke(null, "/", new Value [] {two}, null), two);
			assertEquals(minusFour.invoke(null, "/", new Value [] {minusTwo}, null), two);
			assertEquals(one.invoke(null, "/", new Value [] {two}, null), rhalf);
			assertEquals(one.invoke(null, "/", new Value [] {three}, null), rthrird);
			
			assertTrue(zero.invoke(null, "div", new Value [] {zero}, null).isUndefined());
			assertTrue(one.invoke(null, "div", new Value [] {zero}, null).isUndefined());
			assertEquals(zero.invoke(null, "div", new Value [] {one}, null), zero);
			assertEquals(one.invoke(null, "div", new Value [] {one}, null), one);
			assertEquals(minusOne.invoke(null, "div", new Value [] {one}, null), minusOne);
			assertEquals(minusOne.invoke(null, "div", new Value [] {minusOne}, null), one);
			assertEquals(two.invoke(null, "div", new Value [] {two}, null), one);
			assertEquals(four.invoke(null, "div", new Value [] {two}, null), two);
			assertEquals(minusFour.invoke(null, "div", new Value [] {minusTwo}, null), two);
			assertEquals(one.invoke(null, "div", new Value [] {two}, null), zero);
			assertEquals(one.invoke(null, "div", new Value [] {three}, null), zero);
			assertEquals(four.invoke(null, "div", new Value [] {three}, null), one);
			assertEquals(minusFour.invoke(null, "div", new Value [] {three}, null), minusOne);
			
			assertEquals(zero.invoke(null, "mod", new Value [] {zero}, null), zero);
			assertTrue(one.invoke(null, "mod", new Value [] {zero}, null).isUndefined());
			assertEquals(zero.invoke(null, "mod", new Value [] {one}, null), zero);
			assertEquals(one.invoke(null, "mod", new Value [] {one}, null), zero);
			assertEquals(minusOne.invoke(null, "mod", new Value [] {one}, null), zero);
			assertEquals(minusOne.invoke(null, "mod", new Value [] {minusOne}, null), zero);
			assertEquals(two.invoke(null, "mod", new Value [] {two}, null), zero);
			assertEquals(four.invoke(null, "mod", new Value [] {two}, null), zero);
			assertEquals(minusFour.invoke(null, "mod", new Value [] {minusTwo}, null), zero);
			assertEquals(one.invoke(null, "mod", new Value [] {two}, null), one);
			assertEquals(one.invoke(null, "mod", new Value [] {three}, null), one);
			assertEquals(four.invoke(null, "mod", new Value [] {three}, null), one);
			assertEquals(minusFour.invoke(null, "mod", new Value [] {three}, null), minusOne);
			assertEquals(minusTwentyNine.invoke(null, "mod", new Value [] {three}, null), minusTwo);
		} catch (MultipleCommandException x) {
			fail(x.getMessage());
			x.printStackTrace();
		} catch (UnknownCommandException x) {
			fail(x.getMessage());
			x.printStackTrace();
		}
	}

}

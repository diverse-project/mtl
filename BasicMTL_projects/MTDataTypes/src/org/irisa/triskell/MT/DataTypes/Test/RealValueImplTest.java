/*
 * Created on 29 juil. 2003
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package org.irisa.triskell.MT.DataTypes.Test;

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
public class RealValueImplTest extends TestCase {
	public RealValueImpl zero = new RealValueImpl(false, null, 0);
	public RealValueImpl one = new RealValueImpl(false, null, 1);
	public RealValueImpl oneAndAHalf = new RealValueImpl(false, null, (float)1.5);
	public RealValueImpl twoAndAHalf = new RealValueImpl(false, null, (float)2.5);
	public RealValueImpl minusHalf = new RealValueImpl(false, null, (float)-0.5);
	public RealValueImpl minusOneAndAHalf = new RealValueImpl(false, null, (float)-1.5);
	public RealValueImpl twoTwentyFife = new RealValueImpl(false, null, (float)2.25);
	public RealValueImpl ohTwentyFife = new RealValueImpl(false, null, (float)0.25);
	public RealValueImpl ohSeventyFife = new RealValueImpl(false, null, (float)0.75);
	public IntegerValueImpl ione = new IntegerValueImpl(false, null, 1);
	public IntegerValueImpl itwo = new IntegerValueImpl(false, null, 2);
	public IntegerValueImpl izero = new IntegerValueImpl(false, null, 0);
	public IntegerValueImpl iminusOne = new IntegerValueImpl(false, null, -1);
	public IntegerValueImpl iminusTwo = new IntegerValueImpl(false, null, -2);
	
	public void testEquals0 () {
		RealValueImpl z = new RealValueImpl(false, null, 0);
		try {
			assertTrue(((BooleanValueImpl)z.invoke(null, "=", new Value [] {zero}, null)).getTheBoolean());
			assertFalse(((BooleanValueImpl)z.invoke(null, "<>", new Value [] {zero}, null)).getTheBoolean());
			assertTrue(((BooleanValueImpl)z.invoke(null, "=", new Value [] {izero}, null)).getTheBoolean());
			assertFalse(((BooleanValueImpl)z.invoke(null, "<>", new Value [] {izero}, null)).getTheBoolean());
			assertFalse(((BooleanValueImpl)z.invoke(null, "=", new Value [] {one}, null)).getTheBoolean());
			assertTrue(((BooleanValueImpl)z.invoke(null, "<>", new Value [] {one}, null)).getTheBoolean());
			assertFalse(((BooleanValueImpl)z.invoke(null, "=", new Value [] {ione}, null)).getTheBoolean());
			assertTrue(((BooleanValueImpl)z.invoke(null, "<>", new Value [] {ione}, null)).getTheBoolean());
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
			assertFalse(((BooleanValueImpl)zero.invoke(null, "=", new Value [] {oneAndAHalf}, null)).getTheBoolean());
			assertTrue(((BooleanValueImpl)zero.invoke(null, "<>", new Value [] {oneAndAHalf}, null)).getTheBoolean());
			assertFalse(((BooleanValueImpl)one.invoke(null, "=", new Value [] {zero}, null)).getTheBoolean());
			assertTrue(((BooleanValueImpl)one.invoke(null, "<>", new Value [] {oneAndAHalf}, null)).getTheBoolean());
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
			assertEquals(oneAndAHalf.invoke(null, "-", null, null), (minusOneAndAHalf));
			assertEquals(zero.invoke(null, "+", new Value [] {zero}, null), (zero));
			assertEquals(zero.invoke(null, "+", new Value [] {oneAndAHalf}, null), (oneAndAHalf));
			assertEquals(one.invoke(null, "+", new Value [] {oneAndAHalf}, null), (twoAndAHalf));
			assertEquals(one.invoke(null, "+", new Value [] {minusOneAndAHalf}, null), (minusHalf));
			assertEquals(zero.invoke(null, "-", new Value [] {zero}, null), (zero));
			assertEquals(zero.invoke(null, "-", new Value [] {oneAndAHalf}, null), (minusOneAndAHalf));
			assertEquals(one.invoke(null, "-", new Value [] {oneAndAHalf}, null), (minusHalf));
			assertEquals(one.invoke(null, "-", new Value [] {minusOneAndAHalf}, null), (twoAndAHalf));
			assertEquals(zero.invoke(null, "*", new Value [] {zero}, null), (zero));
			assertEquals(zero.invoke(null, "*", new Value [] {oneAndAHalf}, null), (zero));
			assertEquals(one.invoke(null, "*", new Value [] {oneAndAHalf}, null), (oneAndAHalf));
			assertEquals(one.invoke(null, "*", new Value [] {minusOneAndAHalf}, null), (minusOneAndAHalf));
			assertEquals(oneAndAHalf.invoke(null, "*", new Value [] {oneAndAHalf}, null), (twoTwentyFife));
			assertEquals(oneAndAHalf.invoke(null, "*", new Value [] {minusHalf}, null), (ohTwentyFife.invoke(null, "-", null, null).invoke(null, "*", new Value [] {new IntegerValueImpl(false, null, 3)}, null)));
			assertTrue(zero.invoke(null, "/", new Value [] {zero}, null).isUndefined());
			assertEquals(zero.invoke(null, "/", new Value [] {oneAndAHalf}, null), (zero));
			assertTrue(oneAndAHalf.invoke(null, "/", new Value [] {zero}, null).isUndefined());
			assertEquals(oneAndAHalf.invoke(null, "/", new Value [] {one}, null), (oneAndAHalf));
			assertEquals(minusOneAndAHalf.invoke(null, "/", new Value [] {one}, null), (minusOneAndAHalf));
			assertEquals(twoTwentyFife.invoke(null, "/", new Value [] {oneAndAHalf}, null), (oneAndAHalf));
		} catch (MultipleCommandException x) {
			fail(x.getMessage());
			x.printStackTrace();
		} catch (UnknownCommandException x) {
			fail(x.getMessage());
			x.printStackTrace();
		}
	}
	
	public void testDivers () {
		try {
			assertEquals(zero.invoke(null, "abs", null, null), (zero));
			assertEquals(oneAndAHalf.invoke(null, "abs", null, null), (oneAndAHalf));
			assertEquals(minusOneAndAHalf.invoke(null, "abs", null, null), (oneAndAHalf));
			assertEquals(zero.invoke(null, "floor", null, null), (izero));
			assertEquals(one.invoke(null, "floor", null, null), (ione));
			assertEquals(oneAndAHalf.invoke(null, "floor", null, null), (ione));
			assertEquals(minusOneAndAHalf.invoke(null, "floor", null, null), (iminusTwo));
			assertEquals(zero.invoke(null, "round", null, null), (izero));
			assertEquals(one.invoke(null, "round", null, null), (ione));
			assertEquals(oneAndAHalf.invoke(null, "round", null, null), (itwo));
			assertEquals(minusOneAndAHalf.invoke(null, "round", null, null), (iminusOne));
			assertEquals(ohTwentyFife.invoke(null, "round", null, null), (izero));
			assertEquals(twoTwentyFife.invoke(null, "round", null, null), (itwo));
			assertEquals(ohSeventyFife.invoke(null, "round", null, null), (ione));
			assertEquals(zero.invoke(null, "max", new Value [] {zero}, null), (zero));
			assertEquals(one.invoke(null, "max", new Value [] {zero}, null), (one));
			assertEquals(zero.invoke(null, "max", new Value [] {one}, null), (one));
			assertEquals(oneAndAHalf.invoke(null, "max", new Value [] {one}, null), (oneAndAHalf));
			assertEquals(minusOneAndAHalf.invoke(null, "max", new Value [] {minusHalf}, null), (minusHalf));
			assertEquals(ohTwentyFife.invoke(null, "max", new Value [] {minusHalf}, null), (ohTwentyFife));
			assertEquals(ohTwentyFife.invoke(null, "max", new Value [] {ohTwentyFife}, null), (ohTwentyFife));
			assertEquals(zero.invoke(null, "min", new Value [] {zero}, null), (zero));
			assertEquals(one.invoke(null, "min", new Value [] {zero}, null), (zero));
			assertEquals(zero.invoke(null, "min", new Value [] {one}, null), (zero));
			assertEquals(oneAndAHalf.invoke(null, "min", new Value [] {one}, null), (one));
			assertEquals(minusOneAndAHalf.invoke(null, "min", new Value [] {minusHalf}, null), (minusOneAndAHalf));
			assertEquals(ohTwentyFife.invoke(null, "min", new Value [] {minusHalf}, null), (minusHalf));
			assertEquals(minusHalf.invoke(null, "min", new Value [] {minusHalf}, null), (minusHalf));
		} catch (MultipleCommandException x) {
			fail(x.getMessage());
			x.printStackTrace();
		} catch (UnknownCommandException x) {
			fail(x.getMessage());
			x.printStackTrace();
		}
	}
	
	public void testComp () {
		try {
			assertEquals(zero.invoke(null, "<", new Value [] {zero}, null), (BooleanValueImpl.FALSE));
			assertEquals(one.invoke(null, "<", new Value [] {zero}, null), (BooleanValueImpl.FALSE));
			assertEquals(zero.invoke(null, "<", new Value [] {one}, null), (BooleanValueImpl.TRUE));
			assertEquals(oneAndAHalf.invoke(null, "<", new Value [] {one}, null), (BooleanValueImpl.FALSE));
			assertEquals(one.invoke(null, "<", new Value [] {oneAndAHalf}, null), (BooleanValueImpl.TRUE));
			assertEquals(minusOneAndAHalf.invoke(null, "<", new Value [] {minusHalf}, null), (BooleanValueImpl.TRUE));
			assertEquals(minusHalf.invoke(null, "<", new Value [] {minusOneAndAHalf}, null), (BooleanValueImpl.FALSE));
			assertEquals(ohTwentyFife.invoke(null, "<", new Value [] {minusHalf}, null), (BooleanValueImpl.FALSE));
			assertEquals(minusHalf.invoke(null, "<", new Value [] {ohTwentyFife}, null), (BooleanValueImpl.TRUE));
			assertEquals(minusHalf.invoke(null, "<", new Value [] {minusHalf}, null), (BooleanValueImpl.FALSE));
			
			assertEquals(zero.invoke(null, ">", new Value [] {zero}, null), (BooleanValueImpl.FALSE));
			assertEquals(one.invoke(null, ">", new Value [] {zero}, null), (BooleanValueImpl.TRUE));
			assertEquals(zero.invoke(null, ">", new Value [] {one}, null), (BooleanValueImpl.FALSE));
			assertEquals(oneAndAHalf.invoke(null, ">", new Value [] {one}, null), (BooleanValueImpl.TRUE));
			assertEquals(one.invoke(null, ">", new Value [] {oneAndAHalf}, null), (BooleanValueImpl.FALSE));
			assertEquals(minusOneAndAHalf.invoke(null, ">", new Value [] {minusHalf}, null), (BooleanValueImpl.FALSE));
			assertEquals(minusHalf.invoke(null, ">", new Value [] {minusOneAndAHalf}, null), (BooleanValueImpl.TRUE));
			assertEquals(ohTwentyFife.invoke(null, ">", new Value [] {minusHalf}, null), (BooleanValueImpl.TRUE));
			assertEquals(minusHalf.invoke(null, ">", new Value [] {ohTwentyFife}, null), (BooleanValueImpl.FALSE));
			assertEquals(minusHalf.invoke(null, ">", new Value [] {minusHalf}, null), (BooleanValueImpl.FALSE));
			
			assertEquals(zero.invoke(null, "<=", new Value [] {zero}, null), (BooleanValueImpl.TRUE));
			assertEquals(one.invoke(null, "<=", new Value [] {zero}, null), (BooleanValueImpl.FALSE));
			assertEquals(zero.invoke(null, "<=", new Value [] {one}, null), (BooleanValueImpl.TRUE));
			assertEquals(oneAndAHalf.invoke(null, "<=", new Value [] {one}, null), (BooleanValueImpl.FALSE));
			assertEquals(one.invoke(null, "<=", new Value [] {oneAndAHalf}, null), (BooleanValueImpl.TRUE));
			assertEquals(minusOneAndAHalf.invoke(null, "<=", new Value [] {minusHalf}, null), (BooleanValueImpl.TRUE));
			assertEquals(minusHalf.invoke(null, "<=", new Value [] {minusOneAndAHalf}, null), (BooleanValueImpl.FALSE));
			assertEquals(ohTwentyFife.invoke(null, "<=", new Value [] {minusHalf}, null), (BooleanValueImpl.FALSE));
			assertEquals(minusHalf.invoke(null, "<=", new Value [] {ohTwentyFife}, null), (BooleanValueImpl.TRUE));
			assertEquals(minusHalf.invoke(null, "<=", new Value [] {minusHalf}, null), (BooleanValueImpl.TRUE));
			
			assertEquals(zero.invoke(null, ">=", new Value [] {zero}, null), (BooleanValueImpl.TRUE));
			assertEquals(one.invoke(null, ">=", new Value [] {zero}, null), (BooleanValueImpl.TRUE));
			assertEquals(zero.invoke(null, ">=", new Value [] {one}, null), (BooleanValueImpl.FALSE));
			assertEquals(oneAndAHalf.invoke(null, ">=", new Value [] {one}, null), (BooleanValueImpl.TRUE));
			assertEquals(one.invoke(null, ">=", new Value [] {oneAndAHalf}, null), (BooleanValueImpl.FALSE));
			assertEquals(minusOneAndAHalf.invoke(null, ">=", new Value [] {minusHalf}, null), (BooleanValueImpl.FALSE));
			assertEquals(minusHalf.invoke(null, ">=", new Value [] {minusOneAndAHalf}, null), (BooleanValueImpl.TRUE));
			assertEquals(ohTwentyFife.invoke(null, ">=", new Value [] {minusHalf}, null), (BooleanValueImpl.TRUE));
			assertEquals(minusHalf.invoke(null, ">=", new Value [] {ohTwentyFife}, null), (BooleanValueImpl.FALSE));
			assertEquals(minusHalf.invoke(null, ">=", new Value [] {minusHalf}, null), (BooleanValueImpl.TRUE));
		} catch (MultipleCommandException x) {
			fail(x.getMessage());
			x.printStackTrace();
		} catch (UnknownCommandException x) {
			fail(x.getMessage());
			x.printStackTrace();
		}
	}

}

/*
 * Created on 19 mai 2003
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package org.irisa.triskell.MT.utils.Java.Test;

import java.util.Arrays;

import junit.framework.TestCase;

import org.irisa.triskell.MT.utils.Java.PositionSorter;

/**
 * @author ffondeme
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class PositionSorterTest extends TestCase {

	public static void main(String[] args) {
		junit.textui.TestRunner.run(PositionSorterTest.class);
	}

	public void testSort1() {
		Object [] model = 
			new Integer[] {
				new Integer(1),
				new Integer(2),
				new Integer(3),
				new Integer(4),
				new Integer(5),
				new Integer(6)
				};
		Object [] elements = new Integer [] {
				new Integer(6),
				new Integer(2),
				new Integer(5),
				new Integer(3),
				new Integer(1),
				new Integer(4)
			};
		PositionSorter ps = new PositionSorter(
			model,
			elements,
			new PositionSorter.Criterium [] {
				new PositionSorter.Criterium() {
					public boolean mayBeEquivalent (Object o, Object model) {
						return o.equals(model);
					}
				}
			}
			);
		Object [] result = ps.sort();
		assertTrue(Arrays.equals(model, result));
	}

	//Given criterium is not enough
	public void testSort2() {
		Object [] model = 
			new Integer[] {
				new Integer(1),
				new Integer(2),
				new Integer(3),
				new Integer(4),
				new Integer(5),
				new Integer(6)
				};
		Object [] elements = new Integer [] {
				new Integer(6),
				new Integer(2),
				new Integer(5),
				new Integer(3),
				new Integer(1),
				new Integer(4)
			};
		PositionSorter ps = new PositionSorter(
			model,
			elements,
			new PositionSorter.Criterium [] {
				new PositionSorter.Criterium() {
					public boolean mayBeEquivalent (Object o, Object model) {
						int i1 = ((Integer)o).intValue();
						int i2 = ((Integer)model).intValue();
						return (i1 & 0x5555) == (i2 & 0x5555);
					}
				}
			}
			);
		Object [] result = ps.sort();
		assertTrue(result == null);
	}

	public void testSort3() {
		Object [] model = 
			new Integer[] {
				new Integer(1),
				new Integer(2),
				new Integer(3),
				new Integer(4),
				new Integer(5),
				new Integer(6)
				};
		Object [] elements = new Integer [] {
				new Integer(6),
				new Integer(2),
				new Integer(5),
				new Integer(3),
				new Integer(1),
				new Integer(4)
			};
		PositionSorter ps = new PositionSorter(
			model,
			elements,
			new PositionSorter.Criterium [] {
				new PositionSorter.Criterium() {
					public boolean mayBeEquivalent (Object o, Object model) {
						int i1 = ((Integer)o).intValue();
						int i2 = ((Integer)model).intValue();
						return (i1 & 0x5555) == (i2 & 0x5555);
					}
				},
				new PositionSorter.Criterium() {
					public boolean mayBeEquivalent (Object o, Object model) {
						int i1 = ((Integer)o).intValue();
						int i2 = ((Integer)model).intValue();
						return (i1 & 0xAAAA) == (i2 & 0xAAAA);
					}
				}
			}
			);
		Object [] result = ps.sort();
		assertTrue(Arrays.equals(model, result));
	}

	//Two values must not share the same cell...
	public void testSort4() {
		Object [] model = 
			new Integer[] {
				new Integer(1),
				new Integer(2),
				new Integer(3),
				new Integer(4),
				new Integer(5),
				new Integer(6)
				};
		Object [] elements = new Integer [] {
				new Integer(6),
				new Integer(6),
				new Integer(5),
				new Integer(3),
				new Integer(1),
				new Integer(4)
			};
		PositionSorter ps = new PositionSorter(
			model,
			elements,
			new PositionSorter.Criterium [] {
				new PositionSorter.Criterium() {
					public boolean mayBeEquivalent (Object o, Object model) {
						return o.equals(model);
					}
				}
			}
			);
		Object [] result = ps.sort();
		assertTrue(result == null);
	}
}

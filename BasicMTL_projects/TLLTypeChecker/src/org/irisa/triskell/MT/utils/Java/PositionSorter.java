/*
 * Created on 15 mai 2003
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package org.irisa.triskell.MT.utils.Java;

import java.util.Arrays;
import java.util.Comparator;
import java.lang.reflect.Array;

/**
 * @author ffondeme for the IRISA (www.irisa.fr)
 * This class sorts some elements according to a model using a list of criteria.
 * It is assumed each one of the element corresponds to one (and only one) element of the model.
 * This correspondance is given by the criteria, which are able to say if an element CAN correspond to another one from the model.
 * Each one of the criteria has not to fully determine the correspondance, but the association of all of them should.
 */
public class PositionSorter {
	/**
	 * A class describing a sort criterium.
	 */
	public static interface Criterium {
		/**
		 * @param o an element of the elements to sort
		 * @param model an element of the sorted model
		 * @return boolean only the false value is deterministic
		 */
		public abstract boolean mayBeEquivalent (Object o, Object model); 
	}
	
	private static class NoSolutionException extends RuntimeException {}
	
	protected static final NoSolutionException noSolutionException = new NoSolutionException();
	
	/**
	 * The sorted model
	 */
	protected final Object [] model;
	/**
	 * The elements to sort
	 */
	protected final Object [] elements;
	/**
	 * The criteria to be applied
	 */
	protected final Criterium [] criteria;
	
	/**
	 * The size of the collections (both element and model)
	 */
	protected final int size;
	/**
	 * The computed possibilities.
	 * lines are elements and columns model elements
	 */
	protected boolean [] [] possibilities;
	/**
	 * The computed ambiguous lines (each of the true at startup)
	 */
	protected boolean [] ambiguousLines;
	/**
	 * The computed first ambiguous line (0 at startup)
	 */
	protected int firstAmbiguousLine;
	/**
	 * The firsts possible position of each element (acording to the index)
	 */ 
	protected int [] firstTruePosition;
	/**
	 * The computed solution
	 */
	protected Object [] solution;
	
	/**
	 * @param model the model of the solution
	 * @param elements the elements to sort
	 * @param criteria the criterium to infer the solution; each one of them will be applied (in the given order)
	 */
	public PositionSorter (final Object [] model, final Object [] elements, final Criterium [] criteria) {
		if (model.length != elements.length)
			throw new IllegalArgumentException();
		this.model = model;
		this.elements = elements;
		this.criteria = criteria;
		this.size = model.length;
		this.possibilities = new boolean [this.size] [];
		for (int i = 0; i < this.size; ++i) {
			this.possibilities[i] = new boolean[this.size];
			Arrays.fill(this.possibilities[i], true);
		}
		this.ambiguousLines = new boolean [this.size];
		Arrays.fill(this.ambiguousLines, true);
		this.firstAmbiguousLine = 0;
		this.firstTruePosition = new int [this.size];
		Arrays.fill(this.firstTruePosition, 0);
		this.solution = null;
	}
	
	/**
	 * Each one of the criteria is applied to enforce the solution is correct
	 * @return Object[] null if there is no solution (more or less than 1)
	 */
	public Object [] sort () {
		try {
			this.solution = null;
			boolean sorted = false;
			for (int ic = 0; ic < this.criteria.length; ++ic)
				sorted = this.sortWithCriterium(ic) || sorted;
			if (sorted) {
				this.doSort();
				return this.solution;
			} else
				return null;
		} catch (NoSolutionException x) {
			return null;
		}
	}
	
	/**
	 * Indicates if the solution has been found.
	 * @return boolean
	 */
	public boolean hasFinished() {
		for (; this.firstAmbiguousLine < this.size; ++this.firstAmbiguousLine) {
			if (this.ambiguousLines[this.firstAmbiguousLine])
				return false;
		}
		return true;
	}
	
	/**
	 * To call each time a possibility has changed:
	 *  - refreshes ambiguousLines field
	 *  - refreshes firstAmbiguousLine field
	 *  - infers possibilities field according to the fact just one element corresponds to an element from the model
	 *
	 */
	protected void rearrangePossibilities () {
		int p, ei = this.firstAmbiguousLine;
		boolean acted = false;
		while (ei < this.size) {
			if (this.ambiguousLines[ei]) {
				p = this.getElementPosition(ei);
				if (p != -1) {
					if (this.firstAmbiguousLine == ei)
						this.firstAmbiguousLine++;
					for (int i = this.firstAmbiguousLine; i < this.size; i++) {
						if (i != ei && this.possibilities[i][p]) {
							this.possibilities[i][p] = false;
							acted = true;
						}
					}
				}
			}
			ei++;
		}
		
		if (acted && (! this.hasFinished())) {
			this.rearrangePossibilities();
		}
	}
	
	/**
	 * The position the eith element should be placed in.
	 * @param ei the index of the element
	 * @return int -1 if undetermined
	 */
	protected int getElementPosition (int ei) {
		int ms = -1;
		
		try {
			while (! this.possibilities[ei][this.firstTruePosition[ei]])
				this.firstTruePosition[ei]++;
		} catch (ArrayIndexOutOfBoundsException x){
			throw noSolutionException;
		}
		
		for (int i = this.firstTruePosition[ei]+1; i < this.size; ++i) {
			if (this.possibilities[ei][i])
				return -1;
		}
		
		this.ambiguousLines[ei] = false;
		return this.firstTruePosition[ei];
	}
	
	/**
	 * If the solution is computed (according to the hasFinished operation) refreshes the solution field.
	 */
	protected void doSort () {
		if (this.hasFinished()) {
			this.solution = (Object[])Array.newInstance(this.elements.getClass().getComponentType(), this.size);
			Arrays.fill(this.solution, null);
			for (int i = 0, pos; i < this.size; ++i) {
				 pos = this.getElementPosition(i);
				 if (this.solution[pos] != null) {
				 	this.solution = null;
				 	throw noSolutionException;
				 }
				 this.solution[pos] = this.elements[i];
			}
		}
	}
	
	/**
	 * Computes the possibilities field.
	 * @param ci the cith criterium to apply
	 * @return boolean indicates if the sort is deterministic
	 */
	public boolean sortWithCriterium (int ci) {
		for (int ei = 0, mi; ei < this.size; ++ei) {
			for (mi = this.firstTruePosition[ei]; mi < this.size; ++mi) {
				if (this.possibilities[ei][mi]) {
					this.possibilities[ei][mi] = this.criteria[ci].mayBeEquivalent(this.elements[ei], this.model[mi]);
					if (! this.possibilities[ei][mi]) {
						if ((!this.ambiguousLines[ei]) && (this.firstTruePosition[ei] == mi))
							throw noSolutionException;
						this.rearrangePossibilities();
					}
				}
			}
		}
		return this.hasFinished();
	}
	
	/**
	 * The computed solution. If still not computed, do the inference...
	 * @return Object[]
	 */
	public Object [] getSolution () {
		if (this.solution == null)
			this.sort();
		return this.solution;
	}
}

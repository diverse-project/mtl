/*
* $Id: IJavaAnnotation.java,v 1.2 2004-08-26 12:40:07 sdzale Exp $
* Authors : ${user}
*
* Created on ${date}
* Copyright 2004 - INRIA - LGPL license
*/ 
package org.inria.mtl.editors.utils;

import java.util.Iterator;

import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;

/**
 * Interface of annotations representing markers
 * and problems.
 * 
 * @see org.eclipse.core.resources.IMarker
 * @see org.eclipse.jdt.core.compiler.IProblem
 */
public interface IJavaAnnotation {
	
	AnnotationType getAnnotationType();
	
	boolean isTemporary();
	
	String getMessage();
	
	String[] getArguments();
	
	int getId();
	
	
	Image getImage(Display display);
	
	/**
	 * Returns whether this annotation is relavant.
	 * <p>
	 * If the annotation is overlaid then it is not
	 * relevant. After all overlays have been removed
	 * the annotation might either become relevant again
	 * or stay irrelevant.
	 * </p>
	 * 
	 * @return <code>true</code> if relevant
	 * @see #hasOverlay()
	 */
	boolean isRelevant();
	
	/**
	 * Returns whether this annotation is overlaid.
	 * 
	 * @return <code>true</code> if overlaid
	 */
	boolean hasOverlay();
	
	/**
	 * Returns an iterator for iterating over the
	 * annotation which are overlaid by this annotation.
	 * 
	 * @return an iterator over the overlaid annotaions
	 */
	Iterator getOverlaidIterator();
	
	/**
	 * Adds the given annotation to the list of
	 * annotations which are overlaid by this annotations.
	 *  
	 * @param annotation	the problem annoation
	 */
	void addOverlaid(IJavaAnnotation annotation);
	
	/**
	 * Removes the given annotation from the list of
	 * annotations which are overlaid by this annotation.
	 *  
	 * @param annotation	the problem annoation
	 */
	void removeOverlaid(IJavaAnnotation annotation);
	
	/**
	 * Tells whether this annotation is a problem
	 * annotation.
	 * 
	 * @return <code>true</code> if it is a problem annotation
	 */
	boolean isProblem();
}

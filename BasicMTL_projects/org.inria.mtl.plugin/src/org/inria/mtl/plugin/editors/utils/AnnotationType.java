/*
* $Id: AnnotationType.java,v 1.2 2004-05-19 09:22:12 sdzale Exp $
* Authors : ${user}
*
* Created on ${date}
* Copyright 2004 - INRIA - LGPL license
*/ 
package org.inria.mtl.plugin.editors.utils;

import org.eclipse.jface.text.Assert;

public final class AnnotationType {
	
	public static final AnnotationType ALL= new AnnotationType();
	public static final AnnotationType UNKNOWN= new AnnotationType();
	public static final AnnotationType BOOKMARK= new AnnotationType();
	public static final AnnotationType TASK= new AnnotationType();
	public static final AnnotationType ERROR= new AnnotationType();
	public static final AnnotationType WARNING= new AnnotationType();
	public static final AnnotationType SEARCH= new AnnotationType();
	
	private AnnotationType() {
	}

	public String toString() {
		if (this == ALL)
			return "AnnotationType.ALL"; //$NON-NLS-1$
		
		if (this == UNKNOWN)
			return "AnnotationType.UNKNOWN"; //$NON-NLS-1$

		if (this == BOOKMARK)
			return "AnnotationType.BOOKMARK"; //$NON-NLS-1$

		if (this == TASK)
			return "AnnotationType.TASK"; //$NON-NLS-1$
		
		if (this == ERROR)
			return "AnnotationType.ERROR"; //$NON-NLS-1$

		if (this == WARNING)
			return "AnnotationType.WARNING"; //$NON-NLS-1$

		if (this == SEARCH)
			return "AnnotationType.SEARCH"; //$NON-NLS-1$

		Assert.isLegal(false);
		return ""; //$NON-NLS-1$
	}
}
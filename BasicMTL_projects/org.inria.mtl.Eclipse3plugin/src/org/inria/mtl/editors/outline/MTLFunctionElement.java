/*
* $Id: MTLFunctionElement.java,v 1.2 2004-08-26 12:40:54 sdzale Exp $
* Authors : ${user}
*
* Created on ${date}
* Copyright 2004 - INRIA - LGPL license
*/ 
package org.inria.mtl.editors.outline;

import org.eclipse.jface.resource.ImageDescriptor;

public class MTLFunctionElement extends MTLElement
{

	protected String arguments;
	/**
	 * @param aName
	 * @param offset
	 * @param length
	 */
	public MTLFunctionElement(String aName, String argumentString, int offset, int length)
	{
		super(aName, offset, length);
		arguments = argumentString;
	}

	/**
	 * @see org.eclipse.ui.model.IWorkbenchAdapter#getImageDescriptor(Object)
	 */
	public ImageDescriptor getImageDescriptor(Object object)
	{
		return MTLImages.ICON_VIEW_FUNCTION;
	}

	/**
	 * Method declared on IWorkbenchAdapter
	 */
	public String getLabel(Object o)
	{
		String firstPart = name;
		if(firstPart.length() <= 0){
			firstPart =  "<anonymous>";
		}
		return firstPart + arguments;
	}
	public int category(){ return FUNCTION;}
	public String getArguments(){ return arguments;}
}

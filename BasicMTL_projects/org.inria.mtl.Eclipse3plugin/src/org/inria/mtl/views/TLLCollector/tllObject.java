/*
* $Id: tllObject.java,v 1.1 2004-08-26 12:40:35 sdzale Exp $
* Authors : ${user}
*
* Created on ${date}
* Copyright 2004 - INRIA - LGPL license
*/
package org.inria.mtl.views.TLLCollector;
import org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.QualifiedName;

/**
  * To change the template for this generated type comment go to Window - Preferences - Java - Code Generation - Code and Comments
 */
public class tllObject
{	
	/** the type of the object * */
	private QualifiedName objectType;
	/** the category of the event * */
	private int category;
	/** the thread for the event * */
	private String objectName;
	/** the msg for the event * */
	private String message;
	/** the location details for the event * */
	private String locationDetails;

	public tllObject( String objName,QualifiedName objType,  int category, String locationDetails,String message)
	{
		this.objectType = objType;
		this.objectName = objName;
		this.category = category;
		this.message= message;
		this.locationDetails = locationDetails;
	}


	/**
	 * @return Returns the categoryName.
	 */
	public int getCategoryName()
	{
		return this.category;
	}
	
	/**
	 * @return Returns the object Name.
	 */
	public String getName()
	{
		return this.objectName;
	}
	/**
	 * @return Returns the locationDetails.
	 */
	public String getLocationDetails()
	{
		return this.locationDetails;
	}
	/**
	 * @return Returns the message.
	 */
	public String getMessage()
	{
		return this.message;
	}
	/**
	 * @return Returns the type.
	 */
	public QualifiedName getType()
	{
		return this.objectType;
	}
	
	
	

}
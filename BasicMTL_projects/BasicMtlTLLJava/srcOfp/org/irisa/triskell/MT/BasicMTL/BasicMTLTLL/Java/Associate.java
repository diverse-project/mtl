/*
 * $Id: Associate.java,v 1.5 2004-04-29 13:55:35 edrezen Exp $
 * Authors : modelware
 * 
 * Copyright 2004 - INRIA - LGPL license
 */
package org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java;

import java.util.*;

/**
  * A link implementation between model elements (of the same model).
  */
public class Associate 
    extends org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.Instruction
{
    /**
      * Representation of model elements to be linked.
      */
    public Vector roles = new Vector();
    public org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.Role getRoles (int i) {
        return (org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.Role)this.roles.elementAt(i);
    }
    public void setRoles (int i, org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.Role value) {
        this.roles.setElementAt(value, i);
    }
    public void appendRoles (org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.Role value) {
        this.roles.addElement(value);
    }
    public void eraseRoles (org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.Role value)  {
        this.roles.removeElement(value);
    }
    public void eraseRoles (int i)  {
        this.roles.removeElementAt(i);
    }
    public int cardRoles () {
        return this.roles.size();
    }

    /**
      * The name of the association to be implemented.
      */
    public String AssociationName;
    public String getAssociationName () {
        return this.AssociationName;
    }

    public boolean isAssociate;
    public boolean getIsAssociate () {
        return this.isAssociate;
    }
    public void setIsAssociate (boolean value) {
        this.isAssociate = value; 
    }


    public Associate(
        int lineNumber,
        boolean isAssociate)
    {
this.lineNumber=lineNumber;
this.isAssociate=isAssociate;
    }
}

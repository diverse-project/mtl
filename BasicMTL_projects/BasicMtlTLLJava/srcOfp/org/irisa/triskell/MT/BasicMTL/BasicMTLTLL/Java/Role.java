/*
 * $Id: Role.java,v 1.4 2004-04-29 13:55:35 edrezen Exp $
 * Authors : modelware
 * 
 * Copyright 2004 - INRIA - LGPL license
 */
package org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java;



/**
  * A map between a model element and an association end on which the link to be created must place this element.
  */
public class Role 
    extends org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.ASTNode
{
    /**
      * The name of the association end where the model elemnt should be linked (if available).
      */
    public String RoleName;
    public String getRoleName () {
        return this.RoleName;
    }

    /**
      * A value representing thi model element.
      */
    public org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.Expression expression;
    public org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.Expression getExpression () {
        return this.expression;
    }
    public void setExpression (org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.Expression value) {
        this.expression = value; 
    }
    public int cardExpression () {
        if ( this.expression == null ) return 0;
        else return 1;
    }

    public org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.QualifiedName linkedEltType;
    public org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.QualifiedName getLinkedEltType () {
        return this.linkedEltType;
    }
    public void setLinkedEltType (org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.QualifiedName value) {
        this.linkedEltType = value; 
    }
    public int cardLinkedEltType () {
        if ( this.linkedEltType == null ) return 0;
        else return 1;
    }


    public Role(
        String name)
    {
this.RoleName=name;
    }
}

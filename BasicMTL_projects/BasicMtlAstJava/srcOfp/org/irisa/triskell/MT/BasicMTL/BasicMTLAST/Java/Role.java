package org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java;

import org.irisa.triskell.MT.visitors.Java.GenericVisitor.*;
import org.irisa.triskell.MT.visitors.Java.AnalysingVisitor.*;
import java.util.*;
import org.irisa.triskell.MT.visitors.Java.GenericVisitor.Visitor;
import org.irisa.triskell.MT.visitors.Java.AnalysingVisitor.Property;
import java.util.Map;
import java.lang.Object;

/**
  * A map between a model element and an association end on which the link to be created must place this element.
  */
public class Role 
    extends org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.ASTNode
{
    /**
      * The name of the association end where the model elemnt should be linked (if available).
      */
    protected String RoleName;
    public String getRoleName () {
        return this.RoleName;
    }

    /**
      * A value representing thi model element.
      */
    protected org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Expression expression;
    public org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Expression getExpression () {
        return this.expression;
    }
    public void setExpression (org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Expression value) {
        this.expression = value; 
    }
    public int cardExpression () {
        if ( this.expression == null ) return 0;
        else return 1;
    }

    /**
      * The type of the association end where the model element should be linked (if available).
      */
    protected org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.UserClass linkedEltType;
    public org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.UserClass getLinkedEltType () {
        return this.linkedEltType;
    }
    public void setLinkedEltType (org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.UserClass value) {
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

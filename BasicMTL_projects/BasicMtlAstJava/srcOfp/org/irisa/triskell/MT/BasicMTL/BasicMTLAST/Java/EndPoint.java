package org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java;

import org.irisa.triskell.MT.visitors.Java.GenericVisitor.*;
import org.irisa.triskell.MT.visitors.Java.AnalysingVisitor.*;
import java.util.*;
import org.irisa.triskell.MT.visitors.Java.GenericVisitor.Visitor;
import org.irisa.triskell.MT.visitors.Java.AnalysingVisitor.Property;
import java.util.Map;
import java.lang.Object;

public class EndPoint 
    extends org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.ASTNode
{
    protected String roleName;
    public String getRoleName () {
        return this.roleName;
    }
    public void setRoleName (String value) {
        this.roleName = value; 
    }

    protected String className;
    public String getClassName () {
        return this.className;
    }
    public void setClassName (String value) {
        this.className = value; 
    }

    protected boolean isAggregation;
    public boolean getIsAggregation () {
        return this.isAggregation;
    }
    public void setIsAggregation (boolean value) {
        this.isAggregation = value; 
    }

    protected boolean isComposition;
    public boolean getIsComposition () {
        return this.isComposition;
    }
    public void setIsComposition (boolean value) {
        this.isComposition = value; 
    }

    protected boolean isOrdered;
    public boolean getIsOrdered () {
        return this.isOrdered;
    }
    public void setIsOrdered (boolean value) {
        this.isOrdered = value; 
    }

    protected boolean isNavigable;
    public boolean getIsNavigable () {
        return this.isNavigable;
    }
    public void setIsNavigable (boolean value) {
        this.isNavigable = value; 
    }

    protected org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Multiplicity multiplicity;
    public org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Multiplicity getMultiplicity () {
        return this.multiplicity;
    }
    public void setMultiplicity (org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Multiplicity value) {
        this.multiplicity = value; 
    }
    public int cardMultiplicity () {
        if ( this.multiplicity == null ) return 0;
        else return 1;
    }


    public EndPoint(
        String roleName,
        String className,
        boolean isComposition,
        boolean isAggregation,
        boolean isOrdered,
        boolean isNavigable)
    {
this.roleName=roleName;
this.className=className;
this.isComposition=isComposition;
this.isAggregation=isAggregation;
this.isOrdered=isOrdered;
this.isNavigable=isNavigable;
    }
}

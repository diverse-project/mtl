package org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java;

import org.irisa.triskell.MT.visitors.Java.GenericVisitor.*;
import org.irisa.triskell.MT.visitors.Java.AnalysingVisitor.*;
import java.util.*;
import org.irisa.triskell.MT.visitors.Java.GenericVisitor.Visitor;
import org.irisa.triskell.MT.visitors.Java.AnalysingVisitor.Property;
import java.util.Map;

public class InheritedOpSignature 
    extends org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.OpSignature
{
    public org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.QualifiedName typeWhichDefineOp;
    public org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.QualifiedName getTypeWhichDefineOp () {
        return this.typeWhichDefineOp;
    }
    public void setTypeWhichDefineOp (org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.QualifiedName value) {
        this.typeWhichDefineOp = value; 
    }
    public int cardTypeWhichDefineOp () {
        if ( this.typeWhichDefineOp == null ) return 0;
        else return 1;
    }

    public org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.QualifiedName parentThatRelayOp;
    public org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.QualifiedName getParentThatRelayOp () {
        return this.parentThatRelayOp;
    }
    public void setParentThatRelayOp (org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.QualifiedName value) {
        this.parentThatRelayOp = value; 
    }
    public int cardParentThatRelayOp () {
        if ( this.parentThatRelayOp == null ) return 0;
        else return 1;
    }


    public InheritedOpSignature(
        String name,
        String mangle)
    {
super(name,mangle);
    }
}

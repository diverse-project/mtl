package org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java;

import java.util.*;
import org.irisa.triskell.MT.visitors.Java.GenericVisitor.*;
import org.irisa.triskell.MT.visitors.Java.AnalysingVisitor.*;
import org.irisa.triskell.MT.visitors.Java.GenericVisitor.Visitor;
import org.irisa.triskell.MT.visitors.Java.AnalysingVisitor.Property;
import java.util.Map;

public class OpSignature 
    implements java.io.Serializable
{
    public String opName;
    public String getOpName () {
        return this.opName;
    }

    public int argsCount;
    public int getArgsCount () {
        return this.argsCount;
    }
    public void setArgsCount (int value) {
        this.argsCount = value; 
    }

    org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.Operation theOperation;
    org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.Operation getTheOperation () {
        return this.theOperation;
    }
    int cardTheOperation () {
        if ( this.theOperation == null ) return 0;
        else return 1;
    }

    public org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.QualifiedName returnedType;
    public org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.QualifiedName getReturnedType () {
        return this.returnedType;
    }
    public void setReturnedType (org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.QualifiedName value) {
        this.returnedType = value; 
    }
    public int cardReturnedType () {
        if ( this.returnedType == null ) return 0;
        else return 1;
    }

    public Vector argsTypes = new Vector();
    public org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.QualifiedName getArgsTypes (int i) {
        return (org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.QualifiedName)this.argsTypes.elementAt(i);
    }
    public void setArgsTypes (int i, org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.QualifiedName value) {
        this.argsTypes.setElementAt(value, i);
    }
    public void appendArgsTypes (org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.QualifiedName value) {
        this.argsTypes.addElement(value);
    }
    public void eraseArgsTypes (org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.QualifiedName value)  {
        this.argsTypes.removeElement(value);
    }
    public void eraseArgsTypes (int i)  {
        this.argsTypes.removeElementAt(i);
    }
    public int cardArgsTypes () {
        return this.argsTypes.size();
    }

    public String opMangle;
    public String getOpMangle () {
        return this.opMangle;
    }


    public OpSignature(
        String name,
        String mangle)
    {
this.opName=name;
this.opMangle=mangle;
    }
}

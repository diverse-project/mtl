package org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java;

import org.irisa.triskell.MT.visitors.Java.GenericVisitor.*;
import org.irisa.triskell.MT.visitors.Java.AnalysingVisitor.*;
import java.util.*;
import org.irisa.triskell.MT.visitors.Java.GenericVisitor.Visitor;
import org.irisa.triskell.MT.visitors.Java.AnalysingVisitor.Property;
import java.util.Map;
import java.lang.Object;

/**
  * A view described by a Basic MTL library. Source parameters for binding must be compatible: it must also be a TypedModelRef, typed by the same BasicMTLLibrary ore one of its sub-libraries.
  */
public class TypedModelRef 
    extends org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.ModelRef
{
    /**
      * The type of the referenced library.
      */
    public org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.BasicMtlLibrary type;
    public org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.BasicMtlLibrary getType () {
        return this.type;
    }
    public void setType (org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.BasicMtlLibrary value) {
        this.type = value; 
    }
    public int cardType () {
        if ( this.type == null ) return 0;
        else return 1;
    }

    public String typeName;
    public String getTypeName () {
        return this.typeName;
    }
    public void setTypeName (String value) {
        this.typeName = value; 
    }


    public TypedModelRef(
        String name,
        String typeName)
    {
super(name);
this.typeName=typeName;
    }
}

package org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java;

import org.irisa.triskell.MT.visitors.Java.GenericVisitor.*;
import org.irisa.triskell.MT.visitors.Java.AnalysingVisitor.*;
import java.util.*;
import org.irisa.triskell.MT.visitors.Java.GenericVisitor.Visitor;
import org.irisa.triskell.MT.visitors.Java.AnalysingVisitor.Property;
import java.util.Map;

abstract public class Feature 
    extends org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.ASTNode
{
    public String name;
    public String getName () {
        return this.name;
    }

    public String mangle;
    public String getMangle () {
        return this.mangle;
    }

    public org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.QualifiedName featureType;
    public org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.QualifiedName getFeatureType () {
        return this.featureType;
    }
    public void setFeatureType (org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.QualifiedName value) {
        this.featureType = value; 
    }
    public int cardFeatureType () {
        if ( this.featureType == null ) return 0;
        else return 1;
    }

    public int lineNumber;
    public int getLineNumber () {
        return this.lineNumber;
    }


    public Feature(
        String name,
        String mangle,
        int lineNumber)
    {
this.name=name;
this.mangle=mangle;
this.lineNumber=lineNumber;
    }
}

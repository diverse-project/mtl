package org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java;

import org.irisa.triskell.MT.visitors.Java.GenericVisitor.Visitor;
import org.irisa.triskell.MT.visitors.Java.AnalysingVisitor.Property;
import java.util.Map;

public class OclTypeLiteral 
    extends org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.Literal
{
    public org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.QualifiedName theType;
    public org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.QualifiedName getTheType () {
        return this.theType;
    }
    public void setTheType (org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.QualifiedName value) {
        this.theType = value; 
    }
    public int cardTheType () {
        if ( this.theType == null ) return 0;
        else return 1;
    }

}

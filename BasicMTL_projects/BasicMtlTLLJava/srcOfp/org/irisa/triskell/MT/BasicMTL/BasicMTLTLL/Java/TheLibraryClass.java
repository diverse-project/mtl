package org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java;

import org.irisa.triskell.MT.visitors.Java.GenericVisitor.*;
import org.irisa.triskell.MT.visitors.Java.AnalysingVisitor.*;
import java.util.*;
import org.irisa.triskell.MT.visitors.Java.GenericVisitor.Visitor;
import org.irisa.triskell.MT.visitors.Java.AnalysingVisitor.Property;
import java.util.Map;

public class TheLibraryClass 
    extends org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.UserDefinedClass
{
    public org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.BasicMtlLibrary theLibrary;
    public org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.BasicMtlLibrary getTheLibrary () {
        return this.theLibrary;
    }
    public void setTheLibrary (org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.BasicMtlLibrary value) {
        this.theLibrary = value; 
    }
    public int cardTheLibrary () {
        if ( this.theLibrary == null ) return 0;
        else return 1;
    }


    public TheLibraryClass(
        String name,
        String mangle,
        int lineNumber)
    {
super(name,mangle,lineNumber);
    }
}

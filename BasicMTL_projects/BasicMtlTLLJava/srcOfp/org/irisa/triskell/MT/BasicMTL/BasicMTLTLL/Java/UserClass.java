package org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java;

import java.util.*;
import org.irisa.triskell.MT.visitors.Java.GenericVisitor.*;
import org.irisa.triskell.MT.visitors.Java.AnalysingVisitor.*;
import org.irisa.triskell.MT.visitors.Java.GenericVisitor.Visitor;
import org.irisa.triskell.MT.visitors.Java.AnalysingVisitor.Property;

/**
  * The usual concept of class; this is the stakeholder for attributes and operations. Its name is deterministic within the library. It may extends other classes.
  */
public class UserClass 
    extends org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.UserDefinedClass
{

    public UserClass(
        String name,
        String mangle,
        int lineNumber)
    {
super(name,mangle,lineNumber);
    }
}

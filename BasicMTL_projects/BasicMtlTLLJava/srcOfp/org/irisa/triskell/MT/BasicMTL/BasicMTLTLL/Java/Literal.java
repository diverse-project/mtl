package org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java;

import org.irisa.triskell.MT.visitors.Java.GenericVisitor.*;
import org.irisa.triskell.MT.visitors.Java.AnalysingVisitor.*;
import java.util.*;
import org.irisa.triskell.MT.visitors.Java.GenericVisitor.Visitor;
import org.irisa.triskell.MT.visitors.Java.AnalysingVisitor.Property;
import java.util.Map;

/**
  * A non-computed value.
  */
abstract public class Literal 
    extends org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.Expression
{
    /**
      * The type of this value.
      */
    public org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.UserClass type;
    public org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.UserClass getType () {
        return this.type;
    }
    public int cardType () {
        if ( this.type == null ) return 0;
        else return 1;
    }

}

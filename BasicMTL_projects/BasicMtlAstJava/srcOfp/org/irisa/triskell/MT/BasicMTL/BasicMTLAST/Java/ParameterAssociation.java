/*
 * $Id: ParameterAssociation.java,v 1.3 2004-04-29 13:38:53 edrezen Exp $
 * Authors : modelware
 * 
 * Copyright 2004 - INRIA - LGPL license
 */
package org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java;

import org.irisa.triskell.MT.visitors.Java.GenericVisitor.*;
import org.irisa.triskell.MT.visitors.Java.AnalysingVisitor.*;
import java.util.*;
import org.irisa.triskell.MT.visitors.Java.GenericVisitor.Visitor;
import org.irisa.triskell.MT.visitors.Java.AnalysingVisitor.Property;
import java.util.Map;
import java.lang.Object;

/**
  * A map between the using library and the used library parameters. Binded parameters must be compatible.
  */
public class ParameterAssociation 
{
    /**
      * The source parameter (must be provided by the importing library).
      */
    protected org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.LibParameter source;
    public org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.LibParameter getSource () {
        return this.source;
    }
    public void setSource (org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.LibParameter value) {
        this.source = value; 
    }
    public int cardSource () {
        if ( this.source == null ) return 0;
        else return 1;
    }

    /**
      * The target parameters (must be assigned to the imported library to the source parameter value).
      */
    protected org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.LibParameter target;
    public org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.LibParameter getTarget () {
        return this.target;
    }
    public void setTarget (org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.LibParameter value) {
        this.target = value; 
    }
    public int cardTarget () {
        if ( this.target == null ) return 0;
        else return 1;
    }

}

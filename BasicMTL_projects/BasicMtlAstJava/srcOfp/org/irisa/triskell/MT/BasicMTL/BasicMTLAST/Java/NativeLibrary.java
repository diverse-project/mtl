/*
 * $Id: NativeLibrary.java,v 1.3 2004-04-29 13:38:49 edrezen Exp $
 * Authors : modelware
 * 
 * Copyright 2004 - INRIA - LGPL license
 */
package org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java;

import org.irisa.triskell.MT.visitors.Java.GenericVisitor.*;
import org.irisa.triskell.MT.visitors.Java.AnalysingVisitor.*;
import org.irisa.triskell.MT.visitors.Java.GenericVisitor.Visitor;

/**
  * A library known to be implemented in target language (le language of the interpreter or the target language of the compiler).
  */
public class NativeLibrary 
    extends org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.BasicMtlLibrary
{

    public NativeLibrary(
        String name)
    {
        super(name);
    }

    public boolean isNative()
    {
return true;
    }
}

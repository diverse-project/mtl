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

package org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java;

import org.irisa.triskell.MT.visitors.Java.GenericVisitor.*;
import org.irisa.triskell.MT.visitors.Java.AnalysingVisitor.*;
import java.util.*;
import org.irisa.triskell.MT.visitors.Java.GenericVisitor.Visitor;
import org.irisa.triskell.MT.visitors.Java.AnalysingVisitor.Property;

/**
  * Any kind of instruction.
  */
abstract public class Instruction 
    extends org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.ASTNode
{
    protected org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Operation containerOp;
    public org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Operation getContainerOp () {
        return this.containerOp;
    }
    public void setContainerOp (org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Operation value) {
        this.containerOp = value; 
    }
    public int cardContainerOp () {
        if ( this.containerOp == null ) return 0;
        else return 1;
    }

}

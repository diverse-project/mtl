package org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java;



/**
  * Any kind of instruction.
  */
abstract public class Instruction 
    extends org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.ASTNode
{
    public org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.Operation containerOp;
    public org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.Operation getContainerOp () {
        return this.containerOp;
    }
    public void setContainerOp (org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.Operation value) {
        this.containerOp = value; 
    }
    public int cardContainerOp () {
        if ( this.containerOp == null ) return 0;
        else return 1;
    }

    public int lineNumber;
    public int getLineNumber () {
        return this.lineNumber;
    }
    public void setLineNumber (int value) {
        this.lineNumber = value; 
    }

}

package org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java;

import java.util.*;
import org.irisa.triskell.MT.visitors.Java.GenericVisitor.*;
import org.irisa.triskell.MT.visitors.Java.AnalysingVisitor.*;
import java.util.*;
import org.irisa.triskell.MT.visitors.Java.GenericVisitor.Visitor;
import org.irisa.triskell.MT.visitors.Java.AnalysingVisitor.Property;
import java.util.Map;
import java.lang.Object;

/**
  * Contains a block of instructions tha may thow an exception and make possible to treat it.
  */
public class Try 
    extends org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Instruction
{
    /**
      * The instruction sequence that may throw an exception.
      */
    public Vector tryBody = new Vector();
    public org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Instruction getTryBody (int i) {
        return (org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Instruction)this.tryBody.elementAt(i);
    }
    public void setTryBody (int i, org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Instruction value) {
        this.tryBody.setElementAt(value, i);
    }
    public void appendTryBody (org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Instruction value) {
        this.tryBody.addElement(value);
    }
    public void eraseTryBody (org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Instruction value) throws Exception {
        if (this.tryBody.size() == 1)
            throw new Exception ( "TooFewArgsInAssociation(1)" );
        else
            this.tryBody.removeElement(value);
    }
    public void eraseTryBody (int i) throws Exception {
        if (this.tryBody.size() == 1)
            throw new Exception ( "TooFewArgsInAssociation(1)" );
        else
            this.tryBody.removeElementAt(i);
    }
    public int cardTryBody () {
        return this.tryBody.size();
    }

    /**
      * A block of instructions wich is executed in any case. If an exception occurs here, it is re-thrown by the averall try execution.
      */
    public Vector finalizeBody = new Vector();
    public org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Instruction getFinalizeBody (int i) {
        return (org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Instruction)this.finalizeBody.elementAt(i);
    }
    public void setFinalizeBody (int i, org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Instruction value) {
        this.finalizeBody.setElementAt(value, i);
    }
    public void appendFinalizeBody (org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Instruction value) {
        this.finalizeBody.addElement(value);
    }
    public void eraseFinalizeBody (org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Instruction value)  {
        this.finalizeBody.removeElement(value);
    }
    public void eraseFinalizeBody (int i)  {
        this.finalizeBody.removeElementAt(i);
    }
    public int cardFinalizeBody () {
        return this.finalizeBody.size();
    }

    /**
      * The exception treatment possibilities.
      */
    public Vector catchPart = new Vector();
    public org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Catch getCatchPart (int i) {
        return (org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Catch)this.catchPart.elementAt(i);
    }
    public void setCatchPart (int i, org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Catch value) {
        this.catchPart.setElementAt(value, i);
    }
    public void appendCatchPart (org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Catch value) {
        this.catchPart.addElement(value);
    }
    public void eraseCatchPart (org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Catch value)  {
        this.catchPart.removeElement(value);
    }
    public void eraseCatchPart (int i)  {
        this.catchPart.removeElementAt(i);
    }
    public int cardCatchPart () {
        return this.catchPart.size();
    }

}

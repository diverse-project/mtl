package org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java;

import java.util.*;


/**
  * A loop of instruction execution: the sequence of instruction is performed after each time the condition is true.
  */
public class While 
    extends org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.Instruction
{
    /**
      * The loop stop contition; this must be a boolean value.
      */
    public org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.Expression condition;
    public org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.Expression getCondition () {
        return this.condition;
    }
    public void setCondition (org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.Expression value) {
        this.condition = value; 
    }
    public int cardCondition () {
        if ( this.condition == null ) return 0;
        else return 1;
    }

    /**
      * The sequence of instruction to be performed while the cointition is true.
      */
    public Vector body = new Vector();
    public org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.Instruction getBody (int i) {
        return (org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.Instruction)this.body.elementAt(i);
    }
    public void setBody (int i, org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.Instruction value) {
        this.body.setElementAt(value, i);
    }
    public void appendBody (org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.Instruction value) {
        this.body.addElement(value);
    }
    public void eraseBody (org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.Instruction value)  {
        this.body.removeElement(value);
    }
    public void eraseBody (int i)  {
        this.body.removeElementAt(i);
    }
    public int cardBody () {
        return this.body.size();
    }


    public While(
        int lineNumber)
    {
this.lineNumber=lineNumber;
    }
}

package org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java;

import java.util.*;
import org.irisa.triskell.MT.visitors.Java.GenericVisitor.*;
import org.irisa.triskell.MT.visitors.Java.AnalysingVisitor.*;
import org.irisa.triskell.MT.visitors.Java.GenericVisitor.Visitor;
import org.irisa.triskell.MT.visitors.Java.AnalysingVisitor.Property;

/**
  * An operation call. If the operation retuens a value, this expression is this value, else it remains in an undefined value. If the operation raises an exception, this operation call raises this exceptioni within the context of this instruction. This is a side effect instruction if calling a side effect operation.
  */
public class OperationCall 
    extends org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.Expression
{
    /**
      * The called operation.
      */
    public org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.Operation calledOperation;
    public org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.Operation getCalledOperation () {
        return this.calledOperation;
    }
    public int cardCalledOperation () {
        if ( this.calledOperation == null ) return 0;
        else return 1;
    }

    /**
      * The transmitted values for the operation called.
      */
    public Vector arguments = new Vector();
    public org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.Expression getArguments (int i) {
        return (org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.Expression)this.arguments.elementAt(i);
    }
    public void setArguments (int i, org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.Expression value) {
        this.arguments.setElementAt(value, i);
    }
    public void appendArguments (org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.Expression value) {
        this.arguments.addElement(value);
    }
    public void eraseArguments (org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.Expression value)  {
        this.arguments.removeElement(value);
    }
    public void eraseArguments (int i)  {
        this.arguments.removeElementAt(i);
    }
    public int cardArguments () {
        return this.arguments.size();
    }

    /**
      * The value to wich the operation call is sent. It must be of a class or subclass of the called operation owner. If the called operation is owned by a library (and not a class), this expression is empty.
      */
    public org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.Expression caller;
    public org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.Expression getCaller () {
        return this.caller;
    }
    public void setCaller (org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.Expression value) {
        this.caller = value; 
    }
    public int cardCaller () {
        if ( this.caller == null ) return 0;
        else return 1;
    }

    public String name;
    public String getName () {
        return this.name;
    }
    public void setName (String value) {
        this.name = value; 
    }

    public boolean isToInvoke;
    public boolean getIsToInvoke () {
        return this.isToInvoke;
    }
    public void setIsToInvoke (boolean value) {
        this.isToInvoke = value; 
    }

    public org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.OperationKind kind = OperationKind.getOperationCall();
    public org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.OperationKind getKind () {
        return this.kind;
    }
    public void setKind (org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.OperationKind value) {
        this.kind = value; 
    }
    public int cardKind () {
        if ( this.kind == null ) return 0;
        else return 1;
    }


    public OperationCall(
        String name,
        int lineNumber)
    {
this.name=name;
this.lineNumber=lineNumber;
this.isToInvoke=false;
    }
}

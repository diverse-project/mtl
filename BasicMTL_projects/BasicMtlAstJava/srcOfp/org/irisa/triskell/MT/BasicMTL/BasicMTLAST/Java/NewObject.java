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
  * Creates a new value of the defined class. This is a side effect operation.
  */
public class NewObject 
    extends org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Expression
{
    /**
      * The class to be instanciated.
      */
    public org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.UserClass type;
    public org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.UserClass getType () {
        return this.type;
    }
    public int cardType () {
        if ( this.type == null ) return 0;
        else return 1;
    }

    /**
      * Some arguments to be given to an eventual constructor. This is valid only if building a model element.
      */
    public Vector arguments = new Vector();
    public org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Expression getArguments (int i) {
        return (org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Expression)this.arguments.elementAt(i);
    }
    public void setArguments (int i, org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Expression value) {
        this.arguments.setElementAt(value, i);
    }
    public void appendArguments (org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Expression value) {
        this.arguments.addElement(value);
    }
    public void eraseArguments (org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Expression value)  {
        this.arguments.removeElement(value);
    }
    public void eraseArguments (int i)  {
        this.arguments.removeElementAt(i);
    }
    public int cardArguments () {
        return this.arguments.size();
    }

}

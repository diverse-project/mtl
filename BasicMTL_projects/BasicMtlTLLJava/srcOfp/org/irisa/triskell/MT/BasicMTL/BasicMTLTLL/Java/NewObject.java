package org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java;

import java.util.*;


/**
  * Creates a new value of the defined class. This is a side effect operation.
  */
public class NewObject 
    extends org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.Expression
{
    /**
      * Some arguments to be given to an eventual constructor. This is valid only if building a model element.
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

    public org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.QualifiedName typeToCreate;
    public org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.QualifiedName getTypeToCreate () {
        return this.typeToCreate;
    }
    public void setTypeToCreate (org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.QualifiedName value) {
        this.typeToCreate = value; 
    }
    public int cardTypeToCreate () {
        if ( this.typeToCreate == null ) return 0;
        else return 1;
    }

    public boolean modelEltCreation;
    public boolean getModelEltCreation () {
        return this.modelEltCreation;
    }
    public void setModelEltCreation (boolean value) {
        this.modelEltCreation = value; 
    }


    public NewObject(
        int lineNumber)
    {
this.lineNumber=lineNumber;
this.modelEltCreation=false;
    }
}

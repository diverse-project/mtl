package org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java;

import java.util.*;
import org.irisa.triskell.MT.visitors.Java.GenericVisitor.*;
import org.irisa.triskell.MT.visitors.Java.AnalysingVisitor.*;

/**
  * A library wtitten in Basic MTL.
  */
public class BasicMtlLibrary 
    extends org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Library
{
    /**
      * A library used as an entry point must provide an interface to fill parameters values. These values may be transmitted to the used libraries (see Use).
      */
    protected Vector parameters = new Vector();
    public org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.LibParameter getParameters (int i) {
        return (org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.LibParameter)this.parameters.elementAt(i);
    }
    public void setParameters (int i, org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.LibParameter value) {
        this.parameters.setElementAt(value, i);
    }
    public void appendParameters (org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.LibParameter value) {
        this.parameters.addElement(value);
    }
    public void eraseParameters (org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.LibParameter value)  {
        this.parameters.removeElement(value);
    }
    public void eraseParameters (int i)  {
        this.parameters.removeElementAt(i);
    }
    public int cardParameters () {
        return this.parameters.size();
    }

    /**
      * The extended libraries. The extensions are given by the name; the extending element (either class or operation) must have the name of the extended one(s). This mechanism allows the concept of view / adapter (see TypedModelRef).
      */
    protected Vector extendedLib = new Vector();
    public org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.BasicMtlLibrary getExtendedLib (int i) {
        return (org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.BasicMtlLibrary)this.extendedLib.elementAt(i);
    }
    public void setExtendedLib (int i, org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.BasicMtlLibrary value) {
        this.extendedLib.setElementAt(value, i);
    }
    public void appendExtendedLib (org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.BasicMtlLibrary value) {
        this.extendedLib.addElement(value);
    }
    public void eraseExtendedLib (org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.BasicMtlLibrary value)  {
        this.extendedLib.removeElement(value);
    }
    public void eraseExtendedLib (int i)  {
        this.extendedLib.removeElementAt(i);
    }
    public int cardExtendedLib () {
        return this.extendedLib.size();
    }


    public BasicMtlLibrary(
        String name)
    {
        super(name);
    }

    public boolean isNative()
    {
return false;
    }
}

/*
 * $Id: BasicMtlLibrary.java,v 1.7 2004-04-29 13:55:32 edrezen Exp $
 * Authors : modelware
 * 
 * Copyright 2004 - INRIA - LGPL license
 */
package org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java;

import java.util.*;


/**
  * A library wtitten in Basic MTL.
  */
public class BasicMtlLibrary 
    extends org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.Library
{
    /**
      * A library used as an entry point must provide an interface to fill parameters values. These values may be transmitted to the used libraries (see Use).
      */
    public Vector usedModels = new Vector();
    public org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.ModelRef getUsedModels (int i) {
        return (org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.ModelRef)this.usedModels.elementAt(i);
    }
    public void setUsedModels (int i, org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.ModelRef value) {
        this.usedModels.setElementAt(value, i);
    }
    public void appendUsedModels (org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.ModelRef value) {
        this.usedModels.addElement(value);
    }
    public void eraseUsedModels (org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.ModelRef value)  {
        this.usedModels.removeElement(value);
    }
    public void eraseUsedModels (int i)  {
        this.usedModels.removeElementAt(i);
    }
    public int cardUsedModels () {
        return this.usedModels.size();
    }

    public Vector classes = new Vector();
    public org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.UserDefinedClass getClasses (int i) {
        return (org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.UserDefinedClass)this.classes.elementAt(i);
    }
    public void setClasses (int i, org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.UserDefinedClass value) {
        this.classes.setElementAt(value, i);
    }
    public void appendClasses (org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.UserDefinedClass value) {
        this.classes.addElement(value);
    }
    public void eraseClasses (org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.UserDefinedClass value) throws Exception {
        if (this.classes.size() == 1)
            throw new Exception ( "TooFewArgsInAssociation(1)" );
        else
            this.classes.removeElement(value);
    }
    public void eraseClasses (int i) throws Exception {
        if (this.classes.size() == 1)
            throw new Exception ( "TooFewArgsInAssociation(1)" );
        else
            this.classes.removeElementAt(i);
    }
    public int cardClasses () {
        return this.classes.size();
    }

    public org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.TheLibraryClass libraryClass;
    public org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.TheLibraryClass getLibraryClass () {
        return this.libraryClass;
    }
    public void setLibraryClass (org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.TheLibraryClass value) {
        this.libraryClass = value; 
    }
    public int cardLibraryClass () {
        if ( this.libraryClass == null ) return 0;
        else return 1;
    }

    public Vector usedLibs = new Vector();
    public org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.QualifiedName getUsedLibs (int i) {
        return (org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.QualifiedName)this.usedLibs.elementAt(i);
    }
    public void setUsedLibs (int i, org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.QualifiedName value) {
        this.usedLibs.setElementAt(value, i);
    }
    public void appendUsedLibs (org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.QualifiedName value) {
        this.usedLibs.addElement(value);
    }
    public void eraseUsedLibs (org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.QualifiedName value)  {
        this.usedLibs.removeElement(value);
    }
    public void eraseUsedLibs (int i)  {
        this.usedLibs.removeElementAt(i);
    }
    public int cardUsedLibs () {
        return this.usedLibs.size();
    }


    public BasicMtlLibrary(
        String name,
        String mangle,
        String packageName,
        int lineNumber)
    {
super(name,mangle,packageName,lineNumber);
    }

    public boolean isNative()
    {
return false;
    }
}

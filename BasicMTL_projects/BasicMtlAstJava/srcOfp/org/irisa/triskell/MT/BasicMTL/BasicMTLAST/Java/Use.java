/*
 * $Id: Use.java,v 1.3 2004-04-29 13:38:51 edrezen Exp $
 * Authors : modelware
 * 
 * Copyright 2004 - INRIA - LGPL license
 */
package org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java;

import java.util.*;
import org.irisa.triskell.MT.visitors.Java.GenericVisitor.*;
import org.irisa.triskell.MT.visitors.Java.AnalysingVisitor.*;
import org.irisa.triskell.MT.visitors.Java.GenericVisitor.Visitor;
import org.irisa.triskell.MT.visitors.Java.AnalysingVisitor.Property;
import java.util.Map;
import java.lang.Object;

/**
  * Indicates usage between two libraries. In that a library may have some parameters, the used library may be seen as an "instanciation" with specific parameters.
  */
public class Use 
    extends org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.ASTNode
{
    /**
      * The used library.
      */
    protected org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Library usedLibrary;
    public org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Library getUsedLibrary () {
        return this.usedLibrary;
    }
    public void setUsedLibrary (org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Library value) {
        this.usedLibrary = value; 
    }
    public int cardUsedLibrary () {
        if ( this.usedLibrary == null ) return 0;
        else return 1;
    }

    /**
      * Parameter binding for a library usage.
      */
    protected Vector modelsAssociations = new Vector();
    public org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.ParameterAssociation getModelsAssociations (int i) {
        return (org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.ParameterAssociation)this.modelsAssociations.elementAt(i);
    }
    public void setModelsAssociations (int i, org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.ParameterAssociation value) {
        this.modelsAssociations.setElementAt(value, i);
    }
    public void appendModelsAssociations (org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.ParameterAssociation value) {
        this.modelsAssociations.addElement(value);
    }
    public void eraseModelsAssociations (org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.ParameterAssociation value)  {
        this.modelsAssociations.removeElement(value);
    }
    public void eraseModelsAssociations (int i)  {
        this.modelsAssociations.removeElementAt(i);
    }
    public int cardModelsAssociations () {
        return this.modelsAssociations.size();
    }

    /**
      * The name of the imported library used in the importing one.  In that a same library may be used with different parameters, it is necessary to differenciate them, thanks to a name.
      */
    protected String name;
    public String getName () {
        return this.name;
    }
    public void setName (String value) {
        this.name = value; 
    }

}

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
  * A link implementation between model elements (of the same model).
  */
public class Associate 
    extends org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Instruction
{
    /**
      * Representation of model elements to be linked.
      */
    public Vector roles = new Vector();
    public org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Role getRoles (int i) {
        return (org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Role)this.roles.elementAt(i);
    }
    public void setRoles (int i, org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Role value) {
        this.roles.setElementAt(value, i);
    }
    public void appendRoles (org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Role value) {
        this.roles.addElement(value);
    }
    public void eraseRoles (org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Role value)  {
        this.roles.removeElement(value);
    }
    public void eraseRoles (int i)  {
        this.roles.removeElementAt(i);
    }
    public int cardRoles () {
        return this.roles.size();
    }

    /**
      * The name of the association to be implemented.
      */
    public String AssociationName;
    public String getAssociationName () {
        return this.AssociationName;
    }

}

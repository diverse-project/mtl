package org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java;

import java.util.*;
import org.irisa.triskell.MT.visitors.Java.GenericVisitor.*;
import org.irisa.triskell.MT.visitors.Java.AnalysingVisitor.*;
import org.irisa.triskell.MT.visitors.Java.GenericVisitor.Visitor;
import org.irisa.triskell.MT.visitors.Java.AnalysingVisitor.Property;

/**
  * The usual concept of class; this is the stakeholder for attributes and operations. Its name is deterministic within the library. It may extends other classes.
  */
public class UserClass 
    extends org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.ASTNode
{
    /**
      * Operations owned by the class.
      */
    protected Vector definedMethods = new Vector();
    public org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Operation getDefinedMethods (int i) {
        return (org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Operation)this.definedMethods.elementAt(i);
    }
    public void setDefinedMethods (int i, org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Operation value) {
        this.definedMethods.setElementAt(value, i);
    }
    public void appendDefinedMethods (org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Operation value) {
        this.definedMethods.addElement(value);
    }
    public void eraseDefinedMethods (org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Operation value)  {
        this.definedMethods.removeElement(value);
    }
    public void eraseDefinedMethods (int i)  {
        this.definedMethods.removeElementAt(i);
    }
    public int cardDefinedMethods () {
        return this.definedMethods.size();
    }

    /**
      * Attributes owned by the class.
      */
    protected Vector definedAttributes = new Vector();
    public org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Attribute getDefinedAttributes (int i) {
        return (org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Attribute)this.definedAttributes.elementAt(i);
    }
    public void setDefinedAttributes (int i, org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Attribute value) {
        this.definedAttributes.setElementAt(value, i);
    }
    public void appendDefinedAttributes (org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Attribute value) {
        this.definedAttributes.addElement(value);
    }
    public void eraseDefinedAttributes (org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Attribute value)  {
        this.definedAttributes.removeElement(value);
    }
    public void eraseDefinedAttributes (int i)  {
        this.definedAttributes.removeElementAt(i);
    }
    public int cardDefinedAttributes () {
        return this.definedAttributes.size();
    }

    /**
      * The parent classes.
      */
    protected Vector parentClasses = new Vector();
    public org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.UserClass getParentClasses (int i) {
        return (org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.UserClass)this.parentClasses.elementAt(i);
    }
    public void setParentClasses (int i, org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.UserClass value) {
        this.parentClasses.setElementAt(value, i);
    }
    public void appendParentClasses (org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.UserClass value) {
        this.parentClasses.addElement(value);
    }
    public void eraseParentClasses (org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.UserClass value)  {
        this.parentClasses.removeElement(value);
    }
    public void eraseParentClasses (int i)  {
        this.parentClasses.removeElementAt(i);
    }
    public int cardParentClasses () {
        return this.parentClasses.size();
    }

    public String name;
    public String getName () {
        return this.name;
    }
    public void setName (String value) {
        this.name = value; 
    }

    /**
      * QualifiedName is only used for "RepositoryRef" classes which are behind a repository.
      * An example of UML2 qualified name is : Core::InfraastructureLibrary:: ...::Class
      */
    protected Vector QualifiedName = new Vector();
    public String getQualifiedName (int i) {
        return (String)this.QualifiedName.elementAt(i);
    }
    public void setQualifiedName (int i, String value) {
        this.QualifiedName.setElementAt(value, i);
    }
    public void appendQualifiedName (String value) {
        this.QualifiedName.addElement(value);
    }
    public void eraseQualifiedName (String value) {
        this.QualifiedName.removeElement(value);
    }
    public void eraseQualifiedName (int i) {
        this.QualifiedName.removeElementAt(i);
    }
    public int cardQualifiedName () {
        return this.QualifiedName.size();
    }


    public UserClass(
        String name)
    {
this.name=name;
    }

    public org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Attribute findAttribute(
        String attributeName)
    {
for (int i=0;i<this.cardDefinedAttributes();i++)
{ if (this.getDefinedAttributes(i).getName().equals(attributeName))
return this.getDefinedAttributes(i);
}
return null;
    }
}

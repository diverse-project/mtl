package org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java;

import java.util.*;
import org.irisa.triskell.MT.visitors.Java.GenericVisitor.*;
import org.irisa.triskell.MT.visitors.Java.AnalysingVisitor.*;
import org.irisa.triskell.MT.visitors.Java.GenericVisitor.Visitor;
import org.irisa.triskell.MT.visitors.Java.AnalysingVisitor.Property;
import java.io.IOException;

/**
  * A library is the stakeholder for classes. It may also contains operations. Its name is deterministic within the collection of all accessible libraries.
  */
abstract public class Library 
    extends org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.ASTNode
    implements org.irisa.triskell.MT.visitors.Java.GenericVisitor.Visitable
{
    protected Vector definedClasses = new Vector();
    public org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.UserClass getDefinedClasses (int i) {
        return (org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.UserClass)this.definedClasses.elementAt(i);
    }
    public void setDefinedClasses (int i, org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.UserClass value) {
        this.definedClasses.setElementAt(value, i);
    }
    public void appendDefinedClasses (org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.UserClass value) {
        this.definedClasses.addElement(value);
    }
    public void eraseDefinedClasses (org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.UserClass value) throws Exception {
        if (this.definedClasses.size() == 1)
            throw new Exception ( "TooFewArgsInAssociation(1)" );
        else
            this.definedClasses.removeElement(value);
    }
    public void eraseDefinedClasses (int i) throws Exception {
        if (this.definedClasses.size() == 1)
            throw new Exception ( "TooFewArgsInAssociation(1)" );
        else
            this.definedClasses.removeElementAt(i);
    }
    public int cardDefinedClasses () {
        return this.definedClasses.size();
    }

    /**
      * Any of these operation may be an entry point (parameters constraint ?)
      */
    protected Vector definedOperations = new Vector();
    public org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Operation getDefinedOperations (int i) {
        return (org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Operation)this.definedOperations.elementAt(i);
    }
    public void setDefinedOperations (int i, org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Operation value) {
        this.definedOperations.setElementAt(value, i);
    }
    public void appendDefinedOperations (org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Operation value) {
        this.definedOperations.addElement(value);
    }
    public void eraseDefinedOperations (org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Operation value)  {
        this.definedOperations.removeElement(value);
    }
    public void eraseDefinedOperations (int i)  {
        this.definedOperations.removeElementAt(i);
    }
    public int cardDefinedOperations () {
        return this.definedOperations.size();
    }

    public String name;
    public String getName () {
        return this.name;
    }
    public void setName (String value) {
        this.name = value; 
    }

    /**
      * Indicates if the library is "abstract", that is if its contents are to be redefined in a more concrete library.
      */
    public boolean isUsable;
    public boolean getIsUsable () {
        return this.isUsable;
    }
    public void setIsUsable (boolean value) {
        this.isUsable = value; 
    }

    /**
      * The used libraries.
      */
    protected Vector uses = new Vector();
    public org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Use getUses (int i) {
        return (org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Use)this.uses.elementAt(i);
    }
    public void setUses (int i, org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Use value) {
        this.uses.setElementAt(value, i);
    }
    public void appendUses (org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Use value) {
        this.uses.addElement(value);
    }
    public void eraseUses (org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Use value)  {
        this.uses.removeElement(value);
    }
    public void eraseUses (int i)  {
        this.uses.removeElementAt(i);
    }
    public int cardUses () {
        return this.uses.size();
    }

    protected Vector definedAssociations = new Vector();
    public org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Association getDefinedAssociations (int i) {
        return (org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Association)this.definedAssociations.elementAt(i);
    }
    public void setDefinedAssociations (int i, org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Association value) {
        this.definedAssociations.setElementAt(value, i);
    }
    public void appendDefinedAssociations (org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Association value) {
        this.definedAssociations.addElement(value);
    }
    public void eraseDefinedAssociations (org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Association value)  {
        this.definedAssociations.removeElement(value);
    }
    public void eraseDefinedAssociations (int i)  {
        this.definedAssociations.removeElementAt(i);
    }
    public int cardDefinedAssociations () {
        return this.definedAssociations.size();
    }


    public Library(
        String name)
    {
this.name=name;
    }

    /**
      * Retreives a library definition (without the operation implementation).
      */
    public static org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Library load(
        String Filename)
    {
 java.io.ObjectInputStream in;
		org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Library Lib=null;
		try { in=new java.io.ObjectInputStream(new java.io.FileInputStream(Filename));
					 Lib=(org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Library)in.readObject();
					 in.close();
			  } catch(Exception e) { System.err.println("Load: inputstream problem=>"+Filename); }	
 return(Lib);
    }

    /**
      * Saves the libtrary definition (without operation implementation).
      */
    public static void store(
        String Filename,
        org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Library Lib,
        String Directoryname)
    {
 java.io.ObjectOutputStream out;
 java.io.File directoryFile=new java.io.File(Directoryname);
 if (! directoryFile.isDirectory())
	 try { directoryFile.mkdir();
	 } catch (SecurityException e) {System.err.println("Can't make directory "+Directoryname); }
   try { out=new java.io.ObjectOutputStream(new java.io.FileOutputStream(Directoryname+Filename));
      	     out.writeObject(Lib);
      	     out.close();
      } catch(java.io.IOException e) { System.err.println("Store: outputstream problem=>"+Directoryname+Filename);
      									e.printStackTrace(System.err); }
    }

    public abstract boolean isNative();
}

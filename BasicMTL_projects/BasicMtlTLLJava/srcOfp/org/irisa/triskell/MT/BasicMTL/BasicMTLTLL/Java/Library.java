package org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java;

import org.irisa.triskell.MT.visitors.Java.GenericVisitor.*;
import org.irisa.triskell.MT.visitors.Java.AnalysingVisitor.*;
import java.util.*;
import org.irisa.triskell.MT.visitors.Java.GenericVisitor.Visitor;
import org.irisa.triskell.MT.visitors.Java.AnalysingVisitor.Property;
import java.util.Map;
import java.io.IOException;

/**
  * A library is the stakeholder for classes. It may also contains operations. Its name is deterministic within the collection of all accessible libraries.
  */
abstract public class Library 
    extends org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.ASTNode
    implements org.irisa.triskell.MT.visitors.Java.GenericVisitor.Visitable
{
    public String name;
    public String getName () {
        return this.name;
    }
    public void setName (String value) {
        this.name = value; 
    }

    public String mangle;
    public String getMangle () {
        return this.mangle;
    }
    public void setMangle (String value) {
        this.mangle = value; 
    }

    public org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.KnownClasses knownTypes;
    public org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.KnownClasses getKnownTypes () {
        return this.knownTypes;
    }
    public void setKnownTypes (org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.KnownClasses value) {
        this.knownTypes = value; 
    }
    public int cardKnownTypes () {
        if ( this.knownTypes == null ) return 0;
        else return 1;
    }

    public org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.KnownOperations knownLibOperations;
    public org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.KnownOperations getKnownLibOperations () {
        return this.knownLibOperations;
    }
    public void setKnownLibOperations (org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.KnownOperations value) {
        this.knownLibOperations = value; 
    }
    public int cardKnownLibOperations () {
        if ( this.knownLibOperations == null ) return 0;
        else return 1;
    }

    public String packageName;
    public String getPackageName () {
        return this.packageName;
    }
    public void setPackageName (String value) {
        this.packageName = value; 
    }

    public org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.AllReferedTypes allReferedTypes;
    public org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.AllReferedTypes getAllReferedTypes () {
        return this.allReferedTypes;
    }
    public void setAllReferedTypes (org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.AllReferedTypes value) {
        this.allReferedTypes = value; 
    }
    public int cardAllReferedTypes () {
        if ( this.allReferedTypes == null ) return 0;
        else return 1;
    }

    public int lineNumber;
    public int getLineNumber () {
        return this.lineNumber;
    }
    public void setLineNumber (int value) {
        this.lineNumber = value; 
    }


    public Library(
        String name,
        String mangle,
        String packageName,
        int lineNumber)
    {
this.name=name;
this.mangle=mangle;
this.packageName=packageName;
this.lineNumber=lineNumber;
    }

    /**
      * Retreives a library definition (without the operation implementation).
      */
    public static org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.Library load(
        String Filename)
    {
java.io.ObjectInputStream in;
		org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.Library Lib=null;
		try { in=new java.io.ObjectInputStream(new java.io.FileInputStream(Filename));
					 Lib=(org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.Library)in.readObject();
					 in.close();
			  } catch(Exception e) { System.err.println("Load: inputstream problem=>"+Filename); }	
 return(Lib);
    }

    /**
      * Saves the libtrary definition (without operation implementation).
      */
    public static void store(
        String Filename,
        org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.Library Lib,
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
}

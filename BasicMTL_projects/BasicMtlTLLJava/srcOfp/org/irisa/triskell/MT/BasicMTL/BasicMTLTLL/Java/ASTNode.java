package org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java;

import java.util.*; 
// import org.irisa.triskell.MT.visitors.Java.GenericVisitor.*;
import org.irisa.triskell.MT.visitors.Java.AnalysingVisitor.*;
// import org.irisa.triskell.MT.visitors.Java.GenericVisitor.Visitor;

/**
  * Any node involved in the Basic MTL abstract syntax. 
  * This class implements the Visitable interface wich provides facilites to implement a tree visit.
  * This class inherits the java Serializable interface to easily store and reteive a tree.
  * Decorations are implemented by an association with the Property class (key / value pairs). These properties may be organized in domains (class ProipertyDomain).
  */
abstract public class ASTNode 
    implements java.io.Serializable, org.irisa.triskell.MT.visitors.Java.GenericVisitor.Visitable
{
    /**
      * private void writeObject(java.io.ObjectOutputStream out)
      *                                         throws IOException
      *                                     private void readObject(java.io.ObjectInputStream in)
      *                                         throws IOException, ClassNotFoundException;
      */
    /**
      * The properties that decorates this node.
      */
    public Vector decoration = new Vector();
    public org.irisa.triskell.MT.visitors.Java.AnalysingVisitor.Property getDecoration (int i) {
        return (org.irisa.triskell.MT.visitors.Java.AnalysingVisitor.Property)this.decoration.elementAt(i);
    }
    public void setDecoration (int i, org.irisa.triskell.MT.visitors.Java.AnalysingVisitor.Property value) {
        this.decoration.setElementAt(value, i);
    }
    public void appendDecoration (org.irisa.triskell.MT.visitors.Java.AnalysingVisitor.Property value) {
        this.decoration.addElement(value);
    }
    public void eraseDecoration (org.irisa.triskell.MT.visitors.Java.AnalysingVisitor.Property value)  {
        this.decoration.removeElement(value);
    }
    public void eraseDecoration (int i)  {
        this.decoration.removeElementAt(i);
    }
    public int cardDecoration () {
        return this.decoration.size();
    }


    public void setProperty(
        String key,
        java.lang.Object value)
    {
		this.findProperty(key).setValue(value);
    }

    public org.irisa.triskell.MT.visitors.Java.AnalysingVisitor.Property getProperty(
        String key)
    {
	Property property = this.findProperty(key);
	return property == null ? null : property;
    }

    public void createNewProperty(
        String key,
        java.lang.Object value,
        String tagType)
    {
		appendDecoration (new org.irisa.triskell.MT.visitors.Java.AnalysingVisitor.Property(key,value,tagType));
    }

    /**
      * decoration.append(new Property(key,value));
      */
    public void accept(
        org.irisa.triskell.MT.visitors.Java.GenericVisitor.Visitor v,
        java.util.Map context)
    {
		v.visit(this, context);	
    }

    /**
      * decoration.append(new Property(key,value));
      */
    protected org.irisa.triskell.MT.visitors.Java.AnalysingVisitor.Property findProperty(
        String name)
    {
    	java.util.Iterator properties = this.decoration.iterator();
    	Property cur;
    	while (properties.hasNext()) {
    		cur = (Property)properties.next();
    		if (cur.getName().equals(name))
    			return cur;
    	}
    	return null;
    }
}

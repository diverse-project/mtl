package org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java;

import java.util.*;
import org.irisa.triskell.MT.visitors.Java.GenericVisitor.*;
import org.irisa.triskell.MT.visitors.Java.AnalysingVisitor.*;
import org.irisa.triskell.MT.visitors.Java.GenericVisitor.Visitor;
import org.irisa.triskell.MT.visitors.Java.AnalysingVisitor.Property;
import java.util.Map;
import java.lang.Object;

public class Association 
    extends org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.ASTNode
{
    protected String name;
    public String getName () {
        return this.name;
    }
    public void setName (String value) {
        this.name = value; 
    }

    protected Vector endPoints = new Vector();
    public org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.EndPoint getEndPoints (int i) {
        return (org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.EndPoint)this.endPoints.elementAt(i);
    }
    public void setEndPoints (int i, org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.EndPoint value) {
        this.endPoints.setElementAt(value, i);
    }
    public void appendEndPoints (org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.EndPoint value) {
        this.endPoints.addElement(value);
    }
    public void eraseEndPoints (org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.EndPoint value) throws Exception {
        if (this.endPoints.size() == 1)
            throw new Exception ( "TooFewArgsInAssociation(1)" );
        else
            this.endPoints.removeElement(value);
    }
    public void eraseEndPoints (int i) throws Exception {
        if (this.endPoints.size() == 1)
            throw new Exception ( "TooFewArgsInAssociation(1)" );
        else
            this.endPoints.removeElementAt(i);
    }
    public int cardEndPoints () {
        return this.endPoints.size();
    }


    public Association(
        String name)
    {
this.name=name;
    }
}

package org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java;

import java.util.*;
import org.irisa.triskell.MT.visitors.Java.GenericVisitor.Visitor;
import org.irisa.triskell.MT.visitors.Java.AnalysingVisitor.Property;
import java.util.Map;
import java.lang.Object;

public class OclAsType 
    extends org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Expression
{
    public org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Expression expression;
    public org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Expression getExpression () {
        return this.expression;
    }
    public void setExpression (org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Expression value) {
        this.expression = value; 
    }
    public int cardExpression () {
        if ( this.expression == null ) return 0;
        else return 1;
    }

    public Vector type = new Vector();
    public String getType (int i) {
        return (String)this.type.elementAt(i);
    }
    public void setType (int i, String value) {
        this.type.setElementAt(value, i);
    }
    public void appendType (String value) {
        this.type.addElement(value);
    }
    public void eraseType (String value) {
        this.type.removeElement(value);
    }
    public void eraseType (int i) {
        this.type.removeElementAt(i);
    }
    public int cardType () {
        return this.type.size();
    }

}

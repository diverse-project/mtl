package org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java;

import java.util.*;
import org.irisa.triskell.MT.visitors.Java.GenericVisitor.Visitor;
import org.irisa.triskell.MT.visitors.Java.AnalysingVisitor.Property;
import java.util.Map;
import java.lang.Object;

public class OperationKind 
    extends org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.ASTNode
{
    public static org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.OperationKind OperationCall = new OperationKind(0);
    public static org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.OperationKind getOperationCall () {
        return OperationKind.OperationCall;
    }
    public static int cardOperationCall () {
        if ( OperationKind.OperationCall == null ) return 0;
        else return 1;
    }

    public static org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.OperationKind AttributeCall = new OperationKind(1);
    public static org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.OperationKind getAttributeCall () {
        return OperationKind.AttributeCall;
    }
    public static int cardAttributeCall () {
        if ( OperationKind.AttributeCall == null ) return 0;
        else return 1;
    }

    public static org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.OperationKind AttributeSet = new OperationKind(2);
    public static org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.OperationKind getAttributeSet () {
        return OperationKind.AttributeSet;
    }
    public static int cardAttributeSet () {
        if ( OperationKind.AttributeSet == null ) return 0;
        else return 1;
    }

    private final int kind;


    private OperationKind(
        int kind)
    {
		this.kind = kind;
    }

    public boolean equals(
        java.lang.Object rhs)
    {
		return this == rhs || ((rhs instanceof OperationKind) && ((OperationKind)rhs).kind == this.kind);
    }

    public void Operation()
    {
    }
}

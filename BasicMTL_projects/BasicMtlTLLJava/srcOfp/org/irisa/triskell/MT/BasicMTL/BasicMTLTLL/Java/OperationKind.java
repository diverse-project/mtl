/*
 * $Id: OperationKind.java,v 1.4 2004-04-29 13:55:32 edrezen Exp $
 * Authors : modelware
 * 
 * Copyright 2004 - INRIA - LGPL license
 */
package org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java;



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

    public static org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.OperationKind CurrentLibraryCall = new OperationKind(3);
    public static org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.OperationKind getCurrentLibraryCall () {
        return OperationKind.CurrentLibraryCall;
    }
    public static int cardCurrentLibraryCall () {
        if ( OperationKind.CurrentLibraryCall == null ) return 0;
        else return 1;
    }

    public static org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.OperationKind LibraryCall = new OperationKind(4);
    public static org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.OperationKind getLibraryCall () {
        return OperationKind.LibraryCall;
    }
    public static int cardLibraryCall () {
        if ( OperationKind.LibraryCall == null ) return 0;
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

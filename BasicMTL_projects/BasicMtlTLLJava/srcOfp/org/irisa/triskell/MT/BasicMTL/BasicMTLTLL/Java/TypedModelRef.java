package org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java;



/**
  * A view described by a Basic MTL library. Source parameters for binding must be compatible: it must also be a TypedModelRef, typed by the same BasicMTLLibrary ore one of its sub-libraries.
  */
public class TypedModelRef 
    extends org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.ModelRef
{
    /**
      * The type of the referenced library.
      */
    public org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.BasicMtlLibrary type;
    public org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.BasicMtlLibrary getType () {
        return this.type;
    }
    public void setType (org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.BasicMtlLibrary value) {
        this.type = value; 
    }
    public int cardType () {
        if ( this.type == null ) return 0;
        else return 1;
    }

    public String view;
    public String getView () {
        return this.view;
    }
    public void setView (String value) {
        this.view = value; 
    }


    public TypedModelRef(
        String name,
        String typeName,
        int lineNumber)
    {
super(name,lineNumber,true);
this.view=typeName;
    }
}

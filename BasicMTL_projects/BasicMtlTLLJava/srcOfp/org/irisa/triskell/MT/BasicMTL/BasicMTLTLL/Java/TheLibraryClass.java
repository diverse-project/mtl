package org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java;



public class TheLibraryClass 
    extends org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.UserDefinedClass
{
    public org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.BasicMtlLibrary theLibrary;
    public org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.BasicMtlLibrary getTheLibrary () {
        return this.theLibrary;
    }
    public void setTheLibrary (org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.BasicMtlLibrary value) {
        this.theLibrary = value; 
    }
    public int cardTheLibrary () {
        if ( this.theLibrary == null ) return 0;
        else return 1;
    }


    public TheLibraryClass(
        String name,
        String mangle,
        int lineNumber)
    {
super(name,mangle,lineNumber);
    }
}

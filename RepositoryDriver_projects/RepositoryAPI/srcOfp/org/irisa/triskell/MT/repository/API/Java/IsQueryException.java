package org.irisa.triskell.MT.repository.API.Java;

import java.lang.Exception;

public class IsQueryException 
    extends java.lang.Exception
{
    public final org.irisa.triskell.MT.repository.API.Java.MetaOperation sideEffectOperation;
    public org.irisa.triskell.MT.repository.API.Java.MetaOperation getSideEffectOperation () {
        return this.sideEffectOperation;
    }
    public int cardSideEffectOperation () {
        if ( this.sideEffectOperation == null ) return 0;
        else return 1;
    }


    public IsQueryException(
        org.irisa.triskell.MT.repository.API.Java.MetaOperation sideEffectOperation)
    {
        super("Illegal side effect operation call on " + sideEffectOperation.toString());
    	this.sideEffectOperation = sideEffectOperation;
    }
}

package org.irisa.triskell.MT.visitors.Java.GenericVisitor;

import java.util.Map;

public interface Visitable 
{

     void accept(
        org.irisa.triskell.MT.visitors.Java.GenericVisitor.Visitor v,
        java.util.Map context);
}

package org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java;

import org.irisa.triskell.MT.visitors.Java.GenericVisitor.*;
import org.irisa.triskell.MT.visitors.Java.AnalysingVisitor.*;
import java.util.*;
import org.irisa.triskell.MT.visitors.Java.GenericVisitor.Visitor;
import org.irisa.triskell.MT.visitors.Java.AnalysingVisitor.Property;
import java.util.Map;
import java.lang.Object;

/**
  * An acces to a model beside a model repository (see org.irisa.triskell.MT.repository.API).
  */
public class RepositoryRef 
    extends org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.ModelRef
{

    public RepositoryRef(
        String name)
    {
super(name);
    }
}

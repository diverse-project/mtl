package org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java;

import org.irisa.triskell.MT.visitors.Java.GenericVisitor.Visitor;
import org.irisa.triskell.MT.visitors.Java.AnalysingVisitor.Property;
import java.util.Map;

/**
  * An acces to a model beside a model repository (see org.irisa.triskell.MT.repository.API).
  */
public class RepositoryRef 
    extends org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.ModelRef
{

    public RepositoryRef(
        String name,
        int lineNumber)
    {
super(name,lineNumber,false);
    }
}

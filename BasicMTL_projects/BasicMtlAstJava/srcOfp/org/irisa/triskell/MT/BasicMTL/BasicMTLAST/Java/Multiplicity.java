package org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java;

import org.irisa.triskell.MT.visitors.Java.GenericVisitor.*;
import org.irisa.triskell.MT.visitors.Java.AnalysingVisitor.*;
import java.util.*;
import org.irisa.triskell.MT.visitors.Java.GenericVisitor.Visitor;
import org.irisa.triskell.MT.visitors.Java.AnalysingVisitor.Property;
import java.util.Map;
import java.lang.Object;

public class Multiplicity 
{
    protected int lowerBound;
    public int getLowerBound () {
        return this.lowerBound;
    }
    public void setLowerBound (int value) {
        this.lowerBound = value; 
    }

    protected int upperBound;
    public int getUpperBound () {
        return this.upperBound;
    }
    public void setUpperBound (int value) {
        this.upperBound = value; 
    }


    public Multiplicity(
        int lowerBound,
        int upperBound)
    {
this.lowerBound=lowerBound;
this.upperBound=upperBound;
    }
}

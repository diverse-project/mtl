package org.irisa.triskell.MT.visitors.Java.AnalysingVisitor;

import org.irisa.triskell.MT.visitors.Java.GenericVisitor.*;
import java.lang.*;
import java.io.*;
import java.util.*;
import org.irisa.triskell.MT.visitors.Java.GenericVisitor.Visitable;
import org.irisa.triskell.MT.visitors.Java.GenericVisitor.Visitor;
import java.lang.Class;
import java.util.Map;

public interface AnalysingVisitor 
    extends org.irisa.triskell.MT.visitors.Java.GenericVisitor.Visitor
{

     org.irisa.triskell.MT.visitors.Java.AnalysingVisitor.Analyser getAnalyser(
        org.irisa.triskell.MT.visitors.Java.GenericVisitor.Visitable node);
}

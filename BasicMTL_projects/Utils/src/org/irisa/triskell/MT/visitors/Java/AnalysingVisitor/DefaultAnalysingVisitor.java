package org.irisa.triskell.MT.visitors.Java.AnalysingVisitor;

import java.util.*;

import org.irisa.triskell.MT.utils.Java.AWK;
import org.irisa.triskell.MT.visitors.Java.GenericVisitor.*;
import org.irisa.triskell.MT.visitors.Java.GenericVisitor.*;
import org.irisa.triskell.MT.visitors.Java.GenericVisitor.Visitable;
import org.irisa.triskell.MT.visitors.Java.GenericVisitor.Visitor;

public class DefaultAnalysingVisitor 
    implements org.irisa.triskell.MT.visitors.Java.AnalysingVisitor.AnalysingVisitor
{
	public static Comparator ClassComparator = new Comparator () {

		public int compare(Object o1, Object o2) {
			return ((Class)o1).getName().compareTo(((Class)o2).getName());
		}
		
	};
	
    protected TreeMap analysers = new TreeMap(ClassComparator);

    public final String analysersPackage;
    public String getAnalysersPackage () {
        return this.analysersPackage;
    }

    private org.irisa.triskell.MT.visitors.Java.AnalysingVisitor.DefaultAnalysingVisitor parent;
    protected org.irisa.triskell.MT.visitors.Java.AnalysingVisitor.DefaultAnalysingVisitor getParent () {
        return this.parent;
    }
    private void setParent (org.irisa.triskell.MT.visitors.Java.AnalysingVisitor.DefaultAnalysingVisitor value) {
        this.parent = value; 
    }
    protected int cardParent () {
        if ( this.parent == null ) return 0;
        else return 1;
    }


    /**
      * The constructor giving the package far automatic analyser search.
      */
    public DefaultAnalysingVisitor(
        String analysersPackage)
    {
		this(analysersPackage, null);
    }

    /**
      * The constructor giving the package far automatic analyser search.
      */
    public DefaultAnalysingVisitor(
        String analysersPackage,
        org.irisa.triskell.MT.visitors.Java.AnalysingVisitor.DefaultAnalysingVisitor parent)
    {
		this.analysersPackage = analysersPackage;
		this.parent = parent;
    }

    /**
      * The analyser is found
      */
    public org.irisa.triskell.MT.visitors.Java.AnalysingVisitor.Analyser getAnalyser(
        org.irisa.triskell.MT.visitors.Java.GenericVisitor.Visitable node)
    {
		return this.getAnalyser(node.getClass());
    }

    public String AnalyserBuildName(
        java.lang.Class nodeType)
    {
		  return (this.getAnalysersPackage() + '.' + AWK.lastFieldOf(nodeType.getName(), ".") + this.analyserSuffix());
    }

    public org.irisa.triskell.MT.visitors.Java.AnalysingVisitor.Analyser getAnalyser(
        java.lang.Class nodeType)
    {
		if (nodeType == null)
			return null;
    		
		Analyser c = (Analyser)this.analysers.get(nodeType);
		if (c == null) {
			if (this.analysers.containsKey(nodeType))
				return null;
		} else
			return c;
    			
		Analyser foundAnalyser = null;
		/* System.out.println(this.AnalyserBuildName(nodeType)); */
		try  {
			//Attempts to retreive the right analyser thanks to the name of the node
			Class analyserCandidate = Class.forName(this.AnalyserBuildName(nodeType));
			foundAnalyser = (Analyser)analyserCandidate.newInstance();
		} catch (Exception x) {
			foundAnalyser = this.getAnalyser(nodeType.getSuperclass());
		} finally {
		}
		
		if (foundAnalyser == null && this.getParent() != null)
			foundAnalyser = this.getParent().getAnalyser(nodeType);
			
		if (foundAnalyser != null)
			this.analysers.put(nodeType, foundAnalyser);
			
		return foundAnalyser;
    }

    public void visit(
        org.irisa.triskell.MT.visitors.Java.GenericVisitor.Visitable node,
        java.util.Map context)
    {
		Analyser theAnalyser = this.getAnalyser(node);
		if (theAnalyser != null)
			theAnalyser.analyse(node, this, context);
    }

    protected String analyserSuffix()
    {
		return "Analyser";
    }
}

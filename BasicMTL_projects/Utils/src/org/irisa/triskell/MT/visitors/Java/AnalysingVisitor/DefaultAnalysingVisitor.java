package org.irisa.triskell.MT.visitors.Java.AnalysingVisitor;

import java.util.*;
import org.irisa.triskell.MT.visitors.Java.GenericVisitor.*;
import java.lang.*;
import org.irisa.triskell.MT.visitors.Java.GenericVisitor.Visitable;
import org.irisa.triskell.MT.visitors.Java.GenericVisitor.Visitor;

public class DefaultAnalysingVisitor 
    implements org.irisa.triskell.MT.visitors.Java.AnalysingVisitor.AnalysingVisitor
{
    protected Vector analysers = new Vector();
    public org.irisa.triskell.MT.visitors.Java.AnalysingVisitor.Analyser getAnalysers (int i) {
        return (org.irisa.triskell.MT.visitors.Java.AnalysingVisitor.Analyser)this.analysers.elementAt(i);
    }
    public int cardAnalysers () {
        return this.analysers.size();
    }

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
String name=nodeType.getName();
		  int lastPointIndex=name.length();
		  while ((lastPointIndex!=0) && (name.charAt(lastPointIndex-1)!='.'))
		   		lastPointIndex--;
		  return (this.getAnalysersPackage() + '.' + name.substring(lastPointIndex) + this.analyserSuffix());
    }

    public org.irisa.triskell.MT.visitors.Java.AnalysingVisitor.Analyser getAnalyser(
        java.lang.Class nodeType)
    {
		if (nodeType == null)
			return null;
    		
		for (int i = 0; i < this.cardAnalysers(); ++i)
			if (this.getAnalysers(i).getNodeType().equals(nodeType))
				return this.getAnalysers(i);
    			
		Analyser foundAnalyser = null;
		/* System.out.println(this.AnalyserBuildName(nodeType)); */
		try  {
			//Attempts to retreive the right analyser thanks to the name of the node
			Class analyserCandidate = Class.forName(this.AnalyserBuildName(nodeType));
			foundAnalyser = (Analyser)analyserCandidate.newInstance();
		} catch (Exception x) {
			foundAnalyser = this.getAnalyser(nodeType.getSuperclass());
		} finally {
			if (foundAnalyser != null)
				this.analysers.addElement(foundAnalyser);
		}
		
		if (foundAnalyser == null && this.getParent() != null)
			foundAnalyser = this.getParent().getAnalyser(nodeType);
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

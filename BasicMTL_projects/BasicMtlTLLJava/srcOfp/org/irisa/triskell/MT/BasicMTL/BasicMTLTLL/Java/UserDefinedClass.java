package org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java;

import java.util.*;

abstract public class UserDefinedClass 
    extends org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.ASTNode
{
    public Vector definedFeatures = new Vector();
    public org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.Feature getDefinedFeatures (int i) {
        return (org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.Feature)this.definedFeatures.elementAt(i);
    }
    public void setDefinedFeatures (int i, org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.Feature value) {
        this.definedFeatures.setElementAt(value, i);
    }
    public void appendDefinedFeatures (org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.Feature value) {
        this.definedFeatures.addElement(value);
    }
    public void eraseDefinedFeatures (org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.Feature value) throws Exception {
        if (this.definedFeatures.size() == 1)
            throw new Exception ( "TooFewArgsInAssociation(1)" );
        else
            this.definedFeatures.removeElement(value);
    }
    public void eraseDefinedFeatures (int i) throws Exception {
        if (this.definedFeatures.size() == 1)
            throw new Exception ( "TooFewArgsInAssociation(1)" );
        else
            this.definedFeatures.removeElementAt(i);
    }
    public int cardDefinedFeatures () {
        return this.definedFeatures.size();
    }

    public String name;
    public String getName () {
        return this.name;
    }

    public String mangle;
    public String getMangle () {
        return this.mangle;
    }

    public org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.InheritedTypesList inheritance;
    public org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.InheritedTypesList getInheritance () {
        return this.inheritance;
    }
    public void setInheritance (org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.InheritedTypesList value) {
        this.inheritance = value; 
    }
    public int cardInheritance () {
        if ( this.inheritance == null ) return 0;
        else return 1;
    }

    public Vector localSignatures = new Vector();
    public org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.OpSignature getLocalSignatures (int i) {
        return (org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.OpSignature)this.localSignatures.elementAt(i);
    }
    public void setLocalSignatures (int i, org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.OpSignature value) {
        this.localSignatures.setElementAt(value, i);
    }
    public void appendLocalSignatures (org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.OpSignature value) {
        this.localSignatures.addElement(value);
    }
    public void eraseLocalSignatures (org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.OpSignature value) throws Exception {
        if (this.localSignatures.size() == 1)
            throw new Exception ( "TooFewArgsInAssociation(1)" );
        else
            this.localSignatures.removeElement(value);
    }
    public void eraseLocalSignatures (int i) throws Exception {
        if (this.localSignatures.size() == 1)
            throw new Exception ( "TooFewArgsInAssociation(1)" );
        else
            this.localSignatures.removeElementAt(i);
    }
    public int cardLocalSignatures () {
        return this.localSignatures.size();
    }

    public Vector inheritedSignatures = new Vector();
    public org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.InheritedOpSignature getInheritedSignatures (int i) {
        return (org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.InheritedOpSignature)this.inheritedSignatures.elementAt(i);
    }
    public void setInheritedSignatures (int i, org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.InheritedOpSignature value) {
        this.inheritedSignatures.setElementAt(value, i);
    }
    public void appendInheritedSignatures (org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.InheritedOpSignature value) {
        this.inheritedSignatures.addElement(value);
    }
    public void eraseInheritedSignatures (org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.InheritedOpSignature value)  {
        this.inheritedSignatures.removeElement(value);
    }
    public void eraseInheritedSignatures (int i)  {
        this.inheritedSignatures.removeElementAt(i);
    }
    public int cardInheritedSignatures () {
        return this.inheritedSignatures.size();
    }

    public org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.KnownOperations knownOperations;
    public org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.KnownOperations getKnownOperations () {
        return this.knownOperations;
    }
    public void setKnownOperations (org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.KnownOperations value) {
        this.knownOperations = value; 
    }
    public int cardKnownOperations () {
        if ( this.knownOperations == null ) return 0;
        else return 1;
    }

    public int lineNumber;
    public int getLineNumber () {
        return this.lineNumber;
    }

    public boolean completedInheritedSignatures;
    public boolean getCompletedInheritedSignatures () {
        return this.completedInheritedSignatures;
    }
    public void setCompletedInheritedSignatures (boolean value) {
        this.completedInheritedSignatures = value; 
    }

    public org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.QualifiedName qualifiedName;
    public org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.QualifiedName getQualifiedName () {
        return this.qualifiedName;
    }
    public void setQualifiedName (org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.QualifiedName value) {
        this.qualifiedName = value; 
    }
    public int cardQualifiedName () {
        if ( this.qualifiedName == null ) return 0;
        else return 1;
    }

    public org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.InheritedTypesList refinement;
    public org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.InheritedTypesList getRefinement () {
        return this.refinement;
    }
    public void setRefinement (org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.InheritedTypesList value) {
        this.refinement = value; 
    }
    public int cardRefinement () {
        if ( this.refinement == null ) return 0;
        else return 1;
    }


    public UserDefinedClass(
        String name,
        String mangle,
        int lineNumber)
    {
this.name=name;
this.mangle=mangle;
this.completedInheritedSignatures=false;
this.lineNumber=lineNumber;
    }
}

package org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java;

import java.util.*;


public class QualifiedName 
    extends java.util.Vector
{
    public boolean isLocalType;
    public boolean getIsLocalType () {
        return this.isLocalType;
    }
    public void setIsLocalType (boolean value) {
        this.isLocalType = value; 
    }

    public boolean isExternType;
    public boolean getIsExternType () {
        return this.isExternType;
    }
    public void setIsExternType (boolean value) {
        this.isExternType = value; 
    }

    public boolean isModelType;
    public boolean getIsModelType () {
        return this.isModelType;
    }
    public void setIsModelType (boolean value) {
        this.isModelType = value; 
    }

    public String externCompleteName;
    public String getExternCompleteName () {
        return this.externCompleteName;
    }
    public void setExternCompleteName (String value) {
        this.externCompleteName = value; 
    }

    public String externMangledName;
    public String getExternMangledName () {
        return this.externMangledName;
    }
    public void setExternMangledName (String value) {
        this.externMangledName = value; 
    }

    public String localMangledName;
    public String getLocalMangledName () {
        return this.localMangledName;
    }
    public void setLocalMangledName (String value) {
        this.localMangledName = value; 
    }

    public String externLibMangledName;
    public String getExternLibMangledName () {
        return this.externLibMangledName;
    }
    public void setExternLibMangledName (String value) {
        this.externLibMangledName = value; 
    }

    public String externLibCompleteName;
    public String getExternLibCompleteName () {
        return this.externLibCompleteName;
    }
    public void setExternLibCompleteName (String value) {
        this.externLibCompleteName = value; 
    }

    public boolean isRepositoryModel;
    public boolean getIsRepositoryModel () {
        return this.isRepositoryModel;
    }
    public void setIsRepositoryModel (boolean value) {
        this.isRepositoryModel = value; 
    }

    public String declarationName;
    public String getDeclarationName () {
        return this.declarationName;
    }
    public void setDeclarationName (String value) {
        this.declarationName = value; 
    }

    public java.util.Vector notAlreadySolvedBy = null;
    public java.util.Vector getNotAlreadySolvedBy () {
        return this.notAlreadySolvedBy;
    }
    public void setNotAlreadySolvedBy (java.util.Vector value) {
        this.notAlreadySolvedBy = value; 
    }

    public String pureDeclarationName = null;
    public String getPureDeclarationName () {
        return this.pureDeclarationName;
    }
    public void setPureDeclarationName (String value) {
        this.pureDeclarationName = value; 
    }

    public Vector typeForVarDeclarations = new Vector();
    public org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.VarDeclaration getTypeForVarDeclarations (int i) {
        return (org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.VarDeclaration)this.typeForVarDeclarations.elementAt(i);
    }
    public void setTypeForVarDeclarations (int i, org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.VarDeclaration value) {
        this.typeForVarDeclarations.setElementAt(value, i);
    }
    public void appendTypeForVarDeclarations (org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.VarDeclaration value) {
        this.typeForVarDeclarations.addElement(value);
    }
    public void eraseTypeForVarDeclarations (org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.VarDeclaration value)  {
        this.typeForVarDeclarations.removeElement(value);
    }
    public void eraseTypeForVarDeclarations (int i)  {
        this.typeForVarDeclarations.removeElementAt(i);
    }
    public int cardTypeForVarDeclarations () {
        return this.typeForVarDeclarations.size();
    }

    public Vector typeForFeatures = new Vector();
    public org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.Feature getTypeForFeatures (int i) {
        return (org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.Feature)this.typeForFeatures.elementAt(i);
    }
    public void setTypeForFeatures (int i, org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.Feature value) {
        this.typeForFeatures.setElementAt(value, i);
    }
    public void appendTypeForFeatures (org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.Feature value) {
        this.typeForFeatures.addElement(value);
    }
    public void eraseTypeForFeatures (org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.Feature value)  {
        this.typeForFeatures.removeElement(value);
    }
    public void eraseTypeForFeatures (int i)  {
        this.typeForFeatures.removeElementAt(i);
    }
    public int cardTypeForFeatures () {
        return this.typeForFeatures.size();
    }

}

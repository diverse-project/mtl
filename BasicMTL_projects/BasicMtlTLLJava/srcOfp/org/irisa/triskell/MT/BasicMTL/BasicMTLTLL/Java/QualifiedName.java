package org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java;

import java.util.*;
import org.irisa.triskell.MT.visitors.Java.GenericVisitor.Visitor;
import org.irisa.triskell.MT.visitors.Java.AnalysingVisitor.Property;
import java.util.Map;
import java.util.Vector;

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

}

package org.irisa.triskell.MT.DataTypes.Java;

import java.util.*;
import java.io.*;
import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.*;
import org.irisa.triskell.MT.DataTypes.Java.commands.*;

public interface Type 
{
    public static final String PackageIndirection = "::";


     String getName();

     String[] getQualifiedName();

     boolean isKindOf(
        org.irisa.triskell.MT.DataTypes.Java.Value v);

     boolean isTypeOf(
        org.irisa.triskell.MT.DataTypes.Java.Value v);

     boolean conformsTo(
        org.irisa.triskell.MT.DataTypes.Java.Type parentType);

     org.irisa.triskell.MT.DataTypes.Java.CollectionValue allInstances()
        throws java.lang.Exception;

     String toString();

     String getQualifiedNameAsString();
}

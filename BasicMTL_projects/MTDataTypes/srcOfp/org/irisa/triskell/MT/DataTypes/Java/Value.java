package org.irisa.triskell.MT.DataTypes.Java;

import java.util.*;

public interface Value 
{

     boolean isUndefined();

     String getErrorMessage();

     boolean equals(
        org.irisa.triskell.MT.DataTypes.Java.Value rhs);

     org.irisa.triskell.MT.DataTypes.Java.Value invoke(
        String[] scopeQualifiedName,
        String name,
        org.irisa.triskell.MT.DataTypes.Java.Value[] arguments,
        String[] discriminants)
        throws org.irisa.triskell.MT.DataTypes.Java.commands.UnknownCommandException, org.irisa.triskell.MT.DataTypes.Java.commands.MultipleCommandException;

     void accept(
        org.irisa.triskell.MT.DataTypes.Java.ValueVisitor visitor);

     org.irisa.triskell.MT.DataTypes.Java.Type getType();
}

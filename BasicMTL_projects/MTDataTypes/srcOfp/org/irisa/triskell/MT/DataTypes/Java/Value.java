package org.irisa.triskell.MT.DataTypes.Java;

import java.util.*;
import java.io.*;
import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.*;
import org.irisa.triskell.MT.DataTypes.Java.commands.*;

/**
  * The abstract concept of value.
  */
public interface Value 
{

    /**
      * A value may be undefined either to represent the empty value (like java null), or to represent a runtime error. In the latter case, the getErrorMessage operation gives some additional information about the error. If this is not is a separate class because the undefined value must conform to each kind of value.
      */
     boolean isUndefined();

    /**
      * If this value is undefined because of a runtime error, this operation returns informal explanation of this error.
      */
     String getErrorMessage();

     boolean equals(
        org.irisa.triskell.MT.DataTypes.Java.Value rhs);

    /**
      * Invoke an operation on this value. Returns an undefined value if this invokation leads to a problem (e.g. this object is null).
      */
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

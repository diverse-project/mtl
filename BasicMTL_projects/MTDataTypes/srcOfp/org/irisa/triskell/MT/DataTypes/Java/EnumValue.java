package org.irisa.triskell.MT.DataTypes.Java;

import java.io.*;
import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.*;
import org.irisa.triskell.MT.DataTypes.Java.commands.*;

/**
  * The value for enumered. The corresponding enumeration may have been defined either in the metamodel or in the transformation.
  */
public interface EnumValue 
    extends org.irisa.triskell.MT.DataTypes.Java.PrimitiveValue
{

    /**
      * The contained enumeration as a character string.
      */
     String getTheEnum();

    /**
      * The declaring enumeration. May result in nothing if undefined.
      * 
      * 
      * 
      * @return  May be null if unknown.
      */
     String[] getEnumeration();
}

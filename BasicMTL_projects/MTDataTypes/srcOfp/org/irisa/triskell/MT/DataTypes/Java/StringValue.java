package org.irisa.triskell.MT.DataTypes.Java;

import java.io.*;
import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.*;
import org.irisa.triskell.MT.DataTypes.Java.commands.*;

/**
  * The value for character strings.
  */
public interface StringValue 
    extends org.irisa.triskell.MT.DataTypes.Java.PrimitiveValue
{

    /**
      * The contained character string.
      */
     String getTheString();
}

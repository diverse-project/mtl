package org.irisa.triskell.MT.DataTypes.Java;

import java.io.*;
import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.*;
import org.irisa.triskell.MT.DataTypes.Java.commands.*;

/**
  * A primitive value is a value that will not change over time.
  */
public interface PrimitiveValue 
    extends org.irisa.triskell.MT.DataTypes.Java.Value
{

    /**
      * The contained value as a character string.
      */
     String getValue();
}

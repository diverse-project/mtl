package org.irisa.triskell.MT.DataTypes.Java;

import java.io.*;
import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.*;
import org.irisa.triskell.MT.DataTypes.Java.commands.*;

/**
  * The value for booleans.
  */
public interface BooleanValue 
    extends org.irisa.triskell.MT.DataTypes.Java.PrimitiveValue
{

    /**
      * The contained boolean.
      */
     boolean getTheBoolean();
}

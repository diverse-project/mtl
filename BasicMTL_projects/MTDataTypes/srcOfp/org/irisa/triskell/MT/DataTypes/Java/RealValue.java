package org.irisa.triskell.MT.DataTypes.Java;

import java.io.*;
import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.*;
import org.irisa.triskell.MT.DataTypes.Java.commands.*;

/**
  * The value for real numerics.
  */
public interface RealValue 
    extends org.irisa.triskell.MT.DataTypes.Java.PrimitiveValue
{

    /**
      * The contained real numeric.
      */
     float getTheReal();
}

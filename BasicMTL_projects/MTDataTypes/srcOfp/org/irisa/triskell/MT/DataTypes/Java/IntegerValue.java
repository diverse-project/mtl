package org.irisa.triskell.MT.DataTypes.Java;

import java.io.*;
import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.*;
import org.irisa.triskell.MT.DataTypes.Java.commands.*;

/**
  * The value for integer numerics. Note: integer numeric may be seen as a real numeric. (see omg document on OCL 2.0 : ad/2003-01-07 pages 3-5, and 6-6. this implies the isubclassing of real )
  */
public interface IntegerValue 
    extends org.irisa.triskell.MT.DataTypes.Java.RealValue
{

    /**
      * The contained integer numeric.
      */
     int getTheInteger();
}

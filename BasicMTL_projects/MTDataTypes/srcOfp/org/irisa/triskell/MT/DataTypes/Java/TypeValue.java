package org.irisa.triskell.MT.DataTypes.Java;

import java.io.*;
import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.*;
import org.irisa.triskell.MT.DataTypes.Java.commands.*;

/**
  * A value wdescribing a type.
  */
public interface TypeValue 
    extends org.irisa.triskell.MT.DataTypes.Java.PrimitiveValue
{

     org.irisa.triskell.MT.DataTypes.Java.Type getTheType();
}

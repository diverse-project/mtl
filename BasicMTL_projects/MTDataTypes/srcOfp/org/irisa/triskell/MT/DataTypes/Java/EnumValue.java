package org.irisa.triskell.MT.DataTypes.Java;

import java.io.*;
import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.*;
import org.irisa.triskell.MT.DataTypes.Java.commands.*;

public interface EnumValue 
    extends org.irisa.triskell.MT.DataTypes.Java.PrimitiveValue
{

     String getTheEnum();

     String[] getEnumeration();
}

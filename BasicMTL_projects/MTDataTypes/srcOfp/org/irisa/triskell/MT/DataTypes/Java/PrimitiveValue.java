package org.irisa.triskell.MT.DataTypes.Java;

import java.io.*;
import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.*;
import org.irisa.triskell.MT.DataTypes.Java.commands.*;

public interface PrimitiveValue 
    extends org.irisa.triskell.MT.DataTypes.Java.Value
{

     String getValue();

     String getType();
}

package org.irisa.triskell.MT.DataTypes.Java;

import java.util.*;

public interface TupleValue 
    extends org.irisa.triskell.MT.DataTypes.Java.Value
{

     String[] getPartNames();

     org.irisa.triskell.MT.DataTypes.Java.Value getPart(
        String partName);
}

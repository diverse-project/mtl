package org.irisa.triskell.MT.DataTypes.Java;

import java.util.*;

/**
  * A value combining values of different types into a single. The components of a tuple value are described by tuple parts each having a name and a value. The name of a part is unique inside a tuple value.
  */
public interface TupleValue 
    extends org.irisa.triskell.MT.DataTypes.Java.Value
{

    /**
      * The names of every part.
      */
     String[] getPartNames();

    /**
      * The value of the part of the given name. Undefined if the name does not exists.
      */
     org.irisa.triskell.MT.DataTypes.Java.Value getPart(
        String partName);
}

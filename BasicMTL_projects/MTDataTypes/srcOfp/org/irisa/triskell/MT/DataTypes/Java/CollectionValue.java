package org.irisa.triskell.MT.DataTypes.Java;

import java.util.*;

public interface CollectionValue 
    extends org.irisa.triskell.MT.DataTypes.Java.Value
{

     org.irisa.triskell.MT.DataTypes.Java.Value[] getTheCollection();

     org.irisa.triskell.MT.DataTypes.Java.CollectionKind getKind();
}

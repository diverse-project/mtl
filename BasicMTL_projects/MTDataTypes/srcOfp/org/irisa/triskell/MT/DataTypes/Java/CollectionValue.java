package org.irisa.triskell.MT.DataTypes.Java;

import java.util.*;

/**
  * The value for collections of values.
  */
public interface CollectionValue 
    extends org.irisa.triskell.MT.DataTypes.Java.Value
{

    /**
      * The contained values in the collection. These values may not have all the same type, and may others collections.
      */
     org.irisa.triskell.MT.DataTypes.Java.Value[] getTheCollection();

    /**
      * Give some more inforations about the collection. 
      * CollectionKind.set_kind means the contained collection have not the same value twice.
      * CollectionKind.bag_kind means the contained collection may contain the same value more than once.
      * CollectionKind.sequence_kind means the contained collection may contain the same value more than once. It also means their order have a special meaning.
      */
     org.irisa.triskell.MT.DataTypes.Java.CollectionKind getKind();
}

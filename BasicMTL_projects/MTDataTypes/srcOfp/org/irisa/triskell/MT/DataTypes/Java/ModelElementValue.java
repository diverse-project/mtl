package org.irisa.triskell.MT.DataTypes.Java;

import java.io.*;
import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.*;
import org.irisa.triskell.MT.DataTypes.Java.commands.*;

/**
  * The value for elements of the model.
  */
public interface ModelElementValue 
    extends org.irisa.triskell.MT.DataTypes.Java.Value
{

    /**
      * The contained model element. I this object is null, returns the character string 'null'. Elsewhere, if this object is transient (not in the repository, like an undefined value), this operation returns null.
      * 
      * 
      * 
      * @return  A unique identifier formally designating the model element. If this identifier is empty, this is the special "null" value.
      */
     String getTheModelElement();

     void deleteTheModelElement()
        throws java.lang.Exception;
}

package org.irisa.triskell.MT.repository.API.Java;

import java.util.*;
import org.irisa.triskell.MT.DataTypes.Java.*;
import java.lang.*;

public interface ModelElementIterator 
{

     int size();

     boolean hasNext();

     org.irisa.triskell.MT.repository.API.Java.ModelElement next()
        throws NoMoreElementException;

     void reset();
}

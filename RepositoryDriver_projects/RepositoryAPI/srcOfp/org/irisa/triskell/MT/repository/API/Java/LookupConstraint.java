package org.irisa.triskell.MT.repository.API.Java;

import java.util.*;
import org.irisa.triskell.MT.DataTypes.Java.*;
import java.lang.*;

public interface LookupConstraint 
{

     boolean match(
        org.irisa.triskell.MT.repository.API.Java.ModelElement element);
}

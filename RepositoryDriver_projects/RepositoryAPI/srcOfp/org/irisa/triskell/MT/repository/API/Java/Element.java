package org.irisa.triskell.MT.repository.API.Java;

import java.util.*;
import org.irisa.triskell.MT.DataTypes.Java.*;
import java.lang.*;

public interface Element 
{

     org.irisa.triskell.MT.repository.API.Java.API getAPI();

     void addListener(
        org.irisa.triskell.MT.repository.API.Java.EventListener listener);

     void removeListener(
        org.irisa.triskell.MT.repository.API.Java.EventListener listener);
}

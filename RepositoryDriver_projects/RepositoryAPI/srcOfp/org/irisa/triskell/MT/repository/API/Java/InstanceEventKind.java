package org.irisa.triskell.MT.repository.API.Java;


import java.io.*;

public class InstanceEventKind implements Serializable
{
    private int value;
    private static final String[] strValues = {"instanceCreate",
                                                                "instanceDelete"
                                                                };

    public static final InstanceEventKind instanceCreate = new InstanceEventKind(0);
    public static final InstanceEventKind instanceDelete = new InstanceEventKind(1);

    private static final InstanceEventKind[] enumValues = {instanceCreate,
                                                                        instanceDelete
                                                                        };

    private InstanceEventKind(int code) {
        value = code;
    }

    public java.lang.String toString () {
        return strValues[value];
    }

    public int toInt () {
        return value;
    }

    public static InstanceEventKind fromString (String str) {
        InstanceEventKind toReturn = null;
        int i = 0;

        while (i < strValues.length)
        {
            if (strValues[i].equals(str))
                toReturn = enumValues[i];
            i++;
        }
        return toReturn;
    }

}

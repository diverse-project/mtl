package org.irisa.triskell.MT.repository.API.Java;


import java.io.*;

public class AttributeEventKind implements Serializable
{
    private int value;
    private static final String[] strValues = {"attributeAdd",
                                                                "attributeRemove",
                                                                "attributeSet"
                                                                };

    public static final AttributeEventKind attributeAdd = new AttributeEventKind(0);
    public static final AttributeEventKind attributeRemove = new AttributeEventKind(1);
    public static final AttributeEventKind attributeSet = new AttributeEventKind(2);

    private static final AttributeEventKind[] enumValues = {attributeAdd,
                                                                        attributeRemove,
                                                                        attributeSet
                                                                        };

    private AttributeEventKind(int code) {
        value = code;
    }

    public java.lang.String toString () {
        return strValues[value];
    }

    public int toInt () {
        return value;
    }

    public static AttributeEventKind fromString (String str) {
        AttributeEventKind toReturn = null;
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

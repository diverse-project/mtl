package org.irisa.triskell.MT.repository.API.Java;


import java.io.*;

public class AssociationEventKind implements Serializable
{
    private int value;
    private static final String[] strValues = {"associationAdd",
                                                                "associationRemove",
                                                                "associationSet"
                                                                };

    public static final AssociationEventKind associationAdd = new AssociationEventKind(0);
    public static final AssociationEventKind associationRemove = new AssociationEventKind(1);
    public static final AssociationEventKind associationSet = new AssociationEventKind(2);

    private static final AssociationEventKind[] enumValues = {associationAdd,
                                                                        associationRemove,
                                                                        associationSet
                                                                        };

    private AssociationEventKind(int code) {
        value = code;
    }

    public java.lang.String toString () {
        return strValues[value];
    }

    public int toInt () {
        return value;
    }

    public static AssociationEventKind fromString (String str) {
        AssociationEventKind toReturn = null;
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

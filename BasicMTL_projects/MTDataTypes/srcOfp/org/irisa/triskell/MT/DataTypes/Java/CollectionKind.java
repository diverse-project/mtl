package org.irisa.triskell.MT.DataTypes.Java;

import java.util.*;
import java.io.Serializable;

public final class CollectionKind 
    implements java.io.Serializable
{
    public static final org.irisa.triskell.MT.DataTypes.Java.CollectionKind set_kind = new CollectionKind(1);
    public static org.irisa.triskell.MT.DataTypes.Java.CollectionKind getSet_kind () {
        return CollectionKind.set_kind;
    }
    public static int cardSet_kind () {
        if ( CollectionKind.set_kind == null ) return 0;
        else return 1;
    }

    public static final org.irisa.triskell.MT.DataTypes.Java.CollectionKind bag_kind = new CollectionKind(2);
    public static org.irisa.triskell.MT.DataTypes.Java.CollectionKind getBag_kind () {
        return CollectionKind.bag_kind;
    }
    public static int cardBag_kind () {
        if ( CollectionKind.bag_kind == null ) return 0;
        else return 1;
    }

    public static final org.irisa.triskell.MT.DataTypes.Java.CollectionKind sequence_kind = new CollectionKind(3);
    public static org.irisa.triskell.MT.DataTypes.Java.CollectionKind getSequence_kind () {
        return CollectionKind.sequence_kind;
    }
    public static int cardSequence_kind () {
        if ( CollectionKind.sequence_kind == null ) return 0;
        else return 1;
    }

    public static final org.irisa.triskell.MT.DataTypes.Java.CollectionKind ordered_set_kind = new CollectionKind(1);
    public static org.irisa.triskell.MT.DataTypes.Java.CollectionKind getOrdered_set_kind () {
        return CollectionKind.ordered_set_kind;
    }
    public static int cardOrdered_set_kind () {
        if ( CollectionKind.ordered_set_kind == null ) return 0;
        else return 1;
    }

    public final int kind;
    public int getKind () {
        return this.kind;
    }


    private CollectionKind(
        int kind)
    {
		this.kind = kind;
    }

    public String toString()
    {
    	if (this == set_kind) return "Set";
    	if (this == ordered_set_kind) return "OrderedSet";
    	if (this == bag_kind) return "Bag";
    	if (this == sequence_kind) return "Sequence";
    	throw new RuntimeException("Unmanaged collection " + this.kind + '.');
    }
}

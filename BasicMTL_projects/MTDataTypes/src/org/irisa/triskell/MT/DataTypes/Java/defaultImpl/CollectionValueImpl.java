package org.irisa.triskell.MT.DataTypes.Java.defaultImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;


import org.irisa.triskell.MT.DataTypes.Java.CollectionKind;
import org.irisa.triskell.MT.DataTypes.Java.CollectionValue;
import org.irisa.triskell.MT.DataTypes.Java.Value;
import org.irisa.triskell.MT.DataTypes.Java.commands.CommandGroup;
import org.irisa.triskell.MT.DataTypes.Java.commands.Collection.CollectionCommandGroup;
import org.irisa.triskell.MT.DataTypes.Java.commands.Collection.CollectionType;
import org.irisa.triskell.MT.DataTypes.Java.commands.OclAny.OclAnyType;
;

abstract public class CollectionValueImpl 
    extends org.irisa.triskell.MT.DataTypes.Java.defaultImpl.ValueImpl
    implements org.irisa.triskell.MT.DataTypes.Java.CollectionValue
{
    protected Collection theCollection = null;
    protected transient org.irisa.triskell.MT.DataTypes.Java.Value [] theCollectionAsArray = null;

    protected org.irisa.triskell.MT.DataTypes.Java.CollectionKind kind;


    public CollectionValueImpl(
        boolean isUndefined,
        String errorMessage,
        org.irisa.triskell.MT.DataTypes.Java.Value[] theCollection,
        org.irisa.triskell.MT.DataTypes.Java.CollectionKind theKind,
        boolean checkSet)
    {
    	this(isUndefined, errorMessage, theCollection, theKind, checkSet, CollectionType.getCollectionType(OclAnyType.TheInstance), CollectionCommandGroup.getCollectionCommandGroup(CollectionType.getCollectionType(OclAnyType.TheInstance)));
    }


    public CollectionValueImpl(
        boolean isUndefined,
        String errorMessage,
        org.irisa.triskell.MT.DataTypes.Java.Value[] theCollection,
        org.irisa.triskell.MT.DataTypes.Java.CollectionKind theKind,
        boolean checkSet,
        CollectionType type,
        CommandGroup commands)
    {
    	this(isUndefined, errorMessage, Arrays.asList(theCollection), theKind, checkSet, type, commands);
    	//if checkSet, the collection may have changed ; can be determined according to the size...
    	if ((! checkSet) || (this.theCollection.size() == theCollection.length))
    		this.theCollectionAsArray = theCollection;
    }


    public CollectionValueImpl(
        boolean isUndefined,
        String errorMessage,
        java.util.Collection theCollection,
        org.irisa.triskell.MT.DataTypes.Java.CollectionKind theKind,
        boolean checkSet)
    {
        this(isUndefined, errorMessage, theCollection, theKind, checkSet, CollectionType.getCollectionType(OclAnyType.TheInstance), CollectionCommandGroup.getCollectionCommandGroup(CollectionType.getCollectionType(OclAnyType.TheInstance)));
    }


    protected CollectionValueImpl(
        boolean isUndefined,
        String errorMessage,
        java.util.Collection theCollection,
        org.irisa.triskell.MT.DataTypes.Java.CollectionKind theKind,
        boolean checkSet,
        CollectionType type,
        CommandGroup commands)
    {
        super(isUndefined, errorMessage, type, commands);
		this.kind = theKind;
		if ((kind == CollectionKind.getSet_kind() || kind == CollectionKind.getOrdered_set_kind()) && checkSet) {
			this.theCollection = CollectionValueImpl.asSet(theCollection);
		} else
			this.theCollection = theCollection;
    }

    public CollectionValueImpl(
        boolean isUndefined,
        String errorMessage,
        org.irisa.triskell.MT.DataTypes.Java.Value[] theCollection,
        org.irisa.triskell.MT.DataTypes.Java.CollectionKind theKind)
    {
		this(isUndefined,errorMessage,theCollection, theKind, true);
    }

    public CollectionValueImpl(
        boolean isUndefined,
        String errorMessage,
        java.util.Collection theCollection,
        org.irisa.triskell.MT.DataTypes.Java.CollectionKind theKind)
    {
    	this(isUndefined, errorMessage, theCollection, theKind, true);
    }

    public boolean checkValueEquality(
        org.irisa.triskell.MT.DataTypes.Java.Value rhs)
    {
		if (! (rhs instanceof CollectionValue)) return false;
		if (! ((CollectionValue)rhs).getKind().equals(this.getKind())) return false;
		boolean collectionComparison = this.theCollectionAsArray == null || ((rhs instanceof CollectionValueImpl) && ((CollectionValueImpl)rhs).theCollectionAsArray == null);
		if (this.kind == CollectionKind.getSet_kind())  {
			if (collectionComparison)
				return CollectionValueImpl.compareSet(this.getTheCollectionAsCollection(), ((CollectionValueImpl)rhs).getTheCollectionAsCollection());
			else
				return CollectionValueImpl.compareSet(this.getTheCollection(), ((CollectionValue)rhs).getTheCollection());
		} else if (this.kind == CollectionKind.getOrdered_set_kind()) {
			if (collectionComparison)
				return CollectionValueImpl.compareOrderedSet(this.getTheCollectionAsCollection(), ((CollectionValueImpl)rhs).getTheCollectionAsCollection());
			else
				return CollectionValueImpl.compareOrderedSet(this.getTheCollection(), ((CollectionValue)rhs).getTheCollection());
		} else if (this.kind == CollectionKind.getBag_kind()) {
			if (collectionComparison)
				return CollectionValueImpl.compareBag(this.getTheCollectionAsCollection(), ((CollectionValueImpl)rhs).getTheCollectionAsCollection());
			else
				return CollectionValueImpl.compareBag(this.getTheCollection(), ((CollectionValue)rhs).getTheCollection());
		} else if (this.kind == CollectionKind.getSequence_kind()) {
			if (collectionComparison)
				return CollectionValueImpl.compareSequence(this.getTheCollectionAsCollection(), ((CollectionValueImpl)rhs).getTheCollectionAsCollection());
			else
				return CollectionValueImpl.compareSequence(this.getTheCollection(), ((CollectionValue)rhs).getTheCollection());
		}
		throw new RuntimeException("Unmanaged collection " + this.kind + '.');
    }

    public org.irisa.triskell.MT.DataTypes.Java.Value[] getTheCollection()
    {
    	return this.getTheCollectionAsArray();
    }

    public org.irisa.triskell.MT.DataTypes.Java.Value[] getTheCollectionAsArray()
    {
    	if (this.theCollectionAsArray == null && this.theCollection != null) {
    		Collection c = this.getTheCollectionAsCollection();
    		this.theCollectionAsArray = (Value[])c.toArray(new Value [c.size()]);
    	}
		return this.theCollectionAsArray;
    }

    public java.util.Collection getTheCollectionAsCollection()
    {
		return this.theCollection;
    }

    public org.irisa.triskell.MT.DataTypes.Java.CollectionKind getKind()
    {
		return this.kind;
    }

    public void accept(
        org.irisa.triskell.MT.DataTypes.Java.ValueVisitor visitor)
    {
		visitor.visitCollectionValue(this);
    }

	public String getValueAsString() {
		StringBuffer sb = new StringBuffer();
		if (this.getKind() == CollectionKind.getSet_kind())
			sb.append("Set {");
		else if (this.getKind() == CollectionKind.getBag_kind())
			sb.append("Bag {");
		else if (this.getKind() == CollectionKind.getSequence_kind())
			sb.append("Sequence {");
		else if (this.getKind() == CollectionKind.getOrdered_set_kind())
			sb.append("OrderedSet {");
		Iterator it = this.getTheCollectionAsCollection().iterator();
		int i = 0;
		Value v;
		while (it.hasNext() && i++ < 10) {
			if (i > 0)
				sb.append(", ");
			v = (Value)it.next();
			if (v instanceof ValueImpl)
				sb.append(((ValueImpl)v).getValueAsString());
			else
				sb.append(it.next());
		}
		if (it.hasNext())
			sb.append(", ... and much more !");
		sb.append('}');
		return sb.toString();
	}

    protected static java.util.Collection asSet(
        java.util.Collection theCollection)
    {
		ArrayList result = new ArrayList(theCollection.size());
		Iterator it = theCollection.iterator();
		Object o;
		while (it.hasNext()) {
			o = it.next();
			if (! result.contains(o))
				result.add(o);
		}
		return result;
    }

    public static boolean compareSet(
        java.util.Collection collection1,
        java.util.Collection collection2)
    {
    	if (collection1 == null)
    		return collection2 == null;
		Iterator it1 = collection1.iterator();
		while (it1.hasNext()) {
			if (! collection2.contains(it1.next()))
				return false;
		}
		return collection1.size() == collection2.size();
    }

    public static boolean compareSet(
        Object [] collection1,
        Object [] collection2)
    {
		if (collection1 == null)
			return collection2 == null;
		if (collection1.length != collection2.length)
			return false;
		for (int i1 = 0; i1 < collection1.length; ++i1) {
			boolean elementFound = false;
			for (int i2 = 0; (i2 < collection2.length) && (! elementFound); ++i2) {
				elementFound = collection1[i1].equals(collection2[i2]);
			}
			if (! elementFound)
				return false;
		}
		return true;
    }

    public static boolean compareOrderedSet(
        java.util.Collection collection1,
        java.util.Collection collection2)
    {
		if (collection1 == null)
			return collection2 == null;
		Iterator it1 = collection1.iterator(), it2 = collection2.iterator();
		while (it1.hasNext() && it2.hasNext())
			if (! it1.next().equals(it2.next()))
				return false;
		return !it1.hasNext() & !it2.hasNext();
    }

    public static boolean compareOrderedSet(
        Object[] collection1,
        Object[] collection2)
    {
		if (collection1 == null)
			return collection2 == null;
		if (collection1.length != collection2.length)
			return false;
		for (int i = 0; i < collection1.length; ++i)
			if (! collection1[i].equals(collection2[i]))
				return false;
		return true;
    }

    public static boolean compareBag(
        java.util.Collection collection1,
        java.util.Collection collection2)
    {
		if (collection1 == null)
			return collection2 == null;
    	return compareBag(collection1.toArray(), collection2.toArray());
    }

    public static boolean compareBag(
        Object[] collection1,
        Object[] collection2)
    {
		if (collection1 == null)
			return collection2 == null;
		if (collection1.length != collection2.length)
			return false;
		boolean [] c1done = new boolean [collection1.length];
		boolean [] c2done = new boolean [collection2.length];
		Arrays.fill(c1done, false);
		Arrays.fill(c2done, false);
		for (int i = 0, i1, i2, count; i < collection1.length; ++i) {
			if (! c1done [i]) {
				count = 0;
				for (i1 = i+1; i1 < collection1.length; ++i1) {
					if ((! c1done[i1]) && collection1[i1].equals(collection1[i])) {
						c1done[i1] = true;
						count++;
					}
				}
				for (i2 = 0; i2 < collection2.length; ++i2) {
					if ((! c2done[i2]) && collection2[i2].equals(collection1[i])) {
						c2done[i2] = true;
						count--;
					}
				}
				if (count != 0)
					return false;
			}
		}
		return true;
    }

    public static boolean compareSequence(
        java.util.Collection collection1,
        java.util.Collection collection2)
    {
		return compareOrderedSet(collection1, collection2);
    }

    public static boolean compareSequence(
        Object [] collection1,
        Object [] collection2)
    {
		return compareOrderedSet(collection1, collection2);
    }

}

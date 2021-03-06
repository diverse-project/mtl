/*
 * $Id: JMIMetaEnumeration.java,v 1.1 2004-10-25 12:32:52 dvojtise Exp $
 * Authors : ffondeme dvojtise
 */
package org.irisa.triskell.MT.repository.genericJMIDriver;

import java.util.Arrays;

import org.irisa.triskell.MT.DataTypes.Java.CollectionValue;
import org.irisa.triskell.MT.DataTypes.Java.EnumValue;
import org.irisa.triskell.MT.DataTypes.Java.StringValue;
import org.irisa.triskell.MT.DataTypes.Java.Type;
import org.irisa.triskell.MT.DataTypes.Java.TypeValue;
import org.irisa.triskell.MT.DataTypes.Java.Value;
import org.irisa.triskell.MT.DataTypes.Java.commands.CommandGroup;
import org.irisa.triskell.MT.DataTypes.Java.commands.CommandGroupImpl;
import org.irisa.triskell.MT.DataTypes.Java.commands.MultipleCommandException;
import org.irisa.triskell.MT.DataTypes.Java.commands.ModelElement.ModelElementCommandGroup;
import org.irisa.triskell.MT.DataTypes.Java.commands.ModelElement.ModelElementType;
import org.irisa.triskell.MT.DataTypes.Java.commands.OclAny.OclAnyCommandGroup;
import org.irisa.triskell.MT.DataTypes.Java.commands.OclType.OclTypeType;
import org.irisa.triskell.MT.DataTypes.Java.commands.enum.EnumCommandGroup;
import org.irisa.triskell.MT.DataTypes.Java.commands.enum.EnumType;
import org.irisa.triskell.MT.repository.API.Java.CommonException;
import org.irisa.triskell.MT.repository.API.Java.MetaAssociationEnd;
import org.irisa.triskell.MT.repository.API.Java.MetaClass;
import org.irisa.triskell.MT.repository.API.Java.MetaOperation;
import org.irisa.triskell.MT.repository.API.Java.ModelElement;
import org.irisa.triskell.MT.repository.API.Java.ModelElementIterator;
import org.irisa.triskell.MT.repository.API.Java.NoMoreElementException;
import org.irisa.triskell.MT.repository.API.Java.UnknownElementException;

/**
 * Generic implementation of the repository API (org.irisa.triskell.MT.repository.API.Java.API)
 * This serve as the base for all Driver that uses JMI to connect to the repository 
 */
public class JMIMetaEnumeration 
    extends org.irisa.triskell.MT.repository.genericJMIDriver.JMIMetaType
    implements org.irisa.triskell.MT.repository.API.Java.MetaClass, org.irisa.triskell.MT.repository.API.Java.ModelElement, TypeValue
{
    private final javax.jmi.reflect.RefPackage packageContainer;

    private final javax.jmi.reflect.RefClass classContainer;

    private final javax.jmi.model.EnumerationType refMetaObject;

    protected org.irisa.triskell.MT.repository.genericJMIDriver.JMIEnumered[] enumered = null;
    public org.irisa.triskell.MT.repository.genericJMIDriver.JMIEnumered getEnumered (int i) {
        return this.enumered[i];
    }
    public org.irisa.triskell.MT.repository.genericJMIDriver.JMIEnumered[] getEnumered () {
        return this.enumered;
    }
    public int cardEnumered () {
        return this.enumered.length;
    }
    
	public String [] getPartsAsStrings () {
		JMIEnumered [] enums = this.getEnumered();
		String [] enumStrings = new String [enums.length];
		for (int i = 0; i < enums.length; ++i)
			enumStrings[i] = enums[i].getTheEnum();
		return enumStrings;
	}
	
    private class JMIEnumType extends EnumType {
    	
		public JMIEnumType() {
			super(JMIMetaEnumeration.this.getPartsAsStrings(), new Type [] {ModelElementType.TheInstance});
		}

		public boolean isKindOfInternal (Value v) {
			return (v instanceof JMIEnumered) && ((JMIEnumered)v).isKindOf(JMIMetaEnumeration.this);
		}
		
		public boolean equals (Type parentType) {
			return parentType == this || ((parentType instanceof JMIEnumType) && JMIMetaEnumeration.this.equals(((JMIEnumType)parentType).getOwner()));
		}

		public CollectionValue allInstances() throws Exception {
			return JMIMetaEnumeration.this.allInstances();
		}
		
		protected final JMIMetaEnumeration getOwner () {
			return JMIMetaEnumeration.this;
		}

    }
    private transient JMIEnumType enumType = null;
    private JMIEnumType getEnumType () {
    	if (this.enumType == null)
    		this.enumType = new JMIEnumType();
    	return this.enumType;
    }
    
    public Type getType () {
    	return OclTypeType.TheInstance;
    }
    
    private transient CommandGroupImpl commandGroup = null;
    public CommandGroupImpl getCommandGroup () {
    	if (this.commandGroup == null) {
    		this.commandGroup = new CommandGroupImpl(this, Arrays.asList(new CommandGroup [] {new EnumCommandGroup(this.getEnumType()), ModelElementCommandGroup.TheInstance}));
    	}
    	return this.commandGroup;
    }

    public class EnumeredNameLookupConstraint 
        implements org.irisa.triskell.MT.repository.API.Java.LookupConstraint
    {
        protected final String name;
        public String getName () {
            return this.name;
        }


        public EnumeredNameLookupConstraint(
            String name)
        {
			this.name = name;
        }

        public boolean match(
            org.irisa.triskell.MT.repository.API.Java.ModelElement element)
        {
			return (element instanceof EnumValue) && (((EnumValue)element).getEnumeration().equals(JMIMetaEnumeration.this.getQualifiedName())) && (((EnumValue)element).getTheEnum().equals(this.getName()));
        }
    }


    public JMIMetaEnumeration(
        org.irisa.triskell.MT.repository.genericJMIDriver.JMIAPI api,
        javax.jmi.model.EnumerationType metaObject,
        javax.jmi.reflect.RefPackage container)
    {
		this(api, metaObject, container, null);
    }

    public JMIMetaEnumeration(
        org.irisa.triskell.MT.repository.genericJMIDriver.JMIAPI api,
        javax.jmi.model.EnumerationType metaObject,
        javax.jmi.reflect.RefClass container)
    {
		this(api, metaObject, null, container);
    }

    public JMIMetaEnumeration(
        org.irisa.triskell.MT.repository.genericJMIDriver.JMIAPI api,
        javax.jmi.model.EnumerationType metaObject,
        javax.jmi.reflect.RefPackage packageContainer,
        javax.jmi.reflect.RefClass classContainer)
    {
        super(api, metaObject, null, retreiveQualifiedName(metaObject));
		this.refMetaObject = metaObject;
		this.packageContainer = packageContainer;
		this.classContainer = classContainer;
    }

    public org.irisa.triskell.MT.repository.API.Java.ModelElement getMetaObject()
    {


		return this;
    }
    
    public ModelElementIterator allInstancesIterator () {
    	this.createAllInstances();
    	return new JMIEnumeredIterator(this);
    }

    public org.irisa.triskell.MT.repository.API.Java.ModelElement instanciate(
        org.irisa.triskell.MT.repository.API.Java.ModelElement contextualElement,
        org.irisa.triskell.MT.DataTypes.Java.Value[] arguments)
    {
		org.irisa.triskell.MT.repository.API.Java.ModelElement ret;
		if ((arguments != null) && (arguments.length == 1) && (arguments[0] instanceof StringValue)) {
			ret = this.findInstance(((StringValue)arguments[0]).getTheString());
			if (ret == null)
				ret = new JMIException("The enumered " + ((StringValue)arguments[0]).getTheString() + " does not exists in the enumeration " + this.toString(), this.getSpecificAPI());			
		} else
			ret = new JMIException("Cannot instancitate an enumeration; you may build one by giving its name as the argument of the constructor.", this.getSpecificAPI());
		return ret;
    }

    public boolean isMetaObject()
    {
		return true;
    }

    public org.irisa.triskell.MT.DataTypes.Java.Value getFeatureValue(
        org.irisa.triskell.MT.repository.API.Java.ModelElement contextualElement,
        org.irisa.triskell.MT.repository.API.Java.MetaFeature feature,
        org.irisa.triskell.MT.DataTypes.Java.Value[] arguments)
        throws org.irisa.triskell.MT.repository.API.Java.UnknownElementException
    {
		JMIEnumered ret = null;
    	if ((arguments == null || arguments.length == 0) && !(feature instanceof MetaAssociationEnd) && !(feature instanceof MetaOperation))
    		ret = this.findInstance(feature.getName());
    	if (ret == null)
    		throw new UnknownElementException(feature);
		return ret;
    }

    public void setAttributeValue(
        org.irisa.triskell.MT.repository.API.Java.ModelElement contextualElement,
        org.irisa.triskell.MT.repository.API.Java.MetaAttribute argument,
        org.irisa.triskell.MT.DataTypes.Java.Value value)
        throws org.irisa.triskell.MT.repository.API.Java.UnknownElementException, org.irisa.triskell.MT.repository.API.Java.CommonException
    {
    	this.getFeatureValue(contextualElement, argument, null);
    	throw new CommonException("Cannot affect an enumered value");
    }

    public Value invokeQueryOperation(
        ModelElement contextualElement,
        MetaOperation feature,
        Value[] arguments)
        throws org.irisa.triskell.MT.repository.API.Java.UnknownElementException
    {
		throw new UnknownElementException(feature);
    }

    /**
      * @see org.irisa.triskell.MT.repository.API.Java.ModelElement#delete() 
      */
    public void delete()
    {
    }
	
	public void deleteTheModelElement () {
		this.delete();
	}

    /**
      * @see org.irisa.triskell.MT.repository.API.Java.ModelElement#isKindOf(org.irisa.triskell.MT.repository.API.Java.MetaClass) 
      */
    public boolean isKindOf(
        org.irisa.triskell.MT.repository.API.Java.MetaClass classifier)
    {
		return this.isTypeOf(classifier);
    }

    /**
      * @see org.irisa.triskell.MT.repository.API.Java.ModelElement#isTypeOf(org.irisa.triskell.MT.repository.API.Java.MetaClass) 
      */
    public boolean isTypeOf(
        org.irisa.triskell.MT.repository.API.Java.MetaClass classifier)
    {
		return this.equals(classifier);
    }

    public boolean isUndefined()
    {
		return false;
    }

    public String getErrorMessage()
    {
		return null;
    }

    public boolean equals(
        org.irisa.triskell.MT.DataTypes.Java.Value rhs)
    {
		return (rhs == this) || ((rhs instanceof JMIMetaEnumeration) && (this.getSpecificAPI().equals(((JMIMetaEnumeration)rhs).getSpecificAPI())) && (((MetaClass)rhs).getQualifiedName().equals(this.getQualifiedName())));
    }

    public org.irisa.triskell.MT.DataTypes.Java.Value invoke(
    	String [] scopeQualifiedName,
        String name,
        org.irisa.triskell.MT.DataTypes.Java.Value[] arguments,
        String[] discriminants)
        throws org.irisa.triskell.MT.DataTypes.Java.commands.UnknownCommandException, MultipleCommandException
    {
    	if (arguments == null || arguments.length == 0) {
    		JMIEnumered e = this.findInstance(name);
    		if (e != null)
    			return e;
    	}
		return OclAnyCommandGroup.TheInstance.invoke(scopeQualifiedName, this, name, arguments, discriminants);
    }

    public String getTheModelElement()
    {
		return this.refMetaObject.refMofId();
    }

    public void accept(
        org.irisa.triskell.MT.DataTypes.Java.ValueVisitor visitor)
    {
		visitor.visitModelElementValue(this);
    }

    public String toString()
    {
		return "enumeration " + org.irisa.triskell.MT.utils.Java.AWK.merge(this.getQualifiedName(), Type.PackageIndirection);
    }

    public void createAllInstances()
    {
    	if (this.enumered == null) {
			java.util.List values = this.refMetaObject.getLabels();
			this.enumered = new JMIEnumered [values.size()];
			java.util.Iterator valuesIt = values.iterator();
			int index = 0;
			if (this.packageContainer != null)
				while (valuesIt.hasNext())
					this.enumered[index++] = new JMIEnumered(this, this.packageContainer.refGetEnum(this.refMetaObject, (String)valuesIt.next()));
			else
				while (valuesIt.hasNext())
					this.enumered[index++] = new JMIEnumered(this, this.classContainer.refGetEnum(this.refMetaObject, (String)valuesIt.next()));
		}
    }

    public org.irisa.triskell.MT.repository.genericJMIDriver.JMIEnumered findInstance(
        String name)
    {
    	try {
    		return (JMIEnumered)this.allInstancesIterator(new EnumeredNameLookupConstraint(name)).next();
    	} catch (NoMoreElementException x) {
    		return null;
    	}
    }

    public String getUniqId()
    {
		return this.refMetaObject.refMofId();
    }

    public static String[] retreiveQualifiedName(
        javax.jmi.model.EnumerationType ref)
    {
		java.util.Collection qualifiedNameAsCollection = ref.getQualifiedName();
		String [] ret = new String [qualifiedNameAsCollection.size()];
		qualifiedNameAsCollection.toArray(ret);
		return ret;
    }

	public boolean conformsTo(Type parentType) {
		return this.equals(parentType) || ModelElementType.TheInstance.conformsTo(parentType) || this.getEnumType().conformsTo(parentType);
	}


	public boolean isKindOf(Value v) {
		return (v instanceof JMIEnumered) && ((JMIEnumered)v).isKindOf(this);
	}


	public boolean isTypeOf(Value v) {
		return (v instanceof JMIEnumered) && ((JMIEnumered)v).isTypeOf(this);
	}

	/* (non-Javadoc)
	 * @see org.irisa.triskell.MT.DataTypes.Java.TypeValue#getTheType()
	 */
	public Type getTheType() {
		return this;
	}

	/* (non-Javadoc)
	 * @see org.irisa.triskell.MT.DataTypes.Java.PrimitiveValue#getValue()
	 */
	public String getValue() {
		return this.getQualifiedNameAsString();
	}

}

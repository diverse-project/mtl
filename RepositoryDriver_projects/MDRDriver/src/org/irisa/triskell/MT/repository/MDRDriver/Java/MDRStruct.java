package org.irisa.triskell.MT.repository.MDRDriver.Java;

import java.util.Arrays;
import java.util.List;

import org.irisa.triskell.MT.DataTypes.Java.Type;
import org.irisa.triskell.MT.DataTypes.Java.Value;
import org.irisa.triskell.MT.DataTypes.Java.commands.MultipleCommandException;
import org.irisa.triskell.MT.DataTypes.Java.commands.UnknownCommandException;
import org.irisa.triskell.MT.DataTypes.Java.commands.ModelElement.ModelElementType;
import org.irisa.triskell.MT.DataTypes.Java.commands.OclAny.OclAnyCommandGroup;
import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.VoidValueImpl;
import org.irisa.triskell.MT.repository.API.Java.MetaAssociationEnd;
import org.irisa.triskell.MT.repository.API.Java.MetaClass;
import org.irisa.triskell.MT.repository.API.Java.MetaOperation;
import org.irisa.triskell.MT.repository.API.Java.ModelElement;
import org.irisa.triskell.MT.repository.API.Java.UnknownElementException;

public class MDRStruct 
    extends org.irisa.triskell.MT.repository.MDRDriver.Java.MDRElement
    implements org.irisa.triskell.MT.DataTypes.Java.TupleValue, org.irisa.triskell.MT.repository.API.Java.ModelElement
{
    private final javax.jmi.reflect.RefStruct refStruct;

    private final org.irisa.triskell.MT.repository.MDRDriver.Java.MDRMetaStructure type;


    public MDRStruct(
        org.irisa.triskell.MT.repository.MDRDriver.Java.MDRMetaStructure type,
        javax.jmi.reflect.RefStruct refStruct)
    {
        super(type.getSpecificAPI(), refStruct);
		this.type = type;
		this.refStruct = refStruct;
    }

    public void accept(
        org.irisa.triskell.MT.DataTypes.Java.ValueVisitor visitor)
    {
		visitor.visitTupleValue(this);
    }

    public boolean equals(
        org.irisa.triskell.MT.DataTypes.Java.Value rhs)
    {
		return (this == rhs) || ((rhs instanceof MDRStruct) ? (this.refStruct.equals(((MDRStruct)rhs).refStruct)) : super.equals(rhs));
    }

    public String getErrorMessage()
    {
		return null;
    }

    public org.irisa.triskell.MT.DataTypes.Java.Value invoke(
    	String [] scopeQualifiedName,
        String name,
        org.irisa.triskell.MT.DataTypes.Java.Value[] arguments,
        String[] discriminants)
        throws org.irisa.triskell.MT.DataTypes.Java.commands.UnknownCommandException, MultipleCommandException
    {
    	boolean local = true;
    	String fn = name;
    	MetaClass scope = null;
    	try {
    		scope = scopeQualifiedName == null ? null : this.getAPI().getMetaClass(scopeQualifiedName);
    	} catch (UnknownElementException x) {
    		local = false;
    	}
	    boolean get = true; //elsewhere: set
    	if (local) {
	    	List discs = Arrays.asList(discriminants);
	    	if (discs.contains(AttributeDiscriminant))
	    		get = true;
	    	else if (discs.contains(AssociationDiscriminant))
	    		get = true;
	    	else if (discs.contains(OperationDiscriminant)) {
	    		if (fn.startsWith("get_") && (arguments == null || arguments.length == 0))
	    			fn = fn.substring(4);
	    		else if (fn.startsWith("set_") && arguments != null && arguments.length == 1) {
	    			get = false;
	    			fn = fn.substring(4);
	    		} else
	    			local = false;
	    	}
    	}
		if (local)
			local = Arrays.asList(this.getPartNames()).contains(fn);
    	if (local) {
    		if (get) {
    			if (scope != null 	&& (!scope.equals(this.type)))
    				throw new UnsupportedOperationException("Hard cast (as oclAsType) is not supported for structures.");
    			else
	    			return this.getPart(fn);
    		} else {
    			try {
    				this.setAttributeValue(null, this.getAPI().getMetaAttribute(fn, scope), arguments[0]);
    			} catch (org.irisa.triskell.MT.repository.API.Java.IllegalAccessException x) {
    				throw new UnknownCommandException(this, name, arguments, discriminants, x.getMessage());
    			}
    			return VoidValueImpl.getTheInstance();
    		}
    	} else
    		return OclAnyCommandGroup.TheInstance.invoke(scopeQualifiedName, this, name, arguments, discriminants);
    }

    public boolean isUndefined()
    {
		return false;
    }

    public org.irisa.triskell.MT.DataTypes.Java.Value getPart(
        String partName)
    {
		javax.jmi.model.StructureField field = this.type.getStructureField(partName);
		javax.jmi.model.Classifier fieldType = field == null ? null : field.getType();
		javax.jmi.model.CollectionType fieldCollectionType = field != null && (fieldType instanceof javax.jmi.model.CollectionType) ? (javax.jmi.model.CollectionType)fieldType : null;
		if (field == null)
			return new MDRException("No tuple part named " + partName + '.', this.getSpecificAPI());

		return this.getSpecificAPI().java2value(this.refStruct.refGetValue(partName), fieldCollectionType == null ? true : fieldCollectionType.getMultiplicity().isOrdered(), fieldCollectionType == null ? false : fieldCollectionType.getMultiplicity().isUnique(), false);
    }

    public String[] getPartNames()
    {
		java.util.List fields = this.refStruct.refFieldNames();
		return (String []) fields.toArray(new String [fields.size()]);
    }

    public String toString()
    {
		String [] fields = this.getPartNames();
		StringBuffer ret = new StringBuffer();
		ret.append("structure ");
		ret.append(this.type.toString());
		ret.append(" {");
		for (int i = 0; i < fields.length; ++i) {
			ret.append("\n\t");
			ret.append(fields[i]);
			ret.append(" = ");
			ret.append(this.getPart(fields[i]).toString());
		}
		ret.append("\n}");
		return  ret.toString();
    }

    public org.irisa.triskell.MT.DataTypes.Java.Value getFeatureValue(
        org.irisa.triskell.MT.repository.API.Java.ModelElement contextualElement,
        org.irisa.triskell.MT.repository.API.Java.MetaFeature feature,
        org.irisa.triskell.MT.DataTypes.Java.Value[] arguments)
        throws org.irisa.triskell.MT.repository.API.Java.UnknownElementException
    {
		if ((feature instanceof MetaAssociationEnd) || (feature instanceof MetaOperation) || (!this.refStruct.refFieldNames().contains(feature.getName())))
			throw new UnknownElementException(feature);
		return this.getPart(feature.getName());
    }

    public String getUniqId()
    {
		return this.toString();
    }

    public Value invokeQueryOperation(
        ModelElement contextualElement,
        MetaOperation feature,
        Value[] arguments)
        throws org.irisa.triskell.MT.repository.API.Java.UnknownElementException
    {
		throw new UnknownElementException(feature);
    }

    public boolean isKindOf(MetaClass classifier)
    {
		return ModelElementType.TheInstance.conformsTo(classifier) || this.getType().conformsTo(classifier);
    }

    public boolean isMetaObject()
    {
		return false;
    }

    public boolean isTypeOf(MetaClass classifier)
    {
		return this.getType().equals(classifier);
    }

    public void setAttributeValue(
        org.irisa.triskell.MT.repository.API.Java.ModelElement contextualElement,
        org.irisa.triskell.MT.repository.API.Java.MetaAttribute argument,
        org.irisa.triskell.MT.DataTypes.Java.Value value)
        throws org.irisa.triskell.MT.repository.API.Java.IllegalAccessException
    {
		throw new org.irisa.triskell.MT.repository.API.Java.IllegalAccessException(contextualElement, argument);
    }

    public String getTheModelElement()
    {
		return this.getUniqId();
    }

    public void delete()
    {
    }
	
	public void deleteTheModelElement () {
		this.delete();
	}

	public Type getType() {
		return this.type.getStructType();
	}

}

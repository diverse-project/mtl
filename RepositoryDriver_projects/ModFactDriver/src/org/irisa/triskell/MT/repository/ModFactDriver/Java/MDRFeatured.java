package org.irisa.triskell.MT.repository.ModFactDriver.Java;

import java.util.Arrays;
import java.util.List;

import javax.jmi.reflect.InvalidCallException;
import javax.jmi.reflect.InvalidObjectException;

import org.irisa.triskell.MT.DataTypes.Java.Value;
import org.irisa.triskell.MT.DataTypes.Java.commands.CommandGroup;
import org.irisa.triskell.MT.DataTypes.Java.commands.UnknownCommandException;
import org.irisa.triskell.MT.DataTypes.Java.commands.OclAny.OclAnyType;
import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.VoidValueImpl;
import org.irisa.triskell.MT.repository.API.Java.CommonException;
import org.irisa.triskell.MT.repository.API.Java.IllegalAccessException;
import org.irisa.triskell.MT.repository.API.Java.IsQueryException;
import org.irisa.triskell.MT.repository.API.Java.MetaAttribute;
import org.irisa.triskell.MT.repository.API.Java.ModelElement;
import org.irisa.triskell.MT.repository.API.Java.UnknownElementException;

abstract public class MDRFeatured 
    extends org.irisa.triskell.MT.repository.ModFactDriver.Java.MDRElement
    implements org.irisa.triskell.MT.repository.API.Java.ModelElement
{
	//public static final String AttributeGetDiscriminant = "AttributeGet";
	
    protected final javax.jmi.reflect.RefFeatured refFeatured;
    public javax.jmi.reflect.RefFeatured getRefFeatured () {
        return this.refFeatured;
    }
    public int cardRefFeatured () {
        if ( this.refFeatured == null ) return 0;
        else return 1;
    }

    private final boolean undefined;

    private final String error;


    public MDRFeatured(
        boolean undefined,
        String error,
        org.irisa.triskell.MT.repository.ModFactDriver.Java.MDRAPI api,
        javax.jmi.reflect.RefFeatured ref)
    {
        super(api, ref);
		this.undefined = undefined;
		this.error = error;
		this.refFeatured = ref;
    }

    /**
      * @see org.irisa.triskell.MT.DataTypes.Java.Value#invoke(String, org.irisa.triskell.MT.DataTypes.Java.Value[], String[]) 
      */
    public org.irisa.triskell.MT.DataTypes.Java.Value invoke(
    	String [] scopeQualifiedName,
        String name,
        org.irisa.triskell.MT.DataTypes.Java.Value[] arguments,
        String[] discriminants)
        throws org.irisa.triskell.MT.DataTypes.Java.commands.UnknownCommandException, org.irisa.triskell.MT.DataTypes.Java.commands.MultipleCommandException
    {
    	MDRMetaFeature f = null, g = null;
    	MDRMetaClass c = null;
		List discs = Arrays.asList(discriminants == null ? new Object [0] : discriminants);
		boolean setAttribute = discs.contains(ModelElement.SetAttributeDiscriminant);
		boolean attribute = discs.contains(ModelElement.AttributeDiscriminant);
		boolean associationEnd = discs.contains(ModelElement.AssociationDiscriminant);
		boolean operation = discs.contains(ModelElement.OperationDiscriminant);
		boolean feature = ! (attribute || associationEnd || operation);
		if (setAttribute && !feature)
			throw new UnknownCommandException(this, name, arguments, discriminants, "Cannot both query the model and affect an attribute.");
		if (setAttribute) { 
			if (arguments == null || arguments.length != 1)
				throw new UnknownCommandException(this, name, arguments, discriminants, "One parameter required : the new attriute value.");
			try {
				MetaAttribute att = this.getAPI().getMetaAttribute(name, scopeQualifiedName == null ? null : this.getAPI().getMetaClass(scopeQualifiedName));
				this.setAttributeValue(null, att, arguments[0]);	
				return VoidValueImpl.getTheInstance();
			} catch (Exception x) {
				UnknownCommandException thrown = new UnknownCommandException(this, name, arguments, discriminants, "Problems while affecting attribute: " + x.getMessage());
				thrown.initCause(x); 
				throw thrown;
			}
    	} else {
	    	try {
				c = scopeQualifiedName == null ? null : (MDRMetaClass)this.getSpecificAPI().getMetaClass(scopeQualifiedName);
				if (feature)
					f = (MDRMetaFeature)this.getSpecificAPI().getMetaFeature(name, c);
				else {
					if (attribute)
						f = (MDRMetaFeature)this.getSpecificAPI().getMetaAttribute(name, c);
					if (associationEnd) {
						g = (MDRMetaFeature)this.getSpecificAPI().getMetaAssociationEnd(name, null, c);
						if (f == null)
							f = g;
						else
							f = new MDRMetaFeature(this.getSpecificAPI(), name, c, new MDRMetaFeature [] {f, g});
					}
					if (operation) {
						g = (MDRMetaFeature)this.getSpecificAPI().getMetaOperation(name, c);
						if (f == null)
							f = g;
						else
							f = new MDRMetaFeature(this.getSpecificAPI(), name, c, new MDRMetaFeature [] {f, g});
					}
				}
				return ((MDRMetaFeature)f).retreiveRef(null, this, arguments).execute();
	    	} catch (MDRMetaFeature.VisibilityException x) {
	    		throw new UnknownCommandException(this, name, arguments, discriminants, x.getMessage());
	    	} catch (MDRMetaFeature.MultipleDeclarationException x) {
	    		throw new UnknownCommandException(this, name, arguments, discriminants, x.getMessage());
	    	} catch (MDRMetaFeature.ScopeException x) {
	    		throw new UnknownCommandException(this, name, arguments, discriminants, x.getMessage());
	    	} catch (Exception x) {
	    		if ((x instanceof UnknownElementException) && (scopeQualifiedName != null) && (c == null) && (!Arrays.equals(OclAnyType.TheInstance.getQualifiedName(), scopeQualifiedName)))
	    			throw new UnknownCommandException(this, name, arguments, discriminants, x.getMessage());
	    		//Uncomment the following would make the driver able to answer the operation message get_<attribute name> : <attribute type> and set_<attribute name> (<attribute type) wich are the accessor of an attribute
	//    		if ((! discs.contains(AttributeGetDiscriminant)) && name.startsWith("get_") && (arguments == null || arguments.length == 0) && (operation || feature)){
	//				return this.invoke(scopeQualifiedName, name.substring(4), arguments, new String [] {AttributeDiscriminant, AssociationDiscriminant, AttributeGetDiscriminant});
	//    		} else if (name.startsWith("set_") && (arguments != null && arguments.length == 1) && (operation || feature)) {
	//    			f = (MDRMetaFeature)this.getSpecificAPI().getMetaAttribute(name.substring(4), c);
	//    			try {
	//    				this.setAttributeValue(null, (MetaAttribute)f, arguments[0]);
	//    				return VoidValueImpl.getTheInstance();
	//    			} catch (CommonException y) {
	//    			} catch (IllegalAccessException y) {
	//    				throw new UnknownCommandException(this, name, arguments, discriminants, y.toString());
	//    			} catch (UnknownElementException y) {
	//    			}
	//    		}
	    		
	    		return this.getBaseCommandGroup().invoke(scopeQualifiedName, this, name, arguments, discriminants);
	    	}
    	}
    }
    
    protected abstract CommandGroup getBaseCommandGroup ();

    /**
      * @see org.irisa.triskell.MT.repository.API.Java.ModelElement#getFeatureValue(org.irisa.triskell.MT.repository.API.Java.ModelElement, org.irisa.triskell.MT.repository.API.Java.MetaFeature, org.irisa.triskell.MT.DataTypes.Java.Value[]) 
      */
    public org.irisa.triskell.MT.DataTypes.Java.Value getFeatureValue(
        org.irisa.triskell.MT.repository.API.Java.ModelElement contextualElement,
        org.irisa.triskell.MT.repository.API.Java.MetaFeature feature,
        org.irisa.triskell.MT.DataTypes.Java.Value[] arguments)
        throws org.irisa.triskell.MT.repository.API.Java.UnknownElementException, org.irisa.triskell.MT.repository.API.Java.IllegalAccessException, org.irisa.triskell.MT.repository.API.Java.CommonException
    {
	try {
			return ((MDRMetaFeature)feature).retreiveRef((MDRFeatured)contextualElement, this, arguments).execute();
    	} catch (MDRMetaFeature.ElementNotFoundException x) {
    		throw new org.irisa.triskell.MT.repository.API.Java.UnknownElementException(feature);
    	} catch (MDRMetaFeature.MultipleDeclarationException x) {
    		throw new CommonException(feature.toString() + " is ambiguous for " + this.toString() + '.');
    	} catch (MDRMetaFeature.ScopeException x) {
    		throw new CommonException(feature.toString() + " is not accessible in a classifier scope.");
    	} catch (MDRMetaFeature.VisibilityException x) {
			throw new IllegalAccessException(contextualElement, feature);
		} catch (InvalidObjectException x) {
			String msg = "Cannot operate on deleted object.";
			String xmsg = x.getMessage();
			if (xmsg != null && xmsg.length() > 0)
				msg += " (" + xmsg + ')';
			throw new CommonException(msg);
    	} catch (Exception x) {
			throw new CommonException(x.getMessage() + " - " + x.getClass().getName());
		}
    }

    public org.irisa.triskell.MT.DataTypes.Java.Value invokeQueryOperation(
        org.irisa.triskell.MT.repository.API.Java.ModelElement contextualElement,
        org.irisa.triskell.MT.repository.API.Java.MetaOperation feature,
        org.irisa.triskell.MT.DataTypes.Java.Value[] arguments)
        throws org.irisa.triskell.MT.repository.API.Java.UnknownElementException, org.irisa.triskell.MT.repository.API.Java.IllegalAccessException, org.irisa.triskell.MT.repository.API.Java.CommonException, org.irisa.triskell.MT.repository.API.Java.IsQueryException
    {
		try {
			ExecutableJMIOperation op = (ExecutableJMIOperation)((MDRMetaFeature)feature).retreiveRef((MDRFeatured)contextualElement, this, arguments);
			if (op.operation.isQuery())
				return op.execute();
			else
				throw new IsQueryException(feature);
		} catch (MDRMetaFeature.ElementNotFoundException x) {
    		throw new org.irisa.triskell.MT.repository.API.Java.UnknownElementException(feature);
    	} catch (MDRMetaFeature.MultipleDeclarationException x) {
    		throw new CommonException(feature.toString() + " is ambiguous for " + this.toString() + '.');
    	} catch (MDRMetaFeature.ScopeException x) {
    		throw new CommonException(feature.toString() + " is not accessible in a classifier scope.");
    	} catch (MDRMetaFeature.VisibilityException x) {
			throw new IllegalAccessException(contextualElement, feature);
		} catch (InvalidObjectException x) {
			String msg = "Cannot operate on deleted object.";
			String xmsg = x.getMessage();
			if (xmsg != null && xmsg.length() > 0)
				msg += " (" + xmsg + ')';
			throw new CommonException(msg);
    	} catch (Exception x) {
			throw new CommonException(x.getMessage() + " - " + x.getClass().getName());
		}
    }

    /**
      * @see org.irisa.triskell.MT.repository.API.Java.ModelElement#setAttributeValue(org.irisa.triskell.MT.repository.API.Java.ModelElement, org.irisa.triskell.MT.repository.API.Java.MetaAttribute, org.irisa.triskell.MT.DataTypes.Java.Value) 
      */
    public void setAttributeValue(
        org.irisa.triskell.MT.repository.API.Java.ModelElement contextualElement,
        org.irisa.triskell.MT.repository.API.Java.MetaAttribute argument,
        org.irisa.triskell.MT.DataTypes.Java.Value value)
        throws org.irisa.triskell.MT.repository.API.Java.UnknownElementException, org.irisa.triskell.MT.repository.API.Java.CommonException, org.irisa.triskell.MT.repository.API.Java.IllegalAccessException
    {
		try {
			((ExecutableJMIAttribute)((MDRMetaAttribute)argument).retreiveRef((MDRFeatured)contextualElement, this, null)).setValue(value);
		} catch (MDRMetaFeature.ElementNotFoundException x) {
			throw new org.irisa.triskell.MT.repository.API.Java.UnknownElementException(argument);
		} catch (MDRMetaFeature.MultipleDeclarationException x) {
			throw new CommonException(argument.toString() + " is ambiguous for " + this.toString() + '.');
		} catch (MDRMetaFeature.ScopeException x) {
			throw new CommonException(argument.toString() + " is not accessible in a classifier scope.");
		} catch (MDRMetaFeature.VisibilityException x) {
			throw new IllegalAccessException(contextualElement, argument);
		} catch (InvalidCallException x) {
			throw new CommonException("Check you are not attempting to modify a non-changeable resource.");
		} catch (Exception x) {
			throw new CommonException(x.getMessage() + " - " + x.getClass().getName());
		}
    }

    /**
      * @see org.irisa.triskell.MT.DataTypes.Java.ModelElementValue#getTheModelElement() 
      */
    public String getTheModelElement()
    {
		return this.refFeatured == null ? null : (this.refFeatured.refMofId());
    }

    /**
      * @see org.irisa.triskell.MT.DataTypes.Java.Value#getErrorMessage() 
      */
    public String getErrorMessage()
    {
		return error;
    }

    /**
      * @see org.irisa.triskell.MT.DataTypes.Java.Value#isUndefined() 
      */
    public boolean isUndefined()
    {
		return this.undefined;
    }

    public boolean equals(
        org.irisa.triskell.MT.DataTypes.Java.Value rhs)
    {
		return (this == rhs) || ((rhs instanceof MDRFeatured) ? (this.refFeatured == ((MDRFeatured)rhs).refFeatured) && (this.isUndefined() == ((MDRFeatured)rhs).isUndefined()) && (this.isUndefined() || (this.getErrorMessage() == null && ((MDRFeatured)rhs).getErrorMessage() == null) || (this.getErrorMessage().equals(((MDRFeatured)rhs).getErrorMessage()))) : rhs != null && rhs.equals(this));
    }

    public boolean equals(
        java.lang.Object rhs)
    {
		return (rhs instanceof Value) && (this.equals((Value)rhs));
    }

    public void accept(
        org.irisa.triskell.MT.DataTypes.Java.ValueVisitor visitor)
    {
		visitor.visitModelElementValue(this);
    }

    public abstract javax.jmi.reflect.RefClass getRefClass();

    public String getUniqId()
    {
		return this.refFeatured.refMofId();
    }
}

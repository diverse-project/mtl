package org.irisa.triskell.MT.repository.ModFactDriver.Java;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.jmi.model.AliasType;
import javax.jmi.model.Association;
import javax.jmi.model.Classifier;
import javax.jmi.model.CollectionType;
import javax.jmi.model.EnumerationType;
import javax.jmi.model.ModelElement;
import javax.jmi.model.MofClass;
import javax.jmi.model.MofPackage;
import javax.jmi.model.MultiplicityType;
import javax.jmi.model.PrimitiveType;
import javax.jmi.model.StructureType;
import javax.jmi.reflect.RefAssociation;
import javax.jmi.reflect.RefBaseObject;
import javax.jmi.reflect.RefClass;
import javax.jmi.reflect.RefEnum;
import javax.jmi.reflect.RefObject;
import javax.jmi.reflect.RefPackage;
import javax.jmi.reflect.RefStruct;
import javax.jmi.xmi.XmiReader;
import javax.jmi.xmi.XmiWriter;

import org.apache.log4j.Logger;
import org.irisa.triskell.MT.DataTypes.Java.CollectionKind;
import org.irisa.triskell.MT.DataTypes.Java.CollectionValue;
import org.irisa.triskell.MT.DataTypes.Java.Type;
import org.irisa.triskell.MT.DataTypes.Java.commands.Bag.BagType;
import org.irisa.triskell.MT.DataTypes.Java.commands.Boolean.BooleanType;
import org.irisa.triskell.MT.DataTypes.Java.commands.Integer.IntegerType;
import org.irisa.triskell.MT.DataTypes.Java.commands.OclString.StringType;
import org.irisa.triskell.MT.DataTypes.Java.commands.OrderedSet.OrderedSetType;
import org.irisa.triskell.MT.DataTypes.Java.commands.Real.RealType;
import org.irisa.triskell.MT.DataTypes.Java.commands.Sequence.SequenceType;
import org.irisa.triskell.MT.DataTypes.Java.commands.Set.SetType;
import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.BagValueImpl;
import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.OrderedSetValueImpl;
import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.SequenceValueImpl;
import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.SetValueImpl;
import org.irisa.triskell.MT.repository.API.Java.ModelElementIterator;
import org.irisa.triskell.MT.repository.API.Java.utils.ModelElementIteratorToJavaIteratorConverter;
import org.irisa.triskell.MT.utils.Java.AWK;
import org.irisa.triskell.MT.utils.Java.IteratingFinalList;
import org.objectweb.modfact.jmi.xmi.XmiReaderImpl;
import org.objectweb.modfact.jmi.xmi.XmiWriterImpl;
/*import org.netbeans.api.mdr.CreationFailedException;*/

/**
  * The system property
  * "org.irisa.triskell.MT.repository.MDRDriver.ignoreAssociationEndsForNavigation"
  * indicates if association ends should not be explored
  * while asking for a feature or an association end value,
  * even if the corresponding reference does not exist.
  * Notions of reference and AssociationEnd are specific to MOF 1.x
  */
public class MDRAPI 
    implements org.irisa.triskell.MT.repository.API.Java.API
{
    protected static final javax.jmi.xmi.XmiReader reader;
    public static javax.jmi.xmi.XmiReader getReader () {
        return MDRAPI.reader;
    }
    public static int cardReader () {
        if ( MDRAPI.reader == null ) return 0;
        else return 1;
    }

    protected static final javax.jmi.xmi.XmiWriter writer;
    public static javax.jmi.xmi.XmiWriter getWriter () {
        return MDRAPI.writer;
    }
    public static int cardWriter () {
        if ( MDRAPI.writer == null ) return 0;
        else return 1;
    }

//    protected static final org.netbeans.api.mdr.MDRManager mdrManager;
//    public static org.netbeans.api.mdr.MDRManager getMdrManager () {
//        return MDRAPI.mdrManager;
//    }
//    public static int cardMdrManager () {
//        if ( MDRAPI.mdrManager == null ) return 0;
//        else return 1;
//    }

    protected static final org.apache.log4j.Logger staticLog = Logger.getLogger("MDRDriver");
    public static org.apache.log4j.Logger getStaticLog () {
        return staticLog;
    }
    public static int cardStaticLog () {
        if ( staticLog == null ) return 0;
        else return 1;
    }
    
    protected static final org.irisa.triskell.MT.repository.API.Java.CommonException nullPointerException = new org.irisa.triskell.MT.repository.API.Java.CommonException("Null pointer exception.");
    public static org.irisa.triskell.MT.repository.API.Java.CommonException getNullPointerException () {
    	return nullPointerException;
    }
    public static int cardNullPointerException () {
    	if (nullPointerException == null) return 0;
    	else return 1;
    }

    protected final org.apache.log4j.Logger log;
    public org.apache.log4j.Logger getLog () {
        return this.log;
    }
    public int cardLog () {
        if ( this.log == null ) return 0;
        else return 1;
    }

    protected static final String IGNORE_ASSOCIATION_ENDS_FOR_NAVIGATION_KEY = "org.irisa.triskell.MT.repository.MDRDriver.ignoreAssociationEndsForNavigation";
    public static String getIGNORE_ASSOCIATION_ENDS_FOR_NAVIGATION_KEY () {
        return MDRAPI.IGNORE_ASSOCIATION_ENDS_FOR_NAVIGATION_KEY;
    }
    
    private java.lang.ref.SoftReference relatedAssociationEnds = null;
    
    protected static final java.util.Comparator refComparator = new java.util.Comparator () {
    	public int compare(Object o1, Object o2) {
    		return ((javax.jmi.reflect.RefBaseObject)o1).refMofId().compareTo(((javax.jmi.reflect.RefBaseObject)o2).refMofId());
    	}
    };

    protected ModFactRepository modfactRepository = new ModFactRepository();
    public ModFactRepository getModfactRepository () {
        return this.modfactRepository;
    }
//    public int cardMdrRepository () {
//        if ( this.mdrRepository == null ) return 0;
//        else return 1;
//    }

    protected final javax.jmi.reflect.RefPackage model;
    public javax.jmi.reflect.RefPackage getModel () {
        return this.model;
    }
    public int cardModel () {
        if ( this.model == null ) return 0;
        else return 1;
    }

    public final String modelName;
    public String getModelName () {
        return this.modelName;
    }

    protected final Map elements = new Hashtable();

    private final Map refPackages = new Hashtable();

    private final Map refClasses = new Hashtable();

    private final Map refAssociations = new Hashtable();

    protected Value2Java values2javaConverter;
    public Value2Java getValues2javaConverter () {
        return this.values2javaConverter;
    }
    public int cardValues2javaConverter () {
        if ( this.values2javaConverter == null ) return 0;
        else return 1;
    }

    private final Map refEnumerations = new Hashtable();

    private Map primitiveTypes;

    private final Map refStructures = new Hashtable();

    private final org.irisa.triskell.MT.repository.ModFactDriver.Java.Model manipulatedModel;

    public class Value2Java 
        implements org.irisa.triskell.MT.DataTypes.Java.ValueVisitor
    {
        private java.lang.Object distilled;
        protected java.lang.Object getDistilled () {
            return this.distilled;
        }
        protected int cardDistilled () {
            if ( this.distilled == null ) return 0;
            else return 1;
        }

        protected final boolean out;
        public boolean getOut () {
            return this.out;
        }

        protected final boolean multiple;
        public boolean getMultiple () {
            return this.multiple;
        }


        public Value2Java(
            boolean out,
            boolean multiple)
        {
			this.out = out;
			this.multiple = multiple;
        }

        public void visitValue(
            org.irisa.triskell.MT.DataTypes.Java.Value value)
        {
        	this.distilled = null;
        }

        public void visitPrimitiveValue(
            org.irisa.triskell.MT.DataTypes.Java.PrimitiveValue value)
        {
			this.distilled = value.getValue();
			if (multiple) {
				Object val = this.distilled;
				this.distilled = new java.util.ArrayList(1);
				((java.util.List)this.distilled).add(val);
			}
			if (out) {
				if (multiple)
					this.distilled = new java.util.List [] {(java.util.List)this.distilled};
				else
					this.distilled = new String [] {(String)this.distilled};
			}
        }

        public void visitBooleanValue(
            org.irisa.triskell.MT.DataTypes.Java.BooleanValue value)
        {
			this.distilled = value.getTheBoolean() ? Boolean.TRUE : Boolean.FALSE;
			if (multiple) {
				Object val = this.distilled;
				this.distilled = new java.util.ArrayList(1);
				((java.util.List)this.distilled).add(val);
			}
			if (out) {
				if (multiple)
					this.distilled = new java.util.List [] {(java.util.List)this.distilled};
				else
					this.distilled = new Boolean [] {(Boolean)this.distilled};
			}
        }

        public void visitStringValue(
            org.irisa.triskell.MT.DataTypes.Java.StringValue value)
        {
        	this.distilled = value.getTheString();
			if (multiple) {
				Object val = this.distilled;
				this.distilled = new java.util.ArrayList(1);
				((java.util.List)this.distilled).add(val);
			}
			if (out) {
				if (multiple)
					this.distilled = new java.util.List [] {(java.util.List)this.distilled};
				else
					this.distilled = new String [] {(String)this.distilled};
			}
        }

        public void visitRealValue(
            org.irisa.triskell.MT.DataTypes.Java.RealValue value)
        {
        	this.distilled = new Float(value.getTheReal());
			if (multiple) {
				Object val = this.distilled;
				this.distilled = new java.util.ArrayList(1);
				((java.util.List)this.distilled).add(val);
			}
			if (out) {
				if (multiple)
					this.distilled = new java.util.List [] {(java.util.List)this.distilled};
				else
					this.distilled = new Float [] {(Float)this.distilled};
			}
        }

        public void visitIntegerValue(
            org.irisa.triskell.MT.DataTypes.Java.IntegerValue value)
        {
			this.distilled = new Integer(value.getTheInteger());
			if (multiple) {
				Object val = this.distilled;
				this.distilled = new java.util.ArrayList(1);
				((java.util.List)this.distilled).add(val);
			}
			if (out) {
				if (multiple)
					this.distilled = new java.util.List [] {(java.util.List)this.distilled};
				else
					this.distilled = new Integer [] {(Integer)this.distilled};
			}
        }

        public void visitEnumValue(
            org.irisa.triskell.MT.DataTypes.Java.EnumValue value)
        {
			if (! (value instanceof MDREnumered))
				throw new RuntimeException("This enumered is not from the good model");
			this.distilled = ((MDREnumered)value).getRef();
			if (multiple) {
				Object val = this.distilled;
				this.distilled = new java.util.ArrayList(1);
				((java.util.List)this.distilled).add(val);
			}
			if (out) {
				if (multiple)
					this.distilled = new java.util.List [] {(java.util.List)this.distilled};
				else {
					Object val = this.distilled;
					this.distilled = java.lang.reflect.Array.newInstance(this.distilled.getClass(), 1);
					java.lang.reflect.Array.set(this.distilled, 1, val);
				}
			}
        }

        public void visitCollectionValue(
            org.irisa.triskell.MT.DataTypes.Java.CollectionValue value)
        {
	org.irisa.triskell.MT.DataTypes.Java.Value [] theCollection = value.getTheCollection();
//        	CollectionKind theKind = value.getKind();
			Object [] result = new Object [value.getTheCollection().length];
        	for (int i = 0; i < theCollection.length; ++i) {
				theCollection[i].accept(this);
        		result[i] = this.distilled;
        	}
			this.distilled = java.util.Arrays.asList(result);
			if (out)
				this.distilled = new java.util.List [] {(java.util.List)this.distilled};
        }

        public void visitModelElementValue(
            org.irisa.triskell.MT.DataTypes.Java.ModelElementValue value)
        {
			if (value instanceof MDRElement) {
				this.distilled = ((MDRElement)value).getRef();
			} else if (value instanceof MDREnumered) {
					this.distilled = ((MDREnumered)value).getRef();
			} else if (value instanceof MDRStruct) {
					this.distilled = ((MDRStruct)value).getRef();
			} else if (value.isUndefined()) {
				this.distilled = new Throwable(value.getErrorMessage());
			} else
				throw new RuntimeException("This element is not from the good model");

			if (multiple) {
				Object val = this.distilled;
				this.distilled = new java.util.ArrayList(1);
				((java.util.List)this.distilled).add(val);
			}

			if (out) {
				if (multiple)
					this.distilled = new java.util.List [] {(java.util.List)this.distilled};
				else {
					Object val = this.distilled;
					this.distilled = java.lang.reflect.Array.newInstance(this.distilled.getClass(), 1);
					java.lang.reflect.Array.set(this.distilled, 1, val);
				}
			}
        }

        public void visitTupleValue(
            org.irisa.triskell.MT.DataTypes.Java.TupleValue value)
        {
			if (! (value instanceof MDRStruct))
				throw new RuntimeException("This tuple is not from the good model");
			this.distilled = ((MDRStruct)value).getRef();
			if (multiple) {
				Object val = this.distilled;
				this.distilled = new java.util.ArrayList(1);
				((java.util.List)this.distilled).add(val);
			}
			if (out) {
				if (multiple)
					this.distilled = new java.util.List [] {(java.util.List)this.distilled};
				else {
					Object val = this.distilled;
					this.distilled = java.lang.reflect.Array.newInstance(this.distilled.getClass(), 1);
					java.lang.reflect.Array.set(this.distilled, 1, val);
				}
			}
        }

        public void visitVoidValue(
            org.irisa.triskell.MT.DataTypes.Java.VoidValue value)
        {
        	throw new RuntimeException("Cannot convert a Void value.");
        }

        public void visitTypeValue(
            org.irisa.triskell.MT.DataTypes.Java.TypeValue value)
        {
        	throw new RuntimeException("Cannot convert a Type value.");
        }

        public void visitNullValue(
            org.irisa.triskell.MT.DataTypes.Java.NullValue value)
        {
        	this.distilled = null;
        }
    }


    public MDRAPI(
        String repository,
        org.irisa.triskell.MT.repository.ModFactDriver.Java.Metamodel metamodel,
        String modelName,
        org.irisa.triskell.MT.repository.ModFactDriver.Java.Model model)
        throws java.lang.Exception
    {
		synchronized(MDRAPI.class) {
			this.log = Logger.getLogger("MDRDriver." + modelName);
			this.modelName = modelName;
			this.getLog().debug("Initializing MDR driver:\tmodel name is : " + this.getModelName() + '.');
	
//			if (repository != null) {
//				this.getLog().debug("Initializing MDR driver:\tloading repository : " + repository + '.');
//				this.mdrRepository = mdrManager.getRepository(repository);
//			} else {
//				this.getLog().debug("Initializing MDR driver:\tloading default repository.");
//				this.mdrRepository = mdrManager.getDefaultRepository();
//			}
//			if (this.mdrRepository.getExtent(modelName) != null)
//				throw new CreationFailedException("Repository " + modelName + " already exists.");

			this.getLog().debug("Initializing MDR driver:\tinitializing metamodel.");
			this.model = metamodel.getRefPackage(this);
	
			this.getLog().debug("Initializing MDR driver:\tloading model.");
			this.manipulatedModel = model;
			model.load(this);
    	}
    }

    public synchronized void startup(
        org.irisa.triskell.MT.DataTypes.Java.Value[] arguments)
    {
		this.getLog().debug("Starting up MDR driver " + this.getModelName() + '.');
    }

    /**
      * Giving a Boolean value as the first element of the arguments determines if the used extend should be removed.
      */
    public synchronized void shutdown(
        org.irisa.triskell.MT.DataTypes.Java.Value[] arguments)
    {
		this.getLog().debug("Finalizing MDR driver " + this.getModelName() + '.');
		try {
			if (this.manipulatedModel != null)
				this.manipulatedModel.store(this);
		} catch (Exception x) {
			throw new RuntimeException("Problem storing resulting model.", x);
		} finally {
			if (arguments != null && (arguments[0] instanceof org.irisa.triskell.MT.DataTypes.Java.BooleanValue) && ((org.irisa.triskell.MT.DataTypes.Java.BooleanValue)arguments[0]).getTheBoolean())
				this.getModel().refDelete();
		}
    }

    public org.irisa.triskell.MT.repository.API.Java.MetaClass getMetaClass(
        String[] qualifiedName)
        throws org.irisa.triskell.MT.repository.API.Java.UnknownElementException
    {
		String qualifiedNameAsString = org.irisa.triskell.MT.utils.Java.AWK.merge(qualifiedName, Type.PackageIndirection);
		this.getLog().debug("Retreiving class " + qualifiedNameAsString + '.');
		try {
			java.util.List qualifiedNameAsList = Arrays.asList(qualifiedName);
			RefClass clazz = (RefClass)this.refClasses.get(qualifiedNameAsList);
			EnumerationType enum = clazz != null ? null : (EnumerationType)this.refEnumerations.get(qualifiedNameAsList);
			StructureType structure = clazz != null || enum != null ? null : (StructureType)this.refStructures.get(qualifiedNameAsList);
			RefPackage owningPack = null;
			RefClass owningClass = null;
			
			if ((clazz == null) && (enum == null) && (structure == null))  {
				this.getLog().debug("Searching class " + qualifiedNameAsString + '.');
				
				//some optimizations upon packages references
				int indexOfPackages;
				java.util.List considered = qualifiedNameAsList.subList(0, qualifiedNameAsList.size() - 1);
				boolean firstIter = true;
				while ((owningPack == null) && (owningClass == null) && (!considered.isEmpty())) {
					owningPack = (RefPackage) this.refPackages.get(considered);
					if (firstIter) {
						firstIter = false;
						if (owningPack == null)
							owningClass = (RefClass) this.refClasses.get(considered);
					}
					if ((owningPack == null) && (owningClass == null))
						considered = considered.subList(0, considered.size() - 1);
				}
				if ((owningPack == null) && (owningClass == null)) {
					owningPack = this.getModel();
					indexOfPackages = qualifiedName[0].equals(((javax.jmi.model.MofPackage)owningPack.refMetaObject()).getName()) ? 1 : 0;
				} else
					indexOfPackages = considered.size();
				if (owningClass != null)
					owningPack = null;
					
				//A class cannot contain another; owningPack must be non null to enter the loop (unlike owningClass)
				boolean packFound;
				for (; indexOfPackages < qualifiedName.length - 1; ++indexOfPackages) {
					//An enumeration cannot contain another
					if (indexOfPackages == qualifiedName.length - 2) {
						try {
							owningPack = owningPack.refPackage(qualifiedName[indexOfPackages]);
							packFound = owningPack != null;
						} catch (Exception x) {
							packFound = false;
						}
						if (!packFound) {
							owningClass = owningPack.refClass(qualifiedName[indexOfPackages]);
							this.refClasses.put(qualifiedNameAsList.subList(0, indexOfPackages+1), owningClass);
							owningPack = null;
						}
					} else {
						owningPack = owningPack.refPackage(qualifiedName[indexOfPackages]);
						packFound = owningPack != null;
					}
					if (packFound)
						this.refPackages.put(qualifiedNameAsList.subList(0, indexOfPackages+1), owningPack);
				}
	
				//A class cannot contain another
				if (owningClass == null) {
					try {
						clazz = owningPack.refClass(qualifiedName[qualifiedName.length - 1]);
					} catch (Exception x) {
						clazz = null;
					}
				}
				if (clazz == null) {
					Object o;
					if (owningClass != null)
						o = owningClass.refMetaObject().refInvokeOperation("lookupElement", Arrays.asList(new String [] {qualifiedName[qualifiedName.length - 1]}));
					else
						o = owningPack.refMetaObject().refInvokeOperation("lookupElement", Arrays.asList(new String [] {qualifiedName[qualifiedName.length - 1]}));
					if (o instanceof EnumerationType)
						enum = (EnumerationType)o;
					else if (o instanceof StructureType)
						structure = (StructureType)o;
					else throw new Exception("Unsupported element type: " + o.getClass().getName());
				} 
				
				if (clazz != null)
					this.refClasses.put(qualifiedNameAsList, clazz);
				if (enum != null)
					this.refEnumerations.put(qualifiedNameAsList, enum);
				if (structure != null)
					this.refStructures.put(qualifiedNameAsList, structure);
			}
			
			if (clazz != null)
				return this.getMetaClass(clazz);
			else if (enum != null)
				return owningPack != null ? this.getMetaEnumeredWithPackOwner(enum, owningPack) : this.getMetaEnumeredWithClassOwner(enum, owningClass);
			else if (structure != null)
				return owningPack != null ? this.getMetaStructureWithPackOwner(structure, owningPack) : this.getMetaStructureWithClassOwner(structure, owningClass);
			else
				throw new Exception("meta class not found"); 

		} catch (Exception x) {
			this.getLog().debug("Searching class " + qualifiedNameAsString + " failed (" + x.getMessage() + ").");
			throw new org.irisa.triskell.MT.repository.API.Java.UnknownElementException(new MDRUnknownElement(this, "meta class " + org.irisa.triskell.MT.utils.Java.AWK.merge(qualifiedName, Type.PackageIndirection)));
		}
    }

    public org.irisa.triskell.MT.repository.API.Java.MetaAssociation getMetaAssociation(
        String[] qualifiedName)
        throws org.irisa.triskell.MT.repository.API.Java.UnknownElementException
    {
		String qualifiedNameAsString = org.irisa.triskell.MT.utils.Java.AWK.merge(qualifiedName, Type.PackageIndirection);
		this.getLog().debug("Retreiving association " + qualifiedNameAsString + '.');
		try {
			java.util.List qualifiedNameAsList = Arrays.asList(qualifiedName);
			RefAssociation ass = (RefAssociation)this.refAssociations.get(qualifiedNameAsList);
			RefPackage owningPack = null;
			
			if (ass == null)  {
				this.getLog().debug("Searching association " + qualifiedNameAsString + '.');
				
				//some optimizations upon packages references
				int indexOfPackages;
				java.util.List considered = qualifiedNameAsList.subList(0, qualifiedNameAsList.size() - 1);
				while ((owningPack == null) && (!considered.isEmpty())) {
					owningPack = (RefPackage) this.refPackages.get(considered);
					if (owningPack == null)
						considered = considered.subList(0, considered.size() - 1);
				}
				if (owningPack == null) {
					owningPack = this.getModel();
					indexOfPackages = qualifiedName[0].equals(((javax.jmi.model.MofPackage)owningPack.refMetaObject()).getName()) ? 1 : 0;
				} else
					indexOfPackages = considered.size();
					
				boolean packFound;
				for (; indexOfPackages < qualifiedName.length - 1; ++indexOfPackages) {
					owningPack = owningPack.refPackage(qualifiedName[indexOfPackages]);
					if (owningPack != null)
						this.refPackages.put(qualifiedNameAsList.subList(0, indexOfPackages+1), owningPack);
				}
	
				ass = owningPack.refAssociation(qualifiedName[qualifiedName.length - 1]);
				
				if (ass != null)
					this.refAssociations.put(qualifiedNameAsList, ass);
			}
			
			if (ass != null)
				return this.getMetaAssociation(ass);
			else
				throw new Exception("meta association not found"); 

		} catch (Exception x) {
			this.getLog().debug("Searching association " + qualifiedNameAsString + " failed (" + x.getMessage() + ").");
			throw new org.irisa.triskell.MT.repository.API.Java.UnknownElementException(new MDRUnknownElement(this, "meta association " + org.irisa.triskell.MT.utils.Java.AWK.merge(qualifiedName, Type.PackageIndirection)));
		}
    }

    public org.irisa.triskell.MT.repository.API.Java.MetaAssociation getMetaAssociationWithAssociationEnds(
        org.irisa.triskell.MT.repository.API.Java.MetaAssociationEnd[] associationEnds)
        throws org.irisa.triskell.MT.repository.API.Java.UnknownElementException
    {
    	final StringBuffer associationEndsToString = new StringBuffer();
    	for (int i = 0; i < associationEnds.length; ++i) {
    		if (i > 0)
    			associationEndsToString.append(" and ");
    		associationEndsToString.append(associationEnds[i].toString());
    	}
    	class UnknownAssociation extends MDRElement {
    		public UnknownAssociation () {
    			super(MDRAPI.this, null);
    		}
    		
    		public String toString () {
    			return "association composed of " + associationEndsToString.toString();
    		}
    	}
    	
    	MDRMetaAssociationEnd [] ends;
    	if (associationEnds instanceof MDRMetaAssociationEnd[])
    		ends = (MDRMetaAssociationEnd[])associationEnds;
    	else {
    		ends = new MDRMetaAssociationEnd [associationEnds.length];
    		for (int i = 0; i < ends.length; ++i) {
    			if (associationEnds[i] instanceof MDRMetaAssociationEnd)
    				ends[i] = (MDRMetaAssociationEnd)associationEnds[i];
    			else
    				throw new org.irisa.triskell.MT.repository.API.Java.UnknownElementException(new UnknownAssociation());
    		}
    	}    	
    	javax.jmi.reflect.RefAssociation ass = MDRMetaAssociation.retreiveAssociation(ends, this.getModel());
    	if (ass == null)
    		throw new org.irisa.triskell.MT.repository.API.Java.UnknownElementException(new UnknownAssociation());

		return new MDRMetaAssociation(this, ass);
    }

    public org.irisa.triskell.MT.repository.API.Java.MetaFeature getMetaFeature(
        String name,
        org.irisa.triskell.MT.repository.API.Java.MetaClass scope)
    {
		if ((scope != null) && (! (scope instanceof MDRMetaClass)))
			throw new RuntimeException("Undefined scope "  + scope.toString() + "  in this model");
		return new MDRMetaFeature(this, name, (MDRMetaClass)scope);
    }

    public org.irisa.triskell.MT.repository.API.Java.MetaAttribute getMetaAttribute(
        String name,
        org.irisa.triskell.MT.repository.API.Java.MetaClass scope)
    {
		if ((scope != null) && (! (scope instanceof MDRMetaClass)))
			throw new RuntimeException("Undefined scope "  + scope.toString() + "  in this model");
		return new MDRMetaAttribute(this, name, (MDRMetaClass)scope);
    }

    public org.irisa.triskell.MT.repository.API.Java.MetaOperation getMetaOperation(
        String name,
        org.irisa.triskell.MT.repository.API.Java.MetaClass scope)
    {
		if ((scope != null) && (! (scope instanceof MDRMetaClass)))
			throw new RuntimeException("Undefined scope "  + scope.toString() + "  in this model");
		return new MDRMetaOperation(this, name, (MDRMetaClass)scope);
    }

    public org.irisa.triskell.MT.repository.API.Java.MetaAssociationEnd getMetaAssociationEnd(
        String role,
        org.irisa.triskell.MT.repository.API.Java.MetaClass type,
        org.irisa.triskell.MT.repository.API.Java.MetaClass scope)
    {
		if ((scope != null) && (! (scope instanceof MDRMetaClass)))
			throw new RuntimeException("Undefined scope "  + scope.toString() + "  in this model");
		return new MDRMetaAssociationEnd(this, role, (MDRMetaClass)scope, (MDRMetaClass)type);
    }

    public org.irisa.triskell.MT.repository.API.Java.ModelRole getRole(
        org.irisa.triskell.MT.repository.API.Java.ModelElement element,
        org.irisa.triskell.MT.repository.API.Java.MetaAssociationEnd associationEnd)
    {
		return new MDRRole((MDRFeatured)element, (MDRMetaAssociationEnd)associationEnd);
    }

    public org.irisa.triskell.MT.repository.ModFactDriver.Java.MDRElement getElement(
        java.lang.Object ref)
    {
		return (MDRElement) this.elements.get(ref);
    }

    public void setElement(
        org.irisa.triskell.MT.repository.ModFactDriver.Java.MDRElement element,
        java.lang.Object ref)
    {
		this.elements.put(ref, element);
    }

    public org.irisa.triskell.MT.repository.API.Java.MetaClass getMetaClass(
        javax.jmi.reflect.RefClass ref)
    {

		MDRMetaClass ret = (MDRMetaClass) this.getElement(ref);
		if (ret == null) {
			ret = new MDRMetaClass(this, ref);
			this.refClasses.put(java.util.Arrays.asList(ret.getQualifiedName()), ref);
			this.getLog().debug("New proxy for class " + ret.toString() + " built.");
		}
		return ret;
    }

    public org.irisa.triskell.MT.repository.API.Java.MetaAssociation getMetaAssociation(
        javax.jmi.reflect.RefAssociation ref)
    {

		MDRMetaAssociation ret = (MDRMetaAssociation) this.getElement(ref);
		if (ret == null) {
			ret = new MDRMetaAssociation(this, ref);
			this.refAssociations.put(java.util.Arrays.asList(ret.getQualifiedName()), ref);
			this.getLog().debug("New proxy for association " + ret.toString() + " built.");
		}
		return ret;
    }

    public org.irisa.triskell.MT.repository.API.Java.ModelElement getModelElement(
        javax.jmi.reflect.RefObject ref)
    {
		org.irisa.triskell.MT.repository.API.Java.ModelElement ret;
		if (ref == null)
			ret = null;
		else {
			ret = (org.irisa.triskell.MT.repository.API.Java.ModelElement)this.getElement(ref);
			if (ret == null)
				ret = new MDRModelElement(false, null, this, ref);
		}
		return ret;
    }

    public java.lang.Object value2java(
        org.irisa.triskell.MT.DataTypes.Java.Value value,
        boolean out,
        boolean multiple)
    {
		Value2Java translator = new Value2Java(out, multiple);
		value.accept(translator);
		return translator.getDistilled();
    }

    public org.irisa.triskell.MT.repository.API.Java.MetaClass getMetaEnumeredWithPackOwner(
        javax.jmi.model.EnumerationType ref,
        javax.jmi.reflect.RefPackage owner)
    {

		MDRMetaEnumeration ret = (MDRMetaEnumeration) this.getElement(ref);
		if (ret == null) {
			ret = new MDRMetaEnumeration(this, ref, owner);
			this.refEnumerations.put(java.util.Arrays.asList(ret.getQualifiedName()), ref);
			this.getLog().debug("New proxy for " + ret.toString() + " built.");
		}
		return ret;
    }

    public org.irisa.triskell.MT.repository.API.Java.MetaClass getMetaEnumeredWithClassOwner(
        javax.jmi.model.EnumerationType ref,
        javax.jmi.reflect.RefClass owner)
    {

		MDRMetaEnumeration ret = (MDRMetaEnumeration) this.getElement(ref);
		if (ret == null) {
			ret = new MDRMetaEnumeration(this, ref, owner);
			this.refEnumerations.put(java.util.Arrays.asList(ret.getQualifiedName()), ref);
			this.getLog().debug("New proxy for " + ret.toString() + " built.");
		}
		return ret;
    }

    public org.irisa.triskell.MT.DataTypes.Java.Value java2value(
        java.lang.Object object,
        boolean isOrdered,
        boolean isUnique,
        boolean checkSet)
    {
		//Ce code est tr�s laid; il serait int�ressant de forcer MDR � utiliser les "Value"s nativement... 

		if (object == null)
			return org.irisa.triskell.MT.DataTypes.Java.defaultImpl.NullValueImpl.getTheInstance();
    	
    	//Primitive types
    	if (object instanceof Boolean)
    		return new org.irisa.triskell.MT.DataTypes.Java.defaultImpl.BooleanValueImpl(false, null, ((Boolean)object).booleanValue());
    	if (object instanceof String)
			return new org.irisa.triskell.MT.DataTypes.Java.defaultImpl.StringValueImpl(false, null, (String)object);
		if (object instanceof Long) 
			return new org.irisa.triskell.MT.DataTypes.Java.defaultImpl.IntegerValueImpl(false, null, ((Long)object).intValue());
		if (object instanceof Integer) 
			return new org.irisa.triskell.MT.DataTypes.Java.defaultImpl.IntegerValueImpl(false, null, ((Integer)object).intValue());
		if (object instanceof Double) 
			return new org.irisa.triskell.MT.DataTypes.Java.defaultImpl.RealValueImpl(false, null, ((Double)object).floatValue());
		if (object instanceof Float) 
			return new org.irisa.triskell.MT.DataTypes.Java.defaultImpl.RealValueImpl(false, null, ((Float)object).floatValue());
		
		//Model types
		Object me = this.getElement(object);
		if (me instanceof org.irisa.triskell.MT.DataTypes.Java.Value)	
			return (org.irisa.triskell.MT.DataTypes.Java.Value)me;
		 
		if (object instanceof RefEnum)
			return this.getEnumered((RefEnum)object);
		if (object instanceof RefStruct)
			return this.getStruct((RefStruct)object);
		if (object instanceof RefObject)
			return this.getModelElement((RefObject)object);
		
		//Collection type
		//WARNING Nested collection are interpreted as "Sequence"s
		boolean isArray =  object instanceof Object[];
		boolean isCollection = object instanceof java.util.Collection;
		if (isArray || isCollection) {
			if (isArray) {
				Object [] objects = (object instanceof Object[]) ? (Object [])object : ((java.util.Collection)object).toArray();
				java.util.ArrayList valueList = new java.util.ArrayList(objects.length);
				for (int i = 0; i < objects.length; ++i)
					if (objects[i] != null)
						valueList.add(this.java2value(objects[i], true, false, true));
				object = (org.irisa.triskell.MT.DataTypes.Java.Value[])valueList.toArray(new org.irisa.triskell.MT.DataTypes.Java.Value[valueList.size()]);
			} else
				object = new IteratingFinalList(new JMI2JavaConverterIterator(((java.util.Collection)object).iterator(), true, false));
			if (isOrdered && isUnique) {
				if (isArray)
					return new org.irisa.triskell.MT.DataTypes.Java.defaultImpl.OrderedSetValueImpl(false, null, (org.irisa.triskell.MT.DataTypes.Java.Value[])object, checkSet);
				else
					return new org.irisa.triskell.MT.DataTypes.Java.defaultImpl.OrderedSetValueImpl(false, null, (java.util.Collection)object, checkSet);
			} else if (isOrdered && !isUnique) {
				if (isArray)
					return new org.irisa.triskell.MT.DataTypes.Java.defaultImpl.SequenceValueImpl(false, null, (org.irisa.triskell.MT.DataTypes.Java.Value[])object);
				else
					return new org.irisa.triskell.MT.DataTypes.Java.defaultImpl.SequenceValueImpl(false, null, (java.util.Collection)object);
			} else if (!isOrdered && isUnique) {
				if (isArray)
					return new org.irisa.triskell.MT.DataTypes.Java.defaultImpl.SetValueImpl(false, null, (org.irisa.triskell.MT.DataTypes.Java.Value[])object, checkSet);
				else
					return new org.irisa.triskell.MT.DataTypes.Java.defaultImpl.SetValueImpl(false, null, (java.util.Collection)object, checkSet);
			} else /*if (!isOrdered && !isUnique)*/ {
				if (isArray)
					return new org.irisa.triskell.MT.DataTypes.Java.defaultImpl.BagValueImpl(false, null, (org.irisa.triskell.MT.DataTypes.Java.Value[])object);
				else
					return new org.irisa.triskell.MT.DataTypes.Java.defaultImpl.BagValueImpl(false, null, (java.util.Collection)object);
			}
		}

		if (object instanceof Throwable)
				return new MDRException(((Throwable)object).getMessage(), this);
		
		return new MDRException(object.toString() + " : unrecognized value.", this);
    }
    
    /**
     * Does not take care of enumeration ans structures...
     */
    protected RefBaseObject findFromMetaObject(ModelElement e) {
    	LinkedList l = new LinkedList();
    	ModelElement f = e;
    	do {
    		l.addFirst(f);
    		f = f.getContainer(); 
    	} while (f != null);
    	RefBaseObject o = this.getModel();
    	Iterator i = l.iterator();
    	if (o.refMetaObject().equals(l.getFirst()))
    		i.next();
    	while (i.hasNext()) {
    		f = (ModelElement)i.next();
    		if (o instanceof RefPackage) {
    			if (f instanceof MofPackage)
    				o = ((RefPackage)o).refPackage(f);
    			else if (f instanceof MofClass) 
    				o = ((RefPackage)o).refClass(f);
    			else if (f instanceof Association)
    				o = ((RefPackage)o).refAssociation(f);
    			else
    				o = null;
    		} else
    			o = null;
    		if (o == null)
    			throw new IllegalArgumentException("Cannot retreive element from meta object.");
    	}
    	return o;
    }
     
    public RefPackage findPackageFromMetaObject (MofPackage p) {    	
		return (RefPackage)this.findFromMetaObject(p);
    }
    
    public RefAssociation findAssociationFromMetaObject (Association a) { 	
		return (RefAssociation)this.findFromMetaObject(a);
    } 
    
    public RefClass findClassFromMetaObject (MofClass c) { 	
		return (RefClass)this.findFromMetaObject(c);
    }  
    

    public Type getTypeOf(Classifier type, MultiplicityType m)
    {
		Type ret;
		
		while (type instanceof AliasType)
			type = ((AliasType)type).getType();
		
		if (type instanceof CollectionType) {
			ret = this.getTypeOf(((CollectionType)type).getType(), ((CollectionType)type).getMultiplicity());
    	} else if (type instanceof MofClass)
			ret = this.getMetaClass(this.findClassFromMetaObject((MofClass)type));
		else if (type instanceof EnumerationType) {
			RefBaseObject o = this.findFromMetaObject(type.getContainer());
			if (o instanceof RefPackage)
				ret = this.getMetaEnumeredWithPackOwner((EnumerationType)type, (RefPackage)o);
			else if (o instanceof RefClass)
				ret = this.getMetaEnumeredWithClassOwner((EnumerationType)type, (RefClass)o);
			else 
				throw new RuntimeException("Unrecognized type for enumeration " + this.qualifiedName(type.getQualifiedName()));
		} else if (type instanceof StructureType) {
			RefBaseObject o = this.findFromMetaObject(type.getContainer());
			if (o instanceof RefPackage)
				ret = this.getMetaStructureWithPackOwner((StructureType)type, (RefPackage)o);
			else if (o instanceof RefClass)
				ret = this.getMetaStructureWithClassOwner((StructureType)type, (RefClass)o);
			else 
				throw new RuntimeException("Unrecognized type for structure " + this.qualifiedName(type.getQualifiedName()));
		} else if (type instanceof PrimitiveType) {
			String name = type.getName();
	    	if (name.equals("Boolean"))
	    		ret = BooleanType.TheInstance;
	    	else if (name.equals("String"))
				ret = StringType.TheInstance;
	    	else if (name.equals("Long"))
				ret = StringType.TheInstance;
	    	else if (name.equals("Integer"))
				ret = IntegerType.TheInstance;
	    	else if (name.equals("Double"))
				ret = RealType.TheInstance;
	    	else if (name.equals("Float"))
				ret = RealType.TheInstance;
			else
				throw new RuntimeException("Unrecognized type for primitive type " + name);
    	} else
			throw new RuntimeException("Unrecognized type " + this.qualifiedName(type.getQualifiedName()));
		
		if (m != null && m.getUpper() > 1){
			boolean isOrdered = m.isOrdered();
			boolean isUnique = m.isUnique();
			if ((!isOrdered) && (!isUnique))
				ret = BagType.getBagType(ret);
			else if ((!isOrdered) && isUnique)
				ret = SetType.getSetType(ret);
			else if (isOrdered && (!isUnique))
				ret = SequenceType.getSequenceType(ret);
			else
				ret = OrderedSetType.getOrderedSetType(ret);
		}
		
		return ret;
    }
    
    public class JMI2JavaConverterIterator implements Iterator {
    	protected final Iterator delegate;
    	protected final boolean isOrdered;
    	protected final boolean isUnique;
    	
    	public JMI2JavaConverterIterator (Iterator it, boolean isOrdered, boolean isUnique) {
    		this.delegate = it;
    		this.isOrdered = isOrdered;
    		this.isUnique = isUnique;
    	}

		public boolean hasNext() {
			return this.delegate.hasNext();
		}

		public Object next() {
			return MDRAPI.this.java2value(this.delegate.next(), this.isOrdered, this.isUnique, false);
		}

		public void remove() {
			this.delegate.remove();
		}

    }

    public org.irisa.triskell.MT.repository.ModFactDriver.Java.MDREnumered getEnumered(
        javax.jmi.reflect.RefEnum ref)
    {
		MDREnumered ret = (MDREnumered) this.getElement(ref);
		if (ret == null) {
			try {
				java.util.List enumerationQualifiedNameAsList = patched_refTypeName(ref);
				String [] enumerationQualifiedName = new String [enumerationQualifiedNameAsList.size()];
				enumerationQualifiedNameAsList.toArray(enumerationQualifiedName);
				ret = ((MDRMetaEnumeration)this.getMetaClass(enumerationQualifiedName)).findInstance(ref.toString());
			} catch (Exception x) {
				throw new RuntimeException("Internal error.", x);
			}
		}
		return ret;
    }

	private java.util.List patched_refTypeName (javax.jmi.reflect.RefEnum ref)
	{
		// 'ref' contains the label of an enumeration
		// Since the 'refTypeName' method seems to fail, we have to look for every enumerations
		// in the metamodel containing the 'ref' label. If we found only one, we can build the
		// qualified name of the enumeration as a List (just like 'refTypeName' should do)
		this.getLog().debug("Begin work arround for enumerations.");
				
		java.util.List typeName;
		List wantedEnumerationType = new ArrayList();

		// we loop over enumerations defined in the metamodel
		Iterator enumerations = this.getModel().refMetaObject().refOutermostPackage().refClass("EnumerationType").refAllOfType().iterator();
		this.getLog().debug ("We loop over enums definitions");
		while (enumerations.hasNext())
		{
			EnumerationType enum = (EnumerationType)enumerations.next();
			this.getLog().debug ("Current enum is '" + enum.getName()); 
					
			// we loop over the labels of the current enumeration definition
			Iterator labels = enum.getLabels().iterator();
			while (labels.hasNext())
			{
				String label = (String)labels.next();
				this.getLog().debug ("Current label is '" + label);
						
				// we compare the current label with our 'ref'
				if (ref.toString().equals(label))
				{
					wantedEnumerationType.add(enum);
				}
			}
		}
		if (wantedEnumerationType.size() != 1)
		{
			System.out.println (wantedEnumerationType.size());				
			// beeing here means that we have either no enum containing the 'ref' label
			// or more than one definition in different enums.
			throw new RuntimeException ("Work arround failed for enumeration bug.");
		}
		else
		{
			// now we can build the enum qualified name as a List
			EnumerationType enum = (EnumerationType)wantedEnumerationType.get(0);
			typeName = java.util.Collections.unmodifiableList(enum.getQualifiedName());
		}
				
		this.getLog().debug("End work arround for enumerations [" + typeName.toString() + "]");

		return typeName;
	}


    public void removeElement(
        org.irisa.triskell.MT.repository.ModFactDriver.Java.MDRElement element)
    {
    	this.elements.remove(element);
    }

    public javax.jmi.reflect.RefObject getPrimitiveType(
        String name)
    {
    	if (this.primitiveTypes == null) {
    		this.primitiveTypes = new java.util.TreeMap(new java.util.Comparator () {
    				public int compare(Object o1, Object o2) {
    					return((String)o1).compareTo((String)o2);
    				}
    			});
			Iterator primitiveTypeIterator = this.getModel().refMetaObject().refOutermostPackage().refClass("PrimitiveType").refAllOfType().iterator();
			RefObject primitiveType;
			while (primitiveTypeIterator.hasNext()) {
				primitiveType = (RefObject)primitiveTypeIterator.next();
				this.primitiveTypes.put(primitiveType.refGetValue("name"), primitiveType);
			}
    	}
    	return (RefObject)this.primitiveTypes.get(name);
    }

    public org.irisa.triskell.MT.repository.ModFactDriver.Java.MDRStruct getStruct(
        javax.jmi.reflect.RefStruct ref)
    {
		MDRStruct ret = (MDRStruct) this.getElement(ref);
		if (ret == null) {
			try {
				java.util.List structureQualifiedNameAsList = ref.refTypeName();
				String [] structureQualifiedName = new String [structureQualifiedNameAsList.size()];
				structureQualifiedNameAsList.toArray(structureQualifiedName);
				ret = new MDRStruct((MDRMetaStructure)this.getMetaClass(structureQualifiedName), ref);
			} catch (Exception x) {
				throw new RuntimeException("Internal error.", x);
			}
		}
		return ret;
    }

    public org.irisa.triskell.MT.repository.API.Java.MetaClass getMetaStructureWithPackOwner(
        javax.jmi.model.StructureType ref,
        javax.jmi.reflect.RefPackage owner)
    {

		MDRMetaStructure ret = (MDRMetaStructure) this.getElement(ref);
		if (ret == null) {
			ret = new MDRMetaStructure(this, ref, owner);
			this.refStructures.put(java.util.Arrays.asList(ret.getQualifiedName()), ref);
			this.getLog().debug("New proxy for " + ret.toString() + " built.");
		}
		return ret;
    }

    public org.irisa.triskell.MT.repository.API.Java.MetaClass getMetaStructureWithClassOwner(
        javax.jmi.model.StructureType ref,
        javax.jmi.reflect.RefClass owner)
    {

		MDRMetaStructure ret = (MDRMetaStructure) this.getElement(ref);
		if (ret == null) {
			ret = new MDRMetaStructure(this, ref, owner);
			this.refStructures.put(java.util.Arrays.asList(ret.getQualifiedName()), ref);
			this.getLog().debug("New proxy for " + ret.toString() + " built.");
		}
		return ret;
    }

    public String qualifiedName(
        java.util.List qn)
    {
		return AWK.merge((String[])qn.toArray(new String[qn.size()]), Type.PackageIndirection);
    }
    
    protected java.util.Collection getRelatedAssociationEnds(javax.jmi.model.MofClass clazz) {
    	if (this.relatedAssociationEnds == null || this.relatedAssociationEnds.get() == null) {
    		getLog().debug("Listing association ends.");
    		java.util.Map m = new java.util.TreeMap(refComparator);
    		completeRelatedAssociationEnds(this.getModel(), m);
    		this.relatedAssociationEnds = new java.lang.ref.SoftReference(m);
    	}
    	return (java.util.Collection)((java.util.Map)this.relatedAssociationEnds.get()).get(clazz);
    }
    
    protected static void completeRelatedAssociationEnds (javax.jmi.reflect.RefPackage pack, java.util.Map map) {
		java.util.Iterator it = pack.refAllAssociations().iterator(), aes;
		javax.jmi.model.AssociationEnd ae;
		javax.jmi.model.Classifier tae;
		java.util.Collection taes;
		while (it.hasNext()) {
			aes = MDRMetaAssociation.getAssociationEnds((javax.jmi.reflect.RefAssociation)it.next()).iterator();
			while (aes.hasNext()) {
				ae = (javax.jmi.model.AssociationEnd)aes.next();
				tae = ae.getType();
				taes = (java.util.Collection)map.get(tae);
				if (taes == null) {
					taes = new java.util.LinkedList();
					map.put(tae, taes);
				}
				taes.add(ae);
			}
		}
		it = pack.refAllPackages().iterator();
		while (it.hasNext()) {
			completeRelatedAssociationEnds((javax.jmi.reflect.RefPackage)it.next(), map);
		}
    }
    
    public static CollectionValue toCollectionValue (ModelElementIterator it, CollectionKind collectionKind, boolean checkSet) {
    	Collection c = new IteratingFinalList(new ModelElementIteratorToJavaIteratorConverter(it));
		if (collectionKind == CollectionKind.set_kind) return new SetValueImpl(false, null, c, checkSet);
		if (collectionKind == CollectionKind.bag_kind) return new BagValueImpl(false, null, c);
		if (collectionKind == CollectionKind.sequence_kind) return new SequenceValueImpl(false, null, c);
		if (collectionKind == CollectionKind.ordered_set_kind) return new OrderedSetValueImpl(false, null, c, checkSet);
		throw new RuntimeException ("Unsupported collection kind.");
    }


static {
		MDRAPI.getStaticLog().info("Setting up MDR.");
		System.setProperty("org.openide.util.Lookup", "org.irisa.triskell.MT.repository.MDRDriver.Java.RepositoryLookup");
		// mdrManager = org.netbeans.api.mdr.MDRManager.getDefault();
		reader = (XmiReader) new XmiReaderImpl(); //RepositoryLookup.getDefault().lookup(XmiReader.class);
		writer = (XmiWriter) new XmiWriterImpl(); //RepositoryLookup.getDefault().lookup(XmiWriter.class);
		Runtime.getRuntime().addShutdownHook(new Thread (new Runnable () {
			public void run () {
				MDRAPI.getStaticLog().info("Shutting down MDR.");
				//MDRAPI.mdrManager.shutdownAll();
			}
		}));
	

}
}
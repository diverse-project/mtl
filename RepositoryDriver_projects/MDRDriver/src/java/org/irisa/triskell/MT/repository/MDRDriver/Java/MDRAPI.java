/*
 * $Id: MDRAPI.java,v 1.2 2004-10-29 07:02:11 jpthibau Exp $
 * Authors : ffondeme
 * 
 * Copyright 2004 - INRIA - LGPL license
 */
package org.irisa.triskell.MT.repository.MDRDriver.Java;


import javax.jmi.xmi.XmiReader;
import javax.jmi.xmi.XmiWriter;

import org.apache.log4j.Logger;
import org.irisa.triskell.MT.repository.API.Java.Element;
import org.irisa.triskell.MT.repository.API.Java.EventListener;
import org.irisa.triskell.MT.repository.API.Java.EventListenerFactory;
import org.irisa.triskell.MT.repository.MDRDriver.Java.events.MDREventListenerFactory;
import org.irisa.triskell.MT.repository.genericJMIDriver.JMIElement;
import org.netbeans.api.mdr.CreationFailedException;
import org.netbeans.api.mdr.MDRObject;
import org.netbeans.api.mdr.events.MDRChangeListener;

/**
 * 
 * Implementation of the repository API (org.irisa.triskell.MT.repository.API.Java.API)
 * for MDR repository 
 *
  * The system property
  * "org.irisa.triskell.MT.repository.MDRDriver.ignoreAssociationEndsForNavigation"
  * indicates if association ends should not be explored
  * while asking for a feature or an association end value,
  * even if the corresponding reference does not exist.
  * Notions of reference and AssociationEnd are specific to MOF 1.x
  */
public class MDRAPI 
extends org.irisa.triskell.MT.repository.genericJMIDriver.JMIAPI
implements org.irisa.triskell.MT.repository.API.Java.API
{
 /*   protected static final javax.jmi.xmi.XmiReader reader;
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
*/
    protected static final org.netbeans.api.mdr.MDRManager mdrManager;
    public static org.netbeans.api.mdr.MDRManager getMdrManager () {
        return MDRAPI.mdrManager;
    }
    public static int cardMdrManager () {
        if ( MDRAPI.mdrManager == null ) return 0;
        else return 1;
    }

    protected static final org.apache.log4j.Logger staticLog = Logger.getLogger("MDRDriver");
/*
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
*/
/*    protected final org.apache.log4j.Logger log;
    public org.apache.log4j.Logger getLog () {
        return this.log;
    }
    public int cardLog () {
        if ( this.log == null ) return 0;
        else return 1;
    }
 */ 
/*
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
*/
    protected org.netbeans.api.mdr.MDRepository mdrRepository;
    public org.netbeans.api.mdr.MDRepository getMdrRepository () {
        return this.mdrRepository;
    }
    public int cardMdrRepository () {
        if ( this.mdrRepository == null ) return 0;
        else return 1;
    }
/*
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
*/
    private final org.irisa.triskell.MT.repository.genericJMIDriver.Model manipulatedModel;
    public org.irisa.triskell.MT.repository.genericJMIDriver.Model getManipulatedModel () {
        return this.manipulatedModel;
    }
/*
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
*/

    public MDRAPI(
        String repository,
        org.irisa.triskell.MT.repository.genericJMIDriver.Metamodel metamodel,
        String modelName,
        org.irisa.triskell.MT.repository.genericJMIDriver.Model model,
		boolean isSychronized
	)
        throws java.lang.Exception
    {
		super(repository, metamodel, modelName, model, Logger.getLogger("MDRDriver." + modelName));
		synchronized(MDRAPI.class) {

			this.getLog().debug("Initializing MDR driver:\tmodel name is : " + this.getModelName() + '.');
	
			if (repository != null) {
				this.getLog().debug("Initializing MDR driver:\tloading repository : " + repository + '.');
				this.mdrRepository = mdrManager.getRepository(repository);
			} else {
				this.getLog().debug("Initializing MDR driver:\tloading default repository.");
				this.mdrRepository = mdrManager.getDefaultRepository();
			}
			if ((!(metamodel instanceof ImplementedMetamodel)) && this.mdrRepository.getExtent(modelName) != null)
				throw new CreationFailedException("Repository " + modelName + " already exists.");

			this.getLog().debug("Initializing MDR driver:\tinitializing metamodel.");
			super.setModel( metamodel.getRefPackage(this));
	
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


	/** */
	public void addListenerToElement (Element element, EventListener listener)
	{
		MDRObject obj = (MDRObject)((JMIElement)element).getRef();
		if (obj!=null)
        {
			obj.addListener ((MDRChangeListener)listener, listener.isOfType());
        }
		else
		{
			getLog().warn ("Null MDR reference when adding a listener...");
		}
	}
	
	public void removeListenerToElement (Element element, EventListener listener)
	{
	}


	private EventListenerFactory eventListenerFactory = new MDREventListenerFactory (this);
	/** */
	public EventListenerFactory getEventListenerFactory() 
	{
		return this.eventListenerFactory;
	}

	/** */
	private boolean eventThreadChecked = false;
	public void changeEventDispatcherPriority ()
	{
		if (! eventThreadChecked)
		{
			Thread t = getThreadByName ("MDR event dispatcher");
			t.setPriority (t.getThreadGroup().getMaxPriority());
			eventThreadChecked = true;
		}
	}

	/** */
	public Thread getThreadByName (String name)
	{
		Thread[] threads = new Thread[3];
		Thread.enumerate (threads);
		for (int i=0; i<threads.length; i++)
		{
			Thread t = threads[i];
			if (t!=null)
			{
				if (t.getName().equals(name))
				{
					return t;
				}
			}
		}
		return null;
	}
	

static {
		MDRAPI.getStaticLog().info("Setting up MDR.");
		System.setProperty("org.openide.util.Lookup", "org.irisa.triskell.MT.repository.MDRDriver.Java.RepositoryLookup");
		mdrManager = org.netbeans.api.mdr.MDRManager.getDefault();
		reader = (XmiReader) RepositoryLookup.getDefault().lookup(XmiReader.class);
		writer = (XmiWriter) RepositoryLookup.getDefault().lookup(XmiWriter.class);
		Runtime.getRuntime().addShutdownHook(new Thread (new Runnable () {
			public void run () {
				MDRAPI.getStaticLog().info("Shutting down MDR.");
				MDRAPI.mdrManager.shutdownAll();
			}
		}));
	

}

/* (non-Javadoc)
 * @see org.irisa.triskell.MT.DataTypes.Java.Value#equals(org.irisa.triskell.MT.DataTypes.Java.Value)
 */
public boolean equals(Value rhs) {
	// TODO Auto-generated method stub
	return false;
}

/* (non-Javadoc)
 * @see org.irisa.triskell.MT.DataTypes.Java.Value#getErrorMessage()
 */
public String getErrorMessage() {
	// TODO Auto-generated method stub
	return null;
}

/* (non-Javadoc)
 * @see org.irisa.triskell.MT.DataTypes.Java.Value#getType()
 */
public Type getType() {
	// TODO Auto-generated method stub
	return null;
}

/* (non-Javadoc)
 * @see org.irisa.triskell.MT.DataTypes.Java.Value#isUndefined()
 */
public boolean isUndefined() {
	// TODO Auto-generated method stub
	return false;
}

/* (non-Javadoc)
 * @see org.irisa.triskell.MT.DataTypes.Java.Value#invoke(java.lang.String[], java.lang.String, org.irisa.triskell.MT.DataTypes.Java.Value[], java.lang.String[])
 */
public Value invoke(String[] scopeQualifiedName, String name,
		Value[] arguments, String[] discriminants)
		throws UnknownCommandException, MultipleCommandException {
	// TODO Auto-generated method stub
	return null;
}

/* (non-Javadoc)
 * @see org.irisa.triskell.MT.DataTypes.Java.Value#accept(org.irisa.triskell.MT.DataTypes.Java.ValueVisitor)
 */
public void accept(ValueVisitor visitor) {
	visitor.visitRepositoryAPIValue(this);

}
/* (non-Javadoc)
 * @see org.irisa.triskell.MT.DataTypes.Java.RepositoryAPIValue#getTheRepositoryAPI()
 */
public String getTheRepositoryAPI() {
	// TODO Auto-generated method stub
	return null;
}

}


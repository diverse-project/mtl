/* $Id: ExecutableFeature.java,v 1.1 2004-10-25 13:59:56 dvojtise Exp $
 * Authors : 
 * 
 * Copyright 2003 - INRIA - LGPL license
 */
package org.inria.EMFDriver;

/**
 * @author jpthibau
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public abstract class ExecutableFeature {
/*	protected org.irisa.triskell.MT.repository.genericJMIDriver.JMIFeatured self;
	public org.irisa.triskell.MT.repository.genericJMIDriver.JMIFeatured getSelf () {
		return this.self;
	}
	public int cardSelf () {
		if ( this.self == null ) return 0;
		else return 1;
	}

	protected org.irisa.triskell.MT.DataTypes.Java.Value[] arguments = null;
	public org.irisa.triskell.MT.DataTypes.Java.Value getArguments (int i) {
		return this.arguments[i];
	}
	public org.irisa.triskell.MT.DataTypes.Java.Value[] getArguments () {
		return this.arguments;
	}
	public int cardArguments () {
		return this.arguments.length;
	}

	protected org.irisa.triskell.MT.repository.genericJMIDriver.JMIAPI api;
	public org.irisa.triskell.MT.repository.genericJMIDriver.JMIAPI getApi () {
		return this.api;
	}
	public int cardApi () {
		if ( this.api == null ) return 0;
		else return 1;
	}


	public ExecutableFeature(
		org.irisa.triskell.MT.repository.genericJMIDriver.JMIAPI api,
		org.irisa.triskell.MT.repository.genericJMIDriver.JMIFeatured self,
		org.irisa.triskell.MT.DataTypes.Java.Value[] arguments)
	{
		this.api = api;
		this.self = self;
		this.arguments = arguments;
	}*/

	public abstract org.irisa.triskell.MT.DataTypes.Java.Value execute()
		throws java.lang.Exception;

/*	public static java.util.List adaptArguments(
		javax.jmi.model.Operation operation,
		org.irisa.triskell.MT.DataTypes.Java.Value[] arguments,
		org.irisa.triskell.MT.repository.genericJMIDriver.JMIAPI api)
	{
		try {
			javax.jmi.model.Parameter [] parameters = JMIMetaFeature.getParameters(operation, new String [] {"in_dir", "inout_dir", "out_dir"});
			java.util.List ret = new java.util.ArrayList(parameters.length);
			Object toAdd;
			for (int parameterIndex = 0, argumentIndex = 0; parameterIndex < parameters.length; ++parameterIndex) {
				if (parameters[parameterIndex].getDirection().toString().equals("out_dir")) {
					if (parameters[parameterIndex].getMultiplicity().getUpper() > 1)
						ret.add(new java.util.List [] {new java.util.ArrayList()});
					else {
						try {
							javax.jmi.model.Classifier typeClassifier = parameters[parameterIndex].getType();
							Class type;
							while (typeClassifier instanceof javax.jmi.model.AliasType)
								typeClassifier = ((javax.jmi.model.AliasType)typeClassifier).getType();
							
							if (	(typeClassifier instanceof javax.jmi.model.MofClass)
								||	(typeClassifier instanceof javax.jmi.model.EnumerationType)
								||	(typeClassifier instanceof javax.jmi.model.StructureType)
								) {						
								java.util.List typeQualifiedName = parameters[parameterIndex].getType().getQualifiedName();
								JMIMetaClass typeJMI = (JMIMetaClass)api.getMetaClass((String[])typeQualifiedName.toArray(new String[typeQualifiedName.size()]));
								type = typeJMI.getRefClass().getClass();
							} else if (typeClassifier instanceof javax.jmi.model.DataType) {
								String typeName = typeClassifier.getName();
								if (typeName.equals("String"))
									type = String.class;
								else if (typeName.equals("Boolean"))
									type = Boolean.class;
								else if (typeName.equals("Double"))
									type = Double.class;
								else if (typeName.equals("Float"))
									type = Float.class;
								else if (typeName.equals("Integer"))
									type = Integer.class;
								else if (typeName.equals("Long"))
									type = Long.class;
								else
									throw new RuntimeException("Internal error.", new Exception("Unmanaged parameter data type: " + typeName));
							} else
								throw new RuntimeException("Internal error.", new Exception("Unmanaged parameter type: " + typeClassifier.getClass().toString()));
							ret.add(java.lang.reflect.Array.newInstance(type, 1));
						} catch (UnknownElementException x) {
							throw new RuntimeException("Internal error.", x);
						} catch (ClassCastException x) {
						throw new RuntimeException("Internal error.", x);
						}
					}
				} else 
					ret.add(api.value2java(arguments[argumentIndex++], parameters[parameterIndex].getDirection().toString().equals("inout_dir"), parameters[parameterIndex].getMultiplicity().getUpper() > 1));
			}
			return ret;
		} catch (ArrayIndexOutOfBoundsException x) {
			throw new RuntimeException("Internal error.", x);
		}
	}

	public static org.irisa.triskell.MT.DataTypes.Java.Value adaptResults(
		javax.jmi.model.Operation operation,
		java.util.List javaArguments,
		java.lang.Object result,
		org.irisa.triskell.MT.repository.genericJMIDriver.JMIAPI api)
	{
		java.util.List outResults = new java.util.ArrayList();
		javax.jmi.model.Parameter [] parameters = JMIMetaFeature.getParameters(operation, new String [] {"in_dir", "inout_dir", "out_dir"});
		for (int parameterIndex = 0; parameterIndex < parameters.length; ++parameterIndex)
			if (parameters[parameterIndex].getDirection().toString().equals("inout_dir") || parameters[parameterIndex].getDirection().toString().equals("out_dir"))
				outResults.add(new org.irisa.triskell.MT.DataTypes.Java.defaultImpl.TupleElementImpl(parameters[parameterIndex].getName(), api.java2value(((Object[])javaArguments.get(parameterIndex))[0], parameters[parameterIndex].getMultiplicity().isOrdered(), parameters[parameterIndex].getMultiplicity().isUnique(), false)));

		javax.jmi.model.Parameter [] returnParameters = JMIMetaFeature.getParameters(operation, new String [] {"return_dir"});
		javax.jmi.model.Parameter returnParameter = returnParameters.length > 0 ? returnParameters[0] : null;
		Value ret = api.java2value(result, returnParameter == null ? false : returnParameter.getMultiplicity().isOrdered(), returnParameter == null ? false : returnParameter.getMultiplicity().isUnique(), false);
		if (!outResults.isEmpty()) {
			outResults.add(new org.irisa.triskell.MT.DataTypes.Java.defaultImpl.TupleElementImpl("result", ret));
			org.irisa.triskell.MT.DataTypes.Java.defaultImpl.TupleElementImpl [] elements = new org.irisa.triskell.MT.DataTypes.Java.defaultImpl.TupleElementImpl [outResults.size()];
			outResults.toArray(elements);
			ret = new org.irisa.triskell.MT.DataTypes.Java.defaultImpl.TupleValueImpl(false, null, elements);
		}
		return ret;
	}
*/
}

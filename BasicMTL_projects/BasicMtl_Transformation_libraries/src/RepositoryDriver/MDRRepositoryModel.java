/*
 * Created on 25 août 2003
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package RepositoryDriver;

import org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLBooleanInterface;
import org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLDataTypeInterface;
import org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLStringInterface;
import org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface;
import org.irisa.triskell.MT.BasicMTL.DataTypes.impl.BMTLBoolean;
import org.irisa.triskell.MT.BasicMTL.DataTypes.impl.BMTLSet;
import org.irisa.triskell.MT.BasicMTL.DataTypes.impl.BMTLVoid;
import org.irisa.triskell.MT.BasicMTL.DataTypes.impl.CommonFunctions;
import org.irisa.triskell.MT.BasicMTL.TopTypes.BMTLObjectInterface;
import org.irisa.triskell.MT.BasicMTL.TopTypes.InstanciableType;
import org.irisa.triskell.MT.DataTypes.Java.Type;
import org.irisa.triskell.MT.DataTypes.Java.TypeValue;
import org.irisa.triskell.MT.DataTypes.Java.Value;
import org.irisa.triskell.MT.DataTypes.Java.ValueVisitor;
import org.irisa.triskell.MT.DataTypes.Java.commands.MultipleCommandException;
import org.irisa.triskell.MT.DataTypes.Java.commands.UnknownCommandException;
import org.irisa.triskell.MT.DataTypes.Java.commands.OclAny.OclAnyType;
import org.irisa.triskell.MT.DataTypes.Java.commands.OclAny.OclAny_oclIsKindOf;
import org.irisa.triskell.MT.DataTypes.Java.commands.OclAny.OclAny_oclIsTypeOf;
import org.irisa.triskell.MT.DataTypes.Java.commands.OclAny.OclAny_toErr;
import org.irisa.triskell.MT.DataTypes.Java.commands.OclAny.OclAny_toOut;
import org.irisa.triskell.MT.repository.MDRDriver.Java.MDRAPI;
import org.irisa.triskell.MT.repository.MDRDriver.Java.Metamodel;
import org.irisa.triskell.MT.repository.MDRDriver.Java.Model;
import org.irisa.triskell.MT.repository.MDRDriver.Java.SimpleStandaloneModelManager;
import org.irisa.triskell.MT.utils.Java.AWK;

/**
 * @author ffondeme
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class MDRRepositoryModel extends SimpleStandaloneModelManager implements BMTLObjectInterface, Value {
	protected static final String n = "MDRRepositoryModel";
	protected static final String [] qn = new String [] {"MDRModelManager", n};
	 
	public static final Type TheType = new InstanciableType () {
		
     public String getName() {
     	return n;
     }

     public String[] getQualifiedName() {
     	return qn;
     }

     public boolean isKindOf(
        org.irisa.triskell.MT.DataTypes.Java.Value v) {
        return v instanceof MDRRepositoryModel;
     }

     public boolean isTypeOf(
        org.irisa.triskell.MT.DataTypes.Java.Value v) {
        return v instanceof MDRRepositoryModel;
     }


     public boolean conformsTo(
        org.irisa.triskell.MT.DataTypes.Java.Type parentType) {
        return parentType == this;
     }

     public org.irisa.triskell.MT.DataTypes.Java.CollectionValue allInstances()
        throws java.lang.Exception {
        return new BMTLSet();
     }

     public String toString() {
     	return n;
     }

     public String getQualifiedNameAsString() {
     	return AWK.merge(qn, "::");
     }

	 public BMTLObjectInterface instanciate() {
		return new MDRRepositoryModel();
	 }
     	
	};

	public MDRRepositoryModel() {
		super();
	}
	
	

	/* (non-Javadoc)
	 * @see org.irisa.triskell.MT.repository.MDRDriver.Java.SimpleStandaloneModelManager#getModel(java.lang.String, org.irisa.triskell.MT.repository.MDRDriver.Java.Metamodel, java.lang.String, org.irisa.triskell.MT.repository.MDRDriver.Java.Model)
	 */
	public MDRAPI BMTL_getModel(
		BMTLStringInterface repository,
		Metamodel metamodel,
		BMTLStringInterface modelName,
		Model model)
		throws Exception {
		return this.getModel(repository == null ? null : repository.getTheString(), metamodel, modelName == null ? null : modelName.getTheString(), model);
	}

	/* (non-Javadoc)
	 * @see org.irisa.triskell.MT.repository.MDRDriver.Java.SimpleStandaloneModelManager#getModelfromGUI(java.lang.String)
	 */
	public MDRAPI BMTL_getModelfromGUI(BMTLStringInterface userMessage) throws Exception {
		return this.getModelfromGUI(userMessage == null ? null : userMessage.getTheString());
	}

	/* (non-Javadoc)
	 * @see org.irisa.triskell.MT.repository.MDRDriver.Java.SimpleStandaloneModelManager#getModelFromXMI(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public MDRAPI BMTL_getModelFromXMI(
		BMTLStringInterface metamodelXmiFileName,
		BMTLStringInterface modelName,
		BMTLStringInterface modelXmiInputFileName,
		BMTLStringInterface modelXmiOuputFileName)
		throws Exception {
		return this.getModelFromXMI(
			metamodelXmiFileName == null ? null : metamodelXmiFileName.getTheString(),
			modelName == null ? null : modelName.getTheString(),
			modelXmiInputFileName == null ? null : modelXmiInputFileName.getTheString(),
			modelXmiOuputFileName == null ? null : modelXmiOuputFileName.getTheString());
	}

	/* (non-Javadoc)
	 * @see org.irisa.triskell.MT.repository.MDRDriver.Java.SimpleStandaloneModelManager#getModelFromXMI(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public MDRAPI BMTL_getModelFromXMI(
		BMTLStringInterface metamodelXmiFileName,
		BMTLStringInterface metaPackageToInstanciate,
		BMTLStringInterface modelName,
		BMTLStringInterface modelXmiInputFileName,
		BMTLStringInterface modelXmiOuputFileName)
		throws Exception {
		return this.getModelFromXMI(
			metamodelXmiFileName == null ? null : metamodelXmiFileName.getTheString(),
			metaPackageToInstanciate == null ? null : metaPackageToInstanciate.getTheString(),
			modelName == null ? null : modelName.getTheString(),
			modelXmiInputFileName == null ? null : modelXmiInputFileName.getTheString(),
			modelXmiOuputFileName == null ? null : modelXmiOuputFileName.getTheString());
	}

	/* (non-Javadoc)
	 * @see org.irisa.triskell.MT.repository.MDRDriver.Java.SimpleStandaloneModelManager#init()
	 */
	public SimpleStandaloneModelManager BMTL_init() throws Exception {
		return this.init();
	}



	/* (non-Javadoc)
	 * @see org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLDataTypeInterface#getDelegate()
	 */
	public Value getDelegate() {
		return this;
	}



	/* (non-Javadoc)
	 * @see org.irisa.triskell.MT.DataTypes.Java.Value#isUndefined()
	 */
	public boolean isUndefined() {
		return false;
	}



	/* (non-Javadoc)
	 * @see org.irisa.triskell.MT.DataTypes.Java.Value#getErrorMessage()
	 */
	public String getErrorMessage() {
		return null;
	}



	/* (non-Javadoc)
	 * @see org.irisa.triskell.MT.DataTypes.Java.Value#equals(org.irisa.triskell.MT.DataTypes.Java.Value)
	 */
	public boolean equals(Value rhs) {
		return this == rhs;
	}



	/* (non-Javadoc)
	 * @see org.irisa.triskell.MT.DataTypes.Java.Value#invoke(java.lang.String[], java.lang.String, org.irisa.triskell.MT.DataTypes.Java.Value[], java.lang.String[])
	 */
	public Value invoke(String[] scopeQualifiedName, String name, Value[] arguments, String[] discriminants) throws UnknownCommandException, MultipleCommandException {
		return BMTLVoid.TheInstance;
	}



	/* (non-Javadoc)
	 * @see org.irisa.triskell.MT.DataTypes.Java.Value#accept(org.irisa.triskell.MT.DataTypes.Java.ValueVisitor)
	 */
	public void accept(ValueVisitor visitor) {
		visitor.visitValue(this);
	}



	/* (non-Javadoc)
	 * @see org.irisa.triskell.MT.DataTypes.Java.Value#getType()
	 */
	public Type getType() {
		return TheType;
	}



	/* (non-Javadoc)
	 * @see org.irisa.triskell.MT.BasicMTL.TopTypes.BMTLObjectInterface#delete()
	 */
	public void delete() {
		
	}



	/* (non-Javadoc)
	 * @see org.irisa.triskell.MT.BasicMTL.TopTypes.BMTLObjectInterface#BMTL_delete()
	 */
	public BMTLVoidInterface BMTL_delete() {
		return BMTLVoid.TheInstance;
	}



	/* (non-Javadoc)
	 * @see org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOclAnyInterface#getOclAnyDelegate()
	 */
	public Value getOclAnyDelegate() {
		return this;
	}



	/* (non-Javadoc)
	 * @see org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOclAnyInterface#BMTL__3d(org.irisa.triskell.MT.DataTypes.Java.Value)
	 */
	public BMTLBooleanInterface BMTL__3d(Value rhs) {
		return this.equals(rhs) ? BMTLBoolean.TRUE : BMTLBoolean.FALSE;
	}



	/* (non-Javadoc)
	 * @see org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOclAnyInterface#BMTL__3c_3e(org.irisa.triskell.MT.DataTypes.Java.Value)
	 */
	public BMTLBooleanInterface BMTL__3c_3e(Value rhs) {
		return this.BMTL__3d(rhs).BMTL_not();
	}



	/* (non-Javadoc)
	 * @see org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOclAnyInterface#BMTL_oclIsUndefined()
	 */
	public BMTLBooleanInterface BMTL_oclIsUndefined() {
		return BMTLBoolean.FALSE;
	}



	/* (non-Javadoc)
	 * @see org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOclAnyInterface#BMTL_oclIsTypeOf(org.irisa.triskell.MT.DataTypes.Java.TypeValue)
	 */
	public BMTLBooleanInterface BMTL_oclIsTypeOf(TypeValue type) {
		return (BMTLBooleanInterface)CommonFunctions.toBMTLDataType(OclAny_oclIsTypeOf.TheInstance.invoke(this, new Value [] {type}));
	}



	/* (non-Javadoc)
	 * @see org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOclAnyInterface#BMTL_oclIsKindOf(org.irisa.triskell.MT.DataTypes.Java.TypeValue)
	 */
	public BMTLBooleanInterface BMTL_oclIsKindOf(TypeValue type) {
		return (BMTLBooleanInterface)CommonFunctions.toBMTLDataType(OclAny_oclIsKindOf.TheInstance.invoke(this, new Value [] {type}));
	}



	/* (non-Javadoc)
	 * @see org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOclAnyInterface#BMTL_toOut()
	 */
	public BMTLVoidInterface BMTL_toOut() {
		OclAny_toOut.TheInstance.invoke(this, null);
		return BMTLVoid.TheInstance;
	}


	public BMTLVoidInterface BMTL_toErr() {
		OclAny_toErr.TheInstance.invoke(this, null);
		return BMTLVoid.TheInstance;
	}


	public BMTLBooleanInterface BMTL_isNull(Value v) {
		return v == null ? BMTLBoolean.TRUE : BMTLBoolean.FALSE;
	}

}

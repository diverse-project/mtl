/* $Id: MDRModelManager.java,v 1.1 2004-10-13 14:24:39 jpthibau Exp $
 * Created on 25 août 2003
 */
package MDRDriver;

import org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLBagInterface;
import org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLBooleanInterface;
import org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLDataTypeInterface;
import org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLOrderedSetInterface;
import org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSequenceInterface;
import org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLSetInterface;
import org.irisa.triskell.MT.BasicMTL.DataTypes.BMTLVoidInterface;
import org.irisa.triskell.MT.BasicMTL.DataTypes.impl.BMTLBag;
import org.irisa.triskell.MT.BasicMTL.DataTypes.impl.BMTLBoolean;
import org.irisa.triskell.MT.BasicMTL.DataTypes.impl.BMTLOrderedSet;
import org.irisa.triskell.MT.BasicMTL.DataTypes.impl.BMTLSequence;
import org.irisa.triskell.MT.BasicMTL.DataTypes.impl.BMTLSet;
import org.irisa.triskell.MT.BasicMTL.DataTypes.impl.BMTLVoid;
import org.irisa.triskell.MT.BasicMTL.DataTypes.impl.CommonFunctions;
import org.irisa.triskell.MT.BasicMTL.TopTypes.BMTLObjectInterface;
import org.irisa.triskell.MT.DataTypes.Java.CollectionValue;
import org.irisa.triskell.MT.DataTypes.Java.StringValue;
import org.irisa.triskell.MT.DataTypes.Java.BooleanValue;
import org.irisa.triskell.MT.DataTypes.Java.Type;
import org.irisa.triskell.MT.DataTypes.Java.TypeValue;
import org.irisa.triskell.MT.DataTypes.Java.Value;
import org.irisa.triskell.MT.DataTypes.Java.ValueVisitor;
import org.irisa.triskell.MT.DataTypes.Java.commands.InstanciableType;
import org.irisa.triskell.MT.DataTypes.Java.commands.MultipleCommandException;
import org.irisa.triskell.MT.DataTypes.Java.commands.UnknownCommandException;
import org.irisa.triskell.MT.DataTypes.Java.commands.OclAny.OclAnyType;
import org.irisa.triskell.MT.DataTypes.Java.commands.OclAny.OclAny_oclIsKindOf;
import org.irisa.triskell.MT.DataTypes.Java.commands.OclAny.OclAny_oclIsTypeOf;
import org.irisa.triskell.MT.DataTypes.Java.commands.OclAny.OclAny_toErr;
import org.irisa.triskell.MT.DataTypes.Java.commands.OclAny.OclAny_toOut;
import org.irisa.triskell.MT.repository.MDRDriver.Java.ImplementedMetamodel;
import org.irisa.triskell.MT.repository.MDRDriver.Java.LoadedMetamodel;
import org.irisa.triskell.MT.repository.MDRDriver.Java.MDRAPI;
import org.irisa.triskell.MT.repository.genericJMIDriver.Metamodel;
import org.irisa.triskell.MT.repository.genericJMIDriver.Model;
import org.irisa.triskell.MT.repository.MDRDriver.Java.MofMetamodel;
import org.irisa.triskell.MT.repository.MDRDriver.Java.SimpleStandaloneModelManager;
import org.irisa.triskell.MT.repository.MDRDriver.Java.XmiMetamodel;
import org.irisa.triskell.MT.repository.MDRDriver.Java.XmiModel;
import org.irisa.triskell.MT.utils.Java.AWK;

/**
 * @author ffondeme
 *
 *	Implementation of MDRModelManager BMTL object using SimpleStandaloneModelManager from the repository driver
 */
public class MDRModelManager extends SimpleStandaloneModelManager implements BMTLObjectInterface, Value {
	protected static final String n = "MDRModelManager";
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
        return v instanceof MDRModelManager;
     }

     public boolean isTypeOf(
        org.irisa.triskell.MT.DataTypes.Java.Value v) {
        return v instanceof MDRModelManager;
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

	 public Value instanciate() {
		return new MDRModelManager();
	 }

	 public Value instanciateFromJavaObject (Object javaObject)
	 {
		 // currently, no java object allows to create a MDRModelManager 
		 // DVK : currently we have no use for that 
		 return null;
	 }	
	 public boolean isInstanciableFromJavaObject (Object javaObject)
	 {
		 return false;
	 }
     	
	};

	public MDRModelManager() {
		super();
	}
	
	

	/* (non-Javadoc)
	 * @see org.irisa.triskell.MT.repository.MDRDriver.Java.SimpleStandaloneModelManager#getModel(java.lang.String, org.irisa.triskell.MT.repository.MDRDriver.Java.Metamodel, java.lang.String, org.irisa.triskell.MT.repository.MDRDriver.Java.Model)
	 */
	public MDRAPI BMTL_getModel(
		StringValue repository,
		Metamodel metamodel,
		StringValue modelName,
		Model model)
		throws Exception {
		return this.getModel(repository == null ? null : repository.getTheString(), metamodel, modelName == null ? null : modelName.getTheString(), model, false);
	}

	/* (non-Javadoc)
	 * @see org.irisa.triskell.MT.repository.MDRDriver.Java.SimpleStandaloneModelManager#getModelfromGUI(java.lang.String)
	 */
	public MDRAPI BMTL_getModelfromGUI(StringValue userMessage) throws Exception {
		return this.getModelfromGUI(userMessage == null ? null : userMessage.getTheString());
	}

	/* (non-Javadoc)
	 * @see org.irisa.triskell.MT.repository.MDRDriver.Java.SimpleStandaloneModelManager#getModelFromXMI(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public MDRAPI BMTL_getModelFromXMI(
		StringValue metamodelXmiFileName,
		StringValue modelName,
		StringValue modelXmiInputFileName,
		StringValue modelXmiOuputFileName
	)
		throws Exception {
		return this.getModelFromXMI(
			metamodelXmiFileName == null ? null : metamodelXmiFileName.getTheString(),
			modelName == null ? null : modelName.getTheString(),
			modelXmiInputFileName == null ? null : modelXmiInputFileName.getTheString(),
			modelXmiOuputFileName == null ? null : modelXmiOuputFileName.getTheString(),
			this.getIsSynchronized()
		);
	}

	/* (non-Javadoc)
	 * @see org.irisa.triskell.MT.repository.MDRDriver.Java.SimpleStandaloneModelManager#getModelFromXMI(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public MDRAPI BMTL_getModelFromXMI(
		StringValue metamodelXmiFileName,
		StringValue metaPackageToInstanciate,
		StringValue modelName,
		StringValue modelXmiInputFileName,
		StringValue modelXmiOuputFileName
	)
		throws Exception {
		return this.getModelFromXMI(
			metamodelXmiFileName == null ? null : metamodelXmiFileName.getTheString(),
			metaPackageToInstanciate == null ? null : metaPackageToInstanciate.getTheString(),
			modelName == null ? null : modelName.getTheString(),
			modelXmiInputFileName == null ? null : modelXmiInputFileName.getTheString(),
			modelXmiOuputFileName == null ? null : modelXmiOuputFileName.getTheString(),
			this.getIsSynchronized()
		);
	}

	/* (non-Javadoc)
	 * @see org.irisa.triskell.MT.repository.MDRDriver.Java.SimpleStandaloneModelManager#init()
	 */
	public SimpleStandaloneModelManager BMTL_init() throws Exception {
		return this.init();
	}
	
	/* (non-Javadoc)
	 * @see org.irisa.triskell.MT.repository.MDRDriver.Java.SimpleStandaloneModelManager#storeModel()
	 */
	public void BMTL_storeModel(StringValue modelName) throws Exception
	{
		this.storeModel(modelName == null ? null : modelName.getTheString());
	}

	
	/** */
	private boolean isSynchronized = false;
	public boolean getIsSynchronized ()               { return this.isSynchronized; }
	public void    setIsSynchronized (boolean value)  { this.isSynchronized=value; System.out.println ("isSynchro : " + getIsSynchronized()); }
	
	public void BMTL_setIsSynchronized (BooleanValue value)
	{
		setIsSynchronized (value.getTheBoolean());
	}
	
	/* (non-Javadoc)
	 * @see org.irisa.triskell.MT.repository.MDRDriver.Java.SimpleStandaloneModelManager#disposeModel()
	 */
	public void BMTL_disposeModel(StringValue modelName) throws Exception
	{
		this.disposeModel(modelName == null ? null : modelName.getTheString());
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

	public BMTLSetInterface BMTL_newSet() {
		return new BMTLSet(new Value [0]);
	}

	public BMTLBagInterface BMTL_newBag() {
		return new BMTLBag(new Value [0]);
	}

	public BMTLSequenceInterface BMTL_newSequence() {
		return new BMTLSequence(new Value [0]);
	}

	public BMTLOrderedSetInterface BMTL_newOrderedSet() {
		return new BMTLOrderedSet(new Value [0]);
	}

	public Metamodel BMTL_getMdrImplementedMetamodel() {
		return super.getMdrImplementedMetamodel();
	}
	
	public static String [] rootPackage (CollectionValue packageQualifiedName) {
		Value [] rootPackageValues = packageQualifiedName.getTheCollection();
		String [] rootPackage = new String [rootPackageValues.length];
		for (int i = 0; i < rootPackage.length; ++i)
			rootPackage[i] = ((StringValue)rootPackageValues[i]).getTheString();
		return rootPackage;
	}

	public Metamodel BMTL_getMdrLoadedMetamodel(
		StringValue metamodelPackageName,
		CollectionValue metaPackageToInstanciate) {
		return super.getMdrLoadedMetamodel(
			metamodelPackageName.getTheString(),
			rootPackage(metaPackageToInstanciate));
	}

	public Metamodel BMTL_getMdrMOFMetaModel() {
		return super.getMdrMOFMetaModel();
	}

	public Metamodel BMTL_getMdrXMIMetaModel(
		StringValue file,
		CollectionValue metaPackageToInstanciate) {
		return super.getMdrXMIMetaModel(file.getTheString(), rootPackage(metaPackageToInstanciate));
	}

	public Model BMTL_getMdrXMIModel(StringValue loadingFile, StringValue storingFile) {
		return super.getMdrXMIModel(loadingFile.getTheString(), storingFile.getTheString());
	}
}

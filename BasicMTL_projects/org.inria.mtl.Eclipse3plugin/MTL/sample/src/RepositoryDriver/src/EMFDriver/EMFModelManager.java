/* $Id: EMFModelManager.java,v 1.1 2004-07-30 14:11:44 sdzale Exp $
 * Created on 25 août 2003
 */
package EMFDriver;

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
import org.irisa.triskell.MT.utils.Java.AWK;

import org.inria.EMFDriver.EMFDriver;
import org.inria.EMFDriver.EMFAPI;

/**
 * @author ffondeme
 *
 *	Implementation of EMFModelManager BMTL object 
 */
public class EMFModelManager implements BMTLObjectInterface, Value {
	protected static final String n = "EMFModelManager";
	protected static final String [] qn = new String [] {"EMFModelManager", n};
	
	private EMFDriver driver;
	public static final Type TheType = new InstanciableType () {
		
     public String getName() {
     	return n;
     }

     public String[] getQualifiedName() {
     	return qn;
     }

     public boolean isKindOf(
        org.irisa.triskell.MT.DataTypes.Java.Value v) {
        return v instanceof EMFModelManager;
     }

     public boolean isTypeOf(
        org.irisa.triskell.MT.DataTypes.Java.Value v) {
        return v instanceof EMFModelManager;
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
		return new EMFModelManager();
	 }

	 public Value instanciateFromJavaObject (Object javaObject)
	 {
		 // currently, no java object allows to create a EMFModelManager 
		 // DVK : currently we have no use for that 
		 return null;
	 }	
	 public boolean isInstanciableFromJavaObject (Object javaObject)
	 {
		 return false;
	 }
     	
	};

	public EMFModelManager() {
		this.driver = new EMFDriver();
	}
	
	

	/**
	  * return a model (ie. equivalent to a library)
	  * gives access to a model previously loaded or created
	  * It has no effect on the content of this model nor its storage file.
	  */
	public EMFAPI BMTL_getModel(
		StringValue modelName) {
		return EMFDriver.getModel(modelName == null ? null : modelName.getTheString());
	}

	/* createModel(
		StringValue modelName, : the name under which the model is registered in the modelsTable of the EMFDriver
		StringValue modelFileExtension, : precise the editingDomain needed to load the XMI file (for instance 'ecore' => EditingDomain for ecore models
		StringValue modelXmiInputFileName) : the filename containing model data.
	 */
	public EMFAPI BMTL_createModel(
		StringValue modelName,
		StringValue modelFileExtension,
		StringValue modelXmiInputFileName)
		throws Exception {
		return EMFDriver.createModel(
			modelName == null ? null : modelName.getTheString(),
			modelFileExtension == null ? null : modelFileExtension.getTheString(),
			modelXmiInputFileName == null ? null : modelXmiInputFileName.getTheString());
	}

	/* loadModelFromXMI(
		StringValue modelName, : the name under which the model is registered in the modelsTable of the EMFDriver
		StringValue modelFileExtension, : precise the editingDomain needed to load the XMI file (for instance 'ecore' => EditingDomain for ecore models
		StringValue modelXmiInputFileName) : the filename containing model data.
	 */
	public EMFAPI BMTL_loadModelFromXMI(
		StringValue modelName,
		StringValue modelFileExtension,
		StringValue modelXmiInputFileName)
		throws Exception {
		return EMFDriver.loadModelFromXMI(
			modelName == null ? null : modelName.getTheString(),
			modelFileExtension == null ? null : modelFileExtension.getTheString(),
			modelXmiInputFileName == null ? null : modelXmiInputFileName.getTheString());
	}

	/* saveModelToXMI(
		StringValue modelName, : the name under which the model is registered in the modelsTable of the EMFDriver
		the previous filename used by loadFrom or saveAs is used to store the model, its content is overwritten with model data.
	*/
	public void BMTL_saveModelToXMI(
		StringValue modelName)
		throws Exception {
		EMFDriver.saveModelToXMI(
			modelName == null ? null : modelName.getTheString());
	}

	/* saveModelAsXMI(
		StringValue modelName, : the name under which the model is registered in the modelsTable of the EMFDriver
		StringValue modelXmiOutputFileName) : the filename to store the model, create or overwritte the file to store data.
	*/
	public void BMTL_saveModelAsXMI(
		StringValue modelName,
		StringValue modelXmiOutputFileName)
		throws Exception {
		EMFDriver.saveModelAsXMI(
			modelName == null ? null : modelName.getTheString(),
		modelXmiOutputFileName == null ? null : modelXmiOutputFileName.getTheString());
	}

	/* dispose() : Standard::Set
	   try to cleanup the models manager in removing all "safe" managed models
	   this function returns a set of modified and unsaved models.
	   The application has to remove or save these unsaved models
	   till a call to dipose() returns an empty set.
	   Otherwise unsaved models are maintained in the model manager. 
	*/
	public org.irisa.triskell.MT.DataTypes.Java.CollectionValue BMTL_dispose()
		throws Exception {
		return EMFDriver.dispose();
	}
	
	/* disposeModel(
		StringValue modelName, : the name under which the model is registered in the modelsTable of the EMFDriver
		removes the model from the managed models. If the model was not saved but modiied, it saves it before release. 
	*/
	public void BMTL_disposeModel(StringValue modelName)
		throws Exception {
	EMFDriver.disposeModel(modelName == null ? null : modelName.getTheString());	
	}
	
	/* saveAllModelsToXMI()
		Save all models currently under management of the model manager.
	*/
	public void BMTL_saveAllModelsToXMI()
		throws Exception {
			EMFDriver.saveAllModelsToXMI();
	}	

	/* displayModelInformation(
		StringValue modelName, : the name under which the model is registered in the modelsTable of the EMFDriver
		removes the model from the managed models. If the model was not saved but modiied, it saves it before release. 
	*/
	public void BMTL_displayModelInformation(StringValue modelName) {
	EMFDriver.displayModelInformation(modelName == null ? null : modelName.getTheString());	
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

	public static String [] rootPackage (CollectionValue packageQualifiedName) {
		Value [] rootPackageValues = packageQualifiedName.getTheCollection();
		String [] rootPackage = new String [rootPackageValues.length];
		for (int i = 0; i < rootPackage.length; ++i)
			rootPackage[i] = ((StringValue)rootPackageValues[i]).getTheString();
		return rootPackage;
	}

}

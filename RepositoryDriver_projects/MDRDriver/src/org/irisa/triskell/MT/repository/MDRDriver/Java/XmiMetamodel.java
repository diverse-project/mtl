package org.irisa.triskell.MT.repository.MDRDriver.Java;

import java.io.File;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import javax.jmi.model.*;
import org.netbeans.api.mdr.CreationFailedException;
import org.irisa.triskell.MT.utils.Java.AWK;
import org.irisa.triskell.MT.utils.Java.Mangler;

public class XmiMetamodel 
    extends org.irisa.triskell.MT.repository.MDRDriver.Java.Metamodel
{
    public final String [] xmiFiles;
    public String [] getXmiFiles () {
        return this.xmiFiles;
    }

    public final String rootPackage;
    public String getRootPackage () {
        return this.rootPackage;
    }


    public XmiMetamodel()
    {
		this(new String [0], null);
    }


    public XmiMetamodel(
        String xmiFile)
    {
		this(xmiFile, null);
    }


    public XmiMetamodel(
        String xmiFile, String rootPackage)
    {
		this(new String [] {xmiFile}, rootPackage);
    }

	/**
	 * 
	 * @param xmiFiles more than one XMI makes a (naive) addition of them; be sure there is no name conflict in the metamodel definition !
	 * @param rootPackages null indicates it should be retreived; null is invalid for multiple XMI in that there will have several roots possibles
	 */
    public XmiMetamodel(
        String [] xmiFiles,
        String rootPackage)
    {
		if (xmiFiles.length > 1 && rootPackage == null)
			throw new IllegalArgumentException();
		this.xmiFiles = xmiFiles;
		this.rootPackage = rootPackage;
    }

    public javax.jmi.reflect.RefPackage getRefPackage(
        org.irisa.triskell.MT.repository.MDRDriver.Java.MDRAPI api)
        throws java.lang.Exception
    {
			String metamodelRepositoryName = this.createMetamodelName(api);
			api.getLog().debug("Creating repository " + metamodelRepositoryName + " for metamodel.");
			ModelPackage metametaPackage = (ModelPackage)api.getMdrRepository().createExtent(metamodelRepositoryName);
			String [] files = this.getXmiFiles();
			java.util.Collection readen = new java.util.ArrayList();
			for (int i = 0; i < files.length; ++i) {
				String uri = new File(files[i]).toURI().toString();
				api.getLog().debug("Reading metamodel into repository " + metamodelRepositoryName + " from XMI file " + uri);
				readen.addAll(MDRAPI.getReader().read(uri, metametaPackage));
				api.getLog().debug("Retreiving meta package to be instanciated.");
			}
				
			MofPackage metaPackageToInstanciate = null;
			Object o;
			Iterator it = readen.iterator();
			while (it.hasNext() && (metaPackageToInstanciate == null || this.getRootPackage() == null)) {
				o = it.next();
				if (	(o instanceof MofPackage)
					&&	((this.getRootPackage() == null) || (((MofPackage)o).getName().equalsIgnoreCase(this.getRootPackage())))
					&&	(((MofPackage)o).getContainer() == null)
					) {
					if (metaPackageToInstanciate != null)
						throw new CreationFailedException("Multiple meta package can be instanciated; please, select one explicitly.");
					metaPackageToInstanciate = (MofPackage)o;
				}
			}
				
			if (metaPackageToInstanciate == null)
				throw new CreationFailedException("Cannot find meta package to instanciate; check name; note this package must be a root package");
			
			api.getLog().debug("Instanciating metamodel from repository " + metamodelRepositoryName + " to repository " + api.getModelName());
			return api.getMdrRepository().createExtent(api.getModelName(), metaPackageToInstanciate);
    }

    protected String createMetamodelName(
        org.irisa.triskell.MT.repository.MDRDriver.Java.MDRAPI api)
    {
			StringBuffer fullMetamodelName = new StringBuffer();
			String metamodelName;
			for (int i = 0; i < this.getXmiFiles().length; ++i) {
				if (i > 0)
					fullMetamodelName.append('_');
					
				metamodelName = AWK.lastFieldOf(this.getXmiFiles()[i], "/");
				metamodelName = AWK.lastFieldOf(metamodelName, "\\");
				if (metamodelName.indexOf('.') != -1)
					metamodelName = AWK.eliminateLastFieldOf(metamodelName, ".");
				metamodelName = Mangler.mangle("MM_", metamodelName);
				fullMetamodelName.append(metamodelName);
			}
			final String basicMetamodelName = fullMetamodelName.toString();
			metamodelName = basicMetamodelName; 
			HashSet alreadyDefinedNames = new HashSet(Arrays.asList(api.getMdrRepository().getExtentNames()));
			for (int i = 2; alreadyDefinedNames.contains(metamodelName); ++i)
				metamodelName = basicMetamodelName + Integer.toString(i);
			return metamodelName;
    }
}

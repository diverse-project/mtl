/*
 * $Id: StandaloneMdrManager.java,v 1.2 2004-02-16 15:44:17 dvojtise Exp $
 * Authors : ffondeme
 * 
 * Copyright 2004 - INRIA - LGPL license
 */
package org.irisa.triskell.MT.repository.MDRDriver.Java;
import org.netbeans.api.mdr.MDRManager;
import org.netbeans.api.mdr.MDRepository;
import org.netbeans.mdr.NBMDRepositoryImpl;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.io.File;


/**
 * @author ffondeme
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class StandaloneMdrManager extends MDRManager {
	public synchronized MDRepository getRepository(String s)
	{
		MDRepository mdrepository = (MDRepository)repositories.get(s);
		if(mdrepository != null)
		{
			return mdrepository;
		} else
		{
			MDRepository mdrepository1 = createRepository(s);
			repositories.put(s, mdrepository1);
			return mdrepository1;
		}
	}

	protected MDRepository createRepository(String s)
	{
		HashMap hashmap = new HashMap();
		hashmap.put("storage", a);
		hashmap.put("org.netbeans.mdr.persistence.btreeimpl.filename", a);
		return new NBMDRepositoryImpl();
	}

	public MDRepository getDefaultRepository()
	{
		return getRepository("MDR");
	}

	public synchronized String[] getRepositoryNames()
	{
		String as[] = new String[repositories.size()];
		Iterator iterator = repositories.keySet().iterator();
		iterator.hasNext();
		for(int i = 0; i < repositories.size(); i++)
			as[i] = (String)iterator.next();

		return as;
	}

	public StandaloneMdrManager()
	{
		repositories = new HashMap();
	}

	private static final String _flddo = "org.netbeans.mdr.storagemodel.StorageFactoryClassName";
	private static final String _fldbyte = "org.netbeans.lib.jmi.Logger.fileName";
	private static final String _fldfor = "org.netbeans.mdr.Logger.fileName";
	private static final String _fldnew = "org.netbeans.lib.jmi.Logger";
	private static final String _fldtry = "org.netbeans.mdr.Logger";
	private static final String _fldint = "org.netbeans.mdr.persistence.btreeimpl.btreestorage.BtreeFactory";
	private static final String _fldif = "org.netbeans.mdr.persistence.memoryimpl.StorageFactoryImpl";
	private static String a;
	public final String REPOSITORY_DEFAULT = "MDR";
	public Map repositories;

	public static String getDataPath () {
		File home = new File(org.irisa.triskell.MT.utils.Java.Directories.getUserPath(), ".MDR");
		if (! home.exists())
			home.mkdirs();
		return home.getAbsolutePath();
	}
	
	public static String getLogPath () {
		return getDataPath() + File.separatorChar + "mdr.log";
	}

	static 
	{
		a = null;
		if(System.getProperty("org.openide.version") != null);
		System.setProperty("org.netbeans.mdr.storagemodel.StorageFactoryClassName", "org.netbeans.mdr.persistence.memoryimpl.StorageFactoryImpl");
		a = "org.netbeans.mdr.persistence.memoryimpl.StorageFactoryImpl";
//		Configuration.setString(ConfigurationKeys.KEY_REPOSITORY_STORAGE, a);
		System.setProperty("org.netbeans.lib.jmi.Logger.fileName", getLogPath());
		System.setProperty("org.netbeans.mdr.Logger.fileName", getLogPath());
		System.setProperty("org.netbeans.lib.jmi.Logger", "16");
		System.setProperty("org.netbeans.mdr.Logger", "16");
	}

}

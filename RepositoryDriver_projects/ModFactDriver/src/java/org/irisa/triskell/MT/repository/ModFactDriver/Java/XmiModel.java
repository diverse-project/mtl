/*
 * $Id: XmiModel.java,v 1.1 2004-10-25 13:57:13 dvojtise Exp $
 * Authors : ffondeme xblanc dvojtise
 * 
 * Copyright 2004 - INRIA - LGPL license
 */
package org.irisa.triskell.MT.repository.ModFactDriver.Java;

import java.io.File;
import org.irisa.triskell.MT.repository.genericJMIDriver.JMIAPI;

public class XmiModel 
    extends org.irisa.triskell.MT.repository.genericJMIDriver.Model
{
	public static final byte Read = 0;
	public static final byte Write = 1;
	public static final byte ReadWrite = 2;
	
    protected final String [] xmiLoadingFiles;
    public String [] getXmiLoadingFiles () {
        return this.xmiLoadingFiles;
    }
	
    protected final String xmiStoringFile;
    public String getXmiStoringFile () {
        return this.xmiStoringFile;
    }


    public XmiModel(
        String xmiFile, byte mode)
    {
		this(mode == Read || mode == ReadWrite ? new String [] {xmiFile} : new String [0],
				mode == Write || mode == ReadWrite ? xmiFile : null);
    }


    public XmiModel(
        String xmiLoadingFile,
        String xmiStoringFile)
    {
		this(xmiLoadingFile == null ? null : new String [] {xmiLoadingFile}, xmiStoringFile);
    }


    public XmiModel(
        String [] xmiLoadingFiles,
        String xmiStoringFile)
    {
		this.xmiLoadingFiles = xmiLoadingFiles;
		this.xmiStoringFile = xmiStoringFile;
    }

    public void load(
		org.irisa.triskell.MT.repository.genericJMIDriver.JMIAPI api)
        throws java.lang.Exception
    {
    	String uri;
    	String [] load = this.getXmiLoadingFiles();
    	if (load != null)
	    	for (int i = 0; i < load.length; ++i) {
				uri = new File(load[i]).toURI().toString();
				api.getLog().info("Loading model from XMI file " + uri + '.');
				JMIAPI.getReader().read(uri, api.getModel());
	    	}
    }

    public void store(
        org.irisa.triskell.MT.repository.genericJMIDriver.JMIAPI api)
        throws java.lang.Exception
    {
    	String store = this.getXmiStoringFile();
    	if (store != null) {
			java.io.OutputStream out = new java.io.BufferedOutputStream(new java.io.FileOutputStream(new File(store)));
			api.getLog().info("Storing model to XMI file (DVK test 1.1) " + store + '.');
			// MDRAPI.getWriter().write(out, api.getModel(), "1.2");
			JMIAPI.getWriter().write(out, api.getModel(), "1.1");
			out.flush();
			out.close();
    	}
    }
}

package org.irisa.triskell.MT.repository.MDRDriver.Java;

import java.io.File;

public class XmiModel 
    extends org.irisa.triskell.MT.repository.MDRDriver.Java.Model
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
        org.irisa.triskell.MT.repository.MDRDriver.Java.MDRAPI api)
        throws java.lang.Exception
    {
    	String uri;
    	String [] load = this.getXmiLoadingFiles();
    	if (load != null)
	    	for (int i = 0; i < load.length; ++i) {
				uri = new File(load[i]).toURI().toString();
				api.getLog().info("Loading model from XMI file " + uri + '.');
				MDRAPI.getReader().read(uri, api.getModel());
	    	}
    }

    public void store(
        org.irisa.triskell.MT.repository.MDRDriver.Java.MDRAPI api)
        throws java.lang.Exception
    {
    	String store = this.getXmiStoringFile();
    	if (store != null) {
			java.io.OutputStream out = new java.io.BufferedOutputStream(new java.io.FileOutputStream(new File(store)));
			api.getLog().info("Storing model to XMI file " + store + '.');
			MDRAPI.getWriter().write(out, api.getModel(), "1.2");
			out.flush();
			out.close();
    	}
    }
}

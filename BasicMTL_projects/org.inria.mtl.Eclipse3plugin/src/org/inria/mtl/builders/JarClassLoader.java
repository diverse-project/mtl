/*
 * $Id: JarClassLoader.java,v 1.1 2004-08-31 13:46:06 sdzale Exp $
 * Created on 16 août 2004
 * Copyright (c) 2002 Object Factory Inc. All rights reserved.
 * Permission to copy granted under the MIT license
 * http://www.opensource.org/licenses/mit-license.php
 * adapted from http://eclipsewiki.swiki.net/123
 */

package org.inria.mtl.builders;

import java.net.URL;

import java.net.URLClassLoader;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.Properties;

import org.eclipse.osgi.util.ManifestElement;
import org.osgi.framework.Constants;

import org.inria.mtl.MTLPlugin;

import antlr.collections.impl.Vector;
/**
 * Used to load Compiler classes independent of any plugins.
 * @author dvojtise, Bob Foster
 */
public class JarClassLoader extends URLClassLoader {
	
	HashMap fMap = new HashMap();
	public JarClassLoader(URL[] urls, ClassLoader parent) {
		super(urls, parent);
	}
	
	public JarClassLoader(URL[] urls) {
		super(urls);
	}
	
	protected Class findClass(String name) throws ClassNotFoundException {
		Class c = super.findClass(name);
		return c;
	}
	
	protected Class loadClass(String name, boolean resolve) throws ClassNotFoundException {
		
		// All non-MDR classes handled normally
		System.out.println("  org.inria.mtl.builders.JarClassLoader trying to load: "+name);
		if (!(name.startsWith("BasicMtlCompiler"))) {//org.apache.crimson.jaxp
			System.out.println("  org.inria.mtl.builders.JarClassLoader    looking in super()");
			return super.loadClass(name, resolve);
		}
		//System.out.println(" Class0 :"+name+"  ");
		Class c = (Class) fMap.get(name);
		//System.out.println(" Class1 :"+c.getName()+"  ");
		
		if (c == null) {
			c = findClass(name);
		
			if (c != null)
				fMap.put(name, c);
			else {
				c = super.loadClass(name);
			}
		}
		
		if (c != null && resolve)
			resolveClass(c);
		
		//System.out.println(" Class2 :"+c.getName()+"  ");
		return c;
	}
	public static void  showRuntimeLibraries()
	{
		String ret;
		ManifestElement[] elements=new ManifestElement[]{};
		//org.eclipse.core.runtime.IPluginDescriptor pd = MTLPlugin.getDefault().getDescriptor();
		java.net.URL url = MTLPlugin.getBaseURL();
		//MTLPlugin.getDefault().
		String urlString = url.toString();
		//org.eclipse.core.runtime.ILibrary[] libraries = pd.getRuntimeLibraries();
		String requires = (String)MTLPlugin.getDefault().getBundle().getHeaders().get(Constants.BUNDLE_CLASSPATH);
		
	//	String requires1 = (String)MTLPlugin.getDefault().getBundle().getHeaders().get(Constants..BUNDLE_CLASSPATH);
		
	    try{
		 elements = ManifestElement.parseHeader(Constants.BUNDLE_CLASSPATH, requires);
	    }catch(Exception e){
	    	System.out.println(e.getMessage());
	    }
		for (int i = 0; i < elements.length; i++) {
			//org.eclipse.core.runtime.ILibrary iLibrary = libraries[i];
			//org.eclipse.core.runtime.IPath libPath = iLibrary.getPath();
			
			//String libPathStr = libPath.toString();
			//String libUrlStr = urlString + libPathStr;
			System.out.println(" Runtime elements :" + elements[i].getValue() );
		}
		
		String classpath = System.getProperty( "java.class.path ", ". ");
		System.out.println("Classpath :"+classpath);
		
	}
	
	public static Enumeration getSystemElements(){
        try{
        	Properties proprietes = System.getProperties();
            return proprietes.propertyNames();
        }
        catch(Exception e){
            return null;
        }
    }
	
	public static Object getElements(Enumeration enum){
        try{
            return enum.nextElement();
        }
        catch(NoSuchElementException e){
            return null;
        }
    }
	  public static void systemProperties() {
	  		Properties proprietes = System.getProperties();
	        Enumeration noms = getSystemElements();
	        Vector tabObj = new Vector();
	        Object nom;
	        while((nom = getElements(noms)) != null){
	            tabObj.appendElement(nom);
	        }
	        for(int i = 0; i < tabObj.size(); i++){
	            System.out.println (tabObj.elementAt(i)
	            + "\t\t\t:\t" + proprietes.getProperty((String)tabObj.elementAt(i)));
	        }
	    }
	    
}


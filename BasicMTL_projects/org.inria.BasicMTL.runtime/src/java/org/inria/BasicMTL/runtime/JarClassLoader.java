/*
 * $Id: JarClassLoader.java,v 1.1 2004-10-25 14:46:04 jpthibau Exp $
 * Created on 16 août 2004
 * Copyright (c) 2002 Object Factory Inc. All rights reserved.
 * Permission to copy granted under the MIT license
 * http://www.opensource.org/licenses/mit-license.php
 * adapted from http://eclipsewiki.swiki.net/123
 */

package org.inria.BasicMTL.runtime;

import java.net.URLClassLoader;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashMap;
/**
 * Used to load MDR classes independent of any use of MDR by other plugins.
 * @author dvojtise, Bob Foster
 */
public class JarClassLoader extends URLClassLoader {
	public JarClassLoader(URL[] urls, ClassLoader parent) {
		super(urls, parent);
	}
	
	protected Class findClass(String name) throws ClassNotFoundException {
		Class c = super.findClass(name);
		return c;
	}
	
	protected Class loadClass(String name, boolean resolve) throws ClassNotFoundException {
		
		// All non-MDR classes handled normally
		System.out.println("  org.inria.BasicMTL.runtime.JarClassLoader trying to load: "+name);
		if (!name.startsWith("org.netbeans.mdr")
				//&& !name.startsWith("org.apache.crimson.jaxp")
				&& !name.startsWith("org.apache.log4j")
			/*	&& !name.startsWith("org.xml.sax.")
				&& !name.startsWith("javax.xml.")*/) {//org.apache.crimson.jaxp
			System.out.println("  org.inria.BasicMTL.runtime.JarClassLoader    looking in super()");
			return super.loadClass(name, resolve);
		}
		
		// Other versions of MDR classes may already be
		// loaded. Make sure we don't get any of these by
		// trying to load locally first.
		
		Class c = (Class) fMap.get(name);
		
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
			
		return c;
	}
	HashMap fMap = new HashMap();
}

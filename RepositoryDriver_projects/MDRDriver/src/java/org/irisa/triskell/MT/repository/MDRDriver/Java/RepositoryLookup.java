/*
 * $Id: RepositoryLookup.java,v 1.1 2004-10-25 12:32:23 dvojtise Exp $
 * Authors : ffondeme
 * 
 * Copyright 2004 - INRIA - LGPL license
 */
package org.irisa.triskell.MT.repository.MDRDriver.Java;

import org.openide.util.Lookup;
// import org.openide.util.LookupListener;
import org.netbeans.lib.jmi.xmi.XMISaxReaderImpl;
import org.netbeans.lib.jmi.xmi.XMIWriterImpl;
import org.netbeans.lib.jmi.mapping.JMIMapperImpl;
import java.util.ArrayList;
import java.util.Iterator;

/**
  * extends Lookup
  */
public class RepositoryLookup 
    extends org.openide.util.Lookup
{
    private java.util.ArrayList instances = new ArrayList();
    protected java.util.ArrayList getInstances () {
        return this.instances;
    }
    protected int cardInstances () {
        if ( this.instances == null ) return 0;
        else return 1;
    }

    private static class Result 
        extends Lookup.Result
    {
        private final java.util.ArrayList result = new ArrayList();
        protected java.util.ArrayList getResult () {
            return this.result;
        }
        protected int cardResult () {
            if ( this.result == null ) return 0;
            else return 1;
        }


        public Result(
            java.lang.Object o)
        {
			if (o != null) result.add(o);
        }

        /**
          * Registers a listener that is invoked when there is a possible
          * 		  change in this result.
          * 
          * 
          * @param l the listener to add
          */
        public void addLookupListener(
            org.openide.util.LookupListener l)
        {
			// this lookup never changes so nothing need to be registered
        }

        /**
          * Get all instances in the result.
          * 
          * 
          * 
          * @return collection of all instances
          */
        public java.util.Collection allInstances()
        {
			return result;
        }

        /**
          * Unregisters a listener previously added.
          * 
          * 
          * @param l the listener to remove
          */
        public void removeLookupListener(
            org.openide.util.LookupListener l)
        {
			// nothing was registered
        }
    }


    /**
      * Creates new RepositoryLookup
      */
    public RepositoryLookup()
    {
	 instances.add(new StandaloneMdrManager());
	 instances.add(new XMISaxReaderImpl());
	 instances.add(new JMIMapperImpl());
	 instances.add(new XMIWriterImpl());
	 instances.add(new MDRLogger(""));
    }

    public java.lang.Object lookup(
        java.lang.Class cls)
    {
		for (Iterator it = instances.iterator(); it.hasNext();) {
			Object instance = it.next();
			if (cls.isAssignableFrom(instance.getClass())) {
				return instance;
			}
		}
		return null;
    }

    public Lookup.Result lookup(
        Lookup.Template template)
    {
		return new Result(lookup(template.getType()));
    }
}

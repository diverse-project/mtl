/*
 * Created on Jun 3, 2003
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package org.irisa.triskell.MT.repository.MDRDriver.Java;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.WeakHashMap;

import javax.jmi.model.GeneralizableElement;

import org.irisa.triskell.MT.DataTypes.Java.commands.Type;
import org.irisa.triskell.MT.DataTypes.Java.commands.modelElement.ModelElementType;
import org.irisa.triskell.MT.repository.API.Java.MetaClass;
import org.irisa.triskell.MT.repository.API.Java.UnknownElementException;

/**
 * @author ffondeme
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class MDRModelElementType extends ModelElementType {
	private static WeakHashMap instances;
	
	public static MDRModelElementType getMDRModelElementType (MetaClass clazz) {
		MDRModelElementType ret = (MDRModelElementType)instances.get(clazz);
		if (ret == null) {
			ret = new MDRModelElementType(clazz);
			instances.put(clazz, ret);
		}
		return ret;
			
	}
	
	//Null is none, empty if not computed.
	private List generalizations;
	
	protected MDRModelElementType (MetaClass clazz) {
		super(clazz);
		if (! (clazz instanceof MDRElement))
			throw new IllegalArgumentException();
		if (((MDRElement)clazz).getRef() instanceof GeneralizableElement)
			this.generalizations = new ArrayList();
		else
			this.generalizations = null;
	}
	
	/**
	 * 
	 * @return List null if none
	 */
	protected List getGeneralizations () {
		if (this.generalizations == null)
			return null;
		else if (this.generalizations.isEmpty()) {
			Iterator gsIt = ((GeneralizableElement)((MDRElement)this.getClazz()).getRef()).getSupertypes().iterator();
			MDRAPI api = ((MDRElement)this.getClazz()).getSpecificAPI();
			List qn;
			GeneralizableElement p;
			if (gsIt.hasNext()) {
				try {
					do {
						p = (GeneralizableElement)gsIt.next();
						qn = p.getQualifiedName();
						this.generalizations.add(new MDRModelElementType(api.getMetaClass((String [])qn.toArray(new String [qn.size()]))));
					} while (gsIt.hasNext());
				} catch (UnknownElementException x) {
					throw new RuntimeException("Internal error.", x);
				}
			} else
				this.generalizations = null;
		}
		return this.generalizations;
	}

	public Type[] getParents() {
		List gen = this.getGeneralizations();
		if (gen == null)
			return super.getParents();
		else
			return (Type[])gen.toArray(new Type [gen.size()]);
	}

}

/*
 * Created on 18 juin 2003
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package org.irisa.triskell.MT.BasicMTL.TopTypes;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

import org.irisa.triskell.MT.DataTypes.Java.Value;
import org.irisa.triskell.MT.DataTypes.Java.commands.CommandGroup;
import org.irisa.triskell.MT.DataTypes.Java.commands.CommandGroupImpl;
import org.irisa.triskell.MT.DataTypes.Java.commands.Type;
import org.irisa.triskell.MT.DataTypes.Java.commands.OclAny.OclAnyCommandGroup;
import org.irisa.triskell.MT.DataTypes.Java.commands.OclAny.OclAnyType;
import org.irisa.triskell.MT.utils.Java.AWK;

public class BMTLType extends CommandGroupImpl implements Type {
	
	protected final Class clazz;
	protected final String [] qualifiedName;
	
	/**
	 * 
	 * @param qualifiedName
	 * @param clazz Must be the interface representent
	 * @param parents
	 */
	public BMTLType(BMTLLibrary library, String [] qualifiedName, Class clazz, Collection parents) {
		super(null, parents == null || parents.size() == 0 ? Arrays.asList(new CommandGroup [] {OclAnyCommandGroup.TheInstance}): parents);
		this.qualifiedName = qualifiedName;
		this.clazz = clazz;
	}

	/* (non-Javadoc)
	 * @see org.irisa.triskell.MT.DataTypes.Java.commands.Type#allInstances()
	 */
	public Collection allInstances() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean conformsTo(Type t) throws UnsupportedOperationException {
		if (OclAnyType.TheInstance.conformsTo(t))
			return true;
		if (! (t instanceof BMTLType))
			return false;
		Iterator ps = this.getParents().iterator();
		Type p; 
		while (ps.hasNext()) {
			if (((CommandGroup)ps.next()).getOwner().conformsTo(t))
				return true;
		}
		return false;
	}

	public String getName() {
		String [] qn = this.getQualifiedName();
		return qn[qn.length - 1];
	}

	public String[] getQualifiedName() {
		return this.qualifiedName;
	}

	public String getQualifiedNameAsString() {
		return AWK.merge(this.getQualifiedName(), Type.PackageIndirection);
	}

	public boolean isOfType(Value v) {
		return this.clazz.isInstance(v);
	}

	public Type getOwner() {
		return this;
	}

	public boolean equals(Object obj) {
		return this == obj || ((obj instanceof BMTLType) && ((BMTLType)obj).clazz.equals(this.clazz));
	}

}

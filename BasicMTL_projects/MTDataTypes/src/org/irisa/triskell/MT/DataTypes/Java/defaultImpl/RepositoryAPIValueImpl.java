/*
 * Created on 25 oct. 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.irisa.triskell.MT.DataTypes.Java.defaultImpl;

import org.irisa.triskell.MT.DataTypes.Java.RepositoryAPIValue;
import org.irisa.triskell.MT.DataTypes.Java.Type;
import org.irisa.triskell.MT.DataTypes.Java.Value;
import org.irisa.triskell.MT.DataTypes.Java.ValueVisitor;
import org.irisa.triskell.MT.DataTypes.Java.commands.MultipleCommandException;
import org.irisa.triskell.MT.DataTypes.Java.commands.UnknownCommandException;

/**
 * @author jpthibau
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class RepositoryAPIValueImpl implements RepositoryAPIValue {

	/* (non-Javadoc)
	 * @see org.irisa.triskell.MT.DataTypes.Java.RepositoryAPIValue#getTheRepositoryAPI()
	 */
	public String getTheRepositoryAPI() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.irisa.triskell.MT.DataTypes.Java.Value#isUndefined()
	 */
	public boolean isUndefined() {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see org.irisa.triskell.MT.DataTypes.Java.Value#getErrorMessage()
	 */
	public String getErrorMessage() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.irisa.triskell.MT.DataTypes.Java.Value#equals(org.irisa.triskell.MT.DataTypes.Java.Value)
	 */
	public boolean equals(Value rhs) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see org.irisa.triskell.MT.DataTypes.Java.Value#invoke(java.lang.String[], java.lang.String, org.irisa.triskell.MT.DataTypes.Java.Value[], java.lang.String[])
	 */
	public Value invoke(String[] scopeQualifiedName, String name,
			Value[] arguments, String[] discriminants)
			throws UnknownCommandException, MultipleCommandException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.irisa.triskell.MT.DataTypes.Java.Value#accept(org.irisa.triskell.MT.DataTypes.Java.ValueVisitor)
	 */
	public void accept(ValueVisitor visitor) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.irisa.triskell.MT.DataTypes.Java.Value#getType()
	 */
	public Type getType() {
		// TODO Auto-generated method stub
		return null;
	}

}

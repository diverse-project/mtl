/*
 * Created on 29 oct. 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package TypeChecker;

/**
 * @author edrezen
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class CompilerObservable extends java.util.Observable 
{
	/** */
	public void notifyObservers () 
	{
		notifyObservers (null);
	}

	/** */
	public void notifyObservers (Object arg) 
	{
		this.setChanged();
		super.notifyObservers(arg);
		this.clearChanged();
	}

}

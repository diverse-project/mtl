/*
 * Created on 2 nov. 2004
 *
 * This interface is the parent of all MTL compiler messages
 */
package CompilerEvents;

/**
 * @author edrezen
 *
 */
public interface CompilerMessage 
{
	static final public int ERROR   = 0;
	static final public int WARNING = 1;
	static final public int INFO    = 2;
	
	/** */
	public String getDescription ();
	
	/** */
	public int getSeverity (); 
	
	/** */
	public CompilerMessageLocator getLocator ();
}

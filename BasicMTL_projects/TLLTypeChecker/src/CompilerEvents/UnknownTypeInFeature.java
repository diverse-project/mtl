/*
 * Created on 29 oct. 2004
 *
 */
package CompilerEvents;

import org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.Feature;


/**
 * @author edrezen
 *
 */
public class UnknownTypeInFeature extends UnknownType 
{
	////////////////////////////////////////////////////////////////////////////////
	// ATTRIBUTES
	////////////////////////////////////////////////////////////////////////////////
	private Feature feature;
	public Feature getFeature() { return feature; }
	private void setFeature(Feature feature) { this.feature = feature; }
	
	
	////////////////////////////////////////////////////////////////////////////////
	// CONSTRUCTORS
	////////////////////////////////////////////////////////////////////////////////

	/**  */
	public UnknownTypeInFeature (Feature feature) 
	{
		super (feature.getFeatureType());
		setFeature (feature);
	}


	////////////////////////////////////////////////////////////////////////////////
	// METHODS
	////////////////////////////////////////////////////////////////////////////////

	/** */
	public String getDescription() 
	{
		return "The type '" + getType() + "' for the feature '" + getFeature().getName() + "' is unknown";
	}
	
	/** */
	public int getSeverity() 
	{
		return CompilerMessage.ERROR;
	}

	
	/** */
	public CompilerMessageLocator getLocator() 
	{
		String fileName   = (String) getFeature().getProperty ("FileName").getValue();
		String lineNumber = (String) getFeature().getProperty ("LineNumber").getValue();

		return new FileCompilerMessageLocator (fileName, Integer.parseInt(lineNumber));
	}

}

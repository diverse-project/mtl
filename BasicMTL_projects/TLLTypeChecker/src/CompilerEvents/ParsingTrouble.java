/*
 * Created on 3 nov. 2004
 *
 */
package CompilerEvents;

/**
 * @author edrezen
 *
 */
public class ParsingTrouble implements CompilerMessage 
{
	////////////////////////////////////////////////////////////////////////////////
	// ATTRIBUTES
	////////////////////////////////////////////////////////////////////////////////

	private String message; 
	private String getMessage() { return message; }
	private void setMessage(String message) { this.message = message; }

	private String filename; 
	private String getFilename() { return filename; }
	private void setFilename(String filename) { this.filename = filename; }
	
	private Integer line; 
	private Integer getLine() { return line; }
	private void setLine(Integer line) { this.line = line; }
	
	private Integer column;
	private Integer getColumn() { return column; }
	private void setColumn(Integer column) { this.column = column; }

	
	////////////////////////////////////////////////////////////////////////////////
	// CONSTRUCTORS
	////////////////////////////////////////////////////////////////////////////////

	/** */
	public ParsingTrouble (String message, String filename, Integer line, Integer column)
	{
		setMessage  (message);
		setFilename (filename);
		setLine     (line);
		setColumn   (column);
	}
	
	
	////////////////////////////////////////////////////////////////////////////////
	// METHODS
	////////////////////////////////////////////////////////////////////////////////
	
	/** */
	public String getDescription() 
	{
		return getMessage();
	}

	/** */
	public int getSeverity() 
	{
		return CompilerMessage.ERROR;
	}

	/** */
	public CompilerMessageLocator getLocator() 
	{
		return new FileCompilerMessageLocator (getFilename(), getLine().intValue());
	}
}

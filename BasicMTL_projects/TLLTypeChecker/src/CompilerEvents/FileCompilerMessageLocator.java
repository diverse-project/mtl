/*
 * Created on 2 nov. 2004
 *
 */
package CompilerEvents;

/**
 * @author edrezen
 *
 */
public class FileCompilerMessageLocator extends CompilerMessageLocator 
{
	////////////////////////////////////////////////////////////////////////////////
	// ATTRIBUTES
	////////////////////////////////////////////////////////////////////////////////
	
	private String fileName;
	public String getFileName() { return fileName; }
	private void setFileName(String fileName) { this.fileName = fileName; }

	private int lineNumber;
	public int getLineNumber() { return lineNumber; }
	private void setLineNumber(int lineNumber) { this.lineNumber = lineNumber; }


	////////////////////////////////////////////////////////////////////////////////
	// CONSTRUCTORS
	////////////////////////////////////////////////////////////////////////////////
	public FileCompilerMessageLocator (String fileName, int lineNumber)
	{
		setFileName (fileName);
		setLineNumber (lineNumber);
	}
}

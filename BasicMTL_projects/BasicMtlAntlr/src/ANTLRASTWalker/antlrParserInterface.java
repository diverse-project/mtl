// $Id: antlrParserInterface.java,v 1.1 2003-10-17 14:48:56 jpthibau Exp $
package ANTLRASTWalker;

public interface antlrParserInterface {

	antlrParserInterface newParser();

	org.irisa.triskell.MT.BasicMTL.BasicMTLAST.Java.Library buildLibraryFromTexts(java.util.Vector filenames);
}
/*
 * Created on 2 nov. 2004
 *
 */
package CompilerEvents;

import org.irisa.triskell.MT.BasicMTL.BasicMTLTLL.Java.*;

import TypeChecker.CompilerObservable;

/**
 * @author edrezen
 *
 */
public class CompilerMessageFactory extends CompilerObservable 
{
	// Singleton
	final static private CompilerMessageFactory singleton = new CompilerMessageFactory ();
	static public CompilerMessageFactory instance () { return singleton; }
	
	protected CompilerMessageFactory () { }
	
	
	/** */
	public CompilerMessage createInheritanceLoopMessage (java.util.Vector remainingUnsolved)
	{
		return new InheritanceLoop (remainingUnsolved);
	}
	
	/** */
	public CompilerMessage createMultipleInheritanceIndeterminism (UserDefinedClass userClass, InheritedOpSignature operation, QualifiedName origin)
	{
		return new MultipleInheritanceIndeterminism (userClass, operation, origin);
	}
	
	/** */
	public CompilerMessage createUnknownExternType (QualifiedName type)
	{
		return new UnknownExternType (type);
	}

	/** */
	public CompilerMessage createUnknownLocalType (QualifiedName type)
	{
		return new UnknownLocalType (type);
	}

	/** */
	public CompilerMessage createUnknownInheritedParent (String parentName, String className)
	{
		return new UnknownInheritedParent (parentName, className);
	}
	
	/** */
	public CompilerMessage createUnknownTypeInFeature (Feature feature)
	{
		return new UnknownTypeInFeature (feature);
	}
	
	/** */
	public CompilerMessage createUnknownTypeInVarDeclaration (VarDeclaration varDeclartion)
	{
		return new UnknownTypeInVarDeclaration (varDeclartion);
	}
 
	/** */
	public CompilerMessage createParsingTrouble (String message, String filename, Integer line, Integer column) 
	{
		return new ParsingTrouble (message, filename, line, column);
	} 

}

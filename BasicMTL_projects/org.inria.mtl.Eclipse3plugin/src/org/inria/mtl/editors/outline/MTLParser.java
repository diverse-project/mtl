package org.inria.mtl.editors.outline;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.rules.IToken;
import org.inria.mtl.MTLPlugin;
import org.inria.mtl.editors.MTLCodeScanner;
import org.inria.mtl.editors.utils.log;
/**
 * @author sdzale
 *
 *Parse a MTL File to build the outline
 */
//import log;

public class MTLParser
{
//	Nom de la fonction ou classe à déterminer
	public static final String FUNCTION = "class";

	/**
	 * line separator
	 */
	public static final String LINE_SEPARATOR = System.getProperty("line.separator");

	/**
	 * Array of system types to ignore.
	 */
	private static String[] systemClassNames= {"Array","String"};
	
	protected HashMap systemClassMap = new HashMap();
	
	private Map fTokenMap= new HashMap();

	protected IDocument sourceDocument;
	protected HashMap functions = new HashMap();
	protected HashMap classes = new HashMap();
	protected HashMap globalVariables = new HashMap();
	protected HashMap librairies = new HashMap();
	protected HashMap models = new HashMap();
	protected HashMap librairiesMethod = new HashMap();
	protected List elementList = new LinkedList();
	private String classesOutline="Classes";
	private String modelsOutline="Use models";
	private String functionsOutline="Functions";
	private int offsetOutline=1;
	private int lengthOutline=1;

	protected MTLCodeScanner scanner;
	
	MTLClassOutline cOutline=new MTLClassOutline(classesOutline,offsetOutline,lengthOutline);
	MTLModelOutline mOutline=new MTLModelOutline(modelsOutline,offsetOutline,lengthOutline);
	MTLFunctionOutline fOutline=new MTLFunctionOutline(functionsOutline,offsetOutline,lengthOutline);

	/**
	 * Constructor for EiffelParser.
	 */
	public MTLParser()
	{
		super();
		int i;
		for(i = 0;i < systemClassNames.length; i++)
		{
			String aName = systemClassNames[i];
			systemClassMap.put(aName, aName);
		}
		scanner=(MTLCodeScanner)MTLPlugin.getDefault().getMTLEditorEnvironment().getCodeScanner();
		
		elementList.add(cOutline);
		elementList.add(mOutline);
		elementList.add(fOutline);
		
	}

	/**
	 * Returns a string containing the contents of the given file.  Returns an empty string if there
	 * were any errors reading the file.
	 */
	protected static String getText(IFile file)
	{
		try
		{
			InputStream in = file.getContents();
			return streamToString(in);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return "";
	}

	protected static String streamToString(InputStream in) throws IOException
	{
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		byte[] buf = new byte[2048];
		int read = in.read(buf);

		while (read > 0)
		{
			out.write(buf, 0, read);
			read = in.read(buf);
		}

		return out.toString();
	}

	/**
	 * Skips ahead and finds next non-whitespace token.
	 */
	public IToken nextNonWhitespaceToken()
	{
		IToken aToken = scanner.nextToken();

		while (!aToken.isEOF() && aToken.isWhitespace())
		{
			aToken = scanner.nextToken();
			
		}

		return aToken;
	}

	/**
	 * Parses the input given by the argument.
	 * @param file  the element containing the input text
	 * @return an element collection representing the parsed input
	 */
	public List parse(IFile file)
	{
		return parse(new Document(getText(file)));
	}

	/**
	 * Parses the input given by the argument.
	 * @param aSourceDocument the element containing the input text
	 * @return an element collection representing the parsed input
	 */
	public List parse(IDocument aSourceDocument)
	{
		sourceDocument = aSourceDocument;

		scanner.setRange(sourceDocument, 0, sourceDocument.getLength());
		IToken token = scanner.nextToken();
		while (!token.isEOF())
		{
			int offset = scanner.getTokenOffset();
			int length = scanner.getTokenLength();
			String expression = getExpression(offset, length);
			log.log_this(" e: "+expression+"  "+token.toString());		
			if (token.equals(MTLCodeScanner.TOKEN_FUNCTION))//Si c'est une fonction
			{
				//log.log_(" FUNCTION: "+expression);
				//addClass(expression, offset, length);//Ajouter ce token dans les classes???
				//addFunction(expression, offset, length);
			}
			if (token.equals(MTLCodeScanner.TOKEN_DEFAULT))//
			{
				//log.log_(" DEFAULT : "+expression);
				token = nextNonWhitespaceToken();
				int offset1 = scanner.getTokenOffset();
				int length1 = scanner.getTokenLength();
				String isPar = getExpression(offset1, length1);
				
				if (isPar.equals("("))
					{
						//log.log_(" METHOD : "+expression);
						detectLibraryMethod(expression,offset);
					}
				
			}
				
			if (token.equals(MTLCodeScanner.TOKEN_KEYWORD))//Si c'est un mot clé
				{
					if (expression.equals("library")){
						detectLibrary();
						//log.log_(" MOT CLE:"+expression);
					}
					if (expression.equals("model")){
						detectModel();
					}
					if (expression.equals("class")){
						detectClass();
					}
					if (expression.equals("main")){//Main est une fonction mais aussi un mot clé
						token = nextNonWhitespaceToken();
						int offset1 = scanner.getTokenOffset();
						int length1 = scanner.getTokenLength();
						String isPar = getExpression(offset1, length1);
						
						if (isPar.equals("("))
							{
								detectLibraryMethod(expression,offset);
							}
					}
					
						//addClass(expression, offset, length);//Ajouter ce token dans les classes???
							//addFunction(expression, offset, length);
				}
			token = scanner.nextToken();
		}
		return elementList;
	}
	
	
	private void parseClassContext(MTLClassElement aClass)
	{
		IToken token;
		token = nextNonWhitespaceToken();
		int parOuv=0;
		while (!token.isEOF())
		{
			int offsete = scanner.getTokenOffset();
			int lengthe = scanner.getTokenLength();
			String expression = getExpression(offsete, lengthe);
			log.log_(" E: "+expression+"  "+token.toString());
			if (expression.equals("}")){
				parOuv=parOuv-1;
			} else	if (expression.equals("{")){
				parOuv=parOuv+1;
			}else 	if (parOuv==0){
				return;
			}		//	else if (expression.equals("{")) 
			else if (token.equals(MTLCodeScanner.TOKEN_DEFAULT) && (parOuv==1) )
			{	
				token = nextNonWhitespaceToken();
				int offset1 = scanner.getTokenOffset();
				int length1 = scanner.getTokenLength();
				String isPar = getExpression(offset1, length1);
				//log.log_(" E E E: "+isPar+"  "+token.toString());
				
				if (isPar.equals("("))
					{
						//log.log_(" METHOD CLASS : "+getExpression(offsete, lengthe));
						MTLClassMethodElement cMethod= addClassMethod(expression,aClass.getName(),aClass.getOffset(),offsete,lengthe);
					}else if (isPar.equals("{")){
						parOuv=parOuv+1;
					}
			
			} 

			token = nextNonWhitespaceToken();
		}
	}




	/*
	 * Retourne la chaine de caractères contenue entre offset et offset+length
	 */
	
	private String getExpression(int offset, int length)
	{
		String expression;
		try {
			//sourceBuffer.substring(offset, offset + length);
			expression = sourceDocument.get(offset, length);
		} catch(BadLocationException e)
		{
			expression = "";
		}
		return expression;
	}
	private void detectLibrary()
	{
		IToken token;
		int length;
		int offset;

		token = nextNonWhitespaceToken();
		if (!token.isEOF())
		{
			int offsete = scanner.getTokenOffset();
			int lengthe = scanner.getTokenLength();
			String expression = getExpression(offsete, lengthe);
			int varOffset = scanner.getTokenOffset();
			length = scanner.getTokenLength();
			String libraryName = getExpression(varOffset, length);
			MTLLibraryElement aVariable = addLibrary(libraryName, varOffset);

		}
	}
	
	private void detectLibraryMethod(String fct, int offsete)
	{
		IToken token;
		int length;
		int offset;
		token = nextNonWhitespaceToken();
		
		if (!token.isEOF())
		{
		
			int varOffset = scanner.getTokenOffset();
			int lengthModelName = scanner.getTokenLength();
			String argName = getExpression(varOffset, lengthModelName);
						
			//Recherche du :
			token = nextNonWhitespaceToken();
			if (!token.isEOF())
				{
					offset = scanner.getTokenOffset();
					int lengthDot = scanner.getTokenLength();
					String aToken = getExpression(offset, lengthDot);
					if (aToken.equals(":"))
					{
						//Recherche du type de modèle librairiesMethod
						MTLLibraryMethodElement aVariable = addLibraryMethod(fct, offsete);
//						token = nextNonWhitespaceToken();
//						if (!token.isEOF()){
//							offset = scanner.getTokenOffset();
//							int lengthType = scanner.getTokenLength();
//							String typeModel = getExpression(offset, lengthType);
//							String modelNameWithType=getExpression(varOffset,lengthModelName+lengthDot+lengthType+2);
//							MTLModelElement aVariable = addModel(modelNameWithType, varOffset);
//						}
					}
					// a method has not necessarly return argument
					else if (aToken.equals("{"))
					{
						MTLLibraryMethodElement aVariable = addLibraryMethod(fct, offsete);
					}
				}
			}
	}
	

	private void detectClass()
	{
		IToken token;
		int length;
		int offset;

		token = nextNonWhitespaceToken();
		if (!token.isEOF())
		{
		
			int varOffset = scanner.getTokenOffset();
			int lengthClassName = scanner.getTokenLength();
			String className = getExpression(varOffset, lengthClassName);
			MTLClassElement aVariable = addClassObject(className, varOffset);
			parseClassContext(aVariable);
			//
			}
	}

	
	private void detectModel()
	{
		IToken token;
		int length;
		int offset;

		token = nextNonWhitespaceToken();
		if (!token.isEOF())
		{
		
			int varOffset = scanner.getTokenOffset();
			int lengthModelName = scanner.getTokenLength();
			String modelName = getExpression(varOffset, lengthModelName);

			//Recherche du :
			token = nextNonWhitespaceToken();
			if (!token.isEOF())
				{
					offset = scanner.getTokenOffset();
					int lengthDot = scanner.getTokenLength();
					String dot = getExpression(offset, lengthDot);
					if (dot.equals(":"))
					{
						//Recherche du type de modèle
						token = nextNonWhitespaceToken();
						if (!token.isEOF()){
							int offsetType = scanner.getTokenOffset();
							int lengthType = scanner.getTokenLength();
							String typeModel = getExpression(offsetType, lengthType);
							//String modelNameWithType=getExpression(varOffset,lengthModelName+lengthDot+lengthType+2);
							String modelNameWithType=modelName+" "+dot+" "+typeModel;
							MTLModelElement aVariable = addModel(modelNameWithType, varOffset);
						}
							
						
					}
				}
			}
	}
	

private void handleThisReference(String className, int expressionStart)
	{
		IToken token = nextNonWhitespaceToken();
		if (!token.isEOF())
		{
			int offset = scanner.getTokenOffset();
			int length = scanner.getTokenLength();

			String expression = getExpression(offset, length);

			if(expression.equals("."))
			{
				token = nextNonWhitespaceToken();
				if (!token.isEOF())
				{
					int memberStart = scanner.getTokenOffset();
					length = scanner.getTokenLength();

					String memberName = getExpression(memberStart, length);

					token = nextNonWhitespaceToken();
					if (!token.isEOF())
					{
						offset = scanner.getTokenOffset();
						length = scanner.getTokenLength();
						expression = getExpression(offset, length);

						if (expression.equals(":"))
						{
							addInstanceVariable(memberName, className, expressionStart, 1 + 4 - className.length());
						}
					}
				}
			}
		}
	}


	private MTLInstanceMethodElement addInstanceMethod(
		String memberName,
		String className,
		String arguments,
		int classOffset,
		int functionOffset,
		int functionLength)
	{
		int signatureLength = functionOffset - classOffset + functionLength;
		MTLInstanceMethodElement aMethod =
			new MTLInstanceMethodElement(memberName, arguments, classOffset, signatureLength);

		findOrCreateClass(className).addChildElement(aMethod);

		return aMethod;
	}

	private MTLClassMethodElement addClassMethod(
		String memberName,
		String className,
		int classOffset,
		int functionOffset,
		int functionLength)
	{
		MTLClassElement aClass = findOrCreateClass(className);
		int signatureLength = memberName.length();
		MTLClassMethodElement aMethod;

		aMethod = new MTLClassMethodElement(memberName,  functionOffset, functionLength);

		aClass.addChildElement(aMethod);
		//detectClassMethodContext(aMethod); 
	

		return aMethod;
	}

	private MTLElement addClassVariable(String memberName, String className, int classOffset)
	{
		//One extra char for "."
		MTLElement aVariable;
		MTLClassElement aClass = findOrCreateClass(className);

		if(aClass.isPrototype())
		{
			//aVariable =	new EiffelInstanceVariableElement(memberName, classOffset, className.length() + memberName.length() + 1);
			aVariable =	new MTLInstanceVariableElement(memberName, classOffset, memberName.length());

		} else {
			//aVariable =	new EiffelInstanceVariableElement(memberName, classOffset, className.length() + memberName.length() + 1);
			aVariable =	new MTLClassVariableElement(memberName, classOffset, memberName.length());
		}
		aClass.addChildElement(aVariable);

		return aVariable;
	}

	private MTLInstanceVariableElement addInstanceVariable(
		String memberName,
		String className,
		int classOffset,
		int paddingWidth)
	{
		//11 extra chars for ".prototype."
		MTLInstanceVariableElement aVariable =
			new MTLInstanceVariableElement(
				memberName,
				classOffset,
				className.length() + memberName.length() + paddingWidth);

		findOrCreateClass(className).addChildElement(aVariable);

		return aVariable;
	}

	private MTLGlobalVariableElement addGlobalVariable(String variableName, int offset)
	{
		MTLGlobalVariableElement aVariable;
		if (!globalVariables.containsKey(variableName))
		{
			aVariable = new MTLGlobalVariableElement(variableName, offset, variableName.length());

			elementList.add(aVariable);
			globalVariables.put(variableName, aVariable);
		} else
		{
			aVariable = (MTLGlobalVariableElement) classes.get(variableName);
		}

		return aVariable;
	}
	private MTLLibraryElement addLibrary(String libraryName, int offset)
	{
		MTLLibraryElement aVariable;
		if (!librairies.containsKey(libraryName))
		{
			aVariable = new MTLLibraryElement(libraryName, offset, libraryName.length());

			elementList.add(aVariable);
			librairies.put(libraryName, aVariable);
		} else
		{
			aVariable = (MTLLibraryElement) classes.get(libraryName);
		}

		return aVariable;
	}
	private MTLModelElement addModel(String modelName, int offset)
	{
		MTLModelElement aVariable;
		if (!models.containsKey(modelName))
		{
			aVariable = new MTLModelElement(modelName, offset, modelName.length());
			

			//elementList.add(aVariable);
			mOutline.addChildElement(aVariable);
			models.put(modelName, aVariable);
		} else
		{
			aVariable = (MTLModelElement) classes.get(modelName);
		}

		return aVariable;
	}
	
	private MTLClassElement addClassObject(String className, int offset)
	{
		MTLClassElement aVariable;
		if (!models.containsKey(className))
		{
			aVariable = new MTLClassElement(className, offset, className.length());

			//elementList.add(aVariable);
			cOutline.addChildElement(aVariable);
			classes.put(className, aVariable);
		} else
		{
			aVariable = (MTLClassElement) classes.get(className);
		}

		return aVariable;
	}
	
	private MTLLibraryMethodElement addLibraryMethod(String methodName, int offset)
	{
		MTLLibraryMethodElement aVariable;
		if (!librairiesMethod.containsKey(methodName))
		{
			aVariable = new MTLLibraryMethodElement(methodName, offset, methodName.length());

			//elementList.add(aVariable);
			fOutline.addChildElement(aVariable);
			librairiesMethod.put(methodName, aVariable);
		} else
		{
			aVariable = (MTLLibraryMethodElement) classes.get(methodName);
		}

		return aVariable;
	}
	
	private MTLClassElement findOrCreateClass(String className)
	{
		MTLClassElement aClass = null;
		if (!classes.containsKey(className))
		{
			if(functions.containsKey(className))
			{
				//if we're creating a class from an existing function we must
				//migrate the existing function to become a constructor in the class.
				MTLFunctionElement constructor = (MTLFunctionElement) functions.get(className);

				aClass = new MTLClassElement(className, constructor.getStart(), constructor.getLength());
				aClass.addChildElement(constructor);

				elementList.remove(constructor);
				elementList.add(aClass);
				classes.put(className, aClass);
			} else if(globalVariables.containsKey(className))
			{
				//if we're creating a class from an existing global variable we must
				//migrate the existing function to become a constructor in the class.
				MTLGlobalVariableElement aVariable = (MTLGlobalVariableElement) globalVariables.get(className);

				aClass = new MTLClassElement(className, aVariable.getStart(), aVariable.getLength());

				elementList.remove(aVariable);
				elementList.add(aClass);
				classes.put(className, aClass);
				globalVariables.remove(className);
			} else {
				//The final case is if we have no idea where this class came from, but shouldn't be ignored.
				aClass = new MTLClassElement(className, 0, 0);

				elementList.add(aClass);
				classes.put(className, aClass);
			}
		} else
		{
			aClass = (MTLClassElement) classes.get(className);
		}

		return aClass;
	}

	//est-ce une classe déjà détectée
	public boolean isSystemClass(String aClassName)
	{
		return systemClassMap.containsKey(aClassName);
	}

	/**
	 * Method getNaked.
	 * @param funcName
	 */
	private String getNaked(String funcName)
	{
		if (funcName == null)
		{
			return null;
		}

		funcName = funcName.trim().substring(FUNCTION.length()).trim();
		funcName = replaceInString(funcName.trim(), LINE_SEPARATOR, "");

		StringBuffer strBuf = new StringBuffer("");
		int len = funcName.length();
		boolean wasSpace = false;
		for (int i = 0; i < len; i++)
		{
			char ch = funcName.charAt(i);
			if (ch == ' ')
			{
				wasSpace = true;
			} else // not space
				{
				if (wasSpace)
				{
					strBuf.append(' ');
				}
				strBuf.append(ch);
				wasSpace = false;
			}
		}
		return strBuf.toString();
	}

	/**
	 * replace in a string a string sequence with another string sequence
	 */
	public static String replaceInString(String source, String whatBefore, String whatAfter)
	{
		if (null == source || source.length() == 0)
		{
			return source;
		}
		int beforeLen = whatBefore.length();
		if (beforeLen == 0)
		{
			return source;
		}
		StringBuffer result = new StringBuffer("");
		int lastIndex = 0;
		int index = source.indexOf(whatBefore, lastIndex);
		while (index >= 0)
		{
			result.append(source.substring(lastIndex, index));
			result.append(whatAfter);
			lastIndex = index + beforeLen;

			// get next
			index = source.indexOf(whatBefore, lastIndex);
		}
		result.append(source.substring(lastIndex));
		return result.toString();
	}

}
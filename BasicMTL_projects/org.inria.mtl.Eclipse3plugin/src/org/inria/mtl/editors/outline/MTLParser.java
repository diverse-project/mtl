package org.inria.mtl.editors.outline;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.Token;

import org.inria.mtl.editors.utils.MTLColorManager;
import org.inria.mtl.editors.utils.log;
import org.inria.mtl.editors.MTLCodeScanner;
import org.inria.mtl.MTLPlugin;
import org.inria.mtl.preferences.PreferencesConstants;
/**
 * @author sdzale
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
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
				log.log_(" FUNCTION: "+expression);
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
				
//				if (token.equals(MTLSyntaxScanner.TOKEN_DEFAULT))//
//				{
//					//We need to check if the token is already a function or class
//					if (functions.containsKey(expression) || classes.containsKey(expression))
//					{
//						token = nextNonWhitespaceToken();
//						if (token.equals(MTLSyntaxScanner.TOKEN_MEMBER))
//						{
//							log.log_this(" Instance Method:"+expression);
//							//detectInstanceMethod(offset, expression);
//						} else
//						{
//							log.log_this(" Class Method:"+expression);
//							//detectClassMethod(token, offset, expression);
//						}
//					} else
//					{
//						//Je fin d'ignorer les commentaires, en tant normal c'est la déclaration d'une variable
//						//à detecter, du type var ou static int, etc....
//						if (expression.equals("//"))
//						{
//							//detectGlobalVariable();
//						}
//					}
				
				
			//}
			if (token.equals(MTLCodeScanner.TOKEN_KEYWORD))//Si c'est un mot clé
				{
					if (expression.equals("library")){
						detectLibrary();
						log.log_(" MOT CLE:"+expression);
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
	
//	private void addClass(String expression, int offset, int length)
//		{
//			String functionSignature = getNaked(expression);
//			String Ouestcacheloffset = expression;
//			String functionName = ""; 
//			String arguments = ""; 
//			String corpsclasse = "";
//			int Melissa = offset;
//			int braceOffset = functionSignature.indexOf("\n");
//			String[] constructor= new String[12];; 
//			int constructeur = 0;
//			//Je cherche si la classe a déjà un corps ou si c'est juste une entête
//			if (braceOffset>=0) { 
//				functionName = functionSignature.substring(0, braceOffset).trim();
//			}
//			else {
//				if (functionSignature.indexOf("creation")>0){
//				} else functionName = functionSignature.substring(0, functionSignature.indexOf("end")); 
//			}
//			//Je vire le deferred si je détecte une classe abstraite
//			if (functionName.length()>9){
//				if (functionName.substring(0, 9).equals("red class"))
//					{ functionName = functionName.substring(10);}
//			}
//			//Je récupère les constructeurs de cette classe
//			if (functionSignature.indexOf("creation")> 0){
//				if (functionSignature.indexOf("feature")> 0){
//				arguments = functionSignature.substring(functionSignature.indexOf("creation"), functionSignature.indexOf("feature"));
//				corpsclasse = functionSignature.substring(functionSignature.indexOf("feature"));
//				} else {arguments = functionSignature.substring(functionSignature.indexOf("creation"), functionSignature.indexOf("end"));}
//			}
//			//log.log_this(" t:"+corpsclasse);
//			if(! functions.containsKey(functionName))
//			{
//				MTLClassElement aClass = new MTLClassElement(functionName, offset, length);
//				elementList.add(aClass);
//				classes.put(functionName, aClass);
//				if(!arguments.equals(""))
//				{
//					//Je découpe le mot clé creation pour prendre les constructeurs
//					arguments = arguments.substring(9);
//					int oucouper = arguments.indexOf(",");
//					String temp;int debut; int fin;
//					while (oucouper>0){
//						if (arguments.substring(0,1).equals(" ")) debut=1;
//						else debut=0; 
//						temp = arguments.substring(debut,oucouper);
//						temp = nettoyage(temp);
//						//log.log_this(" t:"+temp);
//						//Penser à donner un véritable offset
//						int offfff = Melissa + expression.indexOf("feature");
//						String tempoffset = expression.substring(expression.indexOf("feature"));  
//						offfff = offfff + tempoffset.indexOf(temp);
//						//
//						MTLElement aElement = addClassVariable(temp, functionName, offfff);
//						constructor[constructeur++] = temp;
//						//EiffelFunctionElement aMethod =
//						//	addClassMethod(temp, functionName, "", offset, offset2, temp.length());
//						//detectElementMethodContext(aElement);
//						arguments = arguments.substring(oucouper+1);
//						oucouper = arguments.indexOf(",");
//					}
//					// enleve l'espace devant le nom
//					if (arguments.substring(0,1).equals(" ")) {
//					arguments = arguments.substring(1,arguments.length());} 
//					fin = arguments.length();
//					braceOffset = arguments.indexOf("\n");
//					// Je cherche à virer le retour à la ligne ou les commentaires
//					if (braceOffset>0) fin = braceOffset;
//					braceOffset = arguments.indexOf("--"); 
//					if (braceOffset>0) fin = braceOffset;
//					temp = arguments.substring(0,fin);
//					temp = nettoyage(temp);
//					int offfff = Melissa + expression.indexOf("feature");
//					String tempoffset = expression.substring(expression.indexOf("feature"));  
//					offfff = offfff + tempoffset.indexOf(temp);
//					MTLElement aElement = addClassVariable(temp, functionName, offfff);
//					constructor[constructeur++] = temp;
//					//log.log_this(" t:"+temp);
//				}
//				if(!corpsclasse.equals(""))
//				{
//					//Je découpe de feature a is pour prendre la fonction
//					braceOffset = corpsclasse.indexOf(" is");
//					// Je cherche à prendre les différentes fonctions
//					//int offfff = Melissa + expression.indexOf("feature");
//					while (braceOffset>0){
//						//int offset2 = functionSignature.indexOf(arguments);
//						// Je découpe les fonctions si elle sont précédées d'un feature ou non
//						int offsetMethod = corpsclasse.indexOf("feature");
//						if (offsetMethod<0){offsetMethod=braceOffset+1;}
//						if (offsetMethod<braceOffset){
//							arguments = corpsclasse.substring(corpsclasse.indexOf("feature")+7, corpsclasse.indexOf(" is"));
//						} else {
//							//while ((arguments.indexOf("end\n"))>=0){
//							//arguments = corpsclasse.substring(corpsclasse.indexOf("end")+5, corpsclasse.indexOf(" is"));}}
//							arguments = corpsclasse.substring(corpsclasse.indexOf("end")+5, corpsclasse.indexOf(" is")); 
//						}
//						
//						//Je vais chercher à nettoyer
//						arguments=nettoyage(arguments);
//						//On garde cet offset dans le coin
//						//int offset2 = corpsclasse.indexOf(arguments);
//						//String tempoffset = arguments;  
//						
//						int cons = constructeur;
//						boolean ok = true; 
//						while (cons!=0)
//						{
//							cons--;
//							if (arguments.indexOf(constructor[cons])>=0)
//								{ok=false;}
//						}
//						//EiffelElement aElement = addClassVariable(temp, functionName, offset);
//						/*if (ok){EiffelFunctionElement aMethod =
//									addClassMethod(arguments, functionName, "", offset2, (offset2+arguments.length()), arguments.length());
//									detectClassMethodContext(aMethod);}*/
//						//Certains élémens du type corps de fonction précédent un is ne sont pas des fonctions, je cherche à les enlever
//						if (arguments.length()>65) {ok=false;}
//						//Certains arrivent jusqu'ici sans être une fonction je dois les dégager
//						if (arguments.indexOf(" end")>=0){
//							arguments = arguments.substring(arguments.indexOf("end")+3);
//							arguments=nettoyage(arguments);
//							}
//						int offfff = Melissa + expression.indexOf(arguments);
//						//offfff = offfff + offset2 + tempoffset.indexOf(arguments);
//						if (ok){MTLFunctionElement aMethod =
//															//addClassMethod(arguments, functionName, "", offset2++, (offset2+arguments.length()), arguments.length());
//							addClassMethod(arguments, functionName, "", offfff, offfff, arguments.length());
//															detectClassMethodContext(aMethod);}
//						corpsclasse = corpsclasse.substring( corpsclasse.indexOf(" is")+3);
//						braceOffset = corpsclasse.indexOf(" is");
//					}	
//				}			
//			detectClassContext(aClass); 
//			}
//		}

	private String nettoyage(String arguments)
	{
		String expression = arguments;
		int offset= arguments.indexOf("--");
		while (offset>0){
			while ((expression.indexOf("\n"))<(expression.indexOf("--"))){
				expression = expression.replaceFirst("\n","");
			}
			expression = expression.replaceFirst(expression.substring(expression.indexOf("--"), expression.indexOf("\n")),"");
			offset= expression.indexOf("--");
		} 
		if (expression.indexOf("feature")>0){
			expression = expression.substring(expression.indexOf("feature")+8);
		}
		/*
		while ((expression.indexOf(" "))>0){
			expression = expression.replaceFirst(" ","");
			while ((expression.indexOf("\n"))>0){
						expression = expression.replaceFirst("\n","");
					}	
		}
		
		while ((expression.indexOf("\n"))>0){
			expression = expression.replaceFirst("\n","");
			while ((expression.indexOf(" "))>0){
						expression = expression.replaceFirst(" ","");
					}
		}
		
		while ((expression.indexOf("\t"))>0){
			expression = expression.replaceFirst(" ","");
		}
		*/
		expression = expression.replaceFirst(" ","");
		expression = expression.replaceFirst("\t","");
		expression = expression.replaceFirst("\n","");
		offset = expression.indexOf("\n");
		if (offset>0) expression = expression.substring(offset);
		//expression = expression.replaceAll(" ","");
		expression = expression.replaceAll("\t","");
		expression = expression.replaceAll("\n","");
		while (expression.indexOf(" ")==0){expression = expression.replaceFirst(" ","");}


			
		return expression;
	}

	private void detectClassContext(MTLClassElement aClass)
	{
		IToken token = nextNonWhitespaceToken();
		while (!token.isEOF())
		{
			int offset = scanner.getTokenOffset();
			int length = scanner.getTokenLength();
			String expression = getExpression(offset, length);
			if (expression.equals("feature"))
			{
				parseClassContext(aClass);
				return;
			}
			token = nextNonWhitespaceToken();
		}
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
			}else 			if (parOuv==0){
				return;
			}		//	else if (expression.equals("{")) 
			else if (token.equals(MTLCodeScanner.TOKEN_DEFAULT) && (parOuv==1) )
			{	
				token = nextNonWhitespaceToken();
				int offset1 = scanner.getTokenOffset();
				int length1 = scanner.getTokenLength();
				String isPar = getExpression(offset1, length1);
				
				if (isPar.equals("("))
					{
						log.log_(" METHOD CLASS : "+getExpression(offsete, lengthe));
						MTLClassMethodElement cMethod= addClassMethod(expression,aClass.getName(),aClass.getOffset(),offsete,lengthe);
					}
//				if (isPar.equals("("))
//				{
//					//variables de classes
//					addClassMethod(aClass,expression,offset);
//				}
				
			} 

			token = nextNonWhitespaceToken();
		}
	}


//	private void addFunction(String expression, int offset, int length)
//	{
//		String functionSignature = getNaked(expression);
//		//Je déclare ici le debut de la classe/fonction 
//		int braceOffset = functionSignature.indexOf("\n");
//		String functionName = functionSignature.substring(0, braceOffset).trim();
//		String arguments =
//			functionSignature.substring(functionSignature.indexOf("creation"), functionSignature.indexOf("creation"));
//		//*///String arguments = "";
//		//log.log_this(functionSignature);
//		if (functionName.indexOf(".") >= 0)
//		{
//			//If the function signature includes .prototype. then it's a member.
//			if (functionName.indexOf(".prototype.") >= 0)
//			{
//				String className = functionName.substring(0, functionName.indexOf("."));
//				String memberName = functionName.substring(functionName.lastIndexOf(".") + 1);
//				MTLInstanceMethodElement aMethod =
//					this.addInstanceMethod(memberName, className, arguments, offset, offset, length);
//				detectInstanceMethodContext(className, aMethod);
//			} else
//			{
//				String className = functionName.substring(0, functionName.indexOf("."));
//				if (functions.containsKey(className) || classes.containsKey(className))
//				{
//					String memberName = functionName.substring(functionName.lastIndexOf(".") + 1);
//					MTLFunctionElement aMethod =
//						this.addClassMethod(memberName, className, arguments, offset, offset, length);
//				}
//			}
//		} else
//		{
//			if(! functions.containsKey(functionName))
//			{
//				MTLFunctionElement aFunction = new MTLFunctionElement(functionName, arguments, offset, length);
//
//				elementList.add(aFunction);
//				functions.put(functionName, aFunction);
//
//				detectFunctionContext(aFunction);
//			}
//		}
//	}

	private void checkForSpecialGlobalTypes(MTLGlobalVariableElement aVariable)
	{
		IToken token = nextNonWhitespaceToken();
		if (!token.isEOF())
		{
			if(!checkForDynamicClass(aVariable, token))
			{
				checkForAnonymousFunction(aVariable, token);
			}
		}
	}

	private boolean checkForDynamicClass(MTLGlobalVariableElement aVariable, IToken rhsToken)
	{
		if (rhsToken.equals(MTLSyntaxScanner.TOKEN_DEFAULT))
		{
			int offset = scanner.getTokenOffset();
			int length = scanner.getTokenLength();

			String expression = getExpression(offset, length);

			if (expression.equals("deferred"))
			{
				IToken token = nextNonWhitespaceToken();
				if (!token.isEOF())
				{
					if (token.equals(MTLSyntaxScanner.TOKEN_DEFAULT))
					{
						offset = scanner.getTokenOffset();
						length = scanner.getTokenLength();
						expression = getExpression(offset, length);

						if(! isSystemClass(expression))
						{
							MTLClassElement aClass = findOrCreateClass(aVariable.getName());
							if(aClass != null)
							{
								//Tell the class it's abstract declared: what we will parse as abstract class methods & vars are really instance methods & vars
								aClass.setPrototype(true);

								return true;
							}
						}
					}
				}
			}
		}
		return false;
	}

	private boolean checkForAnonymousFunction(MTLGlobalVariableElement aVariable, IToken rhsToken)
	{
		if (rhsToken.equals(MTLSyntaxScanner.TOKEN_FUNCTION))
		{
			String functionName = aVariable.getName();
			int offset = aVariable.getOffset();
			int length = aVariable.getLength();

			int functionOffset = scanner.getTokenOffset();
			int functionLength = scanner.getTokenLength();
			String functionSignature =
				getExpression(functionOffset, functionLength);
			String arguments = getArgumentString(functionSignature);

			MTLFunctionElement aFunction = new MTLFunctionElement(functionName, arguments, offset, functionOffset - offset + functionLength);

			elementList.add(aFunction);
			functions.put(functionName, aFunction);

			elementList.remove(aVariable);
			globalVariables.remove(functionName);

			detectFunctionContext(aFunction);

			return true;
		}

		return false;
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

	private void detectGlobalVariable()
	{
		IToken token;
		int length;
		int offset;

		token = nextNonWhitespaceToken();
		if (!token.isEOF())
		{
			if (token.equals(MTLSyntaxScanner.TOKEN_DEFAULT))
			{
				int varOffset = scanner.getTokenOffset();
				length = scanner.getTokenLength();
				String variableName = getExpression(varOffset, length);

				token = nextNonWhitespaceToken();
				if (!token.isEOF())
				{
					offset = scanner.getTokenOffset();
					length = scanner.getTokenLength();
					String expression = getExpression(offset, length);
					if (expression.equals(":"))
					{
						MTLGlobalVariableElement aVariable = addGlobalVariable(variableName, varOffset);
						checkForSpecialGlobalTypes(aVariable);
					}
				}
			}
		}
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
//			System.out.println("TOKENNN :"+expression );
//			
//			if (token.equals(MTLSyntaxScanner.TOKEN_DEFAULT))
//			{
				int varOffset = scanner.getTokenOffset();
				length = scanner.getTokenLength();
				String libraryName = getExpression(varOffset, length);
				MTLLibraryElement aVariable = addLibrary(libraryName, varOffset);

//				token = nextNonWhitespaceToken();
//				if (!token.isEOF())
//				{
//					offset = scanner.getTokenOffset();
//					length = scanner.getTokenLength();
//					String expression = getExpression(offset, length);
//					if (expression.equals(":"))
//					{
//						MTLGlobalVariableElement aVariable = addGlobalVariable(variableName, varOffset);
//						checkForSpecialGlobalTypes(aVariable);
//					}
//				}
			//}
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
					String dot = getExpression(offset, lengthDot);
					if (dot.equals(":"))
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
				}
			}
	}
	
//	private void detectClass()
//	{
//		IToken token;
//		int length;
//		int offset;
//
//		token = nextNonWhitespaceToken();
//		if (!token.isEOF())
//		{
//		
//			int varOffset = scanner.getTokenOffset();
//			int lengthClassName = scanner.getTokenLength();
//			String className = getExpression(varOffset, lengthClassName);
//			MTLClassElement aVariable = addClassObject(className, varOffset);
//
//			//
//			}
//	}

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
//			System.out.println("TOKEN :"+modelName);
			
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
	
//	private void detectClassMethod(IToken token, int classOffset, String className)
//	{
//		int offset = scanner.getTokenOffset();
//		int length = scanner.getTokenLength();
//		String expression = getExpression(offset, length);
//
//		if (expression.equals("."))
//		{
//
//			token = nextNonWhitespaceToken();
//			if (!token.isEOF())
//			{
//				offset = scanner.getTokenOffset();
//				length = scanner.getTokenLength();
//				String memberName = getExpression(offset, length);
//
//				token = nextNonWhitespaceToken();
//				if (!token.isEOF())
//				{
//					offset = scanner.getTokenOffset();
//					length = scanner.getTokenLength();
//					expression = getExpression(offset, length);
//					if (expression.equals(":"))
//					{
//
//						token = nextNonWhitespaceToken();
//						int tokenOffset = scanner.getTokenOffset();
//						int tokenLength = scanner.getTokenLength();
//
//						if (token.equals(MTLSyntaxScanner.TOKEN_FUNCTION))
//						{
//							String functionSignature = getExpression(tokenOffset, tokenLength);
//							String arguments = getArgumentString(functionSignature);
//
//							MTLFunctionElement aMethod =
//								addClassMethod(memberName, className, arguments, classOffset, tokenOffset, tokenLength);
//
//
//						} else
//						{
//							addClassVariable(memberName, className, classOffset);
//						}
//					}
//				}
//			}
//		}
//	}
//
	//A savoir si indexof peut supporter plus d'une lettre
	private String getArgumentString(String functionSignature)
	{
		return functionSignature.substring(
		//functionSignature.indexOf("("), functionSignature.indexOf(")") + 1);
		functionSignature.indexOf("creation"), functionSignature.indexOf("feature") + 1);
	}

	private void detectInstanceMethod(int classOffset, String className)
	{
		String expression;
		IToken token;
		int length;
		int offset;

		token = nextNonWhitespaceToken();
		if (!token.isEOF())
		{
			offset = scanner.getTokenOffset();
			length = scanner.getTokenLength();
			expression = getExpression(offset, length);

			if (expression.equals("."))
			{

				token = nextNonWhitespaceToken();
				if (!token.isEOF())
				{
					offset = scanner.getTokenOffset();
					length = scanner.getTokenLength();
					String memberName = getExpression(offset, length);

					token = nextNonWhitespaceToken();
					if (!token.isEOF())
					{
						offset = scanner.getTokenOffset();
						length = scanner.getTokenLength();
						expression = getExpression(offset, length);
						if (expression.equals(":"))
						{
							token = nextNonWhitespaceToken();
							if (token.equals(MTLSyntaxScanner.TOKEN_FUNCTION))
							{
								int functionOffset = scanner.getTokenOffset();
								int functionLength = scanner.getTokenLength();
								String functionSignature =
									getExpression(functionOffset, functionLength);
								String arguments = getArgumentString(functionSignature);

								MTLInstanceMethodElement aMethod =
									addInstanceMethod(
										memberName,
										className,
										arguments,
										classOffset,
										functionOffset,
										functionLength);

								detectInstanceMethodContext(className, aMethod);

							} else
							{
								addInstanceVariable(memberName, className, classOffset, (".prototype.").length());
							}

						}
					}
				}
			}
		}
	}

	private void parseInstanceMethodContext(String className, MTLFunctionElement aMethod)
	{
		IToken token;

		token = nextNonWhitespaceToken();
		while (!token.isEOF())
		{
			int offset = scanner.getTokenOffset();
			int length = scanner.getTokenLength();
			String expression = getExpression(offset, length);

			// if (token.equals(JSSyntaxScanner.TOKEN_END_CONTEXT))
			// if (expression.equals("}"))
			if (expression.equals("end"))
			{
				return;
			}// else if (expression.equals("{")) 
			else if (expression.equals("creation"))
			{
				parseInstanceMethodContext(className, aMethod);
			} else if (token.equals(MTLSyntaxScanner.TOKEN_DEFAULT))
			{
				if (expression.equals("local"))
				{
					handleThisReference(className, offset);
				}
			}

			token = nextNonWhitespaceToken();
		}
	}

	private void detectInstanceMethodContext(String className, MTLFunctionElement aMethod)
	{
		IToken token;

		token = nextNonWhitespaceToken();
		while (!token.isEOF())
		{
			int offset = scanner.getTokenOffset();
			int length = scanner.getTokenLength();
			String expression = getExpression(offset, length);

			//			if (token.equals(MTLSyntaxScanner.TOKEN_BEGIN_CONTEXT))
			if (expression.equals("creation"))
			{
				//parseInstanceMethodContext(className, aMethod);
				return;
			}

			token = nextNonWhitespaceToken();
		}
	}

	private void parseClassMethodContext(MTLFunctionElement aMethod)
	{
		IToken token;

		token = nextNonWhitespaceToken();
		while (!token.isEOF())
		{
			int offset = scanner.getTokenOffset();
			int length = scanner.getTokenLength();
			String expression = getExpression(offset, length);
			// if (expression.equals("}"))
			if (expression.equals("end"))
			{
				return;
			}// else if (expression.equals("{")) 
			else if (expression.equals("creation"))
			{
				parseClassMethodContext(aMethod);
			}

			token = nextNonWhitespaceToken();
		}
	}

	private void parseFunctionContext(MTLFunctionElement aFunction)
	{
		IToken token;

		token = nextNonWhitespaceToken();
		while (!token.isEOF())
		{
			int offset = scanner.getTokenOffset();
			int length = scanner.getTokenLength();
			String expression = getExpression(offset, length);
			// if (expression.equals("}"))
			if (expression.equals("end"))
			{
				return;
			}//	else if (expression.equals("{")) 
			else if (expression.equals("do"))
			{
				parseFunctionContext(aFunction);
			} else if (token.equals(MTLSyntaxScanner.TOKEN_DEFAULT))
			{
				if (expression.equals(":"))
				{
					handleThisReference(aFunction.getName(), offset);
				}
			}

			token = nextNonWhitespaceToken();
		}
	}

	private void detectClassMethodContext(MTLFunctionElement aMethod)
	{
		IToken token = nextNonWhitespaceToken();
		while (!token.isEOF())
		{
			int offset = scanner.getTokenOffset();
			int length = scanner.getTokenLength();
			String expression = getExpression(offset, length);
			// if (expression.equals("{"))
			if (expression.equals("feature"))
			{
				parseClassMethodContext(aMethod);
				return;
			}
			token = nextNonWhitespaceToken();
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

	private void detectFunctionContext(MTLFunctionElement aFunction)
	{
		IToken token = nextNonWhitespaceToken();
		while (!token.isEOF())
		{
			int offset = scanner.getTokenOffset();
			int length = scanner.getTokenLength();
			String expression = getExpression(offset, length);

			if (expression.equals("feature"))
			{
				parseFunctionContext(aFunction);
				return;
			}

			token = nextNonWhitespaceToken();
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
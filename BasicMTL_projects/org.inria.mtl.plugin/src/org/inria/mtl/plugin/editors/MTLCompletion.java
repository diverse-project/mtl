/**
 * Created on 03-05-2004
 * 
 *@author sdzale
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package org.inria.mtl.plugin.editors;

//import ish.etex.spell.GlobalDictionary;
//import ish.etex.spell.IgnoreChars;

import java.io.File;
import java.io.FileInputStream;
import java.net.URI;
import java.net.URL;
import java.util.Enumeration;
import java.util.Vector;


import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.contentassist.CompletionProposal;
import org.eclipse.jface.text.contentassist.ContextInformation;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.jface.text.contentassist.IContentAssistProcessor;
import org.eclipse.jface.text.contentassist.IContextInformation;
import org.eclipse.jface.text.contentassist.IContextInformationValidator;

import org.inria.mtl.plugin.MTLPlugin;

//import sjg.xml.Attribute;
//import sjg.xml.Data;
//import sjg.xml.Document;
//import sjg.xml.Element;
//import sjg.xml.Parser;

/**
 * @author Serge DZALE
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class MTLCompletion implements IContentAssistProcessor {
	
	private	Command[] commands;
	
	public MTLCompletion(){
		super();
		LoadCommmadDatabase();
	}
	
	private void LoadCommmadDatabase(){
		FileInputStream fis=null;
		try{
			IPath rootPath = new Path("codeassist/commands");
			URL urlPath = MTLPlugin.getDefault().find(rootPath);
			URI uri = new URI(urlPath.toExternalForm());
			File rootFolder = new File(uri.getSchemeSpecificPart());
			
			String[] children = rootFolder.list();
			Vector vFiles = new Vector();
			for(int i=0;i<children.length;i++){
				if(new File(children[i]).getName().endsWith("xml"))vFiles.add(rootFolder.getAbsolutePath()+"/"+children[i]);
			}
			String[] files = new String[vFiles.size()];
			vFiles.copyInto(files);
			Vector vCommands = new Vector();
			for(int i=0;i<files.length;i++){
				fis = new FileInputStream(files[i]);				
				Document d = Parser.parse(fis);
						Element root = d.getRoot();
						Enumeration ecompletions = root.elements();
						try{
						while(ecompletions.hasMoreElements()){
							Object o = ecompletions.nextElement();
							if(o instanceof Data){
								//ignore
							}else{
								Element e = (Element)o;
								Attribute aword = e.getAttribute("Code");
								String word = aword.getValue();
								Element edescription  = (Element)e.elements("Description").nextElement();
								String description = edescription.getContents();
								Element earguments  = null;
								try{
									earguments = (Element)e.elements("Arguments").nextElement();
								}catch(Exception ex){
									//No Arguments.
									//System.out.println("No Arguments for "+word);
								}
								Argument[] arguments = new Argument[0];
								if(earguments!=null){
									Enumeration args = earguments.elements("Argument");
									Vector vArgs = new Vector();
									while(args.hasMoreElements()){
										Element arg = (Element)args.nextElement();
										String argWord = arg.getAttribute("Code").getValue();
										String argDescription = arg.getContents();
										Argument a = new Argument(argWord,argDescription);
										vArgs.add(a);
									}
									arguments = new Argument[vArgs.size()];
									vArgs.copyInto(arguments);
								}
								
								Element eoptionals = null;
								
								try{
									eoptionals = (Element)e.elements("Optionals").nextElement();
								}catch(Exception ex){
									
								}
								
								Argument[] optionals = new Argument[0];
								if(eoptionals!=null){
									Enumeration options = eoptionals.elements("Argument");
									Vector vOptions = new Vector();
									while(options.hasMoreElements()){
										Element option = (Element)options.nextElement();
										String optionWord = option.getAttribute("Code").getValue();
										String optionDescription = option.getContents();
										Argument a = new Argument(optionWord,optionDescription);
										vOptions.add(a);
									}
									optionals = new Argument[vOptions.size()];
									vOptions.copyInto(optionals);
								}
								
								
								
								Command c = new Command(word,description,arguments,optionals);
								vCommands.add(c);
							}
						}
						}catch(Exception ex){
							System.out.println("Error Parsing XML.");
							System.out.println(ex);
						}
			}
			commands = new Command[vCommands.size()];
			vCommands.copyInto(commands);
			}catch(Exception ex){
			System.out.println("Error Loading XML.");
			System.out.println(ex);	
			}
			
	}
	
	public ICompletionProposal[] computeCompletionProposals(
		ITextViewer viewer,
		int documentOffset) {
		String filter = completionFilter(viewer,documentOffset);
		boolean environment = false;
		boolean optional = false;
		boolean argument = false;
		boolean bcommand = false;
		if(filter!=null){
			environment = isEnvironment(filter);
			optional = isOptionalArgument(filter);
			argument = isArgument(filter);
			bcommand = isCommand(filter);
		}
		//System.out.println("Filter: "+filter);
		
		if(bcommand && !environment && !optional && !argument){
			System.out.println("Is a command.");
			Command[] comp = getCommands(filter);
			ICompletionProposal[] results = new ICompletionProposal[comp.length];
			for(int i=0;i<comp.length;i++){
				IContextInformation ci = new ContextInformation(comp[i].word,comp[i].description);
				results[i] = new CompletionProposal(comp[i].word,documentOffset-filter.length(),filter.length(),comp[i].word.length(),null,comp[i].word,ci,"Latex Command: "+comp[i].word);
			}
			return results;
		}else if(optional){
			String command = getCommand(filter);
			System.out.println("Is an optional.");
			String optionalFilter = getOptionalFilter(filter);
			Argument[] optionals = getOptionals(command,optionalFilter);
			ICompletionProposal[] results = new ICompletionProposal[optionals.length];
			for(int i=0;i<optionals.length;i++){
				results[i] = new CompletionProposal(optionals[i].word,documentOffset-optionalFilter.length(),optionalFilter.length(),optionals[i].word.length());
			}
			return results;
			
			//return new ICompletionProposal[]{new CompletionProposal("Optional Detected",documentOffset,0,documentOffset)};
		}else if(argument){
			String command = getCommand(filter);
			System.out.println("Is an argument.");
			String argFilter = getArgumentFilter(filter);
			System.out.println("Argument Filter: "+argFilter);
			Argument[] args = getArguments(command,argFilter);
			ICompletionProposal[] results = new ICompletionProposal[args.length];
			for(int i=0;i<args.length;i++){
				results[i] = new CompletionProposal(args[i].word,documentOffset-argFilter.length(),argFilter.length(),args[i].word.length());
			}
			return results;
			//return new ICompletionProposal[]{new CompletionProposal("Argument Detected",documentOffset,0,documentOffset)};
		}else{
			System.out.println("Is a word.");
			String word = getWord(viewer,documentOffset);
			if(GlobalDictionary.isCorrect(word)){
				return new ICompletionProposal[0];
			}else{
				System.out.println("Checking : "+word);
				String[] suggestions = GlobalDictionary.getSuggestions(word);
				System.out.println("Found : "+suggestions.length+" suggestions.");
				ICompletionProposal[] results = new ICompletionProposal[suggestions.length];
				for(int i=0;i<suggestions.length;i++){
					results[i] = new CompletionProposal(suggestions[i],documentOffset-word.length(),word.length(),suggestions[i].length());
				}
				
				return results;
			}
		}
	}
	
	private String getCommand(String filter){
		int squareIndex = filter.indexOf("[");
		int curlyIndex = filter.indexOf("{");
		int endPos = filter.length()==0?0:filter.length()-1;
		if(squareIndex!=-1)endPos=squareIndex;
		if(curlyIndex!=-1 && curlyIndex<endPos)endPos = curlyIndex;
		return filter.substring(0,endPos);
	}
	
	private boolean isEnvironment(String filter){
		if(filter.startsWith("begin{")||filter.startsWith("end{"))return true;
		else return false;
	}
	
	private boolean isCommand(String filter){
		int indexofSpace = filter.indexOf(" ");
		if(indexofSpace!=-1)return false;
		return true;
	}
	
	private boolean isOptionalArgument(String filter){
		int squareIndex = filter.indexOf("[");
		int curlyIndex = filter.indexOf("{"); 
		
		if(squareIndex==-1)return false;
		if(squareIndex!=-1 && curlyIndex==-1)return true;
		//both curly and square are present and as optional always come before
		//required a curly bracket should not be present in the string, hence
		//incorrect format
		return false;
	}
	
	private boolean isArgument(String filter){
		int squareIndex = filter.indexOf("[");
		int curlyIndex = filter.indexOf("{"); 
		int curlyCloseIndex = filter.indexOf("}");
		if(curlyIndex==-1)return false;
		if(curlyCloseIndex!=-1)return false;
		if(curlyIndex!=-1 && squareIndex==-1)return true;
		if(curlyIndex > squareIndex)return true;
		return false;
	}
	
	private String completionFilter(ITextViewer viewer,int documentOffset){
		String currentC = "";
		int rearSet = 0;
		try{
		while(!(currentC=viewer.getDocument().get(documentOffset-rearSet,1)).equals("\\")){
			rearSet++;
		}
		return viewer.getDocument().get(documentOffset-(rearSet-1),rearSet-1);
		}
		catch(Exception ex){
			System.out.println(ex);
			return null;
		}
	}
	
	private String getWord(ITextViewer viewer,int documentOffset){
		String currentC = "";
		int rearSet = 1;
		try{
		while(!IgnoreChars.isIgnoreChar((viewer.getDocument().get(documentOffset-rearSet,1)).charAt(0))){
			rearSet++;
		}
		return viewer.getDocument().get(documentOffset-(rearSet-1),rearSet-1);
		}
		catch(Exception ex){
			System.out.println(ex);
			return "";
		}
		
	}
	
	private String getArgumentFilter(String completeFilter){
		int commaIndex = completeFilter.lastIndexOf(",");
		int curlyIndex = completeFilter.lastIndexOf("{");
		System.out.println("commaIndex: "+commaIndex+" curlyIndex: "+curlyIndex);
		if(curlyIndex==-1)return "";
		if(commaIndex!=-1 && (commaIndex>curlyIndex)){
			return completeFilter.substring(commaIndex+1,completeFilter.length());			
		}else{
			return completeFilter.substring(curlyIndex+1,completeFilter.length());
		}
	}
	
	private String getOptionalFilter(String completeFilter){
		int commaIndex = completeFilter.lastIndexOf(",");
		int squareIndex = completeFilter.lastIndexOf("[");
		if(squareIndex==-1)return "";
		if(squareIndex!=-1 && (commaIndex>squareIndex)){
			return completeFilter.substring(commaIndex+1,completeFilter.length());
		}else{
			return completeFilter.substring(squareIndex+1,completeFilter.length());
		}
	}
	
	private Argument[] getOptionals(String command,String optionalFilter){
		Command[] commands = getCommands(command);
		Vector vOptionals = new Vector();
		for(int i=0;i<commands.length;i++){
			for(int j=0;j<commands[i].optionals.length;j++){
				if(commands[i].optionals[j].word.toLowerCase().startsWith(optionalFilter)){
					vOptionals.add(commands[i].optionals[j]);
				}
			}
		}
		Argument[] optionals = new Argument[vOptionals.size()];
		vOptionals.copyInto(optionals);
		return optionals;
	}
	
	private Argument[] getArguments(String command,String argumentFilter){
		Command[] commands = getCommands(command);
		Vector vArgs = new Vector();
		for(int i=0;i<commands.length;i++){
			for(int j=0;j<commands[i].arguments.length;j++){
				if(commands[i].arguments[j].word.toLowerCase().startsWith(argumentFilter)){
					vArgs.add(commands[i].arguments[j]);
				}
			}
		}
		Argument[] args = new Argument[vArgs.size()];
		vArgs.copyInto(args);
		return args;
	}
	
	private Command[] getCommands(String filter){
		if(filter.equals(""))return commands;
		Vector possiblecompletions = new Vector();
		for(int i=0;i<commands.length;i++){
			if(commands[i].word.toLowerCase().startsWith(filter)){
				possiblecompletions.add(commands[i]);
			}
		}
		Command[] pc = new Command[possiblecompletions.size()];
		possiblecompletions.copyInto(pc);
		return pc;
	}

	
	
	public char[] getCompletionProposalAutoActivationCharacters() {
		return new char[] { '\\','{','[',','};
	}

	
	public IContextInformationValidator getContextInformationValidator() {
		return null;
	}
	
	public char[] getContextInformationAutoActivationCharacters() {
		return null;
	}
	
	public IContextInformation[] computeContextInformation(
		ITextViewer viewer,
		int documentOffset) {
		return null;
	}

	public String getErrorMessage() {
		return null;
	}


}

/* $Id: basicmtlANTLRVisitor.g,v 1.1 2003-07-11 08:25:06 jpthibau Exp $ */
header {
package ANTLRParser;

}

/*options {
        mangleLiteralPrefix = "TK_";
}*/

{
import java.io.*;
import java.util.*;
import org.apache.log4j.Logger;
import org.apache.log4j.LogManager;
import org.apache.log4j.xml.DOMConfigurator;
import ANTLRASTWalker.*;



class BMTL {
static DataInputStream input;

public static final org.apache.log4j.Logger log = Logger.getLogger("BMTLParser");

public static org.apache.log4j.Logger getLog () {
		return BMTL.log;
}

public static Object Parse(String name,ANTLRWalkerActionsInterface aWalker)
{	log.info("Opening source file : "+name);
	boolean noPb=true;
	Object builtTree=null;
	try { input=new DataInputStream(new FileInputStream(name)); }
	catch (FileNotFoundException e) {
		log.error("PB input file opening");}
	try {
		BasicmtlLexer lexer = new BasicmtlLexer(input);
		BasicmtlParser parser = new BasicmtlParser(lexer);
		parser.basicMTL(lexer);
		BasicmtlTreeWalker walker = new BasicmtlTreeWalker();
		builtTree=walker.basicMTL(parser.getAST(),aWalker);
		}
	catch(Exception e) {
		noPb=false;
		log.warn("exception: "+e+"=>"+e.getMessage());
		e.printStackTrace(); }				
	log.info("Parsing of file "+name+" is over.");
	if (noPb) return builtTree;
	else return null;
}

public static void main(String[] args)
{	DummyWalker aWalker=new DummyWalker();
	try {
		String filePath = new java.io.File("ThirdParty/log4j/log4j_configuration").getCanonicalPath();
		LogManager.resetConfiguration();
		DOMConfigurator.configure(filePath); }
	catch(java.io.IOException e) {
		System.err.println("Can't state log4j in BMTLParser"); }
	if (args.length > 0)
		for (int i=0;i<args.length;i++)
			try { Parse(args[i],aWalker); }
			catch (Exception e) {
				log.error(e);
				e.printStackTrace();}
	else log.error("USAGE : java BMTL <sourcefiles>");
}
}
}

/*==========================================*/
/*                                          */
/*           BASIC MTL TREE WALKER          */
/*                                          */
/*==========================================*/

class BasicmtlTreeWalker extends TreeParser;
options {
        k=2;
}
{
	ANTLRWalkerActionsInterface walker=null;
}

basicMTL[ANTLRWalkerActionsInterface theWalker] returns [Object tree=null;]
{	walker=theWalker;
	java.util.Vector theModels=new java.util.Vector();
	java.util.Vector theClasses=new java.util.Vector();
	java.util.Vector theMethods=new java.util.Vector();
	Object l1=null;
	Object l2=null;
	Object l3=null;
}
	: tree=headerdef
	  (l1=modelUse {theModels.addElement(l1); })*
	  ( l2=classDefinition {theClasses.addElement(l2); }
	    | l3=methodDefinition {theMethods.addElement(l3); } )+
	    {tree=walker.library(tree,theModels,theMethods,theClasses); }
		;

headerdef returns [Object tree=null;]
{	java.util.Vector theTags=new java.util.Vector();
	Object l1=null;
	Object l2=null;
}
	: #("library" s1:IDENTIFIER (l1=inheritance)? n1:NUM_INT
	 (l2=tag {theTags.addElement(l2); } )* )
		{tree=walker.libraryHeader(n1.getText(),s1.getText(),l1,theTags); }
	| #("nativelibrary" s2:IDENTIFIER n2:NUM_INT
		( l2=tag {theTags.addElement(l2); } )* )
		{tree=walker.nativeLibHeader(n2.getText(),s2.getText(),theTags); }
	;

modelUse returns [Object tree=null;]
{String typed=null;}
	: #("Model" s1:IDENTIFIER ("is" s2:IDENTIFIER {typed=s2.getText(); })? n:NUM_INT)
	  {tree=walker.model(n.getText(),s1.getText(),typed); }
	;
	
classDefinition returns [Object tree=null;]
{	java.util.Vector theTags=new java.util.Vector();
	java.util.Vector theAttributes=new java.util.Vector();
	java.util.Vector theMethods=new java.util.Vector();
	Object l1=null;
	Object l2=null;
	Object l3=null;
	Object l4=null;
}
	: #(n:NUM_INT s1:IDENTIFIER (l1=inheritance)?
	(l2=tag {theTags.addElement(l2); } )*
	( l3=attributesDef {theAttributes.addElement(l3); } )*
	( l4=methodDefinition {theMethods.addElement(l2); } )* )
	{tree=walker.classDefinition(n.getText(),s1.getText(),l1,theTags,theAttributes,theMethods); } 
	;
	
inheritance returns [Object tree=null;]
	: #("extends" tree=typeList)
	 {tree = walker.inheritance(tree);}
	;

typeList returns [Object tree=null;]
{ java.util.Vector theTypesList=new java.util.Vector(); }
	: tree=type {theTypesList.addElement(tree); }
	  (COMMA tree=type {theTypesList.addElement(tree); } )*
	 {tree=theTypesList; }
	;

attributesDef returns [Object tree=null;]
{	java.util.Vector theTags=new java.util.Vector();
	Object l1=null;
	Object l2=null;
}
	: l1=localVarDef
      (l2=tag {theTags.addElement(l2); } )*
      {tree=walker.attribute(l1,theTags); }
	;

methodDefinition returns [Object tree=null;]
{	String creation=null;
	String throwsException=null;
	java.util.Vector theTags=new java.util.Vector();
	java.util.Vector theVars=new java.util.Vector();
	java.util.Vector theInstructions=new java.util.Vector();
	Object l1=null;
	Object l2=null;
	Object l3=null;
	Object l4=null;
	Object l5=null;
}
	:	("creation" {creation=new String("creation"); } )?
			s1:IDENTIFIER n:NUM_INT
		    ( l1=parameterdef )? CLOSEBRACKET (COLON l2=type)?
			( "throwsException" {throwsException=new String("throwsException"); } )?
			( l3=tag {theTags.addElement(l3); } )*
			OPENBRACE (l4=localVarDef {theVars.addElement(l4); } )*
			(l5=instruction {theInstructions.addElement(l5); } )* CLOSEBRACE
		{tree=walker.method(creation,s1.getText(),n.getText(),l1,l2,throwsException,theVars,theInstructions,theTags); }
	;

parameterdef returns [Object tree=null;]
{ java.util.Vector theVarDecls=new java.util.Vector();
	Object l1=null;
}
	: tree=varDecl {theVarDecls.addElement(tree); }
	  ( SEMICOLON l1=varDecl {theVarDecls.addElement(l1); } )*
	  {tree=(Object)theVarDecls; }
	;

localVarDef returns [Object tree=null;]
	: tree=varDecl n:NUM_INT
	{tree=walker.varsDeclaration(tree,n.getText()); }
	;

varDecl returns [Object tree=null;]
{	java.util.Vector theVars=new java.util.Vector();
}
	: s1:IDENTIFIER { theVars.addElement(s1.getText()); }
	 ( COMMA s2:IDENTIFIER { theVars.addElement(s2.getText()); } )*
	 COLON tree=type
	 {tree=walker.typedVars(theVars,tree); }
	;

instruction returns [Object tree=null;]
{	java.util.Vector theInstructions=new java.util.Vector();
	java.util.Vector theElseInstructions=new java.util.Vector();
	java.util.Vector theCatchInstructions=null;
	java.util.Vector theCatches=new java.util.Vector();
	java.util.Vector theAssociatePoints=new java.util.Vector();
	String classIdentifier = null;
	Object l1=null;
	Object l2=null;
	Object l3=null;
	Object l4=null;
	Object l5=null;
	Object l6=null;
}
	:((IDENTIFIER POINT)? IDENTIFIER EQUAL) => 	(s1:IDENTIFIER POINT {classIdentifier=s1.getText();} )? s2:IDENTIFIER EQUAL tree=expression n2:NUM_INT
	  {tree=walker.varSettingInstr(classIdentifier,s2.getText(),tree,n2.getText());}
	| (tree=expression n:NUM_INT)
	  {tree=walker.expressionInstr(tree,n.getText());}
	| "return" (OPENBRACKET tree=expression CLOSEBRACKET)? n3:NUM_INT
	  {tree=walker.returnInstr(tree,n3.getText());}
	| "while" tree=expression n4:NUM_INT (l1=instruction {theInstructions.addElement(l1);} )* CLOSEBRACE
	  {tree=walker.whileInstr(tree,theInstructions,n4.getText());}	
	| "if" tree=expression n5:NUM_INT (l1=instruction {theInstructions.addElement(l1);} )+ CLOSEBRACE
		( "else" (l2=instruction {theElseInstructions.addElement(l2);} )+ CLOSEBRACE)?
	  {tree=walker.ifInstr(tree,theInstructions,theElseInstructions,n5.getText());}
	| "throws" tree=expression n6:NUM_INT
	  {tree=walker.throwsInstr(tree,n6.getText());}
	| "try" n7:NUM_INT ( l1=instruction {theInstructions.addElement(l1);} )+ CLOSEBRACE
	  ({theCatchInstructions=new java.util.Vector();}
	   "catch" s3:IDENTIFIER COLON l2=type n9:NUM_INT (l3=instruction {theCatchInstructions.addElement(l3);})+ CLOSEBRACE
	    { java.util.Vector v=new java.util.Vector();
	      v.addElement(s3.getText());
	      v.addElement(l2);
	      v.addElement(n9.getText());
	      v.addElement(theCatchInstructions);
	      theCatches.addElement(v); } )+ 
	  ( "finally" (l4=instruction {theElseInstructions.addElement(l4);} )+ CLOSEBRACE)? CLOSEBRACE
	  {tree=walker.tryInstr(theInstructions,theCatches,theElseInstructions,n7.getText());}
	| "associate" n8:NUM_INT l5=associateEndPoint {theAssociatePoints.addElement(l5);} 
		( COMMA l6=associateEndPoint {theAssociatePoints.addElement(l5);} )+ CLOSEBRACKET
	  {tree=walker.associateInstr(theAssociatePoints,n8.getText()); }
	;

associateEndPoint returns [Object tree=null;]
{	String role=null;
}
	: (s1:IDENTIFIER EQUAL {role=s1.getText(); })? s2:IDENTIFIER (COLON tree=type)? 
	  {tree=walker.associateEndPoint(role,s2.getText(),tree); }
	;

expression returns [Object tree=null;]
{	java.util.Vector theCalls=new java.util.Vector();
	java.util.Vector theAttributes=new java.util.Vector();
	String methodName=null;
	Object l1=null;
	Object l2=null;
}
	: ("new") => "new" tree=type (POINT s1:IDENTIFIER {methodName=s1.getText(); })? n:NUM_INT (l1=arguments)? CLOSEBRACKET (l2=operationCall {theCalls.addElement(l2); })*
		{tree=walker.newExpr(tree,methodName,l1,n.getText(),theCalls); }
	| (CHARORSTRING) => s2:CHARORSTRING (l2=operationCall {theCalls.addElement(l2); })*
		{tree=walker.stringLiteral(s2.getText(),theCalls); } 
	| (NUM_INT) => s3:NUM_INT (l2=operationCall {theCalls.addElement(l2); })*
		{tree=walker.intLiteral(s3.getText(),theCalls); } 
	| (NUM_FLOAT) => s4:NUM_FLOAT (l2=operationCall {theCalls.addElement(l2); })*
		{tree=walker.realLiteral(s4.getText(),theCalls); } 
	| ("self") => "self" (l2=operationCall {theCalls.addElement(l2); })*
		{tree=walker.selfLiteral(theCalls); } 
	| ("null") => "null" (l2=operationCall {theCalls.addElement(l2); })*
		{tree=walker.nullLiteral(theCalls); } 
	| ("true") => "true" (l2=operationCall {theCalls.addElement(l2); })*
		{tree=walker.trueLiteral(theCalls); } 
	| ("false") => "false" (l2=operationCall {theCalls.addElement(l2); })*
		{tree=walker.falseLiteral(theCalls); } 
	| (type POINT IDENTIFIER OPENBRACKET) => tree=type (l2=operationCall {theCalls.addElement(l2); })+ /*variable.method() or library.staticmethod() */
		{tree=walker.libraryOrVariable(tree,theCalls); } 
	| (IDENTIFIER POINT IDENTIFIER) => s5:IDENTIFIER (POINT s6:IDENTIFIER {theAttributes.addElement(s6.getText());})+ /*attribute getter*/
		{tree=walker.attributeGetter(s5.getText(),theAttributes); } 
	| s7:IDENTIFIER /* variable reference */
		{tree=walker.variableName(s7.getText()); } 
	;

/* expression returns [Object tree=null;]
{	java.util.Vector theCalls=new java.util.Vector();
	String methodName=null;
	Object l1=null;
	Object l2=null;
}
	:  ("new" tree=type (POINT s1:IDENTIFIER {methodName=s1.getText(); })? n:NUM_INT (l1=arguments)? CLOSEBRACKET
	     {tree=walker.newExpr(tree,methodName,l1,n.getText()); }
		| s2:CHARORSTRING
		{tree=walker.stringLiteral(s2.getText()); } 
		| s3:NUM_INT 
		{tree=walker.intLiteral(s3.getText()); } 
		| s4:NUM_FLOAT 
		{tree=walker.realLiteral(s4.getText()); } 
		| "self" 
		{tree=walker.selfLiteral(); } 
		| "null" 
		{tree=walker.nullLiteral(); } 
		| "true" 
		{tree=walker.trueLiteral(); } 
		| "false"
		{tree=walker.falseLiteral(); } 
		| s5:IDENTIFIER
		{tree=walker.variableName(s5.getText()); } 
						) (l2=operationCall {theCalls.addElement(l2); })* 
		{tree=walker.expression(tree,theCalls);}
	;*/

operationCall returns [Object tree=null;]
{	Object l1=null;
}
	:	( l1=simpleOperationCall
		| l1=oclAsTypeCall	)
		{tree=l1; }
	;

simpleOperationCall returns [Object tree=null;]
	: POINT s1:IDENTIFIER  n:NUM_INT (tree=arguments)? CLOSEBRACKET
	{tree=walker.operationCall(s1.getText(),tree,n.getText());}
	;

oclAsTypeCall returns [Object tree=null;]
	: POINT "oclAsType" n:NUM_INT tree=type CLOSEBRACKET
	{tree=walker.oclAsType(tree,n.getText());}
	;

arguments returns [Object tree=null;]
{	java.util.Vector theExpressions=new java.util.Vector();
	Object l1=null;
}
	: l1=expression {theExpressions.addElement(l1);}
	 ( COMMA l1=expression  {theExpressions.addElement(l1);} )*
	 {tree=walker.arguments(theExpressions); }
		;


type returns [Object tree=null;]
{ java.util.Vector theNamesList=new java.util.Vector(); }
	: #(s1:IDENTIFIER {theNamesList.addElement(s1.getText());}
		("::" s2:IDENTIFIER {theNamesList.addElement(s2.getText());})* )
	{tree=walker.typeName(theNamesList); } 
		;

tag  returns [Object tree=null;]
{	Object l1=null;
}
	: "tag" s1:IDENTIFIER EQUAL l1=taggedValue n:NUM_INT
	{tree=walker.tag(n.getText(),s1.getText(),l1); } 
	;
	
taggedValue	 returns [Object tree=null;]
	:"BooleanTag" ("true" {tree=walker.boolTagValueTrue();} | "false" {tree=walker.boolTagValueFalse();})
	| "IntegerTag" s1:NUM_INT
	{tree=walker.intTagValue(s1.getText());}
	| "RealTag" s2:NUM_FLOAT
	{tree=walker.realTagValue(s2.getText());}
	| "StringTag" s3:CHARORSTRING
	{tree=walker.stringTagValue(s3.getText());}
/*	| "VoidTag"
	| "EnumTag" IDENTIFIER "is" type
	| ("BagTag" | "SetTag" | "SequenceTag") (taggedValue) + "end"
	| "TupleTag" (IDENTIFIER taggedValue ) + "end" */
	| "SpecialTag" s4:TAGVALUE
	{tree=walker.specialTagValue(s4.getText());}
	  ;

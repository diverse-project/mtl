/* $Id: oldbasicmtl.g,v 1.1 2003-07-24 09:54:31 dvojtise Exp $ */
header {
package ANTLRParser;

}

options {
        mangleLiteralPrefix = "BMTL_";
	
}

{
import java.io.*;
import java.util.*;
import org.apache.log4j.Logger;
import org.apache.log4j.LogManager;
import org.apache.log4j.xml.DOMConfigurator;
import ANTLRASTWalker.*;
import antlr.debug.misc.*;



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
/*        importVocab=BasicMtl;*/
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
	/*to debug and see the current AST in a frame */
	ASTFrame frame = new ASTFrame("built AST:BasicMTL",#basicMTL);
  	frame.setVisible(true);
}
	: tree=headerdef
	  (l1=modelUse {theModels.addElement(l1); })*
	  ( l2=classDefinition {theClasses.addElement(l2); }
	    | l3=methodDefinition {theMethods.addElement(l3); } )+
	    {tree=walker.library(tree,theModels,theMethods,theClasses); }
exception catch [RecognitionException ex] {
	throw ex; }
		;

headerdef returns [Object tree=null;]
{	java.util.Vector theTags=new java.util.Vector();
	String n;
	Object l1=null;
	Object l2=null;
}
	: l1=libheader n=semicolon ( l2=tag {theTags.addElement(l2); })*
	 { tree=walker.libraryHeader(n,l1,theTags); }
exception catch [RecognitionException ex] {
	throw ex; } 
	;

libheader returns [Object tree=null;]
{	boolean nativeLib=false;
	Object l1=null;
}
	: #("library" s1:IDENTIFIER (l1=inheritance)? )
	| #("nativelibrary" {nativeLib=true;} s2:IDENTIFIER) 
	{ if (nativeLib) tree=walker.nativeLibHeader(s2.getText());
	  else tree=walker.bmtllibraryHeader(s1.getText(),l1); }
exception catch [RecognitionException ex] {
	throw ex; } 
	;

/*{	java.util.Vector theTags=new java.util.Vector();
	Object l1=null;
	Object l2=null;
}
	: #("library" s1:IDENTIFIER (l1=inheritance)? n1:NUM_INT
	 (l2=tag {theTags.addElement(l2); } )* )
		{tree=walker.libraryHeader(n1.getText(),s1.getText(),l1,theTags); }
	| #("nativelibrary" s2:IDENTIFIER n2:NUM_INT
		( l2=tag {theTags.addElement(l2); } )* )
		{tree=walker.nativeLibHeader(n2.getText(),s2.getText(),theTags); }
exception catch [RecognitionException ex] {
	throw ex; }
	;*/
modelUse returns [Object tree=null;]
{String n;
 String typed=null;
}
	: node:"model" s1:IDENTIFIER {node.addChild(s1);} ("is" s2:IDENTIFIER {typed=s2.getText(); })? n=semicolon
	  { tree=walker.model(n,s1.getText(),typed); }
exception catch [RecognitionException ex] {
	throw ex; }
	;
	
classDefinition returns [Object tree=null;]
{	java.util.Vector theTags=new java.util.Vector();
	java.util.Vector theAttributes=new java.util.Vector();
	java.util.Vector theMethods=new java.util.Vector();
	String n;
	Object l1=null;
	Object l2=null;
	Object l3=null;
	Object l4=null;
}
	: n=classkeyword s1:IDENTIFIER (l1=inheritance)?
	(l2=tag {theTags.addElement(l2); } )*
	OPENBRACE ( l3=attributesDef {theAttributes.addElement(l3); } )*
	( l4=methodDefinition {theMethods.addElement(l2); } )* CLOSEBRACE 
	{tree=walker.classDefinition(n,s1.getText(),l1,theTags,theAttributes,theMethods); } 
exception catch [RecognitionException ex] {
	throw ex; }
	;
	
inheritance returns [Object tree=null;]
	: "extends" tree=typeList
	 {tree = walker.inheritance(tree);}
exception catch [RecognitionException ex] {
	throw ex; }
	;

typeList returns [Object tree=null;]
{ java.util.Vector theTypesList=new java.util.Vector(); }
	: tree=type {theTypesList.addElement(tree); }
	  (COMMA tree=type {theTypesList.addElement(tree); } )*
	 {tree=theTypesList; }
exception catch [RecognitionException ex] {
	throw ex; }
	;

attributesDef returns [Object tree=null;]
{	java.util.Vector theTags=new java.util.Vector();
	Object l1=null;
	Object l2=null;
}
	: l1=localVarDef
      (l2=tag {theTags.addElement(l2); } )*
      {tree=walker.attribute(l1,theTags); }
exception catch [RecognitionException ex] {
	throw ex; }
	;

methodDefinition returns [Object tree=null;]
{	String creation=null;
	String throwsException=null;
	java.util.Vector theTags=new java.util.Vector();
	java.util.Vector theVars=new java.util.Vector();
	java.util.Vector theInstructions=new java.util.Vector();
	String n;
	Object l1=null;
	Object l2=null;
	Object l3=null;
	Object l4=null;
	Object l5=null;
}
	:	("creation" {creation=new String("creation"); } )?
			s1:IDENTIFIER n=openbracket
		    ( l1=parameterdef )? CLOSEBRACKET (COLON l2=type)?
			( "throwsException" {throwsException=new String("throwsException"); } )?
			( l3=tag {theTags.addElement(l3); } )*
			OPENBRACE (l4=localVarDef {theVars.addElement(l4); } )*
			(l5=instruction {theInstructions.addElement(l5); } )* CLOSEBRACE
		{tree=walker.method(creation,s1.getText(),n,l1,l2,throwsException,theVars,theInstructions,theTags); }
exception catch [RecognitionException ex] {
	throw ex; }
	;

parameterdef returns [Object tree=null;]
{ java.util.Vector theVarDecls=new java.util.Vector();
	Object l1=null;
}
	: tree=varDecl {theVarDecls.addElement(tree); }
	  ( SEMICOLON l1=varDecl {theVarDecls.addElement(l1); } )*
	  {tree=(Object)theVarDecls; }
exception catch [RecognitionException ex] {
	throw ex; }
	;

localVarDef returns [Object tree=null;]
{String n;}
	: tree=varDecl n=semicolon
	{tree=walker.varsDeclaration(tree,n); }
exception catch [RecognitionException ex] {
	throw ex; }
	;

varDecl returns [Object tree=null;]
{	java.util.Vector theVars=new java.util.Vector();
}
	: s1:IDENTIFIER { theVars.addElement(s1.getText()); }
	 ( COMMA s2:IDENTIFIER { theVars.addElement(s2.getText()); } )*
	 COLON tree=type
	 {tree=walker.typedVars(theVars,tree); }
exception catch [RecognitionException ex] {
	throw ex; }
	;

instruction returns [Object tree=null;]
{	java.util.Vector theInstructions=new java.util.Vector();
	java.util.Vector theElseInstructions=new java.util.Vector();
	java.util.Vector theCatchInstructions=null;
	java.util.Vector theCatches=new java.util.Vector();
	java.util.Vector theAssociatePoints=new java.util.Vector();
	String classIdentifier = null;
	String n;
	Object l1=null;
	Object l2=null;
	Object l3=null;
	Object l4=null;
	Object l5=null;
	Object l6=null;
	/*to debug and see the current AST in a frame */
	ASTFrame frame = new ASTFrame("built AST:instruction",#instruction);
  	frame.setVisible(true);
}
	: #(EQUAL (s1:IDENTIFIER POINT {classIdentifier=s1.getText();} )? s2:IDENTIFIER EQUAL tree=expression n=semicolon)
	  {tree=walker.varSettingInstr(classIdentifier,s2.getText(),tree,n);}
	| (DIESE) => #(DIESE tree=expression n=semicolon)
	  {tree=walker.expressionInstr(tree,n);}
	| "return" (OPENBRACKET tree=expression CLOSEBRACKET)? n=semicolon
	  {tree=walker.returnInstr(tree,n);}
	| "while" tree=expression n=openbrace (l1=instruction {theInstructions.addElement(l1);} )* CLOSEBRACE
	  {tree=walker.whileInstr(tree,theInstructions,n);}	
	| "if" tree=expression n=openbrace (l1=instruction {theInstructions.addElement(l1);} )+ CLOSEBRACE
		( "else" (l2=instruction {theElseInstructions.addElement(l2);} )+ CLOSEBRACE)?
	  {tree=walker.ifInstr(tree,theInstructions,theElseInstructions,n);}
	| "throws" tree=expression n=semicolon
	  {tree=walker.throwsInstr(tree,n);}
	| "try" n=openbrace ( l1=instruction {theInstructions.addElement(l1);} )+ CLOSEBRACE
	  ({theCatchInstructions=new java.util.Vector();}
	   "catch" s3:IDENTIFIER COLON l2=type n=openbrace (l3=instruction {theCatchInstructions.addElement(l3);})+ CLOSEBRACE
	    { java.util.Vector v=new java.util.Vector();
	      v.addElement(s3.getText());
	      v.addElement(l2);
	      v.addElement(n);
	      v.addElement(theCatchInstructions);
	      theCatches.addElement(v); } )+ 
	  ( "finally" (l4=instruction {theElseInstructions.addElement(l4);} )+ CLOSEBRACE)? CLOSEBRACE
	  {tree=walker.tryInstr(theInstructions,theCatches,theElseInstructions,n);}
	| "associate" n=openbracket l5=associateEndPoint {theAssociatePoints.addElement(l5);} 
		( COMMA l6=associateEndPoint {theAssociatePoints.addElement(l5);} )+ CLOSEBRACKET
	  {tree=walker.associateInstr(theAssociatePoints,n); }
exception catch [RecognitionException ex] {
	throw ex; }
	;

associateEndPoint returns [Object tree=null;]
{	String role=null;
}
	: (s1:IDENTIFIER EQUAL {role=s1.getText(); })? s2:IDENTIFIER (COLON tree=type)? 
	  {tree=walker.associateEndPoint(role,s2.getText(),tree); }
exception catch [RecognitionException ex] {
	throw ex; }
	;

/*expression returns [Object tree=null;]
{	java.util.Vector theCalls=new java.util.Vector();
	java.util.Vector theAttributes=new java.util.Vector();
	String n;
	String methodName=null;
	Object l1=null;
	Object l2=null;
	/*to debug and see the current AST in a frame */
/*	ASTFrame frame = new ASTFrame("built AST:expression",#expression);
  	frame.setVisible(true);
}
	: ("new") => "new" tree=type (POINT s1:IDENTIFIER {methodName=s1.getText(); })? n=openbracket (l1=arguments)? CLOSEBRACKET  (POINT l2=operationCall {theCalls.addElement(l2); })*
		{tree=walker.newExpr(tree,methodName,l1,n,theCalls); }
	| (CHARORSTRING) => s2:CHARORSTRING (POINT l2=operationCall {theCalls.addElement(l2); })*
		{tree=walker.stringLiteral(s2.getText(),theCalls); } 
	| (NUM_INT) => s3:NUM_INT (POINT l2=operationCall {theCalls.addElement(l2); })*
		{tree=walker.intLiteral(s3.getText(),theCalls); } 
	| (NUM_FLOAT) => s4:NUM_FLOAT (POINT l2=operationCall {theCalls.addElement(l2); })*
		{tree=walker.realLiteral(s4.getText(),theCalls); } 
	| ("self") => "self" (POINT l2=operationCall {theCalls.addElement(l2); })*
		{tree=walker.selfLiteral(theCalls); } 
	| ("null") => "null" (POINT l2=operationCall {theCalls.addElement(l2); })*
		{tree=walker.nullLiteral(theCalls); } 
	| ("true") => "true" (POINT l2=operationCall {theCalls.addElement(l2); })*
		{tree=walker.trueLiteral(theCalls); } 
	| ("false") => "false" (POINT l2=operationCall {theCalls.addElement(l2); })*
		{tree=walker.falseLiteral(theCalls); } 
	| (type POINT IDENTIFIER OPENBRACKET) => tree=type (POINT l2=operationCall {theCalls.addElement(l2); })+ /*variable.method() or library.staticmethod() */
/*		{tree=walker.libraryOrVariable(tree,theCalls); } 
	| (IDENTIFIER OPENBRACKET) => (l1=operationCall {theCalls.addElement(l1); })+
		{tree=walker.directOperationCalls(theCalls); } 
	| (IDENTIFIER POINT IDENTIFIER) => s5:IDENTIFIER (POINT s6:IDENTIFIER {theAttributes.addElement(s6.getText());})+ /*attribute getter*/
/*		{tree=walker.attributeGetter(s5.getText(),theAttributes); } 
	| s7:IDENTIFIER /* variable reference */
/*		{tree=walker.variableName(s7.getText()); } 
exception catch [RecognitionException ex] {
	throw ex; }
	;*/
	
expression returns [Object tree=null;]
{	java.util.Vector theCalls=new java.util.Vector();
	java.util.Vector theAttributes=new java.util.Vector();
	String n;
	String methodName=null;
	Object l1=null;
	Object l2=null;
	/*to debug and see the current AST in a frame */
	ASTFrame frame = new ASTFrame("built AST:expression",#expression);
  	frame.setVisible(true);
}
	: #("new" tree=type (POINT s1:IDENTIFIER {methodName=s1.getText(); })? n=openbracket (l1=arguments)? CLOSEBRACKET  (POINT l2=operationCall {theCalls.addElement(l2); })*)
		{tree=walker.newExpr(tree,methodName,l1,n,theCalls); }
	| #(s2:CHARORSTRING (POINT l2=operationCall {theCalls.addElement(l2); })* )
		{tree=walker.stringLiteral(s2.getText(),theCalls); } 
	| #(s3:NUM_INT (POINT l2=operationCall {theCalls.addElement(l2); })* )
		{tree=walker.intLiteral(s3.getText(),theCalls); } 
	| #(s4:NUM_FLOAT (POINT l2=operationCall {theCalls.addElement(l2); })* )
		{tree=walker.realLiteral(s4.getText(),theCalls); } 
	| #("self" (POINT l2=operationCall {theCalls.addElement(l2); })* )
		{tree=walker.selfLiteral(theCalls); } 
	| #("null" (POINT l2=operationCall {theCalls.addElement(l2); })* )
		{tree=walker.nullLiteral(theCalls); } 
	| #("true" (POINT l2=operationCall {theCalls.addElement(l2); })* )
		{tree=walker.trueLiteral(theCalls); } 
	| #("false" (POINT l2=operationCall {theCalls.addElement(l2); })* )
		{tree=walker.falseLiteral(theCalls); } 
	| #(OPENBRACKET tree=type (POINT l2=operationCall {theCalls.addElement(l2); })+ )/*variable.method() or library.staticmethod() */
		{tree=walker.libraryOrVariable(tree,theCalls); } 
	| (l1=operationCall {theCalls.addElement(l1); })+
		{tree=walker.directOperationCalls(theCalls); } 
	| #(POINT s5:IDENTIFIER (POINT s6:IDENTIFIER {theAttributes.addElement(s6.getText());})+ )/*attribute getter*/
		{tree=walker.attributeGetter(s5.getText(),theAttributes); } 
	| #(DIESE s7:IDENTIFIER) /* variable reference */
		{tree=walker.variableName(s7.getText()); } 
exception catch [RecognitionException ex] {
	throw ex; }
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
exception catch [RecognitionException ex] {
	throw ex; }
	;

simpleOperationCall returns [Object tree=null;]
{ String n; }
	: s1:IDENTIFIER  n=openbracket (tree=arguments)? CLOSEBRACKET
	{tree=walker.operationCall(s1.getText(),tree,n);}
exception catch [RecognitionException ex] {
	throw ex; }
	;

oclAsTypeCall returns [Object tree=null;]
{ String n; }
	: "oclAsType" n=openbracket tree=type CLOSEBRACKET
	{tree=walker.oclAsType(tree,n);}
exception catch [RecognitionException ex] {
	throw ex; }
	;

arguments returns [Object tree=null;]
{	java.util.Vector theExpressions=new java.util.Vector();
	Object l1=null;
}
	: l1=expression {theExpressions.addElement(l1);}
	 ( COMMA l1=expression  {theExpressions.addElement(l1);} )*
	 {tree=walker.arguments(theExpressions); }
exception catch [RecognitionException ex] {
	throw ex; }
		;


type returns [Object tree=null;]
{ java.util.Vector theNamesList=new java.util.Vector(); }
	: s1:IDENTIFIER {theNamesList.addElement(s1.getText());}
		(DOUBLECOLON s2:IDENTIFIER {theNamesList.addElement(s2.getText());})* 
	{tree=walker.typeName(theNamesList); } 
exception catch [RecognitionException ex] {
	throw ex; }
		;

tag  returns [Object tree=null;]
{	String n;
	Object l1=null;
}
	: "tag" s1:IDENTIFIER EQUAL l1=taggedValue n=semicolon
	{tree=walker.tag(n,s1.getText(),l1); } 
exception catch [RecognitionException ex] {
	throw ex; }
	;
	
taggedValue	 returns [Object tree=null;]
	:"booleantag" ("true" {tree=walker.boolTagValueTrue();} | "false" {tree=walker.boolTagValueFalse();})
	| "integertag" s1:NUM_INT
	{tree=walker.intTagValue(s1.getText());}
	| "realtag" s2:NUM_FLOAT
	{tree=walker.realTagValue(s2.getText());}
	| "stringtag" s3:CHARORSTRING
	{tree=walker.stringTagValue(s3.getText());}
/*	| "VoidTag"
	| "EnumTag" IDENTIFIER "is" type
	| ("BagTag" | "SetTag" | "SequenceTag") (taggedValue) + "end"
	| "TupleTag" (IDENTIFIER taggedValue ) + "end" */
	| "specialtag" s4:TAGVALUE
	{tree=walker.specialTagValue(s4.getText());}
exception catch [RecognitionException ex] {
	throw ex; }
	  ;
	  
semicolon	 returns [String number=null;]
	: n:NUM_INT
	  {number=n.getText(); }
	  ;

classkeyword	 returns [String number=null;]
	: n:NUM_INT
	  {number=n.getText(); }
	  ;

openbracket	 returns [String number=null;]
	: n:NUM_INT
	  {number=n.getText(); }
	  ;

openbrace	 returns [String number=null;]
	: n:NUM_INT
	  {number=n.getText(); }
	  ;


/*==========================================*/
/*                                          */
/*             BASIC MTL PARSER             */
/*                                          */
/*==========================================*/

class BasicmtlParser extends Parser;
options {
	buildAST = true;
/*	importVocab=BasicMtl;*/	
    k=2;
}
{
BasicmtlLexer lexer; }

basicMTL[BasicmtlLexer theLexer]
{ lexer = theLexer; }
	: headerdef modelUse ( classDefinition | methodDefinition )+
	  EOF!
		;

headerdef
	: libheader semicolon ( tag )*
exception catch [RecognitionException ex] {
	throw ex; } 
	;

libheader
	: "library"^ IDENTIFIER (inheritance)?
	| "nativelibrary"^ IDENTIFIER
exception catch [RecognitionException ex] {
	throw ex; } 
	;

modelUse : ("model"^ IDENTIFIER ("is" IDENTIFIER)? semicolon)*
exception catch [RecognitionException ex] {
	throw ex; } 
	;
	
classDefinition
	: classKeyword IDENTIFIER (inheritance)?
	(tag )*
	 OPENBRACE ( attributesDef )* ( methodDefinition )* CLOSEBRACE
exception catch [RecognitionException ex] {
	throw ex; } 
	;

inheritance : "extends" typeList
exception catch [RecognitionException ex] {
	throw ex; } 
	;

typeList : type ( COMMA type )*
exception catch [RecognitionException ex] {
	throw ex; }
	;

attributesDef : localVarDef
                (tag )*
exception catch [RecognitionException ex] {
	throw ex; }
		;

methodDefinition : ("creation")? IDENTIFIER 
		openbracket ( parameterdef )? CLOSEBRACKET (COLON type)?
		( "throwsException" )?
		( tag )*
		OPENBRACE (localVarDef )* (instruction)* CLOSEBRACE
 exception catch [RecognitionException ex] {
	throw ex; } 
	;

parameterdef : varDecl ( SEMICOLON varDecl )*
exception catch [RecognitionException ex] {
	throw ex; }
	;

localVarDef : varDecl semicolon
exception catch [RecognitionException ex] {
	throw ex; }
	;

varDecl : IDENTIFIER  ( COMMA IDENTIFIER )* COLON type
exception catch [RecognitionException ex] {
	throw ex; }
	;

instruction : !	((IDENTIFIER POINT)? IDENTIFIER EQUAL) => s1:(IDENTIFIER POINT)? s2:IDENTIFIER EQUAL e1:expression semicolon
	{#instruction=#(#[EQUAL],#s1,#s2,#e1); }
	| !  e:expression n:semicolon
	{#instruction=#(#[DIESE],#e,#n); }
	| "return" (OPENBRACKET expression CLOSEBRACKET)? semicolon
	| "while" expression openbrace (instruction)* CLOSEBRACE
	| "if" expression openbrace (instruction )+ CLOSEBRACE ( "else" OPENBRACE! (instruction)+ CLOSEBRACE )?
	| "throws" expression semicolon
	| "try" openbrace ( instruction )+
	  ("catch" IDENTIFIER COLON type openbrace (instruction )+ CLOSEBRACE )+
	  ( "finally" OPENBRACE! (instruction )+ CLOSEBRACE )? CLOSEBRACE
	| "associate" openbracket associateEndPoint ( COMMA associateEndPoint )+ CLOSEBRACKET SEMICOLON!
exception catch [RecognitionException ex] {
	throw ex; }
	;

associateEndPoint : (IDENTIFIER EQUAL)? IDENTIFIER (COLON type)?
exception catch [RecognitionException ex] {
	throw ex; }
	;

expression ! :
	("new") => s1:"new" t:type a:(POINT IDENTIFIER)? n:openbracket l:(arguments)? CLOSEBRACKET op:(POINT operationCall)*
	{#expression=#(#s1,#t,#a,#n,#l,#op);}
	| (CHARORSTRING) => s2:CHARORSTRING op2:(POINT operationCall)*
	{#expression=#(#s2,#op2);}
	| (NUM_INT) => s3:NUM_INT op3:(POINT operationCall)*
	{#expression=#(#s3,#op3);}
	| (NUM_FLOAT) => s4:NUM_FLOAT op4:(POINT operationCall)*
	{#expression=#(#s4,#op4);}
	| ("self") => s5:"self" op5:(POINT operationCall)*
	{#expression=#(#s5,#op5);}
	| ("null") => s6:"null" op6:(POINT operationCall)*
	{#expression=#(#s6,#op6);}
	| ("true") => s7:"true" op7:(POINT operationCall)*
	{#expression=#(#s7,#op7);}
	| ("false") => s8:"false" op8:(POINT operationCall)*
	{#expression=#(#s8,#op8);}
	| (type POINT IDENTIFIER b:OPENBRACKET) => t2:type op9:(POINT operationCall)+ /*variable.method() or library.staticmethod() */
	{#expression=#(#b,#t2,#op9);}
	| (IDENTIFIER OPENBRACKET) => op10:(operationCall)+
	{#expression=#op10;}
	| (IDENTIFIER POINT IDENTIFIER) => i1:IDENTIFIER a2:(p:POINT IDENTIFIER)+ /*attribute getter*/
	{#expression=#(#p,#i1,#a2);}
	| i2:IDENTIFIER /* variable reference */
	{#expression=#(#[DIESE],#i2);}
exception catch [RecognitionException ex] {
	throw ex; }
	; 


/*expression :
	 ("new" type (POINT IDENTIFIER)? openbracket (arguments)? CLOSEBRACKET
	| CHARORSTRING 
	| NUM_INT 
	| NUM_FLOAT 
	| "self" 
	| "null" 
	| "true" 
	| "false"
	| IDENTIFIER ) (operationCall)*
exception catch [RecognitionException ex] {
	throw ex; }
	;*/

operationCall :	( simpleOperationCall
				| oclAsTypeCall	)
exception catch [RecognitionException ex] {
	throw ex; }
	;

simpleOperationCall : IDENTIFIER  openbracket (arguments)? CLOSEBRACKET
exception catch [RecognitionException ex] {
	throw ex; }
	;

oclAsTypeCall : "oclAsType" openbracket type CLOSEBRACKET
exception catch [RecognitionException ex] {
	throw ex; }
	;

arguments : expression ( COMMA expression  )*
exception catch [RecognitionException ex] {
	throw ex; }
		;

type :  IDENTIFIER (DOUBLECOLON IDENTIFIER  )*
exception catch [RecognitionException ex] {
	throw ex; }
	;
	
tag	: "tag" IDENTIFIER EQUAL taggedValue semicolon
exception catch [RecognitionException ex] {
	throw ex; }
	;
	
taggedValue	:
	  "booleantag" ("true" | "false")
	| "integertag" NUM_INT
	| "realtag" NUM_FLOAT
	| "stringtag" CHARORSTRING
/*	| "VoidTag"
	| "EnumTag" IDENTIFIER "is" type
	| ("BagTag" | "SetTag" | "SequenceTag") (taggedValue) + "end"
	| "TupleTag" (IDENTIFIER taggedValue ) + "end" */
	| "specialtag" TAGVALUE
exception catch [RecognitionException ex] {
	throw ex; }
	  ;

/* memorize the line number for each semicolon encountered */
/* produce an ANTLR AST node of token type NUM_INT with the actual line value */ 
semicolon! : SEMICOLON! {#semicolon = #[NUM_INT,lexer.getLineNumber()]; } 
	;

/* remember the line number of a new class definition */
classKeyword! : "class"! {#classKeyword = #[NUM_INT,lexer.getLineNumber()]; }
	;

/* remember the line number of the openbracket for a method definition */
openbracket! : OPENBRACKET! {#openbracket = #[NUM_INT,lexer.getLineNumber()]; }
	;

/* remember the line number of the openbrace for while,if,...instructiona */
openbrace! : OPENBRACE! {#openbrace = #[NUM_INT,lexer.getLineNumber()]; }
	;

/*==========================================*/
/*                                          */
/*              BASIC MTL LEXER             */
/*                                          */
/*==========================================*/

class BasicmtlLexer extends Lexer;
options {
	caseSensitive = true;
/*	exportVocab=BasicMtl; */ 	
	k=2;
	charVocabulary = '\3'..'\377' | '\u1000'..'\u1fff';
	testLiterals = false;
}

/*tokens { "library"; "nativelibrary"; "model"; "is";
	      "class"; "extends"; "creation"; "throwsException";
	      "return"; "while"; "if"; "else"; "throws"; "try"; "catch"; "finally"; "associate";
	      "new"; "self"; "null"; "true"; "false";
	      "oclAsType"; DOUBLECOLON;
	      "tag"; "booleantag"; "integertag"; "realtag"; "stringtag"; "specialtag";
	       NUM_FLOAT;
}*/

{ public int lineNumber=1;
  public String getLineNumber()
   { return Integer.toString(this.lineNumber); }
}

WS	:	(' '
	|	'\t'
	|	'\n' {newline();
	          this.lineNumber++; }
	|	'\r')
		{ _ttype = Token.SKIP; }
	;

// Single-line comments
SL_COMMENT
	:	"//"
		(~('\n'|'\r'))* ('\n'|'\r'('\n')?)
		{$setType(Token.SKIP); newline();}
	;

// multiple-line comments
ML_COMMENT
	:	"/*"
		(	/*	'\r' '\n' can be matched in one alternative or by matching
				'\r' in one iteration and '\n' in another.  I am trying to
				handle any flavor of newline that comes in, but the language
				that allows both "\r\n" and "\r" and "\n" to all be valid
				newline is ambiguous.  Consequently, the resulting grammar
				must be ambiguous.  I'm shutting this warning off.
			 */
			options {
				generateAmbigWarnings=false;
			}
		:
			{ LA(2)!='/' }? '*'
		|	'\r' '\n'		{newline();}
		|	'\r'			{newline();}
		|	'\n'			{newline();}
		|	~('*'|'\n'|'\r')
		)*
		"*/"
		{$setType(Token.SKIP);}
	;

// an identifier.  Note that testLiterals is set to true!  This means
// that after we match the rule, we look in the literals table to see
// if it's a literal or really an identifer
IDENTIFIER options {testLiterals=true;}
	: ( 'a'..'z'|'A'..'Z'|'_'|'+'|'-'|'*'| '/'|'!'|'|'|'&'|'%'|'$' | '<' | '>' ) ( 'a'..'z'|'A'..'Z'|'_'|'+'|'-'|'!'|'|'|'&'|'%'|'$'| '<' | '>' |'=' | SPECIAL |'0'..'9' )*
	;
	
CHARORSTRING :	'\''!
		(	ESC
		|	~('\\'|'\'')
		)*
		'\''!
	;

protected
ESC
	:	'\\'!
		(	'n' {$setText("\n");}
		|	'r' {$setText("\r");}
		|	't' {$setText("\t");}
		|	'b' {$setText("\b");}
		|	'f' {$setText("\f");}
		|	'"' {$setText("\"");}
		|	'\'' {$setText("\'");}
		|	'\\' {$setText("\\");}
		|	(
				('0'..'3')
				(
					options {
						warnWhenFollowAmbig = false;
					}
				:	('0'..'7')
					(	
						options {
							warnWhenFollowAmbig = false;
						}
					:	'0'..'7'
					)?
				)?
			|	('4'..'7')
				(
					options {
						warnWhenFollowAmbig = false;
					}
				:	('0'..'7')
				)?
			)
				{
					String s = $getText;
					int i;
					int ret = 0;
					String ans;
					for (i=0; i<s.length(); ++i)
						ret = ret*8 + s.charAt(i) - '0';
					ans = String.valueOf((char) ret);
					$setText(ans);
				}
		)
	;


// a numeric literal
NUM_INT
	:	(DIGIT)+
			(
			|	{	(	LA(1) == 'e'
					||	LA(1) == 'E'
					)
				||	(	LA(1) == '.'
					&& (	LA(2) >= '0'
						&&	LA(2) <= '9'
						)
					)
				}?
				(	'.' (DIGIT)+ (EXPONENT)?
				|	EXPONENT
				)
				{_ttype = NUM_FLOAT;}
			)
	;


// a couple protected methods to assist in matching floating point numbers
protected
EXPONENT
	:	('e'|'E') ('+'|'-')? ('0'..'9')+
	;

protected
DIGIT:
	'0'..'9'
	;

SPECIAL : 
		//For Unicode compatibility (from 0000 to ffff)
	'\u00C0' .. '\u00D6'
	|'\u00D8' .. '\u00F6'
	|'\u00F8' .. '\uFFFE'
	;

SEMICOLON : ';'
	;

OPENBRACE :	'{'
	;

CLOSEBRACE :	'}'
	;


OPENBRACKET :	'('
	;

CLOSEBRACKET :	')'
	;

POINT :	'.'
	;

TILDE :	'~'
	;

COMMA :	','
	;
	
EQUAL : '='
	;
	
DOUBLECOLON :	"::"
	;

COLON :	':'
	;

DIESE : '#'
	;

TAGVALUE :
	'['!
	(
		options {
			generateAmbigWarnings=false;
		}
	:	'\r' '\n'		{newline();}
	|	'\r'			{newline();}
	|	'\n'			{newline();}
	|	!("\\]" {$append("]");})
	|	~(']'|'\n'|'\r')
	)*
	']'!
	;
 
	  
/* $Header: /tmp/cvs2svn/cvsroot/BasicMTL_projects/BasicMtlAntlr/ANTLRParser/basicmtl.g,v 1.1 2003-07-10 07:48:02 jpthibau Exp $ */

header {
package ANTLRParser;

}

{
import java.io.*;
import java.util.*;
import org.apache.log4j.Logger;
import org.apache.log4j.LogManager;
import org.apache.log4j.xml.DOMConfigurator;
import ANTLRASTBuilder.*;



class BMTL {
static DataInputStream input;

public static final org.apache.log4j.Logger log = Logger.getLogger("BMTLParser");

public static org.apache.log4j.Logger getLog () {
		return BMTL.log;
}

public static Object Parse(String name,ANTLRASTBuilderInterface aBuilder)
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
		builtTree=walker.basicMTL(parser.getAST(),aBuilder);
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
{	DummyBuilder aBuilder=new DummyBuilder();
	try {
		String filePath = new java.io.File("ThirdParty/log4j/log4j_configuration").getCanonicalPath();
		LogManager.resetConfiguration();
		DOMConfigurator.configure(filePath); }
	catch(java.io.IOException e) {
		System.err.println("Can't state log4j in BMTLParser"); }
	if (args.length > 0)
		for (int i=0;i<args.length;i++)
			try { Parse(args[i],aBuilder); }
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
	ANTLRASTBuilderInterface builder=null;
}

basicMTL[ANTLRASTBuilderInterface theBuilder] returns [Object tree=null;]
{	builder=theBuilder;
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
	    {tree=builder.library(tree,theModels,theMethods,theClasses); }
		;

headerdef returns [Object tree=null;]
{	java.util.Vector theTags=new java.util.Vector();
	Object l1=null;
	Object l2=null;
}
	: #("library" s1:IDENTIFIER (l1=inheritance)? n1:NUM_INT
	 (l2=tag {theTags.addElement(l2); } )* )
		{tree=builder.libraryHeader(n1.getText(),s1.getText(),l1,theTags); }
	| #("nativelibrary" s2:IDENTIFIER n2:NUM_INT
		( l2=tag {theTags.addElement(l2); } )* )
		{tree=builder.nativeLibHeader(n2.getText(),s2.getText(),theTags); }
	;

modelUse returns [Object tree=null;]
{String typed=null;}
	: #("Model" s1:IDENTIFIER ("is" s2:IDENTIFIER {typed=s2.getText(); })? n:NUM_INT)
	  {tree=builder.model(n.getText(),s1.getText(),typed); }
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
	{tree=builder.classDefinition(n.getText(),s1.getText(),l1,theTags,theAttributes,theMethods); } 
	;
	
inheritance returns [Object tree=null;]
	: #("extends" tree=typeList)
	 {tree = builder.inheritance(tree);}
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
      {tree=builder.attribute(l1,theTags); }
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
		{tree=builder.method(creation,s1.getText(),n.getText(),l1,l2,throwsException,theVars,theInstructions,theTags); }
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
	{tree=builder.varsDeclaration(tree,n.getText()); }
	;

varDecl returns [Object tree=null;]
{	java.util.Vector theVars=new java.util.Vector();
}
	: s1:IDENTIFIER { theVars.addElement(s1.getText()); }
	 ( COMMA s2:IDENTIFIER { theVars.addElement(s2.getText()); } )*
	 COLON tree=type
	 {tree=builder.typedVars(theVars,tree); }
	;

instruction returns [Object tree=null;]
{	java.util.Vector theInstructions=new java.util.Vector();
	java.util.Vector theElseInstructions=new java.util.Vector();
	java.util.Vector theCatchInstructions;
	java.util.Vector theCatches=new java.util.Vector();
	java.util.Vector theAssociatePoints=new java.util.Vector();
	Object l1=null;
	Object l2=null;
	Object l3=null;
	Object l4=null;
	Object l5=null;
	Object l6=null;
}
	:(tree=expression n:NUM_INT)
	  {tree=builder.expressionInstr(tree,n.getText());}
	| (s1:IDENTIFIER EQUAL tree=expression n2:NUM_INT)
	  {tree=builder.varSettingInstr(s1.getText(),tree,n2.getText());}
	| "return" (OPENBRACKET tree=expression CLOSEBRACKET)? n3:NUM_INT
	  {tree=builder.returnInstr(tree,n3.getText());}
	| "while" tree=expression n4:NUM_INT (l1=instruction {theInstructions.addElement(l1);} )* CLOSEBRACE
	  {tree=builder.whileInstr(tree,theInstructions,n4.getText());}	
	| "if" tree=expression n5:NUM_INT (l1=instruction {theInstructions.addElement(l1);} )+ CLOSEBRACE
		( "else" (l2=instruction {theElseInstructions.addElement(l2);} )+ CLOSEBRACE)?
	  {tree=builder.ifInstr(tree,theInstructions,theElseInstructions,n5.getText());}
	| "throws" tree=expression n6:NUM_INT
	  {tree=builder.throwsInstr(tree,n6.getText());}
	| "try" n7:NUM_INT ( l1=instruction {theInstructions.addElement(l1);} )+ CLOSEBRACE
	  ({theCatchInstructions=new java.util.Vector();}
	   "catch" s2:IDENTIFIER COLON l2=type n9:NUM_INT (l3=instruction {theCatchInstructions.addElement(l3);})+ CLOSEBRACE
	    { java.util.Vector v=new java.util.Vector();
	      v.addElement(s2.getText());
	      v.addElement(l2);
	      v.addElement(n9.getText());
	      v.addElement(theCatchInstructions);
	      theCatches.addElement(v); } )+ 
	  ( "finally" (l4=instruction {theElseInstructions.addElement(l4);} )+ CLOSEBRACE)? CLOSEBRACE
	  {tree=builder.tryInstr(theInstructions,theCatches,theElseInstructions,n7.getText());}
	| "associate" n8:NUM_INT l5=associateEndPoint {theAssociatePoints.addElement(l5);} 
		( COMMA l6=associateEndPoint {theAssociatePoints.addElement(l5);} )+ CLOSEBRACKET
	  {tree=builder.associateInstr(theAssociatePoints,n8.getText()); }
	;

associateEndPoint returns [Object tree=null;]
{	String role=null;
}
	: (s1:IDENTIFIER EQUAL {role=s1.getText(); })? s2:IDENTIFIER (COLON tree=type)? 
	  {tree=builder.associateEndPoint(role,s2.getText(),tree); }
	;

expression returns [Object tree=null;]
{	java.util.Vector theCalls=new java.util.Vector();
	String methodName=null;
	Object l1=null;
	Object l2=null;
}
	:  ("new" tree=type (POINT s1:IDENTIFIER {methodName=s1.getText(); })? n:NUM_INT (l1=arguments)? CLOSEBRACKET
	     {tree=builder.newExpr(tree,methodName,l1,n.getText()); }
		| s2:CHARORSTRING
		{tree=builder.stringLiteral(s2.getText()); } 
		| s3:NUM_INT 
		{tree=builder.intLiteral(s3.getText()); } 
		| s4:NUM_FLOAT 
		{tree=builder.realLiteral(s4.getText()); } 
		| "self" 
		{tree=builder.selfLiteral(); } 
		| "null" 
		{tree=builder.nullLiteral(); } 
		| "true" 
		{tree=builder.trueLiteral(); } 
		| "false"
		{tree=builder.falseLiteral(); } 
		| s5:IDENTIFIER
		{tree=builder.variableName(s5.getText()); } 
						) (l2=operationCall {theCalls.addElement(l2); })* 
		{tree=builder.expression(tree,theCalls);}
	;

operationCall returns [Object tree=null;]
{	Object l1=null;
}
	:	( l1=simpleOperationCall
		| l1=oclAsTypeCall	)
		{tree=l1; }
	;

simpleOperationCall returns [Object tree=null;]
	: POINT s1:IDENTIFIER  n:NUM_INT (tree=arguments)? CLOSEBRACKET
	{tree=builder.operationCall(s1.getText(),tree,n.getText());}
	;

oclAsTypeCall returns [Object tree=null;]
	: POINT "oclAsType" n:NUM_INT tree=type CLOSEBRACKET
	{tree=builder.oclAsType(tree,n.getText());}
	;

arguments returns [Object tree=null;]
{	java.util.Vector theExpressions=new java.util.Vector();
	Object l1=null;
}
	: l1=expression {theExpressions.addElement(l1);}
	 ( COMMA l1=expression  {theExpressions.addElement(l1);} )*
	 {tree=builder.arguments(theExpressions); }
		;


type returns [Object tree=null;]
{ java.util.Vector theNamesList=new java.util.Vector(); }
	: #(s1:IDENTIFIER {theNamesList.addElement(s1.getText());}
		("::" s2:IDENTIFIER {theNamesList.addElement(s2.getText());})* )
	{tree=builder.typeName(theNamesList); } 
		;

tag  returns [Object tree=null;]
{	Object l1=null;
}
	: "tag" s1:IDENTIFIER EQUAL l1=taggedValue n:NUM_INT
	{tree=builder.tag(n.getText(),s1.getText(),l1); } 
	;
	
taggedValue	 returns [Object tree=null;]
	:"BooleanTag" ("true" {tree=builder.boolTagValueTrue();} | "false" {tree=builder.boolTagValueFalse();})
	| "IntegerTag" s1:NUM_INT
	{tree=builder.intTagValue(s1.getText());}
	| "RealTag" s2:NUM_FLOAT
	{tree=builder.realTagValue(s2.getText());}
	| "StringTag" s3:CHARORSTRING
	{tree=builder.stringTagValue(s3.getText());}
/*	| "VoidTag"
	| "EnumTag" IDENTIFIER "is" type
	| ("BagTag" | "SetTag" | "SequenceTag") (taggedValue) + "end"
	| "TupleTag" (IDENTIFIER taggedValue ) + "end" */
	| "SpecialTag" s4:TAGVALUE
	{tree=builder.specialTagValue(s4.getText());}
	  ;
	  
/*==========================================*/
/*                                          */
/*             BASIC MTL PARSER             */
/*                                          */
/*==========================================*/

class BasicmtlParser extends Parser;
options {
	buildAST = true;
        k=2;
}
{
BasicmtlLexer lexer; }

basicMTL[BasicmtlLexer theLexer]
{ lexer = theLexer; }
	: headerdef (ModelUse)* ( classDefinition | MethodDefinition )+
	  EOF!
		;

headerdef
	: ("library" IDENTIFIER (inheritance)?
		| "nativelibrary" IDENTIFIER )
		semicolon ( tag )*
exception catch [RecognitionException ex] {
	throw ex; } 
	;

modelUse : "Model" IDENTIFIER ("is" IDENTIFIER)? semicolon
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

instruction : expression semicolon
	| IDENTIFIER EQUAL expression semicolon
	| "return" (OPENBRACKET expression CLOSEBRACKET)? semicolon
	| "while" expression openbrace (instruction)* CLOSEBRACE
	| "if" expression openbrace (instruction )+ CLOSEBRACE ( "else" OPENBRACE! (instruction)+ CLOSEBRACE )?
	| "throws" expression semicolon
	| "try" openbrace ( instruction )+
	  ("catch" IDENTIFIER COLON type openbrace (instruction )+ CLOSEBRACE )+
	  ( "finally" OPENBRACE! (instruction )+ CLOSEBRACE )? CLOSEBRACE
	| "associate" openbracket associateEndPoint ( COMMA associateEndPoint )+ CLOSEBRACKET
exception catch [RecognitionException ex] {
	throw ex; }
	;

associateEndPoint : (IDENTIFIER EQUAL)? IDENTIFIER (COLON type)?
exception catch [RecognitionException ex] {
	throw ex; }
	;

expression :
	 ("new" type (POINT IDENTIFIER)? openbracket (arguments)? CLOSEBRACKET
	| CHARORSTRING 
	| NUM_INT 
	| NUM_FLOAT 
	| "self" 
	| "null" 
	| "true" 
	| "false"
	| IDENTIFIER) (operationCall)*
exception catch [RecognitionException ex] {
	throw ex; }
	;

operationCall :	( simpleOperationCall
				| oclAsTypeCall	)
exception catch [RecognitionException ex] {
	throw ex; }
	;

simpleOperationCall : POINT IDENTIFIER  openbracket (arguments)? CLOSEBRACKET
exception catch [RecognitionException ex] {
	throw ex; }
	;

oclAsTypeCall : POINT  "oclAsType" openbracket type CLOSEBRACKET
exception catch [RecognitionException ex] {
	throw ex; }
	;

arguments : expression ( COMMA expression  )*
exception catch [RecognitionException ex] {
	throw ex; }
		;

type :  IDENTIFIER ("::" IDENTIFIER  )*
exception catch [RecognitionException ex] {
	throw ex; }
	;
	
tag	: "tag" IDENTIFIER EQUAL taggedValue semicolon
exception catch [RecognitionException ex] {
	throw ex; }
	;
	
taggedValue	:
	  "BooleanTag" ("true" | "false")
	| "IntegerTag" NUM_INT
	| "RealTag" NUM_FLOAT
	| "StringTag" CHARORSTRING
/*	| "VoidTag"
	| "EnumTag" IDENTIFIER "is" type
	| ("BagTag" | "SetTag" | "SequenceTag") (taggedValue) + "end"
	| "TupleTag" (IDENTIFIER taggedValue ) + "end" */
	| "SpecialTag" TAGVALUE
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
	k=2;
	charVocabulary = '\3'..'\377' | '\u1000'..'\u1fff';
	testLiterals = false;
}

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
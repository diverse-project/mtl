/* $Id: basicmtlParser.g,v 1.2 2003-07-11 09:09:26 jpthibau Exp $ */
header {
package ANTLRParser;

}
/*==========================================*/
/*                                          */
/*             BASIC MTL PARSER             */
/*                                          */
/*==========================================*/

class BasicmtlParser extends Parser;
options {
	buildAST = true;
	importVocab=BasicMtl;	
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

instruction : 	((IDENTIFIER POINT)? IDENTIFIER EQUAL) => (IDENTIFIER POINT)? IDENTIFIER EQUAL expression semicolon
	| expression semicolon
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
	("new") => "new" type (POINT IDENTIFIER)? openbracket (arguments)? CLOSEBRACKET (operationCall)*
	| (CHARORSTRING) => CHARORSTRING (operationCall)*
	| (NUM_INT) => NUM_INT (operationCall)*
	| (NUM_FLOAT) => NUM_FLOAT (operationCall)*
	| ("self") => "self" (operationCall)*
	| ("null") => "null" (operationCall)*
	| ("true") => "true" (operationCall)*
	| ("false") => "false" (operationCall)*
	| (type POINT IDENTIFIER OPENBRACKET) => type (operationCall)+ /*variable.method() or library.staticmethod() */
	| (IDENTIFIER POINT IDENTIFIER) => IDENTIFIER (POINT IDENTIFIER)+ /*attribute getter*/
	| IDENTIFIER /* variable reference */
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

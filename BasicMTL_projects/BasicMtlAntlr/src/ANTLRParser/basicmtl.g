/* $Id: basicmtl.g,v 1.14 2003-10-14 15:38:06 jpthibau Exp $ */
header {
package ANTLRParser;

}


{
import ANTLRASTWalker.*;
}

/*==========================================*/
/*                                          */
/*             BASIC MTL PARSER             */
/*                                          */
/*==========================================*/

class BasicmtlParser extends Parser;
options {
	buildAST = false;
/*	importVocab=BasicMtl;*/	
    k=2;
}
{
	BasicmtlLexer lexer=null;
	ANTLRWalkerActionsInterface walker=null;
}

/*===============================================================
basicMTL
{ lexer = theLexer; }
	: headerdef modelUse ( associationDefinition | classDefinition | methodDefinition )+
	  EOF!
==================================================================*/
basicMTL[BasicmtlLexer theLexer,ANTLRWalkerActionsInterface theWalker] returns [Object tree=null;]
{	lexer = theLexer;
	walker=theWalker;
	java.util.Vector theModels=new java.util.Vector();
	java.util.Vector theAssociations=new java.util.Vector();
	java.util.Vector theClasses=new java.util.Vector();
	java.util.Vector theMethods=new java.util.Vector();
	Object l1=null;
	Object l2=null;
	Object l3=null;
	Object l4=null;
}
	: tree=headerdef
	  (l1=modelUse {theModels.addElement(l1); })*
	  ( l4=associationDefinition {theAssociations.addElement(l4); }
	   | l2=classDefinition {theClasses.addElement(l2); }
	   | l3=methodDefinition {theMethods.addElement(l3); } )+
	    {tree=walker.library(tree,theModels,theMethods,theClasses); }
exception catch [RecognitionException ex] {
	throw ex; }
		;

/*===============================================================
headerdef
	: libheader semicolon ( tag )*
==================================================================*/
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
/*===============================================================
libheader
	: "library"^ IDENTIFIER (inheritance)?
	| "nativelibrary"^ IDENTIFIER
==================================================================*/
libheader returns [Object tree=null;]
{	Object l1=null;
}
	: "library" s1:IDENTIFIER (l1=inheritance)? 
		{ tree=walker.bmtllibraryHeader(s1.getText(),l1); }
	| "nativelibrary" s2:IDENTIFIER 
	{ tree=walker.nativeLibHeader(s2.getText()); }
exception catch [RecognitionException ex] {
	throw ex; } 
	;

/*===============================================================
modelUse : ("model" IDENTIFIER (COLON (IDENTIFIER | "RepositoryModel"))? semicolon)*
==================================================================*/
modelUse returns [Object tree=null;]
{String n;
 String typed=null;
}
	: "model" s1:IDENTIFIER (COLON (s2:IDENTIFIER {typed=s2.getText(); } | "RepositoryModel" {typed="RepositoryModel"; }))? n=semicolon
	  { tree=walker.model(n,s1.getText(),typed); }
exception catch [RecognitionException ex] {
	throw ex; }
	;
/*===============================================================
associationDefinition
	: associationKeyword (IDENTIFIER)?
	(tag )*
	 OPENBRACE ( endPointDef )* CLOSEBRACE 
==================================================================*/
associationDefinition returns [Object tree=null;]
{	java.util.Vector theTags=new java.util.Vector();
	java.util.Vector theEndPoints=new java.util.Vector();
	String n;
	String name=null;
	Object l1=null;
	Object l2=null;
}
	: n=associationKeyword (s1:IDENTIFIER {name=s1.getText();} )?
	(l1=tag {theTags.addElement(l1); } )*
	OPENBRACE ( l2=endPointDef {theEndPoints.addElement(l2); } )*
	CLOSEBRACE 
	{tree=walker.associationDefinition(n,name,theTags,theEndPoints); } 
exception catch [RecognitionException ex] {
	throw ex; }
	;
	

/*===============================================================
endPointDef : (IDENTIFIER)? COLON IDENTIFIER (multiplicity)? ("composition" | "aggregation" | "ordered" | "notNavigable")* semicolon
                (tag )*
==================================================================*/
endPointDef returns [Object tree=null;]
{	java.util.Vector theTags=new java.util.Vector();
	String n;
	String name=null;
	boolean isComposition=false;
	boolean isAggregation=false;
	boolean isOrdered=false;
	boolean isNavigable=true;
	Object l1=null;
	Object l2=null;
}
	: (s1:IDENTIFIER {name=s1.getText();} )? COLON s2:IDENTIFIER (l1=multiplicityDef)?
	  ("composition" {isComposition=true;} | "aggregation" {isAggregation=true;} | "ordered" {isOrdered=true;} | "notNavigable" {isNavigable=false;})*
      (l2=tag {theTags.addElement(l2); } )*
      n=semicolon
      {tree=walker.endPoint(n,name,s2.getText(),l1,isComposition,isAggregation,isOrdered,isNavigable,theTags); }
exception catch [RecognitionException ex] {
	throw ex; }
	;

/*===============================================================
multiplicityDef :  ( NUM_INT | OPENBRACKET NUM_INT NUM_INT CLOSEBRACKET )
==================================================================*/
multiplicityDef returns [Object tree=null;]
{	String lower=null;
	String upper=null;
}
	: (s1:NUM_INT {lower=s1.getText(); upper=s1.getText();}
	 | OPENBRACKET s2:NUM_INT s3:NUM_INT CLOSEBRACKET { lower=s2.getText(); upper=s3.getText();} )
      {tree=walker.multiplicity(lower,upper); }
exception catch [RecognitionException ex] {
	throw ex; }
	;

/*===============================================================
classDefinition
	: classKeyword IDENTIFIER (inheritance)? (refinement)?
	(tag )*
	 OPENBRACE ( attributesDef )* ( getSetDef )* ( methodDefinition )* CLOSEBRACE 
==================================================================*/
classDefinition returns [Object tree=null;]
{	java.util.Vector theTags=new java.util.Vector();
	java.util.Vector theAttributes=new java.util.Vector();
	java.util.Vector theGettersSetters=new java.util.Vector();
	java.util.Vector theMethods=new java.util.Vector();
	String n;
	Object l1=null;
	Object l2=null;
	Object l3=null;
	Object l4=null;
	Object l5=null;
	Object l6=null;
}
	: n=classKeyword s1:IDENTIFIER (l1=inheritance)? (l5=refinement)?
	(l2=tag {theTags.addElement(l2); } )*
	OPENBRACE ( l3=attributesDef {theAttributes.addElement(l3); } )*
	( l6=getSetDef {theGettersSetters.addElement(l6); } )*
	( l4=methodDefinition {theMethods.addElement(l4); } )* CLOSEBRACE 
	{tree=walker.classDefinition(n,s1.getText(),l1,l5,theTags,theAttributes,theGettersSetters,theMethods); } 
exception catch [RecognitionException ex] {
	throw ex; }
	;
	
/*===============================================================
inheritance : "extends" typeList
==================================================================*/
inheritance returns [Object tree=null;]
	: "extends" tree=typeList
	 {tree = walker.inheritance(tree);}
exception catch [RecognitionException ex] {
	throw ex; }
	;

/*===============================================================
refinement : "refines" typeList
==================================================================*/
refinement returns [Object tree=null;]
	: "refines" tree=typeList
	 {tree = walker.inheritance(tree);}
exception catch [RecognitionException ex] {
	throw ex; }
	;

/*===============================================================
typeList : type ( COMMA type )*
==================================================================*/
typeList returns [Object tree=null;]
{ java.util.Vector theTypesList=new java.util.Vector(); }
	: tree=type {theTypesList.addElement(tree); }
	  (COMMA tree=type {theTypesList.addElement(tree); } )*
	 {tree=theTypesList; }
exception catch [RecognitionException ex] {
	throw ex; }
	;

/*===============================================================
attributesDef : localVarDef
                (tag )*
==================================================================*/
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

/*===============================================================
getSetDef : ( "getter" | "setter" ) IDENTIFIER "is" IDENTIFIER semicolon

==================================================================*/
getSetDef returns [Object tree=null;]
{	boolean isGetter=true;
}
	:( "getter" | "setter" {isGetter=false; }) s1:IDENTIFIER "is" s2:IDENTIFIER SEMICOLON
	{tree=walker.setterGetter(isGetter,s1.getText(),s2.getText()); }
exception catch [RecognitionException ex] {
	throw ex; }
	;
/*===============================================================
methodDefinition : ("creation")? IDENTIFIER 
		openbracket ( parameterdef )? CLOSEBRACKET (COLON type)?
		( "throwsException" )?
		( tag )*
		OPENBRACE (localVarDef )* (instruction)* CLOSEBRACE
==================================================================*/
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

/*===============================================================
parameterdef : varDecl ( SEMICOLON varDecl )*
==================================================================*/
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

/*===============================================================
localVarDef : varDecl semicolon
==================================================================*/
localVarDef returns [Object tree=null;]
{String n;}
	: tree=varDecl n=semicolon
	{tree=walker.varsDeclaration(tree,n); }
exception catch [RecognitionException ex] {
	throw ex; }
	;

/*===============================================================
varDecl : IDENTIFIER  ( COMMA IDENTIFIER )* COLON type
==================================================================*/
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

/*===============================================================
instruction
	 : ((IDENTIFIER POINT)? IDENTIFIER RECEIVES) => (IDENTIFIER POINT)? IDENTIFIER RECEIVES expression semicolon
	| expression n:semicolon
	| "return" (OPENBRACKET expression CLOSEBRACKET)? semicolon
	| "while" expression openbrace (instruction)* CLOSEBRACE
	| "if" expression openbrace (instruction )+ CLOSEBRACE ( "else" OPENBRACE! (instruction)+ CLOSEBRACE )?
	| "throws" expression semicolon
	| "try" openbrace ( instruction )+
	  ("catch" IDENTIFIER COLON type openbrace (instruction )+ CLOSEBRACE )+
	  ( "finally" OPENBRACE! (instruction )+ CLOSEBRACE )? CLOSEBRACE
	| "associate" openbracket associateEndPoint ( COMMA associateEndPoint )+ CLOSEBRACKET SEMICOLON!
==================================================================*/
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
	boolean isAssociate=false;
}
	: tree=expression (r:RECEIVES l1=expression {tree = walker.affectation(l1, tree, Integer.toString(r.getLine()));})? n=semicolon
	  {tree=walker.expressionInstr(tree,n);}
	| "return" ((OPENBRACKET)? tree=expression (CLOSEBRACKET)?)? n=semicolon
	  {tree=walker.returnInstr(tree,n);}
	| "while" tree=expression n=openbrace (l1=instruction {theInstructions.addElement(l1);} )* CLOSEBRACE
	  {tree=walker.whileInstr(tree,theInstructions,n);}	
	| "if" (OPENBRACKET)? tree=expression (CLOSEBRACKET)? n=openbrace (l1=instruction {theInstructions.addElement(l1);} )+ CLOSEBRACE
		( "else" OPENBRACE (l2=instruction {theElseInstructions.addElement(l2);} )+ CLOSEBRACE)?
	  {tree=walker.ifInstr(tree,theInstructions,theElseInstructions,n);}
	| ("throws"|"throw") (OPENBRACKET)? tree=expression (CLOSEBRACKET)?  n=semicolon
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
	  ( "finally" OPENBRACE (l4=instruction {theElseInstructions.addElement(l4);} )+ CLOSEBRACE)? CLOSEBRACE
	  {tree=walker.tryInstr(theInstructions,theCatches,theElseInstructions,n);}
	| ("dissociate" | "associate" {isAssociate=true; } ) n=openbracket l5=associateEndPoint {theAssociatePoints.addElement(l5);} 
		( COMMA l6=associateEndPoint {theAssociatePoints.addElement(l6);} )+ CLOSEBRACKET SEMICOLON
	  {tree=walker.associateInstr(isAssociate,theAssociatePoints,n); }
exception catch [RecognitionException ex] {
	throw ex; }
	;

/*===============================================================
associateEndPoint : (IDENTIFIER RECEIVES)? expression (COLON type)?
==================================================================*/
associateEndPoint returns [Object tree=null;]
{	String role=null;
	Object t=null;
}
	: (s1:IDENTIFIER RECEIVES {role=s1.getText(); })? tree=expression (COLON t=type)? 
	  {tree=walker.associateEndPoint(role,tree,t); }
exception catch [RecognitionException ex] {
	throw ex; }
	;

/*===============================================================
expression :
	 "JavaCode" TAGVALUE
	| "new" type (POINT IDENTIFIER)? openbracket (arguments)? CLOSEBRACKET (propertyCall)*
	| CHARORSTRING (propertyCall)*
	| NUM_INT (propertyCall)*
	| NUM_FLOAT (propertyCall)*
	| EXCLAM type EXCLAM (propertyCall)*
	| "self" (propertyCall)*
	| "null" (propertyCall)*
	| "true" (propertyCall)*
	| "false" (propertyCall)*
	| (type POINT IDENTIFIER OPENBRACKET) => type (POINT propertyCall)+ //variable.method() or library.staticmethod() 
	| (IDENTIFIER OPENBRACKET) => (propertyCall)+ //direct operation call
	| (IDENTIFIER POINT IDENTIFIER) => IDENTIFIER (POINT IDENTIFIER)+ //attribute getter
	| IDENTIFIER // variable reference 
==================================================================*/
expression returns [Object tree=null;]
{	java.util.Vector theCalls=new java.util.Vector();
	java.util.Vector theAttributes=new java.util.Vector();
	String n;
	String methodName=null;
	Object l1=null;
	Object l2=null;
}
:
	 "JavaCode" s5:IDENTIFIER
		{tree=walker.javaCodeLiteral(s5.getText()); }
	| "new" tree=type (POINT s1:IDENTIFIER {methodName=s1.getText(); })? n=openbracket /*(l1=arguments)?*/ CLOSEBRACKET  (POINT l2=propertyCall {theCalls.addElement(l2); })*
		{tree=walker.newExpr(tree,methodName,l1,n,theCalls); }
	| s2:CHARORSTRING (POINT l2=propertyCall {theCalls.addElement(l2); })*
		{tree=walker.stringLiteral(s2.getText(),theCalls); }
	| s3:NUM_INT (POINT l2=propertyCall {theCalls.addElement(l2); })*
		{tree=walker.intLiteral(s3.getText(),theCalls); }
	| s4:NUM_FLOAT (POINT l2=propertyCall {theCalls.addElement(l2); })*
		{tree=walker.realLiteral(s4.getText(),theCalls); }
	| EXCLAM tree=type EXCLAM (POINT l2=propertyCall {theCalls.addElement(l2); })*
		{tree=walker.oclTypeLiteral(tree,theCalls); }
	| ("self"|"this") (POINT l2=propertyCall {theCalls.addElement(l2); })*
		{tree=walker.selfLiteral(theCalls); }
	| "null" (POINT l2=propertyCall {theCalls.addElement(l2); })*
		{tree=walker.nullLiteral(theCalls); }
	| "true" (POINT l2=propertyCall {theCalls.addElement(l2); })* 
		{tree=walker.trueLiteral(theCalls); }
	| "false" (POINT l2=propertyCall {theCalls.addElement(l2); })* 
		{tree=walker.falseLiteral(theCalls); }
	| id:IDENTIFIER (POINT l2=propertyCall {theCalls.addElement(l2); })* /*variable.method() or library.staticmethod() */
		{tree=walker.attributeOrVariable(id.getText(),theCalls); }
	| l1=operationCall {theCalls.addElement(l1);} (POINT l2=propertyCall {theCalls.addElement(l2); })*
		{tree=walker.selfLiteral(theCalls); }
exception catch [RecognitionException ex] {
	throw ex; }
	; 

/*===============================================================
propertyCall :	( attributeCall
				| operationCall	)
==================================================================*/
propertyCall returns [Object tree=null;]
{	Object l1=null;
}
	:	( l1=attributeCall
		| l1=operationCall	)
		{tree=l1; }
exception catch [RecognitionException ex] {
	throw ex; }
	;

/*===============================================================
operationCall :	( simpleOperationCall
				| oclAsTypeCall	)
==================================================================*/
operationCall returns [Object tree=null;]
{	Object l1=null;
}
	:	( l1=simpleOperationCall
		| l1=oclAsTypeCall	)
		{tree=l1; }
exception catch [RecognitionException ex] {
	throw ex; }
	;

/*===============================================================
simpleOperationCall : IDENTIFIER  openbracket (arguments)? CLOSEBRACKET
==================================================================*/
simpleOperationCall returns [Object tree=null;]
{ String n; }
	: s1:IDENTIFIER  n=openbracket (tree=arguments)? CLOSEBRACKET
	{tree=walker.operationCall(s1.getText(),tree,n);}
exception catch [RecognitionException ex] {
	throw ex; }
	;

/*===============================================================
attributeCall : IDENTIFIER
==================================================================*/
attributeCall returns [Object tree=null;]
{ String n; }
	: s1:IDENTIFIER
	{tree=walker.attributeGetter(s1.getText());}
exception catch [RecognitionException ex] {
	throw ex; }
	;

/*===============================================================
oclAsTypeCall : "oclAsType" openbracket type CLOSEBRACKET
==================================================================*/
oclAsTypeCall returns [Object tree=null;]
{ String n;
	boolean isConstant=true;
	String theType=null;
	String theMethod=null;
	String theParameter=null; }
	: "oclAsType" n=openbracket
	(id1:IDENTIFIER COMMA id2:IDENTIFIER COMMA id3:IDENTIFIER COMMA { theType=id1.getText(); theMethod=id2.getText(); theParameter=id3.getText(); isConstant=false; }) ?
	EXCLAM tree=type EXCLAM CLOSEBRACKET
	{tree=walker.oclAsType(tree,n,theType,theMethod,theParameter,isConstant);}
exception catch [RecognitionException ex] {
	throw ex; }
	;

/*===============================================================
arguments : expression ( COMMA expression  )*
==================================================================*/
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


/*===============================================================
type :  (IDENTIFIER (DOUBLECOLON IDENTIFIER  )* | "RepositoryModel")
==================================================================*/
type returns [Object tree=null;]
{ java.util.Vector theNamesList=new java.util.Vector(); }
	: (s1:IDENTIFIER {theNamesList.addElement(s1.getText());}
		(DOUBLECOLON s2:IDENTIFIER {theNamesList.addElement(s2.getText());})*
		| "RepositoryModel" {theNamesList.addElement("RepositoryModel");} ) 
	{tree=walker.typeName(theNamesList); } 
exception catch [RecognitionException ex] {
	throw ex; }
		;

/*===============================================================
tag	: "tag" IDENTIFIER RECEIVES taggedValue semicolon
==================================================================*/
tag  returns [Object tree=null;]
{	String n;
	Object l1=null;
}
	: "tag" s1:IDENTIFIER RECEIVES l1=taggedValue n=semicolon
	{tree=walker.tag(n,s1.getText(),l1); } 
exception catch [RecognitionException ex] {
	throw ex; }
	;
	
/*===============================================================
taggedValue	:
	  "booleantag" ("true" | "false")
	| "integertag" NUM_INT
	| "realtag" NUM_FLOAT
	| "stringtag" CHARORSTRING
//	| "VoidTag"
//	| "EnumTag" IDENTIFIER "is" type
//	| ("BagTag" | "SetTag" | "SequenceTag") (taggedValue) + "end"
//	| "TupleTag" (IDENTIFIER taggedValue ) + "end" 
	| "specialtag" TAGVALUE
==================================================================*/
taggedValue	 returns [Object tree=null;]
	:"booleantag"
			 ("true" {tree=walker.boolTagValueTrue();}
			| "false" {tree=walker.boolTagValueFalse();})
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
	| "specialtag" s4:IDENTIFIER
	{tree=walker.specialTagValue(s4.getText());}
exception catch [RecognitionException ex] {
	throw ex; }
	;
/*===============================================================
semicolon : SEMICOLON
==================================================================*/
/* memorize the line number for each semicolon encountered */
/* produce an ANTLR AST node of token type NUM_INT with the actual line value */ 
semicolon returns [String number=null;]
	: s:SEMICOLON {number=Integer.toString(s.getLine()); } 
	;

/*===============================================================
associationKeyword : "association"
==================================================================*/
/* remember the line number of a new association definition */
associationKeyword returns [String number=null;]
	: c:"association" { number=Integer.toString(c.getLine()); }
	;

/*===============================================================
classKeyword : "class"
==================================================================*/
/* remember the line number of a new class definition */
classKeyword returns [String number=null;]
	: c:"class" { number=Integer.toString(c.getLine()); }
	;

/*===============================================================
openbracket : OPENBRACKET
==================================================================*/
/* remember the line number of the openbracket for a method definition */
openbracket returns [String number=null;]
	: o:OPENBRACKET { number=Integer.toString(o.getLine()); }
	;

/*===============================================================
openbrace : OPENBRACE
==================================================================*/
/* remember the line number of the openbrace for while,if,...instructiona */
openbrace returns [String number=null;]
	: o:OPENBRACE {number=Integer.toString(o.getLine()); }
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

WS	:	(' '
	|	'\t'
	|	'\n' {newline();}
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
	: 	(( 'a'..'z'|'A'..'Z'|'_'|'$'/*|'+'|'-'|'*'| '/'|'|'|'&'|'%' | '<' | '>'*/ ) ( 'a'..'z'|'A'..'Z'|'_'|'+'|'-'|'|'|'&'|'%'|'$'| '<' | '>' |'=' | SPECIAL |'0'..'9' )*)
	|	TAGVALUE
	;
	
CHARORSTRING :	'\''!
		(
			options {
				generateAmbigWarnings=false;
			}
		:	ESC
		|	'\r' '\n'		{newline();}
		|	'\r'			{newline();}
		|	'\n'			{newline();}
		|	~('\\'|'\''|'\r'|'\n')
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
	:	('-')?(DIGIT)+
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

RECEIVES : ":="
	;
	
DOUBLECOLON :	"::"
	;

COLON :	':'
	;

DIESE : '#'
	;

EXCLAM : '!'
	;

protected
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
 
	  
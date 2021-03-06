/* $Id: basicmtl.g,v 1.28 2004-11-29 09:31:09 edrezen Exp $ 			*/
/*															 			*/
/* Copyright 2004 - INRIA - LGPL license 					 			*/
/* This is the parser of the BasicMTL syntax. It uses an ANTLRASTWalker */
/* in order to do the concrete actions.									*/
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
	java.util.Vector packagesStack=new java.util.Vector();
	
	public Object addPacksPrefix(Object qn)
	{	java.util.Vector result=new java.util.Vector();
		for (int i=0;i<packagesStack.size();i++)
			result.addAll((java.util.Vector)packagesStack.get(i));
		result.addAll((java.util.Vector)qn);
		return result;
	}
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
	Object pack=null;
}
	: tree=headerdef
	  (l1=modelUse {theModels.addElement(l1); })*
	  ( pack=packageDefinition {	for (int results=0;results < ((java.util.Vector)pack).size();results++)
	  									{	java.util.Vector aResult=(java.util.Vector)((java.util.Vector)pack).get(results);
	  										if ("ClassDefinition".equals((String)aResult.get(0)))
	  											theClasses.addElement(aResult.get(1));
	  										else theAssociations.addElement(aResult.get(1)); }
	  							}
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
	: ( "library" | "model" ) type (inheritance)?
	| "nativelibrary"^ type
==================================================================*/
libheader returns [Object tree=null;]
{	Object l1=null;
	Object s;
}
	: ("library" | "model" ) s=type /*{ this.packagesStack.addElement(s); }*/ (l1=inheritance)? 
		{ tree=walker.bmtllibraryHeader(s,l1); }
/*	| "nativelibrary" s=type 
	{ tree=walker.nativeLibHeader(s); } no more implemented,should disappear ?*/
exception catch [RecognitionException ex] {
	throw ex; } 
	;

/*===============================================================
modelUse : ("model" ident (COLON (ident | "RepositoryModel"))? semicolon)*
==================================================================*/
modelUse returns [Object tree=null;]
{String n;
 Token s1 = null, s2 = null;
 String typed=null;
}
	: "model" s1=ident (COLON (s2=ident {typed=s2.getText(); } | "RepositoryModel" {typed="RepositoryModel"; }))? n=semicolon
	  { tree=walker.model(n,s1.getText(),typed); }
exception catch [RecognitionException ex] {
	throw ex; }
	;
/*===============================================================
packageDefinition
	: "package" type OPENBRACE (associationDefinition | classDefinition)+ CLOSEBRACE
	   | 
==================================================================*/
packageDefinition returns [Object tree=null;]
{	java.util.Vector theResult=new java.util.Vector();
	java.util.Vector theResultsList=new java.util.Vector();
	Object t=null;
	Object l1=null;
	Object l2=null;
}
	: ( "package" t=type { this.packagesStack.addElement(t); } OPENBRACE (tree=packageDefinition {theResultsList.addAll((java.util.Vector)tree); tree=theResultsList; })+ CLOSEBRACE { this.packagesStack.removeElementAt(packagesStack.size()-1); } 
	   | l1=associationDefinition {	theResult.addElement("AssociationDefinition");
	   								theResult.addElement(l1);
	   								theResultsList.addElement(theResult);
	   								tree=theResultsList; }
	   | l2=classDefinition {	theResult.addElement("ClassDefinition");
	   							theResult.addElement(l2);
	   							 theResultsList.addElement(theResult);
	   							 tree=theResultsList; } )
exception catch [RecognitionException ex] {
	throw ex; }
		;

/*===============================================================
associationDefinition
	: associationKeyword (ident)?
	(tag )*
	 OPENBRACE ( endPointDef )* CLOSEBRACE 
==================================================================*/
associationDefinition returns [Object tree=null;]
{	java.util.Vector theTags=new java.util.Vector();
	java.util.Vector theEndPoints=new java.util.Vector();
	String n;
	Object l1=null;
	Object l2=null;
	Object s1 = null;
}
	: n=associationKeyword (s1=type { s1=this.addPacksPrefix(s1); })?
	(l1=tag {theTags.addElement(l1); } )*
	OPENBRACE ( l2=endPointDef {theEndPoints.addElement(l2); } )*
	CLOSEBRACE 
	{tree=walker.associationDefinition(n,s1,theTags,theEndPoints); } 
exception catch [RecognitionException ex] {
	throw ex; }
	;
	

/*===============================================================
endPointDef : (ident)? COLON ident (multiplicity)? ("composition" | "aggregation" | "ordered" | "notNavigable")* semicolon
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
	Token s1 = null, s2 = null;
}
	: (s1=ident {name=s1.getText();} )? COLON s2=ident (l1=multiplicityDef)?
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
	: ("abstract")? classKeyword type (inheritance)? (refinement)?
	(tag )*
	 OPENBRACE ( attributesDef )* ( getSetDef )* ( methodDefinition )* CLOSEBRACE 
==================================================================*/
classDefinition returns [Object tree=null;]
{	java.util.Vector theTags=new java.util.Vector();
	java.util.Vector theAttributes=new java.util.Vector();
	java.util.Vector theGettersSetters=new java.util.Vector();
	java.util.Vector theMethods=new java.util.Vector();
	String n;
	boolean isAbstract=false;
	Object l1=null;
	Object l2=null;
	Object l3=null;
	Object l4=null;
	Object l5=null;
	Object l6=null;
	Object s1 = null;
}
	: (abstractKeyword {isAbstract=true;})? n=classKeyword s1=type { s1=this.addPacksPrefix(s1); } (l1=inheritance)? (l5=refinement)?
	(l2=tag {theTags.addElement(l2); } )*
	OPENBRACE ( l3=attributesDef {theAttributes.addElement(l3); } )*
	( l6=getSetDef {theGettersSetters.addElement(l6); } )*
	( l4=methodDefinition {theMethods.addElement(l4); } )* CLOSEBRACE 
	{tree=walker.classDefinition(n,isAbstract,s1,l1,l5,theTags,theAttributes,theGettersSetters,theMethods); } 
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
getSetDef : ( "getter" | "setter" ) ident "is" ident semicolon

==================================================================*/
getSetDef returns [Object tree=null;]
{	boolean isGetter=true;
	Token s1 = null, s2 = null;
}
	:( "getter" | "setter" {isGetter=false; }) s1=ident "is" s2=ident SEMICOLON
	{tree=walker.setterGetter(isGetter,s1.getText(),s2.getText()); }
exception catch [RecognitionException ex] {
	throw ex; }
	;
/*===============================================================
methodDefinition : realMethodDefinition | abstractMethodDefinition
==================================================================
methodDefinition returns [Object tree=null;]
{	Object l1=null;
	Object l2=null;
}
	:	"abstract" l1=abstractMethodDefinition"creation" {tree=l1; }
	 |  l2=realMethodDefinition"creation" {tree=l2; }
exception catch [RecognitionException ex] {
	throw ex; }
	;*/

/*===============================================================
realMethodDefinition : ("creation")? (ident | "not" | "and")
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
	boolean isAbstract=false;
	String n;
	Object l1=null;
	Object l2=null;
	Object l3=null;
	Object l4=null;
	Object l5=null;
	Token s1 = null;
	String methodName=null;
}
	:	(abstractKeyword {isAbstract=true;} )? ("creation" {creation=new String("creation"); } )?
			(s1=ident {methodName=s1.getText();} | n=notKeyword {methodName="not";}| n=andKeyword {methodName="and";}) n=openbracket
		    ( l1=parameterdef )? CLOSEBRACKET (COLON l2=type)?
			( "throwsException" {throwsException=new String("throwsException"); } )?
			( l3=tag {theTags.addElement(l3); } )*
			OPENBRACE (l4=localVarDef {theVars.addElement(l4); } )*
			(l5=instruction {theInstructions.addElement(l5); } )* CLOSEBRACE
		{tree=walker.method(creation,isAbstract,methodName,n,l1,l2,throwsException,theVars,theInstructions,theTags); }
exception catch [RecognitionException ex] {
	throw ex; }
	;

/*===============================================================
abstractMethodDefinition : ("creation")? (ident | "not" | "and")
		openbracket ( parameterdef )? CLOSEBRACKET (COLON type)?
		( "throwsException" )?
		( tag )*
==================================================================
abstractMethodDefinition returns [Object tree=null;]
{	String creation=null;
	String throwsException=null;
	java.util.Vector theTags=new java.util.Vector();
	String n;
	Object l1=null;
	Object l2=null;
	Object l3=null;
	Token s1 = null;
	String methodName=null;
}
	:	("creation" {creation=new String("creation"); } )?
			(s1=ident {methodName=s1.getText();} | n=notKeyword {methodName="not";}| n=andKeyword {methodName="and";}) n=openbracket
		    ( l1=parameterdef )? CLOSEBRACKET (COLON l2=type)?
			( "throwsException" {throwsException=new String("throwsException"); } )?
			( l3=tag {theTags.addElement(l3); } )*
		{tree=walker.method(creation,true,methodName,n,l1,l2,throwsException,null,null,theTags); }
exception catch [RecognitionException ex] {
	throw ex; }
	; */

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
varDecl : ident  ( COMMA ident )* COLON type
==================================================================*/
varDecl returns [Object tree=null;]
{	java.util.Vector theVars=new java.util.Vector();
	Token s1 = null, s2 = null;;
}
	: s1=ident { theVars.addElement(s1.getText()); }
	 ( COMMA s2=ident { theVars.addElement(s2.getText()); } )*
	 COLON tree=type
	 {tree=walker.typedVars(theVars,tree); }
exception catch [RecognitionException ex] {
	throw ex; }
	;

/*===============================================================
instruction
	 : ((ident POINT)? ident RECEIVES) => (ident POINT)? ident RECEIVES expressionWithOp semicolon
	| expressionWithOp n:semicolon
	| "return" (OPENBRACKET expressionWithOp CLOSEBRACKET)? semicolon
	| "while" expressionWithOp bodyinstr
	| ifInstruction
	| "throws" expressionWithOp semicolon
	| "try" bodyinstr
	  ("catch" ident COLON type bodyinstr )+
	  ( "finally" bodyinstr )? CLOSEBRACE
	| ("associate" | "dissociate") openbracket associateEndPoint ( COMMA associateEndPoint )+ CLOSEBRACKET SEMICOLON!
	| foreachInstruction
==================================================================*/
instruction returns [Object tree=null;]
{	java.util.Vector theCatches=new java.util.Vector();
	java.util.Vector theAssociatePoints=new java.util.Vector();
	String n;
	Object l1=null;
	Object l2=null;
	Object l3=null;
	Object l4=null;
	Object body=null;
	Object body2=null;
	Object body3=null;
	boolean isAssociate=false;
	Token s3 = null;
}
	: tree=expressionWithOp (r:RECEIVES l1=expressionWithOp {tree = walker.affectation(l1, tree, Integer.toString(r.getLine()));})? n=semicolon
	  {tree=walker.expressionInstr(tree,n);}
	| "return" (tree=expressionWithOp)? n=semicolon
	  {tree=walker.returnInstr(tree,n);}
	| "while" tree=expressionWithOp body=bodyinstr
	  {tree=walker.whileInstr(tree,body);}	
	| tree=ifInstruction
	| ("throws"|"throw") tree=expressionWithOp  n=semicolon
	  {tree=walker.throwsInstr(tree,n);}
	| "try" body=bodyinstr
	  ("catch" s3=ident COLON l2=type body2=bodyinstr
	    { java.util.Vector v=new java.util.Vector();
	      v.addElement(s3.getText());
	      v.addElement(l2);
	      v.addElement(body2);
	      theCatches.addElement(v); } )+ 
	  ( "finally" body3=bodyinstr)?
	  {tree=walker.tryInstr(body,theCatches,body3);}
	| ("dissociate" | "associate" {isAssociate=true; } ) n=openbracket l3=associateEndPoint {theAssociatePoints.addElement(l3);} 
		( COMMA l4=associateEndPoint {theAssociatePoints.addElement(l4);} )+ CLOSEBRACKET SEMICOLON
	  {tree=walker.associateInstr(isAssociate,theAssociatePoints,n); }
	| tree=foreachInstruction	  
exception catch [RecognitionException ex] {
	throw ex; };

/*===============================================================
ifInstruction
	 : 
	 "if" expressionWithOp bodyinstr ( elseInstruction )?
==================================================================*/
ifInstruction returns [Object tree=null;]
{	
	Object body=null;
	Object body2=null;
	Object body3=null;
	Object tree2=null;
}
	: 
	"if" tree=expressionWithOp body=bodyinstr ( body2=elseInstruction)?
		{tree=walker.ifInstr(tree,body,body2);}
	
exception catch [RecognitionException ex] {
	throw ex; };

/*===============================================================
elseInstruction
	 : 
	 ("else" bodyinstr) | ("elseif" expressionWithOp bodyinstr ( elseInstruction )? )
==================================================================*/
elseInstruction returns [Object tree=null;]
{	java.util.Vector theInstructions=new java.util.Vector();
	Object body=null;
	Object body2=null;
	Object tree2=null;
	String n;
}
	: 
		"else" tree=bodyinstr
	| 	n=elseifKeyword tree2=expressionWithOp body=bodyinstr ( body2=elseInstruction )?
		{	tree2=walker.ifInstr(tree2,body,body2);
			theInstructions.addElement(tree2);
			tree=walker.bodyInstr(theInstructions,n);
		}
	
exception catch [RecognitionException ex] {
	throw ex; };
	
/*===============================================================
bodyinstr
	 : openbrace (instruction)* CLOSEBRACE
==================================================================*/
bodyinstr returns [Object tree=null;]
{	java.util.Vector theInstructions=new java.util.Vector();
	String n;
	Object l1=null;
}
	: n=openbrace (l1=instruction {theInstructions.addElement(l1);} )* CLOSEBRACE
	  {tree=walker.bodyInstr(theInstructions,n);}	
exception catch [RecognitionException ex] {
	throw ex; }
	;

/*===============================================================
associateEndPoint : (ident RECEIVES)? expressionWithOp (COLON type)?
==================================================================*/
associateEndPoint returns [Object tree=null;]
{	String role=null;
	Object t=null;
	Token s1 = null;
}
	: (s1=ident RECEIVES {role=s1.getText(); })? tree=expressionWithOp (COLON t=type)? 
	  {tree=walker.associateEndPoint(role,tree,t); }
exception catch [RecognitionException ex] {
	throw ex; }
	;


/*===============================================================
expressionWithOp :
	  andExpression ("or" andExpression)+
    | andExpression
==================================================================*/
expressionWithOp returns [Object tree=null;]
{	Object expr=null;
	Token op1 = null, op2 = null;
	String lineNumber = null;
}
:	
	(andExpression ident) => tree=andExpression (op2=ident expr=andExpression)+
	  		{tree=walker.exprOpExpr(tree,op2.getText(),expr,Integer.toString(op2.getLine())); }
	 | expr=andExpression
	  		{tree=expr; }
exception catch [RecognitionException ex] {
	throw ex; };

/*===============================================================
andExpression :
	  relationalExpression ("and" relationalExpression)+
    | relationalExpression
==================================================================*/
andExpression returns [Object tree=null;]
{	Object expr=null;
	Token op1 = null, op2 = null;
	String lineNumber = null;
}
:
	 (relationalExpression andKeyword) => tree=relationalExpression (lineNumber=andKeyword expr=relationalExpression)+
	  		{tree=walker.exprOpExpr(tree,"and",expr,lineNumber); }
	 | expr=relationalExpression
	  		{tree=expr; }
exception catch [RecognitionException ex] {
	throw ex; };
	
		
/*===============================================================
relationalExpression :
	  addingExpression EQUALS addingExpression
    | addingExpression NOT_EQUALS addingExpression
    | addingExpression GT addingExpression
    | addingExpression GTE addingExpression
    | addingExpression LT addingExpression
    | addingExpression LTE addingExpression
    | addingExpression
==================================================================*/
relationalExpression returns [Object tree=null;]
{	Object expr=null;
	Token op1 = null, op2 = null;
	String lineNumber = null;
}
:
	 (addingExpression EQUALS) => tree=addingExpression lineNumber=equalsKeyword expr=addingExpression
	  		{ tree=walker.exprOpExpr(tree,"=",expr,lineNumber); }
	 | (addingExpression NOT_EQUALS) => tree=addingExpression lineNumber=notequalsKeyword expr=addingExpression
	  		{ tree=walker.exprOpExpr(tree,"<>",expr,lineNumber); } 
	 | (addingExpression GT) => tree=addingExpression lineNumber=gtKeyword expr=addingExpression
	  		{ tree=walker.exprOpExpr(tree,">",expr,lineNumber); }
	 | (addingExpression GTE) => tree=addingExpression lineNumber=gteKeyword expr=addingExpression
	  		{ tree=walker.exprOpExpr(tree,">=",expr,lineNumber); } 
	 | (addingExpression LT) => tree=addingExpression lineNumber=ltKeyword expr=addingExpression
	  		{ tree=walker.exprOpExpr(tree,"<",expr,lineNumber); }
	 | (addingExpression LTE) => tree=addingExpression lineNumber=lteKeyword expr=addingExpression
	  		{ tree=walker.exprOpExpr(tree,"<=",expr,lineNumber); } 
	 | expr=addingExpression
	  		{tree=expr; }
exception catch [RecognitionException ex] {
	throw ex; };
	
/*===============================================================
addingExpression:	
    multiplyingExpression PLUS addingExpression
   		//  | multiplyingExpression MINUS multiplyingExpression // PROBLEM with the MINUS !!!
    | multiplyingExpression
==================================================================*/
addingExpression returns [Object tree=null;]
{	Object expr=null;
	Token op1 = null, op2 = null;
	String lineNumber = null;
}
:
	 (multiplyingExpression PLUS) => tree=multiplyingExpression lineNumber=plusKeyword expr=addingExpression
	  		{ tree=walker.exprOpExpr(tree,"+",expr,lineNumber); }
	 | expr=multiplyingExpression
	  		{tree=expr; }
exception catch [RecognitionException ex] {
	throw ex; };
	
/*===============================================================
multiplyingExpression:	
    booleanNegationExpression MULT multiplyingExpression
    | booleanNegationExpression DIV multiplyingExpression
    | booleanNegationExpression
==================================================================*/
multiplyingExpression returns [Object tree=null;]
{	Object expr=null;
	Token op1 = null, op2 = null;
	String lineNumber = null;
}
:
	 (booleanNegationExpression MULT) => tree=booleanNegationExpression lineNumber=multKeyword expr=multiplyingExpression
	  		{ tree=walker.exprOpExpr(tree,"*",expr,lineNumber); }
	 | (booleanNegationExpression DIV) => tree=booleanNegationExpression lineNumber=divKeyword expr=multiplyingExpression
	  		{ tree=walker.exprOpExpr(tree,"/",expr,lineNumber); }
	 | expr=booleanNegationExpression
	  		{tree=expr; }
exception catch [RecognitionException ex] {
	throw ex; };
		 
/*===============================================================
booleanNegationExpression:
	  
	"not" expression
    | expression
==================================================================*/
booleanNegationExpression returns [Object tree=null;]
{	Object expr=null;
	Token op1 = null, op2 = null;
	String lineNumber = null;
}
:
	 lineNumber=notKeyword tree=expression
		{tree=walker.negateExpr(tree,lineNumber); }
	 | expr=expression
	  		{tree=expr; }
exception catch [RecognitionException ex] {
	throw ex; };
	
/*===============================================================
expression :
	  OPENBRACKET expressionWithOp CLOSEBRACKET
    | singleexpr
==================================================================*/
expression returns [Object tree=null;]
{	Object expr=null;
	Token op1 = null, op2 = null;
	String lineNumber = null;
}
:
	 (OPENBRACKET) => OPENBRACKET expr=expressionWithOp CLOSEBRACKET
	  		{tree=expr; }
	 | expr=singleexpr
	  		{tree=expr; }
exception catch [RecognitionException ex] {
	throw ex; }; 

/*===============================================================
singleexpr :
	 "JavaCode" ident
	| "new" type (POINT ident)? openbracket CLOSEBRACKET  (POINT propertyCall )*
	| CHARORSTRING (POINT propertyCall )*
	| NUM_INT (POINT propertyCall )*
	| NUM_FLOAT (POINT propertyCall )*
	| EXCLAM type EXCLAM (POINT propertyCall )*
	| ("self"|"this") (POINT propertyCall )*
	| "null" (POINT propertyCall )*
	| "true" (POINT propertyCall )* 
	| "false" (POINT propertyCall )* 
	| ident (POINT propertyCall )*
	| operationCall (POINT propertyCall )*
==================================================================*/
singleexpr returns [Object tree=null;]
{	java.util.Vector theCalls=new java.util.Vector();
	java.util.Vector theAttributes=new java.util.Vector();
	String n;
	String methodName=null;
	Object l1=null;
	Object l2=null;
	Token s1 = null, s5 = null, id = null;
}
:
	 "JavaCode" s5=ident
		{tree=walker.javaCodeLiteral(s5.getText()); }
	| "new" tree=type (POINT s1=ident {methodName=s1.getText(); })? n=openbracket /*(l1=arguments)?*/ CLOSEBRACKET  (POINT l2=propertyCall {theCalls.addElement(l2); })*
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
	| id=ident (POINT l2=propertyCall {theCalls.addElement(l2); })* /*variable.method() or library.staticmethod() */
		{tree=walker.attributeOrVariable(id.getText(),theCalls); }
	| l1=operationCall {theCalls.addElement(l1);} (POINT l2=propertyCall {theCalls.addElement(l2); })*
		{tree=walker.selfLiteral(theCalls); }
exception catch [RecognitionException ex] {
	throw ex; }
	; 

/*===============================================================
propertyCall :	( attributeCall
				| operationCall
				| "not" OPENBRACKET CLOSEBRACKET
				| "and" OPENBRACKET arguments CLOSEBRACKET 	)
==================================================================*/
propertyCall returns [Object tree=null;]
{	Object l1=null;
	Object l2=null;
	String n;
	
}
	:	( 
		  
		l1=attributeCall
		| l1=operationCall	
		| ("and") => n=andKeyword OPENBRACKET (l2=arguments)? CLOSEBRACKET 
				{l1=walker.operationCall("and",l2,n);}
		| n=notKeyword OPENBRACKET CLOSEBRACKET 
				{l1=walker.operationCall("not",null,n);}
		)
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
simpleOperationCall : ident  openbracket (arguments)? CLOSEBRACKET
==================================================================*/
simpleOperationCall returns [Object tree=null;]
{ String n; Token s1 = null; }
:	s1=ident n=openbracket (tree=arguments)? CLOSEBRACKET 
		{tree=walker.operationCall(s1.getText(),tree,n);}
exception catch [RecognitionException ex] {
	throw ex; }
	;

/*===============================================================
attributeCall : ident
==================================================================*/
attributeCall returns [Object tree=null;]
{ String n; Token s1 = null; }
	: s1=ident
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
	String theParameter=null;
	Token id1 = null, id2=null, id3 = null; }
	: "oclAsType" n=openbracket
	(id1=ident COMMA id2=ident COMMA id3=ident COMMA { theType=id1.getText(); theMethod=id2.getText(); theParameter=id3.getText(); isConstant=false; }) ?
	EXCLAM tree=type EXCLAM CLOSEBRACKET
	{tree=walker.oclAsType(tree,n,theType,theMethod,theParameter,isConstant);}
exception catch [RecognitionException ex] {
	throw ex; }
	;

/*===============================================================
foreachInstruction : 
"foreach" '(' varDecl ')' "in" '(' expression ')'  ("where" '('expression')')? bodyinstr  
==================================================================*/
foreachInstruction returns [Object tree=null;]
{ 
	Object s1 = null; 
	Object s2 = null; 
	Object s3 = null; 
	Object s4 = null; 
}
 :	
 	"foreach" OPENBRACKET  s1=varDecl  CLOSEBRACKET  ("in"  OPENBRACKET s2=expression CLOSEBRACKET)?  ("where" OPENBRACKET s3=expressionWithOp CLOSEBRACKET)?   s4=bodyinstr  
	{ 
		tree = walker.foreachInstr (s1,s2,s3,s4);
	}
	exception catch [RecognitionException ex] {	throw ex; }
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
type :  (ident (DOUBLECOLON ident  )* | "RepositoryModel")
==================================================================*/
type returns [Object tree=null;]
{ java.util.Vector theNamesList=new java.util.Vector(); Token s1 = null, s2 = null; }
	: (s1=ident {theNamesList.addElement(s1.getText());}
		(DOUBLECOLON s2=ident {theNamesList.addElement(s2.getText());})*
		| "RepositoryModel" {theNamesList.addElement("RepositoryModel");} ) 
	{tree=walker.typeName(theNamesList); } 
exception catch [RecognitionException ex] {
	throw ex; }
		;

/*===============================================================
tag	: "tag" ident RECEIVES taggedValue semicolon
==================================================================*/
tag  returns [Object tree=null;]
{	String n;
	Object l1=null;
	Token s1 = null;
}
	: "tag" s1=ident RECEIVES l1=taggedValue n=semicolon
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
//	| "EnumTag" ident "is" type
//	| ("BagTag" | "SetTag" | "SequenceTag") (taggedValue) + "end"
//	| "TupleTag" (ident taggedValue ) + "end" 
	| "specialtag" TAGVALUE
==================================================================*/
taggedValue	 returns [Object tree=null;] {
	Token s4 = null;
}
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
	| "EnumTag" ident "is" type
	| ("BagTag" | "SetTag" | "SequenceTag") (taggedValue) + "end"
	| "TupleTag" (ident taggedValue ) + "end" */
	| "specialtag" s4=ident
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
abstractKeyword : "abstract"
==================================================================*/
abstractKeyword 
	: c:"abstract"
	;

/*===============================================================
classKeyword : "class"
==================================================================*/
/* remember the line number of a new class definition */
classKeyword returns [String number=null;]
	: c:"class" { number=Integer.toString(c.getLine()); }
	;

/*===============================================================
elseifKeyword : "elseif"
==================================================================*/
/* remember the line number of a elseif */
elseifKeyword returns [String number=null;]
	: c:"elseif" { number=Integer.toString(c.getLine()); };
	
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
/*===============================================================
notKeyword : "not"
==================================================================*/
/* remember the line number of the not */
notKeyword returns [String number=null;]
	: o:"not" {number=Integer.toString(o.getLine()); }
	  ;
/*===============================================================
andKeyword : "and"
==================================================================*/
/* remember the line number of the and */
andKeyword returns [String number=null;]
	: o:"and" {number=Integer.toString(o.getLine()); };

/*===============================================================
orKeyword : "or"
==================================================================*/
/* remember the line number of the and */
//orKeyword returns [String number=null;]
//	: o:"or" {number=Integer.toString(o.getLine()); };
		  	
/*===============================================================
equalsKeyword : EQUALS
==================================================================*/
/* remember the line number of the equals */
equalsKeyword returns [String number=null;]
	: o:EQUALS {number=Integer.toString(o.getLine()); };
	
/*===============================================================
equalsKeyword : NOT_EQUALS
==================================================================*/
/* remember the line number of the equals */
notequalsKeyword returns [String number=null;]
	: o:NOT_EQUALS {number=Integer.toString(o.getLine()); };

/*===============================================================
gtKeyword : GT
==================================================================*/
/* remember the line number of the > */
gtKeyword returns [String number=null;]
	: o:GT {number=Integer.toString(o.getLine()); };

/*===============================================================
gteKeyword : GTE
==================================================================*/
/* remember the line number of the >= */
gteKeyword returns [String number=null;]
	: o:GTE {number=Integer.toString(o.getLine()); };

/*===============================================================
ltKeyword : LT
==================================================================*/
/* remember the line number of the < */
ltKeyword returns [String number=null;]
	: o:LT {number=Integer.toString(o.getLine()); };

/*===============================================================
lteKeyword : LTE
==================================================================*/
/* remember the line number of the <= */
lteKeyword returns [String number=null;]
	: o:LTE {number=Integer.toString(o.getLine()); };

/*===============================================================
plusKeyword : PLUS
==================================================================*/
/* remember the line number of the + */
plusKeyword returns [String number=null;]
	: o:PLUS {number=Integer.toString(o.getLine()); };

/*===============================================================
multKeyword : MULT
==================================================================*/
/* remember the line number of the * */
multKeyword returns [String number=null;]
	: o:MULT {number=Integer.toString(o.getLine()); };

/*===============================================================
divKeyword : DIV
==================================================================*/
/* remember the line number of the / */
divKeyword returns [String number=null;]
	: o:DIV {number=Integer.toString(o.getLine()); };
		
/*===============================================================
ident
	:	IDENTIFIER
	|	TAGVALUE
===============================================================*/
ident returns [Token k = null;]
	:	i: IDENTIFIER {k = i;}
	|	t:TAGVALUE {k = t;}
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

// an IDENTIFIER.  Note that testLiterals is set to true!  This means
// that after we match the rule, we look in the literals table to see
// if it's a literal or really an identifer
// IDENTIFIER options {testLiterals=true;}
//	: 	(( 'a'..'z'|'A'..'Z'|'_'|'$'/*|'+'|'-'|'*'| '/'|'&'|'|' | '%' | '<' | '>'*/ ) ( 'a'..'z'|'A'..'Z'|'_'|'+'|'-'|'&'|'|'|'%'|'$'| '<' | '>' |'=' | SPECIAL |'0'..'9' )*)
//	;
IDENTIFIER options {testLiterals=true;}
	: 	(( 'a'..'z'|'A'..'Z'|'_'|'$' ) ( 'a'..'z'|'A'..'Z'|'_'|'$'| SPECIAL |'0'..'9' )*)
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
EQUALS 		: '=';
RECEIVES 	: ":=";
LT			: '<';
LTE			: "<=";
GT			: '>';
GTE			: ">=";
NOT_EQUALS	: "<>";
PLUS		: '+';
// MINUS		: '-';
MULT		: '*';
DIV			: '/';

	
DOUBLECOLON :	"::"
	;

COLON :	':'
	;

DIESE : '#'
	;

EXCLAM : '!'
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
 
	  
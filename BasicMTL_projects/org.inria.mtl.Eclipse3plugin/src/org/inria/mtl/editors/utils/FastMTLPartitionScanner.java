/*
* $Id: FastMTLPartitionScanner.java,v 1.1 2004-07-30 14:08:17 sdzale Exp $
* Authors : ${user}
*
* Created on ${date}
* Copyright 2004 - INRIA - LGPL license
*/ 
package org.inria.mtl.editors.utils;


import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.rules.ICharacterScanner;
import org.eclipse.jface.text.rules.IPartitionTokenScanner;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.Token;
import org.eclipse.jdt.internal.ui.text.BufferedDocumentScanner;


/**
 * @author sdzale
 *
 * This scanner recognizes the JavaDoc comments, MTL multi line comments, MTL single line comments,
 * MTL strings and MTL characters.
 */
public class FastMTLPartitionScanner implements IPartitionTokenScanner {

	private final static String SKIP= "__skip"; //$NON-NLS-1$	
	public final static String MTL_STRING= "__MTL_string"; //$NON-NLS-1$
	public final static String MTL_SINGLE_LINE_COMMENT= "__MTL_singleline_comment"; //$NON-NLS-1$
	public final static String MTL_MULTI_LINE_COMMENT= "__MTL_multiline_comment"; //$NON-NLS-1$
	public final static String JAVA_DOC= "__java_javadoc"; //$NON-NLS-1$ 

	// states
	private static final int MTL= 0;	
	private static final int SINGLE_LINE_COMMENT= 1;
	private static final int MULTI_LINE_COMMENT= 2;
	private static final int JAVADOC= 3;
	private static final int CHARACTER= 4;
	private static final int STRING= 5;
	
	// beginning of prefixes and postfixes
	private static final int NONE= 0;
	private static final int BACKSLASH= 1; // postfix for STRING and CHARACTER
	private static final int SLASH= 2; // prefix for SINGLE_LINE or MULTI_LINE or JAVADOC
	private static final int SLASH_STAR= 3; // prefix for MULTI_LINE_COMMENT or JAVADOC
	private static final int SLASH_STAR_STAR= 4; // prefix for MULTI_LINE_COMMENT or JAVADOC
	private static final int STAR= 5; // postfix for MULTI_LINE_COMMENT or JAVADOC
	private static final int CARRIAGE_RETURN=6; // postfix for STRING, CHARACTER and SINGLE_LINE_COMMENT
	
	/** The scanner. */
	private final BufferedDocumentScanner fScanner= new BufferedDocumentScanner(1000);	// faster implementation
	
	/** The offset of the last returned token. */
	private int fTokenOffset;
	/** The length of the last returned token. */
	private int fTokenLength;
	
	/** The state of the scanner. */	
	private int fState;
	/** The last significant characters read. */
	private int fLast;
	/** The amount of characters already read on first call to nextToken(). */
	private int fPrefixLength;
	
	// emulate MTLPartitionScanner
	private static final boolean fgEmulate= false;
	private int fMTLOffset;
	private int fMTLLength;
	
	private final IToken[] fTokens= new IToken[] {
		new Token(null),
		new Token(MTL_SINGLE_LINE_COMMENT),
		new Token(MTL_MULTI_LINE_COMMENT),
		new Token(JAVA_DOC),
		new Token(SKIP),
		new Token(MTL_STRING)
	};

	/*
	 * @see org.eclipse.jface.text.rules.ITokenScanner#nextToken()
	 */
	public IToken nextToken() {
		
		// emulate MTLPartitionScanner
		if (fgEmulate) {
			if (fMTLOffset != -1 && fTokenOffset + fTokenLength != fMTLOffset + fMTLLength) {
				fTokenOffset += fTokenLength;		
				return fTokens[MTL];	
			} else {
				fMTLOffset= -1;
				fMTLLength= 0;	
			}
		}		

		fTokenOffset += fTokenLength;
		fTokenLength= fPrefixLength;

		while (true) {
			final int ch= fScanner.read();
			
			// characters
			switch (ch) {
			case ICharacterScanner.EOF:
				if (fTokenLength > 0) {
					fLast= NONE; // ignore last
					return preFix(fState, MTL, NONE, 0);

				} else {
					fLast= NONE;
					fPrefixLength= 0;
					return Token.EOF;
				}

			case '\r':
				// emulate MTLPartitionScanner
				if (!fgEmulate && fLast != CARRIAGE_RETURN) {
						fLast= CARRIAGE_RETURN;
						fTokenLength++;
						continue;

				} else {
	 				
					switch (fState) {
					case SINGLE_LINE_COMMENT:
					case CHARACTER:
					case STRING:
						if (fTokenLength > 0) {
							IToken token= fTokens[fState];
							
							// emulate MTLPartitionScanner
							if (fgEmulate) {
								fTokenLength++;
								fLast= NONE;
								fPrefixLength= 0;
							} else {								
								fLast= CARRIAGE_RETURN;	
								fPrefixLength= 1;
							}
							
							fState= MTL;
							return token;

						} else {
							consume();
							continue;	
						}

					default:
						consume();
						continue;
					}	 				
				}
	
			case '\n': 		 		
				switch (fState) {
				case SINGLE_LINE_COMMENT:
				case CHARACTER:
				case STRING:				
					// assert(fTokenLength > 0);
					return postFix(fState);

				default:
					consume();
					continue;
				}

			default:
				if (!fgEmulate && fLast == CARRIAGE_RETURN) {			
					switch (fState) {
					case SINGLE_LINE_COMMENT:
					case CHARACTER:
					case STRING:

						int last;
						int newState;
						switch (ch) {
						case '/':
							last= SLASH;
							newState= MTL;
							break;

						case '*':
							last= STAR;
							newState= MTL;
							break;
						
						case '\'':
							last= NONE;
							newState= CHARACTER;
							break;

						case '"':
							last= NONE;
							newState= STRING;
							break;

						case '\r':
							last= CARRIAGE_RETURN;
							newState= MTL;
							break;

						case '\\':
							last= BACKSLASH;
							newState= MTL;
							break;

						default:
							last= NONE;
							newState= MTL;
							break;
						}
						
						fLast= NONE; // ignore fLast
						return preFix(fState, newState, last, 1);
	
					default:
						break;
					}
				}
			}

			// states	 
			switch (fState) {
			case MTL:
				switch (ch) {
				case '/':
					if (fLast == SLASH) {
						if (fTokenLength - getLastLength(fLast) > 0) {
							return preFix(MTL, SINGLE_LINE_COMMENT, NONE, 2);
						} else {							
							preFix(MTL, SINGLE_LINE_COMMENT, NONE, 2);
							fTokenOffset += fTokenLength;
							fTokenLength= fPrefixLength;
							break;
						}
	
					} else {
						fTokenLength++;
						fLast= SLASH;
						break;
					}
	
				case '*':
					if (fLast == SLASH) {
						if (fTokenLength - getLastLength(fLast) > 0)
							return preFix(MTL, MULTI_LINE_COMMENT, SLASH_STAR, 2);
						else {
							preFix(MTL, MULTI_LINE_COMMENT, SLASH_STAR, 2);
							fTokenOffset += fTokenLength;
							fTokenLength= fPrefixLength;
							break;
						}

					} else {
						consume();
						break;
					}
					
				case '\'':
					fLast= NONE; // ignore fLast
					if (fTokenLength > 0)
						return preFix(MTL, CHARACTER, NONE, 1);
					else {						
						preFix(MTL, CHARACTER, NONE, 1);
						fTokenOffset += fTokenLength;
						fTokenLength= fPrefixLength;
						break;
					}

				case '"':
					fLast= NONE; // ignore fLast				
					if (fTokenLength > 0)
						return preFix(MTL, STRING, NONE, 1);
					else {
						preFix(MTL, STRING, NONE, 1);
						fTokenOffset += fTokenLength;
						fTokenLength= fPrefixLength;
						break;
					}
	
				default:
					consume();
					break;
				}
				break;
	
			case SINGLE_LINE_COMMENT:
				consume();
				break;
				
			case JAVADOC:
				switch (ch) {
				case '/':
					switch (fLast) {
					case SLASH_STAR_STAR:
						return postFix(MULTI_LINE_COMMENT);
	
					case STAR:
						return postFix(JAVADOC);

					default:
						consume();
						break;
					}
					break;

				case '*':
					fTokenLength++;
					fLast= STAR;
					break;

				default:
					consume();
					break;
				}
				break;
	
			case MULTI_LINE_COMMENT:
				switch (ch) {
				case '*':
					if (fLast == SLASH_STAR) {
						fLast= SLASH_STAR_STAR;
						fTokenLength++;
						fState= JAVADOC;
					} else {
						fTokenLength++;
						fLast= STAR;
					}
					break;
	
				case '/':
					if (fLast == STAR) {
						return postFix(MULTI_LINE_COMMENT);
					} else {
						consume();
						break;
					}
	
				default:
					consume();
					break;			
				}
				break;
				
			case STRING:
				switch (ch) {
				case '\\':
					fLast= (fLast == BACKSLASH) ? NONE : BACKSLASH;
					fTokenLength++;
					break;
					
				case '\"':	 			 			
					if (fLast != BACKSLASH) {
						return postFix(STRING);

					} else {
						consume();
						break; 					
					}
		 		
				default:
					consume();
					break;
				}
				break;
	
			case CHARACTER:
				switch (ch) {
				case '\\':
					fLast= (fLast == BACKSLASH) ? NONE : BACKSLASH;
					fTokenLength++;
					break;
	
				case '\'':
					if (fLast != BACKSLASH) {
						return postFix(CHARACTER);
	
					} else {
						consume();
						break;
					}
	
				default:
					consume();
					break;
				}
				break;
			}
		} 
	}		

	private static final int getLastLength(int last) {
		switch (last) {
		default:
			return -1;

		case NONE:
			return 0;
			
		case CARRIAGE_RETURN:
		case BACKSLASH:
		case SLASH:
		case STAR:
			return 1;

		case SLASH_STAR:
			return 2;

		case SLASH_STAR_STAR:
			return 3;
		}	
	}

	private final void consume() {
		fTokenLength++;
		fLast= NONE;	
	}
	
	private final IToken postFix(int state) {
		fTokenLength++;
		fLast= NONE;
		fState= MTL;
		fPrefixLength= 0;		
		return fTokens[state];
	}

	private final IToken preFix(int state, int newState, int last, int prefixLength) {
		// emulate MTLPartitionScanner
		if (fgEmulate && state == MTL && (fTokenLength - getLastLength(fLast) > 0)) {
			fTokenLength -= getLastLength(fLast);
			fMTLOffset= fTokenOffset;
			fMTLLength= fTokenLength;
			fTokenLength= 1;
			fState= newState;
			fPrefixLength= prefixLength;
			fLast= last;
			return fTokens[state];

		} else {
			fTokenLength -= getLastLength(fLast);
			fLast= last;
			fPrefixLength= prefixLength;
			IToken token= fTokens[state];		
			fState= newState;
			return token;
		}
	}

	private static int getState(String contentType) {

		if (contentType == null)
			return MTL;

		else if (contentType.equals(MTL_SINGLE_LINE_COMMENT))
			return SINGLE_LINE_COMMENT;

		else if (contentType.equals(MTL_MULTI_LINE_COMMENT))
			return MULTI_LINE_COMMENT;

		else if (contentType.equals(JAVA_DOC))
			return JAVADOC;

		else if (contentType.equals(MTL_STRING))
			return STRING;

		else if (contentType.equals(SKIP))
			return CHARACTER;
			
		else
			return MTL;
	}

	/*
	 * @see IPartitionTokenScanner#setPartialRange(IDocument, int, int, String, int)
	 */
	public void setPartialRange(IDocument document, int offset, int length, String contentType, int partitionOffset) {

		fScanner.setRange(document, offset, length);
		fTokenOffset= partitionOffset;
		fTokenLength= 0;
		fPrefixLength= offset - partitionOffset;
		fLast= NONE;
		
		if (offset == partitionOffset) {
			// restart at beginning of partition
			fState= MTL;
		} else {
			fState= getState(contentType);			
		}

		// emulate MTLPartitionScanner
		if (fgEmulate) {
			fMTLOffset= -1;
			fMTLLength= 0;
		}
	}

	/*
	 * @see ITokenScanner#setRange(IDocument, int, int)
	 */
	public void setRange(IDocument document, int offset, int length) {

		fScanner.setRange(document, offset, length);
		fTokenOffset= offset;
		fTokenLength= 0;		
		fPrefixLength= 0;
		fLast= NONE;
		fState= MTL;

		// emulate MTLPartitionScanner
		if (fgEmulate) {
			fMTLOffset= -1;
			fMTLLength= 0;
		}
	}

	/*
	 * @see ITokenScanner#getTokenLength()
	 */
	public int getTokenLength() {
		return fTokenLength;
	}

	/*
	 * @see ITokenScanner#getTokenOffset()
	 */
	public int getTokenOffset() {
		return fTokenOffset;
	}

}

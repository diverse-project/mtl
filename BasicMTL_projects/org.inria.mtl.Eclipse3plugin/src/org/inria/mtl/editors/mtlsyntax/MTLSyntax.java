/*
* $Id: MTLSyntax.java,v 1.2 2004-08-26 12:40:58 sdzale Exp $
* Authors : ${user}
*
* Created on ${date}
* Copyright 2004 - INRIA - LGPL license
*/ 
package org.inria.mtl.editors.mtlsyntax;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jface.preference.IPreferenceStore;
import org.inria.mtl.MTLPlugin;
import org.inria.mtl.preferences.PreferencesConstants;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 * <code>MTLSyntax</code> reads MTL specifics from an XML file (eg. keywords) 
 */

public class MTLSyntax {
  //private static final String PHPDEFAULT_FILE = "default-phpsyntax.xml"; //$NON-NLS-1$
  private static final String MTLSYNTAX_FILE = "mtlsyntax.xml"; //$NON-NLS-1$
  private static final String USERSYNTAX_FILE = "usersyntax.xml"; //$NON-NLS-1$
  //private static final String USERDEFAULT_FILE = "default-usersyntax.xml"; //$NON-NLS-1$
  private static final String MTLSYNTAX_TAG = "mtlsyntax"; //$NON-NLS-1$
  private static final String KEYWORD_ATTR = "keyword"; //$NON-NLS-1$
  private static final String TYPE_ATTR = "type"; //$NON-NLS-1$
  private static final String CONSTANT_ATTR = "constant"; //$NON-NLS-1$
  private static final String FN_ATTR = "function"; //$NON-NLS-1$
  private static final String USAGE_ATTR = "usage"; //$NON-NLS-1$
  private static final String TOKENVAL_ATTR = "tokenval"; //$NON-NLS-1$
  private static IPreferenceStore store;
  private static boolean hasXMLFileBeenRead = true;

  //The following variable is used to hold the syntax from
  //the suers custom file - if that file should be changed,
  //then all entries in this variable should be removed from
  //the word list, reread from the file and then reinserted.
  private static ArrayList userdefsyntaxdata;

  private static ArrayList syntaxdata;

  public MTLSyntax() {
	// see getSyntaxData()
	syntaxdata = null;
	store = MTLPlugin.getDefault().getPreferenceStore();
  }

  public static void readInSyntax() {
	try {
	  hasXMLFileBeenRead = true;
	  /*Attempt to read the syntax file from the metadata
	   * if this does not work, create metadata from default*/
	  File syntaxFile = getSyntaxFile();
	  if (syntaxFile.exists()) {
		readFromFile(syntaxFile);
	  } else {
		readFromStream(MTLSyntax.class.getResourceAsStream(MTLSYNTAX_FILE));
		saveToFile(syntaxFile);
	  }
	  if (store == null)
	  store = MTLPlugin.getDefault().getPreferenceStore();
	  String buffer = new String(store.getString(PreferencesConstants.MTL_USERDEF_XMLFILE));
	  if (!(buffer.equals("") || buffer == null)) {
		readFromFile(buffer);
	  }
	} catch (CoreException ce) {
	  ce.printStackTrace();
	}
  }

  public static void readFromFile(String filename) {
	try {
	  readFromFile(new File(filename));
	} catch (CoreException e) {
	}
  }

  public static void readFromFile(File file) throws CoreException {
	InputStream stream = null;

	if (file.exists()) {
	  try {
		stream = new FileInputStream(file);
		readFromStream(stream);
	  } catch (IOException e) {
		throwReadException(e);
	  } finally {
		try {
		  if (stream != null) {
			stream.close();
		  }
		} catch (IOException e) {
		}
	  }
	}
  }
  public static void readFromStream(InputStream stream) throws CoreException {
	try {
	  DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	  DocumentBuilder parser = factory.newDocumentBuilder();
	  org.apache.crimson.parser.Parser2 pp;
	  Document document = parser.parse(new InputSource(stream));
	  //		Read in the Standard PHPSyntax "stuff"
	  NodeList elements = document.getElementsByTagName(MTLSYNTAX_TAG);

	  int count = elements.getLength();
	  for (int i = 0; i != count; i++) {
		Node node = elements.item(i);
		NamedNodeMap attributes = node.getAttributes();

		if (attributes == null)
		  continue;

		String Keyword = getAttributeValue(attributes, KEYWORD_ATTR);
		String Type = getAttributeValue(attributes, TYPE_ATTR);
		String method = getAttributeValue(attributes, FN_ATTR);
		String Constant = getAttributeValue(attributes, CONSTANT_ATTR);
		String usage = getAttributeValue(attributes, USAGE_ATTR);
		String Tokenval = getAttributeValue(attributes, TOKENVAL_ATTR);
      
		StringBuffer buffer = new StringBuffer();
		NodeList children = node.getChildNodes();
		for (int j = 0; j != children.getLength(); j++) {
		  String value = children.item(j).getNodeValue();
		  if (value != null)
			buffer.append(value);
		}
		String description = buffer.toString().trim();

		if (Keyword == null && Type == null && method == null && Constant == null) {
		  //ignore as it is not a valid phpsyntax tag
		} else {
		  if (Keyword != null) {
			syntaxdata.add(new MTLKeywordObject(Keyword, usage, Tokenval));
		  } else if (Type != null) {
			syntaxdata.add(new MTLTypeObject(Type, usage));
		  } else if (method != null) {
			syntaxdata.add(new MTLMethodObject(method, usage, description));
		  } else if (Constant != null) {
			syntaxdata.add(new MTLConstantObject(Constant, usage));
		  }
		}
	  }
	} catch (ParserConfigurationException e) {
	  throwReadException(e);
	} catch (IOException e) {
	  throwReadException(e);
	} catch (SAXParseException e) {
	  System.out.println("SAXParseException in line:"+e.getLineNumber()+" column:"+e.getColumnNumber());
	  throwReadException(e);
	} catch (SAXException e) {
	  throwReadException(e);
	}
  }

  public static ArrayList getSyntaxData() {
	if (syntaxdata == null) {
	  syntaxdata = new ArrayList();
	  readInSyntax();
	}
	return syntaxdata;
  }

  public static void replaceUserDefFile() {
	/*Replace the user-defined syntax file if it exists*/
	String buffer = new String(store.getString(PreferencesConstants.MTL_USERDEF_XMLFILE));
	if (!buffer.equals("") || buffer == null) {
	  readFromFile(buffer);
	}
  }

  public static ArrayList getUserSyntaxData() {
	return userdefsyntaxdata;
  }

  private static File getSyntaxFile() {
	IPath path = MTLPlugin.getDefault().getStateLocation();
	path = path.append(MTLSYNTAX_FILE);
	return path.toFile();
  }

  private static String getAttributeValue(NamedNodeMap attributes, String name) {
	Node node = attributes.getNamedItem(name);
	return node == null ? null : node.getNodeValue();
  }

  public static void saveToFile(File file) throws CoreException {
	OutputStream stream = null;
	try {
	  stream = new FileOutputStream(file);
	  saveToStream(stream);
	} catch (IOException e) {
	  throwWriteException(e);
	} finally {
	  try {
		if (stream != null)
		  stream.close();
	  } catch (IOException e) {
	  }
	}
  }

  public static void saveToStream(OutputStream stream) throws CoreException {
	try {
	  DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	  DocumentBuilder builder = factory.newDocumentBuilder();
	  Document document = builder.newDocument();
	  Node root = document.createElement("MTLStandardSyntax"); // $NON-NLS-1$ //$NON-NLS-1$
	  document.appendChild(root);
	  for (int i = 0; i != syntaxdata.size(); i++) {
		Object bufferobj = (Object) syntaxdata.get(i);
		Attr name = null;
		Node node = document.createElement(MTLSYNTAX_TAG); // $NON-NLS-1$ //$NON-NLS-1$
		root.appendChild(node);
		NamedNodeMap attributes = node.getAttributes();
		if (bufferobj instanceof MTLTypeObject)
		  name = document.createAttribute(TYPE_ATTR);
		if (bufferobj instanceof MTLKeywordObject)
		  name = document.createAttribute(KEYWORD_ATTR);
		if (bufferobj instanceof MTLMethodObject)
		  name = document.createAttribute(FN_ATTR);
		if (bufferobj instanceof MTLConstantObject)
		  name = document.createAttribute(CONSTANT_ATTR);
		name.setValue(((MTLObject) bufferobj).getName());
		attributes.setNamedItem(name);
		Attr description = document.createAttribute(USAGE_ATTR);
		description.setValue(((MTLObject) bufferobj).getUsage());
		attributes.setNamedItem(description);
		if (bufferobj instanceof MTLKeywordObject) {
		  Attr tokenval = document.createAttribute(TOKENVAL_ATTR);
		  tokenval.setValue((new Integer(((MTLKeywordObject) bufferobj).gettokenval())).toString());
		  attributes.setNamedItem(tokenval);
		}
		if (bufferobj instanceof MTLMethodObject) {
		  Text usage = document.createTextNode(((MTLMethodObject) bufferobj).getDescription());
		  node.appendChild(usage);
		 
		}
	  }
	  
		Transformer transformer=TransformerFactory.newInstance().newTransformer();
		transformer.setOutputProperty(OutputKeys.METHOD, "xml"); //$NON-NLS-1$
		transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8"); //$NON-NLS-1$
		DOMSource source = new DOMSource(document);
		StreamResult result = new StreamResult(stream);

		transformer.transform(source, result);
		
	} catch (ParserConfigurationException e) {
	  throwWriteException(e);
	} catch (TransformerException e) {
	throwWriteException(e);
}	
  }

  private static void throwReadException(Throwable t) throws CoreException {
	MTLPlugin.log(t);
  }

  private static void throwWriteException(Throwable t) throws CoreException {
	MTLPlugin.log(t);
  }

}

/*
* $Id: MTLOutlinePage.java,v 1.1 2004-07-30 14:10:03 sdzale Exp $
* Authors : ${user}
*
* Created on ${date}
* Copyright 2004 - INRIA - LGPL license
*/ 
package org.inria.mtl.editors.outline;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.Path;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.BadPositionCategoryException;
import org.eclipse.jface.text.DefaultPositionUpdater;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IPositionUpdater;
import org.eclipse.jface.text.Position;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display; 
import org.eclipse.ui.texteditor.IDocumentProvider;
import org.eclipse.ui.texteditor.ITextEditor;
import org.eclipse.ui.views.contentoutline.ContentOutlinePage;

import org.inria.mtl.*;


/**
 * @author serge DZALE
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class MTLOutlinePage extends ContentOutlinePage {

//	public class OutlineLabelProvider extends LabelProvider{
//		
//		Image LibraryImage;
//		Image ClassImage;
//		Image FuncImage;
//		Image VarImage;
//		
//		public OutlineLabelProvider(){
//			super();
//			try{
//				this.LibraryImage = new Image(Display.getCurrent(),MTLPlugin.getDefault().openStream(new Path("icons/Chapter.gif")));
//				this.ClassImage = new Image(Display.getCurrent(),MTLPlugin.getDefault().openStream(new Path("icons/Section.gif")));
//				this.FuncImage = new Image(Display.getCurrent(),MTLPlugin.getDefault().openStream(new Path("icons/SubSection.gif")));
//				this.VarImage = new Image(Display.getCurrent(),MTLPlugin.getDefault().openStream(new Path("icons/SubSubSection.gif")));
//				
//			}catch(IOException ex){
//				System.out.println(ex.toString());
//			}
//		}
//		
//		public Image getImage(Object obj){
//			if(obj instanceof Library){
//				if(LibraryImage!=null)return LibraryImage;
//			}
//			if(obj instanceof MTLClass){
//				if(ClassImage!=null)return ClassImage;
//			}
//			if(obj instanceof MTLMethod){
//				if(FuncImage!=null)return FuncImage;
//			}
//			if(obj instanceof MTLProperty){
//				if(VarImage!=null)return VarImage;
//			}
//			return super.getImage(obj);
//		}
//	}
//
//
//
//
//	protected class MTLContentProvider implements ITreeContentProvider {
//
//		private final static String SEGMENTS = "__mtl_segments";
//		private IPositionUpdater positionUpdater =
//			new DefaultPositionUpdater(SEGMENTS);
//		private List content = new ArrayList();
//		
//
//
//		/* (non-Javadoc)
//		 * @see org.eclipse.jface.viewers.ITreeContentProvider#getChildren(java.lang.Object)
//		 */
//		public Object[] getChildren(Object o) {
//			if(o instanceof ICodePart){
//				return ((ICodePart)o).getChildern();
//			}
//			return null;
//		}
//
//		/* (non-Javadoc)
//		 * @see org.eclipse.jface.viewers.ITreeContentProvider#getParent(java.lang.Object)
//		 */
//		public Object getParent(Object o) {
//			if(o instanceof ICodePart){
//				return ((ICodePart)o).getParent();
//			}
//			return null;
//		}
//
//		/* (non-Javadoc)
//		 * @see org.eclipse.jface.viewers.ITreeContentProvider#hasChildren(java.lang.Object)
//		 */
//		public boolean hasChildren(Object o) {
//			if(o instanceof ICodePart){
//				ICodePart e = (ICodePart)o;
//				if(e.getChildern().length!=0)return true;
//				else return false;
//			}
//			if(o == input)return true;
//			else return false;
//		}
//
//		/* (non-Javadoc)
//		 * @see org.eclipse.jface.viewers.IStructuredContentProvider#getElements(java.lang.Object)
//		 */
//		public Object[] getElements(Object arg0) {
//			return content.toArray();
//		}
//
//		/* (non-Javadoc)
//		 * @see org.eclipse.jface.viewers.IContentProvider#dispose()
//		 */
//		public void dispose() {
//			if (content != null) {
//				content.clear();
//				content= null;
//			}
//
//		}
//
//		/* (non-Javadoc)
//		 * @see org.eclipse.jface.viewers.IContentProvider#inputChanged(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
//		 */
//		public void inputChanged(
//			Viewer viewer,
//			Object oldInput,
//			Object newInput) {
//			if (oldInput != null) {
//				IDocument document = provider.getDocument(oldInput);
//				if (document != null) {
//					try {
//						document.removePositionCategory(SEGMENTS);
//					} catch (BadPositionCategoryException x) {
//					}
//					document.removePositionUpdater(positionUpdater);
//				}
//			}
//
//			content.clear();
//
//			if (newInput != null) {
//				IDocument document = provider.getDocument(newInput);
//				if (document != null) {
//					document.addPositionCategory(SEGMENTS);
//					document.addPositionUpdater(positionUpdater);
//					parse(document);
//				}
//			}
//
//		}
//		
//		private String getSectionName(String command){
//			//Regarder si ça désigne tout début
//			int openCurly = command.indexOf("{");
//			int closeCurly = command.lastIndexOf("}");
//			
//			if(openCurly == -1 || closeCurly == -1)return command;
//			return command.substring(openCurly+1,closeCurly);
//		}
//
//		public void parse(IDocument document) {
//			ICodePart parent = null;
//			String currentWord = "";
//			boolean inWord = false;
//			char currentChar;
//			System.out.println("Parsing Document");
//			
//			
//			TextWords wDetector = new TextWords();
//			boolean inTextWord = false;
//			int WordCount = 0;
//			int Citations = 0;
//			for (int i = 0; i < document.getLength(); i++) {
//				try {
//					
//					if(inTextWord){
//						if(!wDetector.isWordPart(document.getChar(i))){
//							inTextWord=false;
//						}
//					}else{
//						if(wDetector.isWordStart(document.getChar(i))){
//							WordCount++;
//							inTextWord = true;
//						}
//					}
//					if ((currentChar = document.getChar(i)) == '\\') {
//						inWord = true;
//					} else {
//						if (inWord) {
//							if (currentChar != '\n' && currentChar != '}') {
//								currentWord += currentChar;
//							} else {
//								inWord = false;
//								WordCount--;
//								//add the close bracket if needed.
//								if(currentChar=='}')currentWord+=currentChar;
//								
//								if(currentWord.startsWith("bibliography{")){
//									String files = getSectionName(currentWord);
//									if(files.indexOf(",")==-1){
//										bibtexfiles = new String[]{files+".bib"};
//									}else{
//										String[] oFiles = files.split(",");
//										bibtexfiles = new String[oFiles.length];
//										for(int j=0;j<oFiles.length;j++){
//											bibtexfiles[j]= oFiles[j].trim()+".bib";
//										}
//									}
//								}
//								
//								
//								if (currentWord.startsWith("library")) {
//									Position p = new Position(i-currentWord.length());
//									document.addPosition(SEGMENTS,p);
//									Library c  = new Library(getSectionName(currentWord));
//									c.setParent(input);
//									c.setPosition(p);
//									content.add(c);
//									parent = c;
//								} else if (currentWord.startsWith("class")) {
//									Position p = new Position(i-currentWord.length());
//									document.addPosition(SEGMENTS,p);
//									MTLClass s = new MTLClass(getSectionName(currentWord),p);
//									if(parent==null){
//										s.setParent(input);
//										content.add(s);
//										parent = s;
//									}else{
//										if(parent instanceof Library){
//											((ICodePart)parent).addChild(s);
//											s.setParent(parent);
//											parent = s;
//										}else{
//											
//											
//											Object secondParent = parent;
//												boolean isISection = true;
//												for(;;){
//													if(secondParent instanceof ICodePart){
//														if(!(secondParent instanceof MTLClass) && !(secondParent instanceof MTLMethod) && !(secondParent instanceof MTLProperty)){
//															break;
//														}else{
//															secondParent = ((ICodePart)secondParent).getParent();
//														}
//													}else{
//														isISection = false;
//														break;
//													}				
//												}
//
//												s.setParent(secondParent);
//												if(isISection){
//													((ICodePart)secondParent).addChild(s);
//												}else{
//													content.add(s);
//												}
//												parent = s;	
//										}
//									}
//								} else if (
//									currentWord.startsWith("subsection")) {
//										Position p = new Position(i-currentWord.length());
//										document.addPosition(SEGMENTS,p);
//										MTLMethod ss = new MTLMethod(getSectionName(currentWord),p);
//										if(parent==null){
//											ss.setParent(input);
//											content.add(ss);
//											parent=ss;
//										}else{
//											if(parent instanceof Library || parent instanceof MTLClass){
//												((ICodePart)parent).addChild(ss);
//												ss.setParent(parent);
//												parent = ss;
//											}else{
//												
//												Object secondParent = parent;
//												boolean isISection = true;
//												for(;;){
//													if(secondParent instanceof ICodePart){
//														if(!(secondParent instanceof MTLMethod) && !(secondParent instanceof MTLProperty)){
//															break;
//														}else{
//															secondParent = ((ICodePart)secondParent).getParent();
//														}
//													}else{
//														isISection = false;
//														break;
//													}				
//												}
//
//												ss.setParent(secondParent);
//												if(isISection){
//													((ICodePart)secondParent).addChild(ss);
//												}else{
//													content.add(ss);
//												}
//												parent = ss;	
//											}
//										}
//										
//								} else if (
//									currentWord.startsWith("subsubsection")) {
//									Position p = new Position(i-currentWord.length());
//									document.addPosition(SEGMENTS,p);
//									MTLProperty sss = new MTLProperty(getSectionName(currentWord),p);
//									if(parent==null){
//										sss.setParent(input);
//										content.add(sss);
//										parent=sss;
//									}else{
//										if(parent instanceof Library || parent instanceof MTLClass || parent instanceof MTLMethod){
//											((ICodePart)parent).addChild(sss);
//											sss.setParent(parent);
//											parent = sss;
//										}else{
//											sss.setParent(((ICodePart)parent).getParent());
//											((ICodePart)((ICodePart)parent).getParent()).addChild(sss);
//											parent=sss;	
//										}
//									}
//
//								}else if(currentWord.startsWith("cite")){
//									Position p = new Position(i-currentWord.length());
//									document.addPosition(SEGMENTS,p);
////									Citation c = new Citation(getSectionName(currentWord),p);
////									if(parent!=null){
////										c.setParent(parent);
////										parent.addChild(c);
////									}
//									Citations++;
//								}
//								currentWord="";
//							}
//						}
//					}
//				} catch (BadLocationException blex) {
//
//				}catch(BadPositionCategoryException bpcex){
//					
//				}
//				
//				
//				
//			}
//			content.add(0,"Word Count: "+WordCount);
//			content.add(0,"Citations : "+Citations);
//		}
//
//	}
//	
//	private String[] bibtexfiles = new String[0];
//		
//
//
//	protected Object input;
//	protected IDocumentProvider provider;
//	protected ITextEditor editor;
//
//	public MTLOutlinePage(IDocumentProvider provider, ITextEditor editor) {
//		super();
//		this.provider = provider;
//		this.editor = editor;
//	}
//	
////	public String[] getBibtexFilenames(){
////		return bibtexfiles;
////	}
//	public void createControl(Composite parent) {
//
//		super.createControl(parent);
//
//		TreeViewer viewer = getTreeViewer();
//		viewer.setContentProvider(new MTLContentProvider());
//		viewer.setLabelProvider(new OutlineLabelProvider());
//		viewer.addSelectionChangedListener(this);
//
//		if (input != null)
//			viewer.setInput(input);
//	}
//
//	public void setInput(Object input) {
//		this.input= input;
//		update();
//	}
//	
//	public void update() {
//		TreeViewer viewer= getTreeViewer();
//
//		if (viewer != null) {
//			Control control= viewer.getControl();
//			if (control != null && !control.isDisposed()) {
//				control.setRedraw(false);
//				viewer.setInput(input);
//				viewer.expandAll();
//				control.setRedraw(true);
//			}
//		}
//	}
//	
//	public void selectionChanged(SelectionChangedEvent event) {
//
//		super.selectionChanged(event);
//
//		ISelection selection= event.getSelection();
//		if (selection.isEmpty())
//			editor.resetHighlightRange();
//		else {
//			Object SelectionElement = ((IStructuredSelection) selection).getFirstElement();
//			if(SelectionElement instanceof ICodePart){
//			
//			ICodePart section= (ICodePart) SelectionElement;
//			int start= section.getPosition().getOffset();
//			//int length= segment.position.getLength();
//			try {
//				editor.setHighlightRange(start, 0, true);
//			} catch (IllegalArgumentException x) {
//				editor.resetHighlightRange();
//			}
//			}
//		}
//	}

}

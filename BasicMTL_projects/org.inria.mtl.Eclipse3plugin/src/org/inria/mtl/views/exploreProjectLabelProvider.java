package org.inria.mtl.views;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

import org.inria.mtl.MTLPlugin;
import org.inria.mtl.views.model.Library;
import org.inria.mtl.views.model.Classe;
import org.inria.mtl.views.model.Attribute;
import org.inria.mtl.views.model.MTLNode;
import org.inria.mtl.views.model.Method;

public class exploreProjectLabelProvider extends LabelProvider {	
	private Map imageCache = new HashMap(11);
	
	/*
	 * @see ILabelProvider#getImage(Object)
	 */
	public Image getImage(Object element) {
		ImageDescriptor descriptor = null;
		if (element instanceof MTLNode) {
			if (((MTLNode)element).type==0)	descriptor = MTLPlugin.getImageDescriptor("movingBox.gif");
			if (((MTLNode)element).type==1)	descriptor = MTLPlugin.getImageDescriptor("table.gif");
			if (((MTLNode)element).type==2)	descriptor = MTLPlugin.getImageDescriptor("cFolder.gif");
			if (((MTLNode)element).type==3)	descriptor = MTLPlugin.getImageDescriptor("attribute.gif");
			if (((MTLNode)element).type==4)	descriptor = MTLPlugin.getImageDescriptor("function.gif");
		} else if (element instanceof Library) {
			descriptor =MTLPlugin.getImageDescriptor("book.gif");
		} else if (element instanceof Attribute) {
			descriptor = MTLPlugin.getImageDescriptor("attribute.gif");
		}else if (element instanceof Classe) {
			descriptor = MTLPlugin.getImageDescriptor("cFolder.gif");
		}else if (element instanceof Method) {
			descriptor = MTLPlugin.getImageDescriptor("function.gif");
		} else {
			throw unknownElement(element);
		}

		//obtain the cached image corresponding to the descriptor
		Image image = (Image)imageCache.get(descriptor);
		if (image == null) {
			image = descriptor.createImage();
			imageCache.put(descriptor, image);
		}
		return image;
	}

	/*
	 * @see ILabelProvider#getText(Object)
	 */
	public String getText(Object element) {
		if (element instanceof MTLNode) {
			if(((MTLNode)element).getName() == null) {
				return "Library";
			} else {
				return ((MTLNode)element).getName();
			}
		} else if (element instanceof Library) {
			return ((Library)element).getTitle();
		} else if (element instanceof Attribute) {
			return ((Attribute)element).getTitle();
		} else if (element instanceof Classe) {
			return ((Classe)element).getTitle();
		} else if (element instanceof Method) {
			return ((Method)element).getTitle(); 
		}else {
			throw unknownElement(element);
		}
	}

	public void dispose() {
		for (Iterator i = imageCache.values().iterator(); i.hasNext();) {
			((Image) i.next()).dispose();
		}
		imageCache.clear();
	}

	protected RuntimeException unknownElement(Object element) {
		return new RuntimeException("Unknown type of element in tree of type " + element.getClass().getName());
	}

}

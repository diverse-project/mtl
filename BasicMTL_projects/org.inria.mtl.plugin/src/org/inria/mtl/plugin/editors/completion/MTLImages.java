package org.inria.mtl.plugin.editors.completion;

import java.net.MalformedURLException;
import java.net.URL;

import org.inria.mtl.plugin.MTLPlugin;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.swt.graphics.Image;

public class MTLImages {

	protected static final String NAME_PREFIX =
		"org.inria.mtl.plugin.editors.competion";
	protected static final int NAME_PREFIX_LENGTH = NAME_PREFIX.length();

	protected static URL iconBaseURL;

	static {
		String pathSuffix = "icons/";
		try {
			iconBaseURL =
				new URL(
					MTLPlugin
						.getDefault()
						.getDescriptor()
						.getInstallURL(),
					pathSuffix);
		} catch (MalformedURLException e) {
			MTLPlugin.log(e);
		}
	}

	protected static final ImageRegistry IMAGE_REGISTRY = new ImageRegistry();

	protected static final String OBJ_PREFIX = "obj16";
	protected static final String OVR_PREFIX = "ovr16";
	protected static final String CTOOL_PREFIX = "ctool16";

	public static final String IMG_OBJS_GHOST= NAME_PREFIX + "ghost.gif"; 	
	public static final String IMG_CLASS = NAME_PREFIX + "class_obj.gif";
	public static final String IMG_DEFINE = NAME_PREFIX + "define_obj.gif";
	public static final String IMG_BUILTIN = NAME_PREFIX + "builtin_obj.gif";
	public static final String IMG_FUN = NAME_PREFIX + "fun_obj.gif";
	public static final String IMG_INC = NAME_PREFIX + "impc_obj.gif";
	public static final String IMG_VAR = NAME_PREFIX + "var_obj.gif";
	public static final String IMG_OBJS_ERROR = NAME_PREFIX + "error_obj.gif";
	public static final String IMG_OBJS_WARNING =
		NAME_PREFIX + "warning_obj.gif";
	public static final String IMG_OBJS_INFO = NAME_PREFIX + "info_obj.gif";
	public static final String IMG_OBJS_INTERFACE= NAME_PREFIX + "int_obj.gif"; 			//$NON-NLS-1$
	public static final String IMG_CTOOLS_PHP_PAGE =
		NAME_PREFIX + "php_page.gif";
	public static final String IMG_CTOOLS_PHP = NAME_PREFIX + "php.gif";

	public static final String IMG_OBJS_TEMPLATE =
		NAME_PREFIX + "template_obj.gif";

	public static final String IMG_CLEAR = NAME_PREFIX + "clear.gif"; 
	
	public static final ImageDescriptor DESC_OBJS_GHOST= createManaged(OBJ_PREFIX, IMG_OBJS_GHOST);
	
	public static final ImageDescriptor DESC_CLASS =
		createManaged(OBJ_PREFIX, IMG_CLASS);
	public static final ImageDescriptor DESC_DEFINE =
		createManaged(OBJ_PREFIX, IMG_DEFINE);
	public static final ImageDescriptor DESC_BUILTIN =
		createManaged(OBJ_PREFIX, IMG_BUILTIN);
	public static final ImageDescriptor DESC_FUN =
		createManaged(OBJ_PREFIX, IMG_FUN);
	public static final ImageDescriptor DESC_INC =
		createManaged(OBJ_PREFIX, IMG_INC);
	public static final ImageDescriptor DESC_VAR =
		createManaged(OBJ_PREFIX, IMG_VAR);
	public static final ImageDescriptor DESC_OBJS_ERROR =
		createManaged(OBJ_PREFIX, IMG_OBJS_ERROR);
	public static final ImageDescriptor DESC_OBJS_WARNING =
		createManaged(OBJ_PREFIX, IMG_OBJS_WARNING);
	public static final ImageDescriptor DESC_OBJS_INFO =
		createManaged(OBJ_PREFIX, IMG_OBJS_INFO);
	public static final ImageDescriptor DESC_CTOOL_PHP_PAGE =
		createManaged(CTOOL_PREFIX, IMG_CTOOLS_PHP_PAGE);
	public static final ImageDescriptor DESC_CTOOL_PHP =
		createManaged(CTOOL_PREFIX, IMG_CTOOLS_PHP);

	public static final ImageDescriptor DESC_OBJS_TEMPLATE =
		createManaged(OBJ_PREFIX, IMG_OBJS_TEMPLATE);
		
	public static final ImageDescriptor DESC_CLEAR =
			createManaged(OBJ_PREFIX, IMG_CLEAR);
	
	private static final String T_OBJ= "obj16"; 		//$NON-NLS-1$
	private static final String T_WIZBAN= "wizban"; 	//$NON-NLS-1$
	
	public static final ImageDescriptor DESC_WIZBAN_NEWCLASS= create(T_WIZBAN, "newclass_wiz.gif"); 			//$NON-NLS-1$
	public static final ImageDescriptor DESC_OBJS_INTERFACE= createManaged(T_OBJ, IMG_OBJS_INTERFACE);
	
	/**
	 * Returns the image managed under the given key in this registry.
	 * 
	 * @param key the image's key
	 * @return the image managed under the given key
	 */
	public static Image get(String key) {
		return IMAGE_REGISTRY.get(key);
	}

	/**
	 * Sets the three image descriptors for enabled, disabled, and hovered to an action. The actions
	 * are retrieved from the *tool16 folders.
	 */
	public static void setToolImageDescriptors(
		IAction action,
		String iconName) {
		setImageDescriptors(action, "tool16", iconName);
	}

	/**
	 * Sets the three image descriptors for enabled, disabled, and hovered to an action. The actions
	 * are retrieved from the *lcl16 folders.
	 */
	public static void setLocalImageDescriptors(
		IAction action,
		String iconName) {
		setImageDescriptors(action, "lcl16", iconName);
	}

	public static ImageRegistry getImageRegistry() {
		return IMAGE_REGISTRY;
	}

	//---- Helper methods to access icons on the file system --------------------------------------

	protected static void setImageDescriptors(
		IAction action,
		String type,
		String relPath) {

		try {
			ImageDescriptor id =
				ImageDescriptor.createFromURL(
					makeIconFileURL("d" + type, relPath));
			if (id != null)
				action.setDisabledImageDescriptor(id);
		} catch (MalformedURLException e) {
		}

		try {
			ImageDescriptor id =
				ImageDescriptor.createFromURL(
					makeIconFileURL("c" + type, relPath));
			if (id != null)
				action.setHoverImageDescriptor(id);
		} catch (MalformedURLException e) {
		}

		action.setImageDescriptor(create("e" + type, relPath));
	}

	protected static ImageDescriptor createManaged(
		String prefix,
		String name) {
		try {
			ImageDescriptor result =
				ImageDescriptor.createFromURL(
					makeIconFileURL(
						prefix,
						name.substring(NAME_PREFIX_LENGTH)));
			IMAGE_REGISTRY.put(name, result);
			return result;
		} catch (MalformedURLException e) {
			return ImageDescriptor.getMissingImageDescriptor();
		}
	}

	protected static ImageDescriptor create(String prefix, String name) {
		try {
			return ImageDescriptor.createFromURL(makeIconFileURL(prefix, name));
		} catch (MalformedURLException e) {
			return ImageDescriptor.getMissingImageDescriptor();
		}
	}

	protected static URL makeIconFileURL(String prefix, String name)
		throws MalformedURLException {
		if (iconBaseURL == null)
			throw new MalformedURLException();

		StringBuffer buffer = new StringBuffer(prefix);
		buffer.append('/');
		buffer.append(name);
		return new URL(iconBaseURL, buffer.toString());
	}
}
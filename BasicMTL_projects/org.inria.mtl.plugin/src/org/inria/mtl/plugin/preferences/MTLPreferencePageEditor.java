package org.inria.mtl.plugin.preferences;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferenceConverter;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.text.source.SourceViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.eclipse.ui.texteditor.WorkbenchChainedTextFontFieldEditor;
import org.inria.mtl.plugin.MTLPlugin;


/*
 * The page for setting the editor options.
 */
public class MTLPreferencePageEditor
	extends PreferencePage
	implements IWorkbenchPreferencePage {

	private static final String BOLD = PreferenceConstants.EDITOR_BOLD_SUFFIX;

	public final OverlayPreferenceStore.OverlayKey[] fKeys =
		new OverlayPreferenceStore.OverlayKey[] {
			new OverlayPreferenceStore.OverlayKey(OverlayPreferenceStore.STRING,PreferenceConstants.EDITOR_FOREGROUND_COLOR),
			new OverlayPreferenceStore.OverlayKey(OverlayPreferenceStore.BOOLEAN,PreferenceConstants.EDITOR_FOREGROUND_DEFAULT_COLOR),
			new OverlayPreferenceStore.OverlayKey(OverlayPreferenceStore.STRING,PreferenceConstants.EDITOR_BACKGROUND_COLOR),
			new OverlayPreferenceStore.OverlayKey(OverlayPreferenceStore.BOOLEAN,PreferenceConstants.EDITOR_BACKGROUND_DEFAULT_COLOR),
			new OverlayPreferenceStore.OverlayKey(OverlayPreferenceStore.INT,PreferenceConstants.EDITOR_TAB_WIDTH),
			new OverlayPreferenceStore.OverlayKey(OverlayPreferenceStore.STRING,PreferenceConstants.EDITOR_MULTI_LINE_COMMENT_COLOR),
			new OverlayPreferenceStore.OverlayKey(OverlayPreferenceStore.BOOLEAN,PreferenceConstants.EDITOR_MULTI_LINE_COMMENT_BOLD),
			new OverlayPreferenceStore.OverlayKey(OverlayPreferenceStore.STRING,PreferenceConstants.EDITOR_SINGLE_LINE_COMMENT_COLOR),
			new OverlayPreferenceStore.OverlayKey(OverlayPreferenceStore.BOOLEAN,PreferenceConstants.EDITOR_SINGLE_LINE_COMMENT_BOLD),
			new OverlayPreferenceStore.OverlayKey(OverlayPreferenceStore.STRING,PreferenceConstants.EDITOR_MTL_TAG_COLOR),
			new OverlayPreferenceStore.OverlayKey(OverlayPreferenceStore.BOOLEAN,PreferenceConstants.EDITOR_MTL_TAG_BOLD),
			new OverlayPreferenceStore.OverlayKey(OverlayPreferenceStore.STRING,PreferenceConstants.EDITOR_MTL_KEYWORD_COLOR),
			new OverlayPreferenceStore.OverlayKey(OverlayPreferenceStore.BOOLEAN,PreferenceConstants.EDITOR_MTL_KEYWORD_BOLD),
			new OverlayPreferenceStore.OverlayKey(OverlayPreferenceStore.STRING,PreferenceConstants.EDITOR_MTL_FUNCTIONNAME_COLOR),
			new OverlayPreferenceStore.OverlayKey(OverlayPreferenceStore.BOOLEAN,PreferenceConstants.EDITOR_MTL_FUNCTIONNAME_BOLD),
			new OverlayPreferenceStore.OverlayKey(OverlayPreferenceStore.STRING,PreferenceConstants.EDITOR_MTL_VARIABLE_COLOR),
			new OverlayPreferenceStore.OverlayKey(OverlayPreferenceStore.BOOLEAN,PreferenceConstants.EDITOR_MTL_VARIABLE_BOLD),
			new OverlayPreferenceStore.OverlayKey(OverlayPreferenceStore.STRING,PreferenceConstants.EDITOR_MTL_CONSTANT_COLOR),
			new OverlayPreferenceStore.OverlayKey(OverlayPreferenceStore.BOOLEAN,PreferenceConstants.EDITOR_MTL_CONSTANT_BOLD),
			new OverlayPreferenceStore.OverlayKey(OverlayPreferenceStore.STRING,PreferenceConstants.EDITOR_MTL_TYPE_COLOR),
			new OverlayPreferenceStore.OverlayKey(OverlayPreferenceStore.BOOLEAN,PreferenceConstants.EDITOR_MTL_TYPE_BOLD),
			new OverlayPreferenceStore.OverlayKey(OverlayPreferenceStore.STRING,PreferenceConstants.EDITOR_STRING_COLOR),
			new OverlayPreferenceStore.OverlayKey(OverlayPreferenceStore.BOOLEAN,PreferenceConstants.EDITOR_STRING_BOLD),
			new OverlayPreferenceStore.OverlayKey(OverlayPreferenceStore.STRING,PreferenceConstants.EDITOR_MTL_DEFAULT_COLOR),
			new OverlayPreferenceStore.OverlayKey(OverlayPreferenceStore.BOOLEAN,PreferenceConstants.EDITOR_MTL_DEFAULT_BOLD),
			new OverlayPreferenceStore.OverlayKey(OverlayPreferenceStore.INT,PreferenceConstants.EDITOR_PRINT_MARGIN_COLUMN),
			new OverlayPreferenceStore.OverlayKey(OverlayPreferenceStore.BOOLEAN,PreferenceConstants.EDITOR_PRINT_MARGIN),
			new OverlayPreferenceStore.OverlayKey(OverlayPreferenceStore.STRING,PreferenceConstants.EDITOR_PROBLEM_INDICATION_COLOR),
			new OverlayPreferenceStore.OverlayKey(OverlayPreferenceStore.BOOLEAN,PreferenceConstants.EDITOR_PROBLEM_INDICATION),
			new OverlayPreferenceStore.OverlayKey(OverlayPreferenceStore.STRING,PreferenceConstants.EDITOR_WARNING_INDICATION_COLOR),
			new OverlayPreferenceStore.OverlayKey(OverlayPreferenceStore.BOOLEAN,PreferenceConstants.EDITOR_WARNING_INDICATION),
			new OverlayPreferenceStore.OverlayKey(OverlayPreferenceStore.STRING,PreferenceConstants.EDITOR_TASK_INDICATION_COLOR),
			new OverlayPreferenceStore.OverlayKey(OverlayPreferenceStore.BOOLEAN,PreferenceConstants.EDITOR_TASK_INDICATION),
			new OverlayPreferenceStore.OverlayKey(OverlayPreferenceStore.STRING,PreferenceConstants.EDITOR_BOOKMARK_INDICATION_COLOR),
			new OverlayPreferenceStore.OverlayKey(OverlayPreferenceStore.BOOLEAN,PreferenceConstants.EDITOR_BOOKMARK_INDICATION),
			new OverlayPreferenceStore.OverlayKey(OverlayPreferenceStore.STRING,PreferenceConstants.EDITOR_SEARCH_RESULT_INDICATION_COLOR),
			new OverlayPreferenceStore.OverlayKey(OverlayPreferenceStore.BOOLEAN,PreferenceConstants.EDITOR_SEARCH_RESULT_INDICATION),
			new OverlayPreferenceStore.OverlayKey(OverlayPreferenceStore.STRING,PreferenceConstants.EDITOR_UNKNOWN_INDICATION_COLOR),
			new OverlayPreferenceStore.OverlayKey(OverlayPreferenceStore.BOOLEAN,PreferenceConstants.EDITOR_UNKNOWN_INDICATION),
			new OverlayPreferenceStore.OverlayKey(
				OverlayPreferenceStore.BOOLEAN,
				PreferenceConstants.EDITOR_ERROR_INDICATION_IN_OVERVIEW_RULER),
			new OverlayPreferenceStore.OverlayKey(
				OverlayPreferenceStore.BOOLEAN,
				PreferenceConstants
					.EDITOR_WARNING_INDICATION_IN_OVERVIEW_RULER),
			new OverlayPreferenceStore.OverlayKey(
				OverlayPreferenceStore.BOOLEAN,
				PreferenceConstants.EDITOR_TASK_INDICATION_IN_OVERVIEW_RULER),
			new OverlayPreferenceStore.OverlayKey(
				OverlayPreferenceStore.BOOLEAN,
				PreferenceConstants
					.EDITOR_BOOKMARK_INDICATION_IN_OVERVIEW_RULER),
			new OverlayPreferenceStore.OverlayKey(
				OverlayPreferenceStore.BOOLEAN,
				PreferenceConstants
					.EDITOR_SEARCH_RESULT_INDICATION_IN_OVERVIEW_RULER),
			new OverlayPreferenceStore.OverlayKey(
				OverlayPreferenceStore.BOOLEAN,
				PreferenceConstants
					.EDITOR_UNKNOWN_INDICATION_IN_OVERVIEW_RULER),
			new OverlayPreferenceStore.OverlayKey(
				OverlayPreferenceStore.BOOLEAN,
				PreferenceConstants.EDITOR_CORRECTION_INDICATION),
			new OverlayPreferenceStore.OverlayKey(
				OverlayPreferenceStore.BOOLEAN,
				PreferenceConstants.CODEASSIST_AUTOACTIVATION),
			new OverlayPreferenceStore.OverlayKey(
				OverlayPreferenceStore.INT,
				PreferenceConstants.CODEASSIST_AUTOACTIVATION_DELAY),
			new OverlayPreferenceStore.OverlayKey(
				OverlayPreferenceStore.BOOLEAN,
				PreferenceConstants.CODEASSIST_AUTOINSERT),
			new OverlayPreferenceStore.OverlayKey(
				OverlayPreferenceStore.STRING,
				PreferenceConstants.CODEASSIST_PROPOSALS_BACKGROUND),
			new OverlayPreferenceStore.OverlayKey(
				OverlayPreferenceStore.STRING,
				PreferenceConstants.CODEASSIST_PROPOSALS_FOREGROUND),
			};

	private final String[][] fSyntaxColorListModel = new String[][] { { MTLMessages.getString("MTLEditorPreferencePage.multiLineComment"), PreferenceConstants.EDITOR_MULTI_LINE_COMMENT_COLOR }, //$NON-NLS-1$
		{
			MTLMessages.getString("MTLEditorPreferencePage.singleLineComment"), PreferenceConstants.EDITOR_SINGLE_LINE_COMMENT_COLOR }, //$NON-NLS-1$
		{
			MTLMessages.getString("MTLEditorPreferencePage.tags"), PreferenceConstants.EDITOR_MTL_TAG_COLOR }, //$NON-NLS-1$
		{
			MTLMessages.getString("MTLEditorPreferencePage.keywords"), PreferenceConstants.EDITOR_MTL_KEYWORD_COLOR }, //$NON-NLS-1$
		{
			MTLMessages.getString("MTLEditorPreferencePage.functionNames"), PreferenceConstants.EDITOR_MTL_FUNCTIONNAME_COLOR }, //$NON-NLS-1$
		{
			MTLMessages.getString("MTLEditorPreferencePage.variables"), PreferenceConstants.EDITOR_MTL_VARIABLE_COLOR }, //$NON-NLS-1$
		{
			MTLMessages.getString("MTLEditorPreferencePage.constants"), PreferenceConstants.EDITOR_MTL_CONSTANT_COLOR }, //$NON-NLS-1$
		{
			MTLMessages.getString("MTLEditorPreferencePage.types"), PreferenceConstants.EDITOR_MTL_TYPE_COLOR }, //$NON-NLS-1$
		{
			MTLMessages.getString("MTLEditorPreferencePage.strings"), PreferenceConstants.EDITOR_STRING_COLOR }, //$NON-NLS-1$
		{
			MTLMessages.getString("MTLEditorPreferencePage.others"), PreferenceConstants.EDITOR_MTL_DEFAULT_COLOR }, //$NON-NLS-1$
		};


	private final String[][] fProblemIndicationColorListModel =
		new String[][] {
			{
				"Errors",
				PreferenceConstants.EDITOR_PROBLEM_INDICATION_COLOR,
				PreferenceConstants.EDITOR_PROBLEM_INDICATION,
				PreferenceConstants.EDITOR_ERROR_INDICATION_IN_OVERVIEW_RULER },
			{
			"Warnings",
				PreferenceConstants.EDITOR_WARNING_INDICATION_COLOR,
				PreferenceConstants.EDITOR_WARNING_INDICATION,
				PreferenceConstants.EDITOR_WARNING_INDICATION_IN_OVERVIEW_RULER },
				{
			"Tasks",
				PreferenceConstants.EDITOR_TASK_INDICATION_COLOR,
				PreferenceConstants.EDITOR_TASK_INDICATION,
				PreferenceConstants.EDITOR_TASK_INDICATION_IN_OVERVIEW_RULER },
				{
			"Search Results",
				PreferenceConstants.EDITOR_SEARCH_RESULT_INDICATION_COLOR,
				PreferenceConstants.EDITOR_SEARCH_RESULT_INDICATION,
				PreferenceConstants.EDITOR_SEARCH_RESULT_INDICATION_IN_OVERVIEW_RULER },
				{
			"Bookmarks",
				PreferenceConstants.EDITOR_BOOKMARK_INDICATION_COLOR,
				PreferenceConstants.EDITOR_BOOKMARK_INDICATION,
				PreferenceConstants.EDITOR_BOOKMARK_INDICATION_IN_OVERVIEW_RULER },
				{
			"Others",
				PreferenceConstants.EDITOR_UNKNOWN_INDICATION_COLOR,
				PreferenceConstants.EDITOR_UNKNOWN_INDICATION,
				PreferenceConstants.EDITOR_UNKNOWN_INDICATION_IN_OVERVIEW_RULER }
	};

	private OverlayPreferenceStore fOverlayStore;
	//private JavaTextTools fJavaTextTools;
	

	private Map fColorButtons = new HashMap();
	private SelectionListener fColorButtonListener = new SelectionListener() {
		public void widgetDefaultSelected(SelectionEvent e) {
		}
		public void widgetSelected(SelectionEvent e) {
			ColorEditor editor = (ColorEditor) e.widget.getData();
			PreferenceConverter.setValue(
				fOverlayStore,
				(String) fColorButtons.get(editor),
				editor.getColorValue());
		}
	};

	private Map fCheckBoxes = new HashMap();
	private SelectionListener fCheckBoxListener = new SelectionListener() {
		public void widgetDefaultSelected(SelectionEvent e) {
		}
		public void widgetSelected(SelectionEvent e) {
			Button button = (Button) e.widget;
			fOverlayStore.setValue(
				(String) fCheckBoxes.get(button),
				button.getSelection());
		}
	};

	private Map fTextFields = new HashMap();
	private ModifyListener fTextFieldListener = new ModifyListener() {
		public void modifyText(ModifyEvent e) {
			Text text = (Text) e.widget;
			fOverlayStore.setValue(
				(String) fTextFields.get(text),
				text.getText());
		}
	};

	private ArrayList fNumberFields = new ArrayList();
	private ModifyListener fNumberFieldListener = new ModifyListener() {
		public void modifyText(ModifyEvent e) {
				//numberFieldChanged((Text) e.widget);
	}
	};

	private WorkbenchChainedTextFontFieldEditor fFontEditor;
	private List fSyntaxColorList;
	private List fAppearanceColorList;
	private List fProblemIndicationList;
	private ColorEditor fSyntaxForegroundColorEditor;
	private ColorEditor fAppearanceForegroundColorEditor;
	private ColorEditor fProblemIndicationForegroundColorEditor;
	private ColorEditor fBackgroundColorEditor;
	private Button fBackgroundDefaultRadioButton;
	private Button fBackgroundCustomRadioButton;
	private Button fBackgroundColorButton;
	private Button fBoldCheckBox;
	private SourceViewer fPreviewViewer;
	private Color fBackgroundColor;
	private Control fAutoInsertDelayText;
		private Button fShowInTextCheckBox;
	private Button fShowInOverviewRulerCheckBox;


	public MTLPreferencePageEditor() {
		setDescription(MTLMessages.getString("MTLEditorPreferencePage.description")); //$NON-NLS-1$
		setPreferenceStore(MTLPlugin.getDefault().getPreferenceStore());
		fOverlayStore = new OverlayPreferenceStore(getPreferenceStore(), fKeys);
		System.out.println(fOverlayStore.getString(PreferenceConstants.EDITOR_FOREGROUND_COLOR));
		System.out.println(fOverlayStore.getString(PreferenceConstants.EDITOR_BACKGROUND_COLOR));
		System.out.println(fOverlayStore.getString(PreferenceConstants.EDITOR_STRING_COLOR));
	}

	/*
	 * @see IWorkbenchPreferencePage#init()
	 */
	public void init(IWorkbench workbench) {
	}

	/*
	 * @see PreferencePage#createControl(Composite)
	 */
	public void createControl(Composite parent) {
		super.createControl(parent);
	}
	private void handleSyntaxColorListSelection() {
		int i = fSyntaxColorList.getSelectionIndex();
		String key = fSyntaxColorListModel[i][1];
		RGB rgb = PreferenceConverter.getColor(fOverlayStore, key);
		fSyntaxForegroundColorEditor.setColorValue(rgb);
		fBoldCheckBox.setSelection(fOverlayStore.getBoolean(key + BOLD));
	}

	private void handleProblemIndicationColorListSelection() {
		int i = fProblemIndicationList.getSelectionIndex();

		String key = fProblemIndicationColorListModel[i][1];
		RGB rgb = PreferenceConverter.getColor(fOverlayStore, key);
		fProblemIndicationForegroundColorEditor.setColorValue(rgb);

		key = fProblemIndicationColorListModel[i][2];
		fShowInTextCheckBox.setSelection(fOverlayStore.getBoolean(key));

		key = fProblemIndicationColorListModel[i][3];
		fShowInOverviewRulerCheckBox.setSelection(
			fOverlayStore.getBoolean(key));
	}

	private Control createSyntaxPage(Composite parent) {

		Composite colorComposite = new Composite(parent, SWT.NULL);
		GridLayout slayout=new GridLayout();
		colorComposite.setLayout(slayout);
	
		String labelTextFont = MTLMessages.getString("MTLEditorPreferencePage.textFont"); //$NON-NLS-1$
		addTextFontEditor(colorComposite, labelTextFont, JFaceResources.TEXT_FONT);

		labelTextFont = MTLMessages.getString("MTLEditorPreferencePage.displayedTabWidth"); //$NON-NLS-1$
		addTextField(colorComposite, labelTextFont, PreferenceConstants.EDITOR_TAB_WIDTH, 4, 0, true);

		labelTextFont = MTLMessages.getString("MTLEditorPreferencePage.printMarginColumn"); //$NON-NLS-1$
		addTextField(colorComposite, labelTextFont, PreferenceConstants.EDITOR_PRINT_MARGIN_COLUMN, 4, 0, true);

		Group backgroundComposite =
		new Group(colorComposite, SWT.SHADOW_ETCHED_IN);
		backgroundComposite.setLayout(new RowLayout());
		backgroundComposite.setText(MTLMessages.getString("MTLEditorPreferencePage.backgroundColor")); //$NON-NLS-1$

		SelectionListener backgroundSelectionListener =
			new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				boolean custom = fBackgroundCustomRadioButton.getSelection();
				fBackgroundColorButton.setEnabled(custom);
				fOverlayStore.setValue(
					PreferenceConstants.EDITOR_BACKGROUND_DEFAULT_COLOR,
					!custom);
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		};

		fBackgroundDefaultRadioButton =	new Button(backgroundComposite, SWT.RADIO | SWT.LEFT);
		fBackgroundDefaultRadioButton.setText(MTLMessages.getString("MTLEditorPreferencePage.systemDefault")); //$NON-NLS-1$
		fBackgroundDefaultRadioButton.addSelectionListener(
			backgroundSelectionListener);

		fBackgroundCustomRadioButton =
			new Button(backgroundComposite, SWT.RADIO | SWT.LEFT);
		fBackgroundCustomRadioButton.setText(MTLMessages.getString("MTLEditorPreferencePage.custom")); //$NON-NLS-1$
		fBackgroundCustomRadioButton.addSelectionListener(
			backgroundSelectionListener);

		fBackgroundColorEditor = new ColorEditor(backgroundComposite);
		fBackgroundColorButton = fBackgroundColorEditor.getButton();

		Label label = new Label(colorComposite, SWT.LEFT);
		label.setText(MTLMessages.getString("MTLEditorPreferencePage.foreground")); //$NON-NLS-1$
		label.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		Composite editorComposite = new Composite(colorComposite, SWT.NONE);
		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		layout.marginHeight = 0;
		layout.marginWidth = 0;
		editorComposite.setLayout(layout);
		GridData gd = new GridData(GridData.FILL_BOTH);
		editorComposite.setLayoutData(gd);

		fSyntaxColorList =
			new List(editorComposite, SWT.SINGLE | SWT.V_SCROLL | SWT.BORDER);
		gd = new GridData(GridData.FILL_BOTH);
		gd.heightHint = convertHeightInCharsToPixels(5);
		fSyntaxColorList.setLayoutData(gd);

		Composite stylesComposite = new Composite(editorComposite, SWT.NONE);
		layout = new GridLayout();
		layout.marginHeight = 0;
		layout.marginWidth = 0;
		layout.numColumns = 2;
		stylesComposite.setLayout(layout);
		stylesComposite.setLayoutData(new GridData(GridData.FILL_BOTH));

		label = new Label(stylesComposite, SWT.LEFT);
		label.setText(MTLMessages.getString("MTLEditorPreferencePage.color")); //$NON-NLS-1$
		gd = new GridData();
		gd.horizontalAlignment = GridData.BEGINNING;
		label.setLayoutData(gd);

		fSyntaxForegroundColorEditor = new ColorEditor(stylesComposite);
		Button foregroundColorButton = fSyntaxForegroundColorEditor.getButton();
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalAlignment = GridData.BEGINNING;
		foregroundColorButton.setLayoutData(gd);

		fBoldCheckBox = new Button(stylesComposite, SWT.CHECK);
		fBoldCheckBox.setText(MTLMessages.getString("MTLEditorPreferencePage.bold")); //$NON-NLS-1$
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalAlignment = GridData.BEGINNING;
		gd.horizontalSpan = 2;
		fBoldCheckBox.setLayoutData(gd);

		fSyntaxColorList.addSelectionListener(new SelectionListener() {
			public void widgetDefaultSelected(SelectionEvent e) {
				// do nothing
			}
			public void widgetSelected(SelectionEvent e) {
				handleSyntaxColorListSelection();
			}
		});

		foregroundColorButton.addSelectionListener(new SelectionListener() {
			public void widgetDefaultSelected(SelectionEvent e) {
				// do nothing
			}
			public void widgetSelected(SelectionEvent e) {
				int i = fSyntaxColorList.getSelectionIndex();
				String key = fSyntaxColorListModel[i][1];

				PreferenceConverter.setValue(
					fOverlayStore,
					key,
					fSyntaxForegroundColorEditor.getColorValue());
			}
		});

		fBackgroundColorButton.addSelectionListener(new SelectionListener() {
			public void widgetDefaultSelected(SelectionEvent e) {
				// do nothing
			}
			public void widgetSelected(SelectionEvent e) {
				PreferenceConverter.setValue(
					fOverlayStore,
					PreferenceConstants.EDITOR_BACKGROUND_COLOR,
					fBackgroundColorEditor.getColorValue());
			}
		});

		fBoldCheckBox.addSelectionListener(new SelectionListener() {
			public void widgetDefaultSelected(SelectionEvent e) {

			}
			public void widgetSelected(SelectionEvent e) {
				int i = fSyntaxColorList.getSelectionIndex();
				String key = fSyntaxColorListModel[i][1];
				fOverlayStore.setValue(
					key + BOLD,
					fBoldCheckBox.getSelection());
			}
		});

		return colorComposite;
	}

	/**
	 * Creates a color from the information stored in the given preference store.
	 * Returns <code>null</code> if there is no such information available.
	 */
	private Color createColor(
		IPreferenceStore store,
		String key,
		Display display) {

		RGB rgb = null;

		if (store.contains(key)) {

			if (store.isDefault(key))
				rgb = PreferenceConverter.getDefaultColor(store, key);
			else
				rgb = PreferenceConverter.getColor(store, key);

			if (rgb != null)
				return new Color(display, rgb);
		}

		return null;
	}

	// sets enabled flag for a control and all its sub-tree
	private static void setEnabled(Control control, boolean enable) {
		control.setEnabled(enable);
		if (control instanceof Composite) {
			Composite composite = (Composite) control;
			Control[] children = composite.getChildren();
			for (int i = 0; i < children.length; i++)
				setEnabled(children[i], enable);
		}
	}

	private Control createProblemIndicationPage(Composite parent) {
		Composite composite = new Composite(parent, SWT.NULL);
		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		composite.setLayout(layout);

		Label label = new Label(composite, SWT.LEFT);
		GridData gd = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		gd.horizontalSpan = 2;
		gd.heightHint = convertHeightInCharsToPixels(1) / 2;
		label.setLayoutData(gd);

		label = new Label(composite, SWT.LEFT);
		label.setText("&Marker presentation options:");
		gd = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		gd.horizontalSpan = 2;
		label.setLayoutData(gd);

		Composite editorComposite = new Composite(composite, SWT.NONE);
		layout = new GridLayout();
		layout.numColumns = 2;
		layout.marginHeight = 0;
		layout.marginWidth = 0;
		editorComposite.setLayout(layout);
		gd =
			new GridData(
				GridData.HORIZONTAL_ALIGN_FILL | GridData.FILL_VERTICAL);
		gd.horizontalSpan = 2;
		editorComposite.setLayoutData(gd);

		fProblemIndicationList =
			new List(editorComposite, SWT.SINGLE | SWT.V_SCROLL | SWT.BORDER);
		gd = new GridData(GridData.FILL_BOTH);
		gd.heightHint = convertHeightInCharsToPixels(5);
		fProblemIndicationList.setLayoutData(gd);

		Composite optionsComposite = new Composite(editorComposite, SWT.NONE);
		layout = new GridLayout();
		layout.marginHeight = 0;
		layout.marginWidth = 0;
		layout.numColumns = 2;
		optionsComposite.setLayout(layout);
		optionsComposite.setLayoutData(new GridData(GridData.FILL_BOTH));

		fShowInTextCheckBox = new Button(optionsComposite, SWT.CHECK);
		fShowInTextCheckBox.setText("Show in &text");
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalAlignment = GridData.BEGINNING;
		gd.horizontalSpan = 2;
		fShowInTextCheckBox.setLayoutData(gd);

		fShowInOverviewRulerCheckBox = new Button(optionsComposite, SWT.CHECK);
		fShowInOverviewRulerCheckBox.setText("Show in overview &ruler");
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalAlignment = GridData.BEGINNING;
		gd.horizontalSpan = 2;
		fShowInOverviewRulerCheckBox.setLayoutData(gd);

		label = new Label(optionsComposite, SWT.LEFT);
		label.setText("C&olor:");
		gd = new GridData();
		gd.horizontalAlignment = GridData.BEGINNING;
		label.setLayoutData(gd);

		fProblemIndicationForegroundColorEditor =
			new ColorEditor(optionsComposite);
		Button foregroundColorButton =
			fProblemIndicationForegroundColorEditor.getButton();
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalAlignment = GridData.BEGINNING;
		foregroundColorButton.setLayoutData(gd);

		fProblemIndicationList.addSelectionListener(new SelectionListener() {
			public void widgetDefaultSelected(SelectionEvent e) {
				// do nothing
			}

			public void widgetSelected(SelectionEvent e) {
				handleProblemIndicationColorListSelection();
			}
		});

		fShowInTextCheckBox.addSelectionListener(new SelectionListener() {
			public void widgetDefaultSelected(SelectionEvent e) {
				// do nothing
			}

			public void widgetSelected(SelectionEvent e) {
				int i = fProblemIndicationList.getSelectionIndex();
				String key = fProblemIndicationColorListModel[i][2];
				fOverlayStore.setValue(key, fShowInTextCheckBox.getSelection());
			}
		});

		fShowInOverviewRulerCheckBox
			.addSelectionListener(new SelectionListener() {
			public void widgetDefaultSelected(SelectionEvent e) {
				// do nothing
			}

			public void widgetSelected(SelectionEvent e) {
				int i = fProblemIndicationList.getSelectionIndex();
				String key = fProblemIndicationColorListModel[i][3];
				fOverlayStore.setValue(
					key,
					fShowInOverviewRulerCheckBox.getSelection());
			}
		});

		foregroundColorButton.addSelectionListener(new SelectionListener() {
			public void widgetDefaultSelected(SelectionEvent e) {
				// do nothing
			}

			public void widgetSelected(SelectionEvent e) {
				int i = fProblemIndicationList.getSelectionIndex();
				String key = fProblemIndicationColorListModel[i][1];
				PreferenceConverter.setValue(
					fOverlayStore,
					key,
					fProblemIndicationForegroundColorEditor.getColorValue());
			}
		});

		return composite;
	}


	private static void indent(Control control) {
		GridData gridData = new GridData();
		gridData.horizontalIndent = 20;
		control.setLayoutData(gridData);
	}

	private static void createDependency(
		final Button master,
		final Control slave) {
		indent(slave);
		master.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				slave.setEnabled(master.getSelection());
			}

			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
	}

	private Control createContentAssistPage(Composite parent) {

		Composite contentAssistComposite = new Composite(parent, SWT.NULL);
		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		contentAssistComposite.setLayout(layout);
		String label;
		
		label = MTLMessages.getString("MTLEditorPreferencePage.enableAutoActivation"); //$NON-NLS-1$
		final Button autoactivation =
			addCheckBox(
				contentAssistComposite,
				label,
				PreferenceConstants.CODEASSIST_AUTOACTIVATION,
				0);

		label = MTLMessages.getString("MTLEditorPreferencePage.autoActivationDelay"); //$NON-NLS-1$
		fAutoInsertDelayText =
			addTextField(
				contentAssistComposite,
				label,
				PreferenceConstants.CODEASSIST_AUTOACTIVATION_DELAY,
				4,
				0,
				true);

			label = MTLMessages.getString("MTLEditorPreferencePage.backgroundForCompletionProposals"); //$NON-NLS-1$
		addColorButton(
			contentAssistComposite,
			label,
			PreferenceConstants.CODEASSIST_PROPOSALS_BACKGROUND,
			0);

		label = MTLMessages.getString("MTLEditorPreferencePage.foregroundForCompletionProposals"); //$NON-NLS-1$
		addColorButton(
			contentAssistComposite,
			label,
			PreferenceConstants.CODEASSIST_PROPOSALS_FOREGROUND,
			0);

		autoactivation.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				updateAutoactivationControls();
			}
		});
		return contentAssistComposite;
	}

	/*
	 * @see PreferencePage#createContents(Composite)
	 */
	protected Control createContents(Composite parent) {

		fOverlayStore.load();
		fOverlayStore.start();

		TabFolder folder = new TabFolder(parent, SWT.NONE);
	
		folder.setLayout(new TabFolderLayout());
		folder.setLayoutData(new GridData(GridData.FILL_BOTH));

		TabItem item = new TabItem(folder, SWT.NONE);
		item.setText(MTLMessages.getString("MTLEditorPreferencePage.syntax.appearance")); //$NON-NLS-1$
		item.setControl(createSyntaxPage(folder));

		item = new TabItem(folder, SWT.NONE);
		item.setText(MTLMessages.getString("MTLEditorPreferencePage.codeAssist")); //$NON-NLS-1$
		item.setControl(createContentAssistPage(folder));

		item = new TabItem(folder, SWT.NONE);
		item.setText(MTLMessages.getString("MTLEditorPreferencePage.problemIndicationTab.title")); //$NON-NLS-1$
		item.setControl(createProblemIndicationPage(folder));

		initialize();

		return folder;
	}

	private void initialize() {
		fFontEditor.setPreferenceStore(getPreferenceStore());
		
		fFontEditor.setPreferencePage(this);
		fFontEditor.load();
		
		initializeFields();

		for (int i = 0; i < fSyntaxColorListModel.length; i++)
			fSyntaxColorList.add(fSyntaxColorListModel[i][0]);
			
		fSyntaxColorList.getDisplay().asyncExec(new Runnable() {
			public void run() {
				if (fSyntaxColorList != null
					&& !fSyntaxColorList.isDisposed()) {
					fSyntaxColorList.select(0);
					handleSyntaxColorListSelection();
				}
			}
		});
		for (int i = 0; i < fProblemIndicationColorListModel.length; i++)
			fProblemIndicationList.add(fProblemIndicationColorListModel[i][0]);

		fProblemIndicationList.getDisplay().asyncExec(new Runnable() {
			public void run() {
				if (fProblemIndicationList != null
					&& !fProblemIndicationList.isDisposed()) {
					fProblemIndicationList.select(0);
					handleProblemIndicationColorListSelection();
				}
			}
		});
	}

	private void initializeFields() {

		Iterator e = fColorButtons.keySet().iterator();
		while (e.hasNext()) {
			ColorEditor c = (ColorEditor) e.next();
			String key = (String) fColorButtons.get(c);
			RGB rgb = PreferenceConverter.getColor(fOverlayStore, key);
			c.setColorValue(rgb);
		}
		e = fCheckBoxes.keySet().iterator();
		while (e.hasNext()) {
			Button b = (Button) e.next();
			String key = (String) fCheckBoxes.get(b);
			b.setSelection(fOverlayStore.getBoolean(key));
		}
		e = fTextFields.keySet().iterator();
		while (e.hasNext()) {
			Text t = (Text) e.next();
			String key = (String) fTextFields.get(t);
			t.setText(fOverlayStore.getString(key));
		}
		RGB rgb =
			PreferenceConverter.getColor(
				fOverlayStore,
				PreferenceConstants.EDITOR_BACKGROUND_COLOR);
		fBackgroundColorEditor.setColorValue(rgb);
		boolean default_ =
			fOverlayStore.getBoolean(
				PreferenceConstants.EDITOR_BACKGROUND_DEFAULT_COLOR);
		fBackgroundDefaultRadioButton.setSelection(default_);
		fBackgroundCustomRadioButton.setSelection(!default_);
		fBackgroundColorButton.setEnabled(!default_);
		updateAutoactivationControls();
	}

	private void updateAutoactivationControls() {
		boolean autoactivation =
			fOverlayStore.getBoolean(PreferenceConstants.CODEASSIST_AUTOACTIVATION);
			fAutoInsertDelayText.setEnabled(autoactivation);
		}

	/*
	 * @see PreferencePage#performOk()
	 */
	public boolean performOk() {
		fFontEditor.store();
		fOverlayStore.propagate();
		MTLPlugin.getDefault().savePluginPreferences();
		return true;
	}

	/*
	 * @see PreferencePage#performDefaults()
	 */
	protected void performDefaults() {

		fFontEditor.loadDefault();

		fOverlayStore.loadDefaults();
		initializeFields();
		handleSyntaxColorListSelection();
		handleProblemIndicationColorListSelection();
		super.performDefaults();

	//	fPreviewViewer.invalidateTextPresentation();
	}

	/*
	 * @see DialogPage#dispose()
	 */
	public void dispose() {
	
//		fFontEditor.setPreferencePage(null);
//		fFontEditor.setPreferenceStore(null);
//
//		if (fOverlayStore != null) {
//			fOverlayStore.stop();
//			fOverlayStore = null;
//		}

		super.dispose();
	}

	private Control addColorButton(
		Composite composite,
		String label,
		String key,
		int indentation) {

		Label labelControl = new Label(composite, SWT.NONE);
		labelControl.setText(label);

		GridData gd = new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING);
		gd.horizontalIndent = indentation;
		labelControl.setLayoutData(gd);

		ColorEditor editor = new ColorEditor(composite);
		Button button = editor.getButton();
		button.setData(editor);

		gd = new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING);
		button.setLayoutData(gd);
		button.addSelectionListener(fColorButtonListener);

		fColorButtons.put(editor, key);

		return composite;
	}

	private Button addCheckBox(
		Composite parent,
		String label,
		String key,
		int indentation) {
		Button checkBox = new Button(parent, SWT.CHECK);
		checkBox.setText(label);

		GridData gd = new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING);
		gd.horizontalIndent = indentation;
		gd.horizontalSpan = 2;
		checkBox.setLayoutData(gd);
		checkBox.addSelectionListener(fCheckBoxListener);

		fCheckBoxes.put(checkBox, key);

		return checkBox;
	}

	private Control addTextField(
		Composite composite,
		String label,
		String key,
		int textLimit,
		int indentation,
		boolean isNumber) {

		Label labelControl = new Label(composite, SWT.NONE);
		labelControl.setText(label);
		GridData gd = new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING);
		gd.horizontalIndent = indentation;
		labelControl.setLayoutData(gd);

		Text textControl = new Text(composite, SWT.BORDER | SWT.SINGLE);
		gd = new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING);
		gd.widthHint = convertWidthInCharsToPixels(textLimit + 1);
		textControl.setLayoutData(gd);
		textControl.setTextLimit(textLimit);
		fTextFields.put(textControl, key);
		if (isNumber) {
			fNumberFields.add(textControl);
			textControl.addModifyListener(fNumberFieldListener);
		} else {
			textControl.addModifyListener(fTextFieldListener);
		}

		return textControl;
	}

	private void addTextFontEditor(
		Composite parent,
		String label,
		String key) {

		Composite editorComposite = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout();
		layout.numColumns = 3;
		editorComposite.setLayout(layout);
		fFontEditor =
			new WorkbenchChainedTextFontFieldEditor(
				key,
				label,
				editorComposite);
		fFontEditor.setChangeButtonText(MTLMessages.getString("MTLEditorPreferencePage.change")); //$NON-NLS-1$

		GridData gd = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		gd.horizontalSpan = 2;
		editorComposite.setLayoutData(gd);
	}

	
	  private void numberFieldChanged(Text textControl) {
		String number = textControl.getText();
		IStatus status = validatePositiveNumber(number);
		if (!status.matches(IStatus.ERROR))
		  fOverlayStore.setValue((String) fTextFields.get(textControl), number);
		updateStatus(status);
	  }
	
	  private IStatus validatePositiveNumber(String number) {
		StatusInfo status = new StatusInfo();
		if (number.length() == 0) {
		  status.setError(MTLMessages.getString("MTLEditorPreferencePage.empty_input")); //$NON-NLS-1$
		} else {
		  try {
			int value = Integer.parseInt(number);
			if (value < 0)
			  status.setError(MTLMessages.getFormattedString("MTLEditorPreferencePage.invalid_input", number)); //$NON-NLS-1$
		  } catch (NumberFormatException e) {
			status.setError(MTLMessages.getFormattedString("MTLEditorPreferencePage.invalid_input", number)); //$NON-NLS-1$
		  }
		}
		return status;
	  }
	
	private void updateStatus(IStatus status) {
		if (!status.matches(IStatus.ERROR)) {
			for (int i = 0; i < fNumberFields.size(); i++) {
				Text text = (Text) fNumberFields.get(i);
					IStatus s = validatePositiveNumber(text.getText());
					status = StatusUtils.getMoreSevere(s, status);
			}
		}
		setValid(!status.matches(IStatus.ERROR));
		StatusUtils.applyToStatusLine(this, status);
	}

	/**
	 * @deprecated Inline to avoid reference to preference page
	 */
	public static boolean indicateQuixFixableProblems() {
		return PreferenceConstants.getPreferenceStore().getBoolean(
			PreferenceConstants.EDITOR_CORRECTION_INDICATION);
	}
}
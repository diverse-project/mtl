package org.inria.mtl.plugin.editors.completion.template;

import java.text.DateFormat;
import java.util.Calendar;

/**
 * Global variables which are available in any context.
 */
public class GlobalVariables {

	/**
	 * The cursor variable determines the cursor placement after template edition.
	 */
	static class Cursor extends SimpleTemplateVariable {
		public Cursor() {
			super(TemplateMessages.getString("GlobalVariables.variable.name.cursor"), TemplateMessages.getString("GlobalVariables.variable.description.cursor")); //$NON-NLS-1$ //$NON-NLS-2$
			setEvaluationString(""); //$NON-NLS-1$
			setResolved(true);
		}
	}

	/**
	 * The dollar variable inserts an escaped dollar symbol.
	 */
	static class Dollar extends SimpleTemplateVariable {
		public Dollar() {
			super(TemplateMessages.getString("GlobalVariables.variable.name.dollar"), TemplateMessages.getString("GlobalVariables.variable.description.dollar")); //$NON-NLS-1$ //$NON-NLS-2$
			setEvaluationString("$"); //$NON-NLS-1$
			setResolved(true);
		}
	}

	/**
	 * The date variable evaluates to the current date.
	 */
	static class Date extends SimpleTemplateVariable {
		public Date() {
			super(TemplateMessages.getString("GlobalVariables.variable.name.date"), TemplateMessages.getString("GlobalVariables.variable.description.date")); //$NON-NLS-1$ //$NON-NLS-2$
			setResolved(true);
		}
		public String evaluate(TemplateContext context) {
			return DateFormat.getDateInstance().format(new java.util.Date());
		}
	}		
	
  /**
   * The year variable evaluates to the current year.
   */
  static class Year extends SimpleTemplateVariable {
	public Year() {
	  super(TemplateMessages.getString("GlobalVariables.variable.name.year"), TemplateMessages.getString("GlobalVariables.variable.description.year")); //$NON-NLS-1$ //$NON-NLS-2$
	  setResolved(true);
	}
	public String evaluate(TemplateContext context) {
	  return Integer.toString(Calendar.getInstance().get(Calendar.YEAR));
	}
  }
	/**
	 * The time variable evaluates to the current time.
	 */
	static class Time extends SimpleTemplateVariable {
		public Time() {
			super(TemplateMessages.getString("GlobalVariables.variable.name.time"), TemplateMessages.getString("GlobalVariables.variable.description.time")); //$NON-NLS-1$ //$NON-NLS-2$
			setResolved(true);
		}
		public String evaluate(TemplateContext context) {
			return DateFormat.getTimeInstance().format(new java.util.Date());
		}
	}

	/**
	 * The user variable evaluates to the current user.
	 */
	static class User extends SimpleTemplateVariable {
		public User() {
			super(TemplateMessages.getString("GlobalVariables.variable.name.user"), TemplateMessages.getString("GlobalVariables.variable.description.user")); //$NON-NLS-1$ //$NON-NLS-2$
			setResolved(true);
		}
		public String evaluate(TemplateContext context) {
			return System.getProperty("user.name"); //$NON-NLS-1$
		}	
	}
}

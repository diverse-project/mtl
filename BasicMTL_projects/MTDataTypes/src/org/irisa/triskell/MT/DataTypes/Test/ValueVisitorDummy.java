/*
 * Created on 16.05.2003
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package org.irisa.triskell.MT.DataTypes.Test;

import junit.framework.TestCase;
import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.*;
import org.irisa.triskell.MT.DataTypes.Java.ValueVisitor;

/**
 * @author rumpe
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class ValueVisitorDummy 
		implements org.irisa.triskell.MT.DataTypes.Java.ValueVisitor
	{
		public void visitValue(
			org.irisa.triskell.MT.DataTypes.Java.Value value)
		{
		}

		public void visitPrimitiveValue(
			org.irisa.triskell.MT.DataTypes.Java.PrimitiveValue value)
		{
		}

		public void visitBooleanValue(
			org.irisa.triskell.MT.DataTypes.Java.BooleanValue value)
		{
		}

        public boolean calledVisitStringValue = false;
		public void visitStringValue(
			org.irisa.triskell.MT.DataTypes.Java.StringValue value)
		{
			calledVisitStringValue = true;
		}

		public void visitRealValue(
			org.irisa.triskell.MT.DataTypes.Java.RealValue value)
		{
		}

		public void visitIntegerValue(
			org.irisa.triskell.MT.DataTypes.Java.IntegerValue value)
		{
		}

		public boolean calledVisitEnumValue = false;
		public void visitEnumValue(
			org.irisa.triskell.MT.DataTypes.Java.EnumValue value)
		{
			calledVisitEnumValue = true;
		}

		public void visitCollectionValue(
			org.irisa.triskell.MT.DataTypes.Java.CollectionValue value)
		{
		}

		public void visitModelElementValue(
			org.irisa.triskell.MT.DataTypes.Java.ModelElementValue value)
		{
		}

		public void visitTupleValue(
			org.irisa.triskell.MT.DataTypes.Java.TupleValue value)
		{
		}

		public void visitVoidValue(
			org.irisa.triskell.MT.DataTypes.Java.VoidValue value)
		{
		}

		public void visitNullValue(			org.irisa.triskell.MT.DataTypes.Java.NullValue value)
		{
		}
}

package org.irisa.triskell.MT.DataTypes.Java;

import java.io.*;
import org.irisa.triskell.MT.DataTypes.Java.defaultImpl.*;
import org.irisa.triskell.MT.DataTypes.Java.commands.*;

/**
  * A facility to visit values.
  */
public interface ValueVisitor 
{

     void visitValue(
        org.irisa.triskell.MT.DataTypes.Java.Value value);

     void visitPrimitiveValue(
        org.irisa.triskell.MT.DataTypes.Java.PrimitiveValue value);

     void visitBooleanValue(
        org.irisa.triskell.MT.DataTypes.Java.BooleanValue value);

     void visitStringValue(
        org.irisa.triskell.MT.DataTypes.Java.StringValue value);

     void visitRealValue(
        org.irisa.triskell.MT.DataTypes.Java.RealValue value);

     void visitIntegerValue(
        org.irisa.triskell.MT.DataTypes.Java.IntegerValue value);

     void visitEnumValue(
        org.irisa.triskell.MT.DataTypes.Java.EnumValue value);

     void visitCollectionValue(
        org.irisa.triskell.MT.DataTypes.Java.CollectionValue value);

     void visitModelElementValue(
        org.irisa.triskell.MT.DataTypes.Java.ModelElementValue value);

     void visitRepositoryAPIValue(
            org.irisa.triskell.MT.DataTypes.Java.RepositoryAPIValue value);

     void visitTupleValue(
        org.irisa.triskell.MT.DataTypes.Java.TupleValue value);

     void visitVoidValue(
        org.irisa.triskell.MT.DataTypes.Java.VoidValue value);

     void visitNullValue(
        org.irisa.triskell.MT.DataTypes.Java.NullValue value);

     void visitTypeValue(
        org.irisa.triskell.MT.DataTypes.Java.TypeValue value);
}

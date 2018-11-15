/*
 * Copyright (c) 2011, 2014, Oracle and/or its affiliates. All rights reserved.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */

package javafx.scene.control.cell;

/**
Builder class for javafx.scene.control.cell.TextFieldTreeCell
@see javafx.scene.control.cell.TextFieldTreeCell
@deprecated This class is deprecated and will be removed in the next version
* @since JavaFX 2.2
*/
@javax.annotation.Generated("Generated by javafx.builder.processor.BuilderProcessor")
@Deprecated
public class TextFieldTreeCellBuilder<T, B extends javafx.scene.control.cell.TextFieldTreeCellBuilder<T, B>> extends javafx.scene.control.TreeCellBuilder<T, B> {
    protected TextFieldTreeCellBuilder() {
    }

    /** Creates a new instance of TextFieldTreeCellBuilder. */
    @SuppressWarnings({"deprecation", "rawtypes", "unchecked"})
    public static <T> javafx.scene.control.cell.TextFieldTreeCellBuilder<T, ?> create() {
        return new javafx.scene.control.cell.TextFieldTreeCellBuilder();
    }

    private boolean __set;
    public void applyTo(javafx.scene.control.cell.TextFieldTreeCell<T> x) {
        super.applyTo(x);
        if (__set) x.setConverter(this.converter);
    }

    private javafx.util.StringConverter<T> converter;
    /**
    Set the value of the {@link javafx.scene.control.cell.TextFieldTreeCell#getConverter() converter} property for the instance constructed by this builder.
    */
    @SuppressWarnings("unchecked")
    public B converter(javafx.util.StringConverter<T> x) {
        this.converter = x;
        __set = true;
        return (B) this;
    }

    /**
    Make an instance of {@link javafx.scene.control.cell.TextFieldTreeCell} based on the properties set on this builder.
    */
    public javafx.scene.control.cell.TextFieldTreeCell<T> build() {
        javafx.scene.control.cell.TextFieldTreeCell<T> x = new javafx.scene.control.cell.TextFieldTreeCell<T>();
        applyTo(x);
        return x;
    }
}

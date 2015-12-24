package com.jui;

import java.awt.*;

/**
 * LineBorder
 *
 * @author Ian
 * @version 1.0
 */
public class LineBorder implements Border {
    private final com.jui.Insets insets;
    private final Color color;

    public LineBorder(int size, Color color) {
        if (size <= 0 || color == null)
            throw new IllegalArgumentException("Size must be > 0 and color must != null.");
        this.insets = new com.jui.Insets(size, size, size, size);
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    @Override
    public com.jui.Insets getPadding(com.jui.Component component) {
        return insets;
    }

    @Override
    public void render(com.jui.Component component, Graphics graphics, int x, int y, int width, int height) {
        Color old = graphics.getColor();
        int dw = component.getWidth();
        int dh = component.getHeight();
        graphics.setColor(color);
        for (int i = 0; i < insets.left; i++)
            graphics.drawRect(i, i, dw - (i * 2) - 1, dh - (i * 2) - 1);
        graphics.setColor(old);
    }
}

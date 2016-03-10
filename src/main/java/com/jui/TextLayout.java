package com.jui;

import sun.font.FontDesignMetrics;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

/**
 * TextLayout
 *
 * @author Ian Caffey
 * @since 1.0
 */
public class TextLayout implements com.jui.LayoutManager {
    @Override
    public boolean layout(com.jui.Component component) {
        return true;
    }

    @Override
    public Dimension size(com.jui.Component component) {
        if (!(component instanceof Text))
            throw new IllegalArgumentException("Text layout must only be used on texts.");
        int width = 0;
        int height = 0;
        Text text = (Text) component;
        com.jui.Insets insets = text.getPadding();
        if (insets != null) {
            width += insets.left + insets.right;
            height += insets.top + insets.bottom;
        }
        DropShadow shadow = text.getDropShadow();
        if (shadow != null) {
            width += shadow.getOffsetX();
            height += shadow.getOffsetY();
        }
        Font font = text.getFont();
        if (font != null) {
            String string = text.getText();
            if (string != null && string.length() > 0) {
                FontDesignMetrics metrics = FontDesignMetrics.getMetrics(font);
                if (metrics != null) {
                    Graphics graphics = null;
                    BufferedImage cache = text.getCachedImage();
                    if (cache != null)
                        graphics = cache.getGraphics();
                    Rectangle2D rect = metrics.getStringBounds(string, graphics);
                    width += rect.getWidth();
                    height += rect.getHeight();
                }
            }
        }
        return new Dimension(width, height);
    }
}

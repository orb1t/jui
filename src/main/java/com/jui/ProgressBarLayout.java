package com.jui;

import java.awt.*;

/**
 * ProgressBarLayout
 *
 * @author Ian Caffey
 * @since 1.0
 */
public class ProgressBarLayout implements com.jui.LayoutManager {
    @Override
    public boolean layout(com.jui.Component component) {
        if (!(component instanceof ProgressBar))
            throw new IllegalArgumentException("Text layout must only be used on texts.");
        ProgressBar bar = (ProgressBar) component;
        Text text = bar.getText();
        if (text == null)
            return false;
        int x = 0;
        int y = 0;
        int width = bar.getWidth();
        int height = bar.getHeight();
        com.jui.Insets insets = bar.getPadding();
        if (insets != null) {
            x += insets.left;
            y += insets.top;
            width -= insets.left + insets.right;
            height -= insets.top + insets.bottom;
        }
        int tw = text.getWidth();
        int th = text.getHeight();
        x += (int) Math.round((width - tw) / 2.0d);
        y += (int) Math.round((height - th) / 2.0d);
        int tx = text.getX();
        int ty = text.getY();
        if (x == tx && y == ty)
            return false;
        text.setLocation(x, y);
        return true;
    }

    @Override
    public Dimension size(com.jui.Component component) {
        if (!(component instanceof ProgressBar))
            throw new IllegalArgumentException("Text layout must only be used on texts.");
        int width = 0;
        int height = 0;
        ProgressBar bar = (ProgressBar) component;
        com.jui.Insets insets = bar.getPadding();
        if (insets != null) {
            width += insets.left + insets.right;
            height += insets.top + insets.bottom;
        }
        Text text = bar.getText();
        if (text != null) {
            Dimension preferred = text.getPreferredSize();
            if (preferred != null) {
                width += preferred.width;
                height += preferred.height;
            }
        }
        width += bar.getHorizontalSpacing() * 2;
        height += bar.getVerticalSpacing() * 2;
        return new Dimension(width, height);
    }
}

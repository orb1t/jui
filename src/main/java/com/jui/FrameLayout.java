package com.jui;

import java.awt.*;

/**
 * FrameLayout
 *
 * @author Ian
 * @version 1.0
 */
public class FrameLayout implements LayoutManager {
    @Override
    public boolean layout(com.jui.Component component) {
        if (!(component instanceof com.jui.Frame))
            throw new IllegalArgumentException("Frame layout must only be used with frames.");
        com.jui.Frame frame = (com.jui.Frame) component;
        int x = 0;
        int y = 0;
        int dy = 0;
        int width = frame.getWidth();
        int height = frame.getHeight();
        boolean changed = false;
        Insets insets = frame.getPadding();
        if (insets != null) {
            x += insets.left;
            y += insets.top;
            width -= insets.left + insets.right;
            height -= insets.top + insets.bottom;
        }
        Header header = frame.getHeader();
        if (header != null) {
            Dimension dimension = header.getPreferredSize();
            int h = dimension == null ? header.getHeight() : dimension.height;
            if (header.getX() != x || header.getY() != y || header.getWidth() != width || header.getHeight() != height)
                changed = true;
            header.setBounds(x, y, width, h);
            dy += h;
        }
        com.jui.Component content = frame.getContent();
        if (content != null) {
            if (content.getX() != x || content.getY() != y || content.getWidth() != width || content.getHeight() != height)
                changed = true;
            content.setBounds(x, y + dy, width, height - dy);
        }
        return changed;
    }

    @Override
    public Dimension size(com.jui.Component component) {
        if (!(component instanceof com.jui.Frame))
            throw new IllegalArgumentException("Frame layout must only be used with frames.");
        com.jui.Frame frame = (com.jui.Frame) component;
        int width = 0;
        int height = 0;
        Header header = frame.getHeader();
        if (header != null) {
            Dimension preferred = header.getPreferredSize();
            if (preferred != null) {
                width = Math.max(width, preferred.width);
                height += preferred.height;
            }
        }
        com.jui.Component content = frame.getContent();
        if (content != null) {
            Dimension preferred = content.getPreferredSize();
            if (preferred != null) {
                width = Math.max(width, preferred.width);
                height += preferred.height;
            }
        }
        Insets insets = frame.getPadding();
        if (insets != null) {
            width += insets.left + insets.right;
            height += insets.top + insets.bottom;
        }
        return new Dimension(width, height);
    }
}

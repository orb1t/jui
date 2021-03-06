package com.iancaffey.jui;

import java.awt.*;
import java.util.Set;

/**
 * HeaderLayout
 *
 * @author Ian Caffey
 * @since 1.0
 */
public class HeaderLayout implements com.iancaffey.jui.LayoutManager {
    @Override
    public boolean layout(Component component) {
        if (!(component instanceof Header))
            throw new IllegalArgumentException("Header layout must only be used with headers.");
        Header header = (Header) component;
        int x = 0;
        int y = 0;
        int width = header.getWidth();
        int height = header.getHeight();
        boolean changed = false;
        com.iancaffey.jui.Insets insets = header.getPadding();
        if (insets != null) {
            x += insets.left;
            y += insets.top;
            width -= insets.left + insets.right;
            height -= insets.top + insets.bottom;
        }
        Text text = header.getText();
        if (text != null) {
            Dimension preferred = text.getPreferredSize();
            int nx = x + (int) Math.round((width - (preferred == null ? text.getWidth() : preferred.width)) / 2.0d);
            int ny = y + (int) Math.round((height - (preferred == null ? text.getHeight() : preferred.height)) / 2.0d);
            text.setLocation(nx, ny);
        }
        Set<Button> leftButtons = header.getLeftButtons();
        if (leftButtons != null) {
            int dx = 0;
            for (Button b : leftButtons) {
                Dimension preferred = b.getPreferredSize();
                int nx = x + dx;
                int ny = y + (int) Math.round((height - (preferred == null ? b.getHeight() : preferred.height)) / 2.0d);
                if (b.getX() != nx || b.getY() != ny)
                    changed = true;
                b.setLocation(nx, ny);
                dx += preferred == null ? b.getWidth() : preferred.width;
            }
        }
        Button[] rightButtons = header.getRightButtonArray();
        if (rightButtons != null) {
            int dx = 0;
            for (int i = rightButtons.length - 1; i >= 0; --i) {
                Button b = rightButtons[i];
                Dimension preferred = b.getPreferredSize();
                int w = preferred == null ? b.getWidth() : preferred.width;
                int nx = x + width - dx - w;
                int ny = y + (int) Math.round((height - (preferred == null ? b.getHeight() : preferred.height)) / 2.0d);
                if (b.getX() != nx || b.getY() != ny)
                    changed = true;
                b.setLocation(nx, ny);
                dx += w;
            }
        }
        return changed;
    }

    @Override
    public Dimension size(Component component) {
        if (!(component instanceof Header))
            throw new IllegalArgumentException("Header layout must only be used with headers.");
        Header header = (Header) component;
        int maxHeight = 0;
        int width = 0;
        Text text = header.getText();
        if (text != null) {
            Dimension preferred = text.getPreferredSize();
            maxHeight = Math.max(maxHeight, preferred == null ? text.getHeight() : preferred.height);
            width += preferred == null ? text.getWidth() : preferred.width;
        }
        int sideWidth = 0;
        Set<Button> leftButtons = header.getLeftButtons();
        if (leftButtons != null) {
            for (Button b : leftButtons) {
                Dimension preferred = b.getPreferredSize();
                maxHeight = Math.max(maxHeight, preferred == null ? b.getHeight() : preferred.height);
                sideWidth = Math.max(sideWidth, preferred == null ? b.getWidth() : preferred.width);
            }
        }
        Set<Button> rightButtons = header.getRightButtons();
        if (rightButtons != null) {
            for (Button b : rightButtons) {
                Dimension preferred = b.getPreferredSize();
                maxHeight = Math.max(maxHeight, preferred == null ? b.getHeight() : preferred.height);
                sideWidth = Math.max(sideWidth, preferred == null ? b.getWidth() : preferred.width);
            }
        }
        width += sideWidth * 2;
        com.iancaffey.jui.Insets insets = header.getPadding();
        if (insets != null) {
            width += insets.left + insets.right;
            maxHeight += insets.top + insets.bottom;
        }
        return new Dimension(width, maxHeight);
    }
}

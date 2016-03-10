package com.iancaffey.jui;

import java.awt.*;

/**
 * RootPaneLayout
 *
 * @author Ian Caffey
 * @since 1.0
 */
public class RootPaneLayout implements LayoutManager {
    @Override
    public boolean layout(Component component) {
        if (!(component instanceof RootPane))
            throw new IllegalArgumentException("Root pane layout may only be used with root panes.");
        return false;
    }

    @Override
    public Dimension size(Component component) {
        if (!(component instanceof RootPane))
            throw new IllegalArgumentException("Root pane layout may only be used with root panes.");
        RootPane pane = (RootPane) component;
        int width = 0;
        int height = 0;
        for (Frame f : pane.getFrames()) {
            if (!f.isVisible())
                continue;
            Dimension preferred = f.getPreferredSize();
            if (preferred == null)
                continue;
            width = Math.max(width, f.getX() + preferred.width);
            height = Math.max(height, f.getY() + preferred.height);
        }
        Insets insets = component.getPadding();
        if (insets != null) {
            width += insets.left + insets.right;
            height += insets.top + insets.bottom;
        }
        return new Dimension(width, height);
    }
}

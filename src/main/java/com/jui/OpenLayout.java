package com.jui;

import java.awt.*;

/**
 * OpenLayout
 *
 * @author Ian Caffey
 * @since 1.0
 */
public class OpenLayout implements com.jui.LayoutManager {
    @Override
    public boolean layout(com.jui.Component component) {
        if (!(component instanceof com.jui.Container))
            throw new IllegalArgumentException("Open layout may only be applied to containers.");
        return false;
    }

    @Override
    public Dimension size(com.jui.Component component) {
        if (!(component instanceof com.jui.Container))
            throw new IllegalArgumentException("Open layout may only be applied to containers.");
        com.jui.Container container = (com.jui.Container) component;
        int width = 0;
        int height = 0;
        for (com.jui.Component c : container.getComponents()) {
            if (!c.isVisible())
                continue;
            Dimension preferred = c.getPreferredSize();
            if (preferred == null)
                continue;
            width = Math.max(width, c.getX() + preferred.width);
            height = Math.max(height, c.getY() + preferred.height);
        }
        com.jui.Insets insets = component.getPadding();
        if (insets != null) {
            width += insets.left + insets.right;
            height += insets.top + insets.bottom;
        }
        return new Dimension(width, height);
    }
}

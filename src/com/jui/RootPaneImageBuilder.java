package com.jui;

import java.awt.*;

/**
 * RootPaneImageBuilder
 *
 * @author Ian
 * @version 1.0
 */
public class RootPaneImageBuilder extends ComponentImageBuilder {
    @Override
    public void paintComponent(com.jui.Component component, Graphics graphics, int x, int y, int width, int height) {
        super.paintComponent(component, graphics, x, y, width, height);
        if (!(component instanceof RootPane))
            throw new IllegalArgumentException("Root pane image builders must only be used on root panes.");
        Rectangle rectangle = new Rectangle(x, y, width, height);
        com.jui.Component clickFocus = ((RootPane) component).getClickFocus();
        Rectangle clickFocusBounds;
        if (clickFocus != null && clickFocus.isVisible() && (clickFocusBounds = clickFocus.getBounds()) != null && (rectangle.intersects(clickFocusBounds) || rectangle.contains(clickFocusBounds)))
            clickFocus.paint(graphics);
        for (com.jui.Frame child : ((RootPane) component).getFrames()) {
            if (child == clickFocus || !child.isVisible())
                continue;
            Rectangle childBounds = child.getBounds();
            if (!rectangle.intersects(childBounds) && !rectangle.contains(childBounds))
                continue;
            child.paint(graphics);
        }
    }

    @Override
    public void paintOverlay(com.jui.Component component, Graphics graphics, int x, int y, int width, int height) {
        super.paintOverlay(component, graphics, x, y, width, height);
        if (!(component instanceof RootPane))
            throw new IllegalArgumentException("Root pane image builders must only be used on root panes.");
    }
}

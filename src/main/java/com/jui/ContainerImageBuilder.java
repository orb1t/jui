package com.jui;

import java.awt.*;

/**
 * ContainerImageBuilder
 *
 * @author Ian Caffey
 * @since 1.0
 */
public class ContainerImageBuilder extends ComponentImageBuilder {
    @Override
    public void paintComponent(com.jui.Component component, Graphics graphics, int x, int y, int width, int height) {
        super.paintComponent(component, graphics, x, y, width, height);
        if (!(component instanceof com.jui.Container))
            throw new IllegalArgumentException("Container image builders must only be used on containers.");
        Rectangle rectangle = new Rectangle(x, y, width, height);
        com.jui.Component clickFocus = ((com.jui.Container) component).getClickFocus();
        Rectangle clickFocusBounds;
        if (clickFocus != null && clickFocus.isVisible() && (clickFocusBounds = clickFocus.getBounds()) != null && (rectangle.intersects(clickFocusBounds) || rectangle.contains(clickFocusBounds)))
            clickFocus.paint(graphics);
        for (com.jui.Component child : ((com.jui.Container) component).getComponents()) {
            if (child == clickFocus || !child.isVisible())
                continue;
            Rectangle childBounds = child.getBounds();
            if (!rectangle.intersects(childBounds) && !rectangle.contains(childBounds))
                continue;
            child.paint(graphics);
        }
    }
}

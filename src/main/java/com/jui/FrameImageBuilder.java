package com.jui;

import java.awt.*;

/**
 * FrameImageBuilder
 *
 * @author Ian
 * @version 1.0
 */
public class FrameImageBuilder extends ComponentImageBuilder {
    @Override
    public void paintComponent(com.jui.Component component, Graphics graphics, int x, int y, int width, int height) {
        super.paintComponent(component, graphics, x, y, width, height);
        if (!(component instanceof com.jui.Frame))
            throw new IllegalArgumentException("Frame image builders must only be used on frames.");
        Rectangle rectangle = new Rectangle(x, y, width, height);
        com.jui.Frame frame = (com.jui.Frame) component;
        Header header = frame.getHeader();
        if (header != null && header.isVisible()) {
            Rectangle bounds = header.getBounds();
            if (rectangle.intersects(bounds) || rectangle.contains(bounds))
                header.paint(graphics);
        }
        com.jui.Component content = frame.getContent();
        if (content != null && content.isVisible()) {
            Rectangle bounds = content.getBounds();
            if (rectangle.intersects(bounds) || rectangle.contains(bounds))
                content.paint(graphics);
        }
    }
}

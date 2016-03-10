package com.iancaffey.jui;

import java.awt.*;

/**
 * LabelImageBuilder
 *
 * @author Ian Caffey
 * @since 1.0
 */
public class LabelImageBuilder extends ComponentImageBuilder {
    @Override
    public void paintComponent(Component component, Graphics graphics, int x, int y, int width, int height) {
        super.paintComponent(component, graphics, x, y, width, height);
        if (!(component instanceof Label))
            throw new IllegalArgumentException("Label image builders must only be used on labels.");
        Rectangle rectangle = new Rectangle(x, y, width, height);
        Label label = (Label) component;
        Picture picture = label.getPicture();
        if (picture != null && picture.isVisible()) {
            Rectangle bounds = picture.getBounds();
            if (rectangle.intersects(bounds) || rectangle.contains(bounds))
                picture.paint(graphics);
        }
        Text text = label.getText();
        if (text != null && text.isVisible()) {
            Rectangle bounds = text.getBounds();
            if (rectangle.intersects(bounds) || rectangle.contains(bounds))
                text.paint(graphics);
        }
    }
}

package com.iancaffey.jui;

import java.awt.*;

/**
 * PictureImageBuilder
 *
 * @author Ian Caffey
 * @since 1.0
 */
public class PictureImageBuilder extends ComponentImageBuilder {
    @Override
    public void paintComponent(Component component, Graphics graphics, int x, int y, int width, int height) {
        super.paintComponent(component, graphics, x, y, width, height);
        if (!(component instanceof Picture))
            throw new IllegalArgumentException("Picture image builders must only be used on pictures.");
        Image image = ((Picture) component).getImage();
        if (image == null)
            return;
        int dx = 0;
        int dy = 0;
        Insets insets = component.getPadding();
        if (insets != null) {
            dx += insets.left;
            dy += insets.top;
        }
        graphics.drawImage(image, dx, dy, null);
    }
}

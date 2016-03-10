package com.iancaffey.jui;

import java.awt.*;

/**
 * ImageBuilder
 *
 * @author Ian Caffey
 * @since 1.0
 */
public interface ImageBuilder {
    public void paintBackground(Component component, Graphics graphics, int x, int y, int width, int height);

    public void paintComponent(Component component, Graphics graphics, int x, int y, int width, int height);

    public void paintOverlay(Component component, Graphics graphics, int x, int y, int width, int height);
}

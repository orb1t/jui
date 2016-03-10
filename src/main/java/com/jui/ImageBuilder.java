package com.jui;

import java.awt.*;

/**
 * ImageBuilder
 *
 * @author Ian Caffey
 * @since 1.0
 */
public interface ImageBuilder {
    public void paintBackground(com.jui.Component component, Graphics graphics, int x, int y, int width, int height);

    public void paintComponent(com.jui.Component component, Graphics graphics, int x, int y, int width, int height);

    public void paintOverlay(com.jui.Component component, Graphics graphics, int x, int y, int width, int height);
}

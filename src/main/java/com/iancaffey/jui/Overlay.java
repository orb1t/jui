package com.iancaffey.jui;

import java.awt.*;

/**
 * Overlay
 *
 * @author Ian Caffey
 * @since 1.0
 */
public interface Overlay {
    public void render(Component component, Graphics graphics, int x, int y, int width, int height);
}

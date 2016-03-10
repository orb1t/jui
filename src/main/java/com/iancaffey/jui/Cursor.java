package com.iancaffey.jui;

import java.awt.*;

/**
 * Cursor
 *
 * @author Ian Caffey
 * @since 1.0
 */
public interface Cursor {
    public void render(Component component, Graphics graphics, int x, int y);
}

package com.jui;

import java.awt.*;

/**
 * Cursor
 *
 * @author Ian Caffey
 * @since 1.0
 */
public interface Cursor {
    public void render(com.jui.Component component, Graphics graphics, int x, int y);
}

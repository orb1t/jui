package com.iancaffey.jui;

import java.awt.*;

/**
 * LayoutManager
 *
 * @author Ian Caffey
 * @since 1.0
 */
public interface LayoutManager {
    public boolean layout(Component component);

    public Dimension size(Component component);
}

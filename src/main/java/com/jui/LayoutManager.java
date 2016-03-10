package com.jui;

import java.awt.*;

/**
 * LayoutManager
 *
 * @author Ian Caffey
 * @since 1.0
 */
public interface LayoutManager {
    public boolean layout(com.jui.Component component);

    public Dimension size(com.jui.Component component);
}

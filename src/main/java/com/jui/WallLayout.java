package com.jui;

import com.jui.util.Model;

import java.awt.*;

/**
 * WallLayout
 *
 * @author Ian
 * @version 1.0
 */
public class WallLayout implements com.jui.LayoutManager {
    private final Model model = new Model();

    public WallLayout() {
        this(0, 0);
    }

    public WallLayout(int horizontalGap, int verticalGap) {
        setHorizontalGap(horizontalGap);
        setVerticalGap(verticalGap);
    }

    public int getVerticalGap() {
        return model.getInt(LookAndFeel.WALL_LAYOUT_VERTICAL_GAP);
    }

    public void setVerticalGap(int componentGap) {
        model.put(LookAndFeel.WALL_LAYOUT_VERTICAL_GAP, componentGap);
    }

    public int getHorizontalGap() {
        return model.getInt(LookAndFeel.WALL_LAYOUT_HORIZONTAL_GAP);
    }

    public void setHorizontalGap(int componentGap) {
        model.put(LookAndFeel.WALL_LAYOUT_HORIZONTAL_GAP, componentGap);
    }

    @Override
    public boolean layout(com.jui.Component component) {
        if (!(component instanceof com.jui.Container))
            throw new IllegalArgumentException("Wall layout may only be applied to containers.");
        com.jui.Container container = (com.jui.Container) component;
        int vgap = getVerticalGap();
        int width = container.getWidth();
        com.jui.Insets insets = container.getPadding();
        int x = 0;
        int y = 0;
        if (insets != null) {
            x += insets.left;
            y += insets.top;
            width -= insets.left + insets.right;
        }
        com.jui.Component[] components = container.getComponentArray();
        boolean changed = false;
        int rows = (int) Math.ceil(components.length / 2.0D);
        for (int i = 0; i < rows; i++) {
            int leftIndex = i * 2;
            int currentHeight = 0;
            com.jui.Component l = components[leftIndex];
            if (l.isVisible()) {
                currentHeight = l.getHeight();
                int oldX = l.getX();
                int oldY = l.getY();
                if (x != oldX || y != oldY)
                    changed = true;
                l.setLocation(x, y);
            }
            int rightIndex = leftIndex + 1;
            if (rightIndex < components.length) {
                com.jui.Component r = components[rightIndex];
                if (r.isVisible()) {
                    currentHeight = Math.max(currentHeight, r.getHeight());
                    int oldX = r.getX();
                    int oldY = r.getY();
                    if (x != oldX || y != oldY)
                        changed = true;
                    r.setLocation(width - r.getWidth() + x, y);
                }
            }
            y += currentHeight + (currentHeight > 0 ? vgap : 0);
        }
        return changed;
    }

    @Override
    public Dimension size(com.jui.Component component) {
        if (!(component instanceof com.jui.Container))
            throw new IllegalArgumentException("Wall layout may only be applied to containers.");
        com.jui.Container container = (com.jui.Container) component;
        int hgap = getHorizontalGap();
        int vgap = getVerticalGap();
        int totalHeight = 0;
        int maxWidth = 0;
        com.jui.Component[] components = container.getComponentArray();
        int rows = (int) Math.ceil(components.length / 2.0D);
        for (int i = 0; i < rows; i++) {
            int currentWidth = 0;
            int currentHeight = 0;
            int leftIndex = i * 2;
            com.jui.Component l = components[leftIndex];
            if (l.isVisible()) {
                Dimension left = l.getPreferredSize();
                currentWidth = left.width;
                currentHeight = left.height;
            }
            int rightIndex = leftIndex + 1;
            if (rightIndex < components.length) {
                com.jui.Component r = components[rightIndex];
                if (r.isVisible()) {
                    Dimension right = r.getPreferredSize();
                    currentHeight = Math.max(currentHeight, right.height);
                    currentWidth += right.width;
                }
            }
            totalHeight += currentHeight;
            if (i < rows - 1 && currentHeight > 0)
                totalHeight += vgap;
            maxWidth = Math.max(maxWidth, currentWidth + (currentWidth > 0 && rightIndex < components.length ? hgap : 0));
        }
        com.jui.Insets insets = container.getPadding();
        if (insets != null) {
            maxWidth += insets.left + insets.right;
            totalHeight += insets.top + insets.bottom;
        }
        return new Dimension(maxWidth, totalHeight);
    }
}

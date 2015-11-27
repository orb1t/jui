package iitc.jui;

import java.awt.*;

/**
 * OpenLayout
 *
 * @author Ian
 * @version 1.0
 */
public class OpenLayout implements LayoutManager {
    @Override
    public boolean layout(Component component) {
        if (!(component instanceof Container))
            throw new IllegalArgumentException("Open layout may only be applied to containers.");
        return false;
    }

    @Override
    public Dimension size(Component component) {
        if (!(component instanceof Container))
            throw new IllegalArgumentException("Open layout may only be applied to containers.");
        Container container = (Container) component;
        int width = 0;
        int height = 0;
        for (Component c : container.getComponents()) {
            if (!c.isVisible())
                continue;
            Dimension preferred = c.getPreferredSize();
            if (preferred == null)
                continue;
            width = Math.max(width, c.getX() + preferred.width);
            height = Math.max(height, c.getY() + preferred.height);
        }
        Insets insets = component.getPadding();
        if (insets != null) {
            width += insets.left + insets.right;
            height += insets.top + insets.bottom;
        }
        return new Dimension(width, height);
    }
}

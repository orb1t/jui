package iitc.jui;

import iitc.util.Model;

import java.awt.*;
import java.util.Set;

/**
 * UniformLayout
 *
 * @author Ian
 * @version 1.0
 */
public class UniformLayout implements LayoutManager {
    private final Model model = new Model();

    public UniformLayout() {
        this(Orientation.HORIZONTAL);
    }

    public UniformLayout(Orientation orientation) {
        this(orientation, 0, 0);
    }

    public UniformLayout(Orientation orientation, int horizontalGap, int verticalGap) {
        setOrientation(orientation);
        setHorizontalGap(horizontalGap);
        setVerticalGap(verticalGap);
    }

    public Orientation getOrientation() {
        return model.get(LookAndFeel.UNIFORM_LAYOUT_ORIENTATION, Orientation.HORIZONTAL);
    }

    public void setOrientation(Orientation orientation) {
        model.put(LookAndFeel.UNIFORM_LAYOUT_ORIENTATION, orientation);
    }

    public int getVerticalGap() {
        return model.getInt(LookAndFeel.UNIFORM_LAYOUT_VERTICAL_GAP);
    }

    public void setVerticalGap(int componentGap) {
        model.put(LookAndFeel.UNIFORM_LAYOUT_VERTICAL_GAP, componentGap);
    }

    public int getHorizontalGap() {
        return model.getInt(LookAndFeel.UNIFORM_LAYOUT_HORIZONTAL_GAP);
    }

    public void setHorizontalGap(int componentGap) {
        model.put(LookAndFeel.WALL_LAYOUT_HORIZONTAL_GAP, componentGap);
    }

    @Override
    public boolean layout(Component component) {
        if (!(component instanceof Container))
            throw new IllegalArgumentException("Uniform layout must only be used on containers.");
        Container container = (Container) component;
        Set<Component> components = container.getComponents();
        Orientation orientation = getOrientation();
        int x = 0;
        int y = 0;
        int maxWidth = container.getWidth();
        int maxHeight = container.getHeight();
        Insets insets = container.getPadding();
        if (insets != null) {
            x += insets.left;
            y += insets.top;
            maxWidth -= insets.left + insets.right;
            maxHeight -= insets.top + insets.bottom;
        }
        int hgap = getHorizontalGap();
        int vgap = getVerticalGap();
        if (orientation == Orientation.HORIZONTAL) {
            for (Component c : components) {
                Dimension dimension = c.getPreferredSize();
                c.setBounds(x, y, dimension == null ? 0 : dimension.width, maxHeight);
                x += (dimension == null ? 0 : dimension.width) + hgap;
            }
        } else {
            for (Component c : components) {
                Dimension dimension = c.getPreferredSize();
                c.setBounds(x, y, maxWidth, dimension == null ? 0 : dimension.height);
                y += (dimension == null ? 0 : dimension.height) + vgap;
            }
        }
        return false;
    }

    @Override
    public Dimension size(Component component) {
        if (!(component instanceof Container))
            throw new IllegalArgumentException("Uniform layout must only be used on containers.");
        Container container = (Container) component;
        Orientation orientation = getOrientation();
        int maxWidth = 0;
        int maxHeight = 0;
        int hgap = getHorizontalGap();
        int vgap = getVerticalGap();
        Set<Component> components = container.getComponents();
        if (orientation == Orientation.HORIZONTAL) {
            int i = 0;
            int length = components.size();
            for (Component c : components) {
                if (i != length - 1)
                    maxWidth += hgap;
                Dimension d = c.getPreferredSize();
                if (d == null)
                    continue;
                maxWidth += d.getWidth();
                maxHeight = (int) Math.max(maxHeight, d.getHeight());
                i++;
            }
        } else {
            int i = 0;
            int length = components.size();
            for (Component c : components) {
                if (i != length - 1)
                    maxHeight += vgap;
                Dimension d = c.getPreferredSize();
                if (d == null)
                    continue;
                maxHeight += d.getHeight();
                maxWidth = (int) Math.max(maxWidth, d.getWidth());
                i++;
            }
        }
        Insets insets = container.getPadding();
        if (insets != null) {
            maxWidth += insets.left + insets.right;
            maxHeight += insets.top + insets.bottom;
        }
        return new Dimension(maxWidth, maxHeight);
    }
}

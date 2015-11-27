package iitc.jui;

import java.awt.*;

/**
 * LayoutManager
 *
 * @author Ian
 * @version 1.0
 */
public interface LayoutManager {
    public boolean layout(Component component);

    public Dimension size(Component component);
}

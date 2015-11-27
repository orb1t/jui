package iitc.jui;

import java.awt.*;

/**
 * Overlay
 *
 * @author Ian
 * @version 1.0
 */
public interface Overlay {
    public void render(Component component, Graphics graphics, int x, int y, int width, int height);
}

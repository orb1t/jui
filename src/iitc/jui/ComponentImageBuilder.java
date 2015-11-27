package iitc.jui;

import java.awt.*;
import java.util.Set;

/**
 * ComponentImageBuilder
 *
 * @author Ian
 * @version 1.0
 */
public class ComponentImageBuilder implements ImageBuilder {
    @Override
    public void paintBackground(Component component, Graphics graphics, int x, int y, int width, int height) {
        Background background = component.getBackground();
        if (background == null)
            return;
        Rectangle bounds = component.getBounds();
        background.render(component, graphics, 0, 0, bounds.width, bounds.height);
    }

    @Override
    public void paintComponent(Component component, Graphics graphics, int x, int y, int width, int height) {

    }

    @Override
    public void paintOverlay(Component component, Graphics graphics, int x, int y, int width, int height) {
        Rectangle bounds = component.getBounds();
        Set<Overlay> overlays = component.getOverlays();
        for (Overlay overlay : overlays)
            overlay.render(component, graphics, bounds.x, y, bounds.width, bounds.height);
        Border border = component.getBorder();
        if (border == null)
            return;
        border.render(component, graphics, 0, 0, bounds.width, bounds.height);
    }
}

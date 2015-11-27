package iitc.jui;

import java.awt.*;
import java.util.Set;

/**
 * HeaderImageBuilder
 *
 * @author Ian
 * @version 1.0
 */
public class HeaderImageBuilder extends ComponentImageBuilder {
    @Override
    public void paintComponent(Component component, Graphics graphics, int x, int y, int width, int height) {
        super.paintComponent(component, graphics, x, y, width, height);
        if (!(component instanceof Header))
            throw new IllegalArgumentException("Header image builders must only be used on headers.");
        Rectangle rectangle = new Rectangle(x, y, width, height);
        Header header = (Header) component;
        Set<Button> leftButtons = header.getLeftButtons();
        if (leftButtons != null) {
            for (Button b : leftButtons) {
                if (!b.isVisible())
                    continue;
                Rectangle bounds = b.getBounds();
                if (rectangle.intersects(bounds) || rectangle.contains(bounds))
                    b.paint(graphics);
            }
        }
        Set<Button> rightButtons = header.getRightButtons();
        if (rightButtons != null) {
            for (Button b : rightButtons) {
                if (!b.isVisible())
                    continue;
                Rectangle bounds = b.getBounds();
                if (rectangle.intersects(bounds) || rectangle.contains(bounds))
                    b.paint(graphics);
            }
        }
        Text text = header.getText();
        if (text != null && text.isVisible()) {
            Rectangle bounds = text.getBounds();
            if (rectangle.intersects(bounds) || rectangle.contains(bounds)) {
                text.paint(graphics);
            }
        }
    }
}

package iitc.jui;

import java.awt.*;

/**
 * ButtonImageBuilder
 *
 * @author Ian
 * @version 1.0
 */
public class ButtonImageBuilder extends ComponentImageBuilder {
    @Override
    public void paintComponent(Component component, Graphics graphics, int x, int y, int width, int height) {
        super.paintComponent(component, graphics, x, y, width, height);
        if (!(component instanceof Button))
            throw new IllegalArgumentException("Button image builders must only be used on buttons.");
        Rectangle rectangle = new Rectangle(x, y, width, height);
        Button button = (Button) component;
        Picture picture = button.isDisabled() ? button.getDisabledPicture() : button.isPressed() ? button.getPressedPicture() : button.isHovered() ? button.getRolloverPicture() : null;
        if (picture == null)
            picture = button.getPicture();
        if (picture != null) {
            Rectangle bounds = picture.getBounds();
            if (rectangle.intersects(bounds) || rectangle.contains(bounds))
                picture.paint(graphics);
        }
        Text text = button.getText();
        if (text != null) {
            Rectangle bounds = text.getBounds();
            if (rectangle.intersects(bounds) || rectangle.contains(bounds))
                text.paint(graphics);
        }
    }
}

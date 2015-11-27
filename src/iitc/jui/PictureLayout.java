package iitc.jui;

import java.awt.*;

/**
 * PictureLayout
 *
 * @author Ian
 * @version 1.0
 */
public class PictureLayout implements LayoutManager {
    @Override
    public boolean layout(Component component) {
        return false;
    }

    @Override
    public Dimension size(Component component) {
        if (!(component instanceof Picture))
            throw new IllegalArgumentException("Picture layout must be used with a picture.");
        int width = 0;
        int height = 0;
        Insets insets = component.getPadding();
        if (insets != null) {
            width += insets.left + insets.right;
            height += insets.top + insets.bottom;
        }
        Image image = ((Picture) component).getImage();
        if (image != null) {
            width += image.getWidth(null);
            height += image.getHeight(null);
        }
        return new Dimension(width, height);
    }
}

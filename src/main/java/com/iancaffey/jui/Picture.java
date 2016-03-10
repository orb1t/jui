package com.iancaffey.jui;

import java.awt.*;

/**
 * Picture
 *
 * @author Ian Caffey
 * @since 1.0
 */
public class Picture extends Component {
    public Picture() {

    }

    public Picture(Image image) {
        setImage(image);
    }

    public Image getImage() {
        return getController().get(LookAndFeel.PICTURE_IMAGE);
    }

    public void setImage(Image image) {
        Image old = getImage();
        if (old == image)
            return;
        getController().put(LookAndFeel.PICTURE_IMAGE, image);
        if (image == null)
            return;
        repaint();
    }
}

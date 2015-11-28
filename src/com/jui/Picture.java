package com.jui;

import java.awt.*;

/**
 * Picture
 *
 * @author Ian
 * @version 1.0
 */
public class Picture extends com.jui.Component {
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

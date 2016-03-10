package com.iancaffey.jui;

import com.iancaffey.jui.util.Model;

import java.awt.*;

/**
 * ImageCursor
 *
 * @author Ian Caffey
 * @since 1.0
 */
public class ImageCursor implements Cursor {
    protected final Model model = new Model();

    public ImageCursor() {

    }

    public ImageCursor(Image image) {
        this(image, 0, 0);
    }

    public ImageCursor(Image image, int offsetX, int offsetY) {
        setImage(image);
        setOffsetX(offsetX);
        setOffsetY(offsetY);
    }

    public Image getImage() {
        return model.get(LookAndFeel.IMAGE_CURSOR_IMAGE);
    }

    public void setImage(Image image) {
        model.put(LookAndFeel.IMAGE_CURSOR_IMAGE, image);
    }

    public int getOffsetX() {
        return model.get(LookAndFeel.IMAGE_CURSOR_OFFSET_X);
    }

    public void setOffsetX(int offset) {
        model.put(LookAndFeel.IMAGE_CURSOR_OFFSET_X, offset);
    }

    public int getOffsetY() {
        return model.get(LookAndFeel.IMAGE_CURSOR_OFFSET_Y);
    }

    public void setOffsetY(int offset) {
        model.put(LookAndFeel.IMAGE_CURSOR_OFFSET_Y, offset);
    }

    @Override
    public void render(Component component, Graphics graphics, int x, int y) {
        Image image = getImage();
        if (image == null)
            return;
        graphics.drawImage(image, x + getOffsetX(), y + getOffsetY(), null);
    }
}

package iitc.jui;

import iitc.util.Model;

import java.awt.*;

/**
 * DropShadow
 *
 * @author Ian
 * @version 1.0
 */
public class DropShadow {
    protected final Model model = new Model();

    public DropShadow(Color color) {
        this(color, 1);
    }

    public DropShadow(Color color, int offset) {
        this(color, offset, offset);
    }

    public DropShadow(Color color, int offsetX, int offsetY) {
        setColor(color);
        setOffsetX(offsetX);
        setOffsetY(offsetY);
    }

    public int getOffsetX() {
        return model.get(LookAndFeel.DROP_SHADOW_OFFSET_X);
    }

    public void setOffsetX(int offset) {
        model.put(LookAndFeel.DROP_SHADOW_OFFSET_X, offset);
    }

    public int getOffsetY() {
        return model.get(LookAndFeel.DROP_SHADOW_OFFSET_Y);
    }

    public void setOffsetY(int offset) {
        model.put(LookAndFeel.DROP_SHADOW_OFFSET_Y, offset);
    }

    public Color getColor() {
        return model.get(LookAndFeel.DROP_SHADOW_COLOR);
    }

    public void setColor(Color color) {
        model.put(LookAndFeel.DROP_SHADOW_COLOR, color);
    }
}

package iitc.jui;

import java.awt.*;
import java.util.Set;

/**
 * ButtonLayout
 *
 * @author Ian
 * @version 1.0
 */
public class ButtonLayout implements LayoutManager {
    @Override
    public boolean layout(Component component) {
        if (!(component instanceof Button))
            throw new IllegalArgumentException("Button layout must only be used with buttons.");
        Button button = (Button) component;
        int x = 0;
        int y = 0;
        int width = button.getWidth();
        int height = button.getHeight();
        Insets insets = button.getPadding();
        if (insets != null) {
            x += insets.left;
            y += insets.top;
            width -= insets.left + insets.right;
            height -= insets.top + insets.bottom;
        }
        boolean changed = false;
        Text text = button.getText();
        Picture picture = button.isDisabled() ? button.getDisabledPicture() : button.isPressed() ? button.getPressedPicture() : button.isHovered() ? button.getRolloverPicture() : null;
        if (picture == null)
            picture = button.getPicture();
        Set<Position> picturePosition = picture == null ? null : button.getPicturePosition();
        Set<Position> textPosition = text == null ? null : button.getTextPosition();
        if (picture != null) {
            int dw = picture.getWidth();
            int dh = picture.getHeight();
            int dx;
            int dy;
            if (picturePosition.contains(Position.LEFT)) {
                dx = 0;
            } else if (picturePosition.contains(Position.RIGHT)) {
                dx = width - dw;
            } else {
                dx = (int) Math.round((width - dw) / 2.0d);
            }
            if (picturePosition.contains(Position.TOP)) {
                dy = 0;
            } else if (picturePosition.contains(Position.BOTTOM)) {
                dy = height - dh;
            } else {
                dy = (int) Math.round((height - dh) / 2.0d);
            }
            dx += x;
            dy += y;
            int oldX = picture.getX();
            int oldY = picture.getY();
            if (oldX != dx || oldY != dy)
                changed = true;
            picture.setLocation(dx, dy);
        }
        if (text != null) {
            int dw = text.getWidth();
            int dh = text.getHeight();
            int dx;
            int dy;
            if (textPosition.contains(Position.LEFT)) {
                dx = 0;
            } else if (textPosition.contains(Position.RIGHT)) {
                dx = width - dw;
            } else {
                dx = (int) Math.round((width - dw) / 2.0d);
            }
            if (textPosition.contains(Position.TOP)) {
                dy = 0;
            } else if (textPosition.contains(Position.BOTTOM)) {
                dy = height - dh;
            } else {
                dy = (int) Math.round((height - dh) / 2.0d);
            }
            dx += x;
            dy += y;
            int oldX = text.getX();
            int oldY = text.getY();
            if (oldX != dx || oldY != dy)
                changed = true;
            text.setLocation(dx, dy);
        }
        return changed;
    }

    @Override
    public Dimension size(Component component) {
        if (!(component instanceof Button))
            throw new IllegalArgumentException("Button layout must only be used with buttons.");
        int width = 0;
        int height = 0;
        Button button = (Button) component;
        Picture picture = button.isDisabled() ? button.getDisabledPicture() : button.isPressed() ? button.getPressedPicture() : button.isHovered() ? button.getRolloverPicture() : null;
        if (picture == null)
            picture = button.getPicture();
        Text text = button.getText();
        if (picture != null && text != null) {
            Set<Position> picturePosition = button.getPicturePosition();
            Set<Position> textPosition = button.getTextPosition();
            Dimension preferredPicture = picture.getPreferredSize();
            Dimension preferredText = text.getPreferredSize();
            if (preferredPicture == null || preferredText == null) {
                if (preferredPicture != null || preferredText != null) {
                    if (preferredPicture == null) {
                        width += preferredText.getWidth();
                        height += preferredText.getHeight();
                    } else {
                        width += preferredPicture.getWidth();
                        height += preferredPicture.getHeight();
                    }
                }
            } else {
                if ((!picturePosition.contains(Position.TOP) && !picturePosition.contains(Position.BOTTOM) && picturePosition.contains(Position.CENTER)) || (!textPosition.contains(Position.TOP) && !textPosition.contains(Position.BOTTOM) && textPosition.contains(Position.CENTER))) {
                    height += Math.max(preferredPicture.getHeight(), preferredText.getHeight());
                } else if (picturePosition.contains(Position.TOP) && textPosition.contains(Position.TOP) || picturePosition.contains(Position.BOTTOM) && textPosition.contains(Position.BOTTOM) || (!picturePosition.contains(Position.TOP) && !picturePosition.contains(Position.BOTTOM) && picturePosition.contains(Position.CENTER)) && (!textPosition.contains(Position.TOP) && !textPosition.contains(Position.BOTTOM) && textPosition.contains(Position.CENTER))) {
                    height += Math.max(preferredPicture.getHeight(), preferredText.getHeight());
                } else if (picturePosition.size() > 0 && textPosition.size() > 0) {
                    height += button.getTextGap() + preferredPicture.getHeight() + preferredText.getHeight();
                } else {
                    height += Math.max(preferredPicture.getHeight(), preferredText.getHeight());
                }
                if ((!picturePosition.contains(Position.LEFT) && !picturePosition.contains(Position.RIGHT) && picturePosition.contains(Position.CENTER)) || (!textPosition.contains(Position.LEFT) && !textPosition.contains(Position.RIGHT) && textPosition.contains(Position.CENTER))) {
                    width += Math.max(preferredPicture.getWidth(), preferredText.getWidth());
                } else if ((picturePosition.contains(Position.LEFT) && textPosition.contains(Position.LEFT) || picturePosition.contains(Position.RIGHT) && textPosition.contains(Position.RIGHT))) {
                    width += button.getTextGap() + Math.max(preferredPicture.getWidth(), preferredText.getWidth());
                } else if (picturePosition.size() > 0 && textPosition.size() > 0) {
                    width += button.getTextGap() + preferredPicture.getWidth() + preferredText.getWidth();
                } else {
                    width += Math.max(preferredPicture.getWidth(), preferredText.getWidth());
                }
            }
        } else if (picture != null) {
            Dimension size = picture.getPreferredSize();
            if (size != null) {
                width += size.getWidth();
                height += size.getHeight();
            }
        } else if (text != null) {
            Dimension size = text.getPreferredSize();
            if (size != null) {
                width += size.getWidth();
                height += size.getHeight();
            }
        }
        Insets insets = button.getPadding();
        if (insets != null) {
            width += insets.left + insets.right;
            height += insets.top + insets.bottom;
        }
        return new Dimension(width, height);
    }
}

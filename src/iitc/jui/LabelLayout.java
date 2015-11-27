package iitc.jui;

import java.awt.*;
import java.util.Set;

/**
 * LabelLayout
 *
 * @author Ian
 * @version 1.0
 */
public class LabelLayout implements LayoutManager {
    @Override
    public boolean layout(Component component) {
        if (!(component instanceof Label))
            throw new IllegalArgumentException("Label layout must only be used with labels.");
        Label label = (Label) component;
        int x = 0;
        int y = 0;
        int width = label.getWidth();
        int height = label.getHeight();
        Insets insets = label.getPadding();
        if (insets != null) {
            x += insets.left;
            y += insets.top;
            width -= insets.left + insets.right;
            height -= insets.top + insets.bottom;
        }
        boolean changed = false;
        Text text = label.getText();
        Picture picture = label.getPicture();
        Set<Position> picturePosition = picture == null ? null : label.getPicturePosition();
        Set<Position> textPosition = text == null ? null : label.getTextPosition();
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
        if (!(component instanceof Label))
            throw new IllegalArgumentException("Label layout must only be used with labels.");
        int width = 0;
        int height = 0;
        Label label = (Label) component;
        Picture picture = label.getPicture();
        Text text = label.getText();
        if (picture != null && text != null) {
            Set<Position> picturePosition = label.getPicturePosition();
            Set<Position> textPosition = label.getTextPosition();
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
                    height += label.getTextGap() + preferredPicture.getHeight() + preferredText.getHeight();
                } else {
                    height += Math.max(preferredPicture.getHeight(), preferredText.getHeight());
                }
                if ((!picturePosition.contains(Position.LEFT) && !picturePosition.contains(Position.RIGHT) && picturePosition.contains(Position.CENTER)) || (!textPosition.contains(Position.LEFT) && !textPosition.contains(Position.RIGHT) && textPosition.contains(Position.CENTER))) {
                    width += Math.max(preferredPicture.getWidth(), preferredText.getWidth());
                } else if ((picturePosition.contains(Position.LEFT) && textPosition.contains(Position.LEFT) || picturePosition.contains(Position.RIGHT) && textPosition.contains(Position.RIGHT))) {
                    width += label.getTextGap() + Math.max(preferredPicture.getWidth(), preferredText.getWidth());
                } else if (picturePosition.size() > 0 && textPosition.size() > 0) {
                    width += label.getTextGap() + preferredPicture.getWidth() + preferredText.getWidth();
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
        Insets insets = label.getPadding();
        if (insets != null) {
            width += insets.left + insets.right;
            height += insets.top + insets.bottom;
        }
        return new Dimension(width, height);
    }
}

package com.jui;

import sun.font.FontDesignMetrics;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.Set;

/**
 * TextImageBuilder
 *
 * @author Ian Caffey
 * @since 1.0
 */
public class TextImageBuilder extends ComponentImageBuilder {
    @Override
    public void paintComponent(com.jui.Component component, Graphics graphics, int x, int y, int width, int height) {
        super.paintComponent(component, graphics, x, y, width, height);
        if (!(component instanceof Text))
            throw new IllegalArgumentException("Text image builders must only be used on texts.");
        if (width == 0 || height == 0)
            return;
        Text text = (Text) component;
        Font font = text.getFont();
        if (font == null)
            return;
        String string = text.getText();
        if (string == null || string.length() == 0)
            return;
        Color color = text.getParent() instanceof com.jui.Button ? (text.isPressed() ? text.getPressedColor() : text.isHovered() ? text.getRolloverColor() : text.getColor()) : text.getColor();
        if (color == null)
            return;
        int tw = text.getWidth();
        int th = text.getHeight();
        int dx = 0;
        int dy = 0;
        int dw = 0;
        int dh = 0;
        int dhh = 0;
        com.jui.Insets insets = text.getPadding();
        if (insets != null) {
            dx += insets.left;
            dy += insets.top;
            tw -= insets.left + insets.right;
            th -= insets.top + insets.bottom;
        }
        FontDesignMetrics metrics = FontDesignMetrics.getMetrics(font);
        if (metrics != null) {
            Rectangle2D rect = metrics.getStringBounds(string, graphics);
            dw += rect.getWidth();
            dh += rect.getHeight();
            dhh += metrics.getAscent() - 1;
        }
        Set<Position> picturePosition = text.getPosition();
        if (picturePosition.contains(Position.LEFT)) {
            dx += 0;
        } else if (picturePosition.contains(Position.RIGHT)) {
            dx += tw - dw;
        } else if (picturePosition.contains(Position.CENTER)) {
            dx += (int) Math.round((tw - dw) / 2.0d);
        }
        if (picturePosition.contains(Position.TOP)) {
            dy += 0;
        } else if (picturePosition.contains(Position.BOTTOM)) {
            dy += th - dh;
        } else if (picturePosition.contains(Position.CENTER)) {
            dy += (int) Math.round((th - dh) / 2.0d);
        }
        if (!new Rectangle(x, y, width, height).contains(new Rectangle(dx, dy, dw, dh)))
            return;
        Color oldColor = graphics.getColor();
        Font oldFont = graphics.getFont();
        graphics.setFont(font);
        Object aliasHint = ((Graphics2D) graphics).getRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING);
        DropShadow shadow = text.getDropShadow();
        if (shadow != null) {
            Color shadowColor = shadow.getColor();
            if (shadowColor != null) {
                graphics.setColor(shadowColor);
                graphics.drawString(string, dx + shadow.getOffsetX(), dy + dhh + shadow.getOffsetY());
            }
        }
        ((Graphics2D) graphics).setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        graphics.setColor(color);
        graphics.drawString(string, dx, dy + dhh);
        ((Graphics2D) graphics).setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, aliasHint);
        graphics.setColor(oldColor);
        graphics.setFont(oldFont);
    }
}

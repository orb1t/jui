package com.jui;

import java.awt.*;

/**
 * ProgressBarImageBuilder
 *
 * @author Ian
 * @version 1.0
 */
public class ProgressBarImageBuilder extends ComponentImageBuilder {
    @Override
    public void paintComponent(com.jui.Component component, Graphics graphics, int x, int y, int width, int height) {
        super.paintComponent(component, graphics, x, y, width, height);
        if (!(component instanceof ProgressBar))
            throw new IllegalArgumentException("Progress bar image builders must only be used on progress bars.");
        ProgressBar bar = (ProgressBar) component;
        int dx = 0;
        int dy = 0;
        int dw = bar.getWidth();
        int dh = bar.getHeight();
        com.jui.Insets insets = bar.getPadding();
        if (insets != null) {
            dx += insets.left;
            dy += insets.top;
            dw -= insets.left + insets.right;
            dh -= insets.top + insets.bottom;
        }
        double progression = bar.getProgression();
        int progressPixels = (int) Math.round(progression * dw);
        int remainingPixels = Math.max(0, dw - progressPixels);
        Color old = graphics.getColor();
        if (progressPixels > 0) {
            Color progressColor = bar.getProgressionColor();
            if (progressColor != null) {
                graphics.setColor(progressColor);
                graphics.fillRect(dx, dy, progressPixels, dh);
            }
        }
        if (remainingPixels > 0) {
            Color remainingColor = bar.getRemainingColor();
            if (remainingColor != null) {
                graphics.setColor(remainingColor);
                graphics.fillRect(dx + progressPixels, dy, remainingPixels, dh);
            }
        }
        graphics.setColor(old);
        Text text = bar.getText();
        if (text != null)
            text.paint(graphics);
    }
}

package com.jui.lnf;

import com.jui.*;
import com.jui.Button;
import com.jui.Component;
import com.jui.Frame;
import com.jui.Insets;

import java.awt.*;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;


/**
 * SmokyGlass
 *
 * @author Ian Caffey
 * @since 1.0
 */
public class SmokyGlass extends LookAndFeel {
    public SmokyGlass(Color color) {
        if (color == null)
            throw new IllegalArgumentException();
        if (color == Color.WHITE)
            color = color.darker();
        final Color textColor = Color.WHITE;
        final Color rolloverColor = Color.GRAY;
        final Color pressedColor = Color.DARK_GRAY;
        final Color translucentBackground = new Color(color.getRed(), color.getGreen(), color.getBlue(), 175);
        final Color overlayColor = new Color(1f, 1f, 1f, 0.2f);
        final Color borderColor = new Color(1f, 1f, 1f, 0.4f);
        final Color buttonColor = new Color(0f, 0f, 0f, 0.2f);
        final Color pressedButton = new Color(0.5f, 0.5f, 0.5f, 0.15f);
        final Color hoveredButton = new Color(0f, 0f, 0f, 0.1f);
        get(Component.class, ComponentModel.class).put(LookAndFeel.COMPONENT_BACKGROUND, new Background(translucentBackground));
        FrameModel frameModel = get(Frame.class, FrameModel.class);
        frameModel.put(LookAndFeel.COMPONENT_BACKGROUND, new Background(translucentBackground));
        Set<Overlay> foverlays = new HashSet<>();
        foverlays.add(new Overlay() {

            @Override
            public void render(Component component, Graphics graphics, int x, int y, int width, int height) {
                int dx = 0;
                int dy = 0;
                int dw = component.getWidth();
                int dh = component.getHeight();
                Insets insets = component.getPadding();
                if (insets != null) {
                    dx += insets.left;
                    dy += insets.top;
                    dw -= insets.left + insets.right;
                    dh -= insets.top + insets.bottom;
                }
                Color old = graphics.getColor();
                graphics.setColor(borderColor);
                graphics.drawRect(dx, dy, dw - 1, dh - 1);
                graphics.setColor(old);
            }
        });
        frameModel.put(LookAndFeel.COMPONENT_OVERLAYS, foverlays);
        TextModel textModel = get(Text.class, TextModel.class);
        textModel.put(LookAndFeel.TEXT_COLOR, textColor);
        textModel.put(LookAndFeel.TEXT_ROLLOVER_COLOR, rolloverColor);
        textModel.put(LookAndFeel.TEXT_PRESSED_COLOR, pressedColor);
        Set<Overlay> overlays = new HashSet<>();
        overlays.add(new Overlay() {
            LinearGradientPaint paint = null;

            @Override
            public void render(Component component, Graphics graphics, int x, int y, int width, int height) {
                int dx = 0;
                int dy = 0;
                int dw = component.getWidth();
                int dh = component.getHeight();
                Insets insets = component.getPadding();
                if (insets != null) {
                    dx += insets.left;
                    dy += insets.top;
                    dw -= insets.left + insets.right;
                    dh -= insets.top + insets.bottom;
                }
                if (paint == null || paint.getEndPoint().getY() != dh)
                    paint = new LinearGradientPaint(0, 0, 0, dh, new float[]{0.0f, 0.2f, 0.8f, 1.0f}, new Color[]{overlayColor, LookAndFeel.TRANSPARENT, LookAndFeel.TRANSPARENT, overlayColor});
                Color old = graphics.getColor();
                Paint oldPaint = ((Graphics2D) graphics).getPaint();
                ((Graphics2D) graphics).setPaint(paint);
                graphics.fillRect(dx, dy, dw, dh);
                ((Graphics2D) graphics).setPaint(oldPaint);
                graphics.setColor(overlayColor);
                graphics.fillRect(dx, dy, dw, (int) Math.round(dh / 2.2d));
                graphics.drawRect(dx, dy, dw - 1, dh - 1);
                graphics.setColor(old);
            }
        });
        get(ProgressBar.class, ProgressBarModel.class).put(LookAndFeel.COMPONENT_OVERLAYS, overlays);
        Set<Overlay> hoverlays = new HashSet<>();
        hoverlays.add(new Overlay() {
            LinearGradientPaint paint = null;

            @Override
            public void render(Component component, Graphics graphics, int x, int y, int width, int height) {
                int dx = 0;
                int dy = 0;
                int dw = component.getWidth();
                int dh = component.getHeight();
                if (paint == null || paint.getEndPoint().getY() != dh)
                    paint = new LinearGradientPaint(0, 0, 0, dh, new float[]{0.0f, 0.2f, 0.8f, 1.0f}, new Color[]{overlayColor, LookAndFeel.TRANSPARENT, LookAndFeel.TRANSPARENT, overlayColor});
                Color old = graphics.getColor();
                Paint oldPaint = ((Graphics2D) graphics).getPaint();
                ((Graphics2D) graphics).setPaint(paint);
                graphics.fillRect(dx, dy, dw, dh);
                ((Graphics2D) graphics).setPaint(oldPaint);
                graphics.setColor(overlayColor);
                graphics.fillRect(dx, dy, dw, (int) Math.round(dh / 2.2d));
                graphics.drawRect(dx, dy, dw - 1, dh - 1);
                graphics.setColor(old);
            }
        });
        get(Header.class, HeaderModel.class).put(LookAndFeel.COMPONENT_OVERLAYS, hoverlays);
        Set<Position> picturePosition = new HashSet<>();
        Collections.addAll(picturePosition, Position.LEFT, Position.CENTER);
        Set<Position> textPosition = new HashSet<>();
        Collections.addAll(textPosition, Position.RIGHT, Position.CENTER);
        ButtonModel bm = get(Button.class, ButtonModel.class);
        Set<Overlay> boverlays = new HashSet<>();
        boverlays.add(new Overlay() {
            Color gradient = buttonColor;
            LinearGradientPaint paint = null;

            @Override
            public void render(Component component, Graphics graphics, int x, int y, int width, int height) {
                if (!(component instanceof Button))
                    return;
                Button button = (Button) component;
                Color color = button.isDisabled() ? hoveredButton : button.isPressed() ? pressedButton : button.isHovered() ? hoveredButton : buttonColor;
                int dx = 0;
                int dy = 0;
                int dw = button.getWidth();
                int dh = button.getHeight();
                if (gradient != color || paint == null || paint.getEndPoint().getY() != dh) {
                    gradient = color;
                    paint = new LinearGradientPaint(0, 0, 0, dh, new float[]{0.0f, 0.2f, 0.8f, 1.0f}, new Color[]{color, LookAndFeel.TRANSPARENT, LookAndFeel.TRANSPARENT, color});
                }
                Color old = graphics.getColor();
                Paint oldPaint = ((Graphics2D) graphics).getPaint();
                ((Graphics2D) graphics).setPaint(paint);
                graphics.fillRect(dx, dy, dw, dh);
                ((Graphics2D) graphics).setPaint(oldPaint);
                graphics.setColor(color);
                graphics.fillRect(dx, dy, dw, (int) Math.round(dh / 2.2d));
                graphics.drawRect(dx, dy, dw - 1, dh - 1);
                graphics.setColor(old);
            }
        });
        bm.put(LookAndFeel.COMPONENT_OVERLAYS, boverlays);
        bm.put(LookAndFeel.BUTTON_PICTURE_POSITION, picturePosition);
        bm.put(LookAndFeel.BUTTON_TEXT_POSITION, textPosition);
        TextModel lm = get(Text.class, TextModel.class);
        lm.put(LookAndFeel.LABEL_PICTURE_POSITION, picturePosition);
        lm.put(LookAndFeel.LABEL_TEXT_POSITION, textPosition);
        ProgressBarModel model = get(ProgressBar.class, ProgressBarModel.class);
        model.put(LookAndFeel.PROGRESS_BAR_PROGRESSION_COLOR, Color.GREEN.darker());
    }
}

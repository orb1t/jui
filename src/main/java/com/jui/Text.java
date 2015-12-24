package com.jui;

import java.awt.*;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Text
 *
 * @author Ian
 * @version 1.0
 */
//TODO:Add hover, and press text colors
public class Text extends com.jui.Component {
    public Text() {

    }

    public Text(String text) {
        setText(text);
    }

    public Text(String text, Font font) {
        setText(text);
        setFont(font);
    }

    public Text(String text, Color color) {
        setText(text);
        setColor(color);
    }

    public Text(String text, Color color, Font font) {
        setText(text);
        setColor(color);
        setFont(font);
    }

    public String getText() {
        return getController().get(LookAndFeel.TEXT_TEXT);
    }

    public void setText(String text) {
        String old = getText();
        if (Objects.equals(old, text))
            return;
        getController().put(LookAndFeel.TEXT_TEXT, text);
        layout();
    }

    public Color getColor() {
        return getController().<Color>get(LookAndFeel.TEXT_COLOR, getBackupModel());
    }

    public void setColor(Color color) {
        Color oldColor = getColor();
        if (oldColor == color)
            return;
        getController().put(LookAndFeel.TEXT_COLOR, color);
        repaint();
    }

    public Color getRolloverColor() {
        return getController().<Color>get(LookAndFeel.TEXT_ROLLOVER_COLOR, getBackupModel());
    }

    public void setRolloverColor(Color color) {
        Color oldColor = getRolloverColor();
        if (oldColor == color)
            return;
        getController().put(LookAndFeel.TEXT_ROLLOVER_COLOR, color);
        repaint();
    }

    public Color getPressedColor() {
        return getController().<Color>get(LookAndFeel.TEXT_PRESSED_COLOR, getBackupModel());
    }

    public void setPressedColor(Color color) {
        Color oldColor = getPressedColor();
        if (oldColor == color)
            return;
        getController().put(LookAndFeel.TEXT_PRESSED_COLOR, color);
        repaint();
    }

    public boolean isHovered() {
        return getController().getBoolean(LookAndFeel.TEXT_HOVERED);
    }

    public void setHovered(boolean hovered) {
        if (isHovered() == hovered)
            return;
        getController().put(LookAndFeel.TEXT_HOVERED, hovered);
        repaint();
    }

    public boolean isPressed() {
        return getController().getBoolean(LookAndFeel.TEXT_PRESSED);
    }

    public void setPressed(boolean pressed) {
        if (isPressed() == pressed)
            return;
        getController().put(LookAndFeel.TEXT_PRESSED, pressed);
        repaint();
    }

    public Font getFont() {
        return getController().<Font>get(LookAndFeel.TEXT_FONT, getBackupModel());
    }

    public void setFont(Font font) {
        Font oldFont = getFont();
        if (oldFont == font)
            return;
        getController().put(LookAndFeel.TEXT_FONT, font);
        layout();
    }

    public DropShadow getDropShadow() {
        return getController().<DropShadow>get(LookAndFeel.TEXT_DROP_SHADOW, getBackupModel());
    }

    public void setDropShadow(DropShadow shadow) {
        DropShadow old = getDropShadow();
        if (old == shadow)
            return;
        getController().put(LookAndFeel.TEXT_DROP_SHADOW, shadow);
        repaint();
    }

    public Set<Position> getPosition() {
        Set<Position> position = getController().get(LookAndFeel.TEXT_POSITION);
        return position == null ? new HashSet<>() : new HashSet<>(position);
    }

    public void setPosition(Position... position) {
        Set<Position> positionSet = getController().get(LookAndFeel.TEXT_POSITION);
        if (positionSet == null) {
            positionSet = new HashSet<>();
            getController().put(LookAndFeel.TEXT_POSITION, positionSet);
        }
        positionSet.clear();
        if (position != null)
            Collections.addAll(positionSet, position);
        layout(true);
    }

    public void addPosition(Position position) {
        if (position == null)
            return;
        Set<Position> positionSet = getController().get(LookAndFeel.TEXT_POSITION);
        if (positionSet == null) {
            positionSet = new HashSet<>();
            getController().put(LookAndFeel.TEXT_POSITION, positionSet);
        }
        if (positionSet.contains(position))
            return;
        positionSet.add(position);
    }

    public void removePosition(Position position) {
        if (position == null)
            return;
        Set<Position> positionSet = getController().get(LookAndFeel.TEXT_POSITION);
        if (positionSet == null || !positionSet.contains(position))
            return;
        positionSet.remove(position);
    }
}

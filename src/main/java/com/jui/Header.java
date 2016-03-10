package com.jui;

import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Header
 *
 * @author Ian Caffey
 * @since 1.0
 */
public class Header extends Component implements Focusable {
    public Header() {
        this(new Text());
    }

    public Header(Text text) {
        setText(text);
    }

    public Header(Button[] leftButtons, Text text, Button[] rightButtons) {
        setLeftButtons(leftButtons);
        setText(text);
        setRightButtons(rightButtons);
    }

    public Text getText() {
        return getController().get(LookAndFeel.HEADER_TEXT);
    }

    public void setText(Text text) {
        Text old = getText();
        if (old == text)
            return;
        if (old != null)
            old.setParent(null);
        if (text != null)
            text.setParent(this);
        getController().put(LookAndFeel.HEADER_TEXT, text);
        layout(true);
    }

    public Button[] getLeftButtonArray() {
        Set<Button> buttons = getController().get(LookAndFeel.HEADER_LEFT_BUTTONS);
        return buttons == null ? new Button[0] : buttons.toArray(new Button[buttons.size()]);
    }

    public Set<Button> getLeftButtons() {
        Set<Button> buttons = getController().get(LookAndFeel.HEADER_LEFT_BUTTONS);
        return buttons == null ? new HashSet<>() : new LinkedHashSet<>(buttons);
    }

    public synchronized void setLeftButtons(Button... buttons) {
        Set<Button> b = getController().get(LookAndFeel.HEADER_LEFT_BUTTONS);
        if (b == null) {
            b = new LinkedHashSet<>();
            getController().put(LookAndFeel.HEADER_LEFT_BUTTONS, b);
        }
        for (Button b1 : b)
            b1.setParent(null);
        b.clear();
        if (buttons != null) {
            for (Button b1 : buttons)
                b1.setParent(this);
            Collections.addAll(b, buttons);
        }
        layout(true);
    }

    public synchronized void addLeftButton(Button button) {
        Set<Button> b = getController().get(LookAndFeel.HEADER_LEFT_BUTTONS);
        if (b == null) {
            b = new LinkedHashSet<>();
            getController().put(LookAndFeel.HEADER_LEFT_BUTTONS, b);
        }
        if (b.contains(button))
            return;
        button.setParent(this);
        b.add(button);
        layout(true);
    }

    public synchronized void removeLeftButton(Button button) {
        Set<Button> b = getController().get(LookAndFeel.HEADER_LEFT_BUTTONS);
        if (b == null || !b.contains(button))
            return;
        button.setParent(null);
        b.remove(button);
        layout(true);
    }

    public Button[] getRightButtonArray() {
        Set<Button> buttons = getController().get(LookAndFeel.HEADER_RIGHT_BUTTONS);
        return buttons == null ? new Button[0] : buttons.toArray(new Button[buttons.size()]);
    }

    public Set<Button> getRightButtons() {
        Set<Button> buttons = getController().get(LookAndFeel.HEADER_RIGHT_BUTTONS);
        return buttons == null ? new HashSet<>() : new LinkedHashSet<>(buttons);
    }

    public synchronized void setRightButtons(Button... buttons) {
        Set<Button> b = getController().get(LookAndFeel.HEADER_RIGHT_BUTTONS);
        if (b == null) {
            b = new LinkedHashSet<>();
            getController().put(LookAndFeel.HEADER_RIGHT_BUTTONS, b);
        }
        for (Button b1 : b)
            b1.setParent(null);
        b.clear();
        if (buttons != null) {
            for (Button b1 : buttons)
                b1.setParent(this);
            Collections.addAll(b, buttons);
        }
        layout(true);
    }

    public synchronized void addRightButton(Button button) {
        Set<Button> b = getController().get(LookAndFeel.HEADER_RIGHT_BUTTONS);
        if (b == null) {
            b = new LinkedHashSet<>();
            getController().put(LookAndFeel.HEADER_RIGHT_BUTTONS, b);
        }
        if (b.contains(button))
            return;
        button.setParent(this);
        b.add(button);
        layout(true);
    }

    public synchronized void removeRightButton(Button button) {
        Set<Button> b = getController().get(LookAndFeel.HEADER_RIGHT_BUTTONS);
        if (b == null || !b.contains(button))
            return;
        button.setParent(null);
        b.remove(button);
        layout(true);
    }

    @Override
    public Component getClickFocus() {
        return getController().get(LookAndFeel.HEADER_CLICK_FOCUS);
    }

    @Override
    public void setClickFocus(Component component) {
        getController().put(LookAndFeel.HEADER_CLICK_FOCUS, component);
    }

    @Override
    public Component getDragFocus() {
        return getController().get(LookAndFeel.HEADER_DRAG_FOCUS);
    }

    @Override
    public void setDragFocus(Component component) {
        getController().put(LookAndFeel.HEADER_DRAG_FOCUS, component);
    }

    @Override
    public Component getHoverFocus() {
        return getController().get(LookAndFeel.HEADER_HOVER_FOCUS);
    }

    @Override
    public void setHoverFocus(Component component) {
        getController().put(LookAndFeel.HEADER_HOVER_FOCUS, component);
    }

    @Override
    public boolean layout() {
        boolean changed = false;
        Text text = getText();
        if (text != null && text.layout())
            changed = true;
        Set<Button> leftButtons = getLeftButtons();
        if (leftButtons != null) {
            for (Button b : leftButtons) {
                if (b.layout()) {
                    changed = true;
                }
            }
        }
        Set<Button> rightButtons = getRightButtons();
        if (rightButtons != null) {
            for (Button b : rightButtons) {
                if (b.layout()) {
                    changed = true;
                }
            }
        }
        return super.layout() || changed;
    }

    @Override
    public void pack() {
        Text text = getText();
        if (text != null)
            text.pack();
        Set<Button> leftButtons = getLeftButtons();
        if (leftButtons != null)
            for (Button b : leftButtons)
                b.pack();
        Set<Button> rightButtons = getRightButtons();
        if (rightButtons != null)
            for (Button b : rightButtons)
                b.pack();
        super.pack();
    }
}

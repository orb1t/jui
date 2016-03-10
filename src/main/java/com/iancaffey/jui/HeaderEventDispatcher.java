package com.iancaffey.jui;

import com.iancaffey.jui.event.ActionEvent;
import com.iancaffey.jui.event.ComponentEvent;
import com.iancaffey.jui.event.FocusEvent;
import com.iancaffey.jui.event.MouseEvent;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * FrameEventDispatcher
 *
 * @author Ian Caffey
 * @since 1.0
 */
public class HeaderEventDispatcher implements EventDispatcher {
    @Override
    public void dispatch(Component component, ComponentEvent event) {
        if (!(component instanceof Header))
            throw new IllegalArgumentException("Header event dispatcher must only be used with headers.");
        Header header = (Header) component;
        if (event.isConsumed())
            return;
        List<Component> components = new ArrayList<>();
        Set<Button> leftButtons = header.getLeftButtons();
        if (leftButtons != null)
            components.addAll(leftButtons);
        Set<Button> rightButtons = header.getRightButtons();
        if (rightButtons != null)
            components.addAll(rightButtons);
        Text text = header.getText();
        if (text != null)
            components.add(text);
        if (event instanceof MouseEvent) {
            Point p = ((MouseEvent) event).getPoint();
            if (p != null) {
                if (event.getId() == MouseEvent.MOUSE_EXITED) {
                    Component hoverFocus = header.getHoverFocus();
                    if (hoverFocus != null) {
                        hoverFocus.dispatch(event);
                        header.setHoverFocus(null);
                    }
                }
                if (event.getId() == MouseEvent.MOUSE_MOVED || event.getId() == MouseEvent.MOUSE_DRAGGED) {
                    Component oldFocus = header.getHoverFocus();
                    if (oldFocus == null || !oldFocus.contains(p)) {
                        if (oldFocus != null) {
                            header.setHoverFocus(null);
                            oldFocus.dispatch(new MouseEvent(oldFocus, new Point(p), MouseEvent.MOUSE_EXITED, MouseEvent.BUTTON1, 0));
                            oldFocus.dispatch(new FocusEvent(oldFocus, FocusEvent.FOCUS_LOST, FocusEvent.HOVER, oldFocus));
                        }
                        for (Component c : components) {
                            if (c.contains(p)) {
                                header.setHoverFocus(c);
                                c.dispatch(new MouseEvent(c, new Point(p), MouseEvent.MOUSE_ENTERED, MouseEvent.BUTTON1, 0));
                                c.dispatch(new FocusEvent(c, FocusEvent.FOCUS_GAINED, FocusEvent.HOVER, oldFocus));
                                c.dispatch(event);
                                break;
                            }
                        }
                    } else {
                        oldFocus.dispatch(event);
                    }
                }
                if (event.getId() == MouseEvent.MOUSE_PRESSED || event.getId() == MouseEvent.MOUSE_CLICKED) {
                    Component oldFocus = header.getClickFocus();
                    if (oldFocus == null || !oldFocus.contains(p)) {
                        if (oldFocus != null) {
                            header.setClickFocus(null);
                            oldFocus.dispatch(new FocusEvent(oldFocus, FocusEvent.FOCUS_LOST, FocusEvent.CLICK, oldFocus));
                        }
                        for (Component c : components) {
                            if (c.contains(p)) {
                                header.setClickFocus(c);
                                c.dispatch(new FocusEvent(c, FocusEvent.FOCUS_GAINED, FocusEvent.CLICK, oldFocus));
                                c.dispatch(event);
                                break;
                            }
                        }
                    } else {
                        oldFocus.dispatch(event);
                    }
                }
                if (event.getId() == MouseEvent.MOUSE_RELEASED) {
                    Component focus = header.getClickFocus();
                    if (focus != null) {
                        focus.dispatch(event);
                        focus.dispatch(new ActionEvent(focus, ActionEvent.ACTION_PERFORMED));
                    }
                }
                if (event.getId() == MouseEvent.MOUSE_PRESSED) {
                    Component oldFocus = header.getDragFocus();
                    if (oldFocus == null || !oldFocus.contains(p)) {
                        if (oldFocus != null) {
                            header.setDragFocus(null);
                            oldFocus.dispatch(new FocusEvent(oldFocus, FocusEvent.FOCUS_LOST, FocusEvent.DRAG, oldFocus));
                        }
                        for (Component c : components) {
                            if (c.contains(p)) {
                                header.setDragFocus(c);
                                c.dispatch(new FocusEvent(c, FocusEvent.FOCUS_GAINED, FocusEvent.DRAG, oldFocus));
                                c.dispatch(event);
                                break;
                            }
                        }
                    }
                }
                if (event.getId() == MouseEvent.MOUSE_RELEASED) {
                    Component oldFocus = header.getDragFocus();
                    if (oldFocus != null) {
                        header.setDragFocus(null);
                        oldFocus.dispatch(new FocusEvent(oldFocus, FocusEvent.FOCUS_LOST, FocusEvent.DRAG, oldFocus));
                    }
                }
                if (event.getId() == MouseEvent.MOUSE_DRAGGED) {
                    Component oldFocus = header.getDragFocus();
                    if (oldFocus != null)
                        oldFocus.dispatch(event);
                }
            }
        }
        if (event.isConsumed() || event instanceof MouseEvent)
            return;
        Component keyFocus = header.getClickFocus();
        if (keyFocus == null)
            return;
        keyFocus.dispatch(event);
    }
}

package com.jui;

import com.jui.event.ActionEvent;
import com.jui.event.ComponentEvent;
import com.jui.event.FocusEvent;
import com.jui.event.MouseEvent;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * FrameEventDispatcher
 *
 * @author Ian
 * @version 1.0
 */
public class FrameEventDispatcher implements EventDispatcher {
    @Override
    public void dispatch(com.jui.Component component, ComponentEvent event) {
        if (!(component instanceof com.jui.Frame))
            throw new IllegalArgumentException("Frame event dispatcher must only be used with frames.");
        com.jui.Frame frame = (com.jui.Frame) component;
        if (event.isConsumed())
            return;
        List<com.jui.Component> components = new ArrayList<>();
        Header header = frame.getHeader();
        if (header != null)
            components.add(header);
        com.jui.Component content = frame.getContent();
        if (content != null)
            components.add(content);
        if (event instanceof MouseEvent) {
            Point p = ((MouseEvent) event).getPoint();
            if (p != null) {
                if (event.getId() == MouseEvent.MOUSE_EXITED) {
                    com.jui.Component hoverFocus = frame.getHoverFocus();
                    if (hoverFocus != null) {
                        hoverFocus.dispatch(event);
                        frame.setHoverFocus(null);
                    }
                }
                if (event.getId() == MouseEvent.MOUSE_MOVED || event.getId() == MouseEvent.MOUSE_DRAGGED) {
                    com.jui.Component oldFocus = frame.getHoverFocus();
                    if (oldFocus == null || !oldFocus.contains(p)) {
                        if (oldFocus != null) {
                            frame.setHoverFocus(null);
                            oldFocus.dispatch(new MouseEvent(oldFocus, new Point(p), MouseEvent.MOUSE_EXITED, MouseEvent.BUTTON1, 0));
                            oldFocus.dispatch(new FocusEvent(oldFocus, FocusEvent.FOCUS_LOST, FocusEvent.HOVER, oldFocus));
                        }
                        for (com.jui.Component c : components) {
                            if (c.contains(p)) {
                                frame.setHoverFocus(c);
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
                    com.jui.Component oldFocus = frame.getClickFocus();
                    if (oldFocus == null || !oldFocus.contains(p)) {
                        if (oldFocus != null) {
                            frame.setClickFocus(null);
                            oldFocus.dispatch(new FocusEvent(oldFocus, FocusEvent.FOCUS_LOST, FocusEvent.CLICK, oldFocus));
                        }
                        for (com.jui.Component c : components) {
                            if (c.contains(p)) {
                                frame.setClickFocus(c);
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
                    com.jui.Component focus = frame.getClickFocus();
                    if (focus != null) {
                        focus.dispatch(event);
                        focus.dispatch(new ActionEvent(focus, ActionEvent.ACTION_PERFORMED));
                    }
                }
                if (event.getId() == MouseEvent.MOUSE_PRESSED) {
                    com.jui.Component oldFocus = frame.getDragFocus();
                    if (oldFocus == null || !oldFocus.contains(p)) {
                        if (oldFocus != null) {
                            frame.setDragFocus(null);
                            oldFocus.dispatch(new FocusEvent(oldFocus, FocusEvent.FOCUS_LOST, FocusEvent.DRAG, oldFocus));
                        }
                        for (com.jui.Component c : components) {
                            if (c.contains(p)) {
                                frame.setDragFocus(c);
                                c.dispatch(new FocusEvent(c, FocusEvent.FOCUS_GAINED, FocusEvent.DRAG, oldFocus));
                                c.dispatch(event);
                                break;
                            }
                        }
                    }
                }
                if (event.getId() == MouseEvent.MOUSE_RELEASED) {
                    com.jui.Component oldFocus = frame.getDragFocus();
                    if (oldFocus != null) {
                        frame.setDragFocus(null);
                        oldFocus.dispatch(new FocusEvent(oldFocus, FocusEvent.FOCUS_LOST, FocusEvent.DRAG, oldFocus));
                    }
                }
                if (event.getId() == MouseEvent.MOUSE_DRAGGED) {
                    com.jui.Component oldFocus = frame.getDragFocus();
                    if (oldFocus != null)
                        oldFocus.dispatch(event);
                }
            }
        }
        if (event.isConsumed() || event instanceof MouseEvent)
            return;
        com.jui.Component keyFocus = frame.getClickFocus();
        if (keyFocus == null)
            return;
        keyFocus.dispatch(event);
    }
}

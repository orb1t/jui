package com.jui;

import com.jui.event.ActionEvent;
import com.jui.event.ComponentEvent;
import com.jui.event.FocusEvent;
import com.jui.event.MouseEvent;

import java.awt.*;
import java.util.Set;

/**
 * ContainerEventDispatcher
 *
 * @author Ian Caffey
 * @since 1.0
 */
public class ContainerEventDispatcher implements EventDispatcher {
    @Override
    public void dispatch(com.jui.Component component, ComponentEvent event) {
        if (!(component instanceof com.jui.Container))
            throw new IllegalArgumentException("Container event dispatcher must only be used with containers.");
        com.jui.Container container = (com.jui.Container) component;
        if (event.isConsumed())
            return;
        Set<com.jui.Component> components = container.getComponents();
        if (event instanceof MouseEvent) {
            Point p = ((MouseEvent) event).getPoint();
            if (p != null) {
                if (event.getId() == MouseEvent.MOUSE_EXITED) {
                    com.jui.Component hoverFocus = container.getHoverFocus();
                    if (hoverFocus != null) {
                        hoverFocus.dispatch(event);
                        event.consume();
                        container.setHoverFocus(null);
                    }
                }
                if (event.getId() == MouseEvent.MOUSE_MOVED || event.getId() == MouseEvent.MOUSE_DRAGGED) {
                    com.jui.Component oldFocus = container.getHoverFocus();
                    if (oldFocus == null || !oldFocus.contains(p)) {
                        if (oldFocus != null) {
                            container.setHoverFocus(null);
                            oldFocus.dispatch(new MouseEvent(oldFocus, new Point(p), MouseEvent.MOUSE_EXITED, MouseEvent.BUTTON1, 0));
                            oldFocus.dispatch(new FocusEvent(oldFocus, FocusEvent.FOCUS_LOST, FocusEvent.HOVER, oldFocus));
                        }
                        for (com.jui.Component c : components) {
                            if (c.contains(p)) {
                                container.setHoverFocus(c);
                                c.dispatch(new MouseEvent(c, new Point(p), MouseEvent.MOUSE_ENTERED, MouseEvent.BUTTON1, 0));
                                c.dispatch(new FocusEvent(c, FocusEvent.FOCUS_GAINED, FocusEvent.HOVER, oldFocus));
                                c.dispatch(event);
                                event.consume();
                                break;
                            }
                        }
                    } else {
                        oldFocus.dispatch(event);
                        event.consume();
                    }
                }
                if (event.getId() == MouseEvent.MOUSE_PRESSED || event.getId() == MouseEvent.MOUSE_CLICKED) {
                    com.jui.Component oldFocus = container.getClickFocus();
                    if (oldFocus == null || !oldFocus.contains(p)) {
                        if (oldFocus != null) {
                            container.setClickFocus(null);
                            oldFocus.dispatch(new FocusEvent(oldFocus, FocusEvent.FOCUS_LOST, FocusEvent.CLICK, oldFocus));
                        }
                        for (com.jui.Component c : components) {
                            if (c.contains(p)) {
                                container.setClickFocus(c);
                                c.dispatch(new FocusEvent(c, FocusEvent.FOCUS_GAINED, FocusEvent.CLICK, oldFocus));
                                c.dispatch(event);
                                event.consume();
                                break;
                            }
                        }
                    } else {
                        oldFocus.dispatch(event);
                        event.consume();
                    }
                }
                if (event.getId() == MouseEvent.MOUSE_RELEASED) {
                    com.jui.Component focus = container.getClickFocus();
                    if (focus != null) {
                        focus.dispatch(event);
                        focus.dispatch(new ActionEvent(focus, ActionEvent.ACTION_PERFORMED));
                    }
                }
                if (event.getId() == MouseEvent.MOUSE_PRESSED) {
                    com.jui.Component oldFocus = container.getDragFocus();
                    if (oldFocus == null || !oldFocus.contains(p)) {
                        if (oldFocus != null) {
                            container.setDragFocus(null);
                            oldFocus.dispatch(new FocusEvent(oldFocus, FocusEvent.FOCUS_LOST, FocusEvent.DRAG, oldFocus));
                        }
                        for (com.jui.Component c : components) {
                            if (c.contains(p)) {
                                container.setDragFocus(c);
                                c.dispatch(new FocusEvent(c, FocusEvent.FOCUS_GAINED, FocusEvent.DRAG, oldFocus));
                                c.dispatch(event);
                                event.consume();
                                break;
                            }
                        }
                    }
                }
                if (event.getId() == MouseEvent.MOUSE_RELEASED) {
                    com.jui.Component oldFocus = container.getDragFocus();
                    if (oldFocus != null) {
                        container.setDragFocus(null);
                        oldFocus.dispatch(new FocusEvent(oldFocus, FocusEvent.FOCUS_LOST, FocusEvent.DRAG, oldFocus));
                    }
                }
                if (event.getId() == MouseEvent.MOUSE_DRAGGED) {
                    com.jui.Component oldFocus = container.getDragFocus();
                    if (oldFocus != null)
                        oldFocus.dispatch(event);
                }
            }
        }
        if (event.isConsumed() || event instanceof MouseEvent)
            return;
        com.jui.Component keyFocus = container.getClickFocus();
        if (keyFocus == null)
            return;
        keyFocus.dispatch(event);
        event.consume();
    }
}

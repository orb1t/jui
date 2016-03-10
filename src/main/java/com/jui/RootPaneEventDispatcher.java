package com.jui;

import com.jui.event.ActionEvent;
import com.jui.event.ComponentEvent;
import com.jui.event.FocusEvent;
import com.jui.event.MouseEvent;

import java.awt.*;
import java.util.Set;

/**
 * RootPaneEventDispatcher
 *
 * @author Ian Caffey
 * @since 1.0
 */
public class RootPaneEventDispatcher implements EventDispatcher {
    @Override
    public void dispatch(com.jui.Component component, ComponentEvent event) {
        if (!(component instanceof RootPane))
            throw new IllegalArgumentException("RootPane event dispatcher must only be used with root panes.");
        RootPane pane = (RootPane) component;
        if (event.isConsumed())
            return;
        Set<com.jui.Frame> frames = pane.getFrames();
        if (event instanceof MouseEvent) {
            Point p = ((MouseEvent) event).getPoint();
            if (p != null) {
                if (event.getId() == MouseEvent.MOUSE_EXITED) {
                    com.jui.Component hoverFocus = pane.getHoverFocus();
                    if (hoverFocus != null) {
                        hoverFocus.dispatch(event);
                        pane.setHoverFocus(null);
                    }
                }
                if (event.getId() == MouseEvent.MOUSE_MOVED || event.getId() == MouseEvent.MOUSE_DRAGGED) {
                    com.jui.Component oldFocus = pane.getHoverFocus();
                    if (oldFocus == null || !oldFocus.contains(p)) {
                        if (oldFocus != null) {
                            pane.setHoverFocus(null);
                            oldFocus.dispatch(new MouseEvent(oldFocus, new Point(p), MouseEvent.MOUSE_EXITED, MouseEvent.BUTTON1, 0));
                            oldFocus.dispatch(new FocusEvent(oldFocus, FocusEvent.FOCUS_LOST, FocusEvent.HOVER, oldFocus));
                        }
                        for (com.jui.Frame f : frames) {
                            if (f.contains(p)) {
                                pane.setHoverFocus(f);
                                f.dispatch(new MouseEvent(f, new Point(p), MouseEvent.MOUSE_ENTERED, MouseEvent.BUTTON1, 0));
                                f.dispatch(new FocusEvent(f, FocusEvent.FOCUS_GAINED, FocusEvent.HOVER, oldFocus));
                                f.dispatch(event);
                                break;
                            }
                        }
                    } else {
                        oldFocus.dispatch(event);
                    }
                }
                if (event.getId() == MouseEvent.MOUSE_PRESSED || event.getId() == MouseEvent.MOUSE_CLICKED) {
                    com.jui.Component oldFocus = pane.getClickFocus();
                    if (oldFocus == null || !oldFocus.contains(p)) {
                        if (oldFocus != null) {
                            pane.setClickFocus(null);
                            oldFocus.dispatch(new FocusEvent(oldFocus, FocusEvent.FOCUS_LOST, FocusEvent.CLICK, oldFocus));
                        }
                        for (com.jui.Frame f : frames) {
                            if (f.contains(p)) {
                                pane.setClickFocus(f);
                                f.dispatch(new FocusEvent(f, FocusEvent.FOCUS_GAINED, FocusEvent.CLICK, oldFocus));
                                f.dispatch(event);

                                break;
                            }
                        }
                    } else {
                        oldFocus.dispatch(event);
                    }
                }
                if (event.getId() == MouseEvent.MOUSE_RELEASED) {
                    com.jui.Component focus = pane.getClickFocus();
                    if (focus != null) {
                        focus.dispatch(event);
                        focus.dispatch(new ActionEvent(focus, ActionEvent.ACTION_PERFORMED));
                    }
                }
                if (event.getId() == MouseEvent.MOUSE_PRESSED) {
                    com.jui.Component oldFocus = pane.getDragFocus();
                    if (oldFocus == null || !oldFocus.contains(p)) {
                        if (oldFocus != null) {
                            pane.setDragFocus(null);
                            oldFocus.dispatch(new FocusEvent(oldFocus, FocusEvent.FOCUS_LOST, FocusEvent.DRAG, oldFocus));
                        }
                        for (com.jui.Frame f : frames) {
                            if (f.contains(p)) {
                                pane.setDragFocus(f);
                                f.dispatch(new FocusEvent(f, FocusEvent.FOCUS_GAINED, FocusEvent.DRAG, oldFocus));
                                f.dispatch(event);
                                break;
                            }
                        }
                    }
                }
                if (event.getId() == MouseEvent.MOUSE_RELEASED) {
                    com.jui.Component oldFocus = pane.getDragFocus();
                    if (oldFocus != null) {
                        pane.setDragFocus(null);
                        oldFocus.dispatch(new FocusEvent(oldFocus, FocusEvent.FOCUS_LOST, FocusEvent.DRAG, oldFocus));
                    }
                }
                if (event.getId() == MouseEvent.MOUSE_DRAGGED) {
                    com.jui.Component oldFocus = pane.getDragFocus();
                    if (oldFocus != null)
                        oldFocus.dispatch(event);
                }
            }
        }
        if (event.isConsumed() || event instanceof MouseEvent)
            return;
        com.jui.Component keyFocus = pane.getClickFocus();
        if (keyFocus == null)
            return;
        keyFocus.dispatch(event);

    }
}

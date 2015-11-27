package iitc.jui;

import iitc.jui.event.ActionEvent;
import iitc.jui.event.ComponentEvent;
import iitc.jui.event.FocusEvent;
import iitc.jui.event.MouseEvent;

import java.awt.*;
import java.util.Set;

/**
 * RootPaneEventDispatcher
 *
 * @author Ian
 * @version 1.0
 */
public class RootPaneEventDispatcher implements EventDispatcher {
    @Override
    public void dispatch(Component component, ComponentEvent event) {
        if (!(component instanceof RootPane))
            throw new IllegalArgumentException("RootPane event dispatcher must only be used with root panes.");
        RootPane pane = (RootPane) component;
        if (event.isConsumed())
            return;
        Set<Frame> frames = pane.getFrames();
        if (event instanceof MouseEvent) {
            Point p = ((MouseEvent) event).getPoint();
            if (p != null) {
                if (event.getId() == MouseEvent.MOUSE_EXITED) {
                    Component hoverFocus = pane.getHoverFocus();
                    if (hoverFocus != null) {
                        hoverFocus.dispatch(event);
                        pane.setHoverFocus(null);
                    }
                }
                if (event.getId() == MouseEvent.MOUSE_MOVED || event.getId() == MouseEvent.MOUSE_DRAGGED) {
                    Component oldFocus = pane.getHoverFocus();
                    if (oldFocus == null || !oldFocus.contains(p)) {
                        if (oldFocus != null) {
                            pane.setHoverFocus(null);
                            oldFocus.dispatch(new MouseEvent(oldFocus, new Point(p), MouseEvent.MOUSE_EXITED, MouseEvent.BUTTON1, 0));
                            oldFocus.dispatch(new FocusEvent(oldFocus, FocusEvent.FOCUS_LOST, FocusEvent.HOVER, oldFocus));
                        }
                        for (Frame f : frames) {
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
                    Component oldFocus = pane.getClickFocus();
                    if (oldFocus == null || !oldFocus.contains(p)) {
                        if (oldFocus != null) {
                            pane.setClickFocus(null);
                            oldFocus.dispatch(new FocusEvent(oldFocus, FocusEvent.FOCUS_LOST, FocusEvent.CLICK, oldFocus));
                        }
                        for (Frame f : frames) {
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
                    Component focus = pane.getClickFocus();
                    if (focus != null) {
                        focus.dispatch(event);
                        focus.dispatch(new ActionEvent(focus, ActionEvent.ACTION_PERFORMED));
                    }
                }
                if (event.getId() == MouseEvent.MOUSE_PRESSED) {
                    Component oldFocus = pane.getDragFocus();
                    if (oldFocus == null || !oldFocus.contains(p)) {
                        if (oldFocus != null) {
                            pane.setDragFocus(null);
                            oldFocus.dispatch(new FocusEvent(oldFocus, FocusEvent.FOCUS_LOST, FocusEvent.DRAG, oldFocus));
                        }
                        for (Frame f : frames) {
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
                    Component oldFocus = pane.getDragFocus();
                    if (oldFocus != null) {
                        pane.setDragFocus(null);
                        oldFocus.dispatch(new FocusEvent(oldFocus, FocusEvent.FOCUS_LOST, FocusEvent.DRAG, oldFocus));
                    }
                }
                if (event.getId() == MouseEvent.MOUSE_DRAGGED) {
                    Component oldFocus = pane.getDragFocus();
                    if (oldFocus != null)
                        oldFocus.dispatch(event);
                }
            }
        }
        if (event.isConsumed() || event instanceof MouseEvent)
            return;
        Component keyFocus = pane.getClickFocus();
        if (keyFocus == null)
            return;
        keyFocus.dispatch(event);

    }
}

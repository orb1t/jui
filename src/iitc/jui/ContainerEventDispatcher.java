package iitc.jui;

import iitc.jui.event.ActionEvent;
import iitc.jui.event.ComponentEvent;
import iitc.jui.event.FocusEvent;
import iitc.jui.event.MouseEvent;

import java.awt.*;
import java.util.Set;

/**
 * ContainerEventDispatcher
 *
 * @author Ian
 * @version 1.0
 */
public class ContainerEventDispatcher implements EventDispatcher {
    @Override
    public void dispatch(Component component, ComponentEvent event) {
        if (!(component instanceof Container))
            throw new IllegalArgumentException("Container event dispatcher must only be used with containers.");
        Container container = (Container) component;
        if (event.isConsumed())
            return;
        Set<Component> components = container.getComponents();
        if (event instanceof MouseEvent) {
            Point p = ((MouseEvent) event).getPoint();
            if (p != null) {
                if (event.getId() == MouseEvent.MOUSE_EXITED) {
                    Component hoverFocus = container.getHoverFocus();
                    if (hoverFocus != null) {
                        hoverFocus.dispatch(event);
                        event.consume();
                        container.setHoverFocus(null);
                    }
                }
                if (event.getId() == MouseEvent.MOUSE_MOVED || event.getId() == MouseEvent.MOUSE_DRAGGED) {
                    Component oldFocus = container.getHoverFocus();
                    if (oldFocus == null || !oldFocus.contains(p)) {
                        if (oldFocus != null) {
                            container.setHoverFocus(null);
                            oldFocus.dispatch(new MouseEvent(oldFocus, new Point(p), MouseEvent.MOUSE_EXITED, MouseEvent.BUTTON1, 0));
                            oldFocus.dispatch(new FocusEvent(oldFocus, FocusEvent.FOCUS_LOST, FocusEvent.HOVER, oldFocus));
                        }
                        for (Component c : components) {
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
                    Component oldFocus = container.getClickFocus();
                    if (oldFocus == null || !oldFocus.contains(p)) {
                        if (oldFocus != null) {
                            container.setClickFocus(null);
                            oldFocus.dispatch(new FocusEvent(oldFocus, FocusEvent.FOCUS_LOST, FocusEvent.CLICK, oldFocus));
                        }
                        for (Component c : components) {
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
                    Component focus = container.getClickFocus();
                    if (focus != null) {
                        focus.dispatch(event);
                        focus.dispatch(new ActionEvent(focus, ActionEvent.ACTION_PERFORMED));
                    }
                }
                if (event.getId() == MouseEvent.MOUSE_PRESSED) {
                    Component oldFocus = container.getDragFocus();
                    if (oldFocus == null || !oldFocus.contains(p)) {
                        if (oldFocus != null) {
                            container.setDragFocus(null);
                            oldFocus.dispatch(new FocusEvent(oldFocus, FocusEvent.FOCUS_LOST, FocusEvent.DRAG, oldFocus));
                        }
                        for (Component c : components) {
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
                    Component oldFocus = container.getDragFocus();
                    if (oldFocus != null) {
                        container.setDragFocus(null);
                        oldFocus.dispatch(new FocusEvent(oldFocus, FocusEvent.FOCUS_LOST, FocusEvent.DRAG, oldFocus));
                    }
                }
                if (event.getId() == MouseEvent.MOUSE_DRAGGED) {
                    Component oldFocus = container.getDragFocus();
                    if (oldFocus != null)
                        oldFocus.dispatch(event);
                }
            }
        }
        if (event.isConsumed() || event instanceof MouseEvent)
            return;
        Component keyFocus = container.getClickFocus();
        if (keyFocus == null)
            return;
        keyFocus.dispatch(event);
        event.consume();
    }
}

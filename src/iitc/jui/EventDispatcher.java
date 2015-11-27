package iitc.jui;

import iitc.jui.event.ComponentEvent;

/**
 * EventDispatcher
 *
 * @author Ian
 * @version 1.0
 */
public interface EventDispatcher {
    public void dispatch(Component component, ComponentEvent event);
}

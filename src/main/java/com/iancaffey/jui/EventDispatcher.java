package com.iancaffey.jui;

import com.iancaffey.jui.event.ComponentEvent;

/**
 * EventDispatcher
 *
 * @author Ian Caffey
 * @since 1.0
 */
public interface EventDispatcher {
    public void dispatch(Component component, ComponentEvent event);
}

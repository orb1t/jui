package com.jui.event;

import com.jui.Component;

/**
 * ActionEvent
 *
 * @author Ian Caffey
 * @since 1.0
 */
public class ActionEvent extends ComponentEvent {
    public static final int ACTION_PERFORMED = 500;

    public ActionEvent(Component component, int id) {
        super(component, id);
    }
}

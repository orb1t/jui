package iitc.jui.event;

import iitc.jui.Component;

/**
 * ActionEvent
 *
 * @author Ian
 * @version 1.0
 */
public class ActionEvent extends ComponentEvent {
    public static final int ACTION_PERFORMED = 500;

    public ActionEvent(Component component, int id) {
        super(component, id);
    }
}

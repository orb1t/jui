package iitc.jui.event;

import iitc.jui.Component;

import java.awt.*;

/**
 * MouseWheelEvent
 *
 * @author Ian
 * @version 1.0
 */
public class MouseWheelEvent extends MouseEvent {
    private final int amount;

    public MouseWheelEvent(Component component, Point point, int id, int amount, int clickCount) {
        super(component, point, id, MOUSE_WHEEL, clickCount);
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }
}

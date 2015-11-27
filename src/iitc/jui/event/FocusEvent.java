package iitc.jui.event;

import iitc.jui.Component;

/**
 * FocusEvent
 *
 * @author Ian
 * @version 1.0
 */
public class FocusEvent extends ComponentEvent {
    public static final int FOCUS_GAINED = 100;
    public static final int FOCUS_LOST = 101;
    public static final int HOVER = 1;
    public static final int CLICK = 2;
    public static final int DRAG = 3;
    private final Component opposite;
    private final int type;

    public FocusEvent(Component component, int id, int type, Component opposite) {
        super(component, id);
        this.type = type;
        this.opposite = opposite;
    }

    public int getType() {
        return type;
    }

    public Component getOpposite() {
        return opposite;
    }
}

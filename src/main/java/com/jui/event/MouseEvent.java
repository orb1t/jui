package com.jui.event;

import com.jui.Component;

import java.awt.*;

/**
 * MouseEvent
 *
 * @author Ian Caffey
 * @since 1.0
 */
public class MouseEvent extends ComponentEvent {
    public static final int NOBUTTON = 0;
    public static final int BUTTON1 = 1;
    public static final int BUTTON2 = 2;
    public static final int BUTTON3 = 3;
    public static final int MOUSE_CLICKED = 200;
    public static final int MOUSE_PRESSED = 201;
    public static final int MOUSE_RELEASED = 202;
    public static final int MOUSE_MOVED = 203;
    public static final int MOUSE_DRAGGED = 204;
    public static final int MOUSE_ENTERED = 205;
    public static final int MOUSE_EXITED = 206;
    public static final int MOUSE_WHEEL = 207;
    private final Point point;
    private final int button;
    private final int clickCount;

    public MouseEvent(Component component, Point point, int id, int button, int clickCount) {
        super(component, id);
        this.point = point;
        this.button = button;
        this.clickCount = clickCount;
    }

    public Point getPoint() {
        return point == null ? null : new Point(point);
    }

    public int getButton() {
        return button;
    }

    public int getClickCount() {
        return clickCount;
    }

    @Override
    public String paramString() {
        return super.paramString() + ", button=" + button + ", point=" + point;
    }
}

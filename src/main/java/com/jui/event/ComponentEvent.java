package com.jui.event;

import com.jui.Component;

/**
 * ComponentEvent
 *
 * @author Ian Caffey
 * @since 1.0
 */
public class ComponentEvent {
    public static final int COMPONENT_MOVED = 300;
    public static final int COMPONENT_RESIZED = 301;
    public static final int COMPONENT_OPACITY_CHANGED = 302;
    private final int id;
    private Component component;
    private boolean consumed = false;

    public ComponentEvent(Component component, int id) {
        this.component = component;
        this.id = id;
    }

    public void reassign(Component component) {
        this.component = component;
    }

    public Component getComponent() {
        return component;
    }

    public int getId() {
        return id;
    }

    protected String paramString() {
        return "component=" + component + ", id=" + id;
    }

    public void consume() {
        consumed = true;
    }

    public boolean isConsumed() {
        return consumed;
    }

    @Override
    public String toString() {
        return getClass().getCanonicalName() + "[" + paramString() + "]";
    }
}

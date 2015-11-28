package com.jui.util;

/**
 * ModelChangeEvent
 *
 * @author Ian
 * @version 1.0
 */
public class ModelChangeEvent {
    public static final int ADDITION = 1;
    public static final int REMOVAL = 2;
    public static final int UPDATE = 3;
    private final int type;
    private final Object key;
    private final Object oldValue;
    private final Object newValue;

    public ModelChangeEvent(int type, Object key, Object oldValue, Object newValue) {
        this.type = type;
        this.key = key;
        this.oldValue = oldValue;
        this.newValue = newValue;
    }

    public int getType() {
        return type;
    }

    public Object getKey() {
        return key;
    }

    public Object getOldValue() {
        return oldValue;
    }

    public Object getNewValue() {
        return newValue;
    }

    @Override
    public String toString() {
        return String.format("%s[type=%s, key=%s, old=%s, new=%s]", getClass().getCanonicalName(), getType(), getKey(), getOldValue(), getNewValue());
    }
}

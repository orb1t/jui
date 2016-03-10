package com.iancaffey.jui.util;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Model
 *
 * @author Ian Caffey
 * @since 1.0
 */
public class Model {
    private final Map<Object, Object> data = new HashMap<>();

    public void put(Object key, Object value) {
        data.put(key, value);
    }

    public <T> T remove(Object key) {
        return (T) data.remove(key);
    }

    public <T> T get(Object key) {
        return (T) data.get(key);
    }

    public <T> T get(Object key, Model backup) {
        if (data.containsKey(key))
            return (T) data.get(key);
        return backup == null ? null : backup.<T>get(key);
    }

    public <T> T get(Object key, T backup) {
        Object o = data.get(key);
        if (o == null && backup != null)
            return backup;
        return (T) o;
    }

    public <T> T get(Object key, Class<T> model, Model backup) {
        if (data.containsKey(key))
            return (T) data.get(key);
        if (backup != null)
            return backup.get(key, model);
        if (model == null)
            return null;
        if (model == Boolean.class || model == boolean.class)
            return (T) Boolean.valueOf(false);
        if (model == Byte.class || model == byte.class)
            return (T) Byte.valueOf((byte) -1);
        if (model == Character.class || model == char.class)
            return (T) Character.valueOf((char) -1);
        if (model == Short.class || model == short.class)
            return (T) Short.valueOf((short) -1);
        if (model == Integer.class || model == int.class)
            return (T) Integer.valueOf(-1);
        if (model == Long.class || model == long.class)
            return (T) Long.valueOf(-1);
        if (model == Float.class || model == float.class)
            return (T) Float.valueOf(-1);
        if (model == Double.class || model == double.class)
            return (T) Double.valueOf(-1);
        return null;
    }

    public <T> T get(Object key, Class<T> model) {
        Object o = data.get(key);
        if (o != null || model == null || model == String.class)
            return (T) o;
        if (model == Boolean.class || model == boolean.class)
            return (T) Boolean.valueOf(false);
        if (model == Byte.class || model == byte.class)
            return (T) Byte.valueOf((byte) -1);
        if (model == Character.class || model == char.class)
            return (T) Character.valueOf((char) -1);
        if (model == Short.class || model == short.class)
            return (T) Short.valueOf((short) -1);
        if (model == Integer.class || model == int.class)
            return (T) Integer.valueOf(-1);
        if (model == Long.class || model == long.class)
            return (T) Long.valueOf(-1);
        if (model == Float.class || model == float.class)
            return (T) Float.valueOf(-1);
        if (model == Double.class || model == double.class)
            return (T) Double.valueOf(-1);
        return null;
    }

    public Model getModel(Object key) {
        return get(key);
    }

    public Color getColor(Object key) {
        return get(key);
    }

    public Image getImage(Object key) {
        return get(key);
    }

    public boolean getBoolean(Object key) {
        return get(key, boolean.class);
    }

    public byte getByte(Object key) {
        return get(key, byte.class);
    }

    public short getShort(Object key) {
        return get(key, short.class);
    }

    public int getInt(Object key) {
        return get(key, int.class);
    }

    public long getLong(Object key) {
        return get(key, long.class);
    }

    public float getFloat(Object key) {
        return get(key, float.class);
    }

    public double getDouble(Object key) {
        return get(key, double.class);
    }

    public String getString(Object key) {
        return get(key, String.class);
    }

    public Model getModel(Object key, Model backup) {
        return get(key, backup);
    }

    public Color getColor(Object key, Model backup) {
        return get(key, backup);
    }

    public Image getImage(Object key, Model backup) {
        return get(key, backup);
    }

    public boolean getBoolean(Object key, Model backup) {
        return get(key, boolean.class, backup);
    }

    public byte getByte(Object key, Model backup) {
        return get(key, byte.class, backup);
    }

    public short getShort(Object key, Model backup) {
        return get(key, short.class, backup);
    }

    public int getInt(Object key, Model backup) {
        return get(key, int.class, backup);
    }

    public long getLong(Object key, Model backup) {
        return get(key, long.class, backup);
    }

    public float getFloat(Object key, Model backup) {
        return get(key, float.class, backup);
    }

    public double getDouble(Object key, Model backup) {
        return get(key, double.class, backup);
    }

    public String getString(Object key, Model backup) {
        return get(key, String.class, backup);
    }

    public Object[] keys() {
        return data.keySet().toArray(new Object[data.size()]);
    }

    public Object[] values() {
        return data.values().toArray(new Object[data.size()]);
    }
}

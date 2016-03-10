package com.iancaffey.jui.util;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Controller
 *
 * @author Ian Caffey
 * @since 1.0
 */
public class Controller {
    private final Set<ModelChangeListener> listeners = new HashSet<>();
    private Model model;

    public Controller() {
        this(new Model());
    }

    public Controller(Model model) {
        this.model = model;
    }

    public void addModelChangeListener(ModelChangeListener listener) {
        listeners.add(listener);
    }

    public void removeModelChangeListener(ModelChangeListener listener) {
        listeners.remove(listener);
    }

    public Set<ModelChangeListener> getModelChangeListeners() {
        return new LinkedHashSet<>(listeners);
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public synchronized void put(Object key, Object value) {
        Model model = getModel();
        if (model == null)
            return;
        Object old = model.get(key);
        model.put(key, value);
        Set<ModelChangeListener> listeners = getModelChangeListeners();
        if (listeners == null)
            return;
        ModelChangeEvent event = new ModelChangeEvent(old == null ? ModelChangeEvent.ADDITION : value == null ? ModelChangeEvent.REMOVAL : ModelChangeEvent.UPDATE, key, old, value);
        switch (event.getType()) {
            case ModelChangeEvent.ADDITION:
                for (ModelChangeListener listener : listeners)
                    listener.onAddition(event);
                break;
            case ModelChangeEvent.REMOVAL:
                for (ModelChangeListener listener : listeners)
                    listener.onRemoval(event);
                break;
            case ModelChangeEvent.UPDATE:
                for (ModelChangeListener listener : listeners)
                    listener.onUpdate(event);
                break;
        }
    }

    public void remove(Object key) {
        put(key, null);
    }

    public <T> T get(Object key) {
        Model model = getModel();
        return model == null ? null : model.<T>get(key);
    }

    public <T> T get(Object key, T backup) {
        Model model = getModel();
        return model == null ? (backup != null ? backup : null) : model.get(key, backup);
    }

    public boolean getBoolean(Object key) {
        Model model = getModel();
        return model != null && model.getBoolean(key);
    }

    public byte getByte(Object key) {
        Model model = getModel();
        return model == null ? -1 : model.getByte(key);
    }

    public short getShort(Object key) {
        Model model = getModel();
        return model == null ? -1 : model.getShort(key);
    }

    public float getFloat(Object key) {
        Model model = getModel();
        return model == null ? -1 : model.getFloat(key);
    }

    public int getInt(Object key) {
        Model model = getModel();
        return model == null ? -1 : model.getInt(key);
    }

    public long getLong(Object key) {
        Model model = getModel();
        return model == null ? -1 : model.getLong(key);
    }

    public double getDouble(Object key) {
        Model model = getModel();
        return model == null ? -1 : model.getDouble(key);
    }

    public String getString(Object key) {
        Model model = getModel();
        return model == null ? null : model.getString(key);
    }

    public <T> T get(Object key, Model backup) {
        Model model = getModel();
        return model == null ? null : model.<T>get(key, backup);
    }

    public boolean getBoolean(Object key, Model backup) {
        Model model = getModel();
        return model != null && model.getBoolean(key, backup);
    }

    public byte getByte(Object key, Model backup) {
        Model model = getModel();
        return model == null ? -1 : model.getByte(key, backup);
    }

    public short getShort(Object key, Model backup) {
        Model model = getModel();
        return model == null ? -1 : model.getShort(key, backup);
    }

    public float getFloat(Object key, Model backup) {
        Model model = getModel();
        return model == null ? -1 : model.getFloat(key, backup);
    }

    public int getInt(Object key, Model backup) {
        Model model = getModel();
        return model == null ? -1 : model.getInt(key, backup);
    }

    public long getLong(Object key, Model backup) {
        Model model = getModel();
        return model == null ? -1 : model.getLong(key, backup);
    }

    public double getDouble(Object key, Model backup) {
        Model model = getModel();
        return model == null ? -1 : model.getDouble(key, backup);
    }

    public String getString(Object key, Model backup) {
        Model model = getModel();
        return model == null ? null : model.getString(key, backup);
    }
}

package com.iancaffey.jui;

import com.iancaffey.jui.util.Controller;
import com.iancaffey.jui.util.ModelChangeListener;

import java.util.Set;

/**
 * View
 *
 * @author Ian Caffey
 * @since 1.0
 */
public abstract class View {
    private final Controller controller;

    public View() {
        this(new Controller());
    }

    public View(Controller controller) {
        if (controller == null)
            throw new IllegalArgumentException();
        this.controller = controller;
    }

    public Controller getController() {
        return controller;
    }

    public void addModelChangeListener(ModelChangeListener listener) {
        getController().addModelChangeListener(listener);
    }

    public void removeModelChangeListener(ModelChangeListener listener) {
        getController().removeModelChangeListener(listener);
    }

    public Set<ModelChangeListener> getModelChangeListeners() {
        return getController().getModelChangeListeners();
    }
}

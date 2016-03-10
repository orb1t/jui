package com.iancaffey.jui;

import com.iancaffey.jui.util.Controller;

import java.util.*;

/**
 * Container
 *
 * @author Ian Caffey
 * @since 1.0
 */
public class Container extends Component implements Focusable {
    public Container() {
    }

    public Container(LayoutManager manager) {
        setLayout(manager);
    }

    public Container(Controller controller) {
        super(controller);
    }

    public synchronized void add(Component component) {
        add(component, null);
    }

    public synchronized void add(Component component, String constraints) {
        if (component == null)
            return;
        Set<Component> components = getController().get(LookAndFeel.CONTAINER_CHILDREN);
        if (components == null) {
            components = new LinkedHashSet<>();
            getController().put(LookAndFeel.CONTAINER_CHILDREN, components);
        }
        if (components.contains(component))
            return;
        components.add(component);
        setConstraints(component, constraints);
        component.setParent(this);
        layout(true);
    }

    public synchronized void remove(Component component) {
        Set<Component> components = getController().get(LookAndFeel.CONTAINER_CHILDREN);
        if (components == null)
            return;
        components.remove(component);
        Map<Component, String> constraintMap = getController().get(LookAndFeel.CONTAINER_CHILDREN_CONSTRAINTS);
        if (constraintMap == null || !constraintMap.containsKey(component))
            return;
        String constraints = constraintMap.get(component);
        constraintMap.remove(component);
        Map<String, Component> reverseMap = getController().get(LookAndFeel.CONTAINER_CHILDREN_REVERSE_CONSTRAINTS);
        if (reverseMap == null)
            return;
        reverseMap.remove(constraints, component);
        component.setParent(null);
        layout(true);
    }

    public synchronized void remove(String constraints) {
        Map<String, Component> reverseMap = getController().get(LookAndFeel.CONTAINER_CHILDREN_REVERSE_CONSTRAINTS);
        if (reverseMap == null || !reverseMap.containsKey(constraints))
            return;
        Component component = reverseMap.get(constraints);
        reverseMap.remove(constraints);
        Map<Component, String> constraintMap = getController().get(LookAndFeel.CONTAINER_CHILDREN_CONSTRAINTS);
        if (constraintMap != null)
            constraintMap.remove(component);
        Set<Component> components = getController().get(LookAndFeel.CONTAINER_CHILDREN);
        if (components == null)
            return;
        components.remove(component);
        component.setParent(null);
        layout(true);
    }

    public synchronized void setConstraints(Component component, String constraints) {
        if (component == null)
            return;
        Map<Component, String> constraintMap = getController().get(LookAndFeel.CONTAINER_CHILDREN_CONSTRAINTS);
        if (constraintMap == null) {
            constraintMap = new HashMap<>();
            getController().put(LookAndFeel.CONTAINER_CHILDREN_CONSTRAINTS, constraintMap);
        }
        String oldConstraints = constraintMap.get(component);
        if (Objects.equals(oldConstraints, constraints))
            return;
        constraintMap.put(component, constraints);
        Map<String, Component> reverseMap = getController().get(LookAndFeel.CONTAINER_CHILDREN_REVERSE_CONSTRAINTS);
        if (reverseMap == null) {
            reverseMap = new HashMap<>();
            getController().put(LookAndFeel.CONTAINER_CHILDREN_REVERSE_CONSTRAINTS, reverseMap);
        }
        reverseMap.put(constraints, component);
        layout(true);
    }

    public String getConstraints(Component component) {
        if (component == null)
            return null;
        Map<Component, String> constraints = getController().get(LookAndFeel.CONTAINER_CHILDREN_CONSTRAINTS);
        return constraints == null ? null : constraints.get(component);
    }

    public Component getComponent(String constraints) {
        Map<String, Component> reverseConstraints = getController().get(LookAndFeel.CONTAINER_CHILDREN_REVERSE_CONSTRAINTS);
        return reverseConstraints == null ? null : reverseConstraints.get(constraints);
    }

    public int getComponentCount() {
        Set<Component> children = getController().get(LookAndFeel.CONTAINER_CHILDREN);
        return children == null ? 0 : children.size();
    }

    public Set<Component> getComponents() {
        Set<Component> children = getController().get(LookAndFeel.CONTAINER_CHILDREN);
        return children == null ? new HashSet<>() : new LinkedHashSet<>(children);
    }

    public Component[] getComponentArray() {
        Set<Component> children = getController().get(LookAndFeel.CONTAINER_CHILDREN);
        return children == null ? new Component[0] : children.toArray(new Component[children.size()]);
    }

    @Override
    public Component getClickFocus() {
        return getController().get(LookAndFeel.CONTAINER_CLICK_FOCUS);
    }

    @Override
    public void setClickFocus(Component component) {
        getController().put(LookAndFeel.CONTAINER_CLICK_FOCUS, component);
    }

    @Override
    public Component getDragFocus() {
        return getController().get(LookAndFeel.CONTAINER_DRAG_FOCUS);
    }

    @Override
    public void setDragFocus(Component component) {
        getController().put(LookAndFeel.CONTAINER_DRAG_FOCUS, component);
    }

    @Override
    public Component getHoverFocus() {
        return getController().get(LookAndFeel.CONTAINER_HOVER_FOCUS);
    }

    @Override
    public void setHoverFocus(Component component) {
        getController().put(LookAndFeel.CONTAINER_HOVER_FOCUS, component);
    }

    @Override
    public boolean layout() {
        boolean changed = false;
        for (Component component : getComponents())
            if (component.layout())
                changed = true;
        return super.layout() || changed;
    }

    @Override
    public void pack() {
        for (Component component : getComponents())
            component.pack();
        super.pack();
    }

    public boolean contains(Component component) {
        Set<Component> components = getComponents();
        return components != null && components.contains(component);
    }
}

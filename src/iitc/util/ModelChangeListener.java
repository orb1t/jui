package iitc.util;

/**
 * ModelChangeListener
 *
 * @author Ian
 * @version 1.0
 */
public interface ModelChangeListener {
    public void onAddition(ModelChangeEvent event);

    public void onRemoval(ModelChangeEvent event);

    public void onUpdate(ModelChangeEvent event);
}

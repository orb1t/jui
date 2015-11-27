package iitc.jui;

/**
 * Focusable
 *
 * @author Ian
 * @version 1.0
 */
public interface Focusable {
    public Component getHoverFocus();

    public void setHoverFocus(Component component);

    public Component getClickFocus();

    public void setClickFocus(Component component);

    public Component getDragFocus();

    public void setDragFocus(Component component);
}

package iitc.jui.event;

/**
 * MouseMotionListener
 *
 * @author Ian
 * @version 1.0
 */
public interface MouseMotionListener extends EventListener {
    public void onMouseDragged(MouseEvent event);

    public void onMouseMoved(MouseEvent event);

    public void onMouseEntered(MouseEvent event);

    public void onMouseExited(MouseEvent event);
}

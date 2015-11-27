package iitc.jui.event;

/**
 * ComponentListener
 *
 * @author Ian
 * @version 1.0
 */
public interface ComponentListener extends EventListener {
    public void onResize(ComponentEvent e);

    public void onMove(ComponentEvent e);

    public void onOpacityChanged(ComponentEvent e);
}

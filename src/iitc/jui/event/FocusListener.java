package iitc.jui.event;

/**
 * FocusListener
 *
 * @author Ian
 * @version 1.0
 */
public interface FocusListener extends EventListener {
    public void onFocusGained(FocusEvent event);

    public void onFocusLost(FocusEvent event);
}

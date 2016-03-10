package com.iancaffey.jui.event;

/**
 * MouseListener
 *
 * @author Ian Caffey
 * @since 1.0
 */
public interface MouseListener extends EventListener {
    public void onMouseClicked(MouseEvent event);

    public void onMousePressed(MouseEvent event);

    public void onMouseReleased(MouseEvent event);
}

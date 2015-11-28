package com.jui.event;

/**
 * KeyListener
 *
 * @author Ian
 * @version 1.0
 */
public interface KeyListener {
    public void keyTyped(KeyEvent event);

    public void keyPressed(KeyEvent event);

    public void keyReleased(KeyEvent event);
}

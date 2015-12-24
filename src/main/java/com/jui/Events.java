package com.jui;

import com.jui.event.ComponentEvent;
import com.jui.event.MouseEvent;

import java.awt.*;

/**
 * Events
 *
 * @author Ian
 * @version 1.0
 */
public class Events {
    private Events() {

    }

    public static int getId(AWTEvent event) {
        switch (event.getID()) {
            case java.awt.event.MouseEvent.MOUSE_EXITED:
                return MouseEvent.MOUSE_EXITED;
            case java.awt.event.MouseEvent.MOUSE_ENTERED:
                return MouseEvent.MOUSE_ENTERED;
            case java.awt.event.MouseEvent.MOUSE_CLICKED:
                return MouseEvent.MOUSE_CLICKED;
            case java.awt.event.MouseEvent.MOUSE_PRESSED:
                return MouseEvent.MOUSE_PRESSED;
            case java.awt.event.MouseEvent.MOUSE_RELEASED:
                return MouseEvent.MOUSE_RELEASED;
            case java.awt.event.MouseEvent.MOUSE_DRAGGED:
                return MouseEvent.MOUSE_DRAGGED;
            case java.awt.event.MouseEvent.MOUSE_MOVED:
                return MouseEvent.MOUSE_MOVED;
            case java.awt.event.MouseEvent.MOUSE_WHEEL:
                return MouseEvent.MOUSE_WHEEL;
        }
        return -1;
    }

    public static ComponentEvent event(AWTEvent event, com.jui.Component target) {
        if (event instanceof java.awt.event.MouseEvent)
            return new MouseEvent(target, ((java.awt.event.MouseEvent) event).getPoint(), getId(event), ((java.awt.event.MouseEvent) event).getButton(), ((java.awt.event.MouseEvent) event).getClickCount());
        //TODO:Add more events
        return null;
    }
}

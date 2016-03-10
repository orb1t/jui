package com.jui;

import java.awt.*;

/**
 * UIManager
 *
 * @author Ian Caffey
 * @since 1.0
 */
public class UIManager {
    private static RootPane pane = new RootPane();

    public static RootPane getRootPane() {
        return pane;
    }

    public static void setRootPane(RootPane pane) {
        UIManager.pane = pane;
    }

    public static void dispatch(final AWTEvent event) {
        UIManager.getRootPane().dispatch(Events.event(event, UIManager.getRootPane()));
    }

    public static void paint(Graphics graphics) {
        RootPane pane = getRootPane();
        if (pane == null)
            return;
        pane.paint(graphics);
        com.jui.Component focus = pane.getClickFocus();
        if (focus == null || !focus.isVisible()) {
            focus = pane.getDragFocus();
            if (focus == null || !focus.isVisible())
                focus = pane.getHoverFocus();
        }
        while (focus instanceof Focusable) {
            com.jui.Component interiorFocus;
            Focusable focusable = (Focusable) focus;
            interiorFocus = focusable.getDragFocus();
            if (interiorFocus == null || !interiorFocus.isVisible())
                interiorFocus = focusable.getHoverFocus();
            if (interiorFocus == null || !interiorFocus.isVisible())
                break;
            focus = interiorFocus;
        }
        if (focus == null || !focus.isVisible())
            focus = pane;
        com.jui.Cursor cursor = focus.isDisabled() ? focus.getDisabledCursor() : focus.isPressed() ? focus.getPressedCursor() : focus.isHovered() ? focus.getRolloverCursor() : focus.getCursor();
        if (cursor == null)
            cursor = focus.getCursor();
        if (cursor == null)
            return;
        Point local = pane.getLocalMousePosition();
        if (local == null)
            return;
        Shape oldClip = graphics.getClip();
        graphics.setClip(null);
        cursor.render(pane, graphics, local.x, local.y);
        graphics.setClip(oldClip);
    }
}

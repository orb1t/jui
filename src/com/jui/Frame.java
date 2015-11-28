package com.jui;

import com.jui.event.*;

import java.awt.*;
import java.util.Objects;

/**
 * Frame
 *
 * @author Ian
 * @version 1.0
 */
public class Frame extends com.jui.Component implements Focusable {
    public Frame() {
        this((String) null);
    }

    public Frame(String text) {
        this(new Text(text));
    }

    public Frame(Text text) {
        final Header header = new Header();
        final Point dragPoint = new Point(-1, -1);
        header.addMouseListener(new MouseListener() {
            @Override
            public void onMouseClicked(MouseEvent event) {
                if (event.getClickCount() == 2 && isResizable())
                    setMaximized(!isMaximized());
            }

            @Override
            public void onMousePressed(MouseEvent event) {
                dragPoint.setLocation(event.getPoint());
            }

            @Override
            public void onMouseReleased(MouseEvent event) {
                dragPoint.setLocation(-1, -1);
            }
        });
        header.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void onMouseDragged(MouseEvent event) {
                if (dragPoint.x == -1 && dragPoint.y == -1)
                    return;
                int x = 0;
                int y = 0;
                Point p = event.getPoint();
                if (isMaximized()) {
                    Rectangle cache = getMaximizedCacheSize();
                    Rectangle bounds = getBounds();
                    if (cache != null && bounds != null) {
                        double percentage = (p.x - bounds.x) / (double) bounds.width;
                        x = p.x - (int) Math.round(cache.width * percentage);
                        dragPoint.y -= bounds.y - cache.y;
                    }
                    setMaximized(false);
                } else {
                    x = Math.max(0, getX() + p.x - dragPoint.x);
                    y = Math.max(0, getY() + p.y - dragPoint.y);
                }
                com.jui.Component parent = getParent();
                if (parent != null) {
                    x = Math.min(parent.getWidth() - Math.min(10, getWidth()), x);
                    y = Math.min(parent.getHeight() - header.getHeight(), y);
                }
                setLocation(x, y);
                dragPoint.setLocation(p);
            }

            @Override
            public void onMouseMoved(MouseEvent event) {

            }

            @Override
            public void onMouseEntered(MouseEvent event) {

            }

            @Override
            public void onMouseExited(MouseEvent event) {

            }
        });
        header.setText(text);
        com.jui.Button x = new com.jui.Button(new Text("x"));
        x.setBackground(null);
        x.setBorder(null);
        x.clearOverlay();
        x.setPadding(new Insets(2, 0, 2, 2));
        x.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                Frame.this.setVisible(false);
            }
        });
        header.addRightButton(x);
        setHeader(header);
        UIManager.getRootPane().add(this);
    }

    public Frame(com.jui.Component component) {
        final Header header = new Header();
        final Point dragPoint = new Point(-1, -1);
        header.addMouseListener(new MouseListener() {
            @Override
            public void onMouseClicked(MouseEvent event) {
                if (event.getClickCount() == 2 && isResizable())
                    setMaximized(!isMaximized());
            }

            @Override
            public void onMousePressed(MouseEvent event) {
                dragPoint.setLocation(event.getPoint());
            }

            @Override
            public void onMouseReleased(MouseEvent event) {
                dragPoint.setLocation(-1, -1);
            }
        });
        header.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void onMouseDragged(MouseEvent event) {
                if (dragPoint.x == -1 && dragPoint.y == -1)
                    return;
                if (isMaximized())
                    setMaximized(false);
                Point p = event.getPoint();
                int x = Math.max(0, getX() + p.x - dragPoint.x);
                int y = Math.max(0, getY() + p.y - dragPoint.y);
                com.jui.Component parent = getParent();
                if (parent != null) {
                    x = Math.min(parent.getWidth() - Math.min(10, getWidth()), x);
                    y = Math.min(parent.getHeight() - header.getHeight(), y);
                }
                setLocation(x, y);
                dragPoint.setLocation(p);
            }

            @Override
            public void onMouseMoved(MouseEvent event) {

            }

            @Override
            public void onMouseEntered(MouseEvent event) {

            }

            @Override
            public void onMouseExited(MouseEvent event) {

            }
        });
        com.jui.Button x = new com.jui.Button(new Text("x"));
        x.setBackground(null);
        x.setBorder(null);
        x.clearOverlay();
        x.setPadding(new Insets(2, 0, 2, 2));
        x.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                Frame.this.setVisible(false);
            }
        });
        header.addRightButton(x);
        setHeader(header);
        setContent(component);
        UIManager.getRootPane().add(this);
    }

    public Frame(Header header, com.jui.Component content) {
        setHeader(header);
        setContent(content);
    }

    public com.jui.Component getContent() {
        return getController().get(LookAndFeel.FRAME_CONTENT);
    }

    public void setContent(com.jui.Component component) {
        com.jui.Component old = getContent();
        if (old == component)
            return;
        if (old != null)
            old.setParent(null);
        if (component != null)
            component.setParent(this);
        getController().put(LookAndFeel.FRAME_CONTENT, component);
        layout(true);
    }

    public Header getHeader() {
        return getController().get(LookAndFeel.FRAME_HEADER);
    }

    public void setHeader(Header header) {
        Header old = getHeader();
        if (old == header)
            return;
        if (old != null)
            old.setParent(null);
        if (header != null)
            header.setParent(this);
        getController().put(LookAndFeel.FRAME_HEADER, header);
        layout(true);
    }

    public boolean isMaximized() {
        return getController().getBoolean(LookAndFeel.FRAME_MAXIMIZED, getBackupModel());
    }

    public void setMaximized(boolean maximized) {
        if (isMaximized() == maximized)
            return;
        getController().put(LookAndFeel.FRAME_MAXIMIZED, maximized);
        if (maximized) {
            Rectangle bounds = getBounds();
            setMaximizedCacheSize(bounds);
            com.jui.Component parent = getParent();
            if (parent != null)
                setBounds(parent.getBounds());
        } else {
            setBounds(getMaximizedCacheSize());
        }
    }

    public Rectangle getMaximizedCacheSize() {
        return getController().<Rectangle>get(LookAndFeel.FRAME_MAXIMIZED_CACHE_SIZE, getBackupModel());
    }

    public void setMaximizedCacheSize(Rectangle rectangle) {
        if (Objects.equals(getMaximizedCacheSize(), rectangle))
            return;
        getController().put(LookAndFeel.FRAME_MAXIMIZED_CACHE_SIZE, rectangle);
    }

    //TODO:Make use of
    public boolean isHeadless() {
        return getController().getBoolean(LookAndFeel.FRAME_HEADLESS, getBackupModel());
    }

    public void setHeadless(boolean headless) {
        if (isHeadless() == headless)
            return;
        getController().put(LookAndFeel.FRAME_HEADLESS, headless);
    }

    //TODO:Make use of
    public boolean isResizable() {
        return getController().getBoolean(LookAndFeel.FRAME_RESIZABLE, getBackupModel());
    }

    public void setResizable(boolean resizable) {
        if (isResizable() == resizable)
            return;
        getController().put(LookAndFeel.FRAME_RESIZABLE, resizable);
    }

    @Override
    public com.jui.Component getClickFocus() {
        return getController().get(LookAndFeel.FRAME_CLICK_FOCUS);
    }

    @Override
    public void setClickFocus(com.jui.Component component) {
        getController().put(LookAndFeel.FRAME_CLICK_FOCUS, component);
    }

    @Override
    public com.jui.Component getDragFocus() {
        return getController().get(LookAndFeel.FRAME_DRAG_FOCUS);
    }

    @Override
    public void setDragFocus(com.jui.Component component) {
        getController().put(LookAndFeel.FRAME_DRAG_FOCUS, component);
    }

    @Override
    public com.jui.Component getHoverFocus() {
        return getController().get(LookAndFeel.FRAME_HOVER_FOCUS);
    }

    @Override
    public void setHoverFocus(com.jui.Component component) {
        getController().put(LookAndFeel.FRAME_HOVER_FOCUS, component);
    }

    @Override
    public boolean layout() {
        boolean changed = false;
        Header header = getHeader();
        if (header != null && header.layout())
            changed = true;
        com.jui.Component content = getContent();
        if (content != null && content.layout())
            changed = true;
        return super.layout() || changed;
    }

    @Override
    public void pack() {
        Header header = getHeader();
        if (header != null)
            header.pack();
        com.jui.Component content = getContent();
        if (content != null)
            content.pack();
        super.pack();
    }
}

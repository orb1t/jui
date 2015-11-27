package iitc.jui;

import iitc.jui.event.*;
import iitc.util.Controller;
import iitc.util.Model;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Component
 *
 * @author Ian
 * @version 1.0
 */
public class Component extends View {
    public Component() {
        super();
        setX(0);
        setY(0);
        setWidth(0);
        setHeight(0);
        MouseAdapter adapter = new MouseAdapter() {
            @Override
            public void onMouseMoved(MouseEvent event) {
                super.onMouseMoved(event);
                setHovered(true);
            }

            @Override
            public void onMousePressed(MouseEvent event) {
                super.onMousePressed(event);
                setPressed(true);
            }

            @Override
            public void onMouseReleased(MouseEvent event) {
                super.onMouseReleased(event);
                setPressed(false);
            }

            @Override
            public void onMouseEntered(MouseEvent event) {
                super.onMouseEntered(event);
                setHovered(true);
            }

            @Override
            public void onMouseExited(MouseEvent event) {
                super.onMouseExited(event);
                setHovered(false);
            }
        };
        addMouseListener(adapter);
        addMouseMotionListener(adapter);
        addFocusListener(new FocusListener() {
            @Override
            public void onFocusGained(FocusEvent event) {
            }

            @Override
            public void onFocusLost(FocusEvent event) {
                if (event.getType() == FocusEvent.HOVER)
                    return;
                setHovered(false);
                setPressed(false);
            }
        });
    }

    public Component(Controller controller) {
        super(controller);
        setX(0);
        setY(0);
        setWidth(0);
        setHeight(0);
        MouseAdapter adapter = new MouseAdapter() {
            @Override
            public void onMouseMoved(MouseEvent event) {
                super.onMouseMoved(event);
                setHovered(true);
            }

            @Override
            public void onMousePressed(MouseEvent event) {
                super.onMousePressed(event);
                setPressed(true);
            }

            @Override
            public void onMouseReleased(MouseEvent event) {
                super.onMouseReleased(event);
                setPressed(false);
            }

            @Override
            public void onMouseEntered(MouseEvent event) {
                super.onMouseEntered(event);
                setHovered(true);
            }

            @Override
            public void onMouseExited(MouseEvent event) {
                super.onMouseExited(event);
                setHovered(false);
            }
        };
        addMouseListener(adapter);
        addMouseMotionListener(adapter);
        addFocusListener(new FocusListener() {
            @Override
            public void onFocusGained(FocusEvent event) {
            }

            @Override
            public void onFocusLost(FocusEvent event) {
                if (event.getType() == FocusEvent.HOVER)
                    return;
                setHovered(false);
                setPressed(false);
            }
        });
    }

    public boolean contains(int x, int y) {
        return getScreenBounds().contains(x, y);
    }

    public boolean contains(Point p) {
        return p != null && contains(p.x, p.y);
    }

    public boolean contains(Rectangle rectangle) {
        return rectangle != null && getScreenBounds().contains(rectangle);
    }

    public int getX() {
        return getController().getInt(LookAndFeel.COMPONENT_X);
    }

    private void setX(int x) {
        getController().put(LookAndFeel.COMPONENT_X, x);
    }

    public int getScreenX() {
        int x = getX();
        Component parent = getParent();
        if (parent != null)
            x += parent.getScreenX();
        return x;
    }

    public int getScreenY() {
        int y = getY();
        Component parent = getParent();
        if (parent != null)
            y += parent.getScreenY();
        return y;
    }

    public int getY() {
        return getController().getInt(LookAndFeel.COMPONENT_Y);
    }

    private void setY(int y) {
        getController().put(LookAndFeel.COMPONENT_Y, y);
    }

    public Point getLocation() {
        return new Point(getX(), getY());
    }

    public synchronized void setLocation(Point p) {
        if (p == null)
            setLocation(-1, -1);
        else
            setLocation(p.x, p.y);
    }

    public void translate(int dx, int dy) {
        setLocation(getX() + dx, getY() + dy);
    }

    public synchronized void setLocation(int x, int y) {
        int oldX = getX();
        int oldY = getY();
        if (oldX == x && oldY == y)
            return;
        setX(x);
        setY(y);
        Component parent = getParent();
        if (parent == null)
            return;
        int width = getWidth();
        int height = getHeight();
        parent.repaint(oldX, oldY, width, height);
        parent.repaint(x, y, width, height);
    }

    public void setLocationRelativeTo(Component component) {
        if (component == null)
            component = getParent();
        if (component == null)
            return;
        int w = getWidth();
        int h = getHeight();
        int cx = component.getX();
        int cy = component.getY();
        int cw = component.getWidth();
        int ch = component.getHeight();
        setLocation((int) Math.round(cx + (cw - w) / 2.0d), (int) Math.round(cy + (ch - h) / 2.0d));
    }

    public int getWidth() {
        return getController().getInt(LookAndFeel.COMPONENT_WIDTH);
    }

    private void setWidth(int width) {
        getController().put(LookAndFeel.COMPONENT_WIDTH, width);
    }

    public int getHeight() {
        return getController().getInt(LookAndFeel.COMPONENT_HEIGHT);
    }

    private void setHeight(int height) {
        getController().put(LookAndFeel.COMPONENT_HEIGHT, height);
    }

    public Dimension getSize() {
        return new Dimension(getWidth(), getHeight());
    }

    public synchronized void setSize(Dimension dimension) {
        if (dimension == null)
            setSize(0, 0);
        else
            setSize(dimension.width, dimension.height);
    }

    public synchronized void setSize(int width, int height) {
        int oldWidth = getWidth();
        int oldHeight = getHeight();
        if (oldWidth == width && oldHeight == height)
            return;
        setWidth(width);
        setHeight(height);
        layout(true);
        Component parent = getParent();
        if (parent != null)
            parent.repaint(getX(), getY(), oldWidth, oldHeight);
    }

    public void grow(double ds) {
        grow(ds, ds);
    }

    public void grow(double dx, double dy) {
        setSize((int) Math.round(getWidth() * dx), (int) Math.round(getHeight() * dy));
    }

    public Dimension getPreferredSize() {
        return getController().<Dimension>get(LookAndFeel.COMPONENT_PREFERRED_SIZE, getBackupModel());
    }

    public void setPreferredSize(Dimension dimension) {
        getController().put(LookAndFeel.COMPONENT_PREFERRED_SIZE, dimension);
    }

    public void setPreferredSize(int width, int height) {
        setPreferredSize(new Dimension(width, height));
    }

    public Rectangle getBounds() {
        return new Rectangle(getX(), getY(), getWidth(), getHeight());
    }

    public void setBounds(Rectangle rectangle) {
        if (rectangle == null)
            return;
        setBounds(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
    }

    public Rectangle getScreenBounds() {
        return new Rectangle(getScreenX(), getScreenY(), getWidth(), getHeight());
    }

    public synchronized void setBounds(int x, int y, int width, int height) {
        int oldX = getX();
        int oldY = getY();
        int oldWidth = getWidth();
        int oldHeight = getHeight();
        if (oldX == x && oldY == y && oldWidth == width && oldHeight == height)
            return;
        setX(x);
        setY(y);
        setWidth(width);
        setHeight(height);
        layout(true);
        Component parent = getParent();
        if (parent != null)
            parent.repaint(oldX, oldY, oldWidth, oldHeight);
    }

    public Point getMousePosition() {
        Point point = getController().get(LookAndFeel.COMPONENT_MOUSE_POSITION);
        return point == null ? null : new Point(point);
    }

    private void setMousePosition(Point point) {
        if (point != null && !contains(point))
            point = null;
        getController().put(LookAndFeel.COMPONENT_MOUSE_POSITION, point);
    }

    public Point getLocalMousePosition() {
        Point point = getMousePosition();
        if (point == null)
            return null;
        point.translate(-getX(), -getY());
        return point;
    }

    public Set<Overlay> getOverlays() {
        Set<Overlay> overlays = getController().<Set<Overlay>>get(LookAndFeel.COMPONENT_OVERLAYS, getBackupModel());
        return overlays == null ? new HashSet<>() : new LinkedHashSet<>(overlays);
    }

    public synchronized void addOverlay(Overlay overlay) {
        if (overlay == null)
            return;
        Set<Overlay> overlays = getController().get(LookAndFeel.COMPONENT_OVERLAYS);
        if (overlays == null) {
            overlays = new HashSet<>();
            getController().put(LookAndFeel.COMPONENT_OVERLAYS, overlays);
        }
        if (overlays.contains(overlay))
            return;
        overlays.add(overlay);
        repaint();
    }

    public synchronized void removeOverlay(Overlay overlay) {
        if (overlay == null)
            return;
        Set<Overlay> overlays = getController().get(LookAndFeel.COMPONENT_OVERLAYS);
        if (overlays == null) {
            overlays = new HashSet<>();
            getController().put(LookAndFeel.COMPONENT_OVERLAYS, overlays);
        }
        if (overlays.contains(overlay))
            return;
        overlays.remove(overlay);
        repaint();
    }

    public void clearOverlay() {
        Set<Overlay> overlays = getController().get(LookAndFeel.COMPONENT_OVERLAYS);
        if (overlays == null)
            getController().put(LookAndFeel.COMPONENT_OVERLAYS, null);
        else
            overlays.clear();
        repaint();
    }

    public Background getBackground() {
        return getController().<Background>get(LookAndFeel.COMPONENT_BACKGROUND, getBackupModel());
    }

    public synchronized void setBackground(Background background) {
        Background old = getBackground();
        if (old == background)
            return;
        getController().put(LookAndFeel.COMPONENT_BACKGROUND, background);
        repaint();
    }

    public Border getBorder() {
        return getController().<Border>get(LookAndFeel.COMPONENT_BORDER, getBackupModel());
    }

    public void setBorder(Border border) {
        Border old = getBorder();
        if (old == border)
            return;
        getController().put(LookAndFeel.COMPONENT_BORDER, border);
        repaint();
    }

    public Insets getPadding() {
        int left = 0;
        int top = 0;
        int right = 0;
        int bottom = 0;
        Insets insets = getController().<Insets>get(LookAndFeel.COMPONENT_INSETS, getBackupModel());
        if (insets != null) {
            left = insets.left;
            top = insets.top;
            right = insets.right;
            bottom = insets.bottom;
        }
        Border border = getBorder();
        if (border != null) {
            Insets borderInsets = border.getPadding(this);
            if (borderInsets != null) {
                left = Math.max(left, borderInsets.left);
                top = Math.max(left, borderInsets.top);
                right = Math.max(left, borderInsets.right);
                bottom = Math.max(left, borderInsets.bottom);
            }
        }
        return new Insets(left, top, right, bottom);
    }

    public void setPadding(Insets insets) {
        getController().put(LookAndFeel.COMPONENT_INSETS, insets);
    }

    public BufferedImage getCachedImage() {
        return getController().get(LookAndFeel.COMPONENT_CACHED_IMAGE);
    }

    public void setCachedImage(BufferedImage image) {
        getController().put(LookAndFeel.COMPONENT_CACHED_IMAGE, image);
    }

    public float getOpacity() {
        return getController().getFloat(LookAndFeel.COMPONENT_OPACITY, getBackupModel());
    }

    public synchronized void setOpacity(float opacity) {
        opacity = Math.min(1.0f, Math.max(0.0f, opacity));
        float old = getOpacity();
        if (old == opacity)
            return;
        getController().put(LookAndFeel.COMPONENT_OPACITY, opacity);
        repaint();
    }

    public boolean isVisible() {
        return getOpacity() != 0.0f;
    }

    public void setVisible(boolean visible) {
        setOpacity(visible ? 1.0f : 0.0f);
    }

    //TODO:Add cases for new dynamic components
    public void dispose() {
        setVisible(false);
        Component parent = getParent();
        if (parent instanceof Label) {
            if (this instanceof Text)
                ((Label) parent).setText(null);
            else if (this instanceof Picture)
                ((Label) parent).setPicture(null);
        } else if (parent instanceof Button) {
            if (this instanceof Text)
                ((Button) parent).setText(null);
            else if (this instanceof Picture)
                ((Button) parent).setPicture(null);
        } else if (parent instanceof Container) {
            ((Container) parent).remove(this);
        } else if (parent instanceof Frame) {
            if (this instanceof Header)
                ((Frame) parent).setHeader(null);
            else
                ((Frame) parent).setContent(null);
        } else if (parent instanceof RootPane) {
            if (this instanceof Frame)
                ((RootPane) parent).remove((Frame) this);
        } else if (parent instanceof ProgressBar) {
            if (this instanceof Text)
                ((ProgressBar) parent).setText(null);
        }
    }

    public Component getParent() {
        return getController().get(LookAndFeel.COMPONENT_PARENT);
    }

    public void setParent(Component component) {
        getController().put(LookAndFeel.COMPONENT_PARENT, component);
    }

    public ImageBuilder getImageBuilder() {
        return getController().<ImageBuilder>get(LookAndFeel.COMPONENT_IMAGE_BUILDER, getBackupModel());
    }

    public synchronized void setImageBuilder(ImageBuilder builder) {
        ImageBuilder old = getImageBuilder();
        if (old == builder)
            return;
        getController().put(LookAndFeel.COMPONENT_IMAGE_BUILDER, builder);
        repaint();
    }

    protected Model getBackupModel() {
        LookAndFeel lookAndFeel = getLookAndFeel();
        return lookAndFeel == null ? null : lookAndFeel.getModel(this);
    }

    public LookAndFeel getLookAndFeel() {
        LookAndFeel lookAndFeel = getController().get(LookAndFeel.COMPONENT_LOOK_AND_FEEL);
        return lookAndFeel == null ? LookAndFeel.getDefault() : lookAndFeel;
    }

    public synchronized void setLookAndFeel(LookAndFeel lookAndFeel) {
        LookAndFeel old = getLookAndFeel();
        if (old == lookAndFeel)
            return;
        getController().put(LookAndFeel.COMPONENT_LOOK_AND_FEEL, lookAndFeel);
        repaint();
    }

    public boolean isDisabled() {
        return getController().getBoolean(LookAndFeel.COMPONENT_DISABLED);
    }

    public void setDisabled(boolean disabled) {
        if (isDisabled() == disabled)
            return;
        getController().put(LookAndFeel.COMPONENT_DISABLED, disabled);
        repaint();
    }

    public boolean isHovered() {
        return getController().getBoolean(LookAndFeel.COMPONENT_HOVERED);
    }

    public void setHovered(boolean hovered) {
        if (isHovered() == hovered)
            return;
        getController().put(LookAndFeel.COMPONENT_HOVERED, hovered);
        repaint();
    }

    public boolean isPressed() {
        return getController().getBoolean(LookAndFeel.COMPONENT_PRESSED);
    }

    public void setPressed(boolean pressed) {
        if (isPressed() == pressed)
            return;
        getController().put(LookAndFeel.COMPONENT_PRESSED, pressed);
        repaint();
    }

    public Cursor getCursor() {
        return getController().<Cursor>get(LookAndFeel.COMPONENT_CURSOR, getBackupModel());
    }

    public void setCursor(Cursor cursor) {
        getController().put(LookAndFeel.COMPONENT_CURSOR, cursor);
    }

    public Cursor getDisabledCursor() {
        return getController().<Cursor>get(LookAndFeel.COMPONENT_CURSOR_DISABLED, getBackupModel());
    }

    public void setDisabledCursor(Cursor cursor) {
        getController().put(LookAndFeel.COMPONENT_CURSOR_DISABLED, cursor);
    }

    public Cursor getRolloverCursor() {
        return getController().<Cursor>get(LookAndFeel.COMPONENT_CURSOR_ROLLOVER, getBackupModel());
    }

    public void setRolloverCursor(Cursor cursor) {
        getController().put(LookAndFeel.COMPONENT_CURSOR_ROLLOVER, cursor);
    }

    public Cursor getPressedCursor() {
        return getController().<Cursor>get(LookAndFeel.COMPONENT_CURSOR_PRESSED, getBackupModel());
    }

    public void setPressedCursor(Cursor cursor) {
        getController().put(LookAndFeel.COMPONENT_CURSOR_PRESSED, cursor);
    }

    public void invalidate() {
        setPreferredSize(null);
        setCachedImage(null);
    }

    public void repaint() {
        repaint(0, 0, getWidth(), getHeight());
    }

    public void repaint(int x, int y, int width, int height) {
        synchronized (this) {
            int compX = getX();
            int compY = getY();
            int compWidth = getWidth();
            int compHeight = getHeight();
            float opacity = getOpacity();
            if (width <= 0 || height <= 0 || compWidth <= 0 || compHeight <= 0)
                return;
            ImageBuilder builder = getImageBuilder();
            if (builder == null)
                return;
            BufferedImage image = getCachedImage();
            if (image == null || image.getWidth() != compWidth || image.getHeight() != compHeight)
                image = new BufferedImage(compWidth, compHeight, BufferedImage.TYPE_INT_ARGB);
            Graphics graphics = image.getGraphics();
            ((Graphics2D) graphics).setComposite(AlphaComposite.Src);
            graphics.clearRect(x, y, width, height);
            Color color = graphics.getColor();
            graphics.setColor(LookAndFeel.TRANSPARENT);
            graphics.fillRect(x, y, width, height);
            graphics.setClip(x, y, width, height);
            graphics.setColor(color);
            if (opacity > 0.0f) {
                ((Graphics2D) graphics).setComposite(AlphaComposite.SrcOver.derive(opacity));
                builder.paintBackground(this, graphics, x, y, width, height);
                builder.paintComponent(this, graphics, x, y, width, height);
                builder.paintOverlay(this, graphics, x, y, width, height);
            }
            setCachedImage(image);
            Component parent = getParent();
            if (parent == null)
                return;
            parent.repaint(compX, compY, compWidth, compHeight);
        }
    }

    public BufferedImage render() {
        BufferedImage image = getCachedImage();
        if (image != null)
            return image;
        repaint();
        return getCachedImage();
    }

    public LayoutManager getLayout() {
        return getController().<LayoutManager>get(LookAndFeel.COMPONENT_LAYOUT_MANAGER, getBackupModel());
    }

    public void setLayout(LayoutManager manager) {
        getController().put(LookAndFeel.COMPONENT_LAYOUT_MANAGER, manager);
    }

    public boolean layout() {
        return layout(false);
    }

    public boolean layout(boolean forceRepaint) {
        LayoutManager manager = getLayout();
        if ((manager == null || (!manager.layout(this))) && !forceRepaint)
            return false;
        repaint();
        return true;
    }

    public void pack() {
        LayoutManager manager = getLayout();
        if (manager == null)
            return;
        Dimension dimension = getPreferredSize();
        Dimension laidOut = manager.size(this);
        if (dimension == null || (laidOut != null && (laidOut.width > dimension.width || laidOut.height > dimension.height)))
            dimension = laidOut;
        if (dimension == null)
            return;
        setPreferredSize(dimension);
        setSize(dimension);
        if (!manager.layout(this))
            return;
        repaint();
    }

    public void paint(Graphics graphics) {
        synchronized (this) {
            int x = getX();
            int y = getY();
            BufferedImage image = render();
            if (image == null)
                return;
            graphics.drawImage(image, x, y, null);
        }
    }

    public synchronized EventDispatcher getEventDispatcher() {
        return getController().<EventDispatcher>get(LookAndFeel.COMPONENT_EVENT_DISPATCHER, getBackupModel());
    }

    public void setEventDispatcher(EventDispatcher dispatcher) {
        getController().put(LookAndFeel.COMPONENT_EVENT_DISPATCHER, dispatcher);
    }

    public synchronized void dispatch(ComponentEvent event) {
        if (event == null || event.isConsumed())
            return;
        Component parent = event.getComponent();
        event.reassign(this);
        EventDispatcher dispatcher = getEventDispatcher();
        if (dispatcher != null)
            dispatcher.dispatch(this, event);
        switch (event.getId()) {
            case ComponentEvent.COMPONENT_MOVED:
                for (ComponentListener listener : getComponentListeners())
                    listener.onMove(event);
                break;
            case ComponentEvent.COMPONENT_OPACITY_CHANGED:
                for (ComponentListener listener : getComponentListeners())
                    listener.onOpacityChanged(event);
                break;
            case ComponentEvent.COMPONENT_RESIZED:
                for (ComponentListener listener : getComponentListeners())
                    listener.onResize(event);
                break;
            case ActionEvent.ACTION_PERFORMED:
                for (ActionListener listener : getActionListeners())
                    listener.actionPerformed((ActionEvent) event);
                break;
            case FocusEvent.FOCUS_GAINED:
                for (FocusListener listener : getFocusListeners())
                    listener.onFocusGained((FocusEvent) event);
                break;
            case FocusEvent.FOCUS_LOST:
                for (FocusListener listener : getFocusListeners())
                    listener.onFocusLost((FocusEvent) event);
                break;
            case KeyEvent.KEY_PRESSED:
                for (KeyListener listener : getKeyListeners())
                    listener.keyPressed((KeyEvent) event);
                break;
            case KeyEvent.KEY_RELEASED:
                for (KeyListener listener : getKeyListeners())
                    listener.keyReleased((KeyEvent) event);
                break;
            case KeyEvent.KEY_TYPED:
                for (KeyListener listener : getKeyListeners())
                    listener.keyTyped((KeyEvent) event);
                break;
            case MouseEvent.MOUSE_CLICKED:
                for (MouseListener listener : getMouseListeners())
                    listener.onMouseClicked((MouseEvent) event);
                break;
            case MouseEvent.MOUSE_DRAGGED:
                setMousePosition(((MouseEvent) event).getPoint());
                for (MouseMotionListener listener : getMouseMotionListeners())
                    listener.onMouseDragged((MouseEvent) event);
                break;
            case MouseEvent.MOUSE_ENTERED:
                for (MouseMotionListener listener : getMouseMotionListeners())
                    listener.onMouseEntered((MouseEvent) event);
                break;
            case MouseEvent.MOUSE_EXITED:
                for (MouseMotionListener listener : getMouseMotionListeners())
                    listener.onMouseExited((MouseEvent) event);
                break;
            case MouseEvent.MOUSE_MOVED:
                setMousePosition(((MouseEvent) event).getPoint());
                for (MouseMotionListener listener : getMouseMotionListeners())
                    listener.onMouseMoved((MouseEvent) event);
                break;
            case MouseEvent.MOUSE_RELEASED:
                setMousePosition(((MouseEvent) event).getPoint());
                for (MouseListener listener : getMouseListeners())
                    listener.onMouseReleased((MouseEvent) event);
                break;
            case MouseEvent.MOUSE_PRESSED:
                setMousePosition(((MouseEvent) event).getPoint());
                for (MouseListener listener : getMouseListeners())
                    listener.onMousePressed((MouseEvent) event);
                break;
            case MouseEvent.MOUSE_WHEEL:
                for (MouseWheelListener listener : getMouseWheelListeners())
                    listener.onMouseWheelMoved((MouseWheelEvent) event);
                break;
        }
        event.reassign(parent);
    }

    public synchronized void addActionListener(ActionListener listener) {
        Set<ActionListener> listeners = getController().get(LookAndFeel.COMPONENT_ACTION_LISTENERS);
        if (listeners == null) {
            listeners = new HashSet<>();
            getController().put(LookAndFeel.COMPONENT_ACTION_LISTENERS, listeners);
        }
        if (listeners.contains(listener))
            return;
        listeners.add(listener);
    }

    public synchronized void removeActionListener(ActionListener listener) {
        Set<ActionListener> listeners = getController().get(LookAndFeel.COMPONENT_ACTION_LISTENERS);
        if (listeners == null || !listeners.contains(listener))
            return;
        listeners.remove(listener);
    }

    public Set<ActionListener> getActionListeners() {
        Set<ActionListener> listeners = getController().get(LookAndFeel.COMPONENT_ACTION_LISTENERS);
        return listeners == null ? new HashSet<>() : new LinkedHashSet<>(listeners);
    }

    public synchronized void addComponentListener(ComponentListener listener) {
        Set<ComponentListener> listeners = getController().get(LookAndFeel.COMPONENT_COMPONENT_LISTENERS);
        if (listeners == null) {
            listeners = new LinkedHashSet<>();
            getController().put(LookAndFeel.COMPONENT_COMPONENT_LISTENERS, listeners);
        }
        if (listeners.contains(listener))
            return;
        listeners.add(listener);
    }

    public synchronized void removeComponentListener(ComponentListener listener) {
        Set<ComponentListener> listeners = getController().get(LookAndFeel.COMPONENT_COMPONENT_LISTENERS);
        if (listeners == null || !listeners.contains(listener))
            return;
        listeners.remove(listener);
    }

    public Set<ComponentListener> getComponentListeners() {
        Set<ComponentListener> listeners = getController().get(LookAndFeel.COMPONENT_COMPONENT_LISTENERS);
        return listeners == null ? new HashSet<>() : new LinkedHashSet<>(listeners);
    }

    public synchronized void addFocusListener(FocusListener listener) {
        Set<FocusListener> listeners = getController().get(LookAndFeel.COMPONENT_FOCUS_LISTENERS);
        if (listeners == null) {
            listeners = new LinkedHashSet<>();
            getController().put(LookAndFeel.COMPONENT_FOCUS_LISTENERS, listeners);
        }
        if (listeners.contains(listener))
            return;
        listeners.add(listener);
    }

    public synchronized void removeFocusListener(FocusListener listener) {
        Set<FocusListener> listeners = getController().get(LookAndFeel.COMPONENT_FOCUS_LISTENERS);
        if (listeners == null || !listeners.contains(listener))
            return;
        listeners.remove(listener);
    }

    public Set<FocusListener> getFocusListeners() {
        Set<FocusListener> listeners = getController().get(LookAndFeel.COMPONENT_FOCUS_LISTENERS);
        return listeners == null ? new HashSet<>() : new LinkedHashSet<>(listeners);
    }

    public synchronized void addKeyListener(KeyListener listener) {
        Set<KeyListener> listeners = getController().get(LookAndFeel.COMPONENT_KEY_LISTENERS);
        if (listeners == null) {
            listeners = new LinkedHashSet<>();
            getController().put(LookAndFeel.COMPONENT_KEY_LISTENERS, listeners);
        }
        if (listeners.contains(listener))
            return;
        listeners.add(listener);
    }

    public synchronized void removeKeyListener(KeyListener listener) {
        Set<KeyListener> listeners = getController().get(LookAndFeel.COMPONENT_KEY_LISTENERS);
        if (listeners == null || !listeners.contains(listener))
            return;
        listeners.remove(listener);
    }

    public Set<KeyListener> getKeyListeners() {
        Set<KeyListener> listeners = getController().get(LookAndFeel.COMPONENT_KEY_LISTENERS);
        return listeners == null ? new HashSet<>() : new LinkedHashSet<>(listeners);
    }

    public synchronized void addMouseListener(MouseListener listener) {
        Set<MouseListener> listeners = getController().get(LookAndFeel.COMPONENT_MOUSE_LISTENERS);
        if (listeners == null) {
            listeners = new LinkedHashSet<>();
            getController().put(LookAndFeel.COMPONENT_MOUSE_LISTENERS, listeners);
        }
        if (listeners.contains(listener))
            return;
        listeners.add(listener);
    }

    public synchronized void removeMouseListener(MouseListener listener) {
        Set<MouseListener> listeners = getController().get(LookAndFeel.COMPONENT_MOUSE_LISTENERS);
        if (listeners == null || !listeners.contains(listener))
            return;
        listeners.remove(listener);
    }

    public Set<MouseListener> getMouseListeners() {
        Set<MouseListener> listeners = getController().get(LookAndFeel.COMPONENT_MOUSE_LISTENERS);
        return listeners == null ? new HashSet<>() : new LinkedHashSet<>(listeners);
    }

    public synchronized void addMouseMotionListener(MouseMotionListener listener) {
        Set<MouseMotionListener> listeners = getController().get(LookAndFeel.COMPONENT_MOUSE_MOTION_LISTENERS);
        if (listeners == null) {
            listeners = new LinkedHashSet<>();
            getController().put(LookAndFeel.COMPONENT_MOUSE_MOTION_LISTENERS, listeners);
        }
        if (listeners.contains(listener))
            return;
        listeners.add(listener);
    }

    public synchronized void removeMouseMotionListener(MouseMotionListener listener) {
        Set<MouseMotionListener> listeners = getController().get(LookAndFeel.COMPONENT_MOUSE_MOTION_LISTENERS);
        if (listeners == null || !listeners.contains(listener))
            return;
        listeners.remove(listener);
    }

    public Set<MouseMotionListener> getMouseMotionListeners() {
        Set<MouseMotionListener> listeners = getController().get(LookAndFeel.COMPONENT_MOUSE_MOTION_LISTENERS);
        return listeners == null ? new HashSet<>() : new LinkedHashSet<>(listeners);
    }

    public synchronized void addMouseWheelListener(MouseWheelListener listener) {
        Set<MouseWheelListener> listeners = getController().get(LookAndFeel.COMPONENT_MOUSE_WHEEL_LISTENERS);
        if (listeners == null) {
            listeners = new LinkedHashSet<>();
            getController().put(LookAndFeel.COMPONENT_MOUSE_WHEEL_LISTENERS, listeners);
        }
        if (listeners.contains(listener))
            return;
        listeners.add(listener);
    }

    public synchronized void removeMouseWheelListener(MouseWheelListener listener) {
        Set<MouseWheelListener> listeners = getController().get(LookAndFeel.COMPONENT_MOUSE_WHEEL_LISTENERS);
        if (listeners == null || !listeners.contains(listener))
            return;
        listeners.remove(listener);
    }

    public Set<MouseWheelListener> getMouseWheelListeners() {
        Set<MouseWheelListener> listeners = getController().get(LookAndFeel.COMPONENT_MOUSE_WHEEL_LISTENERS);
        return listeners == null ? new HashSet<>() : new LinkedHashSet<>(listeners);
    }
}

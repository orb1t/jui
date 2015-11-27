package iitc.jui;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * RootPane
 *
 * @author Ian
 * @version 1.0
 */
public class RootPane extends Component implements Focusable {
    @Override
    public Component getClickFocus() {
        return getController().get(LookAndFeel.ROOT_PANE_CLICK_FOCUS);
    }

    @Override
    public void setClickFocus(Component component) {
        getController().put(LookAndFeel.ROOT_PANE_CLICK_FOCUS, component);
    }

    @Override
    public Component getDragFocus() {
        return getController().get(LookAndFeel.ROOT_PANE_DRAG_FOCUS);
    }

    @Override
    public void setDragFocus(Component component) {
        getController().put(LookAndFeel.ROOT_PANE_DRAG_FOCUS, component);
    }

    @Override
    public Component getHoverFocus() {
        return getController().get(LookAndFeel.ROOT_PANE_HOVER_FOCUS);
    }

    @Override
    public void setHoverFocus(Component component) {
        getController().put(LookAndFeel.ROOT_PANE_HOVER_FOCUS, component);
    }

    public Set<Frame> getFrames() {
        Set<Frame> frames = getController().get(LookAndFeel.ROOT_PANE_FRAMES);
        return frames == null ? new HashSet<>() : new LinkedHashSet<>(frames);
    }

    public synchronized void add(Frame frame) {
        if (frame == null)
            return;
        Set<Frame> frames = getController().get(LookAndFeel.ROOT_PANE_FRAMES);
        if (frames == null) {
            frames = new LinkedHashSet<>();
            getController().put(LookAndFeel.ROOT_PANE_FRAMES, frames);
        }
        if (frames.contains(frame))
            return;
        frame.setParent(this);
        frames.add(frame);
        pack();
    }

    public synchronized void remove(Frame frame) {
        if (frame == null)
            return;
        Set<Frame> frames = getController().get(LookAndFeel.ROOT_PANE_FRAMES);
        if (frames == null)
            return;
        if (!frames.contains(frame))
            return;
        frame.setParent(null);
        frames.remove(frame);
        pack();
    }

    @Override
    public boolean layout() {
        boolean changed = false;
        for (Frame frame : getFrames())
            if (frame.layout())
                changed = true;
        return super.layout() || changed;
    }

    @Override
    public void pack() {
        for (Frame frame : getFrames())
            frame.pack();
        super.pack();
    }
}

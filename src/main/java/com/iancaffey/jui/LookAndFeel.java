package com.iancaffey.jui;

import com.iancaffey.jui.lnf.DarkMatte;
import com.iancaffey.jui.util.Model;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.net.URL;

/**
 * LookAndFeel
 *
 * @author Ian Caffey
 * @since 1.0
 */
public class LookAndFeel extends Model {
    public static final Color TRANSPARENT = new Color(1.0f, 1.0f, 1.0f, 0f);
    public static final String BACKGROUND_COLOR = "background.color";
    public static final String BACKGROUND_IMAGE = "background.image";
    public static final String BACKGROUND_POSITION = "background.position";
    public static final String BACKGROUND_REPEAT = "background.repeat";
    public static final String BUTTON_PICTURE = "button.picture";
    public static final String BUTTON_PICTURE_DISABLED = "button.picture_disabled";
    public static final String BUTTON_PICTURE_ROLLOVER = "button.picture_hovered";
    public static final String BUTTON_PICTURE_PRESSED = "button.picture_pressed";
    public static final String BUTTON_PICTURE_POSITION = "button.picture_position";
    public static final String BUTTON_TEXT = "button.text";
    public static final String BUTTON_TEXT_GAP = "button.text_gap";
    public static final String BUTTON_TEXT_POSITION = "button.text_position";
    public static final String COMPONENT_ACTION_LISTENERS = "component.action.listeners";
    public static final String COMPONENT_BACKGROUND = "component.background";
    public static final String COMPONENT_BORDER = "component.border";
    public static final String COMPONENT_CACHED_IMAGE = "component.cached_image";
    public static final String COMPONENT_COMPONENT_LISTENERS = "component.component.listeners";
    public static final String COMPONENT_CURSOR = "component.cursor";
    public static final String COMPONENT_CURSOR_DISABLED = "component.cursor_disabled";
    public static final String COMPONENT_CURSOR_ROLLOVER = "component.cursor_rollover";
    public static final String COMPONENT_CURSOR_PRESSED = "component.cursor_pressed";
    public static final String COMPONENT_DISABLED = "component.disabled";
    public static final String COMPONENT_EVENT_DISPATCHER = "container.event_dispatcher";
    public static final String COMPONENT_FOCUS_LISTENERS = "component.focus.listeners";
    public static final String COMPONENT_HEIGHT = "component.height";
    public static final String COMPONENT_HOVERED = "component.hovered";
    public static final String COMPONENT_IMAGE_BUILDER = "component.image_builder";
    public static final String COMPONENT_INSETS = "component.insets";
    public static final String COMPONENT_KEY_LISTENERS = "component.key.listeners";
    public static final String COMPONENT_LAYOUT_MANAGER = "container.layout_manager";
    public static final String COMPONENT_LOOK_AND_FEEL = "component.look_and_feel";
    public static final String COMPONENT_OPACITY = "component.opacity";
    public static final String COMPONENT_OVERLAYS = "component.overlays";
    public static final String COMPONENT_MOUSE_LISTENERS = "component.mouse.listeners";
    public static final String COMPONENT_MOUSE_MOTION_LISTENERS = "component.mouse_motion.listeners";
    public static final String COMPONENT_MOUSE_POSITION = "component.mouse.position";
    public static final String COMPONENT_MOUSE_WHEEL_LISTENERS = "component.mouse_wheel.listeners";
    public static final String COMPONENT_PARENT = "component.parent";
    public static final String COMPONENT_PREFERRED_SIZE = "component.preferred_size";
    public static final String COMPONENT_PRESSED = "component.pressed";
    public static final String COMPONENT_WIDTH = "component.width";
    public static final String COMPONENT_X = "component.x";
    public static final String COMPONENT_Y = "component.y";
    public static final String CONTAINER_CLICK_FOCUS = "container.click_focus";
    public static final String CONTAINER_DRAG_FOCUS = "container.drag_focus";
    public static final String CONTAINER_HOVER_FOCUS = "container.hover_focus";
    public static final String CONTAINER_CHILDREN = "container.children";
    public static final String CONTAINER_CHILDREN_CONSTRAINTS = "container.children.constraints";
    public static final String CONTAINER_CHILDREN_REVERSE_CONSTRAINTS = "container.children.constraints.reverse";
    public static final String DROP_SHADOW_OFFSET_X = "drop_shadow.offset_x";
    public static final String DROP_SHADOW_OFFSET_Y = "drop_shadow.offset_y";
    public static final String DROP_SHADOW_COLOR = "drop_shadow.color";
    public static final String FRAME_CONTENT = "frame.content";
    public static final String FRAME_CLICK_FOCUS = "frame.click_focus";
    public static final String FRAME_DRAG_FOCUS = "frame.drag_focus";
    public static final String FRAME_HEADER = "frame.header";
    public static final String FRAME_HEADLESS = "frame.headless";
    public static final String FRAME_HOVER_FOCUS = "frame.hover_focus";
    public static final String FRAME_MAXIMIZED = "frame.maximized";
    public static final String FRAME_MAXIMIZED_CACHE_SIZE = "frame.maximized_cache_size";
    public static final String FRAME_RESIZABLE = "frame.resizable";
    public static final String HEADER_CLICK_FOCUS = "header.click_focus";
    public static final String HEADER_DRAG_FOCUS = "header.drag_focus";
    public static final String HEADER_HOVER_FOCUS = "header.hover_focus";
    public static final String HEADER_LEFT_BUTTONS = "header.left_buttons";
    public static final String HEADER_RIGHT_BUTTONS = "header.right_buttons";
    public static final String HEADER_TEXT = "header.text";
    public static final String IMAGE_CURSOR_IMAGE = "image_cursor.image";
    public static final String IMAGE_CURSOR_OFFSET_X = "image_cursor.offset_x";
    public static final String IMAGE_CURSOR_OFFSET_Y = "image_cursor.offset_y";
    public static final String LABEL_PICTURE = "label.picture";
    public static final String LABEL_PICTURE_POSITION = "label.picture_position";
    public static final String LABEL_TEXT = "label.text";
    public static final String LABEL_TEXT_GAP = "label.text_gap";
    public static final String LABEL_TEXT_POSITION = "label.text_position";
    public static final String PICTURE_IMAGE = "picture.image";
    public static final String PROGRESS_BAR_HORIZONTAL_SPACING = "progress_bar.horizontal_spacing";
    public static final String PROGRESS_BAR_MINIMUM_VALUE = "progress_bar.minimum_value";
    public static final String PROGRESS_BAR_MAXIMUM_VALUE = "progress_bar.maximum_value";
    public static final String PROGRESS_BAR_PROGRESSION_COLOR = "progress_bar.progression_color";
    public static final String PROGRESS_BAR_REMAINING_COLOR = "progress_bar.remaining_color";
    public static final String PROGRESS_BAR_TEXT = "progress_bar.text";
    public static final String PROGRESS_BAR_VALUE = "progress_bar.value";
    public static final String PROGRESS_BAR_VERTICAL_SPACING = "progress_bar.vertical_spacing";
    public static final String ROOT_PANE_CLICK_FOCUS = "root_pane.click_focus";
    public static final String ROOT_PANE_DRAG_FOCUS = "root_pane.drag_focus";
    public static final String ROOT_PANE_HOVER_FOCUS = "root_pane.hover_focus";
    public static final String ROOT_PANE_FRAMES = "root_pane.frames";
    public static final String TEXT_COLOR = "text.color";
    public static final String TEXT_DROP_SHADOW = "text.drop_shadow";
    public static final String TEXT_FONT = "text.font";
    public static final String TEXT_HOVERED = "text.hovered";
    public static final String TEXT_POSITION = "text.position";
    public static final String TEXT_PRESSED = "text.pressed";
    public static final String TEXT_PRESSED_COLOR = "text.pressed_color";
    public static final String TEXT_ROLLOVER_COLOR = "text.rollover_color";
    public static final String TEXT_TEXT = "text.text";
    public static final String UNIFORM_LAYOUT_ORIENTATION = "uniform_layout.orientation";
    public static final String UNIFORM_LAYOUT_HORIZONTAL_GAP = "uniform_layout.horizontal_gap";
    public static final String UNIFORM_LAYOUT_VERTICAL_GAP = "uniform_layout.vertical_gap";
    public static final String WALL_LAYOUT_HORIZONTAL_GAP = "wall_layout.horizontal_gap";
    public static final String WALL_LAYOUT_VERTICAL_GAP = "wall_layout.vertical_gap";
    private static final LookAndFeel backup = new DarkMatte();
    private static final Object lock = new Object();
    private static LookAndFeel defaults;

    public LookAndFeel() {
        put(Component.class, new ComponentModel());
        put(Container.class, new ContainerModel());
        put(Label.class, new LabelModel());
        put(Picture.class, new PictureModel());
        put(ProgressBar.class, new ProgressBarModel());
        put(Text.class, new TextModel());
        put(Button.class, new ButtonModel());
        put(Header.class, new HeaderModel());
        put(Frame.class, new FrameModel());
        put(RootPane.class, new RootPaneModel());
    }

    public static LookAndFeel getDefault() {
        synchronized (lock) {
            return defaults == null ? backup : defaults;
        }
    }

    public static void setDefault(LookAndFeel lookAndFeel) {
        synchronized (lock) {
            LookAndFeel.defaults = lookAndFeel;
        }
    }

    public static Model getDefault(Component component) {
        LookAndFeel lookAndFeel = getDefault();
        return lookAndFeel == null ? null : lookAndFeel.getModel(component);
    }

    public static Model getDefault(Class<? super Component> component) {
        LookAndFeel lookAndFeel = getDefault();
        return lookAndFeel == null ? null : lookAndFeel.getModel(component);
    }

    public Model getModel(Component component) {
        return component == null ? null : getModel((Class<? super Component>) component.getClass());
    }

    public Model getModel(Class<? super Component> component) {
        if (component == null)
            return null;
        Model model = null;
        while (component != null && model == null) {
            model = get(component);
            if (model == null)
                component = component.getSuperclass();
        }
        return model;
    }

    protected class BasicModel extends Model {
        public BasicModel() {
            put(LookAndFeel.COMPONENT_OPACITY, 1.0f);
            try {
                Cursor cursor = new ImageCursor(ImageIO.read(new URL("http://i.imgur.com/aa56XUc.png")), -2, -3);
                put(LookAndFeel.COMPONENT_CURSOR, cursor);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    protected class ComponentModel extends BasicModel {
        public ComponentModel() {
            put(LookAndFeel.COMPONENT_IMAGE_BUILDER, new ComponentImageBuilder());
        }
    }

    protected class ContainerModel extends BasicModel {
        public ContainerModel() {
            put(LookAndFeel.COMPONENT_IMAGE_BUILDER, new ContainerImageBuilder());
            put(LookAndFeel.COMPONENT_LAYOUT_MANAGER, new OpenLayout());
            put(LookAndFeel.COMPONENT_EVENT_DISPATCHER, new ContainerEventDispatcher());
        }
    }

    protected class PictureModel extends BasicModel {
        public PictureModel() {
            put(LookAndFeel.COMPONENT_IMAGE_BUILDER, new PictureImageBuilder());
            put(LookAndFeel.COMPONENT_LAYOUT_MANAGER, new PictureLayout());
        }
    }

    protected class TextModel extends BasicModel {
        public TextModel() {
            put(LookAndFeel.COMPONENT_IMAGE_BUILDER, new TextImageBuilder());
            put(LookAndFeel.COMPONENT_LAYOUT_MANAGER, new TextLayout());
            put(LookAndFeel.TEXT_FONT, new Font("System Regular", Font.PLAIN, 12));
        }
    }

    protected class LabelModel extends BasicModel {
        public LabelModel() {
            put(LookAndFeel.COMPONENT_IMAGE_BUILDER, new LabelImageBuilder());
            put(LookAndFeel.COMPONENT_LAYOUT_MANAGER, new LabelLayout());
        }
    }

    protected class ButtonModel extends BasicModel {
        public ButtonModel() {
            put(LookAndFeel.COMPONENT_IMAGE_BUILDER, new ButtonImageBuilder());
            put(LookAndFeel.COMPONENT_LAYOUT_MANAGER, new ButtonLayout());
            put(LookAndFeel.COMPONENT_INSETS, new Insets(5, 5, 5, 5));
            put(LookAndFeel.BUTTON_TEXT_GAP, 5);
            try {
                Cursor hovered = new ImageCursor(ImageIO.read(new URL("http://i.imgur.com/OcL5kp8.png")), -5, 0);
                Cursor pressed = new ImageCursor(ImageIO.read(new URL("http://i.imgur.com/IDpIUy2.png")), -5, 0);
                put(LookAndFeel.COMPONENT_CURSOR_ROLLOVER, hovered);
                put(LookAndFeel.COMPONENT_CURSOR_PRESSED, pressed);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    protected class ProgressBarModel extends BasicModel {
        public ProgressBarModel() {
            put(LookAndFeel.COMPONENT_IMAGE_BUILDER, new ProgressBarImageBuilder());
            put(LookAndFeel.COMPONENT_LAYOUT_MANAGER, new ProgressBarLayout());
            put(LookAndFeel.PROGRESS_BAR_HORIZONTAL_SPACING, 50);
            put(LookAndFeel.PROGRESS_BAR_VERTICAL_SPACING, 5);
        }
    }

    protected class HeaderModel extends BasicModel {
        public HeaderModel() {
            put(LookAndFeel.COMPONENT_IMAGE_BUILDER, new HeaderImageBuilder());
            put(LookAndFeel.COMPONENT_LAYOUT_MANAGER, new HeaderLayout());
            put(LookAndFeel.COMPONENT_EVENT_DISPATCHER, new HeaderEventDispatcher());
            put(LookAndFeel.COMPONENT_INSETS, new Insets(2, 2, 2, 2));
        }
    }

    protected class FrameModel extends BasicModel {
        public FrameModel() {
            put(LookAndFeel.COMPONENT_OPACITY, 0.0f);
            put(LookAndFeel.COMPONENT_IMAGE_BUILDER, new FrameImageBuilder());
            put(LookAndFeel.COMPONENT_LAYOUT_MANAGER, new FrameLayout());
            put(LookAndFeel.COMPONENT_EVENT_DISPATCHER, new FrameEventDispatcher());
            put(LookAndFeel.FRAME_RESIZABLE, true);
            put(LookAndFeel.FRAME_HEADLESS, false);
        }
    }

    protected class RootPaneModel extends BasicModel {
        public RootPaneModel() {
            put(LookAndFeel.COMPONENT_IMAGE_BUILDER, new RootPaneImageBuilder());
            put(LookAndFeel.COMPONENT_LAYOUT_MANAGER, new RootPaneLayout());
            put(LookAndFeel.COMPONENT_EVENT_DISPATCHER, new RootPaneEventDispatcher());
        }
    }
}

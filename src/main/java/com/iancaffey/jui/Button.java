package com.iancaffey.jui;

import com.iancaffey.jui.event.FocusEvent;
import com.iancaffey.jui.event.FocusListener;
import com.iancaffey.jui.event.MouseAdapter;
import com.iancaffey.jui.event.MouseEvent;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Button
 *
 * @author Ian Caffey
 * @since 1.0
 */
//TODO:Add dispatcher that sends events to the Text but does not consume the event
public class Button extends Component {
    public Button() {
        this(new Text());
    }

    public Button(String text) {
        this(new Text(text));
    }

    public Button(String text, Picture picture) {
        this(new Text(text), picture);
    }

    public Button(Text text) {
        this(text, null);
    }

    public Button(Picture picture) {
        this(new Text(), picture);
    }

    public Button(Text text, Picture picture) {
        setText(text);
        setPicture(picture);
        init();
    }

    private void init() {
        MouseAdapter adapter = new MouseAdapter() {
            @Override
            public void onMouseMoved(MouseEvent event) {
                super.onMouseMoved(event);
                Text text = getText();
                if (text != null)
                    text.setHovered(true);
            }

            @Override
            public void onMousePressed(MouseEvent event) {
                super.onMousePressed(event);
                Text text = getText();
                if (text != null)
                    text.setPressed(true);
            }

            @Override
            public void onMouseReleased(MouseEvent event) {
                super.onMouseReleased(event);
                Text text = getText();
                if (text != null)
                    text.setPressed(true);
            }

            @Override
            public void onMouseEntered(MouseEvent event) {
                super.onMouseEntered(event);
                Text text = getText();
                if (text != null)
                    text.setHovered(true);
            }

            @Override
            public void onMouseExited(MouseEvent event) {
                super.onMouseExited(event);
                Text text = getText();
                if (text != null)
                    text.setHovered(false);
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
                Text text = getText();
                if (text != null) {
                    text.setHovered(false);
                    text.setPressed(false);
                }
            }
        });
    }

    public int getTextGap() {
        return getController().getInt(LookAndFeel.BUTTON_TEXT_GAP, getBackupModel());
    }

    public void setTextGap(int gap) {
        int oldGap = getTextGap();
        if (oldGap == gap)
            return;
        getController().put(LookAndFeel.BUTTON_TEXT_GAP, gap);
        layout();
    }

    public Picture getPicture() {
        return getController().get(LookAndFeel.BUTTON_PICTURE);
    }

    public void setPicture(Picture picture) {
        Picture old = getPicture();
        if (old == picture)
            return;
        if (old != null)
            old.setParent(null);
        if (picture != null)
            picture.setParent(this);
        getController().put(LookAndFeel.BUTTON_PICTURE, picture);
        layout();
    }

    public Picture getDisabledPicture() {
        return getController().get(LookAndFeel.BUTTON_PICTURE_DISABLED);
    }

    public void setDisabledPicture(Picture picture) {
        Picture old = getDisabledPicture();
        if (old == picture)
            return;
        if (old != null)
            old.setParent(null);
        if (picture != null)
            picture.setParent(this);
        getController().put(LookAndFeel.BUTTON_PICTURE_DISABLED, picture);
        layout();
    }

    public Picture getRolloverPicture() {
        return getController().get(LookAndFeel.BUTTON_PICTURE_ROLLOVER);
    }

    public void setRolloverPicture(Picture picture) {
        Picture old = getRolloverPicture();
        if (old == picture)
            return;
        if (old != null)
            old.setParent(null);
        if (picture != null)
            picture.setParent(this);
        getController().put(LookAndFeel.BUTTON_PICTURE_ROLLOVER, picture);
        layout();
    }

    public Picture getPressedPicture() {
        return getController().get(LookAndFeel.BUTTON_PICTURE_PRESSED);
    }

    public void setPressedPicture(Picture picture) {
        Picture old = getPressedPicture();
        if (old == picture)
            return;
        if (old != null)
            old.setParent(null);
        if (picture != null)
            picture.setParent(this);
        getController().put(LookAndFeel.BUTTON_PICTURE_PRESSED, picture);
        layout();
    }

    public Text getText() {
        return getController().get(LookAndFeel.BUTTON_TEXT);
    }

    public void setText(Text text) {
        Text old = getText();
        if (old == text)
            return;
        if (old != null)
            old.setParent(null);
        if (text != null)
            text.setParent(this);
        getController().put(LookAndFeel.BUTTON_TEXT, text);
        layout();
    }

    public Set<Position> getPicturePosition() {
        Set<Position> position = getController().<Set<Position>>get(LookAndFeel.BUTTON_PICTURE_POSITION, getBackupModel());
        return position == null ? new HashSet<>() : new HashSet<>(position);
    }

    public void setPicturePosition(Position... position) {
        Set<Position> positionSet = getController().get(LookAndFeel.BUTTON_PICTURE_POSITION);
        if (positionSet == null) {
            positionSet = new HashSet<>();
            getController().put(LookAndFeel.BUTTON_PICTURE_POSITION, positionSet);
        }
        positionSet.clear();
        if (position != null)
            Collections.addAll(positionSet, position);
        layout(true);
    }

    public void addPicturePosition(Position position) {
        if (position == null)
            return;
        Set<Position> positionSet = getController().get(LookAndFeel.BUTTON_PICTURE_POSITION);
        if (positionSet == null) {
            positionSet = new HashSet<>();
            getController().put(LookAndFeel.BUTTON_PICTURE_POSITION, positionSet);
        }
        if (positionSet.contains(position))
            return;
        positionSet.add(position);
    }

    public void removePicturePosition(Position position) {
        if (position == null)
            return;
        Set<Position> positionSet = getController().get(LookAndFeel.BUTTON_PICTURE_POSITION);
        if (positionSet == null || !positionSet.contains(position))
            return;
        positionSet.remove(position);
    }

    public Set<Position> getTextPosition() {
        Set<Position> position = getController().<Set<Position>>get(LookAndFeel.BUTTON_TEXT_POSITION, getBackupModel());
        return position == null ? new HashSet<>() : new HashSet<>(position);
    }

    public void setTextPosition(Position... position) {
        Set<Position> positionSet = getController().get(LookAndFeel.BUTTON_TEXT_POSITION);
        if (positionSet == null) {
            positionSet = new HashSet<>();
            getController().put(LookAndFeel.BUTTON_TEXT_POSITION, positionSet);
        }
        positionSet.clear();
        if (position != null)
            Collections.addAll(positionSet, position);
        layout(true);
    }

    public void addTextPosition(Position position) {
        if (position == null)
            return;
        Set<Position> positionSet = getController().get(LookAndFeel.BUTTON_TEXT_POSITION);
        if (positionSet == null) {
            positionSet = new HashSet<>();
            getController().put(LookAndFeel.BUTTON_TEXT_POSITION, positionSet);
        }
        if (positionSet.contains(position))
            return;
        positionSet.add(position);
    }

    public void removeTextPosition(Position position) {
        if (position == null)
            return;
        Set<Position> positionSet = getController().get(LookAndFeel.BUTTON_TEXT_POSITION);
        if (positionSet == null || !positionSet.contains(position))
            return;
        positionSet.remove(position);
    }

    @Override
    public boolean layout() {
        boolean changed = false;
        Picture picture = isDisabled() ? getDisabledPicture() : isPressed() ? getPressedPicture() : isHovered() ? getRolloverPicture() : null;
        if (picture == null)
            picture = getPicture();
        if (picture != null && picture.layout())
            changed = true;
        Text text = getText();
        if (text != null && text.layout())
            changed = true;
        return super.layout() || changed;
    }

    @Override
    public void pack() {
        Picture picture = isDisabled() ? getDisabledPicture() : isPressed() ? getPressedPicture() : isHovered() ? getRolloverPicture() : null;
        if (picture == null)
            picture = getPicture();
        if (picture != null)
            picture.pack();
        Text text = getText();
        if (text != null)
            text.pack();
        super.pack();
    }
}

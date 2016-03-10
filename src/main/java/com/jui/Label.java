package com.jui;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Label
 *
 * @author Ian Caffey
 * @since 1.0
 */
//TODO:Add dispatcher that sends events to the Text but does not consume the event
public class Label extends Component {
    public Label() {
        this(new Text());
    }

    public Label(String text) {
        this(new Text(text));
    }

    public Label(Text text) {
        this(text, null);
    }

    public Label(Picture picture) {
        this(new Text(), picture);
    }

    public Label(String text, Picture picture) {
        this(new Text(text), picture);
    }

    public Label(Text text, Picture picture) {
        setText(text);
        setPicture(picture);
    }

    public int getTextGap() {
        return getController().getInt(LookAndFeel.LABEL_TEXT_GAP);
    }

    public void setTextGap(int gap) {
        int oldGap = getTextGap();
        if (oldGap == gap)
            return;
        getController().put(LookAndFeel.LABEL_TEXT_GAP, gap);
        layout(true);
    }

    public Picture getPicture() {
        return getController().get(LookAndFeel.LABEL_PICTURE);
    }

    public void setPicture(Picture picture) {
        Picture old = getPicture();
        if (old == picture)
            return;
        if (old != null)
            old.setParent(null);
        if (picture != null)
            picture.setParent(this);
        getController().put(LookAndFeel.LABEL_PICTURE, picture);
        layout(true);
    }

    public Text getText() {
        return getController().get(LookAndFeel.LABEL_TEXT);
    }

    public void setText(Text text) {
        Text old = getText();
        if (old == text)
            return;
        if (old != null)
            old.setParent(null);
        if (text != null)
            text.setParent(this);
        getController().put(LookAndFeel.LABEL_TEXT, text);
        layout();
    }

    public Set<Position> getPicturePosition() {
        Set<Position> position = getController().get(LookAndFeel.LABEL_PICTURE_POSITION);
        return position == null ? new HashSet<>() : new HashSet<>(position);
    }

    public void setPicturePosition(Position... position) {
        Set<Position> positionSet = getController().get(LookAndFeel.LABEL_PICTURE_POSITION);
        if (positionSet == null) {
            positionSet = new HashSet<>();
            getController().put(LookAndFeel.LABEL_PICTURE_POSITION, positionSet);
        }
        positionSet.clear();
        if (position != null)
            Collections.addAll(positionSet, position);
        layout(true);
    }

    public void addPicturePosition(Position position) {
        if (position == null)
            return;
        Set<Position> positionSet = getController().get(LookAndFeel.LABEL_PICTURE_POSITION);
        if (positionSet == null) {
            positionSet = new HashSet<>();
            getController().put(LookAndFeel.LABEL_PICTURE_POSITION, positionSet);
        }
        if (positionSet.contains(position))
            return;
        positionSet.add(position);
        layout(true);
    }

    public void removePicturePosition(Position position) {
        if (position == null)
            return;
        Set<Position> positionSet = getController().get(LookAndFeel.LABEL_PICTURE_POSITION);
        if (positionSet == null || !positionSet.contains(position))
            return;
        positionSet.remove(position);
        layout(true);
    }

    public Set<Position> getTextPosition() {
        Set<Position> position = getController().get(LookAndFeel.LABEL_TEXT_POSITION);
        return position == null ? new HashSet<>() : new HashSet<>(position);
    }

    public void setTextPosition(Position... position) {
        Set<Position> positionSet = getController().get(LookAndFeel.LABEL_TEXT_POSITION);
        if (positionSet == null) {
            positionSet = new HashSet<>();
            getController().put(LookAndFeel.LABEL_TEXT_POSITION, positionSet);
        }
        positionSet.clear();
        if (position != null)
            Collections.addAll(positionSet, position);
        layout(true);
    }

    public void addTextPosition(Position position) {
        if (position == null)
            return;
        Set<Position> positionSet = getController().get(LookAndFeel.LABEL_TEXT_POSITION);
        if (positionSet == null) {
            positionSet = new HashSet<>();
            getController().put(LookAndFeel.LABEL_TEXT_POSITION, positionSet);
        }
        if (positionSet.contains(position))
            return;
        positionSet.add(position);
        layout(true);
    }

    public void removeTextPosition(Position position) {
        if (position == null)
            return;
        Set<Position> positionSet = getController().get(LookAndFeel.LABEL_TEXT_POSITION);
        if (positionSet == null || !positionSet.contains(position))
            return;
        positionSet.remove(position);
        layout(true);
    }

    @Override
    public boolean layout() {
        boolean changed = false;
        Picture picture = getPicture();
        if (picture != null && picture.layout())
            changed = true;
        Text text = getText();
        if (text != null && text.layout())
            changed = true;
        return super.layout() || changed;
    }

    @Override
    public void pack() {
        Picture picture = getPicture();
        if (picture != null)
            picture.pack();
        Text text = getText();
        if (text != null)
            text.pack();
        super.pack();
    }
}

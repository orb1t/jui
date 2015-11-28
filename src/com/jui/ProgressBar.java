package com.jui;

import java.awt.*;

/**
 * ProgressBar
 *
 * @author Ian
 * @version 1.0
 */
public class ProgressBar extends com.jui.Component {
    public ProgressBar() {
        this(0, 100);
    }

    public ProgressBar(double min, double max) {
        this(min, max, Math.min(min, max));
    }

    public ProgressBar(double min, double max, double value) {
        setMinimum(Math.min(min, max));
        setMaximum(Math.max(min, max));
        setValue(value);
    }

    public double getMinimum() {
        return getController().getDouble(LookAndFeel.PROGRESS_BAR_MINIMUM_VALUE);
    }

    public void setMinimum(double value) {
        if (getMinimum() == value)
            return;
        getController().put(LookAndFeel.PROGRESS_BAR_MINIMUM_VALUE, value);
        Text text = getText();
        if (text == null) {
            setText(new Text(String.valueOf((int) Math.round(getProgression() * 100.0d))));
            return;
        }
        text.setText(String.valueOf((int) Math.round(getProgression() * 100.0d)));
        text.pack();
        layout();
        repaint();
    }

    public double getValue() {
        return getController().getDouble(LookAndFeel.PROGRESS_BAR_VALUE);
    }

    public void setValue(double value) {
        if (getValue() == value)
            return;
        getController().put(LookAndFeel.PROGRESS_BAR_VALUE, value);
        Text text = getText();
        if (text == null) {
            setText(new Text(String.valueOf((int) Math.round(getProgression() * 100.0d))));
            return;
        }
        text.setText(String.valueOf((int) Math.round(getProgression() * 100.0d)));
        text.pack();
        layout();
        repaint();
    }

    public double getMaximum() {
        return getController().getDouble(LookAndFeel.PROGRESS_BAR_MAXIMUM_VALUE);
    }

    public void setMaximum(double value) {
        if (getMaximum() == value)
            return;
        getController().put(LookAndFeel.PROGRESS_BAR_MAXIMUM_VALUE, value);
        Text text = getText();
        if (text == null) {
            setText(new Text(String.valueOf((int) Math.round(getProgression() * 100.0d))));
            return;
        }
        text.setText(String.valueOf((int) Math.round(getProgression() * 100.0d)));
        text.pack();
        layout();
        repaint();
    }

    public double getProgression() {
        double min = getMinimum();
        return (getValue() - min) / (getMaximum() - min);
    }

    public Text getText() {
        return getController().get(LookAndFeel.PROGRESS_BAR_TEXT);
    }

    public void setText(Text text) {
        Text old = getText();
        if (old == text)
            return;
        text.setParent(this);
        getController().put(LookAndFeel.PROGRESS_BAR_TEXT, text);
        repaint();
    }

    public int getHorizontalSpacing() {
        return getController().getInt(LookAndFeel.PROGRESS_BAR_HORIZONTAL_SPACING, getBackupModel());
    }

    public void setHorizontalSpacing(int spacing) {
        getController().put(LookAndFeel.PROGRESS_BAR_HORIZONTAL_SPACING, spacing);
    }

    public int getVerticalSpacing() {
        return getController().getInt(LookAndFeel.PROGRESS_BAR_VERTICAL_SPACING, getBackupModel());
    }

    public void setVerticalSpacing(int spacing) {
        getController().put(LookAndFeel.PROGRESS_BAR_VERTICAL_SPACING, spacing);
    }

    public Color getProgressionColor() {
        return getController().<Color>get(LookAndFeel.PROGRESS_BAR_PROGRESSION_COLOR, getBackupModel());
    }

    public void setProgressionColor(Color color) {
        getController().put(LookAndFeel.PROGRESS_BAR_PROGRESSION_COLOR, color);
    }

    public Color getRemainingColor() {
        return getController().<Color>get(LookAndFeel.PROGRESS_BAR_REMAINING_COLOR, getBackupModel());
    }

    public void setRemainingColor(Color color) {
        getController().put(LookAndFeel.PROGRESS_BAR_REMAINING_COLOR, color);
    }

    @Override
    public boolean layout() {
        boolean changed = false;
        Text text = getText();
        if (text != null && text.layout())
            changed = true;
        return super.layout() || changed;
    }

    @Override
    public void pack() {
        Text text = getText();
        if (text != null)
            text.pack();
        super.pack();
    }
}

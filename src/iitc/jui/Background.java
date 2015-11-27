package iitc.jui;

import iitc.util.Model;

import java.awt.*;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Background
 *
 * @author Ian
 * @version 1.0
 */
public class Background implements Overlay {
    private final Model model = new Model();

    public Background() {

    }

    public Background(Color color) {
        setColor(color);
    }

    public Background(Color color, Position... position) {
        setColor(color);
        setPosition(position);
    }

    public Background(Color color, Orientation... orientation) {
        setColor(color);
        setRepeat(orientation);
    }

    public Background(Color color, Orientation[] orientation, Position... position) {
        setColor(color);
        setRepeat(orientation);
        setPosition(position);
    }

    public Background(Color color, Image image) {
        setColor(color);
        setImage(image);
    }

    public Background(Color color, Image image, Position... position) {
        setColor(color);
        setImage(image);
        setPosition(position);
    }

    public Background(Color color, Image image, Orientation... orientation) {
        setColor(color);
        setImage(image);
        setRepeat(orientation);
    }

    public Background(Color color, Image image, Orientation[] orientation, Position... position) {
        setColor(color);
        setImage(image);
        setRepeat(orientation);
        setPosition(position);
    }

    public Image getImage() {
        return model.getImage(LookAndFeel.BACKGROUND_IMAGE);
    }

    public void setImage(Image image) {
        model.put(LookAndFeel.BACKGROUND_IMAGE, image);
    }

    public Color getColor() {
        return model.getColor(LookAndFeel.BACKGROUND_COLOR);
    }

    public void setColor(Color color) {
        model.put(LookAndFeel.BACKGROUND_COLOR, color);
    }

    public Set<Orientation> getRepeat() {
        Set<Orientation> orientation = model.get(LookAndFeel.BACKGROUND_REPEAT);
        return orientation == null ? new HashSet<>() : new HashSet<>(orientation);
    }

    public void setRepeat(Orientation... orientation) {
        if (orientation == null)
            return;
        Set<Orientation> orientationSet = model.get(LookAndFeel.BACKGROUND_REPEAT);
        if (orientationSet == null) {
            orientationSet = new HashSet<>();
            model.put(LookAndFeel.BACKGROUND_REPEAT, orientationSet);
        }
        orientationSet.clear();
        Collections.addAll(orientationSet, orientation);
    }

    public void addRepeat(Orientation orientation) {
        if (orientation == null)
            return;
        Set<Orientation> orientationSet = model.get(LookAndFeel.BACKGROUND_REPEAT);
        if (orientationSet == null) {
            orientationSet = new HashSet<>();
            model.put(LookAndFeel.BACKGROUND_REPEAT, orientationSet);
        }
        orientationSet.add(orientation);
    }

    public void removeRepeat(Orientation orientation) {
        if (orientation == null)
            return;
        Set<Orientation> orientationSet = model.get(LookAndFeel.BACKGROUND_REPEAT);
        if (orientationSet == null || !orientationSet.contains(orientation))
            return;
        orientationSet.remove(orientation);
    }

    public Set<Position> getPosition() {
        Set<Position> position = model.get(LookAndFeel.BACKGROUND_POSITION);
        return position == null ? new HashSet<>() : new HashSet<>(position);
    }

    public void setPosition(Position... position) {
        if (position == null)
            return;
        Set<Position> positionSet = model.get(LookAndFeel.BACKGROUND_POSITION);
        if (positionSet == null) {
            positionSet = new HashSet<>();
            model.put(LookAndFeel.BACKGROUND_POSITION, positionSet);
        }
        positionSet.clear();
        Collections.addAll(positionSet, position);
    }

    public void addPosition(Position position) {
        if (position == null)
            return;
        Set<Position> positionSet = model.get(LookAndFeel.BACKGROUND_POSITION);
        if (positionSet == null) {
            positionSet = new HashSet<>();
            model.put(LookAndFeel.BACKGROUND_POSITION, positionSet);
        }
        if (positionSet.contains(position))
            return;
        positionSet.add(position);
    }

    public void removePosition(Position position) {
        if (position == null)
            return;
        Set<Position> positionSet = model.get(LookAndFeel.BACKGROUND_POSITION);
        if (positionSet == null || !positionSet.contains(position))
            return;
        positionSet.remove(position);
    }

    @Override
    public void render(Component component, Graphics graphics, int x, int y, int width, int height) {
        Color bg = getColor();
        if (bg != null) {
            Color color = graphics.getColor();
            graphics.setColor(bg);
            graphics.fillRect(x, y, width, height);
            graphics.setColor(color);
        }
        Image image = getImage();
        if (image == null)
            return;
        int dx = 0;
        int dy = 0;
        int imageWidth = image.getWidth(null);
        int imageHeight = image.getHeight(null);
        Set<Position> position = getPosition();
        if (position != null) {
            if (position.contains(Position.LEFT)) {
                dx = 0;
            } else if (position.contains(Position.CENTER)) {
                dx = (int) Math.round((width - imageWidth) / 2.0d);
            } else if (position.contains(Position.RIGHT)) {
                dx = width - imageWidth;
            }
            if (position.contains(Position.TOP)) {
                dy = 0;
            } else if (position.contains(Position.CENTER)) {
                dy = (int) Math.round((height - imageHeight) / 2.0d);
            } else if (position.contains(Position.BOTTOM)) {
                dy = height - imageHeight;
            }
        }
        Set<Orientation> orientationSet = getRepeat();
        boolean orientationHorizontal = orientationSet != null && orientationSet.contains(Orientation.HORIZONTAL);
        boolean orientationVertical = orientationSet != null && orientationSet.contains(Orientation.VERTICAL);
        while (dx < width) {
            int ddy = dy;
            graphics.drawImage(image, x + dx, y + dy, null);
            ddy += imageHeight;
            if (orientationVertical) {
                while (ddy < height) {
                    graphics.drawImage(image, x + dx, y + ddy, null);
                    ddy += imageHeight;
                }
            }
            if (!orientationHorizontal)
                break;
            dx += imageWidth;
        }
    }
}

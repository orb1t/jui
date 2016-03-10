package com.iancaffey.jui.lnf;

import com.iancaffey.jui.*;
import com.iancaffey.jui.Button;
import com.iancaffey.jui.Container;
import com.iancaffey.jui.Frame;
import com.iancaffey.jui.util.Model;

import java.awt.*;

/**
 * DarkMatte
 *
 * @author Ian Caffey
 * @since 1.0
 */
//TODO:Complete
public class DarkMatte extends LookAndFeel {
    public DarkMatte() {
        Color backgroundDark = new Color(17, 17, 17);
        Color backgroundLight = new Color(34, 34, 34);
        Color border = new Color(51, 51, 51);
        Color text = new Color(205, 205, 205);
        Color rollover = new Color(125, 125, 125);
        Color pressed = new Color(45, 45, 45);
        getModel(Container.class).put(LookAndFeel.COMPONENT_BACKGROUND, new Background(backgroundDark));
        Model frameModel = getModel(Frame.class);
        frameModel.put(LookAndFeel.COMPONENT_BACKGROUND, new Background(backgroundLight));
        Model textModel = getModel(Text.class);
        textModel.put(LookAndFeel.TEXT_COLOR, text);
        textModel.put(LookAndFeel.TEXT_ROLLOVER_COLOR, rollover);
        textModel.put(LookAndFeel.TEXT_PRESSED_COLOR, pressed);
        Model buttonModel = getModel(Button.class);
        buttonModel.put(LookAndFeel.COMPONENT_BACKGROUND, new Background(backgroundDark));
        buttonModel.put(LookAndFeel.COMPONENT_BORDER, new LineBorder(1, border));
        Model progressBarModel = getModel(ProgressBar.class);
        progressBarModel.put(LookAndFeel.COMPONENT_BACKGROUND, new Background(backgroundDark));
        progressBarModel.put(LookAndFeel.COMPONENT_BORDER, new LineBorder(1, border));
        progressBarModel.put(LookAndFeel.PROGRESS_BAR_PROGRESSION_COLOR, backgroundLight);
    }
}

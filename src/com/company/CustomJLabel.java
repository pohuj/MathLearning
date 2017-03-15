package com.company;

import javax.swing.*;
import java.awt.*;

/**
 * Created by pin on 14.03.2017.
 */
public class CustomJLabel extends JLabel implements Scrollable {

    @Override
    public void setIcon(Icon icon) {
        super.setIcon(icon);
    }

    public CustomJLabel(ImageIcon image) {
        super(image);
    }

    @Override

    public Dimension getPreferredScrollableViewportSize() {
        return null;
    }

    @Override
    public int getScrollableUnitIncrement(Rectangle visibleRect, int orientation, int direction) {
        return 0;
    }

    @Override
    public int getScrollableBlockIncrement(Rectangle visibleRect, int orientation, int direction) {
        return 0;
    }

    @Override
    public boolean getScrollableTracksViewportWidth() {
        return false;
    }

    @Override
    public boolean getScrollableTracksViewportHeight() {
        return false;
    }
}

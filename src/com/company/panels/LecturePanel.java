package com.company.panels;

import javax.swing.*;
import java.awt.*;

/**
 * Created by pin on 03.02.2017.
 */
public class LecturePanel extends JPanel {
    @Override
    protected void printComponent(Graphics g) {
        super.printComponent(g);
        Dimension sSize = Toolkit.getDefaultToolkit ().getScreenSize ();
        g.drawImage(image, 0, 0,sSize.width,sSize.height, null);
    }

    private JLabel picture;
    private Image image;

    public LecturePanel(Image picture) {

        image = picture;
       // this.picture.setIcon(new ImageIcon(picture));
        //setLayout(new BorderLayout());


       // add(this.picture, BorderLayout.CENTER);
        repaint();
    }

    public void setPicture(Image picture){
        image = picture;
        //this.picture.setIcon(new ImageIcon(picture));
        repaint();
    }


}

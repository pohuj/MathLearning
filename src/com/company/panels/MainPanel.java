package com.company.panels;

import com.company.PanelManager;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by pin on 04.02.2017.
 */
public class MainPanel extends JPanel {

    private Image background;
    private ArrayList<JButton> buttons;
    private PanelManager panelManager;


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Dimension sSize = Toolkit.getDefaultToolkit ().getScreenSize ();
        g.drawImage(background, 0, 0,sSize.width,sSize.height, null);
    }

    public MainPanel(int activeButtons, Color wordsColor, Color backColor) throws IOException {
        super();

        background = ImageIO.read(new File("wall.jpg"));
        GridBagLayout gbl = new GridBagLayout();
        setLayout(gbl);

        GridBagConstraints c =  new GridBagConstraints();

        c.anchor = GridBagConstraints.CENTER;
        c.fill   = GridBagConstraints.NONE;
        c.gridheight = 1;
        c.gridwidth  = 1;
        c.gridx = GridBagConstraints.RELATIVE;
        c.gridy = GridBagConstraints.RELATIVE;
        c.insets = new Insets(0, 0, 0, 0);
        c.ipadx = 0;
        c.ipady = 0;
        c.weightx = 1;
        c.weighty = 0.0;





        //setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        buttons = new ArrayList<>();

        buttons.add(new JButton("Chapter 1"));
        buttons.add(new JButton("Chapter 2"));
        buttons.add(new JButton("Chapter 3"));
        buttons.add(new JButton("Chapter 4"));

        for(int i = activeButtons; i < buttons.size(); i++){
            buttons.get(i).setEnabled(false);

        }

        for(int i = 0; i < buttons.size(); i++){
            //buttons.get(i).setSize(500, 200);
            buttons.get(i).setMargin(new Insets(100,100,100,100));

        }

       // setLayout(new BoxLayout(getRootPane(),BoxLayout.X_AXIS));
        gbl.setConstraints(buttons.get(0), c);
        gbl.setConstraints(buttons.get(1), c);
        gbl.setConstraints(buttons.get(2), c);
        gbl.setConstraints(buttons.get(3), c);
        add(buttons.get(0));
        add(buttons.get(1));
        add(buttons.get(2));
        add(buttons.get(3));


        setBackground(backColor);
        setVisible(true);
    }



}
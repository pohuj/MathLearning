package com.company;

import com.company.logic.AppModel;
import com.company.panels.MainPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * Created by pin on 03.02.2017.
 */
public class MainForm extends JFrame{

    private MainPanel mainPanel;
    private AppModel appModel;
    private int currentProgress;

    private PanelManager panelManager;


    private JLabel header;

    public Color getBackColor() {
        return backColor;
    }

    private Color backColor = Color.WHITE;

    public Color getWordsColor() {
        return wordsColor;
    }

    private Color wordsColor = new Color(100,170,242);

    MainForm() throws IOException {
        super("Learn math");
        ImageIcon img = new ImageIcon("D://Math/icon.png");
        setIconImage(img.getImage());
        appModel = AppModel.getInstance();




        header = new JLabel("Здравствуйте, "+ appModel.getName() + " !");
        header.setFont(new Font("Verdana",Font.PLAIN, 26));
        header.setForeground(wordsColor);
        header.setHorizontalAlignment(SwingConstants.CENTER);

        mainPanel = new MainPanel(this,appModel.getProgress() / 10 + 1,wordsColor, backColor);

        setLayout(new BorderLayout());

        //add(header, BorderLayout.NORTH);
        add(mainPanel, BorderLayout.CENTER);
        //panelManager = new PanelManager(this,2);
        //add(panelManager,BorderLayout.CENTER);
//        panelManager.setVisible(false);
        //add(next, BorderLayout.SOUTH);
        //setContentPane(mainPanel);

        Dimension sSize = Toolkit.getDefaultToolkit ().getScreenSize ();
        setSize (sSize);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //setResizable(false);
        setLocationRelativeTo(null);

        setVisible(true);
        setBackground(backColor);

        //setPanelManager(2);
    }

    public void setPanelManager(int numberOfBlock){
        remove(mainPanel);
        panelManager = new PanelManager(this,numberOfBlock);
        add(panelManager,BorderLayout.CENTER);
        panelManager.setVisible(true);
        validate();
        repaint();
    }

    public void setMainPanel(int number){
        remove(panelManager);
        mainPanel.setEnableButtons(number);
        add(mainPanel);
        validate();
        repaint();
    }

    public void mpvr(){
        mainPanel.vr();
    }

}

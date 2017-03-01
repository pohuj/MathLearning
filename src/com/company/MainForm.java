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

    private JPanel mainPanel;
    private AppModel appModel;
    private int currentProgress;
    private JButton next;

    private JLabel header;
    private Color backColor = Color.WHITE;
    private Color wordsColor = new Color(100,170,242);

    MainForm() throws IOException {
        super("Learn math");

        appModel = AppModel.getInstance();

        next = new JButton("next");
        next.setVisible(false);
        next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        header = new JLabel("Здравствуйте, "+ appModel.getName() + " !");
        header.setFont(new Font("Verdana",Font.PLAIN, 26));
        header.setForeground(wordsColor);
        header.setHorizontalAlignment(SwingConstants.CENTER);

        mainPanel = new MainPanel(appModel.getProgress() / 10 + 1,wordsColor, backColor);

        setLayout(new BorderLayout());

        add(header, BorderLayout.NORTH);
        add(mainPanel, BorderLayout.CENTER);
        add(new JButton("next"), BorderLayout.SOUTH);
        //setContentPane(mainPanel);

        Dimension sSize = Toolkit.getDefaultToolkit ().getScreenSize ();
        setSize (sSize);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setResizable(false);
        setLocationRelativeTo(null);

        setVisible(true);
    }
}

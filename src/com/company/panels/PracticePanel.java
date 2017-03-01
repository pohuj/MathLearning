package com.company.panels;

import javax.swing.*;

/**
 * Created by pin on 03.02.2017.
 */
public class PracticePanel extends JPanel {

    JLabel question;
    JRadioButton firstAnswer;
    JRadioButton secondAnswer;
    JRadioButton thirdAnswer;
    JRadioButton fourthAnswer;

    public PracticePanel() {
        super();

        question = new JLabel();
        firstAnswer = new JRadioButton();
        secondAnswer = new JRadioButton();
        thirdAnswer = new JRadioButton();
        fourthAnswer = new JRadioButton();



    }
}

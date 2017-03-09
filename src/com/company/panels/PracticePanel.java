package com.company.panels;

import com.company.PanelManager;
import com.company.logic.Test;

import javax.swing.*;

/**
 * Created by pin on 03.02.2017.
 */
public class PracticePanel extends JPanel {

    JLabel question;
    JButton firstAnswer;
    JButton secondAnswer;
    JButton thirdAnswer;
    JButton fourthAnswer;
    int rightAnswer = 1;
    private PanelManager panelManager;

    public PracticePanel(PanelManager panelManager,Test test) {
        super();
        this.panelManager = panelManager;

        question = new JLabel(test.question);
        firstAnswer = new JButton(test.answer1);
        secondAnswer = new JButton(test.answer2);
        thirdAnswer = new JButton(test.answer3);
        fourthAnswer = new JButton(test.answer4);

        ButtonGroup buttonGroup = new ButtonGroup();
        //buttonGroup.add(question);


        add(question);
        add(firstAnswer);
        add(secondAnswer);
        add(thirdAnswer);
        add(fourthAnswer);


    }

    public void setTest(Test test){
        question.setText(test.question);
        firstAnswer.setText(test.answer1);
        secondAnswer.setText(test.answer2);
        thirdAnswer.setText(test.answer3);
        fourthAnswer.setText(test.answer4);


    }

}

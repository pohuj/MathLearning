package com.company.panels;

import com.company.logic.Test;

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

    public PracticePanel(Test test) {
        super();

        question = new JLabel(test.question);
        firstAnswer = new JRadioButton(test.answer1);
        secondAnswer = new JRadioButton(test.answer2);
        thirdAnswer = new JRadioButton(test.answer3);
        fourthAnswer = new JRadioButton(test.answer4);

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

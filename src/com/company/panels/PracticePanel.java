package com.company.panels;

import com.company.CustomJButton;
import com.company.PanelManager;
import com.company.logic.Test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * Created by pin on 03.02.2017.
 */
public class PracticePanel extends JPanel {

    JLabel question;
    public ArrayList<CustomJButton> buttons;
    int rightAnswer = 1;
    private PanelManager panelManager;

    public PracticePanel(PanelManager panelManager,Test test) {
        super();
        this.panelManager = panelManager;
        rightAnswer = test.rightAnswer;
        question = new JLabel(test.question);
        question.setHorizontalAlignment(SwingConstants.CENTER);

        buttons = new ArrayList<>();
        buttons.add(new CustomJButton(test.answer1,test.rightAnswer,1, panelManager));
        buttons.add(new CustomJButton(test.answer2,test.rightAnswer,2, panelManager));
        buttons.add(new CustomJButton(test.answer3,test.rightAnswer,3, panelManager));
        buttons.add(new CustomJButton(test.answer4,test.rightAnswer,4, panelManager));


        add(question);
        for(int i = 0; i < 4; i++){
            add(buttons.get(i));
        }
        JButton refresh = new JButton("refresh");
        refresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setBackground(Color.RED);
                repaint();
                new SomeThing().run();
            }
        });
        add(refresh);
        setBackground(Color.WHITE);
    }

    public void setTest(Test test){

        question.setText(test.question);
        buttons.get(0).setTestInfo(test.answer1,test.rightAnswer,1);
        buttons.get(1).setTestInfo(test.answer2,test.rightAnswer,2);
        buttons.get(2).setTestInfo(test.answer3,test.rightAnswer,3);
        buttons.get(3).setTestInfo(test.answer4,test.rightAnswer,4);
    }

    @Override
    public void setVisible(boolean aFlag) {
        super.setVisible(aFlag);
        for(int i = 0; i < 4; i++){
            buttons.get(i).setStandardColor();
        }
    }

    class SomeThing			//Нечто, реализующее интерфейс Runnable
            implements Runnable		//(содержащее метод run())
    {
        public void run()		//Этот метод будет выполняться в побочном потоке
        {
            setBackground(Color.RED);
            repaint();
            panelManager.goToLectureBack();
            System.out.println("Привет из побочного потока!");
        }
    }

}

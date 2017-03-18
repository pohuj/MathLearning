package com.company.panels;

import com.company.CustomJButton;
import com.company.PanelManager;
import com.company.logic.Test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * Created by pin on 03.02.2017.
 */
public class PracticePanel extends JPanel implements ActionListener, PropertyChangeListener{

    JLabel question;
    public ArrayList<CustomJButton> buttons;
    int rightAnswer = 1;
    private PanelManager panelManager;
    private Font font;
    private Task task;


    public PracticePanel(PanelManager panelManager,Test test) {
        super();
        this.panelManager = panelManager;
        rightAnswer = test.rightAnswer;
        font = new Font("Verdana", Font.PLAIN, 50);
        question = new JLabel(test.question);
        question.setFont(font);
        question.setHorizontalAlignment(SwingConstants.CENTER);

        buttons = new ArrayList<>();
        buttons.add(new CustomJButton(test.answer1,test.rightAnswer,1));
        buttons.add(new CustomJButton(test.answer2,test.rightAnswer,2));
        buttons.add(new CustomJButton(test.answer3,test.rightAnswer,3));
        buttons.add(new CustomJButton(test.answer4,test.rightAnswer,4));


        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        //question.setBounds(10,0,1000,100);
        add(question);
        for(int i = 1; i <= 4; i++){
            add(buttons.get(i - 1));
            buttons.get(i - 1).setPreferredSize(new Dimension(300,100));
            buttons.get(i - 1).setActionCommand("button" + i);
            System.out.println(buttons.get(i - 1).getActionCommand());
            buttons.get(i - 1).addActionListener(this);
        }
        setBackground(Color.WHITE);
    }

    public void setTest(Test test){

        question.setText(test.question);
        buttons.get(0).setTestInfo(test.answer1,test.rightAnswer,1);
/*        buttons.get(0).setFont(font);
        buttons.get(1).setFont(font);
        buttons.get(2).setFont(font);
        buttons.get(3).setFont(font);*/

        buttons.get(1).setTestInfo(test.answer2,test.rightAnswer,2);
        buttons.get(2).setTestInfo(test.answer3,test.rightAnswer,3);
        buttons.get(3).setTestInfo(test.answer4,test.rightAnswer,4);
        rightAnswer = test.rightAnswer;
    }

    @Override
    public void setVisible(boolean aFlag) {
        super.setVisible(aFlag);
        for(int i = 0; i < 4; i++){
            buttons.get(i).setStandardColor();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("e = " + e.getActionCommand());
        for(int i = 1; i <= 4; i++){
            if(e.getActionCommand().equals("button" + i)){
                System.out.println("Нажата кнопка button" + i);
                task = new Task(i,rightAnswer);
                task.addPropertyChangeListener(this);
                task.execute();
            }
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        System.out.println("in catch");
        if ("progress" == evt.getPropertyName()) {
            System.out.println("catch progress");
            /*int progress = (Integer) evt.getNewValue();
            progressBar.setValue(progress);
            taskOutput.append(String.format(
                    "Completed %d%% of task.\n", task.getProgress()));
        }*/
            int progress = (Integer) evt.getNewValue();
                buttons.get(progress - 1).setPressed(true);
        }
    }

    class Task extends SwingWorker<Void, Void> {

        private int buttonNumber;
        private int rightanswer;

        public Task(int buttonNumber, int rightanswer) {
            this.buttonNumber = buttonNumber;
            this.rightanswer = rightanswer;
        }

        /*
                 * Main task. Executed in background thread.
                 */
        @Override
        public Void doInBackground() {
            setProgress(buttonNumber);
            try {
                System.out.println("spim");
                Thread.sleep(2000);
            } catch (InterruptedException ignore) {}

            return null;
        }

        /*
         * Executed in event dispatching thread
         */
        @Override
        public void done() {

            System.out.println(buttonNumber + " k " + rightAnswer);
            if(buttonNumber == rightanswer){
                panelManager.goToLectureForward();
            } else {
                panelManager.goToLectureBack();
            }
            //Toolkit.getDefaultToolkit().beep();
            //startButton.setEnabled(true);
            //setCursor(null); //turn off the wait cursor
            //taskOutput.append("Done!\n");
        }
    }

}

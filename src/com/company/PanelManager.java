package com.company;

import com.company.logic.AppModel;
import com.company.panels.LecturePanel;
import com.company.panels.PracticePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;

/**
 * Created by pin on 01.03.2017.
 */
public class PanelManager extends JPanel{
    private AppModel model;
    private MainForm mainForm;
    private LecturePanel lecturePanel;
    private JLabel lectureLabel;
    private PracticePanel practicePanel;
    private JProgressBar progressBar;
    private JButton next;
    private JScrollPane lectureJScrollPane;
    private CustomJLabel customJLabel;

    private ImageIcon currentLecture;

    private int wholeProgress = 0;
    private int currentProgress = 0;
    private int progressBarProgress = 0;
    private int blockOfWholeProgress;

    private boolean isLecture = true;
    private boolean changeProgressInFile = true;


    public PanelManager(MainForm mainForm, int numberOfPressedBlock/*0,1,2,3*/) {
        this.model = AppModel.getInstance();
        this.mainForm = mainForm;
        lectureLabel = new JLabel();
        currentLecture = new ImageIcon();


        wholeProgress = model.getProgress();
        blockOfWholeProgress = wholeProgress / 10;/*0,1,2,3*/

        if(numberOfPressedBlock < blockOfWholeProgress){
            changeProgressInFile = false;
            progressBarProgress = 0;
            currentProgress = numberOfPressedBlock * 10 ;
        } else {
            if(wholeProgress == 40){
                progressBarProgress = 0;
                currentProgress = 29;
                changeProgressInFile = false;
            } else {
                progressBarProgress = wholeProgress % 10;
                currentProgress = wholeProgress;
            }
        }

        customJLabel = new CustomJLabel(new ImageIcon(model.getLecture(currentProgress)));
        lectureJScrollPane = new JScrollPane(customJLabel);
        lectureJScrollPane.setBackground(Color.WHITE);
        //lectureJScrollPane.setForeground(Color.WHITE);
        lectureJScrollPane.getViewport().setOpaque(false);
        //lectureJScrollPane.repaint();
        //lectureJScrollPane.setAlignmentX(CENTER_ALIGNMENT);
        //lectureJScrollPane.setWheelScrollingEnabled(true);
//        lecturePanel = new LecturePanel(model.getLecture(currentProgress));
        practicePanel = new PracticePanel(this, model.getTest(currentProgress));

        currentLecture.setImage(model.getLecture(currentProgress));
        lectureLabel.setIcon(currentLecture);
        lectureLabel.setHorizontalAlignment(SwingConstants.CENTER);


        progressBar = new JProgressBar(0,10);
        progressBar.setStringPainted(true);
        progressBar.setValue(progressBarProgress);

        next = new JButton("next");
        next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //goToLectureForward(new CustomJButton("2",2,3,new PanelManager(mainForm,2)));
               // progressBar.
                incProgress();
                nextPanel();
                swapToggle();
            }
        });


        setLayout(new BorderLayout());

        add(progressBar, BorderLayout.NORTH);
        //add(lecturePanel, BorderLayout.CENTER);
        //add(lectureLabel, BorderLayout.CENTER);
        add(lectureJScrollPane, BorderLayout.CENTER);
        add(practicePanel, BorderLayout.BEFORE_LINE_BEGINS);
        add(next, BorderLayout.SOUTH);

        lectureLabel.setVisible(true);
        //lecturePanel.setVisible(true);
        practicePanel.setVisible(false);
        setBackground(mainForm.getBackColor());
        setVisible(true);

    }

    private void nextPanel() {
        if(isLecture){
            setPracticePanel();
        } else {
            setLecturePanel();
        }
    }

    private void setLecturePanel() {
        practicePanel.setTest(model.getTest(currentProgress));
        currentLecture.setImage(model.getLecture(currentProgress));
        lectureLabel.setIcon(currentLecture);

        lectureJScrollPane = new JScrollPane(new CustomJLabel(new ImageIcon(model.getLecture(currentProgress))));
        lectureJScrollPane.setBackground(Color.WHITE);
        lectureJScrollPane.setWheelScrollingEnabled(true);
        lectureJScrollPane.setAlignmentX(CENTER_ALIGNMENT);
        add(lectureJScrollPane,BorderLayout.CENTER);
        practicePanel.setVisible(false);
        lectureLabel.setVisible(true);
        validate();
        repaint();
//        lecturePanel.setVisible(true);
    }

    private void setPracticePanel() {
//        lecturePanel.setVisible(false);
        //lectureLabel.setVisible(false);
        remove(lectureJScrollPane);
        practicePanel.setVisible(true);
        validate();
        repaint();
    }

    private void swapToggle(){
        if (isLecture){
            isLecture = false;
        } else {
            isLecture = true;
        }
    }

    private void incProgress(){
        if (!isLecture){
            progressBarProgress++;
            progressBar.setValue(progressBarProgress);
            currentProgress++;

            if(changeProgressInFile){
                model.incrementProgress();
            }

            if(progressBarProgress == 10){
                mainForm.setMainPanel(model.getActiveBlocks() /*1,2,3,4*/);
            }
        }
    }

    public void goToLectureBack() {

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        setLecturePanel();
        swapToggle();
    }

    public void goToLectureForward(){



        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        incProgress();
        nextPanel();
        swapToggle();
    }

    public void vr(){
        validate();
        repaint();
        mainForm.mpvr();
        mainForm.validate();
        mainForm.repaint();
    }
}

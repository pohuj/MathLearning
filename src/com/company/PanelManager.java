package com.company;

import com.company.logic.AppModel;
import com.company.panels.LecturePanel;
import com.company.panels.PracticePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by pin on 01.03.2017.
 */
public class PanelManager extends JPanel{
    private AppModel model;
    private MainForm mainForm;
    private LecturePanel lecturePanel;
    private PracticePanel practicePanel;
    private JProgressBar progressBar;
    private JButton next;
    private int wholeProgress = 0;
    private int currentProgress = 0;
    private boolean isLecture = true;
    private int progress;
    private boolean changeProgressInFile = true;


    public PanelManager(MainForm mainForm, int numberOfBlock) {
        this.model = AppModel.getInstance();
        this.mainForm = mainForm;
        wholeProgress = model.getProgress();
        progress = wholeProgress % 10;

        if(numberOfBlock < progress){
            changeProgressInFile = false;
            currentProgress = numberOfBlock - 9;//sorry for magic number
        }
        currentProgress = wholeProgress + 1;

        lecturePanel = new LecturePanel(model.getLecture(currentProgress));
        practicePanel = new PracticePanel(model.getTest(currentProgress));

        progressBar = new JProgressBar(0,10);
        progressBar.setStringPainted(true);
        progressBar.setValue(progress);

        next = new JButton("next");
        next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nextPanel();
                swapToggle();

               // progressBar.
            }
        });


        setLayout(new BorderLayout());

        add(progressBar, BorderLayout.NORTH);
        add(lecturePanel, BorderLayout.CENTER);
        add(practicePanel, BorderLayout.CENTER);
        add(next, BorderLayout.SOUTH);

        practicePanel.setVisible(false);
    }

    private void nextPanel() {
        if(isLecture){
            setPracticePanel();
        } else {
            setLecturePanel();
        }
    }

    private void setLecturePanel() {
        practicePanel.setVisible(false);
        lecturePanel.setVisible(true);
    }

    private void setPracticePanel() {
        lecturePanel.setVisible(false);
        practicePanel.setVisible(true);
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
            progress++;

            progressBar.setValue(progress);
            if(changeProgressInFile){
                model.incrementProgress();
            }

            if(progress == 10){
                mainForm.setMainPanel(model.getProgress() / 10);
            }
        }
    }

}

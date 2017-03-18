package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Created by pin on 09.03.2017.
 */
public class CustomJButton extends JButton {

    private Color firstColor = Color.WHITE;
    private Color secondColor = new Color(100,170,242);
    private int rightAnswer;
    private int numberOfButton;

    public void setPressed(boolean pressed) {
        isPressed = pressed;
        validate();
        repaint();
    }

    private boolean isPressed = false;
    //private Timer timer;

    public CustomJButton(String text, int rightAnswer, int numberOfButton) {
        super(text);
        setContentAreaFilled(false);
        this.rightAnswer = rightAnswer;
        this.numberOfButton = numberOfButton;
        //setStandardColor();
        //setActionCommand("go");
        //addMouseListener(new MouseHandler());
        //setOpaque(false);
 /*       ActionListener animate = new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                validate();
                repaint();
            }
        };
        Timer timer = new Timer(50,animate);
        timer.start();*/
    }

    public void setTestInfo(String text, int rightAnswer, int numberOfButton){
        setText(text);
        this.rightAnswer = rightAnswer;
        this.numberOfButton = numberOfButton;

    }

    public void setWrongAnswerColor(){
        secondColor = Color.RED;
        setBackground(secondColor);
        setOpaque(true);
        validate();
        repaint();
    }

    public void setRightAnswerColor(){
        secondColor = Color.GREEN;
        setBackground(secondColor);
        setOpaque(true);
        validate();
        repaint();
    }

    public void setStandardColor(){
        /*secondColor = new Color(100,170,242,10);
        setBackground(secondColor);
        setOpaque(true);*/
        isPressed = false;
        validate();
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        System.out.println("o");
        if (isPressed) {
            if(rightAnswer == numberOfButton){
                g.setColor(Color.GREEN);
                g.drawRoundRect(0, 0, this.getWidth() - 1, this.getHeight() - 1, 30, 30);
                //need delay
                System.out.println("iG");
                //panelManager.goToLectureForward();
            } else {
                g.setColor(Color.RED);
                g.drawRoundRect(0, 0, this.getWidth() - 1, this.getHeight() - 1, 30, 30);
                //need delay
                System.out.println("iR");
               // panelManager.goToLectureBack();setOpaque(false);
            }
        } else{
            g.setColor(secondColor);
            g.drawRoundRect(0, 0, this.getWidth() - 1, this.getHeight() - 1, 30, 30);
        }

    }
}

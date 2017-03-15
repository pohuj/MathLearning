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
public class CustomJButton extends JButton implements ActionListener, PropertyChangeListener{

    private Color firstColor = Color.WHITE;
    private Color secondColor = new Color(100,170,242);
    private int rightAnswer;
    private int numberOfButton;
    private PanelManager panelManager;
    private Task task;
    private boolean isPressed = false;
    //private Timer timer;

    public CustomJButton(String text, int rightAnswer, int numberOfButton, PanelManager panelManager) {
        super(text);
        setContentAreaFilled(false);
        this.rightAnswer = rightAnswer;
        this.numberOfButton = numberOfButton;
        this.panelManager = panelManager;
        //setStandardColor();
        //setActionCommand("go");
        addActionListener(this);
        task = new Task();
        task.addPropertyChangeListener(this);
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

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("out");
            isPressed = true;
            repaint();
            SomeThing someThing = new SomeThing();
            someThing.run();
            //panelManager.goToLectureForward();
            //task.execute();
            System.out.println("in");

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("progress" == evt.getPropertyName()) {
            repaint();
            System.out.println("repaint");
        }
    }

    public class MouseHandler extends MouseAdapter /*implements PropertyChangeListener*/{

        @Override
        public void mouseEntered(MouseEvent e) {
            getModel().setRollover(true);
        }

        @Override
        public void mouseExited(MouseEvent e) {
            getModel().setRollover(false);
        }

        @Override
        public void mousePressed(MouseEvent e) {
            getModel().setArmed(true);
            getModel().setPressed(true);
            getModel().setActionCommand("go");
            System.out.println("click");
            task = new Task();
            //task.addPropertyChangeListener(this);
            task.execute();
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            getModel().setPressed(false);
            getModel().setArmed(false);
        }

/*        @Override
        public void propertyChange(PropertyChangeEvent evt) {
            if ("progress" == evt.getPropertyName()) {
                repaint();
                System.out.println("repaint");
            }
        }*/
    }

    class Task extends SwingWorker<Void, Void> {
        /*
         * Main task. Executed in background thread.
         */
        @Override
        public Void doInBackground() {
            Random random = new Random();
            int progress = 0;
            System.out.println("start");
            //Initialize progress property.
            setProgress(0);

            while (progress < 100) {
                //Make random progress.
                progress += random.nextInt(10);
                setProgress(Math.min(progress, 100));
            }
            return null;
        }

        /*
         * Executed in event dispatching thread
         */
        @Override
        public void done() {
            System.out.println("end");
            panelManager.goToLectureForward();
        }
    }

    class SomeThing			//Нечто, реализующее интерфейс Runnable
            implements Runnable		//(содержащее метод run())
    {
        public void run()		//Этот метод будет выполняться в побочном потоке
        {
            repaint();
            //setBackground(Color.BLACK);
            if(rightAnswer == numberOfButton){
                panelManager.goToLectureForward();
            } else {
                panelManager.goToLectureBack();
            }
            System.out.println("Привет из побочного потока!");
        }
    }

}

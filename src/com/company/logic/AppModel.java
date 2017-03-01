package com.company.logic;

import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;

/**
 * Created by pin on 03.02.2017.
 */
public class AppModel {

    private static AppModel instance;

    private ArrayList<BufferedImage> lectures;
    private ArrayList<Test> practices;
    private int progress;
    private String name;

    public AppModel() {

        lectures = new ArrayList<>();
        practices = new ArrayList<>();

        File data = new File("src/com/company/resources/data.txt");

        try {
            BufferedReader fin = new BufferedReader(new FileReader(data));
            progress = Integer.parseInt(fin.readLine());
            name = fin.readLine();
            int numberQuestions = Integer.parseInt(fin.readLine());
            for(int i = 0 ; i < numberQuestions; i++){
                practices.add(new Test(fin.readLine(),fin.readLine(),fin.readLine(),fin.readLine(),fin.readLine(),Integer.parseInt(fin.readLine())));
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();//no file exist
        } catch (IOException e) {
            e.printStackTrace();
        }



    }

    public static AppModel getInstance(){
        if(instance == null){
            instance = new AppModel();
        }
        return instance;
    }

    public String getName() {
        return name;
    }

    public int getProgress() {
        return progress;
    }
}

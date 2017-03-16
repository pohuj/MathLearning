package com.company.logic;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pin on 03.02.2017.
 */
public class AppModel {

    private static AppModel instance;

    private ArrayList<Image> lectures;
    private ArrayList<Test> practices;
    private int progress;
    private String name;
    //private String fileName = "src/com/company/resources/data.txt";
    private String fileName = "D://data.txt";
    private List<String> wholeFile = new ArrayList<>();

    public AppModel() {

        lectures = new ArrayList<>();
        practices = new ArrayList<>();

        File data = new File(fileName);

        try {

            wholeFile = Files.readAllLines(Paths.get(fileName), StandardCharsets.UTF_8);

            BufferedReader fin = new BufferedReader(new InputStreamReader(new FileInputStream(data),"UTF-8"));
            progress = Integer.parseInt(fin.readLine());
            name = fin.readLine();
            int numberQuestions = Integer.parseInt(fin.readLine());
            for(int i = 0 ; i < numberQuestions; i++){
                practices.add(new Test(fin.readLine(),fin.readLine(),fin.readLine(),fin.readLine(),fin.readLine(),Integer.parseInt(fin.readLine())));
            }

            Image buffer;

            for(int i = 1 ; i < numberQuestions + 1; i++){
                buffer = ImageIO.read(new File("D://lectures/lecture" + i +".png"));
                lectures.add(i - 1,buffer);
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

    public int getActiveBlocks() {
        if(progress != 40){
        return progress / 10 + 1;
        } else {
            return 4;
        }
    }

    public void incrementProgress(){
        progress++;
        wholeFile.set(0, String.valueOf(progress));
        try {
            Files.write(Paths.get(fileName), wholeFile, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public Image getLecture(int currentProgress) {
        return lectures.get(currentProgress);
    }

    public Test getTest(int currentProgress) {
        return practices.get(currentProgress);
    }
}

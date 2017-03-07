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

    private ArrayList<BufferedImage> lectures;
    private ArrayList<Test> practices;
    private int progress;
    private String name;
    private String fileName = "src/com/company/resources/data.txt";
    private List<String> wholeFile = new ArrayList<>();

    public AppModel() {

        lectures = new ArrayList<>();
        practices = new ArrayList<>();

        File data = new File(fileName);

        try {

            wholeFile = Files.readAllLines(Paths.get(fileName), StandardCharsets.ISO_8859_1);

            BufferedReader fin = new BufferedReader(new FileReader(data));
            progress = Integer.parseInt(fin.readLine());
            name = fin.readLine();
            int numberQuestions = Integer.parseInt(fin.readLine());
            for(int i = 0 ; i < numberQuestions; i++){
                practices.add(new Test(fin.readLine(),fin.readLine(),fin.readLine(),fin.readLine(),fin.readLine(),Integer.parseInt(fin.readLine())));
            }

            for(int i = 1 ; i < numberQuestions + 1; i++){
                lectures.add(ImageIO.read(new File("src/com/company/resources/images/lectures/lecture" + i +".png")));
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

    public void incrementProgress(){
        wholeFile.set(0, wholeFile.get(0).replace(String.valueOf(progress), "" + progress++));
        progress++;
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

package Controller;

import Model.Highscore;

import java.io.*;
import java.util.*;

/**
 * Creates and sorts a list of highscores that is displayed in the HighscoreView.
 *
 * @author Jennifer Krogh
 * @author Antonia Welzel
 * @author Emma Pettersson
 */
public class HighscoreController {
    //Player names -- hardcoded
    private String player1 = "Emma";
    private String player2 = "Antonia";
    private String player3 = "Jennifer";
    private String player4 = "Adam";
    private String player5 = "Jonathan";
    private String player6 = "Pettersson";
    private String player7 = "Welzel";
    private String player8 = "Krogh";
    private String player9 = "Rohdell";
    private String player10 = "Carbol";

    //Player scores -- hardcoded
    private int p1 = 1540;
    private int p2 = 4000;
    private int p3 = 250;
    private int p4 = 30;
    private int p5 = 2010;
    private int p6 = 300;
    private int p7 = 1110;
    private int p8 = 660;
    private int p9 = 9999;
    private int p10 = 1230;

    //Highscores -- hardcoded
    private Highscore h1 = new Highscore(p1, player1);
    private Highscore h2 = new Highscore(p2, player2);
    private Highscore h3 = new Highscore(p3, player3);
    private Highscore h4 = new Highscore(p4, player4);
    private Highscore h5 = new Highscore(p5, player5);
    private Highscore h6 = new Highscore(p6, player6);
    private Highscore h7 = new Highscore(p7, player7);
    private Highscore h8 = new Highscore(p8, player8);
    private Highscore h9 = new Highscore(p9, player9);
    private Highscore h10 = new Highscore(p10, player10);

    private List<Highscore> highscoreList = new ArrayList<Highscore>();
    private String highscoreDataPath;
    private String filename = "HighscoreData";

    /**
     * Class constructor.
     *
     * @param highscoreList
     *      List containing highscores.
     */
    public HighscoreController(List<Highscore> highscoreList) {
        this.highscoreList = highscoreList;
        setH(this.highscoreList); // Change later? this is used for testing with hardcoded values.
        sortList(this.highscoreList); // Change later? this is used for testing with hardcoded values.

        try{
            highscoreDataPath = HighscoreController.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
        } catch (Exception e){
            e.printStackTrace();
        }

        setHighscoreData();
        loadHighscore();
    }

    /**
     * Creates a file were highscores are saved.
     */
    private void createDataFile() {
        try {
            File file = new File(highscoreDataPath, filename);
            FileWriter output = new FileWriter(file);
            BufferedWriter writer = new BufferedWriter(output);

            writer.write("No highscore:0");
            writer.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Loads the highscores from the file.
     */
    private void loadHighscore() {
        String line;
        int playerScore;
        String [] storeSplit;
        String playerName;
        Highscore newHigh;

        highscoreList.clear();

        try {
            File file = new File(highscoreDataPath, filename);

            if (!file.isFile()) {
                createDataFile();
            }

            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));

            if (!reader.ready()) {
                throw new IOException();
            }

           while ((line = reader.readLine()) != null) {
               storeSplit = line.split(":");
               playerScore = Integer.parseInt(storeSplit[1]);
               playerName = storeSplit[0];
               newHigh = new Highscore(playerScore, playerName);
               highscoreList.add(newHigh);
           }

           reader.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Writes the highscore list to the file.
     */
    private void setHighscoreData (){
        FileWriter output = null;

        try {
            File file = new File(highscoreDataPath, filename);
            output = new FileWriter(file);
            BufferedWriter writer = new BufferedWriter(output);

            for (Highscore highscore : highscoreList) {
                writer.write(highscore.getPlayer() + ":" + highscore.getHighscore() + "\n");
            }

            writer.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Method to add a new Highscore to the list, if the score is greater than all other values on the list.
     *
     * @param newScore
     *      The score that possibly will be added to the list.
     * @param highscoreList
     *      The list to which the Highscore is added.
     */
    // This method should be called when a gamerun has ended!!
    public void addToScoreList(Highscore newScore, List<Highscore> highscoreList) {
        if (highscoreList.size() < 10) {
            highscoreList.add(newScore);
        } else {
            for (Highscore oldScores : highscoreList) {
                if (newScore.getHighscore() > oldScores.getHighscore()) {
                    highscoreList.remove(oldScores);
                    highscoreList.add(newScore);
                    break;
                }
            }
        }
    }

    /**
     * Method to sort the list in order of size, where the highest score is listed first.
     *
     * @param highscoreList
     *      List that needs to be sorted, usually after a new Highscore was added
     */
    private void sortList(List<Highscore> highscoreList) {
        Comparator<Highscore> highscoreComparator = Comparator.comparingInt(Highscore::getHighscore);
        Comparator<Highscore> comparatorReversed = highscoreComparator.reversed();

        highscoreList.sort(comparatorReversed);
    }

    private void setH(List<Highscore> hList) {
        hList.add(h1);
        hList.add(h2);
        hList.add(h3);
        hList.add(h4);
        hList.add(h5);
        hList.add(h6);
        hList.add(h7);
        hList.add(h8);
        hList.add(h9);
        hList.add(h10);
    }
}
package Controller;

import Model.HighScore;

import java.io.*;
import java.util.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Creates and sorts a list of HighScores that is displayed in the HighScoreView.
 *
 * @author Jennifer Krogh
 * @author Antonia Welzel
 * @author Emma Pettersson
 * @version
 */
public class HighScoreController {
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

    //HighScores -- hardcoded
    private HighScore h1 = new HighScore(p1, player1);
    private HighScore h2 = new HighScore(p2, player2);
    private HighScore h3 = new HighScore(p3, player3);
    private HighScore h4 = new HighScore(p4, player4);
    private HighScore h5 = new HighScore(p5, player5);
    private HighScore h6 = new HighScore(p6, player6);
    private HighScore h7 = new HighScore(p7, player7);
    private HighScore h8 = new HighScore(p8, player8);
    private HighScore h9 = new HighScore(p9, player9);
    private HighScore h10 = new HighScore(p10, player10);

    private List<HighScore> highScoreList = new ArrayList<>();
    private String HighScoreDataPath;
    private String filename = "HighScoreData";

    /**
     * Class constructor.
     *
     * @param highScoreList List containing HighScores.
     */
    public HighScoreController(List<HighScore> highScoreList) {
        this.highScoreList = highScoreList;
        setH(this.highScoreList); // Change later? this is used for testing with hardcoded values.
        sortList(this.highScoreList); // Change later? this is used for testing with hardcoded values.

        try {
            HighScoreDataPath = HighScoreController.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
        } catch (Exception e) {
            e.printStackTrace();
        }

        loadHighScore();
    }

    /**
     * Creates a file were HighScores are saved.
     */
    private void createDataFile() {
        try {
            File file = new File(HighScoreDataPath, filename);
            FileWriter output = new FileWriter(file);
            BufferedWriter writer = new BufferedWriter(output);

            writer.write("No HighScore:0");
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads the HighScores from the file.
     */
    private void loadHighScore() {
        String line;
        int playerScore;
        String[] storeSplit;
        String playerName;
        HighScore newHigh;

        highScoreList.clear();

        try {
            File file = new File(HighScoreDataPath, filename);

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
                newHigh = new HighScore(playerScore, playerName);
                highScoreList.add(newHigh);
            }

            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Writes the HighScore list to the file.
     */
    private void setHighScoreData() {
        FileWriter output = null;

        try {
            File file = new File(HighScoreDataPath, filename);
            output = new FileWriter(file);
            BufferedWriter writer = new BufferedWriter(output);

            for (HighScore HighScore : highScoreList) {
                writer.write(HighScore.getPlayer() + ":" + HighScore.getHighScore() + "\n");
            }

            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Method to add a new HighScore to the list, if the score is greater than all other values on the list.
     *
     * @param newScore The score that possibly will be added to the list.
     */
    public void addToScoreList(HighScore newScore) {
        if (highScoreList.size() < 10) {
            highScoreList.add(newScore);
        } else {
            if (newScore.getHighScore() > highScoreList.get(9).getHighScore()) {
                highScoreList.remove(9);
                highScoreList.add(newScore);
            }
        }
        setHighScoreData();
    }

    /**
     * Method to sort the list in order of size, where the highest score is listed first.
     *
     * @param highScoreList List that needs to be sorted, usually after a new HighScore has been added.
     */
    private void sortList(List<HighScore> highScoreList){
        Comparator<HighScore> HighScoreComparator = Comparator.comparingInt(HighScore::getHighScore);
        Comparator<HighScore> comparatorReversed = HighScoreComparator.reversed();

        highScoreList.sort(comparatorReversed);
    }

    private void setH(List<HighScore> highScoreList) {
        highScoreList.add(h1);
        highScoreList.add(h2);
        highScoreList.add(h3);
        highScoreList.add(h4);
        highScoreList.add(h5);
        highScoreList.add(h6);
        highScoreList.add(h7);
        highScoreList.add(h8);
        highScoreList.add(h9);
        highScoreList.add(h10);
      }
}
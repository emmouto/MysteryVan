package Controller;

import Model.HighScore;

import java.io.*;
import java.util.*;

/**
 * Creates and sorts a list of HighScores that is displayed in the HighScoreView.
 *
 * @author Jennifer Krogh
 * @author Antonia Welzel
 * @author Emma Pettersson
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

    private List<HighScore> HighScoreList = new ArrayList<HighScore>();
    private String HighScoreDataPath;
    private String filename = "HighScoreData";

    /**
     * Class constructor.
     *
     * @param HighScoreList
     *      List containing HighScores.
     */
    public HighScoreController(List<HighScore> HighScoreList) {
        this.HighScoreList = HighScoreList;
        setH(this.HighScoreList); // Change later? this is used for testing with hardcoded values.
        sortList(this.HighScoreList); // Change later? this is used for testing with hardcoded values.

        try {
            HighScoreDataPath = HighScoreController.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
        } catch (Exception e){
            e.printStackTrace();
        }

        setHighScoreData();
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
        String [] storeSplit;
        String playerName;
        HighScore newHigh;

        HighScoreList.clear();

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
               HighScoreList.add(newHigh);
           }

           reader.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Writes the HighScore list to the file.
     */
    private void setHighScoreData (){
        FileWriter output = null;

        try {
            File file = new File(HighScoreDataPath, filename);
            output = new FileWriter(file);
            BufferedWriter writer = new BufferedWriter(output);

            for (HighScore HighScore : HighScoreList) {
                writer.write(HighScore.getPlayer() + ":" + HighScore.getHighScore() + "\n");
            }

            writer.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Method to add a new HighScore to the list, if the score is greater than all other values on the list.
     *
     * @param newScore
     *      The score that possibly will be added to the list.
     * @param HighScoreList
     *      The list to which the HighScore is added.
     */
    // This method should be called when a gamerun has ended!!
    public void addToScoreList(HighScore newScore, List<HighScore> HighScoreList) {
        if (HighScoreList.size() < 10) {
            HighScoreList.add(newScore);
        } else {
            for (HighScore oldScores : HighScoreList) {
                if (newScore.getHighScore() > oldScores.getHighScore()) {
                    HighScoreList.remove(oldScores);
                    HighScoreList.add(newScore);
                    break;
                }
            }
        }
    }

    /**
     * Method to sort the list in order of size, where the highest score is listed first.
     *
     * @param HighScoreList
     *      List that needs to be sorted, usually after a new HighScore was added
     */
    private void sortList(List<HighScore> HighScoreList) {
        Comparator<HighScore> HighScoreComparator = Comparator.comparingInt(HighScore::getHighScore);
        Comparator<HighScore> comparatorReversed = HighScoreComparator.reversed();

        HighScoreList.sort(comparatorReversed);
    }

    private void setH(List<HighScore> hList) {
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
package Controller;

import Model.HighScore;

import java.io.*;
import java.util.*;
import java.util.List;

/**
 * Creates and sorts a list of HighScores that is displayed in the HighScoreView.
 *
 * @author Jennifer Krogh
 * @version 0.1
 */
public class HighScoreController {
    public List<HighScore> highScoreList = new ArrayList<>();
    private String HighScoreDataPath;
    private String filename = "HighScoreData";

    /**
     * Class constructor.
     */
    public HighScoreController() {

        try {
            HighScoreDataPath = HighScoreController.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
        } catch (Exception ignored) { }

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
        } catch (Exception ignored) { }
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

        if(highScoreList != null) {
            highScoreList.clear();
        }

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
                if (!(newHigh.getPlayer().equals("No HighScore"))){
                    highScoreList.add(newHigh);
                }
            }

            reader.close();
        } catch (Exception ignored) { }
    }

    /**
     * Writes the HighScore list to the file.
     */
    private void setHighScoreData() {
        FileWriter output;

        try {
            File file = new File(HighScoreDataPath, filename);
            output = new FileWriter(file);
            BufferedWriter writer = new BufferedWriter(output);

            for (HighScore HighScore : highScoreList) {
                writer.write(HighScore.getPlayer() + ":" + HighScore.getHighScore() + "\n");
            }

            writer.close();
        } catch (Exception ignored) { }
    }

    /**
     * Method to add a new HighScore to the list, if the score is greater than all other values on the list.
     *
     * @param newScore the score that possibly will be added to the list.
     */
    void addToScoreList(HighScore newScore) {
        if (highScoreList.size() < 10) {
            highScoreList.add(newScore);
        } else {
            if (newScore.getHighScore() > highScoreList.get(9).getHighScore()) {
                highScoreList.remove(9);
                highScoreList.add(newScore);
            }
        }
        sortList(highScoreList);
        setHighScoreData();
        loadHighScore();
    }

    /**
     * Method to sort the list in order of size, where the highest score is listed first.
     *
     * @param highScoreList list that needs to be sorted, usually after a new HighScore has been added.
     */
    private void sortList(List<HighScore> highScoreList){
        Comparator<HighScore> HighScoreComparator = Comparator.comparingInt(HighScore::getHighScore);
        Comparator<HighScore> comparatorReversed = HighScoreComparator.reversed();

        highScoreList.sort(comparatorReversed);
    }
}
package Controller;

import Model.Highscore;

/**
 * Creates and sorts a list of highscores that is displayed in the HighscoreView.
 *
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

    private Highscore[] hh = new Highscore[10];

    // need this?, thought was to have one same array everywhere in the game
    private Highscore[] hsArr;

    /**
     * Class constructor.
     *
     * @param hsArr
     *      Array containing highscores.
     */
    public HighscoreController(Highscore[] hsArr) {
        this.hsArr = hsArr;
        setH(hsArr);
        organizeArr(hsArr);
    }

    /**
     * Method to add a new Highscore to the array, that is used
     * to displaying the highscores in the HighscoreView.
     *
     * @param hs
     *      The new Highscore to be added to the array
     * @param hArr
     *      The array to which the Highscore is added
     */
    public void addToScoreArray(Highscore hs, Highscore[] hArr) {     // ---this method needs to be used somewhere else, as for now highscore data is hardcoded
        for (int i = 0; i < 10; i++) {
            if (hArr[i] == null) {
                //if the index has no value, then place new highscore in it
                hArr[i] = hs;
            } else {
                //else replace the last value since it is the smallest
                // and only the ten highest scores are listed
                hArr[9] = hs;
            }
        }
    }

    /**
     * Method to sort the array in order of size,
     * where the highest score is listed first using bubble sort.
     *
     * @param hArr
     *      Array that needs to be sorted, usually after a new Highscore was added
     * @return
     *      Returns the new sorted array, which can be displayed in the HighscoreView
     */
    private Highscore[] organizeArr(Highscore[] hArr) {
        //maybe change sortingAlg if it is very slow
        boolean swapped;
        do {
            swapped = false;
            for (int i = 0; i < hArr.length; i++) {
                for (int j = 1; j < (hArr.length - i); j++) {
                    if (hArr[j - 1].getHighscore() < hArr[j].getHighscore()) {
                        swapped = true;
                        Highscore temp = hArr[j];
                        hArr[j] = hArr[j - 1];
                        hArr[j - 1] = temp;
                    }
                }
            }
        } while (swapped);

        return hArr;
    }

    private void setH(Highscore[] highhigh) {
        highhigh[0] = h1;
        highhigh[1] = h2;
        highhigh[2] = h3;
        highhigh[3] = h4;
        highhigh[4] = h5;
        highhigh[5] = h6;
        highhigh[6] = h7;
        highhigh[7] = h8;
        highhigh[8] = h9;
        highhigh[9] = h10;
    }
}
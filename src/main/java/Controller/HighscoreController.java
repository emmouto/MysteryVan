package Controller;

import Model.Highscore;


/**
 * Creates and sorts a list of highscores that is displayed in the HighscoreView.
 *
 * @author Antonia Welzel
 */
public class HighscoreController {


    // need this?, thought was to have one same array everywhere in the game
    public Highscore[] hsArr;


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
            if(hArr[i] == null) {
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
    public Highscore[] organizeArr(Highscore[] hArr) {

        //maybe change sortingAlg if it is very slow
        boolean swapped;
        do {
            swapped = false;
            for(int i = 0; i < hArr.length; i++) {
                for (int j = 1; j < (hArr.length - i); j++) {
                    if (hArr[j - 1].getHighscore() < hArr[j].getHighscore()) {
                        swapped = true;
                        Highscore temp = hArr[j];
                        hArr[j] = hArr[j - 1];
                        hArr[j - 1] = temp;
                    }
                }
            }
        } while(swapped);

        return hArr;
    }


    //Player names -- hardcoded
    String player1 = "hello";
    String player2 = "a";
    String player3 = "v";
    String player4 = "b";
    String player5 = "c";
    String player6 = "d";
    String player7 = "e";
    String player8 = "r";
    String player9 = "t";
    String player10 = "z";

    //Player scores -- hardcoded
    int p1 = 0;
    int p2 = 4;
    int p3 = 2;
    int p4 = 8;
    int p5 = 9;
    int p6 = 6;
    int p7 = 5;
    int p8 = 3;
    int p9 = 1;
    int p10 = 5;

    //Highscores -- hardcoded
    Highscore h1 = new Highscore(p1, player1);
    Highscore h2 = new Highscore(p2, player2);
    Highscore h3 = new Highscore(p3, player3);
    Highscore h4 = new Highscore(p4, player4);
    Highscore h5 = new Highscore(p5, player5);
    Highscore h6 = new Highscore(p6, player6);
    Highscore h7 = new Highscore(p7, player7);
    Highscore h8 = new Highscore(p8, player8);
    Highscore h9 = new Highscore(p9, player9);
    Highscore h10 = new Highscore(p10, player10);

    Highscore[] hh = new Highscore[10];

    public void setH(Highscore[] highhigh) {
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

    public HighscoreController(Highscore[] hsArr) {
        this.hsArr = hsArr;
        setH(hsArr);
        organizeArr(hsArr);
    }

}



/*
 private final List<Consumer<Integer>> confirmConsumer;
    private int currentFocus = -1;
    private static final int DELAY = 180;

    private static long lastInput;


    public HighscoreController(double x, double y, double width, double height, String name) {

        //super(x, y, width, height, name);
        this.confirmConsumer = new CopyOnWriteArrayList<>();

        Input.keyboard().onKeyReleased(e -> {
            if (e.getKeyCode() == KeyEvent.VK_ENTER || e.getKeyCode() == KeyEvent.VK_SPACE || e.getKeyCode() == KeyEvent.VK_E) {
                if (this.inputIsLocked()) {
                    return;
                }

                this.confirm();
                lastInput = Game.time().now();
            }
        });

        if(Input.mouse().isPressed() && Input.mouse().isLeftMouseButtonDown()) {

            this.confirm();
            lastInput = Game.time().now();

        }

    }



    private boolean inputIsLocked() {
        // disable menu if the game has started
       /* if (this.isSuspended() || !this.isVisible() || !this.isEnabled()) {
            return true;
        }

        return Game.time().since(lastInput) < DELAY;
    }

public void onConfirm(Consumer<Integer> cons) {
        this.confirmConsumer.add(cons);
        }

private void confirm() {
        for (Consumer<Integer> cons : this.confirmConsumer) {
        cons.accept(this.currentFocus);
        }
        }
        */

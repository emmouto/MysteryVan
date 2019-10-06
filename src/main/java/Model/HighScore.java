package Model;

/**
 * HighScore Class saves and adds the points the player collects during the game,
 * which can later be shown in the Highscore screen.
 * A player receives points when killing enemies.
 *
 * @author Antonia Welzel
 * @author Jennifer Krogh
 */
public class HighScore {
    private int highscore;
    private String player;

    /**
     * At the end of the game, create new Highscore with the current score of the player
     * and the player's name which will then be used in the list of high scores.
     *
     * @param highscore
     *      TODO description
     * @param player
     *      TODO description
     */
    public HighScore(int highscore, String player) {
        this.highscore = highscore;
        this.player = player;

    }

    public int getHighScore() {
        return highscore;
    }

    public void setHighscore(int highscore) {
        this.highscore = highscore;
    }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

}

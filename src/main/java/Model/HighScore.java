package Model;

/**
 * HighScore Class saves and adds the points the player collects during the game,
 * which can later be shown in the high score screen.
 * A player receives points when killing enemies.
 *
 * @author Antonia Welzel
 * @author Jennifer Krogh
 * @version 0.1
 */
public class HighScore {
    private int highScore;
    private String player;

    /**
     * At the end of the game, create new high score with the current score of the player
     * and the player's name which will then be used in the list of high scores.
     *
     * @param highScore the score that the <code>Player</code> has at the end of the game.
     * @param player the current <code>Player</code>.
     */
    public HighScore(int highScore, String player) {
        this.highScore = highScore;
        this.player = player;
    }

    public int getHighScore() {
        return highScore;
    }

    public void setHighScore(int highScore) {
        this.highScore = highScore;
    }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

}

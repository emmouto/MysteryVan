package Model.Enemies;

import Model.Enemy;
import Model.Platform;
import Model.Player;

import java.util.List;

public class Potato extends Enemy {
    /**
     * Constructor for an enemy.
     *
     * @param sprite    the enemy's sprite.
     * @param posX      the enemy's x-coordinate.
     * @param posY      the enemy's y-coordinate.
     * @param width     the enemy's width.
     * @param height    the enemy's height.
     */
    public Potato(String sprite, int posX, int posY, int width, int height) {
        super(sprite, posX, posY, width, height);
    }

    /**
     * Moves the enemy.
     */
    @Override
    public void move() {
        if (getTarget().getX() <= getX()){
            setX(getX() - 0.1);
        } else {
            setX(getX() + 0.1);
        }
        setGrounded(false);
    }
}

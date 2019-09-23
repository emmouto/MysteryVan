package Model;

import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.IUpdateable;
import de.gurkenlabs.litiengine.annotation.CollisionInfo;
import de.gurkenlabs.litiengine.annotation.EntityInfo;
import de.gurkenlabs.litiengine.annotation.MovementInfo;
import de.gurkenlabs.litiengine.entities.Creature;
import de.gurkenlabs.litiengine.graphics.Spritesheet;
import de.gurkenlabs.litiengine.gui.screens.GameScreen;
import de.gurkenlabs.litiengine.gui.screens.Screen;
import de.gurkenlabs.litiengine.input.PlatformingMovementController;

import java.awt.*;
import java.awt.image.BufferedImage;


@EntityInfo(width = 18, height = 18)
@MovementInfo(velocity = 70)
@CollisionInfo(collisionBoxWidth = 8, collisionBoxHeight = 16, collision = true)
public class Player extends Creature implements IUpdateable, ICollidable, IMovable{

    private int HP;
    private int strength;
    private int defence;
    private Weapon weapon;
    private Hat hat;
    private Boost boost1;
    private Boost boost2;
    private int posX;
    private int posY;
    private Screen gameScreen;
    private Spritesheet playerSprite;

    public Player(String name){
        super(name);
        gameScreen = Game.screens().get("Game");
        playerSprite = new Spritesheet(new BufferedImage(700,500,BufferedImage.TYPE_INT_RGB),"textures/Golden Knight walking/spritesheet.png",700,500);
    }

    @Override
    public void update() {}

    public int getHP() {
        return HP;
    }

    public int getStrength() {
        return strength;
    }

    public int getDefence() {
        return defence;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public Hat getHat() {
        return hat;
    }

    public Boost getBoost1() {
        return boost1;
    }

    public Boost getBoost2() {
        return boost2;
    }


    public void setHP(int HP) {
        this.HP = HP;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public void setDefence(int defence) {
        this.defence = defence;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public void setHat(Hat hat) {
        this.hat = hat;
    }

    public void setBoost1(Boost boost1) {
        this.boost1 = boost1;
    }

    public void setBoost2(Boost boost2) {
        this.boost2 = boost2;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public Spritesheet getPlayerSprite() {
        return playerSprite;
    }
}

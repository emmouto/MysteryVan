package Model;

import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.IUpdateable;
import de.gurkenlabs.litiengine.annotation.AnimationInfo;
import de.gurkenlabs.litiengine.annotation.MovementInfo;
import de.gurkenlabs.litiengine.entities.Creature;
import de.gurkenlabs.litiengine.graphics.IRenderable;
import de.gurkenlabs.litiengine.graphics.ImageRenderer;
import de.gurkenlabs.litiengine.graphics.RenderEngine;
import de.gurkenlabs.litiengine.graphics.RenderType;
import de.gurkenlabs.litiengine.pathfinding.Path;
import de.gurkenlabs.litiengine.physics.MovementController;
import de.gurkenlabs.litiengine.resources.Resources;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

@MovementInfo(velocity = 30)
@AnimationInfo(spritePrefix = "enemy")
public class Enemy implements ICollidable, IMovable {

    private int HP;
    private Equipment equipment; //Enemy can have a weapon, armor etc that will make them harder to defeat.
    private Collider collider;
    private int height;
    private int width;
    private int x;
    private int y;
    private String sprite;


    public Enemy(String sprite){
        this.sprite = sprite;
    }


    public boolean checkCollision(ICollidable c){
        //if (c.getCollider())
        return true; //TODO
    }

    public int getHP() {
        return HP;
    }

    public Equipment getWeapon() {
        return equipment;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public void setWeapon(Equipment weapon) {
        this.equipment = weapon;
    }


    @Override
    public Collider getCollider() {
        return this.collider;
    }


    public double getX(){
        return this.x;
    }

    public double getY(){
        return this.y;
    }

    public String getSprite(){
        return this.sprite;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }
}
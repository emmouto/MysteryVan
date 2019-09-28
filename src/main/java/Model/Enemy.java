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
public class Enemy extends Creature implements IUpdateable, ICollidable, IMovable{

    private int HP;
    private Equipment equipment; //Enemy can have a weapon, armor etc that will make them harder to defeat.
    private Path path;
    private int currentDestinationIndex = 0;
    private MovementController<Enemy> mv;
    private BufferedImage sprite;
    private Point2D spawn;
    private Collider collider;
    private int x;
    private int y;

    public Enemy(){
    }

    public void updatePath (Path p){
        this.path = p;
        this.setMoveDestination(null);
        currentDestinationIndex = 0;
    }

    private void followPath(){
        if (this.getMoveDestination() == null){
            this.setMoveDestination(path.getStart());
        }
        if(this.getLocation().distance(this.getMoveDestination()) >= 10){
            move();
        } else {
            currentDestinationIndex++;
            this.setMoveDestination(path.getPoints().get(currentDestinationIndex));
        }
    }

    public boolean checkCollision(ICollidable c){
        //if (c.getCollider())
        return true; //TODO
    }

    private void move(){
        Game.physics().move(this, this.getMoveDestination());
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
    public void update() {
       // followPath();
    }

    @Override
    public Collider getCollider() {
        return this.collider;
    }

    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }
}
package Model;


import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.IUpdateable;
import de.gurkenlabs.litiengine.annotation.MovementInfo;
import de.gurkenlabs.litiengine.entities.Creature;
import de.gurkenlabs.litiengine.pathfinding.Path;
import de.gurkenlabs.litiengine.physics.MovementController;

import java.awt.geom.Point2D;

@MovementInfo(velocity = 30)
public class Enemy extends Creature implements IUpdateable, ICollidable, IMovable {

    private int HP;
    private Equipment equipment; //Enemy can have a weapon, armor etc that will make them harder to defeat.
    private Path path;
    private Point2D currentDestination;
    private MovementController<Enemy> mv;

    public Enemy(){
        this.mv = new MovementController<Enemy>(this);
    }

    public void updatePath (Path p){
        this.path = p;
    }

    private void followPath(){
        this.setMoveDestination(path.getStart());
        while(this.getLocation().distance(this.getMoveDestination()) >= 10){
            move();
        }
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

    }
}
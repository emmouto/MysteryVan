package Model;

import de.gurkenlabs.litiengine.IUpdateable;
import de.gurkenlabs.litiengine.entities.Creature;

public class Player extends Creature implements IUpdateable, ICollidable, IMovable{

    public Player(String name){
        super(name);
    }

    @Override
    public void update() {

    }
}

package Controller;

import Model.Player;
import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.entities.EntityControllers;
import de.gurkenlabs.litiengine.graphics.animation.Animation;
import de.gurkenlabs.litiengine.graphics.animation.AnimationController;
import de.gurkenlabs.litiengine.graphics.animation.CreatureAnimationController;
import de.gurkenlabs.litiengine.graphics.animation.EntityAnimationController;
import de.gurkenlabs.litiengine.input.Input;
import de.gurkenlabs.litiengine.input.PlatformingMovementController;

import java.awt.event.KeyEvent;

public class PlayerController extends PlatformingMovementController {

    Player p1;


    public PlayerController(Player player){
        super(player);
        this.p1 = player;
    }

    public Player getPlayer1() {
        return p1;
    }

    public void playerInit(){
        Input.keyboard().onKeyPressed(KeyEvent.VK_ESCAPE,e->System.exit(0));
        CreatureAnimationController<Player> cac = new CreatureAnimationController<Player>(p1,true);
        cac.add(new Animation(p1.getPlayerSprite(),true));
        p1.addController(cac);
    }

    //https://www.javadoc.io/doc/de.gurkenlabs/litiengine/0.4.17




}

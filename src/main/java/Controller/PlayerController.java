package Controller;

import Model.Player;
import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.entities.EntityControllers;
import de.gurkenlabs.litiengine.entities.IEntity;
import de.gurkenlabs.litiengine.environment.CreatureMapObjectLoader;
import de.gurkenlabs.litiengine.environment.Environment;
import de.gurkenlabs.litiengine.graphics.Spritesheet;
import de.gurkenlabs.litiengine.graphics.animation.Animation;
import de.gurkenlabs.litiengine.graphics.animation.AnimationController;
import de.gurkenlabs.litiengine.graphics.animation.CreatureAnimationController;
import de.gurkenlabs.litiengine.graphics.animation.EntityAnimationController;
import de.gurkenlabs.litiengine.input.Input;
import de.gurkenlabs.litiengine.input.PlatformingMovementController;
import de.gurkenlabs.litiengine.resources.Resources;

import java.awt.*;
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
        p1.getAnimationController().add(new Animation(new Spritesheet(Resources.images().get("textures/Golden Knight walking/spritesheet.png"),"textures/Golden Knight walking/spritesheet.png",18,16),true));

        CreatureMapObjectLoader.registerCustomCreatureType(Player.class);

        Game.world().environment().add(p1);

    }

    //https://www.javadoc.io/doc/de.gurkenlabs/litiengine/0.4.17




}

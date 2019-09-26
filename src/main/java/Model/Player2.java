package Model;

import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.IUpdateable;
import de.gurkenlabs.litiengine.annotation.CollisionInfo;
import de.gurkenlabs.litiengine.annotation.CombatInfo;
import de.gurkenlabs.litiengine.annotation.EntityInfo;
import de.gurkenlabs.litiengine.annotation.MovementInfo;
import de.gurkenlabs.litiengine.entities.Creature;
import de.gurkenlabs.litiengine.graphics.IRenderable;
import de.gurkenlabs.litiengine.graphics.Spritesheet;
import de.gurkenlabs.litiengine.graphics.animation.Animation;
import de.gurkenlabs.litiengine.graphics.animation.IAnimationController;
import de.gurkenlabs.litiengine.input.KeyboardEntityController;
import de.gurkenlabs.litiengine.input.PlatformingMovementController;
import de.gurkenlabs.litiengine.physics.IMovementController;
import de.gurkenlabs.litiengine.resources.Resources;
import de.gurkenlabs.litiengine.util.Imaging;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

@EntityInfo(width = 11, height = 20)
@MovementInfo(velocity = 30)
@CollisionInfo(collisionBoxWidth = 5, collisionBoxHeight = 8, collision = true)
@CombatInfo(hitpoints = 10, team = 1)
public class Player2 extends Creature implements IRenderable, IUpdateable {


    public enum PlayerState {
        CONTROLLABLE,
        LOCKED
    }

    private static Player2 instance;
    //private final Strike strike;
    //private final JumpAbility dash;

    private long lastWalkDust = 0;
    private PlayerState state = PlayerState.LOCKED;

    private Player2() {
        super("knight");
        //this.strike = new Strike(this);
        //this.dash = new JumpAbility(this);
        KeyboardEntityController<Player2> movementController = new KeyboardEntityController<>(this);
        movementController.addUpKey(KeyEvent.VK_UP);
        movementController.addDownKey(KeyEvent.VK_DOWN);
        movementController.addLeftKey(KeyEvent.VK_LEFT);
        movementController.addRightKey(KeyEvent.VK_RIGHT);

        this.setController(IMovementController.class, movementController);
        this.getMovementController().onMovementCheck(e -> {
            return this.getState() == PlayerState.CONTROLLABLE;
        });

        this.addController(new PlatformingMovementController<>(this));
        this.addDeathListener(e -> {
            Game.audio().playSound("fail.ogg");
        });

        //initAnimationController();

        this.setMapId(100000);
    }

    @Override
    public void update() {

    }

    @Override
    public void render(Graphics2D graphics2D) {
        Game.graphics().renderEntity(graphics2D, this);

    }

    public static Player2 instance() {
        if (instance == null) {
            instance = new Player2();
        }

        return instance;
    }

    public PlayerState getState() {
        return state;
    }

    public void setState(PlayerState state) {
        this.state = state;
    }

    private void initAnimationController() {


        IAnimationController controller = this.getAnimationController();
        Spritesheet jump = Resources.spritesheets().get("monger-jump");
        controller.add(new Animation(jump, false));

        final BufferedImage rightJump = Imaging.flipSpritesHorizontally(jump);
        Spritesheet rightJumpSprite = Resources.spritesheets().load(rightJump, "monger-jump-right", jump.getSpriteWidth(), jump.getSpriteHeight());
        controller.add(new Animation(rightJumpSprite, false));

        controller.setDefaultAnimation(controller.getAnimation("monger-idle"));
    }

}

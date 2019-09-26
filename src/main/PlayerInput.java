package main;

import Model.Enemies;
import Model.Player2;
import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.entities.ICombatEntity;
import de.gurkenlabs.litiengine.input.Input;
import de.gurkenlabs.litiengine.util.geom.GeometricUtilities;

import java.awt.event.KeyEvent;

public final class PlayerInput {
    public static void init() {

        Input.keyboard().onKeyReleased(KeyEvent.VK_ESCAPE, e -> {
            if (Player2.instance().getState() == Player2.PlayerState.LOCKED || Player2.instance().isDead()) {
                return;
            }
        });

        Input.keyboard().onKeyPressed(KeyEvent.VK_ESCAPE,e->System.exit(0));

        Input.keyboard().onKeyPressed(KeyEvent.VK_CONTROL, e -> {
            if (Player2.instance().getState() != Player2.PlayerState.CONTROLLABLE) {
                return;
            }

            //Player2.instance().getStrike().cast();
        });

        Input.keyboard().onKeyPressed(KeyEvent.VK_SPACE, e -> {
            if (Player2.instance().getState() != Player2.PlayerState.CONTROLLABLE) {
                return;
            }
            //Player2.instance().getDash().cast();
        });

        Input.keyboard().onKeyPressed(KeyEvent.VK_E, e -> {
            if (Player2.instance().getState() != Player2.PlayerState.CONTROLLABLE) {
                return;
            }

            boolean triggered = false;
            for (ICombatEntity entity : Game.world().environment().findCombatEntities(GeometricUtilities.scaleShape(Player2.instance().getBoundingBox(), 0.3))) {
                if (entity instanceof Enemies) {
                    Enemies enemy = (Enemies) entity;
                    if (!enemy.isDead()) {
                        enemy.sendMessage(Player2.instance(),"Test");
                        triggered = true;
                    }
                }
            }

            if (triggered) {
                return;
            }
        });
    }
}

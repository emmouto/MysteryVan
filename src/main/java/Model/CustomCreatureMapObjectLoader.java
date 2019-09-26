package Model;

import de.gurkenlabs.litiengine.entities.Creature;
import de.gurkenlabs.litiengine.environment.CreatureMapObjectLoader;
import de.gurkenlabs.litiengine.environment.tilemap.IMapObject;

public class CustomCreatureMapObjectLoader extends CreatureMapObjectLoader {

    @Override
    protected Creature createNewCreature(IMapObject mapObject, String spriteSheet, String spawnType) {
        Creature creature = super.createNewCreature(mapObject, spriteSheet, spawnType);
        if (creature instanceof Enemies) {
            Enemies enemy = (Enemies) creature;
            String type = spriteSheet.split("_")[1];
            //enemy.setType(Enum.valueOf(EnemyType.class, type));
            enemy.setIndestructible(true);
        }

        return creature;
    }
}

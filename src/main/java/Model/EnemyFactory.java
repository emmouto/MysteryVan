package Model;

import Model.Enemies.Potato;

public class EnemyFactory {

    public static Enemy spawnEnemy(){
        return new Potato("enemy", 0, 0, 32, 50);
    }
}

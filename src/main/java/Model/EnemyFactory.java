package Model;

import Model.Enemies.Potato;

public class EnemyFactory {

    private static int i= 0;

    public static Enemy spawnEnemy(){
        i+=10;
        return new Potato("enemy", i, 0, 32, 50);
    }
}

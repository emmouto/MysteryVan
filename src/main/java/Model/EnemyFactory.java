package Model;

import Model.Enemies.Potato;

public class EnemyFactory {

    private static int i= 0;

    /**
     * Class that represents the EnemyFactory, responsible
     * for creating actual Potatoes and returning Enemies to limit
     * low-level dependencies.
     *
     * @author Adam Rohdell
     * @version 0.1
     */

    public static Enemy spawnEnemy(){
        i+=10;
        return new Potato("enemy", i, -50, 23, 28,3);
    }
}

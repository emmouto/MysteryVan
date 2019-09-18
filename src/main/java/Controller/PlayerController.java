package Controller;

import Model.Player;

public class PlayerController {

    Player p1 = new Player("player1");

    public void walkLeft(){
        while(true){
            p1.setPosX(p1.getPosX()-1);
        }
    }

    public void walkRight(){
        while(true){
            p1.setPosX(p1.getPosX()+1);
        }
    }

}

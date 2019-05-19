package com.TA.strategy;

import com.TA.GameModel;
import com.TA.Tank;

public class Attack {
    private Fire fireMethod;
    private Tank tank;
    private GameModel gameModel;
    public Attack(Tank tank, Fire fireMethod, GameModel gameModel) {
        this.fireMethod=fireMethod;
        this.gameModel=gameModel;
        this.tank=tank;
    }
    public  void attack(){
        this.fireMethod.fire(tank,gameModel);
    }
}

package com.TA.strategy;

import com.TA.GameModel;
import com.TA.Tank;

public class Attack {
    private Fire fireMethod;
    private Tank tank;
    public Attack(Tank tank, Fire fireMethod) {
        this.fireMethod=fireMethod;
        this.tank=tank;
    }
    public  void attack(){
        this.fireMethod.fire(tank);
    }
}

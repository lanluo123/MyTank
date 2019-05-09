package com.TA;

public class Attack {
    private Fire fireMethod;
    private Tank tank;
    private TankFrame tankFrame;
    public Attack(Tank tank,Fire fireMethod,TankFrame tankFrame) {
        this.fireMethod=fireMethod;
        this.tankFrame=tankFrame;
        this.tank=tank;
    }
    public  void attack(){
        this.fireMethod.fire(tank,tankFrame);
    }
}

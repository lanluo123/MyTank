package com.TA;

public class BigFire implements Fire {

    @Override
    public void fire(Tank tank,TankFrame tankFrame) {
        int dirY=tank.getY()+Tank.HEIGHT/2-Bullet.HEIGHT/2;
        int dirX=tank.getX()+Tank.WIDTH/2-Bullet.WIDTH/2;
        tankFrame.bullets.add(new Bullet(dirX, dirY, Dir.DOWN,tank.getGroup(),tankFrame));
        tankFrame.bullets.add(new Bullet(dirX, dirY, Dir.LEFT,tank.getGroup(),tankFrame));
        tankFrame.bullets.add(new Bullet(dirX, dirY, Dir.RIGHT,tank.getGroup(),tankFrame));
        tankFrame.bullets.add(new Bullet(dirX, dirY, Dir.UP,tank.getGroup(),tankFrame));

    }
}

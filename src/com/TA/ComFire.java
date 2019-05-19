package com.TA;

import com.TA.strategy.Fire;

public class ComFire implements Fire {
    @Override
    public void fire(Tank tank,GameModel gameModel) {
        int dirY=tank.getY()+Tank.HEIGHT/2-Bullet.HEIGHT/2;
        int dirX=tank.getX()+Tank.WIDTH/2-Bullet.WIDTH/2;
        gameModel.bullets.add(new Bullet(dirX, dirY, tank.getDir(),tank.getGroup(),gameModel));
    }
}

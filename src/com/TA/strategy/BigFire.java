package com.TA.strategy;

import com.TA.Bullet;
import com.TA.Dir;
import com.TA.GameModel;
import com.TA.Tank;

public class BigFire implements Fire {

    @Override
    public void fire(Tank tank, GameModel gameModel) {
        int dirY=tank.getY()+Tank.HEIGHT/2- Bullet.HEIGHT/2;
        int dirX=tank.getX()+Tank.WIDTH/2-Bullet.WIDTH/2;
        gameModel.bullets.add(new Bullet(dirX, dirY, Dir.DOWN,tank.getGroup(),gameModel));
        gameModel.bullets.add(new Bullet(dirX, dirY, Dir.LEFT,tank.getGroup(),gameModel));
        gameModel.bullets.add(new Bullet(dirX, dirY, Dir.RIGHT,tank.getGroup(),gameModel));
        gameModel.bullets.add(new Bullet(dirX, dirY, Dir.UP,tank.getGroup(),gameModel));

    }
}

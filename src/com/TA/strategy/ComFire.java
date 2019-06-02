package com.TA.strategy;

import com.TA.Bullet;
import com.TA.GameModel;
import com.TA.Tank;
import com.TA.decrator.BulletDecrator;

public class ComFire implements Fire {
    @Override
    public void fire(Tank tank) {
        int dirY=tank.getY()+Tank.HEIGHT/2- Bullet.HEIGHT/2;
        int dirX=tank.getX()+Tank.WIDTH/2-Bullet.WIDTH/2;
        GameModel.getINSTANCE().add(new BulletDecrator(
                                    new Bullet(dirX, dirY, tank.getDir(),tank.getGroup())));
    }
}

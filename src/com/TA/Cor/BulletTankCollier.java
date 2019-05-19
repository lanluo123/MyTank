package com.TA.Cor;

import com.TA.*;

/**
 * @author XuMinghao
 * @create 2019/5/19-17:24
 */
public class BulletTankCollier implements Collider {
    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        if (o1 instanceof Bullet &&o2 instanceof Tank){
            Bullet b= (Bullet) o1;
            Tank   t= (Tank) o2;
            if (b.getGroup()!=t.getGroup()) {

                if (b.getBuRec().intersects(t.getTankRec())) {
                    b.die();
                    t.die();
                    int DieX=t.getX()+Tank.WIDTH/2- Explode.WIDTH/2;
                    int DieY=t.getY()+Tank.HEIGHT/2-Explode.HEIGHT/2;
                    GameModel.getINSTANCE().add(new Explode(DieX,DieY));
                }
            }
            return  false;
        }else if (o1 instanceof  Tank&&o2 instanceof Bullet){
            return  collide(o2,o1);
        }
        return true;
    }
}

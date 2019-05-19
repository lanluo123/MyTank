package com.TA.Cor;

import com.TA.Bullet;
import com.TA.GameModel;
import com.TA.GameObject;
import com.TA.Wall;

/**
 * @author XuMinghao
 * @create 2019/5/19-23:18
 */
public class WallBCollider implements Collider {
    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        if (o1 instanceof Bullet &&o2 instanceof Wall){
            Bullet b=(Bullet) o1;
            Wall w= (Wall) o2;
            if (b.getBuRec().intersects(w.getWrec())){
                b.die();
            }
        }else if(o1 instanceof Wall &&o2 instanceof Bullet){
            return  collide(o2,o1);
        }
        return  true;
    }
}

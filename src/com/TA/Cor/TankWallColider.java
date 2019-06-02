package com.TA.Cor;

import com.TA.GameObject;
import com.TA.Tank;
import com.TA.Wall;

/**
 * @author XuMinghao
 * @create 2019/5/26-12:18
 */
public class TankWallColider implements Collider {
    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        if(o1 instanceof Tank && o2 instanceof Wall){
            Tank t= (Tank) o1;
            Wall w= (Wall) o2;
            if (t.getTankRec().intersects(w.getWrec())){
                t.back();
            }
        }
        return true;
    }
}

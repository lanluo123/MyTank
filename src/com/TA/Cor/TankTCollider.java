package com.TA.Cor;

import com.TA.GameObject;
import com.TA.Tank;

import java.util.Queue;

/**
 * @author XuMinghao
 * @create 2019/5/19-17:35
 */
public class TankTCollider implements Collider {
    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        if (o1 instanceof Tank && o2 instanceof Tank)
        {
            Tank t1=(Tank) o1;
            Tank t2=(Tank) o2;
            if (t1.getTankRec().intersects(t2.getTankRec())){
                t1.back();
                t2.back();
            }
        }
        return true;
    }
}

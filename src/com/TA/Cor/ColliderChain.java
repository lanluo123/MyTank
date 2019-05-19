package com.TA.Cor;

import com.TA.Bullet;
import com.TA.GameObject;
import com.TA.Wall;

import java.util.ArrayList;
import java.util.List;

/**
 * @author XuMinghao
 * @create 2019/5/19-17:17
 */
public class ColliderChain {
    private List<Collider> list=new ArrayList<>();
    public void add(Collider c){
        this.list.add(c);
    }

    public ColliderChain() {
        add(new BulletTankCollier());
        add(new TankTCollider());
        add(new WallBCollider());
    }

    public boolean doCollide(GameObject o1, GameObject o2){
            for (int i = 0; i < list.size(); i++) {
                if (!list.get(i).collide(o1,o2)) return false;
            }

        return true;
    }
}

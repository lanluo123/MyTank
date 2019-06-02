package com.TA.Cor;

import com.TA.GameObject;

import java.io.Serializable;

/**
 * @author XuMinghao
 * @create 2019/5/19-17:15
 */
public  interface Collider extends Serializable {
      boolean collide(GameObject o1,GameObject o2);
}

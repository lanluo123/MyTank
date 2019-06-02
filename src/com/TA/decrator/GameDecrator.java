package com.TA.decrator;

import com.TA.GameObject;

import java.awt.*;

/**
 * @author XuMinghao
 * @create 2019/5/26-14:50
 */
public abstract class GameDecrator extends  GameObject{
    GameObject gameObject;

    public GameDecrator(GameObject gameObject) {
        this.gameObject = gameObject;
    }

    @Override
    public abstract void paint(Graphics g) ;
}

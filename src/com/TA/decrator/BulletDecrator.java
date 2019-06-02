package com.TA.decrator;

import com.TA.GameObject;

import java.awt.*;

/**
 * @author XuMinghao
 * @create 2019/5/26-14:51
 */
public class BulletDecrator extends GameDecrator {

    public BulletDecrator(GameObject gameObject) {
        super(gameObject);
    }

    @Override
    public void paint(Graphics g) {
        this.x= gameObject.x;
        this.y=gameObject.y;
        gameObject.paint(g);

        Color c=g.getColor();
        g.setColor(Color.BLUE);
        g.drawRect(x,y,gameObject.getWidth()+2,gameObject.getHeight()+2);
        g.setColor(c);
    }

    @Override
    public int getWidth() {
        return super.gameObject.getWidth();
    }

    @Override
    public  int getHeight() {
        return super.gameObject.getHeight();
    }
}

package com.TA;

import java.awt.*;

/**
 * @author XuMinghao
 * @create 2019/5/19-22:41
 */
public class Wall extends GameObject{

    private int WIDTH=200,HEGIT=200;
    private Rectangle wrec;
    public Wall(int x,int y,int WIDTH,int HEGIT)
    {
        this.x=x;
        this.y=y;
        this.HEGIT=HEGIT;
        this.WIDTH=WIDTH;
        this.wrec=new Rectangle(x,y,WIDTH,HEGIT);
    }

    public Rectangle getWrec() {
        return this.wrec;
    }

    @Override
    public void paint(Graphics g) {
        Color c=g.getColor();
        g.setColor(Color.YELLOW);
        g.fillRect(x,y,WIDTH,HEGIT);
        g.setColor(c);
    }
}

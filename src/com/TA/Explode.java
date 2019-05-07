package com.TA;

import java.awt.*;

public class Explode {
    private  int x,y;
    public static  int WIDTH=ImageMgr.explodes[0].getWidth();
    public static  int HEIGHT=ImageMgr.explodes[0].getHeight();
    private  boolean living =true;
    TankFrame tf=null;
    private  int step=0;
    public Explode(int x, int y,TankFrame tf) {
        this.x = x;
        this.y = y;
        this.tf=tf;
    }

    public void paint(Graphics g){

        g.drawImage(ImageMgr.explodes[step++],x,y,null);
        if (step>=ImageMgr.explodes.length)
            tf.explodes.remove(this);
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }


}

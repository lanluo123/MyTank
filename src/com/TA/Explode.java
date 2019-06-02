package com.TA;

import java.awt.*;

public class Explode extends GameObject{
    public static  int WIDTH=ImageMgr.explodes[0].getWidth();
    public static  int HEIGHT=ImageMgr.explodes[0].getHeight();
    private  boolean living =true;
    private  int step=0;
    public Explode(int x, int y ) {
        this.x = x;
        this.y = y;
    }

    public void paint(Graphics g){

        g.drawImage(ImageMgr.explodes[step++],x,y,null);
        if (step>=ImageMgr.explodes.length)
            GameModel.getINSTANCE().remove(this);
    }

    @Override
    public int getWidth() {
        return WIDTH;
    }

    @Override
    public int getHeight() {
        return HEIGHT;
    }

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }


}

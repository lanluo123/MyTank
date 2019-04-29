package com.TA;

import java.awt.Color;
import java.awt.Graphics;

public class Bullet {
	private static final int SPPED = 15;
    private  int x,y;
    private Dir dir;
	private TF tf;
	private static boolean living=true;
    public Bullet(int x,int y,Dir dir, TF tf) {
    	this.dir = dir;
    	this.x = x;
    	this.y = y;
    	this.tf=tf;
    }
    public void paint(Graphics g) {
    	 Color color=g.getColor();
    	 g.setColor(Color.YELLOW);
    	 g.fillOval(x, y, 10, 10);
    	 g.setColor(color);
    	 move();
    }
    public void move() {
    	if (dir == Dir.DOWN)
            y += SPPED;
        if (dir == Dir.UP)
            y -= SPPED;
        if (dir == Dir.LEFT)
            x -= SPPED;
        if (dir == Dir.RIGHT)
            x += SPPED;		
        if(x<0||y<0||y>tf.GAME_HEIGHT||x>tf.GAME_WIDTH)
        	tf.bullets.remove(this);
	}
	public Dir getDir() {
        return dir;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }
}

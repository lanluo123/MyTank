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
    	System.out.println("X"+x+"  y:"+y);
    	if (!living) {
			tf.bullets.remove(this);
		}
    	 Color color=g.getColor();
    	 g.setColor(Color.YELLOW);
    	 g.fillOval(x, y, 10, 10);
    	 g.setColor(color);
    	 move();
    }
    public void move() {
    	switch (dir) {
		case LEFT:
			x -= SPPED;
			break;
		case UP:
			y -= SPPED;
			break;
		case RIGHT:
			x += SPPED;
			break;
		case DOWN:
			y += SPPED;
			break;
		}
        if(x<0||y<0||y>TF.GAME_HEIGHT||x>TF.GAME_WIDTH){
        	living=false;
        }
	}
	public Dir getDir() {
        return dir;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }
}

package com.TA;

import java.awt.Color;
import java.awt.Graphics;

public class Bullet {
	private static final int SPPED = 15;
    private  int x,y;
    private Dir dir=Dir.DOWN;
	private TankFrame tankFrame =null;
	private  boolean living=true;
    public Bullet(int x,int y,Dir dir, TankFrame tankFrame) {
    	this.dir = dir;
    	this.x = x;
    	this.y = y;
    	this.tankFrame = tankFrame;
    }
    public void paint(Graphics g) {
    	if (!living) {
			tankFrame.bullets.remove(this);
		}
    	 Color color=g.getColor();
    	 g.setColor(Color.YELLOW);
    	 g.fillOval(x, y, 10, 10);
    	 g.setColor(color);
    	 move();
    }

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
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
        if(x<0||y<0||y> TankFrame.GAME_HEIGHT||x> TankFrame.GAME_WIDTH){
        	living=false;
        }
	}
}

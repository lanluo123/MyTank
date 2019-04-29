package com.TA;

import java.awt.*;

public class Tank {
    private static final int SPPED = 10;
    private  int x,y;
    private boolean moving=false;
    
    private Dir dir;
    private TF tf;
    public boolean isMvoing(){
        return  moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public Tank(int x, int y, Dir dir, TF tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf=tf;
    }

    public void paint(Graphics g) {
        Color color=g.getColor();
        g.setColor(Color.GRAY);
        g.fillRect(x, y, 50, 50);
        g.setColor(color);
        move();
    }
    private void move() {
    	 if (!moving) return;
         if (dir == Dir.DOWN)
             y += SPPED;
         if (dir == Dir.UP)
             y -= SPPED;
         if (dir == Dir.LEFT)
             x -= SPPED;
         if (dir == Dir.RIGHT)
             x += SPPED;	
	}

	public Dir getDir() {
        return dir;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

	public void fire() {
		tf.bullets.add(new Bullet(x, y, dir,tf));
		
	}

}

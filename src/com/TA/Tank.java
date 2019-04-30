package com.TA;

import java.awt.*;

public class Tank {
    private static final int SPPED = 10;
    private  int x,y;
    private boolean moving=false;
    
    private Dir dir=Dir.DOWN;
    private TankFrame tankFrame =null;
    public boolean isMvoing(){
        return  moving;
    }
    private  boolean living=true;
    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public Tank(int x, int y, Dir dir, TankFrame tankFrame) {
        super();
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tankFrame = tankFrame;
    }

    public Tank(TankFrame tankFrame) {
        this.tankFrame = tankFrame;
        this.x=(int) (Math.random()*TankFrame.GAME_WIDTH);
        this.y=(int) (Math.random()*TankFrame.GAME_HEIGHT);
    }

    public void paint(Graphics g) {
        if(tankFrame.tanks.contains(this)){
            for (int i=0;i<tankFrame.bullets.size();i++) {
                int dirY =  tankFrame.bullets.get(i).getY()-y;
                int dirX =  tankFrame.bullets.get(i).getX()-x;
                if (dirX > 0 && dirX < 50 && dirY > 0 && dirY < 50) {
                    System.out.println("dirX:" + dirX + " dirY" + dirY);
                    System.out.println("炮弹位置X:" + tankFrame.bullets.get(i).getX() + " Y" + tankFrame.bullets.get(i).getY());
                    System.out.println("坦克位置X:" + x + " Y" + y);
                    this.living = false;
                }
            }
            if(!living)
                tankFrame.tanks.remove(this);
            Color c=g.getColor();
            g.setColor(Color.YELLOW);
            g.fillRect(x,y,50,50);
            g.setColor(c);
        }
       else{
            Color color=g.getColor();
            g.setColor(Color.GRAY);
            g.fillRect(x, y, 50, 50);
            g.setColor(color);
            move();
        }
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
		tankFrame.bullets.add(new Bullet(this.x, this.y, this.dir,this.tankFrame));
		
	}

}

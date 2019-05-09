package com.TA;

import java.awt.*;
import java.util.Random;

public class Tank {
    private static final int SPPED = 10;
    private  int x,y;
    public static  int WIDTH=ImageMgr.gtankD.getWidth();
    public static  int HEIGHT=ImageMgr.gtankD.getHeight();
    private boolean moving=true;
    private Group group=Group.BAD;
    private Dir dir=Dir.DOWN;
    private TankFrame tankFrame =null;
    private  boolean living=true;
    Random random=new Random();
    Rectangle tankRec=new Rectangle();
    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public Tank(int x, int y, Dir dir, Group group,TankFrame tankFrame) {
        super();
        this.x = x;
        this.y = y;
        this.group=group;
        this.dir = dir;
        this.tankFrame = tankFrame;

        tankRec.x=x;
        tankRec.y=y;
        tankRec.height=HEIGHT;
        tankRec.width=WIDTH;
        if (this.group==Group.GOOD){
            setMoving(false);
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Tank(TankFrame tankFrame) {
        this.tankFrame = tankFrame;
        this.x=(int) (Math.random()*TankFrame.GAME_WIDTH);
        this.y=(int) (Math.random()*TankFrame.GAME_HEIGHT);
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Group getGroup() {
        return group;
    }

    public void paint(Graphics g) {
        if(!living)
            tankFrame.tanks.remove(this);
        if (group==Group.BAD){
            switch (dir){
                case UP:
                    g.drawImage(ImageMgr.btankU,x,y,null);
                    break;
                case DOWN:
                    g.drawImage(ImageMgr.btankD,x,y,null);
                    break;
                case LEFT:
                    g.drawImage(ImageMgr.btankL,x,y,null);
                    break;
                case RIGHT:
                    g.drawImage(ImageMgr.btankR,x,y,null);
                    break;
                default:
                    break;

            }
        }
        else{
            switch (dir) {
                case UP:
                    g.drawImage(ImageMgr.gtankU, x, y, null);
                    break;
                case DOWN:
                    g.drawImage(ImageMgr.gtankD, x, y, null);
                    break;
                case LEFT:
                    g.drawImage(ImageMgr.gtankL, x, y, null);
                    break;
                case RIGHT:
                    g.drawImage(ImageMgr.gtankR, x, y, null);
                    break;
                default:
                    break;
            }
        }

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
         checkBoundary();
         tankRec.x=x;
         tankRec.y=y;
         if (this.group==Group.BAD&&random.nextInt(100)>90)
         {
             this.fire();
         }
         if (this.group==Group.BAD&&random.nextInt(100)>90)
         {
             this.dir=Dir.values()[random.nextInt(4)];
         }
	}

    private void checkBoundary() {
        if (x<0) x=0;
        if (y<28) y=28;
        if (x>TankFrame.GAME_WIDTH-Tank.WIDTH) x=TankFrame.GAME_WIDTH-Tank.WIDTH;
        if (y>TankFrame.GAME_HEIGHT-Tank.HEIGHT) y=TankFrame.GAME_HEIGHT-Tank.HEIGHT;

    }

    public void die(){
        this.living=false;
    }
	public Dir getDir() {
        return dir;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

	public void fire() {
        /*int dirY=y+this.HEIGHT/2-Bullet.HEIGHT/2;
        int dirX=x+this.WIDTH/2-Bullet.WIDTH/2;
		tankFrame.bullets.add(new Bullet(dirX, dirY, this.dir,this.getGroup(),this.tankFrame));*/
        ComFire comFire=new ComFire();
        Attack tankAttack=new Attack(this,comFire,this.tankFrame);
        tankAttack.attack();
	}

}

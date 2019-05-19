package com.TA;

import java.awt.*;

public class Bullet extends GameObject {
	private static final int SPPED = 15;
	private int x, y;
	public static int WIDTH = ImageMgr.bulletD.getWidth();
	public static int HEIGHT = ImageMgr.bulletD.getHeight();
	private Dir dir = Dir.DOWN;
	private Group group=Group.GOOD;
	private boolean living = true;
	private Rectangle buRec=new Rectangle();

	public Group getGroup() {
		return group;
	}

	public Rectangle getBuRec() {
		return buRec;
	}

	public Bullet(int x, int y, Dir dir, Group group ) {
		this.dir = dir;
		this.x = x;
		this.y = y;
		this.group=group;

		buRec.x=x;
		buRec.y=y;
		buRec.height=HEIGHT;
		buRec.width=WIDTH;
	}

	public void paint(Graphics g) {
		if (!living) {
			GameModel.getINSTANCE().remove(this);
		}
		switch (dir) {
			case UP:
				g.drawImage(ImageMgr.bulletU, x, y, null);
				break;
			case DOWN:
				g.drawImage(ImageMgr.bulletD, x, y, null);
				break;
			case LEFT:
				g.drawImage(ImageMgr.bulletL, x, y, null);
				break;
			case RIGHT:
				g.drawImage(ImageMgr.bulletR, x, y, null);
				break;
			default:
				break;

		}
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
		buRec.x=x;
		buRec.y=y;
		if (x < 0 || y < 0 || y > TankFrame.GAME_HEIGHT || x > TankFrame.GAME_WIDTH) {
			living = false;
		}
	}
	public boolean colldeWith(Tank tank){
		if (this.group!=tank.getGroup()) {

			if (buRec.intersects(tank.tankRec)) {
				this.die();
				tank.die();
				int DieX=tank.getX()+Tank.WIDTH/2-Explode.WIDTH/2;
				int DieY=tank.getY()+Tank.HEIGHT/2-Explode.HEIGHT/2;
				GameModel.getINSTANCE().add(new Explode(DieX,DieY));
				return true;
			}
		}
		return  false;
	}
	public void die() {
		this.living = false;
	}
}
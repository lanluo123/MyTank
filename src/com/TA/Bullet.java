package com.TA;

import java.awt.*;

public class Bullet {
	private static final int SPPED = 15;
	private int x, y;
	public static int WIDTH = ImageMgr.bulletD.getWidth();
	public static int HEIGHT = ImageMgr.bulletD.getHeight();
	private Dir dir = Dir.DOWN;
	private Group group=Group.GOOD;
	private TankFrame tankFrame = null;
	private boolean living = true;

	public Bullet(int x, int y, Dir dir, Group group,TankFrame tankFrame) {
		this.dir = dir;
		this.x = x;
		this.y = y;
		this.group=group;
		this.tankFrame = tankFrame;
	}

	public void paint(Graphics g) {
		if (!living) {
			tankFrame.bullets.remove(this);
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
		if (x < 0 || y < 0 || y > TankFrame.GAME_HEIGHT || x > TankFrame.GAME_WIDTH) {
			living = false;
		}
	}
	public void colldeWith(Tank tank){
		if (this.group!=tank.getGroup()) {
			Rectangle rectangleB = new Rectangle(this.x, this.y, Bullet.WIDTH, Bullet.HEIGHT);
			Rectangle rectangleT = new Rectangle(tank.getX(), tank.getY(), Tank.WIDTH, Tank.HEIGHT);
			if (rectangleB.intersects(rectangleT)) {
				this.die();
				tank.die();
				tankFrame.explodes.add(new Explode(tank.getX(),tank.getY(),tankFrame));
			}
		}
	}
	public void die() {
		this.living = false;
	}
}
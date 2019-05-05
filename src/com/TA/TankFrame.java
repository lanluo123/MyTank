package com.TA;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.util.List;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;


public class TankFrame extends Frame {

	public static final int GAME_WIDTH = 800, GAME_HEIGHT = 600;
	Tank tank=new Tank(20,30,Dir.DOWN,this);
	//Tank tank2=new Tank(500, 300,Dir.DOWN,this);
	List<Bullet> bullets=new ArrayList<>();
	//List<Rect> rects=new ArrayList<>();
	List<Tank> tanks=new ArrayList<>();
	public TankFrame() {
		setSize(GAME_WIDTH, GAME_HEIGHT);
		setResizable(false);
		setTitle("tank war");
		setVisible(true);
		this.addKeyListener(new MyKeyListener());
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}

		});
	}

	@Override
	public void paint(Graphics g) {
		Color color=g.getColor();
		g.setColor(Color.WHITE);
		g.drawString("�ӵ�:"+bullets.size(), 50, 100);
		g.setColor(color);
		tank.paint(g);
		for(int i=0;i<bullets.size();i++){
			bullets.get(i).paint(g);
		}
		produRect();

		/*for(int i=0;i<rects.size();i++){
			rects.get(i).paint(g);
		}*/
		for(int i=0;i<tanks.size();i++){
		    tanks.get(i).setMoving(false);
			tanks.get(i).paint(g);
		}

	}

	private void produRect() {
		/*if (rects.size()==0){
			rects.add(new Rect(this));
			rects.add(new Rect(this));
		}*/

        if (tanks.size()==0){
            tanks.add(new Tank(this));
            tanks.add(new Tank(this));
		}
	}

	Image offImage=null;
	@Override
	public void update(Graphics g) {
		if (offImage==null) {
			offImage=this.createImage(GAME_WIDTH,GAME_HEIGHT);
		}
		Graphics gOffScreen=offImage.getGraphics();
		Color color=gOffScreen.getColor();
		gOffScreen.setColor(Color.BLACK);
		gOffScreen.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
		gOffScreen.setColor(color);
		paint(gOffScreen);
		g.drawImage(offImage, 0, 0, null);
	}
	class MyKeyListener extends KeyAdapter {

		boolean bL = false;
		boolean bU = false;
		boolean bR = false;
		boolean bD = false;

		@Override
		public void keyPressed(KeyEvent e) {
			int key = e.getKeyCode();
			switch (key) {
			
			case KeyEvent.VK_LEFT:
				bL = true;
				break;
			case KeyEvent.VK_UP:
				bU = true;
				break;
			case KeyEvent.VK_RIGHT:
				bR = true;
				break;
			case KeyEvent.VK_DOWN:
				bD = true;
				break;

			default:
				break;
			}
			seMovDir();
		}

		private void seMovDir() {
			if(!bL&&!bD&&!bR&&!bU) tank.setMoving(false);
			else {
				tank.setMoving(true);
				if (bD) tank.setDir(Dir.DOWN);
				if (bL) tank.setDir(Dir.LEFT);
				if (bR) tank.setDir(Dir.RIGHT);
				if (bU) tank.setDir(Dir.UP);
			}

		}

		@Override
		public void keyReleased(KeyEvent e) {

			int key = e.getKeyCode();
			switch (key) {
				case KeyEvent.VK_LEFT:
					bL = false;
					break;
				case KeyEvent.VK_UP:
					bU = false;
					break;
				case KeyEvent.VK_RIGHT:
					bR = false;
					break;
				case KeyEvent.VK_DOWN:
					bD = false;
					break;
				case KeyEvent.VK_CONTROL:
					tank.fire();
					break;
				default:
					break;
			}

			seMovDir();
		}

	}

}
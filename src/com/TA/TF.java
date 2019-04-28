package com.TA;

import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import org.omg.CORBA.INTERNAL;

public class TF extends Frame {

	int x = 200, y = 200;
	private Dir dir = Dir.DOWN;

	private static final int GAME_WIDTH = 800, GAME_HEIGHT = 600;
	private static final int SPPED = 50;

	public TF() {
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
		g.fillRect(x, y, 50, 50);
		if (dir == Dir.DOWN)
			y += SPPED;
		if (dir == Dir.UP)
			y -= SPPED;
		if (dir == Dir.LEFT)
			x -= SPPED;
		if (dir == Dir.RIGHT)
			x += SPPED;
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
			if (bD)
				dir = Dir.DOWN;
			if (bL)
				dir = Dir.LEFT;
			if (bR)
				dir = Dir.RIGHT;
			if (bU)
				dir = Dir.UP;
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
			default:
				break;
			}
			seMovDir();
		}

	}

}

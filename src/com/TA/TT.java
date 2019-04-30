package com.TA;

public class TT {

	public static void main(String[] args) throws InterruptedException {
		TankFrame tankFrame =new TankFrame();
		while(true){
			Thread.sleep(100);
			tankFrame.repaint();

		}

	}
}

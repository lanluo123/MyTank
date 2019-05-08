package com.TA;

public class TT {

	public static void main(String[] args) throws InterruptedException {
		TankFrame tankFrame =new TankFrame();
		for (int i=0;i<Integer.parseInt((String) PropertyMgr.getKey("initialTankAmount"));i++){
			tankFrame.tanks.add(new Tank(50+i*80,100,Dir.DOWN,Group.BAD,tankFrame));
		}
		while(true){
			Thread.sleep(100);
			tankFrame.repaint();
		}
	}
}

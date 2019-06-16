package com.tank;

import net.Client;

public class TT {

	public static void main(String[] args) throws InterruptedException {
		TankFrame tankFrame =TankFrame.INSTANCE;
		tankFrame.setVisible(true);
		/*for (int i=0;i<Integer.parseInt((String) PropertyMgr.getKey("initialTankAmount"));i++){
			tankFrame.tanks.add(new Tank(50+i*80,100,Dir.DOWN,Group.BAD,tankFrame));
		}*/
		new Thread(()->{
			while(true){
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				tankFrame.repaint();
			}
		}).start();
		Client.INSTANCE.connect();
	}
}

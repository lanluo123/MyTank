package com.TA;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author XuMinghao
 * @create 2019/5/19-15:38
 */
public class GameModel {
    Tank tank=new Tank(20,30,Dir.DOWN,Group.GOOD,this);
   public List<Bullet> bullets=new ArrayList<>();
    public List<Tank> tanks=new ArrayList<>();
    public List<Explode> explodes=new ArrayList<>();
    public Tank getMainTank(){
        return this.tank;
    }

    public GameModel() {
        for (int i=0;i<Integer.parseInt((String) PropertyMgr.getKey("initialTankAmount"));i++){
            this.tanks.add(new Tank(50+i*80,100,Dir.DOWN,Group.BAD,this));
        }
    }

    public void paint(Graphics g) {
        Color color=g.getColor();
        g.setColor(Color.WHITE);
        g.drawString("子弹数量"+bullets.size(),50, 100);
        g.drawString("坦克数量"+tanks.size(), 50, 120);
        g.drawString("爆炸数量"+explodes.size(), 50, 140);
        g.setColor(color);
        tank.paint(g);
        for(int i=0;i<bullets.size();i++){
            bullets.get(i).paint(g);
        }

        for(int i=0;i<tanks.size();i++){
            tanks.get(i).paint(g);
        }
        for(int i=0;i<bullets.size();i++){
            for(int j=0;j<tanks.size();j++){
                bullets.get(i).colldeWith(tanks.get(j));
            }
        }
        for (int i = 0; i < explodes.size(); i++) {
            explodes.get(i).paint(g);
        }
    }
}

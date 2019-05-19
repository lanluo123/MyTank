package com.TA;

import com.TA.Cor.ColliderChain;


import java.awt.*;
import java.awt.event.WindowAdapter;
import java.util.ArrayList;
import java.util.List;

/**
 * @author XuMinghao
 * @create 2019/5/19-15:38
 */
public class GameModel {

    private static GameModel INSTANCE =new GameModel();

    static {
        INSTANCE.init();
    }

//    public List<Bullet> bullets=new ArrayList<>();
//    public List<Tank> tanks=new ArrayList<>();
//    public List<Explode> explodes=new ArrayList<>();
    Tank tank;
    public List<GameObject> list=new ArrayList<>();
    ColliderChain chain=new ColliderChain();

    public Tank getMainTank(){
        return this.tank;
    }
    public static GameModel getINSTANCE(){
        return INSTANCE;
    }
    private void init(){
         tank=new Tank(20,30,Dir.DOWN,Group.GOOD);
        for (int i=0;i<Integer.parseInt((String) PropertyMgr.getKey("initialTankAmount"));i++){
            add(new Tank(50+i*80,200,Dir.DOWN,Group.BAD));
        }
        add(new Wall(200,200,100,100));
    }

    public void add(GameObject object){
        list.add(object);
    }

    private GameModel() {
    }

    public void remove(GameObject o){
        list.remove(o);
    }

    public void paint(Graphics g) {
        Color color=g.getColor();
        g.setColor(Color.WHITE);
        g.setColor(color);
        tank.paint(g);
//        g.drawString("子弹数量"+bullets.size(),50, 100);
//        g.drawString("坦克数量"+tanks.size(), 50, 120);
//        g.drawString("爆炸数量"+explodes.size(), 50, 140);
       /* for(int i=0;i<bullets.size();i++){
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
        }*/
        for (int i = 0; i < list.size(); i++) {
            list.get(i).paint(g);
        }
        for (int i = 0; i < list.size(); i++) {
            for (int j = i+1; j < list.size(); j++) {
                GameObject o1=list.get(i);
                GameObject o2=list.get(j);
                    chain.doCollide(o1,o2);

            }
        }
    }
}

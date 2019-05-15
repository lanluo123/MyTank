package com.TA.AbFactory;

import com.TA.Group;
import com.TA.ImageMgr;
import com.TA.Tank;

import java.awt.*;

/**
 * @author XuMinghao
 * @create 2019/5/12-19:28
 */
public class DefaultTPaint extends PTank {
    @Override
    void paintT(Tank t, Graphics g) {
        if (t.getGroup()== Group.BAD){
            switch (t.getDir()){
                case UP:
                    g.drawImage(ImageMgr.btankU,t.getX(),t.getY(),null);
                    break;
                case DOWN:
                    g.drawImage(ImageMgr.btankD,t.getX(),t.getY(),null);
                    break;
                case LEFT:
                    g.drawImage(ImageMgr.btankL,t.getX(),t.getY(),null);
                    break;
                case RIGHT:
                    g.drawImage(ImageMgr.btankR,t.getX(),t.getY(),null);
                    break;
                default:
                    break;

            }
        }
        else{
            switch (t.getDir()) {
                case UP:
                    g.drawImage(ImageMgr.gtankU, t.getX(),t.getY(), null);
                    break;
                case DOWN:
                    g.drawImage(ImageMgr.gtankD, t.getX(),t.getY(), null);
                    break;
                case LEFT:
                    g.drawImage(ImageMgr.gtankL,t.getX(),t.getY(),null);
                    break;
                case RIGHT:
                    g.drawImage(ImageMgr.gtankR, t.getX(),t.getY(), null);
                    break;
                default:
                    break;
            }
        }
    }
}

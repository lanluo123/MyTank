package com.TA;

import java.awt.*;

/**
 * @author XuMinghao
 * @create 2019/5/12-19:36
 */
public class DefaultBPaint extends PBullet {

    @Override
    void PaintB(Bullet b, Graphics g) {
        switch (b.getDir()) {
            case UP:
                g.drawImage(ImageMgr.bulletU, b.getX(), b.getY(), null);
                break;
            case DOWN:
                g.drawImage(ImageMgr.bulletD, b.getX(), b.getY(),null);
                break;
            case LEFT:
                g.drawImage(ImageMgr.bulletL,b.getX(), b.getY(), null);
                break;
            case RIGHT:
                g.drawImage(ImageMgr.bulletR, b.getX(), b.getY(),null);
                break;
            default:
                break;

        }
    }
}

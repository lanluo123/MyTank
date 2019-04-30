
package com.TA;
import java.awt.*;

public class Rect {
    private  int x,y;
    private  TankFrame tf;
    private  boolean living=true;
    public Rect(TankFrame tf) {
        super();
        this.x=(int) (Math.random()*TankFrame.GAME_WIDTH);
        this.y=(int) (Math.random()*TankFrame.GAME_HEIGHT);
        this.tf=tf;
    }
    public  void paint(Graphics g){
        for (int i=0;i<tf.bullets.size();i++) {
            int dirY =  tf.bullets.get(i).getY()-y;
            int dirX =  tf.bullets.get(i).getX()-x;
            if (dirX > 0 && dirX < 50 && dirY > 0 && dirY < 50) {

                System.out.println("dirX:" + dirX + " dirY" + dirY);
                System.out.println("炮弹位置X:" + tf.bullets.get(i).getX() + " Y" + tf.bullets.get(i).getY());
                System.out.println("坦克位置X:" + x + " Y" + y);
                this.living = false;

            }
        }
        if(!living)
            tf.rects.remove(this);
        Color c=g.getColor();
        g.setColor(Color.YELLOW);
        g.fillRect(x,y,50,50);
        g.setColor(c);
    }

}

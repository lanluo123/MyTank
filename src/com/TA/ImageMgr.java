package com.TA;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ImageMgr {
    public static BufferedImage tankD,tankU,tankL,tankR;
    public static BufferedImage bulletD,bulletU,bulletL,bulletR;
    public static BufferedImage[] explodes=new BufferedImage[16];
    static {
        try {
            tankD= ImageIO.read(ImageMgr.class.getClassLoader().getResourceAsStream("images/tankD.gif"));
            tankL= ImageIO.read(ImageMgr.class.getClassLoader().getResourceAsStream("images/tankL.gif"));
            tankR= ImageIO.read(ImageMgr.class.getClassLoader().getResourceAsStream("images/tankR.gif"));
            tankU= ImageIO.read(ImageMgr.class.getClassLoader().getResourceAsStream("images/tankU.gif"));

            bulletD= ImageIO.read(ImageMgr.class.getClassLoader().getResourceAsStream("images/bulletD.gif"));
            bulletL= ImageIO.read(ImageMgr.class.getClassLoader().getResourceAsStream("images/bulletL.gif"));
            bulletR= ImageIO.read(ImageMgr.class.getClassLoader().getResourceAsStream("images/bulletR.gif"));
            bulletU= ImageIO.read(ImageMgr.class.getClassLoader().getResourceAsStream("images/bulletU.gif"));

            for (int i = 0; i < explodes.length; i++) {
                explodes[i]= ImageIO.read(ImageMgr.class.getClassLoader().getResourceAsStream("images/e"+(i+1)+".gif"));

            }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}

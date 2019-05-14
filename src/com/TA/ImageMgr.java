package com.TA;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ImageMgr {
    private static  final ImageMgr imageMgr=new ImageMgr();
    private ImageMgr(){}
    public static ImageMgr getImageMgr(){
        return imageMgr;
    }
    public static BufferedImage gtankD,gtankU,gtankL,gtankR;
    public static BufferedImage tankD,tankU,tankL,tankR;
    public static BufferedImage btankD,btankU,btankL,btankR;
    public static BufferedImage bulletD,bulletU,bulletL,bulletR;
    public static BufferedImage[] explodes=new BufferedImage[16];
    static {
        try {
            tankU= ImageIO.read(ImageMgr.class.getClassLoader().getResourceAsStream("images/tankU.gif"));
            tankL= ImageIO.read(ImageMgr.class.getClassLoader().getResourceAsStream("images/tankL.gif"));
            tankD= ImageIO.read(ImageMgr.class.getClassLoader().getResourceAsStream("images/tankD.gif"));
            tankR= ImageIO.read(ImageMgr.class.getClassLoader().getResourceAsStream("images/tankR.gif"));

            gtankU= ImageIO.read(ImageMgr.class.getClassLoader().getResourceAsStream("images/GoodTank1.png"));
            gtankL= ImageUtil.rotateImage(gtankU,-90);
            gtankR= ImageUtil.rotateImage(gtankU,90);;
            gtankD= ImageUtil.rotateImage(gtankU,180);;

            btankU= ImageIO.read(ImageMgr.class.getClassLoader().getResourceAsStream("images/BadTank1.png"));
            btankL= ImageUtil.rotateImage(btankU,-90);
            btankR= ImageUtil.rotateImage(btankU,90);;
            btankD= ImageUtil.rotateImage(btankU,180);;

            bulletU= ImageIO.read(ImageMgr.class.getClassLoader().getResourceAsStream("images/bulletU.png"));
            bulletL= ImageUtil.rotateImage(bulletU,-90);
            bulletR= ImageUtil.rotateImage(bulletU,90);;
            bulletD= ImageUtil.rotateImage(bulletU,180);

            for (int i = 0; i < explodes.length; i++) {
                explodes[i]= ImageIO.read(ImageMgr.class.getClassLoader().getResourceAsStream("images/e"+(i+1)+".gif"));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

package com.TA;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ImageMgr {
    public static BufferedImage tankD,tankU,tankL,tankR;
    static {
        try {
            tankD= ImageIO.read(ImageMgr.class.getClassLoader().getResourceAsStream("images/tankD.gif"));
            tankL= ImageIO.read(ImageMgr.class.getClassLoader().getResourceAsStream("images/tankL.gif"));
            tankR= ImageIO.read(ImageMgr.class.getClassLoader().getResourceAsStream("images/tankR.gif"));
            tankU= ImageIO.read(ImageMgr.class.getClassLoader().getResourceAsStream("images/tankU.gif"));

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}

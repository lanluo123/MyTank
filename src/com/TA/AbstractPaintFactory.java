package com.TA;

import java.awt.*;

public abstract class AbstractPaintFactory {
    abstract PBullet createPBullet(Graphics g);
    abstract PTank  paintPTank(Graphics g);
}


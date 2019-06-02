package com.TA;

import java.awt.*;
import java.io.Serializable;

/**
 * @author XuMinghao
 * @create 2019/5/19-15:34
 */
public abstract  class GameObject implements Serializable {
    public int x;
    public int y;
    public abstract void paint(Graphics g);

    public abstract int getWidth();
    public abstract int getHeight();
}

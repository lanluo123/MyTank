package net;

/**
 * @author XuMinghao
 * @create 2019/6/9-0:01
 */
public class TankMsg {
    int x;
    int y;

    public TankMsg(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "TankClass{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}

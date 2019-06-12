package net;

/**
 * @author XuMinghao
 * @create 2019/6/12-23:31
 */
public abstract class Msg {
    public abstract byte[] toBytes();
    public abstract void handle();
}

package net.msg;

import com.tank.Bullet;
import com.tank.Tank;
import com.tank.TankFrame;
import net.Msg;
import net.MsgType;

import java.io.*;
import java.util.UUID;

/**
 * @author XuMinghao
 * @create 2019/6/9-0:01
 */
public class TankDieMsg extends Msg {

    public UUID uuid ;
    public UUID bulletId;
    public TankDieMsg(Tank t, Bullet b){
        this.bulletId=b.getId();
        this.uuid=t.getId();
    }
    public TankDieMsg(){}
    public TankDieMsg(UUID tid,UUID bid) {
        super();
        this.uuid = tid;
        this.bulletId=bid;
    }
    @Override
    public void parse(byte[] bytes){
        DataInputStream dis=new DataInputStream(new ByteArrayInputStream(bytes));
        try{
            this.uuid=new UUID(dis.readLong(),dis.readLong());
            this.bulletId=new UUID(dis.readLong(),dis.readLong());
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (dis!=null){
                try {
                    dis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    @Override
    public MsgType getMsgType() {
        return MsgType.TankDie;
    }

    @Override
    public byte[] toBytes() {
        byte[] bytes=null;
        ByteArrayOutputStream baos=null;
        DataOutputStream dos=null;
        try{
            baos=new ByteArrayOutputStream();
            dos=new DataOutputStream(baos);
            dos.writeLong(uuid.getMostSignificantBits());
            dos.writeLong(uuid.getLeastSignificantBits());
            dos.writeLong(bulletId.getLeastSignificantBits());
            dos.writeLong(bulletId.getLeastSignificantBits());
            bytes=baos.toByteArray();
        }catch (Exception e){
            e.printStackTrace();
        }finally {

            if (baos!=null) {
                try {
                    baos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (dos!=null) {
                try {
                    dos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        return bytes;
    }


    @Override
    public void handle() {

        Bullet  bullet=TankFrame.INSTANCE.getBullet(bulletId);
        if (bullet!=null) {
            bullet.die();
        }
        if (this.uuid.equals(TankFrame.INSTANCE.getMainTank().getId())){
           TankFrame.INSTANCE.getMainTank().die();
        }else{

            Tank tank = TankFrame.INSTANCE.getTank(this.uuid);
            if (tank!=null){
                tank.die();
            }
        }

    }
    @Override
    public String toString() {
        StringBuilder builder=new StringBuilder();
        builder.append(this.getClass().getName())
                .append("[")
                .append("tankid="+uuid+"||")
                .append("bulletid="+bulletId+"||")
                .append("]");
        return builder.toString();
    }
}

























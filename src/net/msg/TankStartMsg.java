package net.msg;

import com.TA.Bullet;
import com.TA.Dir;
import com.TA.Tank;
import com.TA.TankFrame;
import io.netty.buffer.ByteBuf;
import net.Msg;
import net.MsgType;

import java.io.*;
import java.util.UUID;

/**
 * @author XuMinghao
 * @create 2019/6/13-21:26
 */
public class TankStartMsg extends Msg {

    int x,y;
    Dir dir=Dir.DOWN;
    UUID id;
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Dir getDir() {
        return dir;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }



    public TankStartMsg() { }

    public TankStartMsg(int x, int y, Dir dir, UUID id) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.id = id;
    }

    public TankStartMsg(Tank t){
        this.x=t.getX();
        this.y=t.getY();
        this.dir=t.getDir();
        this.id=t.getId();
    }

    @Override
    public byte[] toBytes() {
        ByteArrayOutputStream bos=null;
        DataOutputStream dos=null;
        byte[] bytes=null;
        try
        {
            bos=new ByteArrayOutputStream();
            dos=new DataOutputStream(bos);
            dos.writeLong(id.getMostSignificantBits());
            dos.writeLong(id.getLeastSignificantBits());
            dos.writeInt(this.x);
            dos.writeInt(this.y);
            dos.writeInt(this.dir.ordinal());
            dos.flush();
            bytes=bos.toByteArray();

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (bos!=null) {
                try {
                    bos.close();
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
        if (this.id.equals(TankFrame.INSTANCE.getMainTank().getId()))
            return;
        Tank t=TankFrame.INSTANCE.getTank(id);
        t.setMoving(true);
        t.setX(this.x);
        t.setY(this.y);
        t.setDir(this.dir);
    }

    @Override
    public void parse(byte[] bytes) {
        DataInputStream dis=new DataInputStream(new ByteArrayInputStream(bytes));
        try {
            this.id=new UUID(dis.readLong(),dis.readLong());
            this.x=dis.readInt();
            this.y=dis.readInt();
            this.dir=Dir.values()[dis.readInt()];
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (dis != null) {
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
        return MsgType.TankStart;
    }

    @Override
    public String toString() {
        StringBuilder builder=new StringBuilder();
        builder.append(this.getClass().getName())
                .append("[")
                .append("x="+x+"|")
                .append("uuid="+id+"|")
                .append("y="+y+"|")
                .append("Dir="+dir+"|")
                .append("]");
        return builder.toString();
    }
}

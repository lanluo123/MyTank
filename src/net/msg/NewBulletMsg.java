package net.msg;

import com.tank.Bullet;
import com.tank.Dir;
import com.tank.Group;
import com.tank.TankFrame;
import net.Msg;
import net.MsgType;

import java.io.*;
import java.util.UUID;

/**
 * @author XuMinghao
 * @create 2019/6/16-10:03
 */
public class NewBulletMsg extends Msg {
    UUID id;
    UUID playerid;
    Group group;
    Dir dir;
    int x, y;

    public NewBulletMsg() {
    }

    public NewBulletMsg(Bullet b) {
        this.id = b.getId();
        this.playerid = b.getPlayerid();
        this.group = b.getGroup();
        this.dir = b.getDir();
        this.x = b.getX();
        this.y = b.getY();
    }


    @Override
    public byte[] toBytes() {
        ByteArrayOutputStream bos=null;
        DataOutputStream dos=null;
        byte[] bytes=null;
        try {
            bos=new ByteArrayOutputStream();
            dos=new DataOutputStream(bos);
            dos.writeLong(id.getMostSignificantBits());
            dos.writeLong(id.getLeastSignificantBits());
            dos.writeLong(playerid.getMostSignificantBits());
            dos.writeLong(playerid.getLeastSignificantBits());
            dos.writeInt(x);
            dos.writeInt(y);
            dos.writeInt(dir.ordinal());
            dos.writeInt(group.ordinal());
            bytes=bos.toByteArray();

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (dos!=null) {
                try {
                    dos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bos!=null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return  bytes;
    }
    @Override
    public void parse(byte[] bytes) {
        DataInputStream dis=new DataInputStream(new ByteArrayInputStream(bytes));
        try {
            this.id=new UUID(dis.readLong(),dis.readLong());
            this.playerid=new UUID(dis.readLong(),dis.readLong());
            this.x=dis.readInt();
            this.y=dis.readInt();
            this.dir=Dir.values()[dis.readInt()];
            this.group=Group.values()[dis.readInt()];
        } catch (IOException e) {
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
    public void handle() {
        if (this.playerid.equals(TankFrame.INSTANCE.getMainTank().getId())){
            return;
        }
        Bullet bullet=new Bullet(x,y,dir,this.playerid,group,TankFrame.INSTANCE);
        bullet.setId(id);
        TankFrame.INSTANCE.addBullet(bullet);
    }


    @Override
    public MsgType getMsgType()
    {
        return MsgType.BulletNew;
    }

    @Override
    public String toString() {
        StringBuilder builder=new StringBuilder();
        builder.append(this.getClass().getName())
                .append("[")
                .append("x="+x+"|")
                .append("y="+y+"|")
                .append("id="+id+"|")
                .append("playerid="+playerid+"|")
                .append("group="+group)
                .append("dir="+dir)
                .append("]");
        return builder.toString();
    }

    public void setGroup(Group group) {
        this.group = group;
    }
}

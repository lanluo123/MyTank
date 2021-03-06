package net;

import com.TA.Dir;
import com.TA.Group;
import com.TA.Tank;

import java.io.*;
import java.util.UUID;

/**
 * @author XuMinghao
 * @create 2019/6/9-0:01
 */
public class TankJoinMsg {
    public   int x,y;
    public boolean moving;
    public Group group;
    public Dir dir;
    public UUID uuid ;

    public TankJoinMsg(Tank t){
        this.x=t.getX();
        this.y=t.getY();
        this.group=t.getGroup();
        this.moving=t.isMoving();
        this.uuid=t.getId();
    }
    public TankJoinMsg(){}
    public TankJoinMsg(int x, int y, Group group, Dir dir, boolean moving,UUID uuid) {
        super();
        this.x = x;
        this.y = y;
        this.group = group;
        this.dir = dir;
        this.moving=moving;
        this.uuid = uuid;
    }
    public void parse(byte[] bytes){
        DataInputStream dis=new DataInputStream(new ByteArrayInputStream(bytes));
        try{
            this.x=dis.readInt();
            this.y=dis.readInt();
            this.moving=dis.readBoolean();
            this.group=Group.values()[dis.readInt()];
            this.dir=Dir.values()[dis.readInt()];
            this.uuid=new UUID(dis.readLong(),dis.readLong());
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

    public byte[] toBytes() {
        byte[] bytes=null;
        ByteArrayOutputStream baos=null;
        DataOutputStream dos=null;
        try{
            baos=new ByteArrayOutputStream();
            dos=new DataOutputStream(baos);
            dos.writeInt(this.x);
            dos.writeInt(this.y);
            dos.writeBoolean(this.moving);
            dos.writeInt(group.ordinal());
            dos.writeInt(dir.ordinal());
            dos.writeLong(uuid.getMostSignificantBits());
            dos.writeLong(uuid.getLeastSignificantBits());
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
    public String toString() {
        return "TankJoinMsg{" +
                "x=" + x +
                ", y=" + y +
                ", moving=" + moving +
                ", group=" + group +
                ", dir=" + dir +
                ", uuid=" + uuid +
                '}';
    }


}

























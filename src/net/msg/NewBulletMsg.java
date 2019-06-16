package net.msg;

import com.TA.Bullet;
import com.TA.Dir;
import com.TA.Group;
import net.Msg;
import net.MsgType;

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
   int x,y;

    public NewBulletMsg(){}

    public NewBulletMsg(Bullet b){
        this.id=b.getId();
    }


    @Override
    public byte[] toBytes() {
        return new byte[0];
    }

    @Override
    public void handle() {

    }

    @Override
    public void parse(byte[] bytes)  {
    }

    @Override
    public MsgType getMsgType() {
        return null;
    }
}

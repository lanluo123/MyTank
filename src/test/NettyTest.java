package test;

import com.tank.Dir;
import com.tank.Group;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.embedded.EmbeddedChannel;
import net.MsgDecode;
import net.MsgEncode;
import net.MsgType;
import net.msg.TankJoinMsg;
import org.junit.Assert;
import org.junit.Test;

import java.util.UUID;

/**
 * @author XuMinghao
 * @create 2019/6/9-10:45
 */
public class NettyTest {
    @Test
    public void TestMsgEncoder(){
        UUID uuid=UUID.randomUUID();
        TankJoinMsg msg=new TankJoinMsg(3,4, Group.BAD, Dir.DOWN,false,uuid);
        EmbeddedChannel channel=new EmbeddedChannel(new MsgEncode());
        channel.writeOutbound(msg);
        ByteBuf buf= (ByteBuf)  channel.readOutbound();
        int index=buf.readInt();
        MsgType type=MsgType.values()[index];
        Assert.assertTrue(type.equals(MsgType.TankJoin));
        int length=buf.readInt();

        int x=buf.readInt();


        int y=buf.readInt();
        Assert.assertTrue(length==33&&x==3&&y==4);
        buf.release();
    }
    @Test
    public void TestMsgEncoder2(){
        ByteBuf buf= Unpooled.buffer();
        UUID uuid=UUID.randomUUID();
        EmbeddedChannel channel=new EmbeddedChannel();
        channel.pipeline().addLast(new MsgDecode());
        TankJoinMsg msg=new TankJoinMsg(10,10, Group.BAD, Dir.DOWN,false,uuid);
        buf.writeInt(MsgType.TankJoin.ordinal());
        byte[] bytes=msg.toBytes();
        buf.writeInt(bytes.length);
        buf.writeBytes(msg.toBytes());

        channel.writeInbound(buf.duplicate());

        TankJoinMsg tankJoinMsg =(TankJoinMsg)  channel.readInbound();

        Assert.assertTrue(tankJoinMsg.x==10&& tankJoinMsg.y==10);

    }
}

package test;

import com.tank.Dir;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.embedded.EmbeddedChannel;
import net.*;
import net.msg.TankChangeMsg;
import org.junit.Assert;
import org.junit.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author XuMinghao
 * @create 2019/6/9-10:45
 */
public class NettyChangeTest {
    @Test
    public void TestMsgEncoder(){
        UUID uuid=UUID.randomUUID();
        TankChangeMsg msg=new TankChangeMsg(3,4,Dir.DOWN,uuid);
        EmbeddedChannel channel=new EmbeddedChannel(new MsgEncode());
        channel.writeOutbound(msg);
        ByteBuf buf= (ByteBuf)  channel.readOutbound();
        int index=buf.readInt();
        MsgType type=MsgType.values()[index];
        Assert.assertTrue(type.equals(MsgType.TankDirChange));
        int length=buf.readInt();
        int x=buf.readInt();
        int y=buf.readInt();
        Assert.assertTrue(x==3&&y==4);
        buf.release();
    }
    @Test
    public void testDecoder2() {
        ByteBuf buf= Unpooled.buffer();
        UUID uuid=UUID.randomUUID();
        EmbeddedChannel channel=new EmbeddedChannel();
        channel.pipeline().addLast(new MsgDecode());
        TankChangeMsg msg= new TankChangeMsg(10,10,  Dir.DOWN,uuid);
        buf.writeInt(MsgType.TankDirChange.ordinal());
        byte[] bytes=msg.toBytes();
        buf.writeInt(bytes.length);
        buf.writeBytes(msg.toBytes());
        channel.writeInbound(buf.duplicate());
        TankChangeMsg tankJoinMsg =(TankChangeMsg)  channel.readInbound();
        Assert.assertTrue(tankJoinMsg.getX()==10&& tankJoinMsg.getY()==10);
    }
}

package test;

import com.TA.Dir;
import com.TA.Group;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.embedded.EmbeddedChannel;
import net.MsgJoinDecode;
import net.MsgJoinEncode;
import net.TankJoinMsg;
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
        EmbeddedChannel channel=new EmbeddedChannel(new MsgJoinEncode());
        channel.writeOutbound(msg);
        ByteBuf buf= (ByteBuf)  channel.readOutbound();
        int x=buf.readInt();
        int y=buf.readInt();
        Assert.assertTrue(x==3&&y==4);
        buf.release();
    }
    @Test
    public void TestMsgEncoder2(){
        ByteBuf buf= Unpooled.buffer();
        UUID uuid=UUID.randomUUID();
        EmbeddedChannel channel=new EmbeddedChannel();
        channel.pipeline().addLast(new MsgJoinDecode());
        TankJoinMsg msg=new TankJoinMsg(10,10, Group.BAD, Dir.DOWN,false,uuid);
        buf.writeBytes(msg.toBytes());

        channel.writeInbound(buf.duplicate());

        TankJoinMsg tankJoinMsg =(TankJoinMsg)  channel.readInbound();

        Assert.assertTrue(tankJoinMsg.x==10&& tankJoinMsg.y==10);

    }
}

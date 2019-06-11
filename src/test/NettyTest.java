package test;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.embedded.EmbeddedChannel;
import net.MsgDecode;
import net.MsgEncode;
import net.TankMsg;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author XuMinghao
 * @create 2019/6/9-10:45
 */
public class NettyTest {
    @Test
    public void TestMsgEncoder(){
        TankMsg msg=new TankMsg(3,4);
        EmbeddedChannel channel=new EmbeddedChannel(new MsgEncode());
        channel.writeAndFlush(msg);
        ByteBuf buf= (ByteBuf)  channel.readOutbound();
        int x=buf.readInt();
        int y=buf.readInt();
        Assert.assertTrue(x==3&&y==4);
        buf.release();
    }
    @Test
    public void TestMsgEncoder2(){
        ByteBuf buf= Unpooled.buffer();
        TankMsg msg=new TankMsg(10,10);
        EmbeddedChannel channel=new EmbeddedChannel(new MsgEncode(),new MsgDecode());
        buf.writeInt(msg.x);
        buf.writeInt(msg.y);

        channel.writeInbound(buf.duplicate());

        TankMsg tankMsg=(TankMsg)  channel.readInbound();

        Assert.assertTrue(tankMsg.x==10&&tankMsg.y==10);

    }
}

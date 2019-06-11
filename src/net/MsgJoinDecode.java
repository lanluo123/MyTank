package net;

import com.TA.Dir;
import com.TA.Group;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;
import java.util.UUID;

/**
 * @author XuMinghao
 * @create 2019/6/9-0:05
 */
public class MsgJoinDecode extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        if (in.readableBytes()<33)  return;

        int x=in.readInt();
        int y=in.readInt();
        boolean moving = in.readBoolean();
        Group group=Group.values()[in.readInt()];
        Dir dir=Dir.values()[in.readInt()];
        UUID  id=new UUID(in.readLong(),in.readLong());
        TankJoinMsg msg=new TankJoinMsg(x,y,group,dir,moving,id);
        out.add(msg);
    }
}

package net;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @author XuMinghao
 * @create 2019/6/9-0:02
 */
public class MsgJoinEncode extends MessageToByteEncoder<TankJoinMsg> {

    @Override
    protected void encode(ChannelHandlerContext ctx, TankJoinMsg msg, ByteBuf out) throws Exception {
        out.writeBytes(msg.toBytes());

    }
}

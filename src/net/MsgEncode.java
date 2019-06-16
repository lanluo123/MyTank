package net;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @author XuMinghao
 * @create 2019/6/9-0:02
 */
public class MsgEncode extends MessageToByteEncoder<Msg> {

    @Override
    protected void encode(ChannelHandlerContext ctx, Msg msg, ByteBuf out) throws Exception {
        out.writeInt(msg.getMsgType().ordinal());
        System.out.println("发送信息"+msg.getMsgType());
        byte[] bytes=msg.toBytes();
        out.writeInt(bytes.length);
        out.writeBytes(bytes);
    }
}

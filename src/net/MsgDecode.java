package net;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import net.msg.TankChangeMsg;
import net.msg.TankJoinMsg;
import net.msg.TankStartMsg;
import net.msg.TankStopMsg;

import java.util.List;

/**
 * @author XuMinghao
 * @create 2019/6/9-0:05
 */
public class MsgDecode extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        if (in.readableBytes()<8)  return;
        in.markReaderIndex();
        int index =in.readInt();
        System.out.println("接受信息index"+index);
        MsgType type=MsgType.values()[index];
        int length=in.readInt();
        if (in.readableBytes()<length){
                in.resetReaderIndex();
                return;
        }
            byte[] bytes=new byte[length];
            in.readBytes(bytes);
            Msg msg=null;

            switch (type) {
                case TankJoin:
                    msg = new TankJoinMsg();
                    break;
                case TankStart:
                    msg = new TankStartMsg();
                    break;
                case TankStop:
                    msg = new TankStopMsg();
                    break;
                case TankDirChange:
                    msg = new TankChangeMsg();
                    break;
                default:
                    break;

            }
            msg.parse(bytes);

            out.add(msg);



    }
}

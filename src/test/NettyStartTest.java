package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.UUID;

import com.tank.Dir;
import net.*;
import net.msg.TankChangeMsg;
import net.msg.TankStartMsg;
import org.junit.jupiter.api.Test;


import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.embedded.EmbeddedChannel;

class NettyStartTest {

    @Test
    void testEncoder() {
        EmbeddedChannel ch = new EmbeddedChannel();


        UUID id = UUID.randomUUID();
        Msg msg = new TankStartMsg( 5, 10, Dir.LEFT,id);
        ch.pipeline()
                .addLast(new MsgEncode());

        ch.writeOutbound(msg);

        ByteBuf buf = (ByteBuf)ch.readOutbound();
        MsgType msgType = MsgType.values()[buf.readInt()];
        assertEquals(MsgType.TankStart, msgType);

        int length = buf.readInt();
        assertEquals(28, length);

        UUID uuid = new UUID(buf.readLong(), buf.readLong());
        int x = buf.readInt();
        int y = buf.readInt();
        int dirOrdinal = buf.readInt();
        Dir dir = Dir.values()[dirOrdinal];

        assertEquals(5, x);
        assertEquals(10, y);
        assertEquals(Dir.LEFT, dir);
        assertEquals(id, uuid);
    }

    @Test
    void testDecoder() {
        EmbeddedChannel ch = new EmbeddedChannel();


        UUID id = UUID.randomUUID();
        Msg msg = new TankChangeMsg( 5, 10, Dir.LEFT,id);
        ch.pipeline()
                .addLast(new MsgDecode());

        ByteBuf buf = Unpooled.buffer();
        buf.writeInt(MsgType.TankDirChange.ordinal());
        byte[] bytes = msg.toBytes();
        buf.writeInt(bytes.length);
        buf.writeBytes(bytes);

        ch.writeInbound(buf.duplicate());

        TankChangeMsg msgR = (TankChangeMsg)ch.readInbound();

        assertEquals(5, msgR.getX());
        assertEquals(10, msgR.getY());
        assertEquals(Dir.LEFT, msgR.getDir());
        assertEquals(id, msgR.getId());
    }


}

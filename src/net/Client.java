package net; /**
 * @author XuMinghao
 * @create 2019/6/5-22:09
 */

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.ReferenceCountUtil;

/********************Client***********************/
class Client{
    Channel channel=null;
    public Client() {

    }
    public void connect() {
        EventLoopGroup group=new NioEventLoopGroup(1) ;
        Bootstrap bootstrap=new Bootstrap();
            try{
                ChannelFuture f=bootstrap.group(group)
                        .channel(NioSocketChannel.class)
                        .handler(new ClientInitial())
                        .connect("localhost",5555);
                f.addListener(new ChannelFutureListener() {
                            @Override
                            public void operationComplete(ChannelFuture future) throws Exception {
                                if (future.isSuccess()) {
                                    System.out.println("server connected");
                                    channel=future.channel();
                                } else {
                                    System.out.println("server not connect");
                                }
                            }
                        });

                f.sync();
                System.out.println("....");
                f.channel().closeFuture().sync();
            }catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                group.shutdownGracefully();
            }


    }

    public void sendMsg(String msg){
        ByteBuf bf= Unpooled.copiedBuffer(msg.getBytes());
        if (channel!=null){
            channel.writeAndFlush(bf);
        }
    }

    public static void main(String[] args) {
    }
}

/*****************初始化******************/
class ClientInitial extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ch.pipeline().addLast(new MsgEncode())
                     .addLast(new ClientHandler());
    }
}


/*****************Handler******************/
class ClientHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf=null;
        try{
            buf= (ByteBuf) msg;
            byte[] bs=new byte[buf.readableBytes()];
            buf.getBytes(buf.readerIndex(),bs);
            ctx.writeAndFlush(bs);
        }finally {
            if (buf!=null)
                ReferenceCountUtil.release(buf);
        }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
//        ByteBuf bf= Unpooled.copiedBuffer("hello".getBytes());
        ctx.writeAndFlush(new TankMsg(2,3));
    }
}
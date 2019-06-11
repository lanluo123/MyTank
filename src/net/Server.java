package net;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.ReferenceCountUtil;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * @author xuminghao
 */
public class Server {

    public static ChannelGroup clients=new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    /********************************************/
    public void startServer() {
        EventLoopGroup  bossGroup=new NioEventLoopGroup(1);
        EventLoopGroup  workerGroup = new NioEventLoopGroup(2);
        try{
            ServerBootstrap  b=new ServerBootstrap();
            ChannelFuture f=b.group(workerGroup,bossGroup)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new MsgDecode()).addLast(new ServerChannelHandler());
                        }
                    })
                    .channel(NioServerSocketChannel.class)
                    .bind(5555);
            f.sync();
            System.out.println("server started");
            ServerFrame.INSTANCE.updateServerMsg("server started");

            f.channel().closeFuture().sync();
        }catch (Exception e){
            e.printStackTrace();
        } finally{
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    /*********************************************************/
    public static void main(String[] args) throws Exception {


    }
}

class ServerChannelHandler extends ChannelInboundHandlerAdapter{
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Server.clients.add(ctx.channel());
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("server  read.............");
        try {
            TankMsg tmg= (TankMsg) msg;
            System.out.println(tmg);
        }finally {
            ReferenceCountUtil.release(msg);
        }
       /* ByteBuf buf = null;
        try {
            buf = (ByteBuf) msg;
            byte[] bs = new byte[buf.readableBytes()];
            buf.getBytes(buf.readerIndex(),bs);
            String ss=new String(bs);
            ServerFrame.INSTANCE.updateClientMsg(ss);
            if (ss.equals("_bye_")){
                Server.clients.remove(ctx.channel());
                ctx.close();
            }
            System.out.println(ss);
            Server.clients.writeAndFlush(msg);

        } finally {
//            if (buf!=null)              ReferenceCountUtil.release(buf);
        }*/
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
      Server.clients.remove(ctx.channel());
        ctx.close();
    }
}


























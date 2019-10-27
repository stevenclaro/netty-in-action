package nia.chapter2.echoclient;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

/**
 * Listing 2.3 ChannelHandler for the client
 *
 * @author <a href="mailto:norman.maurer@gmail.com">Norman Maurer</a>
 */
@Sharable
public class EchoClientHandlerTestSequn
    extends SimpleChannelInboundHandler<ByteBuf> {
   /* @Override
    public void channelActive(ChannelHandlerContext ctx) {
       *//* ctx.writeAndFlush(Unpooled.copiedBuffer("Netty rocks!BySteven",
                CharsetUtil.UTF_8));
        System.out.println("当前激活的是"+this.getClass().getName());
        ctx.fireChannelActive();*//*
    }*/

    @Override
    public void channelRead0(ChannelHandlerContext ctx, ByteBuf in) {
        System.out.println(
                "Client receivedTest: " + in.toString(CharsetUtil.UTF_8)+this.getClass().getName());
       // ctx.fireChannelRead(in);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx,
        Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
    @Override
    public void channelInactive(ChannelHandlerContext ctx)
    {
        System.out.println(
                "该信道已经关闭");
    }
}

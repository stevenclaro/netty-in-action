package nia.chapter2.echoserver;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.Map;

/**
 * Listing 2.1 EchoServerHandler
 *
 * @author <a href="mailto:norman.maurer@gmail.com">Norman Maurer</a>
 */
@Sharable
public class EchoServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws UnsupportedEncodingException {
        ByteBuf in = (ByteBuf) msg;
        String content="服务器加了一些信息 Finished ";
        System.out.println("当前可读的字节数"+in.readableBytes());

        System.out.println(
                "Server received: " + in.toString(CharsetUtil.UTF_8));
        int length=content.getBytes("UTF-8").length;
        in.writeInt(length);//这个只能增加4位
        byte[] contentArray=content.getBytes("UTF-8");
        in.writeBytes(contentArray);//这个可以增加很多位
        System.out.println("当前可读的字节数"+in.readableBytes());


        ctx.write(in);

    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx)
            throws Exception {
        ctx.writeAndFlush(Unpooled.EMPTY_BUFFER);
              //  .addListener(ChannelFutureListener.CLOSE);
        //这个地方是关键点，如果有.addListener(ChannelFutureListener.CLOSE);那么将关闭该Channel。
        //如果没有.addListener(ChannelFutureListener.CLOSE);那么继续等待，客户端就不会收到close的信号。
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx,
        Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}

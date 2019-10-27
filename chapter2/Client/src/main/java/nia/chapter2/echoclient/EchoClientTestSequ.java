package nia.chapter2.echoclient;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;

/**
 * Listing 2.4 Main class for the client
 *
 * @author <a href="mailto:norman.maurer@gmail.com">Norman Maurer</a>
 */
public class EchoClientTestSequ {
    private final String host;
    private final int port;

    public EchoClientTestSequ(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void start()
        throws Exception {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group)
                .channel(NioSocketChannel.class)
                .remoteAddress(new InetSocketAddress(host, port))
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    public void initChannel(SocketChannel ch)
                        throws Exception {
                        ch.pipeline().addLast(
                                new EchoClientHandlerTestSequn());
                        ch.pipeline().addLast(
                                new EchoClientHandlerTestSequnSecond());
                        ch.pipeline().addLast(
                             new EchoClientHandler());
                    }
                });
            ChannelFuture f = b.connect().sync();
            f.channel().closeFuture().sync();//我认为在客户端connect所产生的channel应该在服务端连接结束之后，就会收到closeFutre的信息
            //服务端bind所产生的channel应该与客户端是否连接上没有关系，除非有明确的要求关闭的动作，否则不会主动关闭。
            //但是在ch12中，ReadTimeoutHandler5000，设置了，客户端就不会关闭。是否不设置这个，就默认关闭？
        } finally {
            group.shutdownGracefully().sync();
        }
    }

    public static void main(String[] args)
            throws Exception {
        /*if (args.length != 2) {
            System.err.println("Usage: " + EchoClient.class.getSimpleName() +
                    " <host> <port>"
            );
            return;
        }

        final String host = args[0];
        final int port = Integer.parseInt(args[1]);*/
        final String host = "127.0.0.1";
        final int port = 1008;
        new EchoClientTestSequ(host, port).start();
    }
}


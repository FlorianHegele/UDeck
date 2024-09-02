package net.fhegele.udeck.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import net.fhegele.udeck.protocol.Connection;
import net.fhegele.udeck.protocol.ConnectionProtocol;
import net.fhegele.udeck.protocol.packet.PacketFlow;
import net.fhegele.udeck.server.protocol.ServerboundPacketListenerImpl;

public class Server {

    private final int port;

    public Server(int port) {
        this.port = port;
    }

    public static void main(String[] args) throws InterruptedException {
        Server server = new Server(28868);
        server.start();
    }

    public void start() throws InterruptedException {
        try(final NioEventLoopGroup boss = new NioEventLoopGroup();
            final NioEventLoopGroup worker = new NioEventLoopGroup()) {

            new ServerBootstrap().group(boss, worker)
                    .channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) {
                            final ChannelPipeline pipeline = socketChannel.pipeline();

                            final PacketFlow flow = PacketFlow.SERVER_BOUND;
                            final Connection connection = new Connection(flow);

                            Connection.configureCodec(pipeline, flow);

                            Connection.setProtocolAttributes(pipeline.channel(), ConnectionProtocol.INIT);
                            pipeline.addLast(connection);

                            Connection.setPacketListener(connection, new ServerboundPacketListenerImpl(connection), pipeline.channel());
                        }
                    })
                    .bind(port)
                    .sync()
                    .channel().closeFuture().sync();
        }
    }
}

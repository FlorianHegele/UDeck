package net.fhegele.udeck.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import net.fhegele.udeck.protocol.Connection;
import net.fhegele.udeck.protocol.ConnectionProtocol;
import net.fhegele.udeck.protocol.packet.PacketFlow;

import java.io.IOException;

public class Client {

    private Connection connection;

    private final int port;
    private final String host;

    public Client(int port, String host) {
        this.port = port;
        this.host = host;
    }

    public static void main(String[] args) throws InterruptedException {
        final Client client = new Client(28868, "localhost");

        // trying to connect client to the server
        client.connect();

        System.out.println("end of program");
    }

    public void connect() throws InterruptedException {
        try (final NioEventLoopGroup group = new NioEventLoopGroup()) {
            new Bootstrap().group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) {
                            final ChannelPipeline pipeline = socketChannel.pipeline();

                            final PacketFlow flow = PacketFlow.CLIENT_BOUND;
                            Connection.configureCodec(pipeline, flow);
                            Connection.setProtocolAttributes(pipeline.channel(), ConnectionProtocol.HANDSHAKE);

                            connection = new Connection(flow);
                            pipeline.addLast(connection);
                        }
                    })
                    .connect(host, port).sync();

            final Keyboard keyboard = new Keyboard(connection);
            // connection.setPacketListener(new ClientPacketListenerImpl());

            try {
                keyboard.handle();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

}

package net.fhegele.udeck.protocol;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.AttributeKey;
import net.fhegele.udeck.protocol.packet.Packet;
import net.fhegele.udeck.protocol.packet.PacketFlow;
import net.fhegele.udeck.protocol.packet.PacketListener;

import java.net.SocketAddress;

public class Connection extends SimpleChannelInboundHandler<Packet<?>> {

    public static final AttributeKey<ConnectionProtocol.CodecData<?>> ATTRIBUTE_SERVER_RECEIVE_PROTOCOL = PacketFlow.SERVER_RECEIVE.getAttributeKey();
    public static final AttributeKey<ConnectionProtocol.CodecData<?>> ATTRIBUTE_CLIENT_RECEIVE_PROTOCOL = PacketFlow.CLIENT_RECEIVE.getAttributeKey();

    private final PacketFlow receiveFlow;
    private PacketListener packetListener;

    private Channel channel;
    private SocketAddress address;

    public Connection(PacketFlow receiveFlow) {
        this.receiveFlow = receiveFlow;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);

        channel = ctx.channel();
        address = channel.remoteAddress();

        System.out.println("New connection from " + address);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Packet<?> packet) {

    }

    public static void setInitProtocolAttributes(Channel channel) {
        channel.attr(ATTRIBUTE_SERVER_RECEIVE_PROTOCOL).set(ConnectionProtocol.INIT_CONNECTION.getCodecData(PacketFlow.SERVER_RECEIVE));
        channel.attr(ATTRIBUTE_CLIENT_RECEIVE_PROTOCOL).set(ConnectionProtocol.INIT_CONNECTION.getCodecData(PacketFlow.CLIENT_RECEIVE));
    }

    public boolean isConnected() {
        return channel != null && channel.isActive();
    }

    public void disconnect() {
        if(isConnected())
            channel.close().awaitUninterruptibly();
    }
}

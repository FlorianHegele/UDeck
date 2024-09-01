package net.fhegele.udeck.protocol;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import net.fhegele.udeck.protocol.packet.Packet;
import net.fhegele.udeck.protocol.packet.PacketFlow;
import net.fhegele.udeck.protocol.packet.PacketListener;
import org.jetbrains.annotations.NotNull;

import java.net.SocketAddress;

public class Connection extends SimpleChannelInboundHandler<Packet<?>> {

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
        if(packetListener == null) throw new IllegalStateException("Received a packet before the initialization of the packet listener");

        try {
            handlePacket(packet, packetListener);
        } catch (ClassCastException castException) {
            throw new IllegalStateException("Packet (" + packet.getClass().getSimpleName() + ") handled on a wrong packet listener (" + packetListener.getClass().getSimpleName() + ")");
        }
    }

    @SuppressWarnings("unchecked")
    private static <T extends PacketListener> void handlePacket(Packet<T> packet, @NotNull PacketListener packetListener) {
        packet.handle((T) packetListener);
    }

    public void setPacketListener(PacketListener packetListener) {
        if(packetListener == null) throw new IllegalArgumentException("packetListener must not be null");

        final PacketFlow flow = packetListener.getFlow();
        if(flow != receiveFlow) throw new IllegalArgumentException("Trying to set a packet listener with opposite flow, the current flow is " + receiveFlow + " but you are trying to set the flow " + flow);

        final ConnectionProtocol protocol = packetListener.getProtocol();
        final ConnectionProtocol usedProtocol = channel.attr(flow.getProtocolKey()).get().getProtocol();
        if(usedProtocol != protocol) throw new IllegalArgumentException("Trying to set a packet listener with other protocol, the current protocol is " + usedProtocol.name() + " but you are trying to set the protocol " + protocol.name());

        this.packetListener = packetListener;
    }

    public static void setProtocolAttributes(Channel channel, ConnectionProtocol protocol) {
        channel.attr(PacketFlow.SERVER_RECEIVE.getProtocolKey()).set(protocol.getCodecData(PacketFlow.SERVER_RECEIVE));
        channel.attr(PacketFlow.CLIENT_RECEIVE.getProtocolKey()).set(protocol.getCodecData(PacketFlow.CLIENT_RECEIVE));
    }

    public boolean isConnected() {
        return channel != null && channel.isActive();
    }

    public void disconnect() {
        if(isConnected())
            channel.close().awaitUninterruptibly();
    }
}

package net.fhegele.udeck.protocol.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import io.netty.util.Attribute;
import io.netty.util.AttributeKey;
import net.fhegele.udeck.protocol.ConnectionProtocol;
import net.fhegele.udeck.protocol.packet.Packet;
import net.fhegele.udeck.protocol.packet.PacketFlow;

public class PacketEncoder extends MessageToByteEncoder<Packet<?>> {

    private final AttributeKey<ConnectionProtocol.CodecData<?>> protocolKey;

    public PacketEncoder(PacketFlow sendFlow) {
        this.protocolKey = sendFlow.getProtocolKey();
    }

    @Override
    protected void encode(ChannelHandlerContext ctx, Packet<?> packet, ByteBuf out) {
        final Attribute<ConnectionProtocol.CodecData<?>> attribute = ctx.channel().attr(protocolKey);
        final ConnectionProtocol.CodecData<?> codecData = attribute.get();

        if(codecData == null) throw new IllegalCallerException("No packet should be sent unless there is codecdata");

        final int packetId = codecData.packetId(packet);
        if(packetId == -1) throw new IllegalArgumentException("Can't serialize unregistered packet (" + packet.getClass().getSimpleName() + ")");

        out.writeShort(packetId); // 2 bytes
        packet.write(out);

        ConnectionProtocol.CodecData.updateProtocolIfNeeded(attribute, packet);
    }
}

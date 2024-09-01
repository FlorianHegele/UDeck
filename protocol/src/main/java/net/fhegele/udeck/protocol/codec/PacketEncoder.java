package net.fhegele.udeck.protocol.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import io.netty.util.AttributeKey;
import net.fhegele.udeck.protocol.ConnectionProtocol;
import net.fhegele.udeck.protocol.packet.Packet;
import net.fhegele.udeck.protocol.packet.PacketFlow;

public class PacketEncoder extends MessageToByteEncoder<Packet<?>> {

    private final AttributeKey<ConnectionProtocol.CodecData<?>> protocolKey;

    public PacketEncoder(PacketFlow receivedFlow) {
        this.protocolKey = receivedFlow.getProtocolKey();
    }

    @Override
    protected void encode(ChannelHandlerContext ctx, Packet<?> packet, ByteBuf out) {
        final int packetId = ctx.channel().attr(protocolKey).get().packetId(packet);;
        out.writeShort(packetId); // 2 bytes
        packet.write(out);
    }
}

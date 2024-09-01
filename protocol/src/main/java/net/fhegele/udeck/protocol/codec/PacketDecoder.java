package net.fhegele.udeck.protocol.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.util.AttributeKey;
import net.fhegele.udeck.protocol.ConnectionProtocol;
import net.fhegele.udeck.protocol.packet.BadPacketException;
import net.fhegele.udeck.protocol.packet.Packet;
import net.fhegele.udeck.protocol.packet.PacketFlow;

import java.util.List;

public class PacketDecoder extends ByteToMessageDecoder {

    private final AttributeKey<ConnectionProtocol.CodecData<?>> protocolKey;

    public PacketDecoder(PacketFlow receivedFlow) {
        this.protocolKey = receivedFlow.getProtocolKey();
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) {
        final int packetId = in.readUnsignedShort();
        final Packet<?> packet = ctx.channel().attr(protocolKey).get().createPacket(packetId, in);

        if(packet == null) throw new BadPacketException("Packet with ID" + packetId + " doesn't exist");

        out.add(packet);
    }
}

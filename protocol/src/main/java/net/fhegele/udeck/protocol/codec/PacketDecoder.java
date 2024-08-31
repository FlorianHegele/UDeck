package net.fhegele.udeck.protocol.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import net.fhegele.udeck.protocol.packet.BadPacketException;
import net.fhegele.udeck.protocol.packet.Packet;

import java.util.List;

public class PacketDecoder extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) {
        final int packetId = in.readUnsignedShort();
        final Packet<?> packet = null;

        if(packet == null) throw new BadPacketException("Packet with ID" + packetId + " doesn't exist");

        out.add(packet);
    }
}

package net.fhegele.udeck.protocol.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import net.fhegele.udeck.protocol.packet.Packet;

public class PacketEncoder extends MessageToByteEncoder<Packet<?>> {

    @Override
    protected void encode(ChannelHandlerContext ctx, Packet<?> packet, ByteBuf out) {
        final int id = 0;
        out.writeShort(id); // 2 bytes
        packet.write(out);
    }
}

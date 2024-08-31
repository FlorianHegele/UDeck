package net.fhegele.udeck.protocol.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import net.fhegele.udeck.protocol.ConnectionProtocol;

public class FramedPacketEncoder extends MessageToByteEncoder<ByteBuf> {

    @Override
    protected void encode(ChannelHandlerContext ctx, ByteBuf msg, ByteBuf out) {
        final int packetLength = msg.readableBytes();
        final int packetDataLength = packetLength - ConnectionProtocol.CodecData.PACKET_ID_BYTE_LENGTH;

        // INT (4 bytes) + Packet Length (Packet ID : 2 bytes, Packet Data : X bytes)
        out.ensureWritable(4 + packetLength);

        out.writeInt(packetDataLength);
        out.writeBytes(msg);
    }
}

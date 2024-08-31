package net.fhegele.udeck.protocol.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import net.fhegele.udeck.protocol.ConnectionProtocol;

import java.util.List;

public class FramedPacketDecoder extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) {
        if(in.readableBytes() < ConnectionProtocol.CodecData.PACKET_HEAD_BYTE_LENGTH) return;
        in.markReaderIndex();

        final int packetDataLength = in.readInt();
        final int packetLength = packetDataLength + ConnectionProtocol.CodecData.PACKET_ID_BYTE_LENGTH;
        if(in.readableBytes() < packetLength) {
            in.resetReaderIndex();
            return;
        }

        final ByteBuf packet = in.readSlice(packetLength);
        out.add(packet);
    }

}

package net.fhegele.udeck.protocol.packet.init;

import io.netty.buffer.ByteBuf;
import net.fhegele.udeck.protocol.packet.ClientboundPacketListener;
import net.fhegele.udeck.protocol.packet.Packet;
import net.fhegele.udeck.protocol.packet.ServerboundPacketListener;

public class PongPacket implements Packet<ClientboundPacketListener> {

    private final long timestamp;

    public PongPacket(long timestamp) {
        this.timestamp = timestamp;
    }

    public PongPacket(ByteBuf buf) {
        timestamp = buf.readLong();
    }

    @Override
    public void write(ByteBuf buf) {
        buf.writeLong(timestamp);
    }

    @Override
    public void handle(ClientboundPacketListener listener) {
        listener.handlePong(this);
    }

    public long getTime() {
        return timestamp;
    }
}

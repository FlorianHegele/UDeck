package net.fhegele.udeck.protocol.packet.handshake;

import net.fhegele.udeck.protocol.SimpleByteBuf;
import net.fhegele.udeck.protocol.packet.ClientboundPacketListener;
import net.fhegele.udeck.protocol.packet.Packet;

public class PongPacketClientBound implements Packet<ClientboundPacketListener> {

    private final long timestamp;

    public PongPacketClientBound(long timestamp) {
        this.timestamp = timestamp;
    }

    public PongPacketClientBound(SimpleByteBuf buf) {
        timestamp = buf.readLong();
    }

    @Override
    public void write(SimpleByteBuf buf) {
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

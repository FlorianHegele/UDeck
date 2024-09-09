package net.fhegele.udeck.protocol.packet.handshake;

import net.fhegele.udeck.protocol.SimpleByteBuf;
import net.fhegele.udeck.protocol.packet.ClientboundPacketListener;
import net.fhegele.udeck.protocol.packet.Packet;

public class PongPacket implements Packet<ClientboundPacketListener> {

    private final long timestamp;

    public PongPacket(long timestamp) {
        this.timestamp = timestamp;
    }

    public PongPacket(SimpleByteBuf buf) {
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

package net.fhegele.udeck.protocol.packet.handshake;

import net.fhegele.udeck.protocol.SimpleByteBuf;
import net.fhegele.udeck.protocol.packet.Packet;
import net.fhegele.udeck.protocol.packet.ServerboundPacketListener;

public class PingPacketServerBound implements Packet<ServerboundPacketListener> {

    private final long timestamp;

    public PingPacketServerBound() {
        timestamp = System.currentTimeMillis();
    }

    public PingPacketServerBound(SimpleByteBuf buf) {
        timestamp = buf.readLong();
    }

    @Override
    public void write(SimpleByteBuf buf) {
        buf.writeLong(timestamp);
    }

    @Override
    public void handle(ServerboundPacketListener listener) {
        listener.handlePing(this);
    }

    public long getTimestamp() {
        return timestamp;
    }
}

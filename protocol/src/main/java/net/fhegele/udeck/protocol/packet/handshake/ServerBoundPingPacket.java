package net.fhegele.udeck.protocol.packet.handshake;

import net.fhegele.udeck.protocol.SimpleByteBuf;
import net.fhegele.udeck.protocol.packet.Packet;
import net.fhegele.udeck.protocol.packet.ServerboundPacketListener;

public class ServerBoundPingPacket implements Packet<ServerboundPacketListener> {

    private final long timestamp;

    public ServerBoundPingPacket() {
        timestamp = System.currentTimeMillis();
    }

    public ServerBoundPingPacket(SimpleByteBuf buf) {
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

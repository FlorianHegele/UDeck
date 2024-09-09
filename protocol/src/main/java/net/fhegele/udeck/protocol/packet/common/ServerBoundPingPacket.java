package net.fhegele.udeck.protocol.packet.common;

import net.fhegele.udeck.protocol.SimpleByteBuf;
import net.fhegele.udeck.protocol.packet.Packet;

public class ServerBoundPingPacket implements Packet<ServerPingPacketListener> {

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
    public void handle(ServerPingPacketListener listener) {
        listener.handlePing(this);
    }

    public long getTimestamp() {
        return timestamp;
    }
}

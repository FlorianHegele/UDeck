package net.fhegele.udeck.protocol.packet.common;

import net.fhegele.udeck.protocol.SimpleByteBuf;
import net.fhegele.udeck.protocol.packet.Packet;

public class ClientBoundPongPacket implements Packet<ClientPongPacketListener> {

    private final long timestamp;

    public ClientBoundPongPacket(long timestamp) {
        this.timestamp = timestamp;
    }

    public ClientBoundPongPacket(SimpleByteBuf buf) {
        timestamp = buf.readLong();
    }

    @Override
    public void write(SimpleByteBuf buf) {
        buf.writeLong(timestamp);
    }

    @Override
    public void handle(ClientPongPacketListener listener) {
        listener.handlePong(this);
    }

    public long getTime() {
        return timestamp;
    }
}

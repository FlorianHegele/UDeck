package net.fhegele.udeck.protocol.packet.init;

import io.netty.buffer.ByteBuf;
import net.fhegele.udeck.protocol.packet.Packet;
import net.fhegele.udeck.protocol.packet.ServerboundPacketListener;

public class PingPacket implements Packet<ServerboundPacketListener> {

    private final long timestamp;

    public PingPacket() {
        timestamp = System.currentTimeMillis();
    }

    public PingPacket(ByteBuf buf) {
        timestamp = buf.readLong();
    }

    @Override
    public void write(ByteBuf buf) {
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

package net.fhegele.udeck.protocol.packet;

import net.fhegele.udeck.protocol.packet.handshake.PingPacket;

public interface ServerboundPacketListener extends PacketListener {

    @Override
    default PacketFlow getFlow() {
        return PacketFlow.SERVER_BOUND;
    }

    void handlePing(PingPacket packet);
}

package net.fhegele.udeck.protocol.packet;

import net.fhegele.udeck.protocol.packet.handshake.ServerBoundPingPacket;

public interface ServerboundPacketListener extends PacketListener {

    @Override
    default PacketFlow getFlow() {
        return PacketFlow.SERVER_BOUND;
    }

    void handlePing(ServerBoundPingPacket packet);
}

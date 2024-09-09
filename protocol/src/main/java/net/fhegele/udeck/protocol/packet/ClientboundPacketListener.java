package net.fhegele.udeck.protocol.packet;

import net.fhegele.udeck.protocol.packet.handshake.PongPacket;

public interface ClientboundPacketListener extends PacketListener {

    @Override
    default PacketFlow getFlow() {
        return PacketFlow.CLIENT_BOUND;
    }


    void handlePong(PongPacket pongPacket);
}

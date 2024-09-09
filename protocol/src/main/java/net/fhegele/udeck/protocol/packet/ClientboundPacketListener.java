package net.fhegele.udeck.protocol.packet;

import net.fhegele.udeck.protocol.packet.handshake.ClientBoundPongPacket;

public interface ClientboundPacketListener extends PacketListener {

    @Override
    default PacketFlow getFlow() {
        return PacketFlow.CLIENT_BOUND;
    }


    void handlePong(ClientBoundPongPacket pongPacket);
}

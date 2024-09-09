package net.fhegele.udeck.protocol.packet;

public interface ClientBoundPacketListener extends PacketListener {

    @Override
    default PacketFlow getFlow() {
        return PacketFlow.CLIENT_BOUND;
    }

}

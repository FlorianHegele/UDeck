package net.fhegele.udeck.protocol.packet;

public interface ClientboundPacketListener extends PacketListener {

    @Override
    default PacketFlow getFlow() {
        return PacketFlow.CLIENT_BOUND;
    }
}

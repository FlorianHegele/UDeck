package net.fhegele.udeck.protocol.packet;

public interface ServerboundPacketListener extends PacketListener {

    @Override
    default PacketFlow getFlow() {
        return PacketFlow.SERVER_BOUND;
    }
}

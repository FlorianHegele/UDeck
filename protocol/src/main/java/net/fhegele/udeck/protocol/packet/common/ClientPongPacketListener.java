package net.fhegele.udeck.protocol.packet.common;

import net.fhegele.udeck.protocol.packet.PacketListener;

public interface ClientPongPacketListener extends PacketListener {

    void handlePong(ClientBoundPongPacket packet);

}

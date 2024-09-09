package net.fhegele.udeck.protocol.packet.common;

import net.fhegele.udeck.protocol.packet.PacketListener;

public interface ServerPingPacketListener extends PacketListener {

    void handlePing(ServerBoundPingPacket packet);

}

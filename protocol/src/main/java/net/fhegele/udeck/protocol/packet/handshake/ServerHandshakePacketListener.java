package net.fhegele.udeck.protocol.packet.handshake;

import net.fhegele.udeck.protocol.ConnectionProtocol;
import net.fhegele.udeck.protocol.packet.ServerBoundPacketListener;

public interface ServerHandshakePacketListener extends ServerBoundPacketListener {

    default ConnectionProtocol getProtocol() {
        return ConnectionProtocol.HANDSHAKE;
    }

    void handleIntention(ServerBoundClientIntentionPacket serverBoundClientIntentionPacket);
}

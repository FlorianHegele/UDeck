package net.fhegele.udeck.protocol.packet.status;

import net.fhegele.udeck.protocol.ConnectionProtocol;
import net.fhegele.udeck.protocol.packet.ServerBoundPacketListener;
import net.fhegele.udeck.protocol.packet.common.ServerPingPacketListener;

public interface ServerStatusPacketListener extends ServerBoundPacketListener, ServerPingPacketListener {

    default ConnectionProtocol getProtocol() {
        return ConnectionProtocol.STATUS;
    }

}

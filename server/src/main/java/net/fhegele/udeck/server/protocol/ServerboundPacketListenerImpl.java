package net.fhegele.udeck.server.protocol;

import net.fhegele.udeck.protocol.Connection;
import net.fhegele.udeck.protocol.ConnectionProtocol;
import net.fhegele.udeck.protocol.packet.ServerboundPacketListener;
import net.fhegele.udeck.protocol.packet.handshake.ServerBoundPingPacket;
import net.fhegele.udeck.protocol.packet.handshake.ClientBoundPongPacket;

public class ServerboundPacketListenerImpl implements ServerboundPacketListener {

    private final Connection connection;

    public ServerboundPacketListenerImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void handlePing(ServerBoundPingPacket packet) {
        final ClientBoundPongPacket pongPacket = new ClientBoundPongPacket(packet.getTimestamp());
        connection.sendPacket(pongPacket);
    }

    @Override
    public ConnectionProtocol getProtocol() {
        return ConnectionProtocol.HANDSHAKE;
    }
}

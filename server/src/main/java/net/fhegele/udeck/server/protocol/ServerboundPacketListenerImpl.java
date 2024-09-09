package net.fhegele.udeck.server.protocol;

import net.fhegele.udeck.protocol.Connection;
import net.fhegele.udeck.protocol.ConnectionProtocol;
import net.fhegele.udeck.protocol.packet.ServerboundPacketListener;
import net.fhegele.udeck.protocol.packet.handshake.PingPacket;
import net.fhegele.udeck.protocol.packet.handshake.PongPacket;

public class ServerboundPacketListenerImpl implements ServerboundPacketListener {

    private final Connection connection;

    public ServerboundPacketListenerImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void handlePing(PingPacket packet) {
        final PongPacket pongPacket = new PongPacket(packet.getTimestamp());
        connection.sendPacket(pongPacket);
    }

    @Override
    public ConnectionProtocol getProtocol() {
        return ConnectionProtocol.HANDSHAKE;
    }
}

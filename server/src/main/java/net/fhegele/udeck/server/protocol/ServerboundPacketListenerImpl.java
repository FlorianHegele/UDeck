package net.fhegele.udeck.server.protocol;

import net.fhegele.udeck.protocol.Connection;
import net.fhegele.udeck.protocol.ConnectionProtocol;
import net.fhegele.udeck.protocol.packet.ServerboundPacketListener;
import net.fhegele.udeck.protocol.packet.handshake.PingPacketServerBound;
import net.fhegele.udeck.protocol.packet.handshake.PongPacketClientBound;

public class ServerboundPacketListenerImpl implements ServerboundPacketListener {

    private final Connection connection;

    public ServerboundPacketListenerImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void handlePing(PingPacketServerBound packet) {
        final PongPacketClientBound pongPacket = new PongPacketClientBound(packet.getTimestamp());
        connection.sendPacket(pongPacket);
    }

    @Override
    public ConnectionProtocol getProtocol() {
        return ConnectionProtocol.HANDSHAKE;
    }
}

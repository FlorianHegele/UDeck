package net.fhegele.udeck.protocol.packet.handshake;

import net.fhegele.udeck.protocol.ConnectionProtocol;
import net.fhegele.udeck.protocol.SimpleByteBuf;
import net.fhegele.udeck.protocol.packet.Packet;

public class ServerBoundClientIntentionPacket implements Packet<ServerHandshakePacketListener> {

    private final int clientVersion;
    private final ClientIntention clientIntention;

    public ServerBoundClientIntentionPacket(int clientVersion, ClientIntention clientIntention) {
        this.clientVersion = clientVersion;
        this.clientIntention = clientIntention;
    }

    public ServerBoundClientIntentionPacket(SimpleByteBuf buf) {
        this.clientVersion = buf.readInt();
        this.clientIntention = buf.readEnum(ClientIntention.class);
    }

    @Override
    public void write(SimpleByteBuf buf) {
        buf.writeInt(clientVersion);
        buf.writeEnum(clientIntention);
    }

    @Override
    public void handle(ServerHandshakePacketListener listener) {
        listener.handleIntention(this);
    }

    public ConnectionProtocol getNextProtocol() {
        return clientIntention.getProtocol();
    }
}

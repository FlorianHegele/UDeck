package net.fhegele.udeck.protocol.packet.handshake;

import net.fhegele.udeck.protocol.ConnectionProtocol;

public enum ClientIntention {

    LOGIN(ConnectionProtocol.LOGIN),
    STATUS(ConnectionProtocol.STATUS);

    private final ConnectionProtocol protocol;

    ClientIntention(ConnectionProtocol protocol) {
        this.protocol = protocol;
    }

    public ConnectionProtocol getProtocol() {
        return protocol;
    }

}

package net.fhegele.udeck.protocol.packet;

import net.fhegele.udeck.protocol.ConnectionProtocol;

public interface PacketListener {

    PacketFlow getFlow();

    ConnectionProtocol getProtocol();

}

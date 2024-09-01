package net.fhegele.udeck.protocol.packet;

import io.netty.util.AttributeKey;
import net.fhegele.udeck.protocol.ConnectionProtocol;

public enum PacketFlow {

    CLIENT_BOUND, // Client receives this packet type
    SERVER_BOUND; // Server receives this packet type

    private final AttributeKey<ConnectionProtocol.CodecData<?>> attributeKey;

    PacketFlow() {
        this.attributeKey = AttributeKey.valueOf(name().toLowerCase());
    }

    public PacketFlow getOpposite() {
        return this == CLIENT_BOUND ? SERVER_BOUND : CLIENT_BOUND;
    }

    public AttributeKey<ConnectionProtocol.CodecData<?>> getProtocolKey() {
        return attributeKey;
    }

}

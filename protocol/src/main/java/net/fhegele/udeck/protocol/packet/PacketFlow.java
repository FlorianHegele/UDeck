package net.fhegele.udeck.protocol.packet;

import io.netty.util.AttributeKey;
import net.fhegele.udeck.protocol.ConnectionProtocol;

public enum PacketFlow {

    CLIENT_RECEIVE, SERVER_RECEIVE;

    private final AttributeKey<ConnectionProtocol.CodecData<?>> attributeKey;

    PacketFlow() {
        this.attributeKey = AttributeKey.valueOf(name().toLowerCase());
    }

    public PacketFlow getOpposite() {
        return this == CLIENT_RECEIVE ? SERVER_RECEIVE : CLIENT_RECEIVE;
    }

    public AttributeKey<ConnectionProtocol.CodecData<?>> getProtocolKey() {
        return attributeKey;
    }

}

package net.fhegele.udeck.protocol.packet;

import io.netty.util.AttributeKey;
import net.fhegele.udeck.protocol.ConnectionProtocol;

public enum PacketFlow {

    CLIENT_RECEIVE, SERVER_RECEIVE;

    public PacketFlow getOpposite() {
        return this == CLIENT_RECEIVE ? SERVER_RECEIVE : CLIENT_RECEIVE;
    }

    public AttributeKey<ConnectionProtocol.CodecData<?>> getAttributeKey() {
        return AttributeKey.valueOf(name().toLowerCase());
    }
}

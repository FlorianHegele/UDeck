package net.fhegele.udeck.protocol.packet;

import net.fhegele.udeck.protocol.ConnectionProtocol;
import net.fhegele.udeck.protocol.SimpleByteBuf;
import org.jetbrains.annotations.Nullable;

public interface Packet<T extends PacketListener> {

    void write(SimpleByteBuf buf);

    void handle(T listener);

    @Nullable
    default ConnectionProtocol nextProtocol() {
        return null;
    }
}

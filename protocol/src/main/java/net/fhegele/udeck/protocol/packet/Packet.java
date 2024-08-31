package net.fhegele.udeck.protocol.packet;

import io.netty.buffer.ByteBuf;
import net.fhegele.udeck.protocol.ConnectionProtocol;
import org.jetbrains.annotations.Nullable;

public interface Packet<T extends PacketListener> {

    void write(ByteBuf buf);

    void handle(T listener);

    @Nullable
    default ConnectionProtocol nextProtocol() {
        return null;
    }
}

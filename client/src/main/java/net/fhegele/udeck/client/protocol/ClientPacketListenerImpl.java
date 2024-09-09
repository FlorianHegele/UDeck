package net.fhegele.udeck.client.protocol;

import net.fhegele.udeck.logging.LogUtils;
import net.fhegele.udeck.protocol.ConnectionProtocol;
import net.fhegele.udeck.protocol.packet.ClientboundPacketListener;
import net.fhegele.udeck.protocol.packet.handshake.ClientBoundPongPacket;
import org.slf4j.Logger;

public class ClientPacketListenerImpl implements ClientboundPacketListener {

    private static final Logger LOGGER = LogUtils.getLogger();

    @Override
    public void handlePong(ClientBoundPongPacket pongPacket) {
        final long time = System.currentTimeMillis() - pongPacket.getTime();
        LOGGER.info("ping : {} ms", time);
    }
    
    @Override
    public ConnectionProtocol getProtocol() {
        return ConnectionProtocol.HANDSHAKE;
    }

}

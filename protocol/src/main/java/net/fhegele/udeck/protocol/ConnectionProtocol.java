package net.fhegele.udeck.protocol;

import io.netty.buffer.ByteBuf;
import io.netty.util.Attribute;
import net.fhegele.udeck.protocol.packet.*;
import net.fhegele.udeck.protocol.packet.init.PingPacket;
import net.fhegele.udeck.protocol.packet.init.PongPacket;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.function.Function;

public enum ConnectionProtocol {

    INIT(initProtocol()
            .addFlow(PacketFlow.CLIENT_BOUND, new PacketSet<ClientboundPacketListener>().add(PongPacket.class, PongPacket::new))
            .addFlow(PacketFlow.SERVER_BOUND, new PacketSet<ServerboundPacketListener>().add(PingPacket.class, PingPacket::new))
    ),
    LINKED(initProtocol());

    private final Map<PacketFlow, CodecData<?>> packetsFlow;

    ConnectionProtocol(ProtocolBuilder protocolBuilder) {
        this.packetsFlow = protocolBuilder.buildCodecData(this);
    }

    public CodecData<?> getCodecData(PacketFlow packetFlow) {
        return packetsFlow.get(packetFlow);
    }

    private static ProtocolBuilder initProtocol() {
        return new ProtocolBuilder();
    }

    public static class ProtocolBuilder {

        private final Map<PacketFlow, PacketSet<?>> packetsFlow = new EnumMap<>(PacketFlow.class);

        private  <T extends PacketListener> ProtocolBuilder addFlow(PacketFlow packetFlow, PacketSet<T> packets) {
            packetsFlow.put(packetFlow, packets);
            return this;
        }

        public Map<PacketFlow, CodecData<?>> buildCodecData(ConnectionProtocol connectionProtocol) {
            final Map<PacketFlow, CodecData<?>> codecDatas = new EnumMap<>(PacketFlow.class);

            for(PacketFlow flow : PacketFlow.values()) {
                final PacketSet<?> packetSet = packetsFlow.getOrDefault(flow, new PacketSet<>());

                codecDatas.put(flow, new CodecData<>(connectionProtocol, flow, packetSet));
            }

            return codecDatas;
        }
    }

    public static class CodecData<T extends PacketListener> {

        public static final int PACKET_ID_BYTE_LENGTH = 2;
        public static final int PACKET_HEAD_BYTE_LENGTH = 4 + PACKET_ID_BYTE_LENGTH;

        private final ConnectionProtocol connectionProtocol;
        private final PacketFlow flow;
        private final PacketSet<T> packetSet;

        private CodecData(ConnectionProtocol connectionProtocol, PacketFlow flow, PacketSet<T> packetSet) {
            this.connectionProtocol = connectionProtocol;
            this.flow = flow;
            this.packetSet = packetSet;
        }

        public ConnectionProtocol getProtocol() {
            return connectionProtocol;
        }

        public PacketFlow getFlow() {
            return flow;
        }

        public int packetId(Packet<?> packet) {
            return packetSet.getId(packet.getClass());
        }

        public boolean packetExists(Packet<?> packet) {
            return packetSet.contains(packet.getClass());
        }

        @Nullable
        public Packet<?> createPacket(int packetId, ByteBuf packetData) {
            return packetSet.createPacket(packetId, packetData);
        }

        public static void updateProtocolIfNeeded(Attribute<CodecData<?>> attribute, Packet<?> packet) {
            final ConnectionProtocol newProtocol = packet.nextProtocol();
            if(newProtocol == null) return;

            final CodecData<?> actualCodecData = attribute.get();
            if(actualCodecData.connectionProtocol == newProtocol) return;

            attribute.set(newProtocol.getCodecData(actualCodecData.flow));
        }
    }

    private static class PacketSet<T extends PacketListener> {

        private final Map<Class<? extends Packet<? super T>>, Integer> packetClassToId = new HashMap<>();
        private final List<Function<ByteBuf, ? extends Packet<? super T>>> idToPacket = new ArrayList<>();

        public <P extends Packet<? super T>> PacketSet<T> add(Class<P> clazz, Function<ByteBuf, P> instanceFunction) {
            if(packetClassToId.containsKey(clazz))
                throw new IllegalArgumentException("Packet class " + clazz.getName() + " is already registered with id " + packetClassToId.get(clazz));

            final int id = idToPacket.size();
            packetClassToId.put(clazz, id);
            idToPacket.add(instanceFunction);

            return this;
        }

        public int getId(Class<?> clazz) {
            return packetClassToId.getOrDefault(clazz, -1);
        }

        public boolean contains(Class<?> clazz) {
            return packetClassToId.containsKey(clazz);
        }

        @Nullable
        public Packet<?> createPacket(int packetId, ByteBuf packetData) {
            final Function<ByteBuf, ? extends Packet<? super T>> instanceFunction = idToPacket.get(packetId);

            return (instanceFunction == null) ? null : instanceFunction.apply(packetData);
        }
    }

}

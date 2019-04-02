package ch.lebois.client.service;

import ch.lebois.client.console.Console;
import ch.lebois.client.handler.ResponseSender;

/**
 * @author Wetwer
 * @project server-control
 **/

public class KillService {

    public void kill() {
        Console.execute("taskkill /f /im explorer.exe");
        new ResponseSender("response", "Explorer Killed");
    }
}

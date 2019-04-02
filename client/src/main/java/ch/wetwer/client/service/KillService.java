package ch.wetwer.client.service;

import ch.wetwer.client.console.Console;
import ch.wetwer.client.handler.ResponseSender;

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

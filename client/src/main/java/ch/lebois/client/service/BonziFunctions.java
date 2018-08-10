package ch.lebois.client.service;

import ch.lebois.client.console.Console;
import ch.lebois.client.handler.ResponseSender;

import java.awt.*;
import java.awt.event.KeyEvent;

public class BonziFunctions {

    public void killExplorer() {
        Console.execute("taskkill /f /im explorer.exe");
        new ResponseSender("response", "Explorer Killed");
    }

    public void showDesktop() {
        try {
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_WINDOWS);
            robot.keyPress(KeyEvent.VK_D);
            robot.keyRelease(KeyEvent.VK_D);
            robot.keyRelease(KeyEvent.VK_WINDOWS);
            new ResponseSender("response", "Desktop shown");
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }
}

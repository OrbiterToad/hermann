package ch.wetwer.client.service;

import ch.wetwer.client.handler.ResponseSender;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * @author Wetwer
 * @project server-control
 **/

public class DesktopService {

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

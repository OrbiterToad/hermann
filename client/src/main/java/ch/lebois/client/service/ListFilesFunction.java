package ch.lebois.client.service;

import ch.lebois.client.handler.ResponseSender;

import java.io.File;
import java.util.ArrayList;

/**
 * Project: Hermann
 **/
public class ListFilesFunction {

    private static ArrayList<String> ls(String path) {
        if (path.equals("")) {
            path = System.getProperty("user.dir");
        }
        File file = new File(path);
        try {
            ArrayList<String> files = new ArrayList<>();
            for (File subFile : file.listFiles()) {
                files.add(subFile.getName());
            }
            return files;
        } catch (NullPointerException e) {

            return null;
        }
    }

    public void listFiles(String command) {
        try {
            for (String s : ls(command.substring(3))) {
                new ResponseSender("response", s);
            }
            new ResponseSender().reset();
        } catch (StringIndexOutOfBoundsException e) {
            for (String s : ls("")) {
                new ResponseSender("response", s);
            }
            new ResponseSender().reset();
        } catch (NullPointerException e1) {
            new ResponseSender("error", "Wrong Syntax with '" + command + "'");
            new ResponseSender().reset();
        }
    }

}

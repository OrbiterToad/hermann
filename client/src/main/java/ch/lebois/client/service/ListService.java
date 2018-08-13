package ch.lebois.client.service;

import ch.lebois.client.handler.ResponseSender;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Project: Hermann
 **/
public class ListService {

    private static ArrayList<String> ls(String path) {
        if (path.equals("")) {
            path = System.getProperty("user.dir");
        }
        File file = new File(path);
        try {
            ArrayList<String> files = new ArrayList<>();
            for (File subFile : file.listFiles()) {
                if (subFile.isDirectory()) {
                    files.add(subFile.getName() + "/");
                } else {
                    files.add(subFile.getName());
                }
            }

            Collections.reverse(files);
            return files;
        } catch (NullPointerException e) {
            e.printStackTrace();
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

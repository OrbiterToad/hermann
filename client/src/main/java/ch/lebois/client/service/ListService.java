package ch.lebois.client.service;

import ch.lebois.client.handler.ResponseSender;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

/**
 * @author Wetwer
 * @project server-control
 **/

public class ListService {

    private ArrayList<String> ls(String path) {
        if (path.equals("")) {
            path = System.getProperty("user.dir");
        }
        File file = new File(path);
        try {
            ArrayList<String> files = new ArrayList<>();
            for (File subFile : file.listFiles()) {
                if (!subFile.isDirectory()) {
                    files.add(getPermissions(file) + " file   | " + subFile.getName());
                }
            }

            for (File subFile : file.listFiles()) {
                if (subFile.isDirectory()) {
                    files.add(getPermissions(file) + " folder | " + subFile.getName() + "/");
                }
            }


            Collections.reverse(files);
            files.add(getPermissions(file) + " folder | .");
            files.add(path);
            return files;
        } catch (NullPointerException e) {
            e.printStackTrace();
            return null;
        }
    }

    private String getPermissions(File file) {
        String permissions = "";

        if (file.canRead()) {
            permissions = permissions + "r";
        } else {
            permissions = permissions + "-";
        }

        if (file.canWrite()) {
            permissions = permissions + "w";
        } else {
            permissions = permissions + "-";
        }

        if (file.canExecute()) {
            permissions = permissions + "x";
        } else {
            permissions = permissions + "-";
        }

        if (file.isHidden()) {
            permissions = permissions + "h";
        } else {
            permissions = permissions + "-";
        }

        return permissions;
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

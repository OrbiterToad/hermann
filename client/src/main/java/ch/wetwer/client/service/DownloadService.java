package ch.wetwer.client.service;

import ch.wetwer.client.handler.ResponseSender;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

/**
 * @author Wetwer
 * @project server-control
 **/

public class DownloadService {

    public void download(String url, String fileName) {
        URL website;
        try {
            website = new URL(url);
            ReadableByteChannel rbc = Channels.newChannel(website.openStream());
            FileOutputStream fos = new FileOutputStream(fileName);
            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
            rbc.close();
            fos.close();
            new ResponseSender("response", "Downloaded File " + fileName);
            new ResponseSender().reset();
        } catch (FileNotFoundException e) {
            new ResponseSender("error", "Could not save file: " + fileName + " from " + url);
            new ResponseSender().reset();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

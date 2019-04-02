package ch.wetwer.client.handler;

import ch.wetwer.client.configure.ClientValues;
import ch.wetwer.client.service.WebHandler;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * @author Wetwer
 * @project server-control
 **/

public class ResponseSender {

    public ResponseSender() {

    }

    public ResponseSender(String type, String response) {
        try {
            WebHandler webHandler = new WebHandler(ClientValues.server + "/in/"
                    + URLEncoder.encode(ClientValues.pcName, "UTF-8")
                    + "?type=" + type
                    + "&response=" + URLEncoder.encode(response, "UTF-8"));
            webHandler.getContent();
        } catch (UnsupportedEncodingException | NullPointerException e) {
            e.printStackTrace();
        }
    }

    public void reset() {
        new ResponseSender("reset", "");
    }
}

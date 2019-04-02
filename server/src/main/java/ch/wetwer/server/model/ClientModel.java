package ch.wetwer.server.model;

import ch.wetwer.server.data.entity.Client;
import lombok.Data;

@Data
public class ClientModel {

    private Client client;

    private boolean hasimages;

    private boolean online;

}

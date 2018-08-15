package ch.lebois.server.model;

import ch.lebois.server.data.entity.Client;
import lombok.Data;

@Data
public class ClientModel {

    private Client client;

    private boolean hasimages;

    private boolean online;

}

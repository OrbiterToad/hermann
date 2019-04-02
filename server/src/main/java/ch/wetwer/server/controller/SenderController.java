package ch.wetwer.server.controller;

import ch.wetwer.server.data.entity.Client;
import ch.wetwer.server.data.entity.Message;
import ch.wetwer.server.data.repository.ClientRepository;
import ch.wetwer.server.data.repository.MessageRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("sender")
public class SenderController {

    private ClientRepository clientRepository;
    private MessageRepository messageRepository;

    public SenderController(ClientRepository clientRepository, MessageRepository messageRepository) {
        this.clientRepository = clientRepository;
        this.messageRepository = messageRepository;
    }

    @PostMapping("{clientId}")
    public String sendCommand(@PathVariable("clientId") Long clientId, @RequestParam("command") String command) {
        Client client = clientRepository.findClientById(clientId);
        client.setCommand(command);
        clientRepository.save(client);

        Message message = new Message();
        message.setClient(client);
        message.setMessage("> " + command);
        if (command.startsWith("chat")) {
            message.setType("message");
        } else {
            message.setType("command");
        }
        messageRepository.save(message);

        return "redirect:/client/" + clientId;
    }

    @GetMapping("{pcName}")
    public String getCommand(@PathVariable("pcName") String pcName, Model model) {
        model.addAttribute("command", clientRepository.findClientByName(pcName).getCommand());
        return "sender";
    }
}

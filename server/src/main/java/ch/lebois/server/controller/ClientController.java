package ch.lebois.server.controller;

import ch.lebois.server.data.entity.Client;
import ch.lebois.server.data.repository.ClientRepository;
import ch.lebois.server.data.repository.MessageRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("client")
public class ClientController {

    private ClientRepository clientRepository;
    private MessageRepository messageRepository;

    public ClientController(ClientRepository clientRepository, MessageRepository messageRepository) {
        this.clientRepository = clientRepository;
        this.messageRepository = messageRepository;
    }

    @GetMapping("{clientId}")
    public String getOneClient(@PathVariable("clientId") Long clientId,
                               @RequestParam(name = "more", required = false, defaultValue = "") String more,
                               Model model) {
        Client client = clientRepository.findClientById(clientId);

        model.addAttribute("client", client);
        if (more.equals("")) {
            model.addAttribute("messages", messageRepository.findTop40ByClientOrderByIdDesc(client));
        } else {
            model.addAttribute("messages", messageRepository.findMessageByClientOrderByIdDesc(client));
        }

        long tenAgo = System.currentTimeMillis() - 60000;
        if (client.getLastseen().getTime() < tenAgo) {
            model.addAttribute("online", false);
        } else {
            model.addAttribute("online", true);
        }

        return "client";
    }

    @PostMapping("{clientId}/clear")
    public String clearConsole(@PathVariable("clientId") Long clientId) {
        messageRepository.deleteByClient(clientRepository.findClientById(clientId));
        return "redirect:/client/" + clientId;
    }

    @PostMapping("{clientId}/nickname")
    public String setNickname(@PathVariable("clientId") Long clientId,
                              @RequestParam("nickname") String nickname) {
        Client client = clientRepository.findClientById(clientId);
        client.setNickname(nickname);
        clientRepository.save(client);
        return "redirect:/client/" + clientId;
    }

}

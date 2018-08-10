package ch.lebois.server.controller;

import ch.lebois.server.data.entity.Client;
import ch.lebois.server.data.repository.ClientRepository;
import ch.lebois.server.model.ClientModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("")
public class DashboardController {

    private ClientRepository clientRepository;

    public DashboardController(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @GetMapping
    public String getDashboard(Model model) {
        List<ClientModel> clients = new ArrayList<>();
        for (Client client : clientRepository.findClientsByOrderByLastseenDesc()) {
            ClientModel clientModel = new ClientModel();
            clientModel.setClient(client);

            long tenAgo = System.currentTimeMillis() - 60000;
            if (client.getLastseen().getTime() < tenAgo) {
                clientModel.setOnline(false);
            } else {
                clientModel.setOnline(true);
            }
            clients.add(clientModel);
        }

        model.addAttribute("clients", clients);
        return "dashboard";
    }

}

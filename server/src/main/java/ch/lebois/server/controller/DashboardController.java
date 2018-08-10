package ch.lebois.server.controller;

import ch.lebois.server.data.repository.ClientRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class DashboardController {

    private ClientRepository clientRepository;

    public DashboardController(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @GetMapping
    public String getDashboard(Model model) {
        model.addAttribute("clients", clientRepository.findClientsByOrderByLastseen());
        return "dashboard";
    }

}

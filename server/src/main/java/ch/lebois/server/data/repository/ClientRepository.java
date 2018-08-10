package ch.lebois.server.data.repository;

import ch.lebois.server.data.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    Client findClientById(Long id);

    Client findClientByName(String name);

    List<Client> findClientsByOrderByLastseenDesc();

}

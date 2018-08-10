package ch.lebois.server.data.repository;

import ch.lebois.server.data.entity.Client;
import ch.lebois.server.data.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {

    List<Message> findTop40ByClientOrderByIdDesc(Client client);

    List<Message> findMessageByClientOrderByIdDesc(Client client);

    @Transactional
    void deleteByClient(Client client);
}

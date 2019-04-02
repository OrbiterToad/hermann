package ch.wetwer.server.data.repository;

import ch.wetwer.server.data.entity.Client;
import ch.wetwer.server.data.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {

    List<Message> findTop40ByClientOrderByIdDesc(Client client);

    List<Message> findMessageByClientOrderByIdDesc(Client client);

    @Transactional
    void deleteByClient(Client client);
}

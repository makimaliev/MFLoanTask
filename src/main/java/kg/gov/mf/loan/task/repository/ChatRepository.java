package kg.gov.mf.loan.task.repository;

import kg.gov.mf.loan.task.model.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Long> {

    List<Chat> findAllByReceiverAndSenderOrSenderAndReceiver(String receiver,String sender,String sender1,String receiver1);
    List<Chat> findAllByReadedAndReceiver(int readed,String receiver);
}

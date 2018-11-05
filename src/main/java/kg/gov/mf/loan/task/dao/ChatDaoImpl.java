package kg.gov.mf.loan.task.dao;

import kg.gov.mf.loan.dao.GenericDaoImpl;
import kg.gov.mf.loan.task.model.Chat;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class ChatDaoImpl extends GenericDaoImpl<Chat> implements ChatDao
{
    @Override
    public List<Chat> getMessages(String sender, String receiver) {

        return entityManager.createQuery("select c from Chat c where ((c.sender = :sender OR c.sender = :receiver) AND (c.receiver = :receiver OR c.receiver = :sender)) order by c.id desc")
                .setParameter("sender", sender)
                .setParameter("receiver", receiver)
                .setMaxResults(10)
                .getResultList();
    }
}
